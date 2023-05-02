package it.polimi.ingsw.Network.Messages.ServerToClient;

import it.polimi.ingsw.model.player.BookShelf;

public class BookshelfMessage extends ServerMessage{
    private BookShelf playerBookshelf;
    public BookshelfMessage(BookShelf bookShelf) {
        super(MessageCategory.RETURN_MESSAGE);
        this.playerBookshelf=bookShelf;
    }
    public BookShelf getPlayerBookshelf() {
        return playerBookshelf;
    }
}
