package it.polimi.ingsw.Network.Server;

import com.google.gson.Gson;
import it.polimi.ingsw.Network.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Network.Messages.ClientToServer.LobbyCreationMessage;
import it.polimi.ingsw.Network.Messages.ServerToClient.ErrorMessage;
import it.polimi.ingsw.Network.Messages.ServerToClient.PingMessage;
import it.polimi.ingsw.Network.Messages.ServerToClient.ServerMessage;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class ServerConnectionToClient implements Runnable {
    private final Socket clientSocket;
    private final Thread ping;
    private boolean serverIsActive;
    private String namePlayer;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private VirtualView virtualView;
    private Gson gson;
    private static final ArrayList<Lobby> lobbies=new ArrayList<>();
    private static final ArrayList<Lobby> startedLobbies=new ArrayList<>();
    private Lobby lobby;

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
                    //Metto a dormire thread per 30 secondi
                    TimeUnit.SECONDS.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            sendPing();
        });
        ping.start();
    }

    private void sendPing() {
        ServerMessage m=new PingMessage();
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
                    ErrorMessage errorMessage=new ErrorMessage();
                    errorMessage.addReturnMessage("Lobby already present, please join that lobby");
                    sendMessage(errorMessage);
                    break;
                }
                if (lobby.getJoinedUsers().contains(message.getNickname())) {
                    ErrorMessage errorMessage=new ErrorMessage();
                    errorMessage.addReturnMessage("You are already part of the lobby");
                    sendMessage(errorMessage);
                    break;
                }
                lobby=new Lobby(((LobbyCreationMessage) message).getNumPlayers());
                lobby.addUser(message.getNickname(),virtualView);
                lobbies.add(lobby);
            }
            case ENTER_LOBBY: {
                synchronized (lobbies){
                lobby=lobbies.get(0);
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

                lobby.addUser(message.getNickname(),virtualView);
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
                lobby.logoutFromLobby(message.getNickname());
            }
            default: lobby.receiveMessage(message);
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


    @Override
    public void run() {

    }
}
