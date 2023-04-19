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

     public GameController(){
         this.launcher=new Launcher();
         this.G= new Game(launcher);
         this.m=null;

     }

     public void startGame(){
         G.startGame();
     }


     public void readMessage(String s){
         m=gson.fromJson(s,Message.class);
         switch (m.getCategory()){
             case COORDINATES:{
                 coordinates.addAll(m.getMessageMove().getMove());
                 G.checkLegalMove(coordinates,coordinates.size()/2);
             }
             case COLUMN :{
                 column=m.getMessageMove().getMove().remove(0);
                 G.checkLegalColumn(column,coordinates.size()/2);
             }
             //case ORDER ->order.addAll(m.getMessageMove().getMove());
         }
     }
     public void playMove(){

         G.playMove(coordinates,order,column);
     }


    public Message endGame() {
        Optional<Player> P = G.endGame();
        return P.map(player -> new Message(null, player.getNickName())).orElse(null);
    }
    public void addPlayer(String nickname){
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

    private void setCoordinates(Move1 m1){
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
