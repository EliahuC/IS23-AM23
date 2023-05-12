package it.polimi.ingsw.Messages.ServerToClient;

public class ErrorMessage extends ServerMessage {
    public ErrorMessage() {
        super(MessageCategory.WARNING);
    }
}
