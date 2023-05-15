package it.polimi.ingsw.model.board;


import java.util.Random;

/**
 * @author Eliahu Cohen
 * class that rapresent the item tile
 */

public class ItemTile {


    private ItemTileCategory category=null ;
    private String color;
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String WHITE = "\u001B[37m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    public static final String PINK = "\u001B[35m";
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
                this.color=GREEN + " ■ " + RESET;
                break;

            case "FRAMES":
                this.category= ItemTileCategory.FRAMES;
                this.color=BLUE + " ■ " + RESET;
                break;
            case "BOOKS":
                this.category= ItemTileCategory.BOOKS;
                this.color=RESET + " ■ " + RESET;
                break;
            case "GAMES":
                this.category= ItemTileCategory.GAMES;
                this.color=YELLOW + " ■ " + RESET;
                break;
            case "PLANTS":
                this.category= ItemTileCategory.PLANTS;
                this.color=PINK + " ■ " + RESET;
                break;
            case "TROPHIES":
                this.category= ItemTileCategory.TROPHIES;
                this.color=CYAN + " ■ " + RESET;
                break;
        }
    }

    public ItemTileCategory getCategory() {
        return category;
    }

    public void print(){
        System.out.print(this.color);
    }

    public String getColor(){
        return color;
    }
}
