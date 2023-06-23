package it.polimi.ingsw.model;

import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.model.board.Bag;
import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.board.LivingRoom;
import it.polimi.ingsw.model.player.BookShelf;
import junit.framework.TestCase;

public class GameCheckerTest extends TestCase {

    public void testIsRestorable() {
        Launcher l = new Launcher();
        l.setNumPlayers(4);
        GameChecker gc = new GameChecker(l);
        Bag bag = new Bag();
        LivingRoom board = new LivingRoom(l);
        //At the moment, board is empty. So the board must be restored!
        assertTrue(gc.isRestorable(board.getBoard()));
        //At the moment, there's only one tile on the board. So the board must be restored!
        board.putTile(4,4);
        assertTrue(gc.isRestorable(board.getBoard()));
        assertTrue(gc.getRestorable());
        //Now, there are two adjacent tiles on the board. Restoring is not necessary anymore.
        board.putTile(4,5);
        assertFalse(gc.isRestorable(board.getBoard()));
        assertFalse(gc.getRestorable());
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
        assertTrue(gc.getMaxPickableTiles()==3);
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

        board4.start(4);
        assertTrue(gc4.isLegalAction(board4.getBoardTile(8,4)));
        assertFalse(gc4.isLegalAction(board4.getBoardTile(4,4)));
        assertFalse(gc4.isLegalAction(board4.getBoardTile(0,8)));
        assertFalse(gc4.isLegalAction(board4.getBoardTile(0,0)));
        assertFalse(gc4.isLegalAction(board4.getBoardTile(8,8)));
        assertFalse(gc4.isLegalAction(board4.getBoardTile(8,0)));
        assertTrue(gc4.isLegalAction(board4.getBoardTile(0,3)));
        assertTrue(gc4.isLegalAction(board4.getBoardTile(0,4)));
        assertTrue(gc4.isLegalAction(board4.getBoardTile(4,0)));
        assertTrue(gc4.isLegalAction(board4.getBoardTile(5,0)));
        assertTrue(gc4.isLegalAction(board4.getBoardTile(8,5)));
        assertTrue(gc4.isLegalAction(board4.getBoardTile(3,8)));
        assertTrue(gc4.isLegalAction(board4.getBoardTile(4,8)));
        assertTrue(gc4.isLegalAction(board4.getBoardTile(7,3)));
        assertTrue(gc4.isLegalAction(board4.getBoardTile(6,2)));
        assertFalse(gc4.isLegalAction(board4.getBoardTile(6,3)));
        assertFalse(gc4.isLegalAction(board4.getBoardTile(7,4)));
        assertFalse(gc4.isLegalAction(board4.getBoardTile(5,3)));
        assertTrue(gc4.isLegalAction(board4.getBoardTile(7,5)));
        assertTrue(gc4.isLegalAction(board4.getBoardTile(5,1)));
        assertTrue(gc4.getLegalSelection());

        board3.start(3);
        assertFalse(gc3.isLegalAction(board3.getBoardTile(8,4)));
        assertFalse(gc3.isLegalAction(board3.getBoardTile(4,4)));
        assertFalse(gc3.isLegalAction(board3.getBoardTile(0,8)));
        assertFalse(gc3.isLegalAction(board3.getBoardTile(0,0)));
        assertFalse(gc3.isLegalAction(board3.getBoardTile(8,8)));
        assertFalse(gc3.isLegalAction(board3.getBoardTile(8,0)));
        assertTrue(gc3.isLegalAction(board3.getBoardTile(0,3)));
        assertFalse(gc3.isLegalAction(board3.getBoardTile(0,4)));
        assertFalse(gc3.isLegalAction(board3.getBoardTile(4,0)));
        assertTrue(gc3.isLegalAction(board3.getBoardTile(5,0)));
        assertTrue(gc3.isLegalAction(board3.getBoardTile(8,5)));
        assertTrue(gc3.isLegalAction(board3.getBoardTile(3,8)));
        assertFalse(gc3.isLegalAction(board3.getBoardTile(4,8)));
        assertFalse(gc3.isLegalAction(board3.getBoardTile(7,3)));
        assertTrue(gc3.isLegalAction(board3.getBoardTile(6,2)));
        assertTrue(gc3.isLegalAction(board3.getBoardTile(6,3)));
        assertTrue(gc3.isLegalAction(board3.getBoardTile(7,4)));
        assertFalse(gc3.isLegalAction(board3.getBoardTile(5,3)));
        assertTrue(gc3.isLegalAction(board3.getBoardTile(7,5)));

        board2.start(2);
        assertFalse(gc2.isLegalAction(board2.getBoardTile(8,4)));
        assertFalse(gc2.isLegalAction(board2.getBoardTile(4,4)));
        assertFalse(gc2.isLegalAction(board2.getBoardTile(0,8)));
        assertFalse(gc2.isLegalAction(board2.getBoardTile(0,0)));
        assertFalse(gc2.isLegalAction(board2.getBoardTile(8,8)));
        assertFalse(gc2.isLegalAction(board2.getBoardTile(8,0)));
        assertFalse(gc2.isLegalAction(board2.getBoardTile(0,3)));
        assertFalse(gc2.isLegalAction(board2.getBoardTile(0,4)));
        assertFalse(gc2.isLegalAction(board2.getBoardTile(4,0)));
        assertFalse(gc2.isLegalAction(board2.getBoardTile(5,0)));
        assertFalse(gc2.isLegalAction(board2.getBoardTile(8,5)));
        assertFalse(gc2.isLegalAction(board2.getBoardTile(3,8)));
        assertFalse(gc2.isLegalAction(board2.getBoardTile(4,8)));
        assertFalse(gc2.isLegalAction(board2.getBoardTile(7,3)));
        assertFalse(gc2.isLegalAction(board2.getBoardTile(6,2)));
        assertTrue(gc2.isLegalAction(board2.getBoardTile(6,3)));
        assertTrue(gc2.isLegalAction(board2.getBoardTile(7,4)));
        assertFalse(gc2.isLegalAction(board2.getBoardTile(5,3)));
        assertTrue(gc2.isLegalAction(board2.getBoardTile(7,5)));

        assertTrue(gc4.isLegalAction(board4.getBoardTile(7,5)));
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

        board4.start(4);
        assertTrue(gc4.isLegalAction(board4.getBoardTile(8,4), board4.getBoardTile(8,5)));
        assertTrue(gc4.isLegalAction(board4.getBoardTile(0,3), board4.getBoardTile(0,4)));
        assertTrue(gc4.isLegalAction(board4.getBoardTile(4,0), board4.getBoardTile(5,0)));
        assertTrue(gc4.isLegalAction(board4.getBoardTile(3,8), board4.getBoardTile(4,8)));
        assertFalse(gc4.isLegalAction(board4.getBoardTile(3,1), board4.getBoardTile(3,2)));
        assertFalse(gc4.isLegalAction(board4.getBoardTile(8,4), board4.getBoardTile(7,4)));
        assertFalse(gc4.isLegalAction(board4.getBoardTile(0,0), board4.getBoardTile(0,1)));
        assertFalse(gc4.isLegalAction(board4.getBoardTile(0,0), board4.getBoardTile(1,0)));
        assertFalse(gc4.isLegalAction(board4.getBoardTile(0,7), board4.getBoardTile(0,8)));
        assertFalse(gc4.isLegalAction(board4.getBoardTile(0,8), board4.getBoardTile(1,8)));
        assertFalse(gc4.isLegalAction(board4.getBoardTile(7,8), board4.getBoardTile(8,8)));
        assertFalse(gc4.isLegalAction(board4.getBoardTile(8,7), board4.getBoardTile(8,8)));
        assertFalse(gc4.isLegalAction(board4.getBoardTile(7,0), board4.getBoardTile(8,0)));
        assertFalse(gc4.isLegalAction(board4.getBoardTile(8,0), board4.getBoardTile(8,1)));
        assertFalse(gc4.isLegalAction(board4.getBoardTile(7,3), board4.getBoardTile(7,4)));
        assertFalse(gc4.isLegalAction(board4.getBoardTile(7,4), board4.getBoardTile(7,5)));
        board4.getBoardTile(8,4).freeTile();
        board4.getBoardTile(8,5).freeTile();
        assertTrue(gc4.isLegalAction(board4.getBoardTile(7,3), board4.getBoardTile(7,4)));
        assertTrue(gc4.isLegalAction(board4.getBoardTile(7,4), board4.getBoardTile(7,5)));
        assertTrue(gc4.isLegalAction(board4.getBoardTile(3,7),board4.getBoardTile(3,8)));
        assertTrue(gc4.isLegalAction(board4.getBoardTile(0,3),board4.getBoardTile(1,3)));

        board3.start(3);
        assertTrue(gc3.isLegalAction(board3.getBoardTile(7,4),board3.getBoardTile(7,5)));
        assertFalse(gc3.isLegalAction(board3.getBoardTile(6,4),board3.getBoardTile(6,5)));
        board3.getBoardTile(7,5).freeTile();
        board3.getBoardTile(7,4).freeTile();
        assertTrue(gc3.isLegalAction(board3.getBoardTile(6,4),board3.getBoardTile(6,5)));
        board3.getBoardTile(6,6).freeTile();

        assertTrue(gc3.isLegalAction(board3.getBoardTile(6,3),board3.getBoardTile(6,4)));
        board3.getBoardTile(6,2).freeTile();
        board3.getBoardTile(6,3).freeTile();
        board3.getBoardTile(6,4).freeTile();
        board3.getBoardTile(6,5).freeTile();
        assertFalse(gc3.isLegalAction(board3.getBoardTile(6,3),board3.getBoardTile(5,4)));
        assertTrue(gc3.isLegalAction(board3.getBoardTile(5,0),board3.getBoardTile(5,1)));
        assertTrue(gc3.isLegalAction(board3.getBoardTile(4,1),board3.getBoardTile(5,1)));
        board3.getBoardTile(5,0).freeTile();
        assertTrue(gc3.isLegalAction(board3.getBoardTile(2,6)));
        assertTrue(gc3.isLegalAction(board3.getBoardTile(0,3),board3.getBoardTile(1,3)));
        assertTrue(gc3.isLegalAction(board3.getBoardTile(5,2),board3.getBoardTile(5,3)));
        assertTrue(gc3.isLegalAction(board3.getBoardTile(5,1),board3.getBoardTile(5,2)));

        board2.start(2);
        assertTrue(gc2.isLegalAction(board2.getBoardTile(1,3),board2.getBoardTile(1,4)));
        assertTrue(gc2.isLegalAction(board2.getBoardTile(4,1),board2.getBoardTile(5,1)));
        assertTrue(gc2.isLegalAction(board2.getBoardTile(7,4),board2.getBoardTile(7,5)));
        board2.getBoardTile(7,4).freeTile();
        board2.getBoardTile(7,5).freeTile();
        assertTrue(gc2.isLegalAction(board2.getBoardTile(6,4),board2.getBoardTile(6,5)));
        assertFalse(gc2.isLegalAction(board2.getBoardTile(5,3),board2.getBoardTile(6,3)));
        assertFalse(gc2.isLegalAction(board2.getBoardTile(5,4),board2.getBoardTile(6,4)));
        assertFalse(gc2.isLegalAction(board2.getBoardTile(5,5),board2.getBoardTile(6,5)));
        board2.getBoardTile(5,6).freeTile();
        assertTrue(gc2.isLegalAction(board2.getBoardTile(5,5),board2.getBoardTile(6,5)));
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

        board4.start(4);
        assertFalse(gc4.isLegalAction(board4.getBoardTile(1,3),board4.getBoardTile(1,4),board4.getBoardTile(1,5)));
        board4.getBoardTile(0,3).freeTile();
        board4.getBoardTile(0,4).freeTile();
        assertTrue(gc4.isLegalAction(board4.getBoardTile(1,3),board4.getBoardTile(1,4),board4.getBoardTile(1,5)));
        board4.getBoardTile(1,3).freeTile();
        board4.getBoardTile(1,4).freeTile();
        board4.getBoardTile(1,5).freeTile();
        assertTrue(gc4.isLegalAction(board4.getBoardTile(2,3),board4.getBoardTile(2,4),board4.getBoardTile(2,5)));
        board4.getBoardTile(2,2).freeTile();
        assertTrue(gc4.isLegalAction(board4.getBoardTile(2,3),board4.getBoardTile(2,4),board4.getBoardTile(2,5)));
        assertFalse(gc4.isLegalAction(board4.getBoardTile(3,6),board4.getBoardTile(3,7),board4.getBoardTile(3,8)));
        board4.getBoardTile(2,6).freeTile();
        assertTrue(gc4.isLegalAction(board4.getBoardTile(3,6),board4.getBoardTile(3,7),board4.getBoardTile(3,8)));
        assertFalse(gc4.isLegalAction(board4.getBoardTile(4,6),board4.getBoardTile(4,7),board4.getBoardTile(4,8)));
        board4.getBoardTile(5,6).freeTile();
        board4.getBoardTile(5,7).freeTile();
        board4.getBoardTile(6,6).freeTile();
        assertTrue(gc4.isLegalAction(board4.getBoardTile(4,6),board4.getBoardTile(4,7),board4.getBoardTile(4,8)));
        assertFalse(gc4.isLegalAction(board4.getBoardTile(3,1),board4.getBoardTile(4,1),board4.getBoardTile(5,1)));
        board4.getBoardTile(4,0).freeTile();
        board4.getBoardTile(5,0).freeTile();
        assertTrue(gc4.isLegalAction(board4.getBoardTile(3,1),board4.getBoardTile(4,1),board4.getBoardTile(5,1)));
        assertFalse(gc4.isLegalAction(board4.getBoardTile(4,2),board4.getBoardTile(5,2),board4.getBoardTile(6,2)));
        board4.getBoardTile(4,1).freeTile();
        board4.getBoardTile(5,1).freeTile();
        assertTrue(gc4.isLegalAction(board4.getBoardTile(4,2),board4.getBoardTile(5,2),board4.getBoardTile(6,2)));
        assertTrue(gc4.isLegalAction(board4.getBoardTile(3,2),board4.getBoardTile(4,2),board4.getBoardTile(5,2)));
        board4.getBoardTile(3,1).freeTile();
        board4.getBoardTile(2,2).freeTile();
        board4.getBoardTile(6,2).freeTile();
        assertTrue(gc4.isLegalAction(board4.getBoardTile(3,2),board4.getBoardTile(4,2),board4.getBoardTile(5,2)));

        board3.start(3);
        assertFalse(gc3.isLegalAction(board3.getBoardTile(0,3),board3.getBoardTile(1,3),board3.getBoardTile(2,3)));
        board3.getBoardTile(2,2).freeTile();
        assertTrue(gc3.isLegalAction(board3.getBoardTile(0,3),board3.getBoardTile(1,3),board3.getBoardTile(2,3)));
        assertFalse(gc3.isLegalAction(board3.getBoardTile(3,6),board3.getBoardTile(3,7),board3.getBoardTile(3,8)));
        board3.getBoardTile(2,6).freeTile();
        board3.getBoardTile(4,7).freeTile();
        assertTrue(gc3.isLegalAction(board3.getBoardTile(3,6),board3.getBoardTile(3,7),board3.getBoardTile(3,8)));
        assertFalse(gc3.isLegalAction(board3.getBoardTile(5,5),board3.getBoardTile(6,5),board3.getBoardTile(7,5)));
        board3.getBoardTile(5,6).freeTile();
        board3.getBoardTile(6,6).freeTile();
        assertTrue(gc3.isLegalAction(board3.getBoardTile(5,5),board3.getBoardTile(6,5),board3.getBoardTile(7,5)));
        board3.getBoardTile(8,5).freeTile();
        assertTrue(gc3.isLegalAction(board3.getBoardTile(5,5),board3.getBoardTile(6,5),board3.getBoardTile(7,5)));

        board2.start(2);

        assertFalse(gc2.isLegalAction(board2.getBoardTile(6,3),board2.getBoardTile(6,4),board2.getBoardTile(6,5)));
        board2.getBoardTile(7,4).freeTile();
        board2.getBoardTile(7,5).freeTile();
        assertTrue(gc2.isLegalAction(board2.getBoardTile(6,3),board2.getBoardTile(6,4),board2.getBoardTile(6,5)));
        assertFalse(gc2.isLegalAction(board2.getBoardTile(3,6),board2.getBoardTile(4,6),board2.getBoardTile(5,6)));
        board2.getBoardTile(3,7).freeTile();
        board2.getBoardTile(4,7).freeTile();
        assertTrue(gc2.isLegalAction(board2.getBoardTile(3,6),board2.getBoardTile(4,6),board2.getBoardTile(5,6)));
        assertFalse(gc2.isLegalAction(board2.getBoardTile(3,5),board2.getBoardTile(4,5),board2.getBoardTile(5,5)));
        board2.getBoardTile(3,6).freeTile();
        board2.getBoardTile(4,6).freeTile();
        board2.getBoardTile(5,6).freeTile();
        board2.getBoardTile(2,5).freeTile();
        board2.getBoardTile(6,5).freeTile();
        assertTrue(gc2.isLegalAction(board2.getBoardTile(3,5),board2.getBoardTile(4,5),board2.getBoardTile(5,5)));
    }
}
