package it.polimi.ingsw.Messages.ClientToServer;


/**
 * Message sent from client to close the connection
 * @author Eliahu Cohen
 */
public class CloseMessage extends ClientMessage{
    public CloseMessage() {
        super(MessageCategory.CLOSE, null, "nickname");
    }
}
