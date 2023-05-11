package it.polimi.ingsw.Network.Server.TCP;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.ClientToServer.LobbyCreationMessage;
import it.polimi.ingsw.Messages.ServerToClient.ErrorMessage;
import it.polimi.ingsw.Messages.ServerToClient.PingFromServer;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.model.player.Player;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ServerConnectionToClient implements Runnable {
    private final Socket clientSocket;
    private Thread ping;
    private boolean serverIsActive;
    private String namePlayer;
    private Scanner input;
    private PrintWriter output;
    private VirtualView virtualView;

    private static final ArrayList<Lobby> lobbies=new ArrayList<>();
    private static final ArrayList<Lobby> startedLobbies=new ArrayList<>();
    private Lobby lobby;
    private DisconnectionHandler disconnectionHandler;
    private static Integer idLobbies =0;

    public ServerConnectionToClient(Socket clientSocket) {
        this.clientSocket = clientSocket;

        this.serverIsActive = true;
        try {
            output = new PrintWriter(clientSocket.getOutputStream());
            input = new Scanner(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*try {
            clientSocket.setSoTimeout(3600);
        } catch (SocketException e) {
            e.printStackTrace();
        }*/

    }

    protected synchronized static void removeVoidLobby(Lobby lobby) {
        lobbies.remove(lobby);
    }

    private void sendPing(int pingCount) {
        ServerMessage m=new PingFromServer(pingCount);

        sendMessage(m);

    }

    private void closeConnection() {
        serverIsActive = false;
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendMessage(ServerMessage message){
        Gson gson =new Gson();
        String m=gson.toJson(message);
        //    output.reset();
        output.println(m);
        output.flush();

    }


    public void receiveMessage() throws IOException, ClassNotFoundException {
        Gson gson =new Gson();
        String s = input.nextLine();
        //System.out.println(s);
        ClientMessage message=null;
        try{
            Object obj = gson.fromJson(s, Object.class);
            System.out.println(obj.toString());
            message = gson.fromJson(s, ClientMessage.class);
        }catch (JsonSyntaxException e){
           // e.printStackTrace();
           // return;
        }

        if(message!=null)
            messageParser(message);
    }
    private void messageParser(ClientMessage message){
        switch (message.getCategory()) {
            case PING: {
                message.dumpPingMessage();
                break;
            }
            case CREATE_LOBBY: {
                alreadyExistentLobby(message);
                lobby = new Lobby(((LobbyCreationMessage) message).getNumPlayers(), idLobbies);
                idLobbies++;
                disconnectionHandler = new DisconnectionHandler(lobby);
                lobby.addUser(this, message.getNickname(), virtualView);
                namePlayer = message.getNickname();
                lobbies.add(lobby);
            }
            case ENTER_LOBBY: {
                synchronized (lobbies) {
                    //nessuna lobby presente
                    noLobbyInServer(message);
                    //player gia in una lobby
                    alreadyExistentLobby(message);
                }
                //player che prova a riconnettersi
                reconnectedPlayer(message);


                lobby = lobbies.get(0);
                disconnectionHandler = new DisconnectionHandler(lobby);
                lobbyIsFull();

                nickNameAlreadyUsed(message);
                lobby.addUser(this, message.getNickname(), virtualView);
                checkCompletedLobby();
                break;
            }

            case LOGOUT_LOBBY: {
                gameAlreadyStarted();
                lobby.logoutFromLobby(namePlayer);
            }
            default:
                sendMessage((ServerMessage) lobby.receiveMessage(message));
        }
    }



    private void gameAlreadyStarted() {
        if (lobby.getStartedGame()) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setReturnMessage("Game already started,you can't logout since the game is finished");
            sendMessage(errorMessage);
        }
    }

    private void nickNameAlreadyUsed(ClientMessage message) {
        if (lobby.getJoinedUsers().contains(message.getNickname())) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setReturnMessage("Nickname already used in the lobby, please choose an other nickname");
            sendMessage(errorMessage);

        }
    }

    private void lobbyIsFull() {
        if (!checkLobbySpace()) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setReturnMessage("the Lobby is full");
            sendMessage(errorMessage);

        }
    }

    private void reconnectedPlayer(ClientMessage message) {
        for (Lobby l : startedLobbies) {
            for (Player p : l.getDisconnectedPlayers()) {
                if (Objects.equals(p.getNickName(), message.getNickname()) && !l.getFullLobby()) {
                    lobby = l;
                    disconnectionHandler = new DisconnectionHandler(lobby);
                    disconnectionHandler.clientReconnection(message.getNickname());
                    checkCompletedLobby();
                    break;
                }
            }
        }
    }

    private void noLobbyInServer(ClientMessage message) {
        if (lobbies.size()==0){
            ErrorMessage errorMessage =new ErrorMessage();
            errorMessage.setReturnMessage("There isn't any lobby, please create yours");
            sendMessage(errorMessage);
            return;
        }
    }



    private void checkCompletedLobby() {
        if(lobby.getNumPlayersLobby()==lobby.getJoinedUsers().size()){
            startedLobbies.add(lobby);
            lobbies.remove(lobby);
            lobby.startGameLobby();
        }
    }

    private void alreadyExistentLobby(ClientMessage message){
        if(lobby!=null){
            if (lobby.getJoinedUsers().contains(message.getNickname())) {
                ErrorMessage errorMessage=new ErrorMessage();
                errorMessage.setReturnMessage("You are already part of a lobby,please log out if you want to create a new lobby.");
                sendMessage(errorMessage);
                return;
            }
            ErrorMessage errorMessage=new ErrorMessage();
            errorMessage.setReturnMessage("Lobby already present, please join that lobby");
            sendMessage(errorMessage);
        }
    }


    public void addVirtualView(VirtualView virtualView){
        this.virtualView=virtualView;
    }




    private boolean checkLobbySpace() {
        return lobby.getJoinedUsers().size()<=4;
    }
  /*  private void asyncSendMessage(ServerMessage m){
        new Thread(()->sendMessage(m)).start();
    }*/

    public  ArrayList<Lobby> getStartedLobbies(){
        return new ArrayList<>(startedLobbies);
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    @Override
    public void run() {
        ping = new Thread(() -> {
            int pingCount=0;
            while (serverIsActive) {
                try {
                    //Metto a dormire thread per 15 secondi
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sendPing(pingCount);
                pingCount++;

            }

        });
        ping.start();

        try{
            while(serverIsActive){
                receiveMessage();

            }
        }catch(IOException e){
            closeConnection();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }

    }
}
