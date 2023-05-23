package it.polimi.ingsw.Messages.ClientToServer;

import it.polimi.ingsw.Messages.ClientToServer.PossibleMoves.Move;
import it.polimi.ingsw.Messages.Message;

public class ClientMessage extends Message {
    public final Move move;
    public ClientMessage(MessageCategory messageCategory,Move move, String nickname) {
        super(messageCategory,nickname);
        this.move =move;
    }


    public Move getMessageMove() {
        return move;

    }
}
