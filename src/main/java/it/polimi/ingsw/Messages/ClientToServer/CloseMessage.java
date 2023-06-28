package it.polimi.ingsw.Messages.ClientToServer;


/**
 * @author Eliahu Cohen
 * Message sent from client to close the connection
 */
public class CloseMessage extends ClientMessage{
    public CloseMessage() {
        super(MessageCategory.CLOSE, null, "nickname");
    }
}
