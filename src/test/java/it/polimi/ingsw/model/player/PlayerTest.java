package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.board.ItemTile;
import junit.framework.TestCase;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class PlayerTest extends TestCase {


    public void testInsertToken_FIRST() {
        Player p = new Player("Bob");
        ArrayList<ItemTile> tiles = new ArrayList<>();
        ItemTile i1 = new ItemTile("CATS");
        ItemTile i2 = new ItemTile("PLANTS");
        ItemTile i3 = new ItemTile("GAMES");
        tiles.add(i1);
        tiles.add(i2);
        tiles.add(i3);
        PropertyChangeListener l = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {

            }
        };
        p.setListener(l);
        p.insertToken(tiles,3);
        assertEquals(i1,p.getPlayerBookshelf().getTile(5,3));
        assertEquals(i2,p.getPlayerBookshelf().getTile(4,3));
        assertEquals(i3,p.getPlayerBookshelf().getTile(3,3));
    }
    public void testInsertToken_SECOND() {
        Player p = new Player("Bob");
        ArrayList<ItemTile> tiles = new ArrayList<>();
        ItemTile i1 = new ItemTile("CATS");
        ItemTile i2 = new ItemTile("PLANTS");
        ItemTile i3 = new ItemTile("GAMES");
        tiles.add(i1);
        tiles.add(i2);
        tiles.add(i3);
        PropertyChangeListener l = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {

            }
        };
        p.setListener(l);
        p.insertToken(tiles,3);
        assertNull(p.getPlayerBookshelf().getTile(2,3));
        assertNull(p.getPlayerBookshelf().getTile(1,3));
        assertNull(p.getPlayerBookshelf().getTile(0,3));
    }
    public void testInsertToken_THIRD() {
        Player p = new Player("Bob");
        ArrayList<ItemTile> tiles = new ArrayList<>();
        ItemTile i1 = new ItemTile("CATS");
        ItemTile i2 = new ItemTile("PLANTS");
        ItemTile i3 = new ItemTile("GAMES");
        ItemTile i4 = new ItemTile("TROPHIES");
        ItemTile i5 = new ItemTile("BOOKS");
        ItemTile i6 = new ItemTile("FRAMES");
        tiles.add(i4);
        tiles.add(i5);
        tiles.add(i6);
        p.getPlayerBookshelf().setTile(5,3,i1);
        p.getPlayerBookshelf().setTile(4,3,i2);
        p.getPlayerBookshelf().setTile(3,3,i3);
        PropertyChangeListener l = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {

            }
        };
        p.setListener(l);
        p.insertToken(tiles,3);
        assertEquals(i1,p.getPlayerBookshelf().getTile(5,3));
        assertEquals(i2,p.getPlayerBookshelf().getTile(4,3));
        assertEquals(i3,p.getPlayerBookshelf().getTile(3,3));
        assertEquals(i4,p.getPlayerBookshelf().getTile(2,3));
        assertEquals(i5,p.getPlayerBookshelf().getTile(1,3));
        assertEquals(i6,p.getPlayerBookshelf().getTile(0,3));
    }
    public void testEndGamePoints_SECOND(){
        Player p = new Player("Tom");
        Player p2 = new Player("Butch");
        p.getPlayerBookshelf().setTile(1,1, new ItemTile("PLANTS"));
        p.getPlayerBookshelf().setTile(5,4, new ItemTile("FRAMES"));
        p.getPlayerBookshelf().setTile(2,0, new ItemTile("PLANTS"));
        p.getPlayerBookshelf().setTile(3,4, new ItemTile("BOOKS"));
        p.getPlayerBookshelf().setTile(2,2, new ItemTile("GAMES"));
        p.getPlayerBookshelf().setTile(4,3, new ItemTile("TROPHIES"));
        p.getPlayerBookshelf().setTile(0,0, new ItemTile("PLANTS"));
        p.getPlayerBookshelf().setTile(0,1, new ItemTile("PLANTS"));
        p.getPlayerBookshelf().setTile(1,0, new ItemTile("PLANTS"));
        p.getPlayerBookshelf().setTile(0,2, new ItemTile("BOOKS"));
        p.getPlayerBookshelf().setTile(0,3, new ItemTile("CATS"));
        p.getPlayerBookshelf().setTile(0,4, new ItemTile("GAMES"));
        p.getPlayerBookshelf().setTile(1,2, new ItemTile("FRAMES"));
        p.getPlayerBookshelf().setTile(1,3, new ItemTile("GAMES"));
        p.getPlayerBookshelf().setTile(1,4, new ItemTile("GAMES"));
        p.getPlayerBookshelf().setTile(2,1, new ItemTile("BOOKS"));
        p.getPlayerBookshelf().setTile(2,3, new ItemTile("FRAMES"));
        p.getPlayerBookshelf().setTile(2,4, new ItemTile("FRAMES"));
        p.getPlayerBookshelf().setTile(3,0, new ItemTile("BOOKS"));
        p.getPlayerBookshelf().setTile(3,1, new ItemTile("BOOKS"));
        p.getPlayerBookshelf().setTile(3,2, new ItemTile("TROPHIES"));
        p.getPlayerBookshelf().setTile(3,3, new ItemTile("CATS"));
        p.getPlayerBookshelf().setTile(4,0, new ItemTile("CATS"));
        p.getPlayerBookshelf().setTile(4,1, new ItemTile("BOOKS"));
        p.getPlayerBookshelf().setTile(4,2, new ItemTile("CATS"));
        p.getPlayerBookshelf().setTile(4,4, new ItemTile("BOOKS"));
        p.getPlayerBookshelf().setTile(5,0, new ItemTile("BOOKS"));
        p.getPlayerBookshelf().setTile(5,1, new ItemTile("CATS"));
        p.getPlayerBookshelf().setTile(5,2, new ItemTile("TROPHIES"));
        p.getPlayerBookshelf().setTile(5,3, new ItemTile("FRAMES"));
        p2.getPlayerBookshelf().setTile(1, 1, new ItemTile("PLANTS"));
        p2.getPlayerBookshelf().setTile(2, 2, new ItemTile("FRAMES"));
        p2.getPlayerBookshelf().setTile(5, 0, new ItemTile("CATS"));
        p2.getPlayerBookshelf().setTile(0, 2, new ItemTile("BOOKS"));
        p2.getPlayerBookshelf().setTile(4, 4, new ItemTile("GAMES"));
        p2.getPlayerBookshelf().setTile(3, 3, new ItemTile("TROPHIES"));
        p2.getPlayerBookshelf().setTile(3, 3, new ItemTile("TROPHIES"));
        p2.getPlayerBookshelf().setTile(0, 0, new ItemTile("PLANTS"));
        p2.getPlayerBookshelf().setTile(0, 1, new ItemTile("PLANTS"));
        p2.getPlayerBookshelf().setTile(0, 3, new ItemTile("BOOKS"));
        p2.getPlayerBookshelf().setTile(0, 4, new ItemTile("BOOKS"));
        p2.getPlayerBookshelf().setTile(1, 0, new ItemTile("GAMES"));
        p2.getPlayerBookshelf().setTile(1, 2, new ItemTile("PLANTS"));
        p2.getPlayerBookshelf().setTile(1, 3, new ItemTile("FRAMES"));
        p2.getPlayerBookshelf().setTile(1, 4, new ItemTile("BOOKS"));
        p2.getPlayerBookshelf().setTile(2, 0, new ItemTile("GAMES"));
        p2.getPlayerBookshelf().setTile(2, 1, new ItemTile("GAMES"));
        p2.getPlayerBookshelf().setTile(2, 3, new ItemTile("FRAMES"));
        p2.getPlayerBookshelf().setTile(2, 4, new ItemTile("CATS"));
        p2.getPlayerBookshelf().setTile(3, 0, new ItemTile("BOOKS"));
        p2.getPlayerBookshelf().setTile(3, 1, new ItemTile("BOOKS"));
        p2.getPlayerBookshelf().setTile(3, 2, new ItemTile("GAMES"));
        p2.getPlayerBookshelf().setTile(3, 4, new ItemTile("TROPHIES"));
        p2.getPlayerBookshelf().setTile(4, 0, new ItemTile("CATS"));
        p2.getPlayerBookshelf().setTile(4, 1, new ItemTile("GAMES"));
        p2.getPlayerBookshelf().setTile(4, 2, new ItemTile("BOOKS"));
        p2.getPlayerBookshelf().setTile(4, 3, new ItemTile("TROPHIES"));
        p2.getPlayerBookshelf().setTile(5, 1, new ItemTile("PLANTS"));
        p2.getPlayerBookshelf().setTile(5, 2, new ItemTile("TROPHIES"));
        p2.getPlayerBookshelf().setTile(5, 3, new ItemTile("TROPHIES"));
        p2.getPlayerBookshelf().setTile(5, 4, new ItemTile("TROPHIES"));
        p.setPersonalGoalCard(new PersonalGoalCard(2));
        p.endGamePoints();
        assertEquals(19,p.getScore());
        p2.setPersonalGoalCard(new PersonalGoalCard(12));
        p2.endGamePoints();
        assertEquals(30,p2.getScore());
        Pair x = new Pair(0,0);
        x.setX(4);
        x.setY(3);
        assertFalse(p.isNowPlaying());
        assertFalse(p.getNowPlaying());
        assertFalse(p.isLastRound());
    }
}
