package it.polimi.ingsw.Messages.ServerToClient;

import it.polimi.ingsw.model.player.BookShelf;
import it.polimi.ingsw.model.player.Player;

public class SuccessMessage extends ServerMessage{
    private final Player player;
    public SuccessMessage(Player player) {
        super(MessageCategory.SUCCESS);
        this.player=player;
    }

    public Integer getScore() {
        return player.getScore();
    }

    public String getNickName(){
        return player.getNickName();
    }

    public BookShelf getBookshelf(){
        return player.getPlayerBookshelf();
    }
}
