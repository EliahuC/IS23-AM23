package it.polimi.ingsw.Network.Server.TCP;

import com.google.gson.Gson;
import it.polimi.ingsw.Network.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Network.Messages.ClientToServer.LobbyCreationMessage;
import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.Network.Messages.ServerToClient.ErrorMessage;
import it.polimi.ingsw.Network.Messages.ServerToClient.PingFromServer;
import it.polimi.ingsw.Network.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.model.player.Player;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class ServerConnectionToClient implements Runnable {
    private final Socket clientSocket;
    private final Thread ping;
    private boolean serverIsActive;
    private String namePlayer;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private VirtualView virtualView;
    private final Gson gson=new Gson();
    private static final ArrayList<Lobby> lobbies=new ArrayList<>();
    private static final ArrayList<Lobby> startedLobbies=new ArrayList<>();
    private Lobby lobby;
    private DisconnectionHandler disconnectionHandler;

    public ServerConnectionToClient(Socket clientSocket) {
        this.clientSocket = clientSocket;

        this.serverIsActive = true;
        try {
            output = new ObjectOutputStream(clientSocket.getOutputStream());
            input = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            clientSocket.setSoTimeout(3600);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        ping = new Thread(() -> {
            while (serverIsActive) {
                try {
                    //Metto a dormire thread per 15 secondi
                    TimeUnit.SECONDS.sleep(15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            sendPing();
        });
        ping.start();
        ServerMessage m=new ServerMessage(Message.MessageCategory.RETURN_MESSAGE);
        if(lobbies.size()==0)m.addReturnMessage("There isn't any lobby where you can join, please create your own lobby");
        else {
            m.addReturnMessage("There are some lobby where you can join, enter this lobby / create your own lobby");
        }
        sendMessage(m);
    }

    protected synchronized static void removeVoidLobby(Lobby lobby) {
        lobbies.remove(lobby);
    }

    private void sendPing() {
        ServerMessage m=new PingFromServer();
        asyncSendMessage(m);

    }

    private void closeConnection() {
        serverIsActive = false;
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public synchronized void sendMessage(ServerMessage message){
        String sms;
        sms=gson.toJson(message);
        try{
            output.reset();
            output.writeObject(sms);
            output.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public synchronized void receiveMessage() throws IOException, ClassNotFoundException {
        String s= (String) input.readObject();
        ClientMessage message = gson.fromJson(s, ClientMessage.class);
        switch (message.getCategory()) {
            case CREATE_LOBBY: {
                    if(lobby!=null){
                        if (lobby.getJoinedUsers().contains(message.getNickname())) {
                        ErrorMessage errorMessage=new ErrorMessage();
                        errorMessage.addReturnMessage("You are already part of a lobby,please log out if you want to create a new lobby.");
                        sendMessage(errorMessage);
                        break;
                        }
                        ErrorMessage errorMessage=new ErrorMessage();
                        errorMessage.addReturnMessage("Lobby already present, please join that lobby");
                        sendMessage(errorMessage);
                        break;
                    }

                lobby=new Lobby(((LobbyCreationMessage) message).getNumPlayers());
                disconnectionHandler=new DisconnectionHandler(lobby);
                lobby.addUser(this, message.getNickname(),virtualView);
                namePlayer= message.getNickname();
                lobbies.add(lobby);
            }
            case ENTER_LOBBY: {
                synchronized (lobbies){
                    //nessuna lobby presente
                if (lobbies.size()==0){
                    ErrorMessage errorMessage =new ErrorMessage();
                    errorMessage.addReturnMessage("There isn't any lobby, please create yours");
                    sendMessage(errorMessage);
                    return;
                }
                //player gia in una lobby
                if(lobby!=null){
                    ErrorMessage errorMessage =new ErrorMessage();
                    errorMessage.addReturnMessage("you are already part of the lobby");
                    sendMessage(errorMessage);
                    return;
                }
                //player che prova a riconnettersi
                for(Lobby l:startedLobbies){
                    for(Player p:l.getDisconnectedPlayers()){
                        if(Objects.equals(p.getNickName(), message.getNickname())&& !l.getFullLobby()){
                            lobby=l;
                            disconnectionHandler=new DisconnectionHandler(lobby);
                            disconnectionHandler.clientReconnection(message.getNickname());
                            checkCompletedLobby();
                            break;
                        }
                    }
                }

                lobby=lobbies.get(0);
                disconnectionHandler=new DisconnectionHandler(lobby);
                if (!checkLobbySpace()) {
                    ErrorMessage errorMessage=new ErrorMessage();
                    errorMessage.addReturnMessage("the Lobby is full");
                    sendMessage(errorMessage);
                    break;
                }

                if (lobby.getJoinedUsers().contains(message.getNickname())) {
                    ErrorMessage errorMessage=new ErrorMessage();
                    errorMessage.addReturnMessage("Nickname already used in the lobby, please choose an other nickname");
                    sendMessage(errorMessage);
                    break;
                }

                lobby.addUser(this,message.getNickname(),virtualView);
                checkCompletedLobby();
                break;
             }
            }
            case LOGOUT_LOBBY:{
                if(lobby.getStartedGame()){
                    ErrorMessage errorMessage=new ErrorMessage();
                    errorMessage.addReturnMessage("Game already started,you can't logout since the game is finished");
                    sendMessage(errorMessage);
                    break;
                }
                lobby.logoutFromLobby(namePlayer);
            }
            default: sendMessage((ServerMessage) lobby.receiveMessage(message));
        }
    }

    private void checkCompletedLobby() {
        if(lobby.getNumPlayersLobby()==lobby.getJoinedUsers().size()){
            startedLobbies.add(lobby);
            lobbies.remove(lobby);
            lobby.startGameLobby();
        }
    }


    public void addVirtualView(VirtualView virtualView){
        this.virtualView=virtualView;
    }




    private boolean checkLobbySpace() {
        return lobby.getJoinedUsers().size()<=4;
    }
    private void asyncSendMessage(ServerMessage m){
        new Thread(()->sendMessage(m)).start();
    }

    public  ArrayList<Lobby> getStartedLobbies(){
        return new ArrayList<>(startedLobbies);
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    @Override
    public void run() {
        try{
            while(serverIsActive){
                receiveMessage();

            }
        }catch(IOException e){
            closeConnection();
            //if(namePlayer!=null)
                //HandlerDisconnessionePlayers
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }

    }
}
