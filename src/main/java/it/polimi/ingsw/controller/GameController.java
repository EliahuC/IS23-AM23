package it.polimi.ingsw.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.Network.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.Network.Messages.ServerToClient.ErrorMessage;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;
import java.util.Optional;

public class GameController {
     private final Game game;
     private ClientMessage message;
     private final Launcher launcher;
     private final Gson gson = new Gson();
     private final ArrayList<Integer> coordinates=new ArrayList<>();
    private final ArrayList<Integer> order=new ArrayList<>();
     private Integer column;
     private final ArrayList<Player> lobby=new ArrayList<>();
     private Integer lobbyMaxSize;
     private boolean startedGame=false;
     public GameController(ArrayList<Player> players){
         this.launcher=new Launcher();
         this.message =null;
         this.lobby.addAll(players);
         this.game =new Game(launcher,lobby);


     }


     public synchronized void startGame(){
         startedGame=true;
         game.startGame();
     }


     public synchronized void readMessage(ClientMessage m){

         switch (m.getCategory()){
             case COORDINATES:{
                 coordinates.addAll(m.getMessageMove().getMove());
                 if(!game.checkLegalMove(coordinates,coordinates.size()/2))
                     sendErrorMessage();
                 break;
             }
             case COLUMN :{
                 column=m.getMessageMove().getMove().remove(0);
                 if(!game.checkLegalColumn(column,coordinates.size()/2))
                     sendErrorMessage();

                 break;
             }
             case ORDER:{
                 order.addAll(m.getMessageMove().getMove());
                 if(!checkOrder()&&!checkNumbers())
                     sendErrorMessage();
                 else playMove();
             }
             case START_GAME:{
                 startGame();
                 break;
             }


         }
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

    private boolean checkNickName(String s) {
         return lobby.contains(s);
    }


    private void sendErrorMessage(String ErrorMotivation) {
         Message error= new ErrorMessage();
         error.addReturnMessage(ErrorMotivation);
         //TO BE ADDED SOON
        //sendingMethod.send(error);
    }
    private void sendErrorMessage() {
        Message error= new ErrorMessage();
        error.addReturnMessage("The move you made isn't a valid move");
        //TO BE ADDED SOON
        //sendingMethod.send(error);
    }

    public synchronized void playMove(){
         game.playMove(coordinates,column,order);
         coordinates.clear();
         column=null;
         order.clear();
     }


    public synchronized Optional<Player> endGame() {
        return game.endGame();
    }


}
