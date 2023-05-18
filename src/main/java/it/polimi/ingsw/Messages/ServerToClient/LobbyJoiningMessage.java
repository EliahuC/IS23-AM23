package it.polimi.ingsw.Messages.ServerToClient;

/**
 * @author Eliahu Cohen
 * Message that returns the id of lobby the player just joined
 */
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
