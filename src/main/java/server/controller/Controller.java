package server.controller;
import server.Launcher;
import server.model.board.ItemTile;
import server.model.player.BookShelf;
import server.model.*;

public class Controller {
     private  Game G;
     private final Launcher launcher;

     public Controller(){
         this.launcher=new Launcher();
         this.G= new Game(launcher);
     }

    public void extractAndRestore(){
         G.getLR().restore();
    }

    public ItemTile selectTiles(){
        return null;
    }

    public void insertIntoShelf(BookShelf s){
    }

    public void endGame(){
         G.endGame();
    }

    public void setScore(){
    }

    public void updateCommonCards(){
    }
}
