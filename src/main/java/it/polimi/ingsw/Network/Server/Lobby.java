package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.controller.ControllerCoordinator;
import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;

public class Lobby {
    private final Integer NumPlayersLobby;
    private ControllerCoordinator controllerCoordinator;
    private final ArrayList<String> joinedUsers;
    private Boolean startedGame=false;
    public Lobby(Integer numPlayersLobby){
        this.NumPlayersLobby=numPlayersLobby;
        this.joinedUsers=new ArrayList<>();
    }
    public synchronized void addUser(String s){
        joinedUsers.add(s);
    }

    public synchronized ArrayList<String> getJoinedUsers() {
        return joinedUsers;
    }

    public synchronized void receiveMessage(ClientMessage message){
        controllerCoordinator.setMessage(message);
    }
    public synchronized void logoutFromLobby(String s){
        joinedUsers.remove(s);
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
