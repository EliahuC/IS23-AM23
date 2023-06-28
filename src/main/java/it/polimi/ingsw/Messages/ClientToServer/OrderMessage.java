package it.polimi.ingsw.Messages.ClientToServer;

import it.polimi.ingsw.Messages.ClientToServer.PossibleMoves.Move_SelectOrder;

/**
 * @author Eliahu Cohen
 * Message that contains the order of the picked tiles
 */
public class OrderMessage extends ClientMessage {

    public OrderMessage(Move_SelectOrder move) {
        super(MessageCategory.ORDER, move, "Client");
    }

}
