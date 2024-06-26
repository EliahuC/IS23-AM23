package it.polimi.ingsw.Network.Server.RMI;

import com.google.gson.Gson;
import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.ClientToServer.LobbyCreationMessage;

import it.polimi.ingsw.Messages.MoveDeserializer;
import it.polimi.ingsw.Messages.ServerToClient.ErrorMessage;
import it.polimi.ingsw.Messages.ServerToClient.LobbyJoiningMessage;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Messages.ServerToClient.ValidNicknameMessage;

import it.polimi.ingsw.Network.Client.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.Network.Server.Server;
import it.polimi.ingsw.Network.Server.ServerConnection;
import it.polimi.ingsw.Network.Server.DisconnectionHandler;
import it.polimi.ingsw.Network.Server.Lobby;
import it.polimi.ingsw.Network.Server.VirtualView;
import it.polimi.ingsw.model.player.Player;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * RMI connection handler
 * @author Eliahu Cohen
 *
 */
public class ServerConnectionRMI extends UnicastRemoteObject implements RemoteInterface, ServerConnection {
    private Lobby lobby;

    private boolean serverIsActive;

    private String namePlayer=null;
    private DisconnectionHandler disconnectionHandler;
    private final ArrayList<VirtualView> virtualViews=new ArrayList<>();

    public ServerConnectionRMI() throws RemoteException {
        super();
        this.run();
    }

    /**
     * @author Eliahu Cohen
     * @param message received from client
     * @param client connection to save.
     */
    public synchronized void receiveMessage(String message, RemoteInterfaceClient client) {
        ClientMessage m= (ClientMessage) MoveDeserializer.deserializeOutput(message);
        if (m != null) {
            messageParser(m, client);
        }
        lobby=null;
    }

    /**
     * Method that response to the message received with an action on the server
     * @author Eliahu Cohen
     * @param message received from client
     * @param client connection
     *
     */
    private void messageParser(ClientMessage message, RemoteInterfaceClient client){
        lobby=lobbyResearch(message.getNickname());
        namePlayer=message.getNickname();
        switch (message.getCategory()) {

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
                Server.rmiConnections.put(message.getNickname(), client);
                namePlayer=message.getNickname();
                sendMessage(new ValidNicknameMessage(),namePlayer);
                System.out.println("ClientRMI connected");
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
                VirtualView virtualView=new VirtualView(this,namePlayer);
                virtualViews.add(virtualView);
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
                                VirtualView virtualView = new VirtualView(this, namePlayer);
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

                VirtualView virtualView=new VirtualView(this,namePlayer);
                virtualViews.add(virtualView);
                lobby.addUser(this, message.getNickname(), virtualView);
                if(!checkCompletedLobby())
                    sendMessage(new LobbyJoiningMessage(lobby.getIdLobby()),namePlayer);
                break;
            }

            case LOGOUT_LOBBY: {
                if (lobby.getStartedGame()||namePlayer==null){
                    gameAlreadyStarted();
                    break;
                }
                lobby.logoutFromLobby(namePlayer);
            }
            default: {
                if (lobby != null) {
                    sendMessage((ServerMessage) lobby.receiveMessage(message),namePlayer);
                }
            }
        }
    }

    /**
     * @author Eliahu Cohen
     * @param nickname of the player
     * @return a lobby where there is a player with this nickname
     */
    private Lobby lobbyResearch(String nickname) {
        for(Lobby l: Server.lobbies){
            if(l.getJoinedUsers().contains(nickname)){
                return l;
            }
        }
        for(Lobby l: Server.startedLobbies){
            if(l.getJoinedUsers().contains(nickname)){
                return l;
            }
        }
        return null;
    }


    @Override
    public String getNamePlayer() {
        return namePlayer;
    }

    /**
     * Method used to send messages to the clients
     * @author Eliahu Cohen
     * @param message to send to the client
     * @param username of the client that have to receive the message
     *
     */
    public  void sendMessage(ServerMessage message, String username) {
        if(message==null)return;
        Gson gson=new Gson();
        String s=gson.toJson(message);

        try {
            Server.rmiConnections.get(username).receiveMessage(s);
        } catch (RemoteException e) {
            closeConnection(username);
        }


    }




    /**
     * send an error message if there is a player logged with the nickname of the sender
     * @author Eliahu Cohen
     * @param message received from the client
     *
     */
    private void alreadyLoggedNickName(ClientMessage message) {
        ErrorMessage errorMessage=new ErrorMessage();
        errorMessage.setReturnMessage("nickname already used");
        sendMessage(errorMessage,message.getNickname());
    }
    /**
     * method that send an error message if the client tryies to logout from an already started game
     * @author Eliahu Cohen
     *
     */

    private void gameAlreadyStarted() {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setReturnMessage("Game already started,you can't logout since the game is finished");
        sendMessage(errorMessage,namePlayer);

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
     * Method that checks if the lobby is full
     * @author Eliahu Cohen
     *
     */
    private void lobbyIsFull() {

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setReturnMessage("the Lobby is full");
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
        if (lobby.getJoinedUsers().contains(message.getNickname())) {
            ErrorMessage errorMessage=new ErrorMessage();
            errorMessage.setReturnMessage("You are already part of a lobby! Please, log out if you want to create a new lobby.");
            sendMessage(errorMessage,namePlayer);
            return;
        }
        ErrorMessage errorMessage=new ErrorMessage();
        errorMessage.setReturnMessage("Lobby already present, please join that lobby.");
        sendMessage(errorMessage, namePlayer);

    }
    /**
     * @author Eliahu Cohen
     * @return true if the lobby has <=4 users
     */
    private boolean checkLobbySpace() {
        return lobby.getJoinedUsers().size()<=4;
    }




    /**
     * Method used to send a ping to the client
     * @author Eliahu Cohen
     * @throws Exception caused from problem with the connection
     *
     */
    public void sendPing() {
        try {
            for (String s : Server.rmiConnections.keySet()) {
                boolean pingIsOk = false;
                try {
                    pingIsOk = Server.rmiConnections.get(s).getPing();
                } catch (RemoteException e) {
                    closeConnection(s);
                }
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    System.out.println(s + " connection crashed");
                    closeConnection(s);
                }
            }
        }catch (ConcurrentModificationException e){
            sendPing();
        }
    }
    /**
     * method that end the game if a player crash
     * @author Eliahu Cohen
     *
     */
    private void closeConnection(String s) {

        Server.connectedPlayers.remove(s);
        Server.rmiConnections.remove(s);
        System.out.println(s+ " disconnected from the server");
        for (Lobby l : Server.lobbies) {
            if(l.getJoinedUsers().contains(s)){
                lobby=l;
                if (lobby.getStartedGame())
                {
                    lobby.endGame();
                    Server.startedLobbies.remove(lobby);
                }
                else{
                    lobby.removePlayer(this,namePlayer);
                    Server.lobbies.remove(lobby);
                }
                lobby=null;
                return;
            }
        }

    }
    /**
     * @author Eliahu Cohen
     * @return true to notify the client the ping-pong is still on
     */
    public boolean getPing() {
        return true;

    }

    /**
     * Method to start the connection with the client and
     * Start the ping exchange with the client
     * @author Eliahu Cohen
     *
     */
    public void run(){

        serverIsActive=true;
        Thread ping = new Thread(() -> {
            while (serverIsActive) {
                try {
                    //Metto a dormire thread per 5 secondi
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sendPing();
            }

        });
        ping.start();
    }

}
