package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Messages.ServerToClient.ClientToServer.ClientMessage;
import it.polimi.ingsw.controller.ControllerCoordinator;
import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;

public class Lobby {
    private ControllerCoordinator controllerCoordinator;
    private final ArrayList<String> joinedUsers;
    public Lobby(){
        joinedUsers=new ArrayList<>();
    }
    public void addUser(String s){
        joinedUsers.add(s);
    }

    public ArrayList<String> getJoinedUsers() {
        return joinedUsers;
    }

    public void reciveMessage(ClientMessage message){
        controllerCoordinator.setMessage(message);
    }
}
