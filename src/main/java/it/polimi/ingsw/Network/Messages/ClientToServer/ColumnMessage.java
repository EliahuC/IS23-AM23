package it.polimi.ingsw.Network.Messages.ClientToServer;

import it.polimi.ingsw.Network.Messages.ClientToServer.PossibleMoves.Move;
import it.polimi.ingsw.Network.Messages.ClientToServer.PossibleMoves.Move_SelectColumn;

public class ColumnMessage extends ClientMessage{

    public ColumnMessage(Move_SelectColumn move) {
        super(MessageCategory.COLUMN, move,"Client");
    }

    public ColumnMessage(MessageCategory messageCategory, Move move, String nickname) {
        super(messageCategory, move, nickname);
    }
}
