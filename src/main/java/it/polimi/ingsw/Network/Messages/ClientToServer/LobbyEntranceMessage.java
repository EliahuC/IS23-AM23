package it.polimi.ingsw.Network.Messages.ClientToServer;

import it.polimi.ingsw.Network.Messages.ClientToServer.PossibleMoves.Move;

public class LobbyEntranceMessage extends ClientMessage{
    public LobbyEntranceMessage(String nickname) {
        super(MessageCategory.ENTER_LOBBY,null, nickname);
    }

    public LobbyEntranceMessage(MessageCategory messageCategory, Move move, String nickname) {
        super(messageCategory, move, nickname);
    }
}
