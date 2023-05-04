package it.polimi.ingsw.Network.Messages.ServerToClient;

public class PingMessage extends ServerMessage{
    public PingMessage() {
        super(MessageCategory.RETURN_MESSAGE);
    }
}
