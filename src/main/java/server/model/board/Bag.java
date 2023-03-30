package server.model.board;



import java.util.ArrayList;
import java.util.Random;

public class Bag {
    private int NumCats;
    private int NumBooks;
    private int NumPlants;
    private int NumGames;
    private int NumFrames;
    private int NumTrophies;
    private boolean ok;



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
        }
    }

/*
    private final ArrayList<ItemTile> Cats = new ArrayList<>();
    private final ArrayList<ItemTile> Books = new ArrayList<>();
    private final ArrayList<ItemTile> Plants = new ArrayList<>();
    private final ArrayList<ItemTile> Games = new ArrayList<>();
    private final ArrayList<ItemTile> Frames = new ArrayList<>();
    private final ArrayList<ItemTile> Trophies = new ArrayList<>();
public Bag() {
    for(int i=0; i<23;i++){
        Cats.add(new ItemTile("CATS"));
        Plants.add(new ItemTile("PLANTS"));
        Games.add(new ItemTile("GAMES"));
        Books.add(new ItemTile("BOOKS"));
        Frames.add(new ItemTile("FRAMES"));
        Trophies.add(new ItemTile("TROPHIES"));

    }
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
        ItemTile Tile = null;
        if(NoMoreTiles()==true) {

        System.out.println("NO MORE TILES");
        return null;
        }
        else {

            int randIndex = new Random().nextInt(6);
            while (ok == false) {
                switch(randIndex) {
                    case 0 :
                        if (NumCats > 0) {
                            Tile = Cats.get(Cats.size()-1);
                            NumCats--;
                            ok = true;

                            break;
                        } else {randIndex = new Random().nextInt(6);
                        break;}
                    case 1:
                        if (NumFrames > 0) {
                            Tile = Frames.get(Frames.size()-1);
                            NumFrames--;
                            ok = true;
                            break;
                        } else {randIndex = new Random().nextInt(6);
                            break;}
                    case 2:
                        if (NumBooks > 0) {
                            Tile = Books.get(Books.size()-1);
                            NumBooks--;
                            break;
                        }else {randIndex = new Random().nextInt(6);
                            break;}
                    case 3:
                        if (NumGames > 0) {
                            Tile = Games.get(Games.size()-1);
                            NumGames--;
                            break;
                        } else {randIndex = new Random().nextInt(6);
                            break;}
                    case 4:
                         if (NumPlants > 0) {
                             Tile = Plants.get(Plants.size()-1);
                            NumPlants--;
                            break;
                        } else {randIndex = new Random().nextInt(6);
                            break;}
                    case 5:
                        if (NumTrophies > 0) {
                            Tile = Trophies.get(Trophies.size()-1);
                            NumTrophies--;
                            break;
                        } else {randIndex = new Random().nextInt(6);
                            break;}

                }

            }
            return Tile;
        }
    }*/
}
