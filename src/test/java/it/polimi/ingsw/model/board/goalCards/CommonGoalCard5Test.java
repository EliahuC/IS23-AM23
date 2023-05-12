package it.polimi.ingsw.model.board.goalCards;

import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.player.BookShelf;
import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class CommonGoalCard5Test
        extends TestCase {

    public void testCategoriesAllowedCGC5_FIRST() { //testing of CategoriesAllowed method, if Hashset cat
                                                    //counts correctly how many categories are in a column of a bookshelf
        CommonGoalCard5 c10 = new CommonGoalCard5(new Launcher());
        c10.initNumCompleted();
        BookShelf bs3 = new BookShelf();
        bs3.setTile(0, 4, new ItemTile("PLANTS"));
        bs3.setTile(1, 4, new ItemTile("PLANTS"));
        bs3.setTile(2, 4, new ItemTile("PLANTS"));
        bs3.setTile(3, 4, new ItemTile("PLANTS"));
        bs3.setTile(4, 4, new ItemTile("PLANTS"));
        bs3.setTile(5, 4, new ItemTile("PLANTS"));
        c10.getCat().add(bs3.getTile(0, 4).getCategory());
        assertEquals(1, c10.CategoriesAllowed(bs3, 4, c10.getCat()));
    }

    public void testCategoriesAllowedCGC5_SECOND() {
        CommonGoalCard5 c11 = new CommonGoalCard5(new Launcher());
        c11.initNumCompleted();
        BookShelf bs4 = new BookShelf();
        bs4.setTile(0, 1, new ItemTile("CATS"));
        bs4.setTile(1, 1, new ItemTile("CATS"));
        bs4.setTile(2, 1, new ItemTile("CATS"));
        bs4.setTile(3, 1, new ItemTile("GAMES"));
        bs4.setTile(4, 1, new ItemTile("GAMES"));
        bs4.setTile(5, 1, new ItemTile("GAMES"));
        c11.getCat().add(bs4.getTile(0, 1).getCategory());
        assertEquals(2, c11.CategoriesAllowed(bs4, 1, c11.getCat()));
    }

    public void testCategoriesAllowedCGC5_THIRD() {
        CommonGoalCard5 c12 = new CommonGoalCard5(new Launcher());
        c12.initNumCompleted();
        BookShelf bs5 = new BookShelf();
        bs5.setTile(0, 2, new ItemTile("TROPHIES"));
        bs5.setTile(1, 2, new ItemTile("TROPHIES"));
        bs5.setTile(2, 2, new ItemTile("FRAMES"));
        bs5.setTile(3, 2, new ItemTile("FRAMES"));
        bs5.setTile(4, 2, new ItemTile("BOOKS"));
        bs5.setTile(5, 2, new ItemTile("BOOKS"));
        c12.getCat().add(bs5.getTile(0, 2).getCategory());
        assertEquals(3, c12.CategoriesAllowed(bs5, 2, c12.getCat()));
    }

    public void testCategoriesAllowedCGC5_FOURTH() {
        CommonGoalCard5 c13 = new CommonGoalCard5(new Launcher());
        c13.initNumCompleted();
        BookShelf bs6 = new BookShelf();
        bs6.setTile(0, 0, new ItemTile("PLANTS"));
        bs6.setTile(1, 0, new ItemTile("PLANTS"));
        bs6.setTile(2, 0, new ItemTile("PLANTS"));
        bs6.setTile(3, 0, new ItemTile("CATS"));
        bs6.setTile(4, 0, new ItemTile("FRAMES"));
        bs6.setTile(5, 0, new ItemTile("TROPHIES"));
        c13.getCat().add(bs6.getTile(0, 0).getCategory());
        assertEquals(4, c13.CategoriesAllowed(bs6, 0, c13.getCat()));
    }

    public void testCheckGoalCardCGC5_FIRST() { //testing the functionality of checkGoalCard method, if a Player achieves
        CommonGoalCard c8 = new CommonGoalCard5(new Launcher()); //the goal of the CGC, the attribute "NumCompleted" will increment,
        c8.initNumCompleted();                      //if not, as in the next test, "NumCompleted" will remain 0
        BookShelf bs1 = new BookShelf();
        bs1.setTile(0, 0, new ItemTile("PLANTS"));
        bs1.setTile(1, 0, new ItemTile("PLANTS"));
        bs1.setTile(2, 0, new ItemTile("PLANTS"));
        bs1.setTile(3, 0, new ItemTile("PLANTS"));
        bs1.setTile(4, 0, new ItemTile("PLANTS"));
        bs1.setTile(5, 0, new ItemTile("PLANTS"));
        bs1.setTile(0, 1, new ItemTile("CATS"));
        bs1.setTile(1, 1, new ItemTile("CATS"));
        bs1.setTile(2, 1, new ItemTile("CATS"));
        bs1.setTile(3, 1, new ItemTile("GAMES"));
        bs1.setTile(4, 1, new ItemTile("GAMES"));
        bs1.setTile(5, 1, new ItemTile("GAMES"));
        bs1.setTile(0, 2, new ItemTile("TROPHIES"));
        bs1.setTile(1, 2, new ItemTile("TROPHIES"));
        bs1.setTile(2, 2, new ItemTile("FRAMES"));
        bs1.setTile(3, 2, new ItemTile("FRAMES"));
        bs1.setTile(4, 2, new ItemTile("BOOKS"));
        bs1.setTile(5, 2, new ItemTile("BOOKS"));
        c8.checkGoal(bs1);
        assertEquals(1, c8.getNumCompleted());
    }

    public void testCheckGoalCardCGC5_SECOND() {
        CommonGoalCard c9 = new CommonGoalCard5(new Launcher());
        c9.initNumCompleted();
        BookShelf bs2 = new BookShelf();
        bs2.setTile(0, 0, new ItemTile("PLANTS"));
        bs2.setTile(1, 0, new ItemTile("PLANTS"));
        bs2.setTile(2, 0, new ItemTile("PLANTS"));
        bs2.setTile(3, 0, new ItemTile("CATS"));
        bs2.setTile(4, 0, new ItemTile("FRAMES"));
        bs2.setTile(5, 0, new ItemTile("TROPHIES"));
        bs2.setTile(0, 1, new ItemTile("CATS"));
        bs2.setTile(1, 1, new ItemTile("TROPHIES"));
        bs2.setTile(2, 1, new ItemTile("BOOKS"));
        bs2.setTile(3, 1, new ItemTile("GAMES"));
        bs2.setTile(4, 1, new ItemTile("GAMES"));
        bs2.setTile(5, 1, new ItemTile("GAMES"));
        bs2.setTile(0, 2, new ItemTile("TROPHIES"));
        bs2.setTile(1, 2, new ItemTile("TROPHIES"));
        bs2.setTile(2, 2, new ItemTile("GAMES"));
        bs2.setTile(3, 2, new ItemTile("FRAMES"));
        bs2.setTile(4, 2, new ItemTile("BOOKS"));
        bs2.setTile(5, 2, new ItemTile("PLANTS"));
        bs2.setTile(0, 3, new ItemTile("PLANTS"));
        bs2.setTile(1, 3, new ItemTile("PLANTS"));
        bs2.setTile(2, 3, new ItemTile("PLANTS"));
        bs2.setTile(3, 3, new ItemTile("PLANTS"));
        bs2.setTile(4, 3, new ItemTile("PLANTS"));
        bs2.setTile(5, 3, new ItemTile("PLANTS"));
        bs2.setTile(0, 4, new ItemTile("PLANTS"));
        bs2.setTile(1, 4, new ItemTile("PLANTS"));
        bs2.setTile(2, 4, new ItemTile("PLANTS"));
        bs2.setTile(3, 4, new ItemTile("PLANTS"));
        bs2.setTile(4, 4, new ItemTile("PLANTS"));
        bs2.setTile(5, 4, new ItemTile("PLANTS"));
        c9.checkGoal(bs2);
        assertEquals(0, c9.getNumCompleted());
    }
    public void testCheckGoalCardCGC5_THIRD() {
        CommonGoalCard c9 = new CommonGoalCard5(new Launcher());
        c9.initNumCompleted();
        BookShelf bs2 = new BookShelf();
        bs2.setTile(0, 0, new ItemTile("PLANTS"));
        bs2.setTile(1, 0, new ItemTile("PLANTS"));
        bs2.setTile(2, 0, new ItemTile("PLANTS"));
        bs2.setTile(3, 0, new ItemTile("CATS"));
        bs2.setTile(4, 0, new ItemTile("FRAMES"));
        bs2.setTile(5, 0, new ItemTile("TROPHIES"));
        bs2.setTile(0, 1, new ItemTile("CATS"));
        bs2.setTile(1, 1, new ItemTile("TROPHIES"));
        bs2.setTile(2, 1, new ItemTile("BOOKS"));
        bs2.setTile(3, 1, new ItemTile("GAMES"));
        bs2.setTile(4, 1, new ItemTile("GAMES"));
        bs2.setTile(5, 1, new ItemTile("GAMES"));
        bs2.setTile(0, 2, new ItemTile("TROPHIES"));
        bs2.setTile(1, 2, new ItemTile("TROPHIES"));
        bs2.setTile(2, 2, new ItemTile("GAMES"));
        bs2.setTile(3, 2, new ItemTile("FRAMES"));
        bs2.setTile(4, 2, new ItemTile("BOOKS"));
        bs2.setTile(5, 2, new ItemTile("PLANTS"));
        c9.checkGoal(bs2);
        assertEquals(0, c9.getNumCompleted());
    }
}