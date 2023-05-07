package it.polimi.ingsw.Network.Messages.ClientToServer;

import it.polimi.ingsw.Network.Messages.ClientToServer.PossibleMoves.Move_SelectColumn;

public class ColumnMessage extends ClientMessage{

    public ColumnMessage(Move_SelectColumn move) {
        super(MessageCategory.COLUMN, move,"Client");
    }
}
