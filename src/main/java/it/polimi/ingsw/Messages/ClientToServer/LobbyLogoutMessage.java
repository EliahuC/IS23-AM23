package it.polimi.ingsw.Messages.ClientToServer;

/**
 *  Message sent from client to logout from a lobby
 * @author Eliahu Cohen
 */
public class LobbyLogoutMessage extends ClientMessage{
    private String nickname;
    public LobbyLogoutMessage() {
        super(MessageCategory.LOGOUT_LOBBY, null,null);
        this.nickname="to be added";
    }
    public void setNickname(String s){
        nickname=s;
    }

    @Override
    public String getNickname() {
        return nickname;
    }

}
