package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.controller.ControllerCoordinator;
import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;

public class Lobby {
    private final Integer NumPlayersLobby;
    private final ControllerCoordinator controllerCoordinator;
    private final ArrayList<String> joinedUsers;
    private Boolean startedGame=false;
    public Lobby(Integer numPlayersLobby){
        this.NumPlayersLobby=numPlayersLobby;
        this.joinedUsers=new ArrayList<>();
        this.controllerCoordinator=new ControllerCoordinator();
    }
    public synchronized void addUser(String s,VirtualView view){
        joinedUsers.add(s);
        controllerCoordinator.joinPlayer(s,view);
        if(controllerCoordinator.getConnectedPlayers().size()==NumPlayersLobby)startGameLobby();
    }

    public synchronized ArrayList<String> getJoinedUsers() {
        return joinedUsers;
    }

    public synchronized void receiveMessage(ClientMessage message){
        controllerCoordinator.setMessage(message);
    }
    public synchronized void logoutFromLobby(String s){
        joinedUsers.remove(s);
        controllerCoordinator.getConnectedPlayers().remove(s);
    }

    public synchronized void startGameLobby(){
        startedGame=true;
        controllerCoordinator.startGame();
    }

    public synchronized Boolean getStartedGame() {
        return startedGame;
    }

    public Integer getNumPlayersLobby() {
        return NumPlayersLobby;
    }
}
