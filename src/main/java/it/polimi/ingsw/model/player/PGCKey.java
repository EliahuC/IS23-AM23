package it.polimi.ingsw.model.player;

/**
 * @author Eliahu Cohen
 * Class that represent the key for the HashMap of the Personal Goal cards
 */
public class PGCKey {
    private final int x;
    private final int y;


    public PGCKey(int row, int column) {

        this.x =row;
        this.y=column;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
