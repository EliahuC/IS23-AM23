package it.polimi.ingsw.model.board.goalCards;
import junit.framework.TestCase;
import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.model.board.goalCards.CommonGoalCard;
import it.polimi.ingsw.model.board.goalCards.CommonGoalCard8;
import it.polimi.ingsw.model.player.BookShelf;

/**
 * Unit test for simple App.
 */
public class CommonGoalCard8Test
        extends TestCase{

    public void testCategoriesAllowedCGC8_FIRST() { //testing of CategoriesAllowed method, if Hashset cat
                                                    //counts correctly how many categories are in a column of a bookshelf
        CommonGoalCard8 c14 = new CommonGoalCard8(new Launcher());
        c14.initNumCompleted();
        BookShelf bs7 = new BookShelf();
        bs7.setTile(0,0,new ItemTile("PLANTS"));
        bs7.setTile(0,1,new ItemTile("PLANTS"));
        bs7.setTile(0,2,new ItemTile("PLANTS"));
        bs7.setTile(0,3,new ItemTile("PLANTS"));
        bs7.setTile(0,4,new ItemTile("PLANTS"));
        c14.getCat().add(bs7.getTile(0,0).getCategory());
        assertEquals(1,c14.CategoriesAllowed(bs7,0,c14.getCat()));

    }

    public void testCategoriesAllowedCGC8_SECOND() {
        CommonGoalCard8 c15 = new CommonGoalCard8(new Launcher());
        c15.initNumCompleted();
        BookShelf bs8 = new BookShelf();
        bs8.setTile(1,0,new ItemTile("CATS"));
        bs8.setTile(1,1,new ItemTile("CATS"));
        bs8.setTile(1,2,new ItemTile("GAMES"));
        bs8.setTile(1,3,new ItemTile("GAMES"));
        bs8.setTile(1,4,new ItemTile("GAMES"));
        c15.getCat().add(bs8.getTile(1,0).getCategory());
        assertEquals(2,c15.CategoriesAllowed(bs8,1,c15.getCat()));
    }

    public void testCategoriesAllowedCGC8_THIRD() {
        CommonGoalCard8 c16 = new CommonGoalCard8(new Launcher());
        c16.initNumCompleted();
        BookShelf bs9 = new BookShelf();
        bs9.setTile(2,0,new ItemTile("TROPHIES"));
        bs9.setTile(2,1,new ItemTile("FRAMES"));
        bs9.setTile(2,2,new ItemTile("FRAMES"));
        bs9.setTile(2,3,new ItemTile("BOOKS"));
        bs9.setTile(2,4,new ItemTile("TROPHIES"));
        c16.getCat().add(bs9.getTile(2,0).getCategory());
        assertEquals(3,c16.CategoriesAllowed(bs9,2,c16.getCat()));
    }

    public void testCategoriesAllowedCGC8_FOURTH() {
        CommonGoalCard8 c17 = new CommonGoalCard8(new Launcher());
        c17.initNumCompleted();
        BookShelf bs10 = new BookShelf();
        bs10.setTile(0,0,new ItemTile("PLANTS"));
        bs10.setTile(0,1,new ItemTile("PLANTS"));
        bs10.setTile(0,2,new ItemTile("GAMES"));
        bs10.setTile(0,3,new ItemTile("CATS"));
        bs10.setTile(0,4,new ItemTile("FRAMES"));
        c17.getCat().add(bs10.getTile(0,0).getCategory());
        assertEquals(4,c17.CategoriesAllowed(bs10,0,c17.getCat()));
    }


    public void testCheckGoalCardCGC8_FIRST(){ //testing the functionality of checkGoalCard method, if a Player achieves
        CommonGoalCard c18 = new CommonGoalCard8(new Launcher());//the goal of the CGC, the attribute "NumCompleted" will increment,
        c18.initNumCompleted();                     //if not, as in the next test, "NumCompleted" will remain 0
        BookShelf bs11 = new BookShelf();
        bs11.setTile(0,0,new ItemTile("PLANTS"));
        bs11.setTile(0,1,new ItemTile("PLANTS"));
        bs11.setTile(0,2,new ItemTile("PLANTS"));
        bs11.setTile(0,3,new ItemTile("PLANTS"));
        bs11.setTile(0,4,new ItemTile("PLANTS"));
        bs11.setTile(1,0,new ItemTile("CATS"));
        bs11.setTile(1,1,new ItemTile("CATS"));
        bs11.setTile(1,2,new ItemTile("GAMES"));
        bs11.setTile(1,3,new ItemTile("GAMES"));
        bs11.setTile(1,4,new ItemTile("GAMES"));
        bs11.setTile(2,0,new ItemTile("TROPHIES"));
        bs11.setTile(2,1,new ItemTile("FRAMES"));
        bs11.setTile(2,2,new ItemTile("FRAMES"));
        bs11.setTile(2,3,new ItemTile("BOOKS"));
        bs11.setTile(2,4,new ItemTile("TROPHIES"));
        bs11.setTile(3,0,new ItemTile("TROPHIES"));
        bs11.setTile(3,1,new ItemTile("FRAMES"));
        bs11.setTile(3,2,new ItemTile("FRAMES"));
        bs11.setTile(3,3,new ItemTile("BOOKS"));
        bs11.setTile(3,4,new ItemTile("TROPHIES"));
        c18.checkGoal(bs11);
        assertEquals(1, c18.getNumCompleted());
    }

    public void testCheckGoalCardCGC8_SECOND(){
        CommonGoalCard c19 = new CommonGoalCard8(new Launcher());
        c19.initNumCompleted();
        BookShelf bs12 = new BookShelf();
        bs12.setTile(0,0,new ItemTile("PLANTS"));
        bs12.setTile(0,1,new ItemTile("PLANTS"));
        bs12.setTile(0,2,new ItemTile("GAMES"));
        bs12.setTile(0,3,new ItemTile("CATS"));
        bs12.setTile(0,4,new ItemTile("FRAMES"));
        bs12.setTile(1,0,new ItemTile("TROPHIES"));
        bs12.setTile(1,1,new ItemTile("CATS"));
        bs12.setTile(1,2,new ItemTile("PLANTS"));
        bs12.setTile(1,3,new ItemTile("BOOKS"));
        bs12.setTile(1,4,new ItemTile("GAMES"));
        bs12.setTile(2,0,new ItemTile("GAMES"));
        bs12.setTile(2,1,new ItemTile("GAMES"));
        bs12.setTile(2,2,new ItemTile("TROPHIES"));
        bs12.setTile(2,3,new ItemTile("PLANTS"));
        bs12.setTile(2,4,new ItemTile("CATS"));
        bs12.setTile(3,0,new ItemTile("FRAMES"));
        bs12.setTile(3,1,new ItemTile("BOOKS"));
        bs12.setTile(3,2,new ItemTile("PLANTS"));
        bs12.setTile(3,3,new ItemTile("PLANTS"));
        bs12.setTile(3,4,new ItemTile("PLANTS"));
        bs12.setTile(4,0,new ItemTile("PLANTS"));
        bs12.setTile(4,1,new ItemTile("PLANTS"));
        bs12.setTile(4,2,new ItemTile("PLANTS"));
        bs12.setTile(4,3,new ItemTile("PLANTS"));
        bs12.setTile(4,4,new ItemTile("PLANTS"));
        bs12.setTile(5,0,new ItemTile("PLANTS"));
        bs12.setTile(5,1,new ItemTile("PLANTS"));
        bs12.setTile(5,2,new ItemTile("PLANTS"));
        bs12.setTile(5,3,new ItemTile("PLANTS"));
        bs12.setTile(5,4,new ItemTile("PLANTS"));
        c19.checkGoal(bs12);
        assertEquals(0, c19.getNumCompleted());
    }

}

