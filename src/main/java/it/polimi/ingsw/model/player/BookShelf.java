package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.board.ItemTileCategory;

import java.util.ArrayList;

public class BookShelf {

    private final ItemTile[][] Shelf;
    private  final ArrayList<ItemTile> usedTiles = new ArrayList<>();
    private int counter;

    private static final int MAX_Row =6;
    private int tileRow;
    private int tileColumn;
    private ItemTileCategory category;

    private static final int MAX_Column =5;
    private int points;

    private final int maxPickableTiles[] = {3,3,3,3,3};

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
        while(usedTiles.size() < NumTiles()) {
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
                if(i<usedTiles.size()-1) {
                    SetLocation(usedTiles.get(i + 1));
                    i++;
                }else {
                    i++;
                    break;
                }
            } while (usedTiles.iterator().hasNext());
            SetPoints();
            counter = 1;
        }
    }

    public int getTileRow() {
        return tileRow;
    }

    public int getTileColumn() {
        return tileColumn;
    }

    public void SetLocation(ItemTile it) {
        boolean c = false;
        for (int i = 0; i < MAX_Row; i++) {
            for (int j = 0; j < MAX_Column; j++) {
                if (getTile(i, j).equals(it)) {
                    tileRow = i;
                    tileColumn = j;
                    c = true;
                }
                if (c)
                    break;
            }
            if(c)
                break;
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
        if(x != MAX_Row-1)
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
        if(y != MAX_Column-1)
        {ItemTile it = getTile(x, y+1);
            return it;}
        else return null;
    }

    public ItemTile LeftTile(int x, int y){
        if(y != 0)
        {ItemTile it = getTile(x, y-1);
            return it;}
        else return null;
    }


    public ArrayList<ItemTile> getUsedTiles() {
        return usedTiles;
    }

    public void SetFirstTile(){
        boolean c = false;
        for(int i = 0; i < MAX_Row; i++){
            for(int j = 0; j < MAX_Column; j++){
                ItemTile it = getTile(i, j);
                if(!AlreadyUsed(it) && it != null && !it.getCategory().equals(category)){
                    usedTiles.add(it);
                    tileColumn = j;
                    tileRow = i;
                    category = it.getCategory();
                    c=true;
                }
                if(c)
                    break;
            }
            if(c)
                break;
        }
    }

    public boolean AlreadyUsed(ItemTile it){
        if(usedTiles.contains(it)){
            return true;
        }
        else return false;
    }

    private void SetPoints(){
        if(counter > 2){
            switch (counter) {
                case 3 -> points += 2;
                case 4 -> points += 3;
                case 5 -> points += 5;
                default -> points += 8;
            }
        }
    }


    public int getPoints(){
        return points;
    }
    public static int getMAX_Row(){ return MAX_Row;
    }

    public static int getMAX_Column() {
        return MAX_Column;
    }

    /* public void resetVisited(){
        for(int i=0; i<5;i++){
            for (ItemTile T:Shelf[i])T.setVisited(false);
        }
    } */

    public int[] getMaxPickableTiles(){
        return maxPickableTiles;
    }

    public void setCapability(int index, int capability){
        maxPickableTiles[index]=capability;
    }

    public void setTile(int i, int j, ItemTile t){
        Shelf[i][j]=t;
    }

    public ItemTileCategory getCategory() {
        return category;
    }

    public void setTile(int j, ItemTile t){
       int i=0;
       while(Shelf[i][j]!=null)i++;
       Shelf[i][j]=t;
    }

    public ItemTile[][] getShelf(){
        return Shelf;
    }
}
