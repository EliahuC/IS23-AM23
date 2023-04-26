package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.controller.ControllerCoordinator;

import java.util.ArrayList;

public class Lobby {
    private final Integer NumPlayersLobby;
    private ControllerCoordinator controllerCoordinator;
    private final ArrayList<String> joinedUsers;
    public Lobby(Integer numPlayersLobby){
        this.NumPlayersLobby=numPlayersLobby;
        this.joinedUsers=new ArrayList<>();
    }
    public synchronized void addUser(String s){
        joinedUsers.add(s);
    }

    public ArrayList<String> getJoinedUsers() {
        return joinedUsers;
    }

    public synchronized void receiveMessage(ClientMessage message){
        controllerCoordinator.setMessage(message);
    }

    public synchronized void startGameLobby(){
        controllerCoordinator.startGame();

    }
}
