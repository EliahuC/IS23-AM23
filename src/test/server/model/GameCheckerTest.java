package server.model;

import junit.framework.TestCase;
import server.Launcher;
import server.model.board.Bag;
import server.model.board.ItemTile;
import server.model.board.LivingRoom;
import server.model.player.BookShelf;

public class GameCheckerTest extends TestCase {

    public void testIsRestorable() {
        Launcher l = new Launcher();
        l.setNumPlayers(4);
        GameChecker gc = new GameChecker(l);
        Bag bag = new Bag();
        LivingRoom board = new LivingRoom(l);
        //At the moment, there's only one tile on the board. So the board must be restored!
        board.putTile(4,4);
        assertTrue(gc.isRestorable(board.getBoard()));
        //Now, there are two adjacent tiles on the board. Restoring is not necessary anymore.
        board.putTile(4,5);
        assertFalse(gc.isRestorable(board.getBoard()));
    }

    public void testCheckColumnCapability() {
        Launcher l = new Launcher();
        l.setNumPlayers(4);
        GameChecker gc = new GameChecker(l);
        BookShelf bs = new BookShelf();
        ItemTile t = new ItemTile("CATS");
        //The bookshelf has the first three rows full.
        for(int i=5; i>2; i--){
            for(int j=0; j<5; j++)
                bs.setTile(i,j,t);
        }
        gc.checkColumnCapability(bs);
        assertTrue(bs.getMaxPickableTiles()[0]==3);
        assertTrue(bs.getMaxPickableTiles()[1]==3);
        assertTrue(bs.getMaxPickableTiles()[2]==3);
        assertTrue(bs.getMaxPickableTiles()[3]==3);
        assertTrue(bs.getMaxPickableTiles()[4]==3);
        assertTrue(gc.getMaxPickableTiles(bs)==3);
        gc.isBookShelfFull(bs);
        assertFalse(gc.getLastRound());
        //Adding one tile per time to the fourth row of the bookshelf.
        bs.setTile(2,0,t);
        gc.checkColumnCapability(bs);
        assertTrue(bs.getMaxPickableTiles()[0]==2);
        assertTrue(bs.getMaxPickableTiles()[1]==3);
        assertTrue(bs.getMaxPickableTiles()[2]==3);
        assertTrue(bs.getMaxPickableTiles()[3]==3);
        assertTrue(bs.getMaxPickableTiles()[4]==3);
        assertTrue(gc.getMaxPickableTiles(bs)==3);
        gc.isBookShelfFull(bs);
        assertFalse(gc.getLastRound());

        bs.setTile(2,1,t);
        gc.checkColumnCapability(bs);
        assertTrue(bs.getMaxPickableTiles()[0]==2);
        assertTrue(bs.getMaxPickableTiles()[1]==2);
        assertTrue(bs.getMaxPickableTiles()[2]==3);
        assertTrue(bs.getMaxPickableTiles()[3]==3);
        assertTrue(bs.getMaxPickableTiles()[4]==3);
        assertTrue(gc.getMaxPickableTiles(bs)==3);
        gc.isBookShelfFull(bs);
        assertFalse(gc.getLastRound());

        bs.setTile(2,2,t);
        gc.checkColumnCapability(bs);
        assertTrue(bs.getMaxPickableTiles()[0]==2);
        assertTrue(bs.getMaxPickableTiles()[1]==2);
        assertTrue(bs.getMaxPickableTiles()[2]==2);
        assertTrue(bs.getMaxPickableTiles()[3]==3);
        assertTrue(bs.getMaxPickableTiles()[4]==3);
        assertTrue(gc.getMaxPickableTiles(bs)==3);
        gc.isBookShelfFull(bs);
        assertFalse(gc.getLastRound());

        bs.setTile(2,3,t);
        gc.checkColumnCapability(bs);
        assertTrue(bs.getMaxPickableTiles()[0]==2);
        assertTrue(bs.getMaxPickableTiles()[1]==2);
        assertTrue(bs.getMaxPickableTiles()[2]==2);
        assertTrue(bs.getMaxPickableTiles()[3]==2);
        assertTrue(bs.getMaxPickableTiles()[4]==3);
        assertTrue(gc.getMaxPickableTiles(bs)==3);
        gc.isBookShelfFull(bs);
        assertFalse(gc.getLastRound());

        bs.setTile(2,4,t);
        gc.checkColumnCapability(bs);
        assertTrue(bs.getMaxPickableTiles()[0]==2);
        assertTrue(bs.getMaxPickableTiles()[1]==2);
        assertTrue(bs.getMaxPickableTiles()[2]==2);
        assertTrue(bs.getMaxPickableTiles()[3]==2);
        assertTrue(bs.getMaxPickableTiles()[4]==2);
        assertTrue(gc.getMaxPickableTiles(bs)==2);
        gc.isBookShelfFull(bs);
        assertFalse(gc.getLastRound());

        //Adding one tile per time to the fifth row of the bookshelf.
        bs.setTile(1,0,t);
        gc.checkColumnCapability(bs);
        assertTrue(bs.getMaxPickableTiles()[0]==1);
        assertTrue(bs.getMaxPickableTiles()[1]==2);
        assertTrue(bs.getMaxPickableTiles()[2]==2);
        assertTrue(bs.getMaxPickableTiles()[3]==2);
        assertTrue(bs.getMaxPickableTiles()[4]==2);
        assertTrue(gc.getMaxPickableTiles(bs)==2);
        gc.isBookShelfFull(bs);
        assertFalse(gc.getLastRound());

        bs.setTile(1,1,t);
        gc.checkColumnCapability(bs);
        assertTrue(bs.getMaxPickableTiles()[0]==1);
        assertTrue(bs.getMaxPickableTiles()[1]==1);
        assertTrue(bs.getMaxPickableTiles()[2]==2);
        assertTrue(bs.getMaxPickableTiles()[3]==2);
        assertTrue(bs.getMaxPickableTiles()[4]==2);
        assertTrue(gc.getMaxPickableTiles(bs)==2);
        gc.isBookShelfFull(bs);
        assertFalse(gc.getLastRound());

        bs.setTile(1,2,t);
        gc.checkColumnCapability(bs);
        assertTrue(bs.getMaxPickableTiles()[0]==1);
        assertTrue(bs.getMaxPickableTiles()[1]==1);
        assertTrue(bs.getMaxPickableTiles()[2]==1);
        assertTrue(bs.getMaxPickableTiles()[3]==2);
        assertTrue(bs.getMaxPickableTiles()[4]==2);
        assertTrue(gc.getMaxPickableTiles(bs)==2);
        gc.isBookShelfFull(bs);
        assertFalse(gc.getLastRound());

        bs.setTile(1,3,t);
        gc.checkColumnCapability(bs);
        assertTrue(bs.getMaxPickableTiles()[0]==1);
        assertTrue(bs.getMaxPickableTiles()[1]==1);
        assertTrue(bs.getMaxPickableTiles()[2]==1);
        assertTrue(bs.getMaxPickableTiles()[3]==1);
        assertTrue(bs.getMaxPickableTiles()[4]==2);
        assertTrue(gc.getMaxPickableTiles(bs)==2);
        gc.isBookShelfFull(bs);
        assertFalse(gc.getLastRound());

        bs.setTile(1,4,t);
        gc.checkColumnCapability(bs);
        assertTrue(bs.getMaxPickableTiles()[0]==1);
        assertTrue(bs.getMaxPickableTiles()[1]==1);
        assertTrue(bs.getMaxPickableTiles()[2]==1);
        assertTrue(bs.getMaxPickableTiles()[3]==1);
        assertTrue(bs.getMaxPickableTiles()[4]==1);
        assertTrue(gc.getMaxPickableTiles(bs)==1);
        gc.isBookShelfFull(bs);
        assertFalse(gc.getLastRound());

        ////Adding one tile per time to the sixth row of the bookshelf.
        bs.setTile(0,0,t);
        gc.checkColumnCapability(bs);
        assertTrue(bs.getMaxPickableTiles()[0]==0);
        assertTrue(bs.getMaxPickableTiles()[1]==1);
        assertTrue(bs.getMaxPickableTiles()[2]==1);
        assertTrue(bs.getMaxPickableTiles()[3]==1);
        assertTrue(bs.getMaxPickableTiles()[4]==1);
        assertTrue(gc.getMaxPickableTiles(bs)==1);
        gc.isBookShelfFull(bs);
        assertFalse(gc.getLastRound());

        bs.setTile(0,1,t);
        gc.checkColumnCapability(bs);
        assertTrue(bs.getMaxPickableTiles()[0]==0);
        assertTrue(bs.getMaxPickableTiles()[1]==0);
        assertTrue(bs.getMaxPickableTiles()[2]==1);
        assertTrue(bs.getMaxPickableTiles()[3]==1);
        assertTrue(bs.getMaxPickableTiles()[4]==1);
        assertTrue(gc.getMaxPickableTiles(bs)==1);
        gc.isBookShelfFull(bs);
        assertFalse(gc.getLastRound());

        bs.setTile(0,2,t);
        gc.checkColumnCapability(bs);
        assertTrue(bs.getMaxPickableTiles()[0]==0);
        assertTrue(bs.getMaxPickableTiles()[1]==0);
        assertTrue(bs.getMaxPickableTiles()[2]==0);
        assertTrue(bs.getMaxPickableTiles()[3]==1);
        assertTrue(bs.getMaxPickableTiles()[4]==1);
        assertTrue(gc.getMaxPickableTiles(bs)==1);
        gc.isBookShelfFull(bs);
        assertFalse(gc.getLastRound());

        bs.setTile(0,3,t);
        gc.checkColumnCapability(bs);
        assertTrue(bs.getMaxPickableTiles()[0]==0);
        assertTrue(bs.getMaxPickableTiles()[1]==0);
        assertTrue(bs.getMaxPickableTiles()[2]==0);
        assertTrue(bs.getMaxPickableTiles()[3]==0);
        assertTrue(bs.getMaxPickableTiles()[4]==1);
        assertTrue(gc.getMaxPickableTiles(bs)==1);
        gc.isBookShelfFull(bs);
        assertFalse(gc.getLastRound());

        bs.setTile(0,4,t);
        gc.checkColumnCapability(bs);
        assertTrue(bs.getMaxPickableTiles()[0]==0);
        assertTrue(bs.getMaxPickableTiles()[1]==0);
        assertTrue(bs.getMaxPickableTiles()[2]==0);
        assertTrue(bs.getMaxPickableTiles()[3]==0);
        assertTrue(bs.getMaxPickableTiles()[4]==0);
        assertTrue(gc.getMaxPickableTiles(bs)==0);
        gc.isBookShelfFull(bs);
        assertTrue(gc.getLastRound());
    }

    public void testIsLegalAction() {
        Launcher l4 = new Launcher();
        l4.setNumPlayers(4);
        Launcher l2 = new Launcher();
        l2.setNumPlayers(2);
        Launcher l3 = new Launcher();
        l3.setNumPlayers(3);
        GameChecker gc4 = new GameChecker(l4);
        GameChecker gc2 = new GameChecker(l2);
        GameChecker gc3 = new GameChecker(l3);
        Bag bag = new Bag();
        LivingRoom board4 = new LivingRoom(l4);
        LivingRoom board2 = new LivingRoom(l2);
        LivingRoom board3 = new LivingRoom(l3);

        board4.Start(4);
        assertTrue(gc4.isLegalAction(board4.getTile(8,4)));
        assertFalse(gc4.isLegalAction(board4.getTile(4,4)));
        assertFalse(gc4.isLegalAction(board4.getTile(0,8)));
        assertFalse(gc4.isLegalAction(board4.getTile(0,0)));
        assertFalse(gc4.isLegalAction(board4.getTile(8,8)));
        assertFalse(gc4.isLegalAction(board4.getTile(8,0)));
        assertTrue(gc4.isLegalAction(board4.getTile(0,3)));
        assertTrue(gc4.isLegalAction(board4.getTile(0,4)));
        assertTrue(gc4.isLegalAction(board4.getTile(4,0)));
        assertTrue(gc4.isLegalAction(board4.getTile(5,0)));
        assertTrue(gc4.isLegalAction(board4.getTile(8,5)));
        assertTrue(gc4.isLegalAction(board4.getTile(3,8)));
        assertTrue(gc4.isLegalAction(board4.getTile(4,8)));
        assertTrue(gc4.isLegalAction(board4.getTile(7,3)));
        assertTrue(gc4.isLegalAction(board4.getTile(6,2)));
        assertFalse(gc4.isLegalAction(board4.getTile(6,3)));
        assertFalse(gc4.isLegalAction(board4.getTile(7,4)));
        assertFalse(gc4.isLegalAction(board4.getTile(5,3)));
        assertFalse(gc4.isLegalAction(board4.getTile(7,5)));

        board3.Start(3);
        assertFalse(gc3.isLegalAction(board3.getTile(8,4)));
        assertFalse(gc3.isLegalAction(board3.getTile(4,4)));
        assertFalse(gc3.isLegalAction(board3.getTile(0,8)));
        assertFalse(gc3.isLegalAction(board3.getTile(0,0)));
        assertFalse(gc3.isLegalAction(board3.getTile(8,8)));
        assertFalse(gc3.isLegalAction(board3.getTile(8,0)));
        assertTrue(gc3.isLegalAction(board3.getTile(0,3)));
        assertFalse(gc3.isLegalAction(board3.getTile(0,4)));
        assertFalse(gc3.isLegalAction(board3.getTile(4,0)));
        assertTrue(gc3.isLegalAction(board3.getTile(5,0)));
        assertTrue(gc3.isLegalAction(board3.getTile(8,5)));
        assertTrue(gc3.isLegalAction(board3.getTile(3,8)));
        assertFalse(gc3.isLegalAction(board3.getTile(4,8)));
        assertFalse(gc3.isLegalAction(board3.getTile(7,3)));
        assertTrue(gc3.isLegalAction(board3.getTile(6,2)));
        assertFalse(gc3.isLegalAction(board3.getTile(6,3)));
        assertTrue(gc3.isLegalAction(board3.getTile(7,4)));
        assertFalse(gc3.isLegalAction(board3.getTile(5,3)));
        assertFalse(gc3.isLegalAction(board3.getTile(7,5)));

        board2.Start(2);
        assertFalse(gc2.isLegalAction(board2.getTile(8,4)));
        assertFalse(gc2.isLegalAction(board2.getTile(4,4)));
        assertFalse(gc2.isLegalAction(board2.getTile(0,8)));
        assertFalse(gc2.isLegalAction(board2.getTile(0,0)));
        assertFalse(gc2.isLegalAction(board2.getTile(8,8)));
        assertFalse(gc2.isLegalAction(board2.getTile(8,0)));
        assertFalse(gc2.isLegalAction(board2.getTile(0,3)));
        assertFalse(gc2.isLegalAction(board2.getTile(0,4)));
        assertFalse(gc2.isLegalAction(board2.getTile(4,0)));
        assertFalse(gc2.isLegalAction(board2.getTile(5,0)));
        assertFalse(gc2.isLegalAction(board2.getTile(8,5)));
        assertFalse(gc2.isLegalAction(board2.getTile(3,8)));
        assertFalse(gc2.isLegalAction(board2.getTile(4,8)));
        assertFalse(gc2.isLegalAction(board2.getTile(7,3)));
        assertFalse(gc2.isLegalAction(board2.getTile(6,2)));
        assertTrue(gc2.isLegalAction(board2.getTile(6,3)));
        assertTrue(gc2.isLegalAction(board2.getTile(7,4)));
        assertFalse(gc2.isLegalAction(board2.getTile(5,3)));
        assertTrue(gc2.isLegalAction(board2.getTile(7,5)));
    }

    public void testTestIsLegalAction() {
        Launcher l4 = new Launcher();
        l4.setNumPlayers(4);
        Launcher l2 = new Launcher();
        l2.setNumPlayers(2);
        Launcher l3 = new Launcher();
        l3.setNumPlayers(3);
        GameChecker gc4 = new GameChecker(l4);
        GameChecker gc2 = new GameChecker(l2);
        GameChecker gc3 = new GameChecker(l3);
        Bag bag = new Bag();
        LivingRoom board4 = new LivingRoom(l4);
        LivingRoom board2 = new LivingRoom(l2);
        LivingRoom board3 = new LivingRoom(l3);

        board4.Start(4);
    }

    public void testTestIsLegalAction1() {
        Launcher l4 = new Launcher();
        l4.setNumPlayers(4);
        Launcher l2 = new Launcher();
        l2.setNumPlayers(2);
        Launcher l3 = new Launcher();
        l3.setNumPlayers(3);
        GameChecker gc4 = new GameChecker(l4);
        GameChecker gc2 = new GameChecker(l2);
        GameChecker gc3 = new GameChecker(l3);
        Bag bag = new Bag();
        LivingRoom board4 = new LivingRoom(l4);
        LivingRoom board2 = new LivingRoom(l2);
        LivingRoom board3 = new LivingRoom(l3);

        board4.Start(4);
    }
}