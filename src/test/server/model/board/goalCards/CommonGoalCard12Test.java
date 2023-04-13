package server.model.board.goalCards;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import server.Launcher;
import server.model.board.ItemTile;
import server.model.player.BookShelf;

import static org.junit.jupiter.api.Assertions.*;

public class CommonGoalCard12Test extends TestCase {

    public void testcheckRtoL(){
        CommonGoalCard c12=new CommonGoalCard12(new Launcher());
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
        CommonGoalCard c12=new CommonGoalCard12(new Launcher());
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
        CommonGoalCard c12=new CommonGoalCard12(new Launcher());
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

}