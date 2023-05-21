package it.polimi.ingsw.model.board;


import java.io.Serializable;
import java.util.Random;

/**
 * @author Eliahu Cohen
 * class that rapresent the item tile
 */

public class ItemTile implements Serializable {


    private ItemTileCategory category=null ;
    private String color;
    public  static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001b[48;2;145;165;65m";
    public static final String WHITE = "\u001b[48;2;236;225;189m";
    public static final String YELLOW = "\u001b[48;2;223;169;59m";
    public static final String BLUE = "\u001b[48;2;0;104;146m";
    public static final String CYAN = "\u001b[48;2;106;183;183m";
    public static final String PINK = "\u001b[48;2;198;77;124m";
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
                this.color=GREEN + "   " + RESET;
                break;

            case "FRAMES":
                this.category= ItemTileCategory.FRAMES;
                this.color=BLUE + "   " + RESET;
                break;
            case "BOOKS":
                this.category= ItemTileCategory.BOOKS;
                this.color=WHITE + "   " + RESET;
                break;
            case "GAMES":
                this.category= ItemTileCategory.GAMES;
                this.color=YELLOW + "   " + RESET;
                break;
            case "PLANTS":
                this.category= ItemTileCategory.PLANTS;
                this.color=PINK + "   " + RESET;
                break;
            case "TROPHIES":
                this.category= ItemTileCategory.TROPHIES;
                this.color=CYAN + "   " + RESET;
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
