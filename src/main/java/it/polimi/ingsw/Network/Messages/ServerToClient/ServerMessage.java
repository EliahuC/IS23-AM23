package it.polimi.ingsw.Network.Messages.ServerToClient;

import it.polimi.ingsw.Network.Messages.Message;

public class ServerMessage extends Message {
    private final static String sender="GameMaster";
    public ServerMessage(MessageCategory MC) {
        super(MC, sender);
    }
}
