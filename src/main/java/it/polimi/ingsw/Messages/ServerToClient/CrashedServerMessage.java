package it.polimi.ingsw.Messages.ServerToClient;

/**
 * @author Eliahu Cohen
 * Message to notify the client the server crushed
 *
 */
public class CrashedServerMessage extends ServerMessage{
    public CrashedServerMessage() {
        super(MessageCategory.CRASHED);
    }
}
