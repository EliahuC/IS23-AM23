package it.polimi.ingsw.Messages.ServerToClient;

/**
 * Message that returns the current playing player when the turn finish
 * @author Eliahu Cohen
 *
 */
public class CurrPlayingMessage extends ServerMessage{
    private Integer currPlaying;
    public CurrPlayingMessage(Integer currPlaying) {
        super(MessageCategory.CURRPLAYING);
         this.currPlaying=currPlaying;
    }

    public Integer getCurrPlaying() {
        return currPlaying;
    }
}
