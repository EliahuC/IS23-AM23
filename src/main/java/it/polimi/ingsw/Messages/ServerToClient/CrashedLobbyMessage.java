package it.polimi.ingsw.Messages.ServerToClient;

public class CrashedLobbyMessage extends ServerMessage{
    public CrashedLobbyMessage(String s) {
        super(MessageCategory.CRASHED);
        setReturnMessage(s);

    }
}
