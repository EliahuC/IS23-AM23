package it.polimi.ingsw.Messages.ServerToClient;

public class ValidMoveMessage extends ServerMessage{
    public ValidMoveMessage() {
        super(MessageCategory.RETURN_MESSAGE);
        setReturnMessage("The move that you made is valid");
    }
}
