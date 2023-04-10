package server.model.board.goalCards;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import server.model.board.ItemTile;
import server.model.player.BookShelf;

import static org.junit.jupiter.api.Assertions.*;

public class CommonGoalCard6Test extends TestCase {
    public void testCheckGoalCardCGC6_FIRST() {
        CommonGoalCard c33 = new CommonGoalCard6();
        c33.initNumCompleted();
        BookShelf bs26 = new BookShelf();
        bs26.setTile(0, 0, new ItemTile("CATS"));
        bs26.setTile(1, 0, new ItemTile("PLANTS"));
        bs26.setTile(2, 0, new ItemTile("GAMES"));
        bs26.setTile(3, 0, new ItemTile("TROPHIES"));
        bs26.setTile(4, 0, new ItemTile("FRAMES"));
        bs26.setTile(5, 0, new ItemTile("BOOKS"));
        bs26.setTile(0, 1, new ItemTile("PLANTS"));
        bs26.setTile(1, 1, new ItemTile("GAMES"));
        bs26.setTile(2, 1, new ItemTile("GAMES"));
        bs26.setTile(3, 1, new ItemTile("TROPHIES"));
        bs26.setTile(4, 1, new ItemTile("FRAMES"));
        bs26.setTile(5, 1, new ItemTile("BOOKS"));
        bs26.setTile(0, 2, new ItemTile("GAMES"));
        bs26.setTile(1, 2, new ItemTile("PLANTS"));
        bs26.setTile(2, 2, new ItemTile("CATS"));
        bs26.setTile(3, 2, new ItemTile("FRAMES"));
        bs26.setTile(4, 2, new ItemTile("GAMES"));
        bs26.setTile(5, 2, new ItemTile("PLANTS"));
        bs26.setTile(0, 3, new ItemTile("PLANTS"));
        bs26.setTile(1, 3, new ItemTile("CATS"));
        bs26.setTile(2, 3, new ItemTile("TROPHIES"));
        bs26.setTile(3, 3, new ItemTile("BOOKS"));
        bs26.setTile(4, 3, new ItemTile("GAMES"));
        bs26.setTile(5, 3, new ItemTile("GAMES"));
        bs26.setTile(0, 4, new ItemTile("PLANTS"));
        bs26.setTile(1, 4, new ItemTile("BOOKS"));
        bs26.setTile(2, 4, new ItemTile("TROPHIES"));
        bs26.setTile(3, 4, new ItemTile("FRAMES"));
        bs26.setTile(4, 4, new ItemTile("CATS"));
        bs26.setTile(5, 4, new ItemTile("GAMES"));
        c33.checkGoal(bs26);
        assertEquals(1, c33.getNumCompleted());
    }

    public void testCheckGoalCardCGC6_SECOND() {
        CommonGoalCard c34 = new CommonGoalCard6();
        c34.initNumCompleted();
        BookShelf bs27 = new BookShelf();
        bs27.setTile(0, 0, new ItemTile("CATS"));
        bs27.setTile(1, 0, new ItemTile("PLANTS"));
        bs27.setTile(2, 0, new ItemTile("GAMES"));
        bs27.setTile(3, 0, new ItemTile("TROPHIES"));
        bs27.setTile(4, 0, new ItemTile("FRAMES"));
        bs27.setTile(5, 0, new ItemTile("BOOKS"));
        bs27.setTile(0, 1, new ItemTile("CATS"));
        bs27.setTile(1, 1, new ItemTile("PLANTS"));
        bs27.setTile(2, 1, new ItemTile("GAMES"));
        bs27.setTile(3, 1, new ItemTile("TROPHIES"));
        bs27.setTile(4, 1, new ItemTile("FRAMES"));
        bs27.setTile(5, 1, new ItemTile("BOOKS"));
        bs27.setTile(0, 2, new ItemTile("CATS"));
        bs27.setTile(1, 2, new ItemTile("PLANTS"));
        bs27.setTile(2, 2, new ItemTile("GAMES"));
        bs27.setTile(3, 2, new ItemTile("TROPHIES"));
        bs27.setTile(4, 2, new ItemTile("FRAMES"));
        bs27.setTile(5, 2, new ItemTile("BOOKS"));
        bs27.setTile(0, 3, new ItemTile("CATS"));
        bs27.setTile(1, 3, new ItemTile("PLANTS"));
        bs27.setTile(2, 3, new ItemTile("GAMES"));
        bs27.setTile(3, 3, new ItemTile("TROPHIES"));
        bs27.setTile(4, 3, new ItemTile("FRAMES"));
        bs27.setTile(5, 3, new ItemTile("BOOKS"));
        bs27.setTile(0, 4, new ItemTile("CATS"));
        bs27.setTile(1, 4, new ItemTile("PLANTS"));
        bs27.setTile(2, 4, new ItemTile("GAMES"));
        bs27.setTile(3, 4, new ItemTile("TROPHIES"));
        bs27.setTile(4, 4, new ItemTile("FRAMES"));
        bs27.setTile(5, 4, new ItemTile("BOOKS"));
        c34.checkGoal(bs27);
        assertEquals(0, c34.getNumCompleted());
    }
}