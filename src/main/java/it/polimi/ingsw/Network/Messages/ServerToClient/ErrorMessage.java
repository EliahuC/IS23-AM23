package it.polimi.ingsw.Network.Messages.ServerToClient;

import it.polimi.ingsw.Network.Messages.Message;

public class ErrorMessage extends ServerMessage {
    public ErrorMessage() {
        super(MessageCategory.WARNING);
    }
}
