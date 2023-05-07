package it.polimi.ingsw.controller;

import it.polimi.ingsw.Network.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.Network.Messages.ServerToClient.ErrorMessage;
import it.polimi.ingsw.Network.Server.TCP.VirtualView;
import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;
import java.util.Objects;

public class ControllerCoordinator {
    private Boolean startedGame=false;

    private  GameController gameController=null;
    //EndGameController endGameController=new EndGameController(gameController.endGame());
    private final ArrayList<Player> connectedPlayers;

    public ControllerCoordinator() {
        this.connectedPlayers = new ArrayList<>();
    }

    public void joinPlayer(String s, VirtualView view) {
        for (Player p: connectedPlayers){
            if (Objects.equals(p.getNickName(), s)) {
                return;
            }
        }
        Player p = new Player(s);
        p.setListener(view);
        connectedPlayers.add(p);
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
    public Message setMessage(ClientMessage message){
        if(startedGame) {
            return gameController.readMessage(message);
        }
        return new ErrorMessage();
    }
    public void startGame(){
        startedGame=true;
        gameController=new GameController(connectedPlayers);
        gameController.startGame();
    }
    public ArrayList<Player> getDisconnectedPlayers(){
        return gameController.getDisconnectedPlayers();
    }

    public Player getConnectedPlayer(String nickname) {
        Player player=null;
        for(Player p: connectedPlayers){
            if(p.getNickName().equals(nickname))player=p;
        }
      return player;
    }
}
