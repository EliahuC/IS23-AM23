package it.polimi.ingsw.model.board.goalCards;

import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.player.BookShelf;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommonGoalCard11Test extends TestCase {

    public void testCheckGoalCardCGC11_FIRST() {
        CommonGoalCard c48 = new CommonGoalCard11(new Launcher());
        c48.initNumCompleted();
        BookShelf bs34 = new BookShelf();
        bs34.setTile(0, 0, new ItemTile("FRAMES"));
        bs34.setTile(1, 0, new ItemTile("FRAMES"));
        bs34.setTile(2, 0, new ItemTile("FRAMES"));
        bs34.setTile(3, 0, new ItemTile("FRAMES"));
        bs34.setTile(4, 0, new ItemTile("FRAMES"));
        bs34.setTile(5, 0, new ItemTile("CATS"));
        bs34.setTile(0, 1, new ItemTile("GAMES"));
        bs34.setTile(1, 1, new ItemTile("GAMES"));
        bs34.setTile(2, 1, new ItemTile("TROPHIES"));
        bs34.setTile(3, 1, new ItemTile("TROPHIES"));
        bs34.setTile(4, 1, new ItemTile("GAMES"));
        bs34.setTile(5, 1, new ItemTile("CATS"));
        bs34.setTile(0, 2, new ItemTile("BOOKS"));
        bs34.setTile(1, 2, new ItemTile("BOOKS"));
        bs34.setTile(2, 2, new ItemTile("GAMES"));
        bs34.setTile(3, 2, new ItemTile("CATS"));
        bs34.setTile(4, 2, new ItemTile("GAMES"));
        bs34.setTile(5, 2, new ItemTile("CATS"));
        bs34.setTile(0, 3, new ItemTile("CATS"));
        bs34.setTile(1, 3, new ItemTile("CATS"));
        bs34.setTile(2, 3, new ItemTile("TROPHIES"));
        bs34.setTile(3, 3, new ItemTile("GAMES"));
        bs34.setTile(4, 3, new ItemTile("TROPHIES"));
        bs34.setTile(5, 3, new ItemTile("BOOKS"));
        bs34.setTile(0, 4, new ItemTile("PLANTS"));
        bs34.setTile(1, 4, new ItemTile("PLANTS"));
        bs34.setTile(2, 4, new ItemTile("GAMES"));
        bs34.setTile(3, 4, new ItemTile("GAMES"));
        bs34.setTile(4, 4, new ItemTile("GAMES"));
        bs34.setTile(5, 4, new ItemTile("BOOKS"));
        c48.checkGoal(bs34);
        assertEquals(1, c48.getNumCompleted());
    }
    public void testCheckGoalCardCGC11_SECOND() {
        CommonGoalCard c49 = new CommonGoalCard11(new Launcher());
        c49.initNumCompleted();
        BookShelf bs35 = new BookShelf();
        bs35.setTile(0, 0, new ItemTile("FRAMES"));
        bs35.setTile(1, 0, new ItemTile("FRAMES"));
        bs35.setTile(2, 0, new ItemTile("FRAMES"));
        bs35.setTile(3, 0, new ItemTile("FRAMES"));
        bs35.setTile(4, 0, new ItemTile("FRAMES"));
        bs35.setTile(5, 0, new ItemTile("FRAMES"));
        bs35.setTile(0, 1, new ItemTile("GAMES"));
        bs35.setTile(1, 1, new ItemTile("GAMES"));
        bs35.setTile(2, 1, new ItemTile("TROPHIES"));
        bs35.setTile(3, 1, new ItemTile("TROPHIES"));
        bs35.setTile(4, 1, new ItemTile("FRAMES"));
        bs35.setTile(5, 1, new ItemTile("CATS"));
        bs35.setTile(0, 2, new ItemTile("BOOKS"));
        bs35.setTile(1, 2, new ItemTile("BOOKS"));
        bs35.setTile(2, 2, new ItemTile("BOOK"));
        bs35.setTile(3, 2, new ItemTile("FRAMES"));
        bs35.setTile(4, 2, new ItemTile("GAMES"));
        bs35.setTile(5, 2, new ItemTile("FRAMES"));
        bs35.setTile(0, 3, new ItemTile("CATS"));
        bs35.setTile(1, 3, new ItemTile("CATS"));
        bs35.setTile(2, 3, new ItemTile("TROPHIES"));
        bs35.setTile(3, 3, new ItemTile("GAMES"));
        bs35.setTile(4, 3, new ItemTile("TROPHIES"));
        bs35.setTile(5, 3, new ItemTile("BOOKS"));
        bs35.setTile(0, 4, new ItemTile("PLANTS"));
        bs35.setTile(1, 4, new ItemTile("PLANTS"));
        bs35.setTile(2, 4, new ItemTile("GAMES"));
        bs35.setTile(3, 4, new ItemTile("GAMES"));
        bs35.setTile(4, 4, new ItemTile("GAMES"));
        bs35.setTile(5, 4, new ItemTile("BOOKS"));
        c49.checkGoal(bs35);
        assertEquals(1, c49.getNumCompleted());
    }
    public void testCheckGoalCardCGC11_THIRD() {
        CommonGoalCard c50 = new CommonGoalCard11(new Launcher());
        c50.initNumCompleted();
        BookShelf bs35 = new BookShelf();
        bs35.setTile(0, 0, new ItemTile("FRAMES"));
        bs35.setTile(1, 0, new ItemTile("PLANTS"));
        bs35.setTile(2, 0, new ItemTile("CATS"));
        bs35.setTile(3, 0, new ItemTile("BOOKS"));
        bs35.setTile(4, 0, new ItemTile("GAMES"));
        bs35.setTile(5, 0, new ItemTile("TROPHIES"));
        bs35.setTile(0, 1, new ItemTile("FRAMES"));
        bs35.setTile(1, 1, new ItemTile("PLANTS"));
        bs35.setTile(2, 1, new ItemTile("CATS"));
        bs35.setTile(3, 1, new ItemTile("BOOKS"));
        bs35.setTile(4, 1, new ItemTile("GAMES"));
        bs35.setTile(5, 1, new ItemTile("TROPHIES"));
        bs35.setTile(0, 2, new ItemTile("FRAMES"));
        bs35.setTile(1, 2, new ItemTile("PLANTS"));
        bs35.setTile(2, 2, new ItemTile("CATS"));
        bs35.setTile(3, 2, new ItemTile("BOOKS"));
        bs35.setTile(4, 2, new ItemTile("GAMES"));
        bs35.setTile(5, 2, new ItemTile("TROPHIES"));
        bs35.setTile(0, 3, new ItemTile("FRAMES"));
        bs35.setTile(1, 3, new ItemTile("PLANTS"));
        bs35.setTile(2, 3, new ItemTile("CATS"));
        bs35.setTile(3, 3, new ItemTile("BOOKS"));
        bs35.setTile(4, 3, new ItemTile("GAMES"));
        bs35.setTile(5, 3, new ItemTile("TROPHIES"));
        bs35.setTile(0, 4, new ItemTile("FRAMES"));
        bs35.setTile(1, 4, new ItemTile("PLANTS"));
        bs35.setTile(2, 4, new ItemTile("CATS"));
        bs35.setTile(3, 4, new ItemTile("BOOKS"));
        bs35.setTile(4, 4, new ItemTile("GAMES"));
        bs35.setTile(5, 4, new ItemTile("TROPHIES"));
        c50.checkGoal(bs35);
        assertEquals(0, c50.getNumCompleted());
    }
}
