package it.polimi.ingsw.controller;

import it.polimi.ingsw.GameSavings;
import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.ServerToClient.ErrorMessage;
import it.polimi.ingsw.Network.Server.VirtualView;
import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Eliahu Cohen
 * controller that coordinates the other controllers
 */
public class ControllerCoordinator {
    private Boolean startedGame=false;

    private  GameController gameController=null;

    private final ArrayList<Player> connectedPlayers;

    public ControllerCoordinator() {
        this.connectedPlayers = new ArrayList<>();
    }

    /**
     * @author Eliahu Cohen
     * @param s nickname
     * @param view listener of the player
     * adding a player to the current game
     */
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

    public void setStartedGame(Boolean startedGame) {
        this.startedGame = startedGame;
    }

    public GameController getGameController() {
        return gameController;
    }


    public ArrayList<Player> getConnectedPlayers() {
        return connectedPlayers;
    }

    /**
     * @author Eliahu Cohen
     * @param message received from the client
     * @return Ok message or errormessage
     */
    public Message setMessage(ClientMessage message){
        if(startedGame) {
            return gameController.readMessage(message);
        }
        return new ErrorMessage();
    }

    /**
     * @author Eliahu Cohen
     * method called to start the game
     */
    public void startGame(){
        startedGame=true;
        gameController=new GameController(connectedPlayers);
        gameController.startGame();
    }
    public ArrayList<Player> getDisconnectedPlayers(){
        return gameController.getDisconnectedPlayers();
    }

    /**
     * @author Eliahu Cohen
     * @param nickname of the player
     * @return the player that have that nickname
     */
    public Player getConnectedPlayer(String nickname) {
        Player player=null;
        for(Player p: connectedPlayers){
            if(p.getNickName().equals(nickname))player=p;
        }
      return player;
    }

    public Boolean getStartedGame() {
        return startedGame;
    }

    /**
     * @author Eliahu Cohen
     * method to end the game when a player crash
     */
    public void endgame() {
        if(gameController!=null)
            gameController.terminateGame();
    }

    /**
     * @author Eliahu Cohen
     * @param gameSavings taken from the disk
     * Method to set the game params
     */
    public void setGame(GameSavings gameSavings) {
        connectedPlayers.clear();
        connectedPlayers.addAll(gameSavings.getPlayers());
        gameController=new GameController(gameSavings.getPlayers());
        gameController.setGame(gameSavings);
    }
}
