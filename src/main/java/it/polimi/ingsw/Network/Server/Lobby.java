package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.Network.Messages.ServerToClient.ErrorMessage;
import it.polimi.ingsw.Network.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Network.Messages.ServerToClient.StartingGameMessage;
import it.polimi.ingsw.Network.Messages.ServerToClient.ValidMoveMessage;
import it.polimi.ingsw.controller.ControllerCoordinator;

import java.util.ArrayList;

public class Lobby {
    private final Integer NumPlayersLobby;
    private final ArrayList<ServerConnectionToClient> connections;
    private final ControllerCoordinator controllerCoordinator;
    private final ArrayList<String> joinedUsers;
    private Boolean startedGame=false;
    public Lobby(Integer numPlayersLobby){
        this.NumPlayersLobby=numPlayersLobby;
        connections=new ArrayList<>();
        this.joinedUsers=new ArrayList<>();
        this.controllerCoordinator=new ControllerCoordinator();
    }
    public synchronized void addUser(ServerConnectionToClient serverConnectionToClient, String s, VirtualView view){
        connections.add(serverConnectionToClient);
        joinedUsers.add(s);
        controllerCoordinator.joinPlayer(s,view);
        if(controllerCoordinator.getConnectedPlayers().size()==NumPlayersLobby)startGameLobby();
    }

    public synchronized ArrayList<String> getJoinedUsers() {
        return joinedUsers;
    }

    public synchronized Message receiveMessage(ClientMessage message){
        if(message.getCategory()== Message.MessageCategory.START_GAME){
            if(joinedUsers.size()!=1){
                startGameLobby();
                return new ValidMoveMessage();
            }
            return new ErrorMessage();
        }
        return controllerCoordinator.setMessage(message);
    }
    public synchronized void logoutFromLobby(String s){
        joinedUsers.remove(s);
        controllerCoordinator.getConnectedPlayers().remove(s);
    }

    public synchronized void startGameLobby(){
        startedGame=true;
        controllerCoordinator.startGame();
        sendMessageToAllTheLobby(new StartingGameMessage());
    }

    public synchronized Boolean getStartedGame() {
        return startedGame;
    }

    public Integer getNumPlayersLobby() {
        return NumPlayersLobby;
    }

    public ArrayList<ServerConnectionToClient> getConnections() {
        return connections;
    }
    public synchronized void sendMessageToAllTheLobby(ServerMessage message){
        for(ServerConnectionToClient s:connections){
            s.sendMessage(message);
        }
    }

}
