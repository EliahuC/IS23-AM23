package it.polimi.ingsw.Messages.ServerToClient;

import it.polimi.ingsw.model.player.BookShelf;

/**
 * @author Eliahu Cohen
 * Message that returns the changed bookshelf to the player
 */
public class BookshelfMessage extends ServerMessage{
    private BookShelf playerBookshelf;
    public BookshelfMessage(BookShelf bookShelf) {
        super(MessageCategory.BOOKSHELF);
        this.playerBookshelf=bookShelf;
    }
    public BookShelf getPlayerBookshelf() {
        return playerBookshelf;
    }
}
