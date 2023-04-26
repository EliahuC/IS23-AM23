package it.polimi.ingsw.Network.Messages.ClientToServer;

import it.polimi.ingsw.Network.Messages.ClientToServer.PossibleMoves.Move;
import it.polimi.ingsw.Network.Messages.Message;

public class ClientMessage extends Message {
    public final Move m;
    public ClientMessage(MessageCategory messageCategory,Move move, String nickname) {
        super(messageCategory,nickname);
        this.m=move;
    }
    public Move getMessageMove() {
        return m;
    }
}
