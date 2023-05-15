package it.polimi.ingsw.Network.Server.RMI;

import com.google.gson.Gson;
import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.ClientToServer.LobbyCreationMessage;
import it.polimi.ingsw.Messages.MoveDeserializer;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Messages.ServerToClient.ValidNicknameMessage;
import it.polimi.ingsw.Network.Client.RMI.ClientConnectionRMI;
import it.polimi.ingsw.Network.Server.Server;
import it.polimi.ingsw.Network.Server.ServerConnection;
import it.polimi.ingsw.Network.Server.DisconnectionHandler;
import it.polimi.ingsw.Network.Server.Lobby;
import it.polimi.ingsw.Network.Server.VirtualView;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.TimeUnit;

public class ServerConnectionRMI extends UnicastRemoteObject implements RemoteInterface, ServerConnection {
    private RMIConnection rmiConnection;
    private Lobby lobby;
    private static ClientConnectionRMI skeleton;
    private boolean serverIsActive;

    private String namePlayer=null;
    private DisconnectionHandler disconnectionHandler;
    private VirtualView virtualView;
    private boolean pingIsArrived =false;
    private Thread ping;

    protected ServerConnectionRMI() throws RemoteException, MalformedURLException, NotBoundException {
        skeleton =(ClientConnectionRMI) Naming.lookup("rmi://localhost:"+22011+"/RMIServer");
        serverIsActive=true;
        ping = new Thread(() -> {
            int pingCount=0;
            while (serverIsActive) {
                try {
                    //Metto a dormire thread per 5 secondi
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    sendPing();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


            }

        });
        ping.start();
    }

    //Metodo che riceve il messaggio da client
    public void sendMessage(String message) {
        ClientMessage m= (ClientMessage) MoveDeserializer.deserializeOutput(message);

        if (m != null) {
            messageParser(m);
        }
    }

    private void messageParser(ClientMessage message){
        switch (message.getCategory()) {
            case PINGTOSERVER: {
                message.dumpPingMessage();
                break;
            }
            case NICKNAME:{
                if(Server.connectedPlayers.contains(message.getNickname())){
                    //alreadyLoggedNickName(message);
                    break;
                }
                Server.connectedPlayers.add(message.getNickname());
                namePlayer=message.getNickname();
                sendMessage(new ValidNicknameMessage());
                break;
            }
            case CREATE_LOBBY: {
                if(lobby!=null||namePlayer==null){
                    //lreadyExistentLobby(message);
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
                        //noLobbyInServer(message);
                        break;
                    }
                    //player already in the lobby
                    if (lobby.getJoinedUsers().contains(message.getNickname())) {
                        //alreadyExistentLobby(message);
                        break;
                    }
                }
                //player try to reconnect
                //reconnectedPlayer(message);


                lobby = Server.lobbies.get(0);
                disconnectionHandler = new DisconnectionHandler(lobby);
               /* if (!checkLobbySpace()){
                    lobbyIsFull();
                    break;
                }*/

                lobby.addUser(this, message.getNickname(), virtualView);
                //checkCompletedLobby();
                break;
            }

            case LOGOUT_LOBBY: {
                if (lobby.getStartedGame()||namePlayer==null){
                    //gameAlreadyStarted();
                    break;
                }
                lobby.logoutFromLobby(namePlayer);
            }
            default: {
                if (lobby != null) {
                    sendMessage((ServerMessage) lobby.receiveMessage(message));
                }
            }
        }
    }


    @Override
    public void sendMessage(ServerMessage message) {
        Gson gson=new Gson();
        String s=gson.toJson(message);
        skeleton.sendMessage(s);

    }

    @Override
    public void login(String username, ClientConnectionRMI client) throws RemoteException {
       rmiConnection=new RMIConnection(client);

    }

    @Override
    public void disconnectMe() throws RemoteException {

    }
    public void addVirtualView(VirtualView virtualView){
        this.virtualView=virtualView;
    }

    public void sendPing() throws InterruptedException {
        boolean pingIsOk=false;
        pingIsOk=skeleton.getPing();
        TimeUnit.SECONDS.sleep(3);
        if(pingIsOk){
            System.out.println("ping arrived");
            return;
        }
        System.out.println("Connection crushed");
        //closeConnection();
        //notifyDisconnection();
    }

    public boolean getPing() {
        return true;

    }
}
