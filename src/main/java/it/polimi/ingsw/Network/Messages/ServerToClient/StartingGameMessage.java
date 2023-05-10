package it.polimi.ingsw.Network.Messages.ServerToClient;

public class StartingGameMessage extends ServerMessage{
    public StartingGameMessage() {
        super(MessageCategory.RETURN_MESSAGE);
        setReturnMessage("The game is starting...");
    }
}
