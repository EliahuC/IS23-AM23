package it.polimi.ingsw.model.board;


import java.io.Serializable;
import java.util.Random;

/**
 * @author Eliahu Cohen
 * class that rapresent the item tile
 */

public class ItemTile implements Serializable {


    private ItemTileCategory category=null ;

    /**
     * @author Eliahu Cohen
     * Constructor of the class used to pick a random Tile
     */
    public ItemTile(){
        ItemTileCategory[] values = ItemTileCategory.values();
        int length = values.length;
        int randIndex = new Random().nextInt(length);
        this.category=values[randIndex];
    }

    /**
     * @author Eliahu Cohen
     * Constructor of the class used to pick a specific Tile
     */
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
