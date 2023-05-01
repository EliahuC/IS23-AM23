package it.polimi.ingsw.controller;

import it.polimi.ingsw.Network.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Network.Server.VirtualView;
import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;

public class ControllerCoordinator {
    private final VirtualView view;
    private final GameController gameController;
    private final ConnectionController connectionController = new ConnectionController();
    //EndGameController endGameController=new EndGameController(gameController.endGame());
    private final ArrayList<Player> connectedPlayers;

    public ControllerCoordinator(ArrayList<String> players,VirtualView view) {
        this.connectedPlayers = new ArrayList<>();
        this.view=view;
        joinPlayers(players);
        gameController =new GameController(connectedPlayers);
    }

    private void joinPlayers(ArrayList<String> players) {
        for(String s:players)connectedPlayers.add(new Player(s));
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
