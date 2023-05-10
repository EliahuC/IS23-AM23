package it.polimi.ingsw.Messages.ServerToClient;

import it.polimi.ingsw.Messages.Message;

public class ServerMessage extends Message {
    private final static String sender="GameMaster";
    public ServerMessage(MessageCategory MC) {
        super(MC, sender);
    }
}
