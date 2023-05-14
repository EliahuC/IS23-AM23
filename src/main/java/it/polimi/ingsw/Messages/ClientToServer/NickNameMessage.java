package it.polimi.ingsw.Messages.ClientToServer;

import it.polimi.ingsw.Messages.ClientToServer.PossibleMoves.Move;

public class NickNameMessage extends ClientMessage{
    public NickNameMessage(String nickname) {
        super(MessageCategory.NICKNAME,null, nickname);
    }
}
