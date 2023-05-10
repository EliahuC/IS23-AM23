package it.polimi.ingsw.Messages.ServerToClient;

import it.polimi.ingsw.Messages.Message;

public class ErrorMessage extends ServerMessage {
    public ErrorMessage() {
        super(Message.MessageCategory.WARNING);
    }
}
