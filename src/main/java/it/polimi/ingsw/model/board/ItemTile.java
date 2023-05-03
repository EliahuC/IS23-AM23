package it.polimi.ingsw.model.board;


import java.util.Random;

public class ItemTile {


    private ItemTileCategory category=null ;
  /*  private boolean visited;

    public boolean isVisited() {
        return visited;
    }


    public void setVisited(boolean visited) {
        this.visited = visited;
    }*/

    public ItemTile(){
        ItemTileCategory[] values = ItemTileCategory.values();
        int length = values.length;
        int randIndex = new Random().nextInt(length);
        this.category=values[randIndex];
    }
    public ItemTile(String x) {
        switch(x){
            case "CATS":
                this.category= ItemTileCategory.CATS;
                break;

            case "FRAMES":
                this.category= ItemTileCategory.FRAMES;
                break;
            case "BOOKS":
                this.category= ItemTileCategory.BOOKS;
                break;
            case "GAMES":
                this.category= ItemTileCategory.GAMES;
                break;
            case "PLANTS":
                this.category= ItemTileCategory.PLANTS;
                break;
            case "TROPHIES":
                this.category= ItemTileCategory.TROPHIES;
                break;
        }
    }

    public ItemTileCategory getCategory() {
        return category;
    }
}
