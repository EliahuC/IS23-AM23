package it.polimi.ingsw.Network.Messages.ClientToServer;

import it.polimi.ingsw.Network.Messages.Message;

public class LobbyCreationMessage extends Message {
    public LobbyCreationMessage( String n) {
        super(MessageCategory.CREATE_LOBBY, n);
    }
}
