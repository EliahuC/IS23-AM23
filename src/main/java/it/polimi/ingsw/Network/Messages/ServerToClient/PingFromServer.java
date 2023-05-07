package it.polimi.ingsw.Network.Messages.ServerToClient;

public class PingFromServer extends ServerMessage{
    public PingFromServer() {
        super(MessageCategory.PING);
    }
}
