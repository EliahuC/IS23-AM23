package it.polimi.ingsw.Network.Messages.ClientToServer;

import it.polimi.ingsw.Network.Messages.ClientToServer.PossibleMoves.Move;

public class PingToServer extends ClientMessage{
    public PingToServer(String nickname) {
        super(MessageCategory.PING,null, nickname);
    }

}
