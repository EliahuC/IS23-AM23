package it.polimi.ingsw.model.board.goalCards;


import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.player.BookShelf;
import junit.framework.TestCase;



public class CommonGoalCard1Test extends TestCase {

    public void testCheckGoalCardCGC1_FIRST(){
        CommonGoalCard c41 = new CommonGoalCard1(2);
        c41.initNumCompleted();
        BookShelf bs27 = new BookShelf();
        bs27.setTile(0, 0, new ItemTile("PLANTS"));
        bs27.setTile(1, 0, new ItemTile("PLANTS"));
        bs27.setTile(2, 0, new ItemTile("FRAMES"));
        bs27.setTile(3, 0, new ItemTile("BOOKS"));
        bs27.setTile(4, 0, new ItemTile("GAMES"));
        bs27.setTile(5, 0, new ItemTile("CATS"));
        bs27.setTile(0, 1, new ItemTile("PLANTS"));
        bs27.setTile(1, 1, new ItemTile("PLANTS"));
        bs27.setTile(2, 1, new ItemTile("BOOKS"));
        bs27.setTile(3, 1, new ItemTile("FRAMES"));
        bs27.setTile(4, 1, new ItemTile("GAMES"));
        bs27.setTile(5, 1, new ItemTile("CATS"));
        bs27.setTile(0, 2, new ItemTile("PLANTS"));
        bs27.setTile(1, 2, new ItemTile("PLANTS"));
        bs27.setTile(2, 2, new ItemTile("FRAMES"));
        bs27.setTile(3, 2, new ItemTile("CATS"));
        bs27.setTile(4, 2, new ItemTile("TROPHIES"));
        bs27.setTile(5, 2, new ItemTile("BOOKS"));
        bs27.setTile(0, 3, new ItemTile("BOOKS"));
        bs27.setTile(1, 3, new ItemTile("TROPHIES"));
        bs27.setTile(2, 3, new ItemTile("CATS"));
        bs27.setTile(3, 3, new ItemTile("FRAMES"));
        bs27.setTile(4, 3, new ItemTile("GAMES"));
        bs27.setTile(5, 3, new ItemTile("PLANTS"));
        bs27.setTile(0, 4, new ItemTile("BOOKS"));
        bs27.setTile(1, 4, new ItemTile("FRAMES"));
        bs27.setTile(2, 4, new ItemTile("TROPHIES"));
        bs27.setTile(3, 4, new ItemTile("GAMES"));
        bs27.setTile(4, 4, new ItemTile("CATS"));
        bs27.setTile(5, 4, new ItemTile("PLANTS"));
        c41.checkGoal(bs27);
        c41.print();
        assertEquals(0, c41.getNumCompleted());
    }
    public void testCheckGoalCardCGC1_SECOND(){
        CommonGoalCard c42 = new CommonGoalCard1(2);
        c42.initNumCompleted();
        BookShelf bs28 = new BookShelf();
        bs28.setTile(0, 0, new ItemTile("PLANTS"));
        bs28.setTile(1, 0, new ItemTile("PLANTS"));
        bs28.setTile(2, 0, new ItemTile("FRAMES"));
        bs28.setTile(3, 0, new ItemTile("BOOKS"));
        bs28.setTile(4, 0, new ItemTile("GAMES"));
        bs28.setTile(5, 0, new ItemTile("CATS"));
        bs28.setTile(0, 1, new ItemTile("FRAMES"));
        bs28.setTile(1, 1, new ItemTile("GAMES"));
        bs28.setTile(2, 1, new ItemTile("BOOKS"));
        bs28.setTile(3, 1, new ItemTile("FRAMES"));
        bs28.setTile(4, 1, new ItemTile("GAMES"));
        bs28.setTile(5, 1, new ItemTile("CATS"));
        bs28.setTile(0, 2, new ItemTile("PLANTS"));
        bs28.setTile(1, 2, new ItemTile("PLANTS"));
        bs28.setTile(2, 2, new ItemTile("FRAMES"));
        bs28.setTile(3, 2, new ItemTile("CATS"));
        bs28.setTile(4, 2, new ItemTile("TROPHIES"));
        bs28.setTile(5, 2, new ItemTile("BOOKS"));
        bs28.setTile(0, 3, new ItemTile("BOOKS"));
        bs28.setTile(1, 3, new ItemTile("TROPHIES"));
        bs28.setTile(2, 3, new ItemTile("CATS"));
        bs28.setTile(3, 3, new ItemTile("FRAMES"));
        bs28.setTile(4, 3, new ItemTile("GAMES"));
        bs28.setTile(5, 3, new ItemTile("PLANTS"));
        bs28.setTile(0, 4, new ItemTile("BOOKS"));
        bs28.setTile(1, 4, new ItemTile("FRAMES"));
        bs28.setTile(2, 4, new ItemTile("TROPHIES"));
        bs28.setTile(3, 4, new ItemTile("GAMES"));
        bs28.setTile(4, 4, new ItemTile("CATS"));
        bs28.setTile(5, 4, new ItemTile("PLANTS"));
        c42.checkGoal(bs28);
        assertEquals(1, c42.getNumCompleted());
    }
    public void testCheckGoalCardCGC1_THIRD(){
        CommonGoalCard c43 = new CommonGoalCard1(2);
        c43.initNumCompleted();
        BookShelf bs29 = new BookShelf();
        bs29.setTile(0, 0, new ItemTile("BOOKS"));
        bs29.setTile(1, 0, new ItemTile("FRAMES"));
        bs29.setTile(2, 0, new ItemTile("FRAMES"));
        bs29.setTile(3, 0, new ItemTile("BOOKS"));
        bs29.setTile(4, 0, new ItemTile("GAMES"));
        bs29.setTile(5, 0, new ItemTile("CATS"));
        bs29.setTile(0, 1, new ItemTile("FRAMES"));
        bs29.setTile(1, 1, new ItemTile("GAMES"));
        bs29.setTile(2, 1, new ItemTile("BOOKS"));
        bs29.setTile(3, 1, new ItemTile("FRAMES"));
        bs29.setTile(4, 1, new ItemTile("GAMES"));
        bs29.setTile(5, 1, new ItemTile("CATS"));
        bs29.setTile(0, 2, new ItemTile("PLANTS"));
        bs29.setTile(1, 2, new ItemTile("PLANTS"));
        bs29.setTile(2, 2, new ItemTile("FRAMES"));
        bs29.setTile(3, 2, new ItemTile("CATS"));
        bs29.setTile(4, 2, new ItemTile("TROPHIES"));
        bs29.setTile(5, 2, new ItemTile("BOOKS"));
        bs29.setTile(0, 3, new ItemTile("BOOKS"));
        bs29.setTile(1, 3, new ItemTile("TROPHIES"));
        bs29.setTile(2, 3, new ItemTile("FRAMES"));
        bs29.setTile(3, 3, new ItemTile("CATS"));
        bs29.setTile(4, 3, new ItemTile("GAMES"));
        bs29.setTile(5, 3, new ItemTile("BOOKS"));
        bs29.setTile(0, 4, new ItemTile("BOOKS"));
        bs29.setTile(1, 4, new ItemTile("FRAMES"));
        bs29.setTile(2, 4, new ItemTile("TROPHIES"));
        bs29.setTile(3, 4, new ItemTile("GAMES"));
        bs29.setTile(4, 4, new ItemTile("CATS"));
        bs29.setTile(5, 4, new ItemTile("BOOKS"));
        c43.checkGoal(bs29);
        assertEquals(1, c43.getNumCompleted());
    }
    public void testCheckGoalCardCGC1_FOURTH() {
        CommonGoalCard c43 = new CommonGoalCard1(2);
        c43.initNumCompleted();
        BookShelf bs29 = new BookShelf();
        bs29.setTile(0, 0, new ItemTile("BOOKS"));
        bs29.setTile(1, 0, new ItemTile("BOOKS"));
        bs29.setTile(2, 1, new ItemTile("BOOKS"));
        bs29.setTile(3, 1, new ItemTile("BOOKS"));
        bs29.setTile(2, 3, new ItemTile("BOOKS"));
        bs29.setTile(3, 3, new ItemTile("BOOKS"));
        bs29.setTile(4, 0, new ItemTile("BOOKS"));
        bs29.setTile(5, 0, new ItemTile("BOOKS"));
        bs29.setTile(4, 4, new ItemTile("BOOKS"));
        bs29.setTile(5, 4, new ItemTile("BOOKS"));
        bs29.setTile(0, 4, new ItemTile("BOOKS"));
        bs29.setTile(1, 4, new ItemTile("BOOKS"));
        bs29.setTile(4, 1, new ItemTile("TROPHIES"));
        bs29.setTile(5, 1, new ItemTile("TROPHIES"));
        bs29.setTile(4, 3, new ItemTile("TROPHIES"));
        bs29.setTile(5, 3, new ItemTile("TROPHIES"));
        c43.checkGoal(bs29);
        assertEquals(1, c43.getNumCompleted());
    }
    public void testCheckGoalCardCGC1_FIFTH() {
        CommonGoalCard c43 = new CommonGoalCard1(2);
        c43.initNumCompleted();
        BookShelf bs29 = new BookShelf();
        bs29.setTile(0, 0, new ItemTile("BOOKS"));
        bs29.setTile(1, 0, new ItemTile("BOOKS"));
        bs29.setTile(2, 1, new ItemTile("BOOKS"));
        bs29.setTile(3, 1, new ItemTile("BOOKS"));
        bs29.setTile(2, 3, new ItemTile("BOOKS"));
        bs29.setTile(3, 3, new ItemTile("BOOKS"));
        bs29.setTile(4, 0, new ItemTile("BOOKS"));
        bs29.setTile(5, 0, new ItemTile("BOOKS"));
        bs29.setTile(4, 4, new ItemTile("BOOKS"));
        bs29.setTile(5, 4, new ItemTile("BOOKS"));
        bs29.setTile(0, 4, new ItemTile("BOOKS"));
        bs29.setTile(1, 4, new ItemTile("BOOKS"));
        bs29.setTile(4, 1, new ItemTile("BOOKS"));
        bs29.setTile(5, 1, new ItemTile("BOOKS"));
        bs29.setTile(4, 3, new ItemTile("TROPHIES"));
        bs29.setTile(5, 3, new ItemTile("TROPHIES"));
        c43.checkGoal(bs29);
        assertEquals(0, c43.getNumCompleted());
    }
}