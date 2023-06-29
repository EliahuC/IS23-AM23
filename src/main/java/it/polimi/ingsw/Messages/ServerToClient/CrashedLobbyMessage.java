package it.polimi.ingsw.Messages.ServerToClient;

/**
 * Message that appears when a lobby crash
 * @author Simone Controguerra
 *
 */
public class CrashedLobbyMessage extends ServerMessage{
    public CrashedLobbyMessage(String s) {
        super(MessageCategory.CRASHED);
        setReturnMessage(s);

    }
}
