package it.polimi.ingsw.Messages.ClientToServer.PossibleMoves;

import java.util.ArrayList;

public class Move_SelectOrder extends Move{
    private final ArrayList<Integer> order=new ArrayList<>();

    public void setOrder(ArrayList<Integer> order) {
        this.order.addAll(order);
    }

    @Override
    public ArrayList<Integer> getMove() {
        return order;
    }
}
