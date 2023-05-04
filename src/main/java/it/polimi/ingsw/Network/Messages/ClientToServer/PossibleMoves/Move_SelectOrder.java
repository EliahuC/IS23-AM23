package it.polimi.ingsw.Network.Messages.ClientToServer.PossibleMoves;

import java.util.ArrayList;

public class Move_SelectOrder extends Move{
    private final ArrayList<Integer> order=new ArrayList<>();

    public void setCoordinates(Integer i) {
        this.order.add(i);
    }

    @Override
    public ArrayList<Integer> getMove() {
        return order;
    }
}
