package it.polimi.ingsw.Messages.ClientToServer.PossibleMoves;

import java.util.ArrayList;

/**
 * Move to choose the tiles in the living room
 * @author Eliahu Cohen
 */
public class Move_SelectTiles extends Move {

    private final ArrayList<Integer> coordinates=new ArrayList<>();

    public void setCoordinates(ArrayList<Integer> coordinates) {
        this.coordinates.addAll(coordinates);
    }

    @Override
    public ArrayList<Integer> getMove() {
        return coordinates;
    }
}
