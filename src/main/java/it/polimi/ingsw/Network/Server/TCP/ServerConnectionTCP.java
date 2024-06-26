package it.polimi.ingsw.Network.Server.TCP;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.ClientToServer.LobbyCreationMessage;
import it.polimi.ingsw.Messages.MoveDeserializer;
import it.polimi.ingsw.Messages.ServerToClient.*;
import it.polimi.ingsw.Network.Server.*;
import it.polimi.ingsw.model.player.Player;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * class that handles the Socket connection between the server and the client
 * @author Eliahu Cohen
 *
 */
public class ServerConnectionTCP implements ServerConnection{
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
        if (clientSocket != null) {
            try {
                output = new PrintWriter(clientSocket.getOutputStream());
                input = new Scanner(clientSocket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    /**
     * @author Eliahu Cohen
     * @param pingCount number of pings server have sent till now
     */
    public void sendPing(int pingCount) {
        ServerMessage m=new PingFromServer(pingCount);

        sendMessage(m,namePlayer);

    }

    /**
     * method to close the connection between the server and the client
     * @author Eliahu Cohen
     *
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
     * method to send to the client a message using Json
     * @author Eliahu Cohen
     * @param message to send to the client
     * @param namePlayer name of the player that have to receive the message
     *
     */
    public void sendMessage(ServerMessage message,String namePlayer){
        if(message==null)return;
        Gson gson =new Gson();
        String m=gson.toJson(message);
        //    output.reset();
        if(output!=null) {
            output.println(m);
            output.flush();
        }

    }

    /**
     * Method to handle the received message from the client
     * @author Eliahu Cohen
     *
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
     * Method that response to the message received with an action on the server
     * @author Eliahu Cohen
     * @param message received from client
     *
     */
    private void messageParser(ClientMessage message){
        switch (message.getCategory()) {
            case CLOSE:{
                closeConnection();
                break;
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
                sendMessage(new ValidNicknameMessage(),namePlayer);
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
                sendMessage(new LobbyJoiningMessage(lobby.getIdLobby()),namePlayer);
                break;
            }
            case ENTER_LOBBY: {
                if(namePlayer==null)break;
                synchronized (Server.lobbies) {
                    //reconnected player
                    synchronized (Server.startedLobbies){
                        for(Lobby l:Server.startedLobbies){
                            if(l.getPlayer(namePlayer)!=null) {
                                l.reconnectPlayer(this, namePlayer);
                                l.getPlayer(namePlayer).setListener(virtualView);
                                lobby = l;
                                if (!checkReconnectedLobby())
                                    sendMessage(new LobbyJoiningMessage(lobby.getIdLobby()), namePlayer);
                                break;
                            }
                        }
                        if(lobby!=null)break;
                    }
                    //zero lobby
                    if (Server.lobbies.size()==0){
                        noLobbyInServer(message);
                        break;
                    }
                    //player already in the lobby
                    lobby = Server.lobbies.get(0);
                    if (lobby.getJoinedUsers().contains(message.getNickname())) {
                        alreadyExistentLobby(message);
                        break;
                    }
                }
                //player try to reconnect
                reconnectedPlayer(message);

                disconnectionHandler = new DisconnectionHandler(lobby);
                if (!checkLobbySpace()){
                    lobbyIsFull();
                    break;
                }

                lobby.addUser(this, message.getNickname(), virtualView);
                if(!checkCompletedLobby())
                    sendMessage(new LobbyJoiningMessage(lobby.getIdLobby()),namePlayer);
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
                if(lobby!=null){
                    sendMessage((ServerMessage) lobby.receiveMessage(message),namePlayer);
                }


        }
    }

    /**
     * method to check if the reconnected lobby is full
     * @author Eliahu Cohen
     * @return true if the lobby is full
     *
     */
    private boolean checkReconnectedLobby() {
        if(lobby.getNumPlayersLobby()==lobby.getJoinedUsers().size()){
            lobby.restartGame();
            return true;
        }
        return false;
    }


    /**
     * send an error message if there is a player logged with the nickname of the sender
     * @author Eliahu Cohen
     * @param message received from the client
     *
     */
    private void alreadyLoggedNickName(ClientMessage message) {
            ErrorMessage errorMessage=new ErrorMessage();
            errorMessage.setReturnMessage("Your nickname is already used. Please, retry.");
            sendMessage(errorMessage,message.getNickname());
    }

    /**
     * method that send an error message if the client tries to log out from an already started game
     * @author Eliahu Cohen
     *
     */
    private void gameAlreadyStarted() {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setReturnMessage("Game has already started: you can't logout until the game is finished!");
            sendMessage(errorMessage,namePlayer);

    }

    /**
     * Method that checks if the lobby is full
     * @author Eliahu Cohen
     *
     */
    private void lobbyIsFull() {

            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setReturnMessage("This lobby is full! Try to enter into another lobby or create a new one, yourself.");
            sendMessage(errorMessage,namePlayer);

        }

    /**
     * method that checks if the player was connected before
     * @author Eliahu Cohen
     * @param message received from client
     *
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
     * Method that response to an entrance message but without any lobby
     * @author Eliahu Cohen
     * @param message received from the client
     *
     */
    private void noLobbyInServer(ClientMessage message) {

            ErrorMessage errorMessage =new ErrorMessage();
            errorMessage.setReturnMessage("There is NO AVAILABLE lobby, please retry!");
            sendMessage(errorMessage,message.getNickname());

    }


    /**
     * method that checks if the lobby is now full
     * @author Eliahu Cohen
     *
     */
    private boolean checkCompletedLobby() {
        if(lobby.getNumPlayersLobby()==lobby.getJoinedUsers().size()){
            Server.startedLobbies.add(lobby);
            Server.lobbies.remove(lobby);
            lobby.startGameLobby();
            return true;
        }
        return false;
    }

    /**
     * Method that sends an error message because the client is already into a lobby
     * @author Eliahu Cohen
     * @param message received from client
     *
     */
    private void alreadyExistentLobby(ClientMessage message){
            if (lobby!= null && lobby.getJoinedUsers().contains(message.getNickname())) {
                ErrorMessage errorMessage=new ErrorMessage();
                errorMessage.setReturnMessage("If you want to create a new one, exit from the lobby you are already in, first.");
                sendMessage(errorMessage,namePlayer);
                return;
            }
            ErrorMessage errorMessage=new ErrorMessage();
            errorMessage.setReturnMessage("Lobby already present, please join that lobby");
            sendMessage(errorMessage,namePlayer);

    }


    public void addVirtualView(VirtualView virtualView){
        this.virtualView=virtualView;
        virtualView.setNickName(namePlayer);
    }


    /**
     * @author Eliahu Cohen
     * @return true if the lobby has <=4 users
     */
    private boolean checkLobbySpace() {
        return lobby.getJoinedUsers().size()<=4;
    }



    public String getNamePlayer() {
        return namePlayer;
    }

    /**
     * method that starts a ping exchange with the client and waits to the clients moves
     * @author Eliahu Cohen
     *
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
                   ping.interrupt();
                }
                sendPing(pingCount);
                pingCount++;

            }

        });
        ping.start();
        try{
        while(serverIsActive) {
            String s = input.nextLine();
            receiveMessage(s);
        }
           }catch (NoSuchElementException | IllegalStateException e){
               closeClientConnection();
           }



    }

    /**
     * method that end the game when a player crush
     * @author Eliahu Cohen
     *
     */
    private void closeClientConnection() {
        if(lobby!=null) {
            if (lobby.getStartedGame())
            {
                lobby.endGame();
                Server.startedLobbies.remove(lobby);
            }
            else{
                lobby.removePlayer(this,namePlayer);
            }
            System.out.println(namePlayer + " disconnected from the server");
            ping.interrupt();
            Server.connectedPlayers.remove(namePlayer);
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
