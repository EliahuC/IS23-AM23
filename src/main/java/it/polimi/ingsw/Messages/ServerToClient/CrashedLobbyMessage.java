package it.polimi.ingsw.Messages.ServerToClient;

/**
 * @author Simone Controguerra
 * Message that appears when a lobby crash
 */
public class CrashedLobbyMessage extends ServerMessage{
    public CrashedLobbyMessage(String s) {
        super(MessageCategory.CRASHED);
        setReturnMessage(s);

    }
}
