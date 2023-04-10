package server.model.board.goalCards;

import junit.framework.TestCase;
import server.model.board.*;
import server.model.board.goalCards.CommonGoalCard;
import server.model.board.goalCards.CommonGoalCard10;
import server.model.player.BookShelf;

/**
 * Unit test for simple App.
 */
/*public class CommonGoalCard10Test
        extends TestCase{

    public void testSameCategoryCGC10_FIRST(){  ////test for SameCategory method,in order to trace if in a bookshelf's column
                                                        //are there any tiles with the same category
        CommonGoalCard10 c24 = new CommonGoalCard10();
        c24.initNumCompleted();
        BookShelf bs17 = new BookShelf();
        bs17.setTile(0,0,new ItemTile("PLANTS"));
        bs17.setTile(0,1,new ItemTile("TROPHIES"));
        bs17.setTile(0,2,new ItemTile("GAMES"));
        bs17.setTile(0,3,new ItemTile("FRAMES"));
        bs17.setTile(0,4,new ItemTile("PLANTS"));
        c24.getCat().add(bs17.getTile(0,0).getCategory());
        assertTrue(c24.SameCategory(bs17,0,c24.getCat()));
    }

    public void testSameCategoryCGC10_SECOND(){
        CommonGoalCard10 c25 = new CommonGoalCard10();
        c25.initNumCompleted();
        BookShelf bs18 = new BookShelf();
        bs18.setTile(0,0,new ItemTile("PLANTS"));
        bs18.setTile(0,1,new ItemTile("GAMES"));
        bs18.setTile(0,2,new ItemTile("TROPHIES"));
        bs18.setTile(0,3,new ItemTile("FRAMES"));
        bs18.setTile(0,4,new ItemTile("CATS"));
        c25.getCat().add(bs18.getTile(0,0).getCategory());
        assertFalse(c25.SameCategory(bs18,0,c25.getCat()));
    }

    public void testCheckGoalCardCGC10_FIRST(){
        CommonGoalCard c26 = new CommonGoalCard10();
        c26.initNumCompleted();
        BookShelf bs19 = new BookShelf();
        bs19.setTile(0,0,new ItemTile("PLANTS"));
        bs19.setTile(0,1,new ItemTile("PLANTS"));
        bs19.setTile(0,2,new ItemTile("PLANTS"));
        bs19.setTile(0,3,new ItemTile("PLANTS"));
        bs19.setTile(0,4,new ItemTile("PLANTS"));
        bs19.setTile(1,0,new ItemTile("PLANTS"));
        bs19.setTile(1,1,new ItemTile("CATS"));
        bs19.setTile(1,2,new ItemTile("CATS"));
        bs19.setTile(1,3,new ItemTile("CATS"));
        bs19.setTile(1,4,new ItemTile("GAMES"));
        bs19.setTile(2,0,new ItemTile("GAMES"));
        bs19.setTile(2,1,new ItemTile("GAMES"));
        bs19.setTile(2,2,new ItemTile("TROPHIES"));
        bs19.setTile(2,3,new ItemTile("TROPHIES"));
        bs19.setTile(2,4,new ItemTile("FRAMES"));
        bs19.setTile(3,0,new ItemTile("FRAMES"));
        bs19.setTile(3,1,new ItemTile("BOOKS"));
        bs19.setTile(3,2,new ItemTile("BOOKS"));
        bs19.setTile(3,3,new ItemTile("BOOKS"));
        bs19.setTile(3,4,new ItemTile("GAMES"));
        bs19.setTile(4,0,new ItemTile("FRAMES"));
        bs19.setTile(4,1,new ItemTile("PLANTS"));
        bs19.setTile(4,2,new ItemTile("CATS"));
        bs19.setTile(4,3,new ItemTile("TROPHIES"));
        bs19.setTile(4,4,new ItemTile("BOOKS"));
        bs19.setTile(5,0,new ItemTile("GAMES"));
        bs19.setTile(5,1,new ItemTile("FRAMES"));
        bs19.setTile(5,2,new ItemTile("PLANTS"));
        bs19.setTile(5,3,new ItemTile("CATS"));
        bs19.setTile(5,4,new ItemTile("TROPHIES"));
        c26.checkGoal(bs19);
        assertEquals(1, c26.getNumCompleted());

    }

    public void testCheckGoalCardCGC10_SECOND() {
        CommonGoalCard c27 = new CommonGoalCard10();
        c27.initNumCompleted();
        BookShelf bs20 = new BookShelf();
        bs20.setTile(0,0,new ItemTile("PLANTS"));
        bs20.setTile(0,1,new ItemTile("PLANTS"));
        bs20.setTile(0,2,new ItemTile("PLANTS"));
        bs20.setTile(0,3,new ItemTile("FRAMES"));
        bs20.setTile(0,4,new ItemTile("CATS"));
        bs20.setTile(1,0,new ItemTile("TROPHIES"));
        bs20.setTile(1,1,new ItemTile("PLANTS"));
        bs20.setTile(1,2,new ItemTile("CATS"));
        bs20.setTile(1,3,new ItemTile("TROPHIES"));
        bs20.setTile(1,4,new ItemTile("GAMES"));
        bs20.setTile(2,0,new ItemTile("BOOKS"));
        bs20.setTile(2,1,new ItemTile("PLANTS"));
        bs20.setTile(2,2,new ItemTile("TROPHIES"));
        bs20.setTile(2,3,new ItemTile("TROPHIES"));
        bs20.setTile(2,4,new ItemTile("FRAMES"));
        bs20.setTile(3,0,new ItemTile("FRAMES"));
        bs20.setTile(3,1,new ItemTile("BOOKS"));
        bs20.setTile(3,2,new ItemTile("BOOKS"));
        bs20.setTile(3,3,new ItemTile("BOOKS"));
        bs20.setTile(3,4,new ItemTile("GAMES"));
        bs20.setTile(4,0,new ItemTile("FRAMES"));
        bs20.setTile(4,1,new ItemTile("GAMES"));
        bs20.setTile(4,2,new ItemTile("CATS"));
        bs20.setTile(4,3,new ItemTile("TROPHIES"));
        bs20.setTile(4,4,new ItemTile("BOOKS"));
        bs20.setTile(5,0,new ItemTile("GAMES"));
        bs20.setTile(5,1,new ItemTile("FRAMES"));
        bs20.setTile(5,2,new ItemTile("PLANTS"));
        bs20.setTile(5,3,new ItemTile("PLANTS"));
        bs20.setTile(5,4,new ItemTile("TROPHIES"));
        c27.checkGoal(bs20);
        assertEquals(0, c27.getNumCompleted());
    }
}
*/