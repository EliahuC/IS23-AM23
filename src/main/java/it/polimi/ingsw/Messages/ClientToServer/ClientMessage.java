package it.polimi.ingsw.Messages.ClientToServer;

import it.polimi.ingsw.Messages.ClientToServer.PossibleMoves.Move;
import it.polimi.ingsw.Messages.Message;

public class ClientMessage extends Message {
    public final Move m;
    public ClientMessage(MessageCategory messageCategory,Move move, String nickname) {
        super(messageCategory,nickname);
        this.m=move;
    }

    public Move getM() {
        return m;
    }

    public Move getMessageMove() {
        return m;

    }
}
