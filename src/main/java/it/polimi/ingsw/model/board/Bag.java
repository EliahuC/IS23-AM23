package it.polimi.ingsw.model.board;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
/**
 * @author Eliahu Cohen
 * Method that rapresent the bag that contains the tiles
 */

public class Bag implements Serializable {
    private int NumCats;
    private int NumBooks;
    private  int NumPlants;
    private  int NumGames;
    private  int NumFrames;
    private int NumTrophies;

    private final ArrayList<ItemTile> Tiles = new ArrayList<>();

public Bag() {
    for(int i=0; i<23;i++){
        Tiles.add(new ItemTile("CATS"));
        Tiles.add(new ItemTile("PLANTS"));
        Tiles.add(new ItemTile("GAMES"));
        Tiles.add(new ItemTile("BOOKS"));
        Tiles.add(new ItemTile("FRAMES"));
        Tiles.add(new ItemTile("TROPHIES"));

    }
        this.NumCats = 22;
        this.NumBooks = 22;
        this.NumPlants = 22;
        this.NumGames = 22;
        this.NumFrames = 22;
        this.NumTrophies = 22;

    }

    /**
     * @author Eliahu Cohen
     * @return true if there isn't any tile in the bag
     */
    public boolean NoMoreTiles(){
        return NumFrames + NumBooks + NumCats + NumPlants + NumTrophies + NumGames == 0;
    }

    /**
     * @author Eliahu Cohen
     * @return the tile extracted from the bag
     */
    public ItemTile extract() {
        if(NoMoreTiles()) return null;
        int randIndex = new Random().nextInt(Tiles.toArray().length -1);
            ItemTile Tile = Tiles.remove(randIndex);

                    switch (Tile.getCategory()) {
                        case CATS -> NumCats--;

                        case FRAMES -> NumFrames--;

                        case BOOKS -> NumBooks--;

                        case GAMES -> NumGames--;

                        case PLANTS -> NumPlants--;

                        case TROPHIES -> NumTrophies--;

                    }


            return Tile;
        }
}



/*

    public Bag() {
        this.NumCats = 22;
        this.NumBooks = 22;
        this.NumPlants = 22;
        this.NumGames = 22;
        this.NumFrames = 22;
        this.NumTrophies = 22;
        ok=false;
    }

    public int getNumCats() {
        return NumCats;
    }

    public int getNumBooks() {
        return NumBooks;
    }

    public int getNumPlants() {
        return NumPlants;
    }

    public int getNumGames() {
        return NumGames;
    }

    public int getNumFrames() {
        return NumFrames;
    }

    public int getNumTrophies() {
        return NumTrophies;
    }
    public boolean NoMoreTiles(){
        if(NumFrames+NumBooks+NumCats+NumPlants+NumTrophies+NumGames==0) return true;
        else return false;
    }
    public ItemTile extract() {
        if(NoMoreTiles()==true) {

        System.out.println("NO MORE TILES");
        return null;
        }
        else {
            ItemTile Tile = new ItemTile();
            while (ok == false) {
                switch (Tile.getCategory()) {
                    case CATS:
                        if (NumCats > 0) {
                            NumCats--;
                            ok = true;
                            break;
                        } else {Tile = new ItemTile();
                        break;}
                    case FRAMES:
                        if (NumFrames > 0) {
                            NumFrames--;
                            ok = true;
                            break;
                        } else {Tile = new ItemTile();
                            break;}
                    case BOOKS:
                        if (NumBooks > 0) {
                            NumBooks--;
                            break;
                        }else {Tile = new ItemTile();
                            break;}
                    case GAMES:
                        if (NumGames > 0) {
                            NumGames--;
                            break;
                        } else {Tile = new ItemTile();
                            break;}
                    case PLANTS:
                        if (NumPlants > 0) {
                            NumPlants--;
                            break;
                        } else {Tile = new ItemTile();
                            break;}
                    case TROPHIES:
                        if (NumTrophies > 0) {
                            NumTrophies--;
                            break;
                        } else {Tile = new ItemTile();
                            break;}

                }

            }
            return Tile;
        }*/
