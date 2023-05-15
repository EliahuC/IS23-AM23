package it.polimi.ingsw.Messages.ClientToServer;

import it.polimi.ingsw.Messages.ClientToServer.PossibleMoves.Move;

public class CloseMessage extends ClientMessage{
    public CloseMessage() {
        super(MessageCategory.CLOSE, null, "nickname");
    }
}
