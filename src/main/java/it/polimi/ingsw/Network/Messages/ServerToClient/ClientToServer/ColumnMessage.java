package it.polimi.ingsw.Network.Messages.ServerToClient.ClientToServer;

import it.polimi.ingsw.Network.Messages.ServerToClient.ClientToServer.PossibleMoves.Move_SelectColumn;

public class ColumnMessage extends ClientMessage{

    public ColumnMessage(Move_SelectColumn move, String nickname) {
        super(MessageCategory.COLUMN, move, nickname);
    }
}
