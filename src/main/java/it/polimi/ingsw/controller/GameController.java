package it.polimi.ingsw.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.Network.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Network.Messages.ClientToServer.LobbyCreationMessage;
import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.Network.Messages.ServerToClient.ErrorMessage;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.board.ItemTileCategory;

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

     public GameController(){
         this.launcher=new Launcher();
         this.G= new Game(launcher,lobbyMaxSize);
         this.m=null;

     }

     private synchronized void startGame(){
         G.startGame();
     }


     public synchronized void readMessage(String s){
         m=gson.fromJson(s,ClientMessage.class);
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
             case CREATE_LOBBY:{
                 if(!lobbyCheck()||(lobby.contains(m.getNickname()))) {
                     sendErrorMessage("Lobby already present, please join that lobby");
                     break;
                 }

                 lobby.clear();
                 lobby.add(0,new Player(m.getNickname()));
                 lobbyMaxSize=((LobbyCreationMessage) m).getNumPlayers();
                 break;
             }
             case ENTER_LOBBY:{
                 if(!checkLobbySpace()){
                     sendErrorMessage("the lobby is full");
                     break;
                 }
                 if(checkNickName(m.getNickname())){
                     sendErrorMessage("Nickname already used in the lobby, please choose an other nickname");
                     break;
                 }
                 lobby.add(new Player(m.getNickname()));
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

    private boolean lobbyCheck() {
        return lobby.size() == 0;
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
    private synchronized void addPlayer(String nickname){
         G.addPlayers(nickname);
    }

}
