package it.polimi.ingsw.Messages.ServerToClient;

/**
 * Message that is sent from the server to the clients
 * when a common goal card is completed
 * @author Eliahu Cohen
 */
public class CommonCompletedMessage extends ServerMessage{
    public CommonCompletedMessage(String s) {
        super(MessageCategory.COMMONGOAL);
        setReturnMessage(s);
    }
}
