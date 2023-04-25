package it.polimi.ingsw.Network.Messages.ClientToServer;

import it.polimi.ingsw.Network.Messages.Message;

public class LobbyCreationMessage extends Message {
    private Integer numPlayers;
    public LobbyCreationMessage( String n,int num) {
        super(MessageCategory.CREATE_LOBBY, n);
        this.numPlayers=num;

    }
}
