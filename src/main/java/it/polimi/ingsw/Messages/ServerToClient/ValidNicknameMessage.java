package it.polimi.ingsw.Messages.ServerToClient;

/**
 * @author Eliahu Cohen
 * Message that indicates the validity of the nickname selected from the client
 */
public class ValidNicknameMessage extends ServerMessage{
    public ValidNicknameMessage() {
        super(MessageCategory.VALID_NICKNAME);
    }
}
