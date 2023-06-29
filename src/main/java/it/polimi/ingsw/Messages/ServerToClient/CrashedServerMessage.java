package it.polimi.ingsw.Messages.ServerToClient;

/**
 * Message to notify the client the server crushed
 * @author Eliahu Cohen
 */
public class CrashedServerMessage extends ServerMessage{
    public CrashedServerMessage() {
        super(MessageCategory.CRASHED);
    }
}
