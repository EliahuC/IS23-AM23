package it.polimi.ingsw.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.Network.Messages.ServerToClient.ClientToServer.ClientMessage;
import it.polimi.ingsw.Network.Messages.ServerToClient.ClientToServer.LobbyCreationMessage;
import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.Network.Messages.ServerToClient.ErrorMessage;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;
import java.util.Optional;

public class GameController {
     private final Game G;
     private ClientMessage m;
     private final Launcher launcher;
     private final Gson gson = new Gson();
     private final ArrayList<Integer> coordinates=new ArrayList<>();
     private Integer column;
     private final ArrayList<Player> lobby=new ArrayList<>();
     private Integer lobbyMaxSize;
     private boolean startedGame=false;
     public GameController(ArrayList<Player> players){
         this.launcher=new Launcher();
         this.m=null;
         this.G=new Game(launcher,lobby);
         lobby.addAll(players);

     }


     private synchronized void startGame(){
         startedGame=true;
         G.startGame();
     }


     public synchronized void readMessage(ClientMessage m){

         switch (m.getCategory()){
             case COORDINATES:{
                 coordinates.addAll(m.getMessageMove().getMove());
                 if(!G.checkLegalMove(coordinates,coordinates.size()/2))
                     sendErrorMessage();
                 break;
             }
             case COLUMN :{
                 column=m.getMessageMove().getMove().remove(0);
                 if(!G.checkLegalColumn(column,coordinates.size()/2))
                     sendErrorMessage();
                 break;
             }
             case START_GAME:{
                 startGame();
                 break;
             }


         }
     }

    private boolean checkNickName(String s) {
         return lobby.contains(s);
    }

    private boolean checkLobbySpace() {
         return lobby.size() <= lobbyMaxSize;
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
         G.playMove(coordinates,column);
         coordinates.clear();

         column=null;
     }


    public synchronized Optional<Player> endGame() {
        return G.endGame();
    }


}
