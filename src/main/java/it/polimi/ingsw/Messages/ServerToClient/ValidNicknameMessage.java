package it.polimi.ingsw.Messages.ServerToClient;

/**
 * Message that indicates the validity of the nickname selected from the client
 * @author Eliahu Cohen
 *
 */
public class ValidNicknameMessage extends ServerMessage{
    public ValidNicknameMessage() {
        super(MessageCategory.VALID_NICKNAME);
    }
}
