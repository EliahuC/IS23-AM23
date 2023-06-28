package it.polimi.ingsw.Messages.ClientToServer;

import it.polimi.ingsw.Messages.ClientToServer.PossibleMoves.Move;
import it.polimi.ingsw.Messages.ClientToServer.PossibleMoves.Move_SelectColumn;

/**
 * @author Eliahu Cohen
 * Message that contains the selected column to put the tiles
 */
public class ColumnMessage extends ClientMessage{

    public ColumnMessage(Move_SelectColumn move) {
        super(MessageCategory.COLUMN, move,"Client");
    }

    public ColumnMessage(MessageCategory messageCategory, Move move, String nickname) {
        super(messageCategory, move, nickname);
    }
}
