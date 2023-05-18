package it.polimi.ingsw.Messages.ClientToServer.PossibleMoves;

import java.util.ArrayList;

/**
 * @author Eliahu Cohen
 * Move to choose the tiles in the living room
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
