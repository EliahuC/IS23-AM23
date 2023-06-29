package it.polimi.ingsw.Messages.ServerToClient;

/**
 * Message returned when the last turn starts
 * @author Eliahu Cohen
 *
 */
public class LastTurnMessage extends ServerMessage{
    public LastTurnMessage() {
        super(MessageCategory.LAST_TURN_MESSAGE);
        setReturnMessage("It's the LAST round!");
    }
}
