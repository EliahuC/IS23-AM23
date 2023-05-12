package it.polimi.ingsw.Messages.ClientToServer;

import it.polimi.ingsw.Messages.ClientToServer.PossibleMoves.Move_SelectOrder;

public class OrderMessage extends ClientMessage {

    public OrderMessage(Move_SelectOrder move) {
        super(MessageCategory.ORDER, move, "Client");
    }

}
