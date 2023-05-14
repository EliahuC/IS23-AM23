package it.polimi.ingsw.Messages.ClientToServer;

public class PingToServer extends ClientMessage{
    public PingToServer(String nickname) {
        super(MessageCategory.PINGTOSERVER,null, nickname);
    }

}
