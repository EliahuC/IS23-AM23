package server.model.player;

import server.model.board.ItemTile;
public class BookShelf {

    private ItemTile[][] Shelf= new ItemTile[6][5];

    public BookShelf() {
        this.Shelf = null;
    }

    public ItemTile getTile(int x , int y) {
        return  Shelf[x][y];
    }

    public void AdjacentScore(){}

}
