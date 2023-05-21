package it.polimi.ingsw.Messages.ServerToClient;

public class CrushedLobbyMessage extends ServerMessage{
    public CrushedLobbyMessage(String s) {
        super(MessageCategory.CRUSHED);
        setReturnMessage(s);

    }
}
