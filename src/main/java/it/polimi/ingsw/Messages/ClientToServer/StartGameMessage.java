package it.polimi.ingsw.Messages.ClientToServer;



public class StartGameMessage extends ClientMessage{
    public StartGameMessage(String nickname) {
        super(MessageCategory.START_GAME, null, nickname);
    }
}
