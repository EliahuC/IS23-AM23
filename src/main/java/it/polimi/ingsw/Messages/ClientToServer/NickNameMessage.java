package it.polimi.ingsw.Messages.ClientToServer;

import it.polimi.ingsw.Messages.ClientToServer.PossibleMoves.Move;

/**
 * @author Eliahu Cohen
 * Message that contains the client nickname to check
 */
public class NickNameMessage extends ClientMessage{
    public NickNameMessage(String nickname) {
        super(MessageCategory.NICKNAME,null, nickname);
    }
}
