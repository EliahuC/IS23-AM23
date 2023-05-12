package it.polimi.ingsw.Messages.ServerToClient;

import it.polimi.ingsw.model.board.BoardToken;

import static it.polimi.ingsw.model.board.LivingRoom.MAX_Column;
import static it.polimi.ingsw.model.board.LivingRoom.MAX_Row;

public class LivingRoomMessage extends ServerMessage{
    private  BoardToken[][] Board = new BoardToken[MAX_Row][MAX_Column];

    public LivingRoomMessage(BoardToken[][] Board) {
        super(MessageCategory.LIVINGROOM);
        this.Board=Board;
    }

    public BoardToken[][] getBoard() {
        return Board;
    }
}
