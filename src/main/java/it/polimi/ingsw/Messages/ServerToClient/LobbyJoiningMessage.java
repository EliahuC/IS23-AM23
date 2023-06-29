package it.polimi.ingsw.Messages.ServerToClient;

/**
 * Message that returns the id of lobby the player just joined
 * @author Eliahu Cohen
 *
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
