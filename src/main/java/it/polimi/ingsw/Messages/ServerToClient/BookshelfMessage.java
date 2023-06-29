package it.polimi.ingsw.Messages.ServerToClient;

import it.polimi.ingsw.model.player.BookShelf;

/**
 * Message that returns the changed bookshelf to the player
 * @author Eliahu Cohen
 *
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
