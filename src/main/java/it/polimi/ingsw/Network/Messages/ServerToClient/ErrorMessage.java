package it.polimi.ingsw.Network.Messages.ServerToClient;

import it.polimi.ingsw.Network.Messages.Message;

public class ErrorMessage extends Message {
    public ErrorMessage() {
        super(MessageCategory.WARNING, "GameMaster");
    }
}
