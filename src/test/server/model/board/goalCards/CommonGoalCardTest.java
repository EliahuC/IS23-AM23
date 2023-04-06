package server.model;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import server.model.board.*;
import server.model.player.BookShelf;

/**
 * Unit test for simple App.
 */
public class CommonGoalCardTest
        extends TestCase {

    public void testIncreaseNumCompleted1() {
        CommonGoalCard c1 = new CommonGoalCard9();
        c1.initNumCompleted();
        c1.increaseNumCompleted();
        assertEquals(1, c1.getNumCompleted());
    }

    public void testIncreaseNumCompleted2() {
        CommonGoalCard c2 = new CommonGoalCard8();
        c2.initNumCompleted();
        c2.increaseNumCompleted();
        c2.increaseNumCompleted();
        assertEquals(2, c2.getNumCompleted());
    }

    public void testIncreaseNumCompleted3() {
        CommonGoalCard c3 = new CommonGoalCard7();
        c3.initNumCompleted();
        c3.increaseNumCompleted();
        c3.increaseNumCompleted();
        c3.increaseNumCompleted();
        assertEquals(3, c3.getNumCompleted());
    }

    public void testIncreaseNumCompleted4() {
        CommonGoalCard c4 = new CommonGoalCard8();
        c4.initNumCompleted();
        c4.increaseNumCompleted();
        c4.increaseNumCompleted();
        c4.increaseNumCompleted();
        c4.increaseNumCompleted();
        assertEquals(4, c4.getNumCompleted());
    }

    public void testIncreaseNumCompleted5() {
        CommonGoalCard c5 = new CommonGoalCard9();
        c5.initNumCompleted();
        c5.increaseNumCompleted();
        c5.increaseNumCompleted();
        c5.increaseNumCompleted();
        c5.increaseNumCompleted();
        c5.increaseNumCompleted();
        assertEquals(4, c5.getNumCompleted());
    }

}