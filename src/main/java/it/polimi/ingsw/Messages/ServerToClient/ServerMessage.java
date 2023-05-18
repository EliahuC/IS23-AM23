package it.polimi.ingsw.Messages.ServerToClient;

import it.polimi.ingsw.Messages.Message;

/**
 * @author Eliahu Cohen
 * Class that will be extended from all the servers messages
 */
public class ServerMessage extends Message {
    private final static String sender="GameMaster";
    public ServerMessage(MessageCategory MC) {
        super(MC, sender);
    }
}
