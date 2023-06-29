package it.polimi.ingsw.Messages.ServerToClient;

import it.polimi.ingsw.model.board.BoardToken;

import static it.polimi.ingsw.model.board.LivingRoom.MAX_Column;
import static it.polimi.ingsw.model.board.LivingRoom.MAX_Row;

/**
 * Message that returns the changed living room after the player pick the tiles
 * @author Eliahu Cohen
 *
 */
public class LivingRoomMessage extends ServerMessage{
    private  BoardToken[][] Board ;

    public LivingRoomMessage(BoardToken[][] Board) {
        super(MessageCategory.LIVINGROOM);
        this.Board=Board;
    }

    public BoardToken[][] getBoard() {
        return Board;
    }
    private BoardToken getBoardTile(int i, int j){
        return Board[i][j];
    }
}
