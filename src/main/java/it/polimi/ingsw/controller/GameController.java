package it.polimi.ingsw.controller;

import com.google.gson.Gson;
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


     public synchronized void startGame(){
         startedGame=true;
         game.startGame();
     }


     public synchronized Message readMessage(ClientMessage m){
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
                 if (!checkOrder() && !checkNumbers())
                     return sendErrorMessage();
                 playMove();
             }
         }
         return new ValidMoveMessage();
     }

    private boolean checkNumbers() {
         for(int i=0;i<order.size();i++){
             if(order.get(i)>3 || order.get(i)<1) return false;
         }
         return true;
    }

    private boolean checkOrder() {
         return order.size()==coordinates.size()/2;
    }



    private Message sendErrorMessage(String ErrorMotivation) {
         Message error= new ErrorMessage();
         error.setReturnMessage(ErrorMotivation);
         return error;
    }
    private Message sendErrorMessage() {
        Message error= new ErrorMessage();
        error.setReturnMessage("The move you made isn't a valid move");
        return error;
    }

    public synchronized void playMove(){
         if(!game.playMove(coordinates,column,order))sendErrorMessage() ;
         coordinates.clear();
         column=null;
         order.clear();
     }


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
}
