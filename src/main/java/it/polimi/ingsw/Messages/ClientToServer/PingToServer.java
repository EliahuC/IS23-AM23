package it.polimi.ingsw.Messages.ClientToServer;

/**
 * @author Eliahu Cohen
 * Message that returns a ping from client to server
 */
public class PingToServer extends ClientMessage{
    public PingToServer(String nickname) {
        super(MessageCategory.PINGTOSERVER,null, nickname);
    }

}
