package it.polimi.ingsw.Messages.ClientToServer;

import it.polimi.ingsw.Messages.ClientToServer.PossibleMoves.Move;

/**
 * Message that contains the client nickname to check
 * @author Eliahu Cohen
 *
 */
public class NickNameMessage extends ClientMessage{
    public NickNameMessage(String nickname) {
        super(MessageCategory.NICKNAME,null, nickname);
    }
}
