package it.polimi.ingsw.Messages.ClientToServer;

/**
 * Message that returns a ping from client to server
 * @author Eliahu Cohen
 *
 */
public class PingToServer extends ClientMessage{
    public PingToServer(String nickname) {
        super(MessageCategory.PINGTOSERVER,null, nickname);
    }

}
