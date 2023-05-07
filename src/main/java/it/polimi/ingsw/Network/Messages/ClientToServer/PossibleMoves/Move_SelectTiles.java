package it.polimi.ingsw.Network.Messages.ClientToServer.PossibleMoves;

import java.util.ArrayList;

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
