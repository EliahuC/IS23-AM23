package it.polimi.ingsw.model.player;

import java.io.Serializable;

/**
 * @author Eliahu Cohen
 * Class that represent the key for the HashMap of the Personal Goal cards
 */
public class Pair implements Serializable {
    private  int x;
    private  int y;


    public Pair(int row, int column) {

        this.x =row;
        this.y=column;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
