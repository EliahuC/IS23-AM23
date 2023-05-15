package it.polimi.ingsw.Messages.ServerToClient;

import it.polimi.ingsw.model.Game;

public class GameIsStartingMessage extends ServerMessage{
    private final Game game;
    public GameIsStartingMessage(Game game) {
        super(MessageCategory.STARTING_GAME_MESSAGE);
        this.game=game;
        setReturnMessage("The game is starting...");
    }

    public Game getGame() {
        return game;
    }
}
