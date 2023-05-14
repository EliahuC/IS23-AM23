package it.polimi.ingsw.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.GameSavings;
import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.ServerToClient.ErrorMessage;
import it.polimi.ingsw.Messages.ServerToClient.ValidMoveMessage;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Eliahu Cohen
 * Controller that make the changes on the model
 */
public class GameController {
     private final Game game;
     private final Launcher launcher;
     private final Gson gson = new Gson();
     private final ArrayList<Integer> coordinates=new ArrayList<>();
     private final ArrayList<Integer> order=new ArrayList<>();
     private Integer column;
     private final ArrayList<Player> players =new ArrayList<>();
     private boolean startedGame=false;
     public GameController(ArrayList<Player> players){
         this.launcher=new Launcher();
         this.players.addAll(players);
         launcher.addPlayers(players);
         launcher.setNumPlayers(players.size());
         this.game =new Game(launcher, this.players);


     }

    /**
     * @author Eliahu Cohen
     * method that starts the game
     */
     public synchronized void startGame(){
         startedGame=true;
         game.startGame();
     }

    /**
     * @author Eliahu Cohen
     * @param m message from the client
     * @return message to send to the client to confirm the success of his move/an error
     */
     public synchronized Message readMessage(ClientMessage m){
         ValidMoveMessage message=new ValidMoveMessage();
         if(!(Objects.equals(m.getNickname(), game.getCurrPlaying())))
             return sendErrorMessage("It's not your turn");
         switch (m.getCategory()) {
             case COORDINATES -> {
                 coordinates.addAll(m.getMessageMove().getMove());
                 if (!game.checkLegalMove(coordinates, coordinates.size() / 2)) {
                     return sendErrorMessage();
                 }
             }
             case COLUMN -> {
                 column = m.getMessageMove().getMove().remove(0);
                 if (!game.checkLegalColumn(column, coordinates.size() / 2))
                     return sendErrorMessage();
             }
             case ORDER -> {
                 order.addAll(m.getMessageMove().getMove());
                 if (!checkOrder() || !checkNumbers())
                     return sendErrorMessage();
                 GameSavings savings=playMove();
                 message.setSavings(savings);
             }
         }
         return message;
     }

    /**
     * @author Eliahu Cohen
     * @return true if the order command that the client sent is ok
     */
    private boolean checkNumbers() {
         for(int i=0;i<order.size();i++){
             if(order.get(i)>3 || order.get(i)<1) return false;
         }
         return true;
    }

    /**
     * @author Eliahu Cohen
     * @return true if the number of tiles i want to order is == number of tiles extracted from the living room
     */
    private boolean checkOrder() {
         return order.size()==coordinates.size()/2;
    }


    /**
     * @author Eliahu Cohen
     * @param ErrorMotivation string that indicates the motivation of the error
     * @return error message to send to the client
     */
    private Message sendErrorMessage(String ErrorMotivation) {
         Message error= new ErrorMessage();
         error.setReturnMessage(ErrorMotivation);
         return error;
    }

    /**
     * @author Eliahu Cohen
     * @return default error message to send to the client
     */
    private Message sendErrorMessage() {
        Message error= new ErrorMessage();
        error.setReturnMessage("The move you made isn't a valid move");
        return error;
    }

    /**
     * @author Eliahu Cohen
     * @return savings of the game after a move
     * method that calls the Game method to play a move
     */
    public synchronized GameSavings playMove(){
         GameSavings savings=game.playMove(coordinates,column,order);
         if(savings==null)sendErrorMessage() ;
         coordinates.clear();
         column=null;
         order.clear();
         return savings;
     }

    /**
     * @author Eliahu Cohen
     * @return the player that wins the game
     */
    public synchronized Optional<Player> endGame() {
        return game.endGame();
    }

    public ArrayList<Player> getDisconnectedPlayers(){
         return game.getDisconnectedPlayers();
    }

    public Game getGame() {
        return game;
    }

    public boolean isStartedGame() {
        return startedGame;
    }
    public ArrayList<Integer> getCoordinates() {
        return coordinates;
    }

    public ArrayList<Integer> getOrder() {
        return order;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public Integer getColumn() {
        return column;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
