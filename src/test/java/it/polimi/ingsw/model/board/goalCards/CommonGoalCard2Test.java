package it.polimi.ingsw.model.board.goalCards;

import junit.framework.TestCase;
import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.board.goalCards.CommonGoalCard;
import it.polimi.ingsw.model.board.goalCards.CommonGoalCard2;
import it.polimi.ingsw.model.player.BookShelf;
import it.polimi.ingsw.Launcher;

/**
 * Unit test for simple App.
 */
public class CommonGoalCard2Test
        extends TestCase
{
    public void testCheckGoalCardCGC2_FIRST() {
        CommonGoalCard c6 = new CommonGoalCard2(new Launcher());
        c6.initNumCompleted();
        BookShelf bs = new BookShelf();
        bs.setTile(0,0,new ItemTile("CATS"));
        bs.setTile(0,4,new ItemTile("CATS"));
        bs.setTile(5,0,new ItemTile("CATS"));
        bs.setTile(5,4,new ItemTile("CATS"));
        c6.checkGoal(bs);
        assertEquals(1,c6.getNumCompleted());
    }

    public void testCheckGoalCardCGC2_SECOND() {
        CommonGoalCard c7 = new CommonGoalCard2(new Launcher());
        c7.initNumCompleted();
        BookShelf bs = new BookShelf();
        bs.setTile(0,0,new ItemTile("CATS"));
        bs.setTile(0,4,new ItemTile("CATS"));
        bs.setTile(5,0,new ItemTile("CATS"));
        bs.setTile(5,4,new ItemTile("TROPHIES"));
        c7.checkGoal(bs);
        assertEquals(0, c7.getNumCompleted());
    }

}
