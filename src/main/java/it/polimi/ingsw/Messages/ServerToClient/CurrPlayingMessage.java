package it.polimi.ingsw.Messages.ServerToClient;

/**
 * @author Eliahu Cohen
 * Message that returns the current playing player when the turn finish
 */
public class CurrPlayingMessage extends ServerMessage{
    private Integer currPlaying;
    public CurrPlayingMessage(Integer currPlaying) {
        super(MessageCategory.CURRPLAYING);
         this.currPlaying=currPlaying;
    }
}
