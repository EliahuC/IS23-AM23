package it.polimi.ingsw.Network.Messages.ServerToClient;

public class ValidMoveMessage extends ServerMessage{
    public ValidMoveMessage() {
        super(MessageCategory.RETURN_MESSAGE);
        addReturnMessage("The move that you made is valid");
    }
}
