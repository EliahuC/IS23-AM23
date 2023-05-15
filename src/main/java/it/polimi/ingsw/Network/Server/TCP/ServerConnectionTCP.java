package it.polimi.ingsw.Network.Server.TCP;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.ClientToServer.LobbyCreationMessage;
import it.polimi.ingsw.Messages.MoveDeserializer;
import it.polimi.ingsw.Messages.ServerToClient.ErrorMessage;
import it.polimi.ingsw.Messages.ServerToClient.PingFromServer;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Messages.ServerToClient.ValidNicknameMessage;
import it.polimi.ingsw.Network.Server.*;
import it.polimi.ingsw.model.player.Player;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author Eliahu Cohen
 * class that handles the tcp connection between the server and the client
 */
public class ServerConnectionTCP implements ServerConnection,Runnable {
    private final Socket clientSocket;
    private Thread ping;
    private boolean serverIsActive;
    private String namePlayer=null;
    private Scanner input;
    private PrintWriter output;
    private VirtualView virtualView;


    private Lobby lobby;
    private DisconnectionHandler disconnectionHandler;


    public ServerConnectionTCP(Socket clientSocket) {
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



    /**
     * @author Eliahu Cohen
     * @param pingCount number of pings server have sent till now
     */
    public void sendPing(int pingCount) {
        ServerMessage m=new PingFromServer(pingCount);

        sendMessage(m);

    }

    /**
     * @author Eliahu Cohen
     * method to close the connection between the server and the client
     */
    private void closeConnection() {
        serverIsActive = false;
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author Eliahu Cohen
     * @param message to send to the client
     * method to send to the client a message using Json
     */
    public void sendMessage(ServerMessage message){
        Gson gson =new Gson();
        String m=gson.toJson(message);
        //    output.reset();
        output.println(m);
        output.flush();

    }

    /**
     * @author Eliahu Cohen
     */
    public void receiveMessage(String s) {

        ClientMessage message;
        try {
            message = (ClientMessage) MoveDeserializer.deserializeOutput(s);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return;
        }

        if (message != null)
            messageParser(message);
    }

    /**
     * @author Eliahu Cohen
     * @param message received from client
     * Method that response to the message received with an action on the server
     */
    private void messageParser(ClientMessage message){
        switch (message.getCategory()) {
            case CLOSE:{
                closeConnection();
            }
            case PINGTOSERVER: {
                message.dumpPingMessage();
                break;
            }
            case NICKNAME:{
                if(Server.connectedPlayers.contains(message.getNickname())){
                    alreadyLoggedNickName(message);
                    break;
                }
                Server.connectedPlayers.add(message.getNickname());
                namePlayer=message.getNickname();
                sendMessage(new ValidNicknameMessage());
                break;
            }
            case CREATE_LOBBY: {
                if(lobby!=null||namePlayer==null){
                    alreadyExistentLobby(message);
                    break;
                }
                lobby = new Lobby(((LobbyCreationMessage) message).getNumPlayers(), Server.idLobbies);
                Server.idLobbies++;
                disconnectionHandler = new DisconnectionHandler(lobby);
                lobby.addUser(this, namePlayer, virtualView);
                Server.lobbies.add(lobby);
            }
            case ENTER_LOBBY: {
                if(namePlayer==null)break;
                synchronized (Server.lobbies) {
                    //zero lobby
                    if (Server.lobbies.size()==0){
                        noLobbyInServer(message);
                        break;
                    }
                    //player already in the lobby
                    if (lobby.getJoinedUsers().contains(message.getNickname())) {
                        alreadyExistentLobby(message);
                        break;
                    }
                }
                //player try to reconnect
                reconnectedPlayer(message);


                lobby = Server.lobbies.get(0);
                disconnectionHandler = new DisconnectionHandler(lobby);
                if (!checkLobbySpace()){
                    lobbyIsFull();
                    break;
                }

                lobby.addUser(this, message.getNickname(), virtualView);
                checkCompletedLobby();
                break;
            }

            case LOGOUT_LOBBY: {
                if (namePlayer==null)break;
                if (lobby.getStartedGame()){
                    gameAlreadyStarted();
                    break;
                }
                lobby.logoutFromLobby(namePlayer);
            }
            default:
                if(lobby!=null)
                    sendMessage((ServerMessage) lobby.receiveMessage(message));
        }
    }

    /**
     * @author Eliahu Cohen
     * @param message received from the client
     * send an error message if there is a player logged with the nickname of the sender
     */
    private void alreadyLoggedNickName(ClientMessage message) {
            ErrorMessage errorMessage=new ErrorMessage();
            errorMessage.setReturnMessage("nickname already used");
            sendMessage(errorMessage);
    }

    /**
     * @author Eliahu Cohen
     * method that send an error message if the client tryies to logout from an already started game
     */
    private void gameAlreadyStarted() {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setReturnMessage("Game already started,you can't logout since the game is finished");
            sendMessage(errorMessage);

    }

    /**
     * @author Eliahu Cohen
     * Method that checks if the lobby is full
     */
    private void lobbyIsFull() {

            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setReturnMessage("the Lobby is full");
            sendMessage(errorMessage);

        }

    /**
     * @author Eliahu Cohen
     * @param message received from client
     * method that checks if the player was connected before
     */
    private void reconnectedPlayer(ClientMessage message) {
        for (Lobby l : Server.startedLobbies) {
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

    /**
     * @author Eliahu Cohen
     * @param message received from the client
     * Method that responce to a entrance message but without any lobby
     */
    private void noLobbyInServer(ClientMessage message) {

            ErrorMessage errorMessage =new ErrorMessage();
            errorMessage.setReturnMessage("There is no available lobby, create a new one using the command:\n" +
                    "/CREATE_LOBBY <your nickname> <number of players>\n" +
                    "Remember that the number of players can only be 2, 3 or 4!");
            sendMessage(errorMessage);

    }


    /**
     * @author Eliahu Cohen
     * method that checks if the lobby is now full
     */
    private void checkCompletedLobby() {
        if(lobby.getNumPlayersLobby()==lobby.getJoinedUsers().size()){
            Server.startedLobbies.add(lobby);
            Server.lobbies.remove(lobby);
            lobby.startGameLobby();
        }
    }

    /**
     * @author Eliahu Cohen
     * @param message received from client
     * Method that sends an error message because the client is already into a lobby
     */
    private void alreadyExistentLobby(ClientMessage message){
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


    public void addVirtualView(VirtualView virtualView){
        this.virtualView=virtualView;
    }


    /**
     * @author Eliahu Cohen
     * @return true if the lobby has <=4 users
     */
    private boolean checkLobbySpace() {
        return lobby.getJoinedUsers().size()<=4;
    }
  /*  private void asyncSendMessage(ServerMessage m){
        new Thread(()->sendMessage(m)).start();
    }*/


    public String getNamePlayer() {
        return namePlayer;
    }

    /**
     * @author Eliahu Cohen
     * method that starts a ping exchange with the client and waits to the clients moves
     */
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

        while(serverIsActive){
            String s= input.nextLine();
            receiveMessage( s);

        }

    }
}
