package server.controller;

import com.google.gson.Gson;
import server.Launcher;
import server.Message;
import server.model.Game;
import server.model.player.Player;
import server.model.board.ItemTileCategory;

import java.util.ArrayList;
import java.util.Optional;

public class GameController {
     private final Game G;
     private Message m;
     private final Launcher launcher;
     private final Gson gson = new Gson();
     private final ArrayList<Integer> coordinates=new ArrayList<>();
     private Integer column;
     private final ArrayList<ItemTileCategory> order =new ArrayList<>();
     private final ArrayList<Player> lobby=new ArrayList<>();

     public GameController(){
         this.launcher=new Launcher();
         this.G= new Game(launcher);
         this.m=null;

     }

     private synchronized void startGame(){
         G.startGame();
     }


     public synchronized void readMessage(String s){
         m=gson.fromJson(s,Message.class);
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
                 lobby.clear();
                 lobby.add(0,new Player(m.getNickname()));
             }
             case ENTER_LOBBY:{
                 lobby.add(new Player(m.getNickname()));
             }
             //case ORDER ->order.addAll(m.getMessageMove().getMove());
         }
     }

    private void sendErrorMessage() {
         Message error= new Message(null,"GameMaster");
         error.setCategory(Message.MessageCategory.WARNING);
         error.addReturnMessage("The move you made isn't a valid move");
         //TO BE ADDED SOON
        //sendingMethod.send(error);
    }

    public synchronized void playMove(){
         G.playMove(coordinates,order,column);
         coordinates.clear();
         order.clear();
         column=null;
     }


    private synchronized Message endGame() {
        Optional<Player> P = G.endGame();
        return P.map(player -> new Message(null, player.getNickName())).orElse(null);
    }
    private synchronized void addPlayer(String nickname){
         G.addPlayers(nickname);
    }

 /*public void readMove(String s){
         m=gson.fromJson(s,Move.class);
         setCoordinates(m);
         setOrder(m);
     }

    private void setOrder(Move m) {
         order.clear();
         order.add(m.getColore1());
         order.add(m.getColore2());
         order.add(m.getColore3());
         removeNullO(order);
    }

    private void setCoordinates(Move_SelectTiles m1){
        coordinates.clear();
        coordinates.add(m.getXmossa1());
        coordinates.add(m.getYmossa1());
        coordinates.add(m.getXmossa2());
        coordinates.add(m.getYmossa2());
        coordinates.add(m.getXmossa3());
        coordinates.add(m.getYmossa3());
        // ??coordinates.removeAll(null);??
        removeNullI(coordinates);
    }

    public void removeNullI(ArrayList<Integer> coordinates) {
         for(int i=0;i<coordinates.size();i++){
             if(coordinates.get(i)==null)coordinates.remove(i);
         }
    }

    private void removeNullO(ArrayList<ItemTileCategory> coordinates) {
        for(int i=0;i< order.size();i++){
            if(order.get(i)==null)order.remove(i);
        }
    }*/
}
