package server.model.player;

import server.model.board.ItemTile;
import server.model.board.ItemTileCategory;

import java.util.ArrayList;

public class BookShelf {

    private ItemTile[][] Shelf;
    private  ArrayList<ItemTile> usedTiles = new ArrayList<>();
    private int counter;

    private static final int MAX_Row =6;
    private int tileRow;
    private int tileColumn;
    private ItemTileCategory category;

    private static final int MAX_Column =5;
    private int points;

    public BookShelf() {

        this.tileRow = 0;
        this.tileColumn = 0;
        this.Shelf =  new ItemTile[MAX_Row][MAX_Column];
        this.counter = 1;
        this.points = 0;
        this.category = null;
    }

    public ItemTile getTile(int x , int y) {
        return  Shelf[x][y];
    }

    public void AdjacentScore(){
        ItemTile it = new ItemTile();
        int i = 0;
        while(usedTiles.size() <= NumTiles()) {
            SetFirstTile();
            do {
                it = UpperTile(tileRow, tileColumn);
                if (!AlreadyUsed(it) && it != null && CheckCategory(it)) {
                    usedTiles.add(it);
                    counter++;
                }
                it = LowerTile(tileRow, tileColumn);
                if (!AlreadyUsed(it) && it != null && CheckCategory(it)) {
                    usedTiles.add(it);
                    counter++;
                }
                it = RightTile(tileRow, tileColumn);
                if (!AlreadyUsed(it) && it != null && CheckCategory(it)) {
                    usedTiles.add(it);
                    counter++;
                }
                it = LeftTile(tileRow, tileColumn);
                if (!AlreadyUsed(it) && it != null && CheckCategory(it)) {
                    usedTiles.add(it);
                    counter++;
                }
                SetLocation(usedTiles.get(i+1));

            } while (usedTiles.iterator().hasNext());
            SetPoints();
            counter = 1;
        }
    }

    public void SetLocation(ItemTile it){
        if(it != null){
            for(int i = 0; i < MAX_Row; i++)
                for (int j = 0; j < MAX_Column; j++) {
                    if (getTile(i, j).equals(it)) {
                        tileRow = i;
                        tileColumn = j;
                    }
                }
        }
    }

    public boolean CheckCategory(ItemTile it){
        if(it.getCategory().equals(category)){
            return true;
        }
        else return false;
    }
    public int NumTiles() {
        int tilesCounter = 0;
        for(int i = 0; i < MAX_Row; i++) {
            for(int j = 0; j < MAX_Column; j++) {
                if(getTile(i, j) != null) {
                    tilesCounter++;
                }
            }
        }
        return tilesCounter;
    }


    public ItemTile UpperTile(int x, int y){
        if(x != MAX_Row)
            {ItemTile it = getTile(x + 1, y);
                return it;}
        else return null;
    }
    
    public ItemTile LowerTile(int x, int y){
        if(x != 0)
            {ItemTile it = getTile(x - 1, y);
            return it;}
        else return null;
    }
    
    public ItemTile RightTile(int x, int y){
        if(y != MAX_Column)
            {ItemTile it = getTile(x + 1, y);
            return it;}
        else return null;
    }
    
    public ItemTile LeftTile(int x, int y){
        if(y != 0)
            {ItemTile it = getTile(x + 1, y);
            return it;}
        else return null;
    }


    public void SetFirstTile(){

        for(int i = 0; i < MAX_Row; i++){
            for(int j = 0; i < MAX_Column; j++){
                ItemTile it = getTile(i, j);
                if(!AlreadyUsed(it) && it != null && !it.getCategory().equals(category)){
                    usedTiles.add(it);
                    tileColumn = i;
                    tileRow = j;
                    category = it.getCategory();
                    break;
                }
            }
        }
    }

    public boolean AlreadyUsed(ItemTile it){
        if(usedTiles.contains(it)){
            return true;
        }
        else return false;
    }

    public void SetPoints(){
        if(counter > 2){
            switch (counter) {
                case 3 -> points += 2;
                case 4 -> points += 3;
                case 5 -> points += 5;
                default -> points += 8;
            }
        }
    }

    /* public void resetVisited(){
        for(int i=0; i<5;i++){
            for (ItemTile T:Shelf[i])T.setVisited(false);
        }
    } */
}
