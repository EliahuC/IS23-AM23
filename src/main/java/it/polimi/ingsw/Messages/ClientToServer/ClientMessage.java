package it.polimi.ingsw.Messages.ClientToServer;

import it.polimi.ingsw.Messages.ClientToServer.PossibleMoves.Move;
import it.polimi.ingsw.Messages.Message;

/**
 * @author Eliahu Cohen
 * Generic Client Message
 */
public class ClientMessage extends Message {
    public Move move;
    public ClientMessage(MessageCategory messageCategory,Move move, String nickname) {
        super(messageCategory,nickname);
        this.move =move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public Move getMessageMove() {
        return move;

    }
}
