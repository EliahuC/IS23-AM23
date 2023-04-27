package it.polimi.ingsw.Network.Messages.ClientToServer;

import it.polimi.ingsw.Network.Messages.ClientToServer.PossibleMoves.Move;

public class LobbyLogoutMessage extends ClientMessage{
    public LobbyLogoutMessage( String nickname) {
        super(MessageCategory.LOGOUT_LOBBY, null, nickname);
    }
}
