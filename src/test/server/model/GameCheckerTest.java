package server.model;

import junit.framework.TestCase;
import server.Launcher;
import server.model.board.Bag;
import server.model.board.LivingRoom;
import server.model.player.BookShelf;

public class GameCheckerTest extends TestCase {

    public void testIsRestorable() {
        Launcher l = new Launcher();
        l.setNumPlayers(4);
        GameChecker gc = new GameChecker(l);
        Bag bag = new Bag();
        LivingRoom board = new LivingRoom(l);
        board.putTile(4,4);
        assertTrue(gc.isRestorable(board.getBoard()));

        board.putTile(4,5);
        assertFalse(gc.isRestorable(board.getBoard()));
    }

    public void testCheckColumnCapability() {
        BookShelf bs = new BookShelf();

    }

    public void testIsLegalAction() {
    }

    public void testTestIsLegalAction() {
    }

    public void testTestIsLegalAction1() {
    }

    public void testIsBookShelfFull() {
    }
}