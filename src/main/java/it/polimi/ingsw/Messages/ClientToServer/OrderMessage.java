package it.polimi.ingsw.Messages.ClientToServer;

import it.polimi.ingsw.Messages.ClientToServer.PossibleMoves.Move_SelectOrder;

/**
 * Message that contains the order of the picked tiles
 * @author Eliahu Cohen
 *
 */
public class OrderMessage extends ClientMessage {

    public OrderMessage(Move_SelectOrder move) {
        super(MessageCategory.ORDER, move, "Client");
    }

}
