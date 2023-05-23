package it.polimi.ingsw.model.board.goalCards;

import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.player.BookShelf;
import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class CommonGoalCard9Test
        extends TestCase{

    public void testSameCategoryCGC9_FIRST(){   //test for SameCategory method,in order to trace if in a bookshelf's column
                                                    //are there any tiles with the same category
        CommonGoalCard9 c20 = new CommonGoalCard9(2);
        c20.initNumCompleted();
        BookShelf bs13 = new BookShelf();
        bs13.setTile(0,0,new ItemTile("PLANTS"));
        bs13.setTile(1,0,new ItemTile("BOOKS"));
        bs13.setTile(2,0,new ItemTile("GAMES"));
        bs13.setTile(3,0,new ItemTile("FRAMES"));
        bs13.setTile(4,0,new ItemTile("CATS"));
        bs13.setTile(5,0,new ItemTile("PLANTS"));
        c20.getCat().add(bs13.getTile(0,0).getCategory());
        assertTrue(c20.SameCategory(bs13,0,c20.getCat()));
    }

    public void testSameCategoryCGC9_SECOND(){
        CommonGoalCard9 c21 = new CommonGoalCard9(2);
        c21.initNumCompleted();
        BookShelf bs14 = new BookShelf();
        bs14.setTile(0,0,new ItemTile("PLANTS"));
        bs14.setTile(1,0,new ItemTile("GAMES"));
        bs14.setTile(2,0,new ItemTile("TROPHIES"));
        bs14.setTile(3,0,new ItemTile("FRAMES"));
        bs14.setTile(4,0,new ItemTile("CATS"));
        bs14.setTile(5,0,new ItemTile("BOOKS"));
        c21.getCat().add(bs14.getShelf()[0][0].getCategory());
        assertFalse(c21.SameCategory(bs14,0,c21.getCat()));
    }

    public void testCheckGoalCardCGC9_FIRST(){
        CommonGoalCard c22 = new CommonGoalCard9(2);
        c22.initNumCompleted();
        BookShelf bs15 = new BookShelf();
        bs15.setTile(0,0,new ItemTile("PLANTS"));
        bs15.setTile(1,0,new ItemTile("PLANTS"));
        bs15.setTile(2,0,new ItemTile("PLANTS"));
        bs15.setTile(3,0,new ItemTile("PLANTS"));
        bs15.setTile(4,0,new ItemTile("PLANTS"));
        bs15.setTile(5,0,new ItemTile("PLANTS"));
        bs15.setTile(0,1,new ItemTile("CATS"));
        bs15.setTile(1,1,new ItemTile("CATS"));
        bs15.setTile(2,1,new ItemTile("CATS"));
        bs15.setTile(3,1,new ItemTile("GAMES"));
        bs15.setTile(4,1,new ItemTile("GAMES"));
        bs15.setTile(5,1,new ItemTile("GAMES"));
        bs15.setTile(0,2,new ItemTile("TROPHIES"));
        bs15.setTile(1,2,new ItemTile("TROPHIES"));
        bs15.setTile(2,2,new ItemTile("FRAMES"));
        bs15.setTile(3,2,new ItemTile("FRAMES"));
        bs15.setTile(4,2,new ItemTile("BOOKS"));
        bs15.setTile(5,2,new ItemTile("BOOKS"));
        bs15.setTile(0,3,new ItemTile("BOOKS"));
        bs15.setTile(1,3,new ItemTile("GAMES"));
        bs15.setTile(2,3,new ItemTile("FRAMES"));
        bs15.setTile(3,3,new ItemTile("PLANTS"));
        bs15.setTile(4,3,new ItemTile("CATS"));
        bs15.setTile(5,3,new ItemTile("TROPHIES"));
        bs15.setTile(0,4,new ItemTile("BOOKS"));
        bs15.setTile(1,4,new ItemTile("GAMES"));
        bs15.setTile(2,4,new ItemTile("FRAMES"));
        bs15.setTile(3,4,new ItemTile("PLANTS"));
        bs15.setTile(4,4,new ItemTile("CATS"));
        bs15.setTile(5,4,new ItemTile("TROPHIES"));
        c22.checkGoal(bs15);
        assertEquals(1, c22.getNumCompleted());
    }


    public void testCheckGoalCardCGC9_SECOND() {
        CommonGoalCard c23 = new CommonGoalCard9(2);
        c23.initNumCompleted();
        BookShelf bs16 = new BookShelf();
        bs16.setTile(0,0,new ItemTile("PLANTS"));
        bs16.setTile(1,0,new ItemTile("PLANTS"));
        bs16.setTile(2,0,new ItemTile("PLANTS"));
        bs16.setTile(3,0,new ItemTile("FRAMES"));
        bs16.setTile(4,0,new ItemTile("CATS"));
        bs16.setTile(5,0,new ItemTile("TROPHIES"));
        bs16.setTile(0,1,new ItemTile("PLANTS"));
        bs16.setTile(1,1,new ItemTile("CATS"));
        bs16.setTile(2,1,new ItemTile("TROPHIES"));
        bs16.setTile(3,1,new ItemTile("GAMES"));
        bs16.setTile(4,1,new ItemTile("BOOKS"));
        bs16.setTile(5,1,new ItemTile("PLANTS"));
        bs16.setTile(0,2,new ItemTile("TROPHIES"));
        bs16.setTile(1,2,new ItemTile("TROPHIES"));
        bs16.setTile(2,2,new ItemTile("FRAMES"));
        bs16.setTile(3,2,new ItemTile("FRAMES"));
        bs16.setTile(4,2,new ItemTile("BOOKS"));
        bs16.setTile(5,2,new ItemTile("BOOKS"));
        bs16.setTile(0,3,new ItemTile("BOOKS"));
        bs16.setTile(1,3,new ItemTile("GAMES"));
        bs16.setTile(2,3,new ItemTile("FRAMES"));
        bs16.setTile(3,3,new ItemTile("GAMES"));
        bs16.setTile(4,3,new ItemTile("CATS"));
        bs16.setTile(5,3,new ItemTile("TROPHIES"));
        bs16.setTile(0,4,new ItemTile("BOOKS"));
        bs16.setTile(1,4,new ItemTile("GAMES"));
        bs16.setTile(2,4,new ItemTile("FRAMES"));
        bs16.setTile(3,4,new ItemTile("PLANTS"));
        bs16.setTile(4,4,new ItemTile("PLANTS"));
        bs16.setTile(5,4,new ItemTile("TROPHIES"));
        c23.checkGoal(bs16);
        assertEquals(0, c23.getNumCompleted());
    }
    public void testCheckGoalCardCGC9_THIRD() {
        CommonGoalCard c23 = new CommonGoalCard9(2);
        c23.initNumCompleted();
        BookShelf bs15 = new BookShelf();
        bs15.setTile(0, 4, new ItemTile("BOOKS"));
        bs15.setTile(1, 4, new ItemTile("GAMES"));
        bs15.setTile(2, 4, new ItemTile("FRAMES"));
        bs15.setTile(3, 4, new ItemTile("PLANTS"));
        bs15.setTile(4, 4, new ItemTile("CATS"));
        bs15.setTile(5, 4, new ItemTile("TROPHIES"));
        c23.checkGoal(bs15);
        assertEquals(0, c23.getNumCompleted());
    }
}
