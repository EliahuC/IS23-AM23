package it.polimi.ingsw.model.board;

import it.polimi.ingsw.Launcher;
import junit.framework.TestCase;

import java.util.ArrayList;

public class LivingRoomTest extends TestCase {
    public void testCorrectLivingRoom1() {               //test for correct position of UNAVAILABLE boardTokenCategory
        LivingRoom L13 = new LivingRoom(new Launcher());
        assertEquals(BoardToken.boardTokenCategory.UNAVAILABLE, L13.getBoard()[0][0].getCategory());
        assertEquals(BoardToken.boardTokenCategory.UNAVAILABLE, L13.getBoard()[0][1].getCategory());
        assertEquals(BoardToken.boardTokenCategory.UNAVAILABLE, L13.getBoard()[0][2].getCategory());
        assertEquals(BoardToken.boardTokenCategory.UNAVAILABLE, L13.getBoard()[0][6].getCategory());
        assertEquals(BoardToken.boardTokenCategory.UNAVAILABLE, L13.getBoard()[0][7].getCategory());
        assertEquals(BoardToken.boardTokenCategory.UNAVAILABLE, L13.getBoard()[0][8].getCategory());
        assertEquals(BoardToken.boardTokenCategory.UNAVAILABLE, L13.getBoard()[1][0].getCategory());
        assertEquals(BoardToken.boardTokenCategory.UNAVAILABLE, L13.getBoard()[1][1].getCategory());
        assertEquals(BoardToken.boardTokenCategory.UNAVAILABLE, L13.getBoard()[1][2].getCategory());
        assertEquals(BoardToken.boardTokenCategory.UNAVAILABLE, L13.getBoard()[1][6].getCategory());
        assertEquals(BoardToken.boardTokenCategory.UNAVAILABLE, L13.getBoard()[1][7].getCategory());
        assertEquals(BoardToken.boardTokenCategory.UNAVAILABLE, L13.getBoard()[1][8].getCategory());
        assertEquals(BoardToken.boardTokenCategory.UNAVAILABLE, L13.getBoard()[7][0].getCategory());
        assertEquals(BoardToken.boardTokenCategory.UNAVAILABLE, L13.getBoard()[7][1].getCategory());
        assertEquals(BoardToken.boardTokenCategory.UNAVAILABLE, L13.getBoard()[7][2].getCategory());
        assertEquals(BoardToken.boardTokenCategory.UNAVAILABLE, L13.getBoard()[7][6].getCategory());
        assertEquals(BoardToken.boardTokenCategory.UNAVAILABLE, L13.getBoard()[7][7].getCategory());
        assertEquals(BoardToken.boardTokenCategory.UNAVAILABLE, L13.getBoard()[7][8].getCategory());
        assertEquals(BoardToken.boardTokenCategory.UNAVAILABLE, L13.getBoard()[8][0].getCategory());
        assertEquals(BoardToken.boardTokenCategory.UNAVAILABLE, L13.getBoard()[8][1].getCategory());
        assertEquals(BoardToken.boardTokenCategory.UNAVAILABLE, L13.getBoard()[8][2].getCategory());
        assertEquals(BoardToken.boardTokenCategory.UNAVAILABLE, L13.getBoard()[8][6].getCategory());
        assertEquals(BoardToken.boardTokenCategory.UNAVAILABLE, L13.getBoard()[8][7].getCategory());
        assertEquals(BoardToken.boardTokenCategory.UNAVAILABLE, L13.getBoard()[8][8].getCategory());
        assertEquals(BoardToken.boardTokenCategory.UNAVAILABLE, L13.getBoard()[3][0].getCategory());
        assertEquals(BoardToken.boardTokenCategory.UNAVAILABLE, L13.getBoard()[0][5].getCategory());
        assertEquals(BoardToken.boardTokenCategory.UNAVAILABLE, L13.getBoard()[5][8].getCategory());
        assertEquals(BoardToken.boardTokenCategory.UNAVAILABLE, L13.getBoard()[8][3].getCategory());
        assertEquals(BoardToken.boardTokenCategory.UNAVAILABLE, L13.getBoard()[2][0].getCategory());
        assertEquals(BoardToken.boardTokenCategory.UNAVAILABLE, L13.getBoard()[2][1].getCategory());
        assertEquals(BoardToken.boardTokenCategory.UNAVAILABLE, L13.getBoard()[2][7].getCategory());
        assertEquals(BoardToken.boardTokenCategory.UNAVAILABLE, L13.getBoard()[2][8].getCategory());
        assertEquals(BoardToken.boardTokenCategory.UNAVAILABLE, L13.getBoard()[6][0].getCategory());
        assertEquals(BoardToken.boardTokenCategory.UNAVAILABLE, L13.getBoard()[6][1].getCategory());
        assertEquals(BoardToken.boardTokenCategory.UNAVAILABLE, L13.getBoard()[6][7].getCategory());
        assertEquals(BoardToken.boardTokenCategory.UNAVAILABLE, L13.getBoard()[6][8].getCategory());
    }

    public void testCorrectLivingRoom2() {   //test for correct position of THREE boardTokenCategory
        LivingRoom L14 = new LivingRoom(new Launcher());
        assertEquals(BoardToken.boardTokenCategory.THREE, L14.getBoard()[0][3].getCategory());
        assertEquals(BoardToken.boardTokenCategory.THREE, L14.getBoard()[2][2].getCategory());
        assertEquals(BoardToken.boardTokenCategory.THREE, L14.getBoard()[2][6].getCategory());
        assertEquals(BoardToken.boardTokenCategory.THREE, L14.getBoard()[3][8].getCategory());
        assertEquals(BoardToken.boardTokenCategory.THREE, L14.getBoard()[5][0].getCategory());
        assertEquals(BoardToken.boardTokenCategory.THREE, L14.getBoard()[6][2].getCategory());
        assertEquals(BoardToken.boardTokenCategory.THREE, L14.getBoard()[6][6].getCategory());
        assertEquals(BoardToken.boardTokenCategory.THREE, L14.getBoard()[8][5].getCategory());
    }

    public void testCorrectLivingRoom3() {   //test for correct position of FOUR boardTokenCategory
        LivingRoom L15 = new LivingRoom(new Launcher());
        assertEquals(BoardToken.boardTokenCategory.FOUR, L15.getBoard()[0][4].getCategory());
        assertEquals(BoardToken.boardTokenCategory.FOUR, L15.getBoard()[1][5].getCategory());
        assertEquals(BoardToken.boardTokenCategory.FOUR, L15.getBoard()[3][1].getCategory());
        assertEquals(BoardToken.boardTokenCategory.FOUR, L15.getBoard()[4][0].getCategory());
        assertEquals(BoardToken.boardTokenCategory.FOUR, L15.getBoard()[4][8].getCategory());
        assertEquals(BoardToken.boardTokenCategory.FOUR, L15.getBoard()[5][7].getCategory());
        assertEquals(BoardToken.boardTokenCategory.FOUR, L15.getBoard()[7][3].getCategory());
        assertEquals(BoardToken.boardTokenCategory.FOUR, L15.getBoard()[8][4].getCategory());
    }

    public void testCorrectLivingRoom4() {   //test for correct position of NORMAL boardTokenCategory
        LivingRoom L16 = new LivingRoom(new Launcher());
        assertEquals(BoardToken.boardTokenCategory.NORMAL, L16.getBoard()[1][3].getCategory());
        assertEquals(BoardToken.boardTokenCategory.NORMAL, L16.getBoard()[1][4].getCategory());
        assertEquals(BoardToken.boardTokenCategory.NORMAL, L16.getBoard()[2][3].getCategory());
        assertEquals(BoardToken.boardTokenCategory.NORMAL, L16.getBoard()[2][4].getCategory());
        assertEquals(BoardToken.boardTokenCategory.NORMAL, L16.getBoard()[2][5].getCategory());
        assertEquals(BoardToken.boardTokenCategory.NORMAL, L16.getBoard()[3][2].getCategory());
        assertEquals(BoardToken.boardTokenCategory.NORMAL, L16.getBoard()[3][3].getCategory());
        assertEquals(BoardToken.boardTokenCategory.NORMAL, L16.getBoard()[3][4].getCategory());
        assertEquals(BoardToken.boardTokenCategory.NORMAL, L16.getBoard()[3][5].getCategory());
        assertEquals(BoardToken.boardTokenCategory.NORMAL, L16.getBoard()[3][6].getCategory());
        assertEquals(BoardToken.boardTokenCategory.NORMAL, L16.getBoard()[3][7].getCategory());
        assertEquals(BoardToken.boardTokenCategory.NORMAL, L16.getBoard()[4][1].getCategory());
        assertEquals(BoardToken.boardTokenCategory.NORMAL, L16.getBoard()[4][2].getCategory());
        assertEquals(BoardToken.boardTokenCategory.NORMAL, L16.getBoard()[4][3].getCategory());
        assertEquals(BoardToken.boardTokenCategory.NORMAL, L16.getBoard()[4][4].getCategory());
        assertEquals(BoardToken.boardTokenCategory.NORMAL, L16.getBoard()[4][5].getCategory());
        assertEquals(BoardToken.boardTokenCategory.NORMAL, L16.getBoard()[4][6].getCategory());
        assertEquals(BoardToken.boardTokenCategory.NORMAL, L16.getBoard()[4][7].getCategory());
        assertEquals(BoardToken.boardTokenCategory.NORMAL, L16.getBoard()[5][1].getCategory());
        assertEquals(BoardToken.boardTokenCategory.NORMAL, L16.getBoard()[5][2].getCategory());
        assertEquals(BoardToken.boardTokenCategory.NORMAL, L16.getBoard()[5][3].getCategory());
        assertEquals(BoardToken.boardTokenCategory.NORMAL, L16.getBoard()[5][4].getCategory());
        assertEquals(BoardToken.boardTokenCategory.NORMAL, L16.getBoard()[5][5].getCategory());
        assertEquals(BoardToken.boardTokenCategory.NORMAL, L16.getBoard()[5][6].getCategory());
        assertEquals(BoardToken.boardTokenCategory.NORMAL, L16.getBoard()[6][3].getCategory());
        assertEquals(BoardToken.boardTokenCategory.NORMAL, L16.getBoard()[6][4].getCategory());
        assertEquals(BoardToken.boardTokenCategory.NORMAL, L16.getBoard()[6][5].getCategory());
        assertEquals(BoardToken.boardTokenCategory.NORMAL, L16.getBoard()[7][4].getCategory());
        assertEquals(BoardToken.boardTokenCategory.NORMAL, L16.getBoard()[7][5].getCategory());
    }

    public void testPutTile1() {             // two players, BoardTokenCategory "THREE"
        LivingRoom L1;
        Launcher L = new Launcher();
        L.setNumPlayers(2);
        L1 = new LivingRoom(L);
        L1.putTile(2, 2);
        assertNull(L1.getBoard()[2][2].getTile());
    }

    public void testPutTile2() {             // two players, BoardTokenCategory "FOUR"
        LivingRoom L2;
        Launcher L = new Launcher();
        L.setNumPlayers(2);
        L2 = new LivingRoom(L);
        L2.putTile(1, 5);
        assertNull(L2.getBoard()[1][5].getTile());
    }

    public void testPutTile3() {         //two players, BoardTokenCategory "UNAVAILABLE
        LivingRoom L3;
        Launcher L = new Launcher();
        L.setNumPlayers(2);
        L3 = new LivingRoom(L);
        L3.putTile(8, 3);
        assertNull(L3.getBoard()[8][3].getTile());
    }

    public void testPutTile4() {             // two players, BoardTokenCategory "NORMAL"
        LivingRoom L4;
        Launcher L = new Launcher();
        L.setNumPlayers(2);
        L4 = new LivingRoom(L);
        L4.putTile(4, 3);
        assertNotNull(L4.getBoard()[4][3].getTile());
    }

    public void testPutTile5() {             // three players, BoardTokenCategory "THREE"
        LivingRoom L5;
        Launcher L = new Launcher();
        L.setNumPlayers(3);
        L5 = new LivingRoom(L);
        L5.putTile(2, 2);
        assertNotNull(L5.getBoard()[2][2].getTile());
    }

    public void testPutTile6() {             // three players, BoardTokenCategory "FOUR"
        LivingRoom L6;
        Launcher L = new Launcher();
        L.setNumPlayers(3);
        L6 = new LivingRoom(L);
        L6.putTile(1, 5);
        assertNull(L6.getBoard()[1][5].getTile());
    }

    public void testPutTile7() {         //three players, BoardTokenCategory "UNAVAILABLE
        LivingRoom L7;
        Launcher L = new Launcher();
        L.setNumPlayers(3);
        L7 = new LivingRoom(L);
        L7.putTile(8, 3);
        assertNull(L7.getBoard()[8][3].getTile());
    }

    public void testPutTile8() {             // three players, BoardTokenCategory "NORMAL"
        LivingRoom L8;
        Launcher L = new Launcher();
        L.setNumPlayers(3);
        L8 = new LivingRoom(L);
        L8.putTile(4, 3);
        assertNotNull(L8.getBoard()[4][3].getTile());
    }

    public void testPutTile9() {             // four players, BoardTokenCategory "THREE"
        LivingRoom L9;
        Launcher L = new Launcher();
        L.setNumPlayers(4);
        L9 = new LivingRoom(L);
        L9.putTile(2, 2);
        assertNotNull(L9.getBoard()[2][2].getTile());
    }

    public void testPutTile10() {             // four players, BoardTokenCategory "FOUR"
        LivingRoom L10;
        Launcher L = new Launcher();
        L.setNumPlayers(4);
        L10 = new LivingRoom(L);
        L10.putTile(1, 5);
        assertNotNull(L10.getBoard()[1][5].getTile());
    }

    public void testPutTile11() {         //four players, BoardTokenCategory "UNAVAILABLE
        LivingRoom L11;
        Launcher L = new Launcher();
        L.setNumPlayers(4);
        L11 = new LivingRoom(L);
        L11.putTile(8, 3);
        assertNull(L11.getBoard()[8][3].getTile());
    }

    public void testPutTile12() {             // four players, BoardTokenCategory "NORMAL"
        LivingRoom L12;
        Launcher L = new Launcher();
        L.setNumPlayers(4);
        L12 = new LivingRoom(L);
        L12.putTile(4, 3);
        assertNotNull(L12.getBoard()[4][3].getTile());
    }

    public void testNullTileAfterExtraction_FIRST() {    //AFTER A PLAYER'S LEGAL MOVE, TAKING AN ITEM TILE FROM THE LIVINGROOM,
        //ITS REFERENCE IS SETTED TO NULL
        LivingRoom L13;
        Launcher L = new Launcher();
        L.setNumPlayers(4);
        L13 = new LivingRoom(L);
        L13.start(L.getNumPlayers());
        ArrayList<Integer> CoordinatesTiles = new ArrayList<>();
        CoordinatesTiles.add(8);
        CoordinatesTiles.add(5);
        L13.getTiles(CoordinatesTiles);
        assertNull(L13.getBoardTile(8, 5).getTile());
    }

    public void testNullTileAfterExtraction_SECOND() {       //AFTER A PLAYER'S LEGAL MOVE, TAKING AN ITEM TILE FROM THE
        LivingRoom L14;                                     //LIVINGROOM, ONLY THE TAKEN TILE'S REFERENCE IS SETTED TO NULL
        Launcher L = new Launcher();
        L.setNumPlayers(4);
        L14 = new LivingRoom(L);
        L14.start(L.getNumPlayers());
        ArrayList<Integer> CoordinatesTiles = new ArrayList<>();
        CoordinatesTiles.add(8);
        CoordinatesTiles.add(5);
        L14.getTiles(CoordinatesTiles);
        assertNotNull(L14.getBoardTile(6, 4).getTile());
    }

    public void testNullTileAfterExtraction_THIRD() {       //AFTER A PLAYER'S LEGAL MOVE, TAKING TWO ITEM TILES FROM THE
        LivingRoom L15;                                     //LIVINGROOM, TILES' REFERENCES ARE SETTED TO NULL
        Launcher L = new Launcher();
        L.setNumPlayers(4);
        L15 = new LivingRoom(L);
        L15.start(L.getNumPlayers());
        ArrayList<Integer> CoordinatesTiles = new ArrayList<>();
        CoordinatesTiles.add(8);
        CoordinatesTiles.add(5);
        CoordinatesTiles.add(8);
        CoordinatesTiles.add(4);
        L15.getTiles(CoordinatesTiles);
        assertNull(L15.getBoardTile(8, 5).getTile());
        assertNull(L15.getBoardTile(8, 4).getTile());
    }
    public void testNullTileAfterExtraction_FOURTH() {   //AFTER A PLAYER'S LEGAL MOVE, TAKING THREE ITEM TILES FROM THE
        LivingRoom L17;                                     //LIVINGROOM, TILES' REFERENCES ARE SETTED TO NULL
        Launcher L = new Launcher();
        L.setNumPlayers(4);
        L17 = new LivingRoom(L);
        L17.start(L.getNumPlayers());
        ArrayList<Integer> CoordinatesTiles = new ArrayList<>();
        CoordinatesTiles.add(3);
        CoordinatesTiles.add(7);
        CoordinatesTiles.add(4);
        CoordinatesTiles.add(7);
        CoordinatesTiles.add(5);
        CoordinatesTiles.add(7);
        L17.getTiles(CoordinatesTiles);
        assertNull(L17.getBoardTile(3, 7).getTile());
        assertNull(L17.getBoardTile(4, 7).getTile());
        assertNull(L17.getBoardTile(5, 7).getTile());
    }

}
