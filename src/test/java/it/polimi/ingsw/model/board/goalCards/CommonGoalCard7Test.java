package it.polimi.ingsw.model.board.goalCards;

import junit.framework.TestCase;
import it.polimi.ingsw.model.board.goalCards.CommonGoalCard;
import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.board.goalCards.CommonGoalCard7;
import it.polimi.ingsw.model.player.BookShelf;

/**
 * Unit test for simple App.
 */
public class CommonGoalCard7Test
        extends TestCase {

    public void testCheckGoalCardCGC7_FIRST() {
        CommonGoalCard c28 = new CommonGoalCard7(new Launcher());
        c28.initNumCompleted();
        BookShelf bs21 = new BookShelf();
        bs21.setTile(0, 0, new ItemTile("CATS"));
        bs21.setTile(1, 0, new ItemTile("PLANTS"));
        bs21.setTile(1, 1, new ItemTile("CATS"));
        bs21.setTile(2, 1, new ItemTile("TROPHIES"));
        bs21.setTile(2, 2, new ItemTile("CATS"));
        bs21.setTile(3, 2, new ItemTile("PLANTS"));
        bs21.setTile(3, 3, new ItemTile("CATS"));
        bs21.setTile(4, 3, new ItemTile("GAMES"));
        bs21.setTile(4, 4, new ItemTile("CATS"));
        bs21.setTile(5, 4, new ItemTile("CATS"));
        bs21.setTile(0, 4, new ItemTile("PLANTS"));
        bs21.setTile(1, 3, new ItemTile("TROPHIES"));
        bs21.setTile(3, 1, new ItemTile("PLANTS"));
        bs21.setTile(4, 0, new ItemTile("GAMES"));
        bs21.setTile(1, 4, new ItemTile("GAMES"));
        bs21.setTile(2, 3, new ItemTile("PLANTS"));
        bs21.setTile(4, 1, new ItemTile("TROPHIES"));
        bs21.setTile(5, 0, new ItemTile("CATS"));
        c28.checkGoal(bs21);
        assertEquals(1, c28.getNumCompleted());
    }

    public void testCheckGoalCardCGC7_SECOND() {
        CommonGoalCard c29 = new CommonGoalCard7(new Launcher());
        c29.initNumCompleted();
        BookShelf bs22 = new BookShelf();
        bs22.setTile(0, 0, new ItemTile("PLANTS"));
        bs22.setTile(1, 1, new ItemTile("CATS"));
        bs22.setTile(2, 2, new ItemTile("FRAMES"));
        bs22.setTile(3, 3, new ItemTile("PLANTS"));
        bs22.setTile(4, 4, new ItemTile("PLANTS"));
        bs22.setTile(1, 0, new ItemTile("PLANTS"));
        bs22.setTile(2, 1, new ItemTile("PLANTS"));
        bs22.setTile(3, 2, new ItemTile("PLANTS"));
        bs22.setTile(4, 3, new ItemTile("PLANTS"));
        bs22.setTile(5, 4, new ItemTile("PLANTS"));
        bs22.setTile(1, 4, new ItemTile("GAMES"));
        bs22.setTile(2, 3, new ItemTile("PLANTS"));
        bs22.setTile(4, 1, new ItemTile("TROPHIES"));
        bs22.setTile(5, 0, new ItemTile("CATS"));
        bs22.setTile(0, 4, new ItemTile("PLANTS"));
        bs22.setTile(1, 3, new ItemTile("TROPHIES"));
        bs22.setTile(3, 1, new ItemTile("PLANTS"));
        bs22.setTile(4, 0, new ItemTile("GAMES"));
        c29.checkGoal(bs22);
        assertEquals(1, c29.getNumCompleted());

    }

    public void testCheckGoalCardCGC7_THIRD() {
        CommonGoalCard c30 = new CommonGoalCard7(new Launcher());
        c30.initNumCompleted();
        BookShelf bs23 = new BookShelf();
        bs23.setTile(0, 0, new ItemTile("PLANTS"));
        bs23.setTile(1, 1, new ItemTile("CATS"));
        bs23.setTile(2, 2, new ItemTile("FRAMES"));
        bs23.setTile(3, 3, new ItemTile("PLANTS"));
        bs23.setTile(4, 4, new ItemTile("PLANTS"));
        bs23.setTile(1, 0, new ItemTile("PLANTS"));
        bs23.setTile(2, 1, new ItemTile("PLANTS"));
        bs23.setTile(3, 2, new ItemTile("PLANTS"));
        bs23.setTile(4, 3, new ItemTile("CATS"));
        bs23.setTile(5, 4, new ItemTile("GAMES"));
        bs23.setTile(1, 4, new ItemTile("GAMES"));
        bs23.setTile(2, 3, new ItemTile("PLANTS"));
        bs23.setTile(4, 1, new ItemTile("TROPHIES"));
        bs23.setTile(5, 0, new ItemTile("CATS"));
        bs23.setTile(0, 4, new ItemTile("FRAMES"));
        bs23.setTile(1, 3, new ItemTile("FRAMES"));
        bs23.setTile(3, 1, new ItemTile("FRAMES"));
        bs23.setTile(4, 0, new ItemTile("FRAMES"));
        c30.checkGoal(bs23);
        assertEquals(1, c30.getNumCompleted());
    }

    public void testCheckGoalCardCGC7_FOURTH() {
        CommonGoalCard c31 = new CommonGoalCard7(new Launcher());
        c31.initNumCompleted();
        BookShelf bs24 = new BookShelf();
        bs24.setTile(0, 0, new ItemTile("PLANTS"));
        bs24.setTile(1, 1, new ItemTile("CATS"));
        bs24.setTile(2, 2, new ItemTile("FRAMES"));
        bs24.setTile(3, 3, new ItemTile("PLANTS"));
        bs24.setTile(4, 4, new ItemTile("PLANTS"));
        bs24.setTile(1, 0, new ItemTile("PLANTS"));
        bs24.setTile(2, 1, new ItemTile("PLANTS"));
        bs24.setTile(3, 2, new ItemTile("PLANTS"));
        bs24.setTile(4, 3, new ItemTile("CATS"));
        bs24.setTile(5, 4, new ItemTile("GAMES"));
        bs24.setTile(1, 4, new ItemTile("PLANTS"));
        bs24.setTile(2, 3, new ItemTile("PLANTS"));
        bs24.setTile(4, 1, new ItemTile("PLANTS"));
        bs24.setTile(5, 0, new ItemTile("PLANTS"));
        bs24.setTile(0, 4, new ItemTile("FRAMES"));
        bs24.setTile(1, 3, new ItemTile("GAMES"));
        bs24.setTile(3, 1, new ItemTile("FRAMES"));
        bs24.setTile(4, 0, new ItemTile("GAMES"));
        c31.checkGoal(bs24);
        assertEquals(1, c31.getNumCompleted());
    }

    public void testCheckGoalCardCGC7_FIFTH() {
        CommonGoalCard c32 = new CommonGoalCard7(new Launcher());
        c32.initNumCompleted();
        BookShelf bs25 = new BookShelf();
        bs25.setTile(0, 0, new ItemTile("PLANTS"));
        bs25.setTile(1, 1, new ItemTile("CATS"));
        bs25.setTile(2, 2, new ItemTile("FRAMES"));
        bs25.setTile(3, 3, new ItemTile("PLANTS"));
        bs25.setTile(4, 4, new ItemTile("PLANTS"));
        bs25.setTile(1, 0, new ItemTile("PLANTS"));
        bs25.setTile(2, 1, new ItemTile("PLANTS"));
        bs25.setTile(3, 2, new ItemTile("PLANTS"));
        bs25.setTile(4, 3, new ItemTile("CATS"));
        bs25.setTile(5, 4, new ItemTile("GAMES"));
        bs25.setTile(1, 4, new ItemTile("PLANTS"));
        bs25.setTile(2, 3, new ItemTile("PLANTS"));
        bs25.setTile(4, 1, new ItemTile("PLANTS"));
        bs25.setTile(5, 0, new ItemTile("CATS"));
        bs25.setTile(0, 4, new ItemTile("FRAMES"));
        bs25.setTile(1, 3, new ItemTile("GAMES"));
        bs25.setTile(3, 1, new ItemTile("FRAMES"));
        bs25.setTile(4, 0, new ItemTile("GAMES"));
        c32.checkGoal(bs25);
        assertEquals(0, c32.getNumCompleted());
    }
}
