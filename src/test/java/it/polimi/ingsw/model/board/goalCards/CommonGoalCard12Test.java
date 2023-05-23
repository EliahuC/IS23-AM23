package it.polimi.ingsw.model.board.goalCards;

import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.player.BookShelf;
import junit.framework.TestCase;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CommonGoalCard12Test extends TestCase {

    public void testcheckRtoL(){
        CommonGoalCard c12=new CommonGoalCard12(2);
        c12.initNumCompleted();
        BookShelf bs= new BookShelf();
        bs.setTile(5,0,new ItemTile());
        bs.setTile(5,1,new ItemTile());
        bs.setTile(5,2,new ItemTile());
        bs.setTile(5,3,new ItemTile());
        bs.setTile(5,4,new ItemTile());
        bs.setTile(4,1,new ItemTile());
        bs.setTile(4,2,new ItemTile());
        bs.setTile(4,3,new ItemTile());
        bs.setTile(4,4,new ItemTile());
        bs.setTile(3,2,new ItemTile());
        bs.setTile(3,3,new ItemTile());
        bs.setTile(3,4,new ItemTile());
        bs.setTile(2,3,new ItemTile());
        bs.setTile(2,4,new ItemTile());
        bs.setTile(1,4,new ItemTile());
     c12.checkGoal(bs);
     assertEquals(1,c12.getNumCompleted());

    }

    public void testcheckLtoR(){
        CommonGoalCard c12=new CommonGoalCard12(2);
        c12.initNumCompleted();
        BookShelf bs= new BookShelf();
        bs.setTile(5,4,new ItemTile());
        bs.setTile(5,3,new ItemTile());
        bs.setTile(5,2,new ItemTile());
        bs.setTile(5,1,new ItemTile());
        bs.setTile(5,0,new ItemTile());
        bs.setTile(4,3,new ItemTile());
        bs.setTile(4,2,new ItemTile());
        bs.setTile(4,1,new ItemTile());
        bs.setTile(4,0,new ItemTile());
        bs.setTile(3,2,new ItemTile());
        bs.setTile(3,1,new ItemTile());
        bs.setTile(3,0,new ItemTile());
        bs.setTile(2,1,new ItemTile());
        bs.setTile(2,0,new ItemTile());
        bs.setTile(1,0,new ItemTile());
        c12.checkGoal(bs);
        assertEquals(1,c12.getNumCompleted());
    }

    public void testFullShelf(){
        CommonGoalCard c12=new CommonGoalCard12(2);
        c12.initNumCompleted();
        BookShelf bs= new BookShelf();
        for(int i=0; i<BookShelf.getMAX_Row();i++){
            for(int j=0;j<BookShelf.getMAX_Column();j++){
                bs.setTile(i,j,new ItemTile());
            }
        }
        c12.checkGoal(bs);
        assertNotEquals(1,c12.getNumCompleted());
    }
    public void testCheckGoalCardCGC12() {
        CommonGoalCard c12 = new CommonGoalCard12(2);
        c12.initNumCompleted();
        BookShelf bs = new BookShelf();
        bs.setTile(5, 4, new ItemTile());
        bs.setTile(5, 3, new ItemTile());
        bs.setTile(5, 2, new ItemTile());
        bs.setTile(5, 1, new ItemTile());
        bs.setTile(5, 0, new ItemTile());
        bs.setTile(4, 4, new ItemTile());
        bs.setTile(4, 3, new ItemTile());
        bs.setTile(4, 2, new ItemTile());
        bs.setTile(4, 1, new ItemTile());
        bs.setTile(4, 0, new ItemTile());
        bs.setTile(3, 3, new ItemTile());
        bs.setTile(3, 2, new ItemTile());
        bs.setTile(3, 1, new ItemTile());
        bs.setTile(3, 0, new ItemTile());
        bs.setTile(2, 1, new ItemTile());
        bs.setTile(2, 0, new ItemTile());
        bs.setTile(1, 1, new ItemTile());
        bs.setTile(1, 0, new ItemTile());
        bs.setTile(0, 0, new ItemTile());
        c12.checkGoal(bs);
        assertEquals(0, c12.getNumCompleted());
    }

    public void testCheckGoalCardCGC12_SECOND() {
        CommonGoalCard c12 = new CommonGoalCard12(2);
        c12.initNumCompleted();
        BookShelf bs = new BookShelf();
        bs.setTile(5, 4, new ItemTile());
        bs.setTile(5, 3, new ItemTile());
        bs.setTile(5, 2, new ItemTile());
        bs.setTile(5, 1, new ItemTile());
        bs.setTile(5, 0, new ItemTile());
        bs.setTile(4, 4, new ItemTile());
        bs.setTile(4, 3, new ItemTile());
        bs.setTile(4, 2, new ItemTile());
        bs.setTile(4, 1, new ItemTile());
        bs.setTile(4, 0, new ItemTile());
        bs.setTile(3, 3, new ItemTile());
        bs.setTile(3, 2, new ItemTile());
        bs.setTile(3, 1, new ItemTile());
        bs.setTile(3, 0, new ItemTile());
        bs.setTile(2, 2, new ItemTile());
        bs.setTile(2, 1, new ItemTile());
        bs.setTile(2, 0, new ItemTile());
        bs.setTile(1, 1, new ItemTile());
        bs.setTile(1, 0, new ItemTile());
        bs.setTile(0, 0, new ItemTile());
        c12.checkGoal(bs);
        assertEquals(1, c12.getNumCompleted());
    }

    public void testCheckGoalCardCGC12_THIRD() {
        CommonGoalCard c12 = new CommonGoalCard12(2);
        c12.initNumCompleted();
        BookShelf bs = new BookShelf();
        bs.setTile(5, 0, new ItemTile());
        bs.setTile(4, 0, new ItemTile());
        bs.setTile(3, 0, new ItemTile());
        bs.setTile(2, 0, new ItemTile());
        bs.setTile(1, 0, new ItemTile());
        bs.setTile(0, 0, new ItemTile());
        bs.setTile(5, 1, new ItemTile());
        bs.setTile(5, 2, new ItemTile());
        bs.setTile(5, 3, new ItemTile());
        bs.setTile(5, 4, new ItemTile());
        c12.checkGoal(bs);
        assertEquals(0, c12.getNumCompleted());
    }
}