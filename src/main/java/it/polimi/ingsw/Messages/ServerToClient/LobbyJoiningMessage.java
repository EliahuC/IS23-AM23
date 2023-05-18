package it.polimi.ingsw.Messages.ServerToClient;

public class LobbyJoiningMessage extends ServerMessage{
    private final Integer idLobby;
    public LobbyJoiningMessage(Integer idLobby) {
        super(MessageCategory.RETURN_MESSAGE);
        this.idLobby=idLobby;
        setReturnMessage("You joined the lobby n° "+String.valueOf(idLobby));
    }

    public Integer getIdLobby() {
        return idLobby;
    }
}
