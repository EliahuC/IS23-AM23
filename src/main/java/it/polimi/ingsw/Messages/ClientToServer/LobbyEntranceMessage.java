package it.polimi.ingsw.Messages.ClientToServer;

import it.polimi.ingsw.Messages.ClientToServer.PossibleMoves.Move;

public class LobbyEntranceMessage extends ClientMessage{
    public LobbyEntranceMessage() {
        super(MessageCategory.ENTER_LOBBY,null, null);
    }

    public LobbyEntranceMessage(MessageCategory messageCategory, Move move, String nickname) {
        super(messageCategory, move, nickname);
    }
}
