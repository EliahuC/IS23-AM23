package it.polimi.ingsw.Network.Server.RMI;

import com.google.gson.Gson;
import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.ClientToServer.LobbyCreationMessage;
import it.polimi.ingsw.Messages.MoveDeserializer;
import it.polimi.ingsw.Messages.ServerToClient.ErrorMessage;
import it.polimi.ingsw.Messages.ServerToClient.LobbyJoiningMessage;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Messages.ServerToClient.ValidNicknameMessage;
import it.polimi.ingsw.Network.Client.RMI.ClientConnectionRMI;
import it.polimi.ingsw.Network.Client.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.Network.Server.Server;
import it.polimi.ingsw.Network.Server.ServerConnection;
import it.polimi.ingsw.Network.Server.DisconnectionHandler;
import it.polimi.ingsw.Network.Server.Lobby;
import it.polimi.ingsw.Network.Server.VirtualView;
import it.polimi.ingsw.model.player.Player;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class ServerConnectionRMI extends UnicastRemoteObject implements RemoteInterface, ServerConnection {
    private Lobby lobby;
    private static ClientConnectionRMI skeleton;
    private boolean serverIsActive;

    private String namePlayer=null;
    private DisconnectionHandler disconnectionHandler;
    private VirtualView virtualView;
    private boolean pingIsArrived =false;
    private Thread ping;

    public ServerConnectionRMI() throws RemoteException {
        super();
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
     * @author Eliahu Cohen
     * @param message received from client
     * @param client connection
     * Method that response to the message received with an action on the server
     */
    private void messageParser(ClientMessage message, RemoteInterfaceClient client){
        lobby=lobbyResearch(message);
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
                    //zero lobby
                    if (Server.lobbies.size()==0){
                        noLobbyInServer(message);
                        break;
                    }
                    //player already in the lobby
                    if (lobby!=null&&lobby.getJoinedUsers().contains(message.getNickname())) {
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

    private Lobby lobbyResearch(ClientMessage message) {
        for(Lobby l: Server.lobbies){
            if(l.getJoinedUsers().contains(message.getNickname())){
                return l;
            }
        }
        for(Lobby l: Server.startedLobbies){
            if(l.getJoinedUsers().contains(message.getNickname())){
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
     * @author Eliahu Cohen
     * @param message to send to the client
     * @param username of the client that have to receive the message
     * Method used to send messages to the clients
     */
    public synchronized void sendMessage(ServerMessage message, String username) {
        Gson gson=new Gson();
        String s=gson.toJson(message);
        try {
            Server.rmiConnections.get(username).receiveMessage(s);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
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
        sendMessage(errorMessage,namePlayer);
    }
    /**
     * @author Eliahu Cohen
     * method that send an error message if the client tryies to logout from an already started game
     */

    private void gameAlreadyStarted() {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setReturnMessage("Game already started,you can't logout since the game is finished");
        sendMessage(errorMessage,namePlayer);

    }

    /**
     * @author Eliahu Cohen
     * Method that checks if the lobby is full
     */
    private void lobbyIsFull() {

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setReturnMessage("the Lobby is full");
        sendMessage(errorMessage,namePlayer);

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
     * Method that response to an entrance message but without any lobby
     */
    private void noLobbyInServer(ClientMessage message) {

        ErrorMessage errorMessage =new ErrorMessage();
        errorMessage.setReturnMessage("There is no available lobby, create a new one using the command:\n" +
                "/CREATE_LOBBY <your nickname> <number of players>\n" +
                "Remember that the number of players can only be 2, 3 or 4!");
        sendMessage(errorMessage,namePlayer);

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
            sendMessage(errorMessage,namePlayer);
            return;
        }
        ErrorMessage errorMessage=new ErrorMessage();
        errorMessage.setReturnMessage("Lobby already present, please join that lobby");
        sendMessage(errorMessage, namePlayer);

    }
    /**
     * @author Eliahu Cohen
     * @return true if the lobby has <=4 users
     */
    private boolean checkLobbySpace() {
        return lobby.getJoinedUsers().size()<=4;
    }


    public void addVirtualView(VirtualView virtualView){
        this.virtualView=virtualView;
    }

    /**
     * @author Eliahu Cohen
     * @throws InterruptedException
     * Method used to send a ping to the client
     */
    public void sendPing() throws InterruptedException, RemoteException {
        for (String s : Server.rmiConnections.keySet()) {
            boolean pingIsOk = false;
            pingIsOk = Server.rmiConnections.get(s).getPing();
            TimeUnit.SECONDS.sleep(3);
            if (pingIsOk) {
                System.out.println("ping arrived");
                return;
            }
            System.out.println("Connection crashed");
            closeConnection(s);

        }
    }

    private void closeConnection(String s) {
        for (Lobby l : Server.lobbies) {
            if(l.getJoinedUsers().contains(s)){
                lobby=l;
                lobby.endGame();
                return;
            }
        }
        for (Lobby l : Server.startedLobbies) {
            if(l.getJoinedUsers().contains(s)){
                lobby=l;
                lobby.endGame();
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
     * @author Eliahu Cohen
     * Method to start the connection with the client and
     * Start the ping exchange with the client
     *
     */
    public void run(){

        serverIsActive=true;
        ping = new Thread(() -> {
            while (serverIsActive) {
                try {
                    //Metto a dormire thread per 5 secondi
                    TimeUnit.SECONDS.sleep(15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    sendPing();
                } catch (InterruptedException | RemoteException e) {
                    throw new RuntimeException(e);
                }


            }

        });
        ping.start();
    }

}
