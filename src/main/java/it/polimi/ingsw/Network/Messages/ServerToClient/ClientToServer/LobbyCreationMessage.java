package it.polimi.ingsw.Network.Messages.ServerToClient.ClientToServer;

public class LobbyCreationMessage extends ClientMessage {
    private Integer numPlayers;
    public LobbyCreationMessage( String n,int num) {
        super(MessageCategory.CREATE_LOBBY,null, n);
        this.numPlayers=num;

    }

    public Integer getNumPlayers() {
        return numPlayers;
    }
}
