package it.polimi.ingsw.Network.Messages.ClientToServer;

import it.polimi.ingsw.Network.Messages.ClientToServer.PossibleMoves.Move;
import it.polimi.ingsw.Network.Messages.ClientToServer.PossibleMoves.Move_SelectOrder;

public class OrderMessage extends ClientMessage {

    public OrderMessage(Move_SelectOrder move) {
        super(MessageCategory.ORDER, move, "Client");
    }

}
