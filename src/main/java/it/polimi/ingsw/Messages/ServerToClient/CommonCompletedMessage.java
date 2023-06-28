package it.polimi.ingsw.Messages.ServerToClient;

/**
 * @author Eliahu Cohen
 * Message that is sent from the server to the clients
 * when a common goal card is compleated
 */
public class CommonCompletedMessage extends ServerMessage{
    public CommonCompletedMessage(String s) {
        super(MessageCategory.COMMONGOAL);
        setReturnMessage(s);
    }
}
