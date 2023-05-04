package it.polimi.ingsw.controller;

import it.polimi.ingsw.Network.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Network.Server.VirtualView;
import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;

public class ControllerCoordinator {

    private final GameController gameController;
    private final ConnectionController connectionController = new ConnectionController();
    //EndGameController endGameController=new EndGameController(gameController.endGame());
    private final ArrayList<Player> connectedPlayers;

    public ControllerCoordinator() {
        this.connectedPlayers = new ArrayList<>();
        gameController =new GameController(connectedPlayers);
    }

    public void joinPlayer(String s,VirtualView view) {
        if (!connectedPlayers.contains(s)) {
            Player p = new Player(s);
            p.setListener(view);
            connectedPlayers.add(p);
        }
    }

    public GameController getGameController() {
        return gameController;
    }

    /*
    public EndGameController getEndGameController() {
        return endGameController;
    }*/

    public ArrayList<Player> getConnectedPlayers() {
        return connectedPlayers;
    }
    public void setMessage(ClientMessage message){
        gameController.readMessage(message);
    }
    public void startGame(){
        gameController.startGame();
    }

}
