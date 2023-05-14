package it.polimi.ingsw.Messages.ClientToServer;

import it.polimi.ingsw.Messages.ClientToServer.PossibleMoves.Move;

public class StartGameMessage extends ClientMessage{
    public StartGameMessage(String nickname) {
        super(MessageCategory.START_GAME, null, nickname);
    }
}
