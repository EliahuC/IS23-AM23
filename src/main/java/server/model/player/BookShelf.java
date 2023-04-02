package server.model.player;

import server.model.board.ItemTile;
public class BookShelf {

    private ItemTile[][] Shelf;
    private int counter;
    private int points;

    public BookShelf() {
        this.Shelf =  new ItemTile[6][5];
        this.counter = 1;
        this.points = 0;
    }

    public ItemTile getTile(int x , int y) {
        return  Shelf[x][y];
    }

    public void AdjacentScore(){}
    
    public void SetPoints(){
        if(counter > 2){
            switch (counter){
                case 3 : points += 2;
                case 4 : points += 3;
                case 5 : points += 5;
                default : points += 8;
            }
        }
    }


}
