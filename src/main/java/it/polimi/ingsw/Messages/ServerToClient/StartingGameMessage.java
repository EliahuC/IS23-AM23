package it.polimi.ingsw.Messages.ServerToClient;

public class StartingGameMessage extends ServerMessage{
    public StartingGameMessage() {
        super(MessageCategory.RETURN_MESSAGE);
        setReturnMessage("The game is starting...");
    }
}
