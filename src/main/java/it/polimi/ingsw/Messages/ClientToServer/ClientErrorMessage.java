package it.polimi.ingsw.Messages.ClientToServer;

public class ClientErrorMessage extends ClientMessage{
    public ClientErrorMessage() {
        super(MessageCategory.WARNING, null, "GameMaster");
    }
}
