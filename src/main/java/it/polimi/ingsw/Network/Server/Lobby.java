package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.Network.Messages.ServerToClient.ErrorMessage;
import it.polimi.ingsw.Network.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Network.Messages.ServerToClient.StartingGameMessage;
import it.polimi.ingsw.Network.Messages.ServerToClient.ValidMoveMessage;
import it.polimi.ingsw.controller.ControllerCoordinator;
import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;

public class Lobby {
    private final Integer NumPlayersLobby;
    private final ArrayList<ServerConnectionToClient> connections;
    private final ControllerCoordinator controllerCoordinator;
    private final ArrayList<String> joinedUsers;
    private Boolean startedGame=false;
    private Boolean fullLobby=false;
    public Lobby(Integer numPlayersLobby){
        this.NumPlayersLobby=numPlayersLobby;
        connections=new ArrayList<>();
        this.joinedUsers=new ArrayList<>();
        this.controllerCoordinator=new ControllerCoordinator();
    }
    public synchronized void addUser(ServerConnectionToClient serverConnectionToClient, String s, VirtualView view){
        if(connections.size()==NumPlayersLobby) return;
        connections.add(serverConnectionToClient);
        joinedUsers.add(s);
        controllerCoordinator.joinPlayer(s,view);
        if(controllerCoordinator.getConnectedPlayers().size()==NumPlayersLobby){
            startGameLobby();
            fullLobby=true;
        }
    }

    public synchronized ArrayList<String> getJoinedUsers() {
        return joinedUsers;
    }

    public synchronized Message receiveMessage(ClientMessage message){
        for(Player p:getDisconnectedPlayers()) {
            if(p.getNickName().equals(message.getNickname()))
            {
                ErrorMessage errorMessage= new ErrorMessage();
                errorMessage.addReturnMessage("The player is disconnected from the game, please log in first.");
                return errorMessage;
            }
        }
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

    public synchronized ArrayList<ServerConnectionToClient> getConnections() {
        return connections;
    }
    public synchronized void sendMessageToAllTheLobby(ServerMessage message){
        for(ServerConnectionToClient s:connections){
            s.sendMessage(message);
        }
    }
    public ArrayList<Player> getDisconnectedPlayers(){
        return controllerCoordinator.getDisconnectedPlayers();
    }


    protected synchronized void deleteLobby() {
        ServerConnectionToClient.removeVoidLobby(this);
    }

    public Player getPlayer(String nickname) {
        return controllerCoordinator.getConnectedPlayer(nickname);
    }

    public Boolean getFullLobby() {
        return fullLobby;
    }

    public void setFullLobby(Boolean fullLobby) {
        this.fullLobby = fullLobby;
    }
}
