package it.polimi.ingsw.Messages.ServerToClient;

public class CurrPlayingMessage extends ServerMessage{
    private Integer currPlaying;
    public CurrPlayingMessage(Integer currPlaying) {
        super(MessageCategory.CURRPLAYING);
         this.currPlaying=currPlaying;
    }
}
