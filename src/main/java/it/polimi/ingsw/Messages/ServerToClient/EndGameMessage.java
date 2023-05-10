package it.polimi.ingsw.Messages.ServerToClient;

public class EndGameMessage extends ServerMessage {
    public EndGameMessage() {
        super(MessageCategory.END_GAME_MESSAGE);
    }
}
