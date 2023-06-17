package it.polimi.ingsw.Messages.ServerToClient;

/**
 * @author Eliahu Cohen
 * Message returned when the last turn starts
 */
public class LastTurnMessage extends ServerMessage{
    public LastTurnMessage() {
        super(MessageCategory.LAST_TURN_MESSAGE);
        setReturnMessage("It's the LAST round!");
    }
}
