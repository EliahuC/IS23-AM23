package it.polimi.ingsw.Messages.ServerToClient;

public class ValidNicknameMessage extends ServerMessage{
    public ValidNicknameMessage() {
        super(MessageCategory.VALID_NICKNAME);
    }
}
