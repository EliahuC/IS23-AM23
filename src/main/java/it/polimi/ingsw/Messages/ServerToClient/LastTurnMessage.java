package it.polimi.ingsw.Messages.ServerToClient;

/**
 * @author Eliahu Cohen
 * Message returned when the last turn starts
 */
public class LastTurnMessage extends ServerMessage{
    public LastTurnMessage() {
        super(MessageCategory.LAST_TURN_MESSAGE);
        setReturnMessage("A player completed his bookshelf, the game will end when player 4 will finish his turn");
    }
}
