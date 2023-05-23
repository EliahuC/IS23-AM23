package it.polimi.ingsw.model.board.goalCards;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class CommonGoalCardTest
        extends TestCase {

    public void testIncreaseNumCompleted1() {
        CommonGoalCard c1 = new CommonGoalCard9(2);
        c1.initNumCompleted();
        c1.increaseNumCompleted();
        assertEquals(1, c1.getNumCompleted());
    }

    public void testIncreaseNumCompleted2() {
        CommonGoalCard c2 = new CommonGoalCard8(2);
        c2.initNumCompleted();
        c2.increaseNumCompleted();
        c2.increaseNumCompleted();
        assertEquals(2, c2.getNumCompleted());
    }

    public void testIncreaseNumCompleted3() {
        CommonGoalCard c3 = new CommonGoalCard7(3);
        c3.initNumCompleted();
        c3.increaseNumCompleted();
        c3.increaseNumCompleted();
        c3.increaseNumCompleted();
        assertEquals(3, c3.getNumCompleted());
    }

    public void testIncreaseNumCompleted4() {
        CommonGoalCard c4 = new CommonGoalCard8(4);
        c4.initNumCompleted();
        c4.increaseNumCompleted();
        c4.increaseNumCompleted();
        c4.increaseNumCompleted();
        c4.increaseNumCompleted();
        assertEquals(4, c4.getNumCompleted());
    }

    public void testIncreaseNumCompleted5() {
        CommonGoalCard c5 = new CommonGoalCard9(4);
        c5.initNumCompleted();
        c5.increaseNumCompleted();
        c5.increaseNumCompleted();
        c5.increaseNumCompleted();
        c5.increaseNumCompleted();
        c5.increaseNumCompleted();
        assertEquals(4, c5.getNumCompleted());
    }

    public void testGetPoints1(){
        CommonGoalCard c6 = new CommonGoalCard9(2);
        c6.initNumCompleted();
        c6.increaseNumCompleted();
        assertEquals(8,c6.getPoints());
    }
    public void testGetPoints2(){
        CommonGoalCard c7 = new CommonGoalCard9(2);
        c7.initNumCompleted();
        c7.increaseNumCompleted();
        c7.increaseNumCompleted();
        assertEquals(4,c7.getPoints());
    }

    public void testGetPoints3(){
        CommonGoalCard c8 = new CommonGoalCard9(3);
        c8.initNumCompleted();
        c8.increaseNumCompleted();
        assertEquals(8,c8.getPoints());
    }

    public void testGetPoints4(){
        CommonGoalCard c9 = new CommonGoalCard9(3);
        c9.initNumCompleted();
        c9.increaseNumCompleted();
        c9.increaseNumCompleted();
        assertEquals(6,c9.getPoints());
    }

    public void testGetPoints5(){
        CommonGoalCard c10 = new CommonGoalCard9(3);
        c10.initNumCompleted();
        c10.increaseNumCompleted();
        c10.increaseNumCompleted();
        c10.increaseNumCompleted();
        assertEquals(4,c10.getPoints());
    }

    public void testGetPoints6(){
        CommonGoalCard c11 = new CommonGoalCard9(4);
        c11.initNumCompleted();
        c11.increaseNumCompleted();
        assertEquals(8,c11.getPoints());
    }

    public void testGetPoints7(){
        CommonGoalCard c12 = new CommonGoalCard9(4);
        c12.initNumCompleted();
        c12.increaseNumCompleted();
        c12.increaseNumCompleted();
        assertEquals(6,c12.getPoints());
    }

    public void testGetPoints8(){
        CommonGoalCard c13 = new CommonGoalCard9(4);
        c13.initNumCompleted();
        c13.increaseNumCompleted();
        c13.increaseNumCompleted();
        c13.increaseNumCompleted();
        assertEquals(4,c13.getPoints());
    }

    public void testGetPoints9(){
        CommonGoalCard c14 = new CommonGoalCard9(4);
        c14.initNumCompleted();
        c14.increaseNumCompleted();
        c14.increaseNumCompleted();
        c14.increaseNumCompleted();
        c14.increaseNumCompleted();
        assertEquals(2,c14.getPoints());
    }
}