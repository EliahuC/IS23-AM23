package it.polimi.ingsw.Messages.ServerToClient;

public class GameIsStartingMessage extends ServerMessage{
    public GameIsStartingMessage() {
        super(MessageCategory.STARTING_GAME_MESSAGE);
        setReturnMessage("The game is starting...");
    }
}
