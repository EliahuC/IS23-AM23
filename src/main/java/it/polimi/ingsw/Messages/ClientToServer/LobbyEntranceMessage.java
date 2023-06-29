package it.polimi.ingsw.Messages.ClientToServer;

import it.polimi.ingsw.Messages.ClientToServer.PossibleMoves.Move;

/**
 * Message sent from client to enter a lobby
 * @author Eliahu Cohen
 */
public class LobbyEntranceMessage extends ClientMessage{
    public LobbyEntranceMessage() {
        super(MessageCategory.ENTER_LOBBY,null, null);
    }

    public LobbyEntranceMessage(MessageCategory messageCategory, Move move, String nickname) {
        super(messageCategory, move, nickname);
    }
}
