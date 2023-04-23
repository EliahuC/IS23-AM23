package it.polimi.ingsw.model.board;

public abstract class Token {
private final int Points;

    public Token (int Points) {
        this.Points=Points;
    }

    public int getPoints(){
        return Points;
    }
}
