package server.controller;

import com.google.gson.Gson;
import server.Launcher;
import server.model.Game;

import server.Move;
import server.model.board.ItemTileCategory;

import java.util.ArrayList;

public class Controller {
     private final Game G;
     private Move m;
     private final Launcher launcher;
     private final Gson gson = new Gson();
     private final ArrayList<Integer> coordinates=new ArrayList<>();;
     private final ArrayList<ItemTileCategory> order =new ArrayList<>();

     public Controller(){
         this.launcher=new Launcher();
         this.G= new Game(launcher);
         this.m=null;

     }

     public void startGame(){
         G.startGame();
     }

     public void readMove(String s){
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

    private void setCoordinates(Move m){
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
    }


    public void extractAndRestore(){

         G.getLR().restore();
    }

    public void playMove(){

         G.playMove(coordinates,order,m.getYBookshelf());
    }

    public void endGame(){
         G.endGame();
    }

    public void addPlayer(String nickname){
         G.addPlayers(nickname);
    }


}
