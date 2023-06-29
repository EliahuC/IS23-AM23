package it.polimi.ingsw.Messages.ClientToServer;

import it.polimi.ingsw.Messages.ClientToServer.PossibleMoves.Move;
import it.polimi.ingsw.Messages.ClientToServer.PossibleMoves.Move_SelectColumn;

/**
 *  Message that contains the selected column to put the tiles
 * @author Eliahu Cohen
 */
public class ColumnMessage extends ClientMessage{

    public ColumnMessage(Move_SelectColumn move) {
        super(MessageCategory.COLUMN, move,"Client");
    }

    public ColumnMessage(MessageCategory messageCategory, Move move, String nickname) {
        super(messageCategory, move, nickname);
    }
}
