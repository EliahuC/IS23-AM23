package server.model.board;


public class Bag {
    private final int NumCats;
    private final int NumBooks;
    private final int NumPlants;
    private final int NumGames;
    private final int NumFrames;
    private final int NumTrophies;


    public Bag(int Num) {
        this.NumCats = Num;
        this.NumBooks = Num;
        this.NumPlants = Num;
        this.NumGames = Num;
        this.NumFrames = Num;
        this.NumTrophies = Num;
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

    public ItemTile extract() {
        //to do//

    }
}
