package it.polimi.ingsw.PossibleMoves;

import java.util.ArrayList;

public class Move_SelectTiles extends Move {

    private final ArrayList<Integer> coordinates=new ArrayList<>();

    public void setCoordinates(Integer i) {
        this.coordinates.add(i);
    }

    @Override
    public ArrayList<Integer> getMove() {
        return coordinates;
    }
}
