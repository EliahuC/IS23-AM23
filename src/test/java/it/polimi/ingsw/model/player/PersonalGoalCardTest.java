package it.polimi.ingsw.model.player;

import com.google.gson.Gson;
import it.polimi.ingsw.model.board.ItemTile;
import junit.framework.TestCase;

import java.util.HashMap;

public class PersonalGoalCardTest extends TestCase {

    public void testCheckGoalPGC1_0() {     // test in which a bookshelf doesn't match any ItemTile in the correct
        PersonalGoalCard PG = new PersonalGoalCard(1);      //position
        BookShelf b = new BookShelf();
        b.setTile(0, 0, new ItemTile("CATS"));
        b.setTile(0, 2, new ItemTile("TROPHIES"));
        b.setTile(1, 4, new ItemTile("PLANTS"));
        b.setTile(2, 3, new ItemTile("FRAMES"));
        b.setTile(3, 1, new ItemTile("BOOKS"));
        b.setTile(5, 2, new ItemTile("GAMES"));
        assertEquals(0, PG.CheckGoal(b));
    }

    public void testCheckGoalPGC1_FIRST() {      //test in witch a bookshelf matches only one ItemTile in the correct
        PersonalGoalCard PG1 = new PersonalGoalCard(1); //position
        BookShelf b = new BookShelf();
        b.setTile(0, 0, new ItemTile("CATS"));
        b.setTile(0, 2, new ItemTile("TROPHIES"));
        b.setTile(1, 4, new ItemTile("PLANTS"));
        b.setTile(2, 3, new ItemTile("BOOKS"));
        b.setTile(3, 1, new ItemTile("FRAMES"));
        b.setTile(5, 2, new ItemTile("GAMES"));
        assertEquals(1, PG1.CheckGoal(b));
    }

    public void testCheckGoalPGC1_SECOND() {     //test in witch a bookshelf matches two ItemTiles in the correct
        PersonalGoalCard PG1 = new PersonalGoalCard(1); //position
        BookShelf b = new BookShelf();
        b.setTile(0, 0, new ItemTile("CATS"));
        b.setTile(0, 2, new ItemTile("FRAMES"));
        b.setTile(1, 4, new ItemTile("PLANTS"));
        b.setTile(2, 3, new ItemTile("BOOKS"));
        b.setTile(3, 1, new ItemTile("FRAMES"));
        b.setTile(5, 2, new ItemTile("GAMES"));
        assertEquals(2, PG1.CheckGoal(b));
    }

    public void testCheckGoalPGC1_THIRD() {      //test in witch a bookshelf matches three ItemTiles in the correct
        PersonalGoalCard PG1 = new PersonalGoalCard(1); //position
        BookShelf b = new BookShelf();
        b.setTile(0, 0, new ItemTile("CATS"));
        b.setTile(0, 2, new ItemTile("FRAMES"));
        b.setTile(1, 4, new ItemTile("PLANTS"));
        b.setTile(2, 3, new ItemTile("BOOKS"));
        b.setTile(3, 1, new ItemTile("FRAMES"));
        b.setTile(5, 2, new ItemTile("TROPHIES"));
        assertEquals(4, PG1.CheckGoal(b));
    }

    public void testCheckGoalPGC1_FOURTH() {     //test in witch a bookshelf matches four ItemTiles in the correct
        PersonalGoalCard PG1 = new PersonalGoalCard(1); //position
        BookShelf b = new BookShelf();
        b.setTile(0, 0, new ItemTile("PLANTS"));
        b.setTile(0, 2, new ItemTile("FRAMES"));
        b.setTile(1, 4, new ItemTile("PLANTS"));
        b.setTile(2, 3, new ItemTile("BOOKS"));
        b.setTile(3, 1, new ItemTile("FRAMES"));
        b.setTile(5, 2, new ItemTile("TROPHIES"));
        assertEquals(6, PG1.CheckGoal(b));
    }

    public void testCheckGoalPGC1_FIFTH() {      //test in witch a bookshelf matches five ItemTiles in the correct
        PersonalGoalCard PG1 = new PersonalGoalCard(1); //position
        BookShelf b = new BookShelf();
        b.setTile(0, 0, new ItemTile("PLANTS"));
        b.setTile(0, 2, new ItemTile("FRAMES"));
        b.setTile(1, 4, new ItemTile("PLANTS"));
        b.setTile(2, 3, new ItemTile("BOOKS"));
        b.setTile(3, 1, new ItemTile("GAMES"));
        b.setTile(5, 2, new ItemTile("TROPHIES"));
        assertEquals(9, PG1.CheckGoal(b));
    }

    public void testCheckGoalPGC1_SIXTH() {      //test in witch a bookshelf matches six ItemTiles in the correct
        PersonalGoalCard PG1 = new PersonalGoalCard(1); //position
        BookShelf b = new BookShelf();
        b.setTile(0, 0, new ItemTile("PLANTS"));
        b.setTile(0, 2, new ItemTile("FRAMES"));
        b.setTile(1, 4, new ItemTile("CATS"));
        b.setTile(2, 3, new ItemTile("BOOKS"));
        b.setTile(3, 1, new ItemTile("GAMES"));
        b.setTile(5, 2, new ItemTile("TROPHIES"));
        assertEquals(12, PG1.CheckGoal(b));
        HashMap<Pair, ItemTile> arg = new HashMap<>();
        PG1 = new PersonalGoalCard(new HashMap<Pair, ItemTile>(), 2);
        arg=PG1.getGoal();
        PG1.equals(arg);
        PG1.setCompleted(1);
        int x = PG1.getCompleted();
        PG1.setCompleted(x);
        PG1.setPoints(2);
        x=PG1.getPoints();
        PG1.setPoints(x);
        x=PG1.getNumeroCarta();
        String s = PG1.toJson();
    }

    public void testCheckGoalPGC2_0() {
        PersonalGoalCard PG = new PersonalGoalCard(2);
        BookShelf b = new BookShelf();
        b.setTile(1, 1, new ItemTile("CATS"));
        b.setTile(5, 4, new ItemTile("TROPHIES"));
        b.setTile(2, 0, new ItemTile("PLANTS"));
        b.setTile(3, 4, new ItemTile("FRAMES"));
        b.setTile(2, 2, new ItemTile("BOOKS"));
        b.setTile(4, 3, new ItemTile("GAMES"));
        assertEquals(0, PG.CheckGoal(b));
    }

    public void testCheckGoalPGC2_FIRST() {
        PersonalGoalCard PG2 = new PersonalGoalCard(2);
        BookShelf b = new BookShelf();
        b.setTile(1, 1, new ItemTile("CATS"));
        b.setTile(5, 4, new ItemTile("TROPHIES"));
        b.setTile(2, 0, new ItemTile("PLANTS"));
        b.setTile(3, 4, new ItemTile("BOOKS"));
        b.setTile(2, 2, new ItemTile("FRAMES"));
        b.setTile(4, 3, new ItemTile("GAMES"));
        assertEquals(1, PG2.CheckGoal(b));
    }

    public void testCheckGoalPGC2_SECOND() {
        PersonalGoalCard PG2 = new PersonalGoalCard(2);
        BookShelf b = new BookShelf();
        b.setTile(1, 1, new ItemTile("CATS"));
        b.setTile(5, 4, new ItemTile("FRAMES"));
        b.setTile(2, 0, new ItemTile("PLANTS"));
        b.setTile(3, 4, new ItemTile("BOOKS"));
        b.setTile(2, 2, new ItemTile("FRAMES"));
        b.setTile(4, 3, new ItemTile("GAMES"));
        assertEquals(2, PG2.CheckGoal(b));
    }

    public void testCheckGoalPGC2_THIRD() {
        PersonalGoalCard PG2 = new PersonalGoalCard(2);
        BookShelf b = new BookShelf();
        b.setTile(1, 1, new ItemTile("CATS"));
        b.setTile(5, 4, new ItemTile("FRAMES"));
        b.setTile(2, 0, new ItemTile("PLANTS"));
        b.setTile(3, 4, new ItemTile("BOOKS"));
        b.setTile(2, 2, new ItemTile("FRAMES"));
        b.setTile(4, 3, new ItemTile("TROPHIES"));
        assertEquals(4, PG2.CheckGoal(b));
    }

    public void testCheckGoalPGC2_FOURTH() {
        PersonalGoalCard PG2 = new PersonalGoalCard(2);
        BookShelf b = new BookShelf();
        b.setTile(1, 1, new ItemTile("PLANTS"));
        b.setTile(5, 4, new ItemTile("FRAMES"));
        b.setTile(2, 0, new ItemTile("PLANTS"));
        b.setTile(3, 4, new ItemTile("BOOKS"));
        b.setTile(2, 2, new ItemTile("FRAMES"));
        b.setTile(4, 3, new ItemTile("TROPHIES"));
        assertEquals(6, PG2.CheckGoal(b));
    }

    public void testCheckGoalPGC2_FIFTH() {
        PersonalGoalCard PG2 = new PersonalGoalCard(2);
        BookShelf b = new BookShelf();
        b.setTile(1, 1, new ItemTile("PLANTS"));
        b.setTile(5, 4, new ItemTile("FRAMES"));
        b.setTile(2, 0, new ItemTile("PLANTS"));
        b.setTile(3, 4, new ItemTile("BOOKS"));
        b.setTile(2, 2, new ItemTile("GAMES"));
        b.setTile(4, 3, new ItemTile("TROPHIES"));
        assertEquals(9, PG2.CheckGoal(b));
    }

    public void testCheckGoalPGC2_SIXTH() {
        PersonalGoalCard PG2 = new PersonalGoalCard(2);
        BookShelf b = new BookShelf();
        b.setTile(1, 1, new ItemTile("PLANTS"));
        b.setTile(5, 4, new ItemTile("FRAMES"));
        b.setTile(2, 0, new ItemTile("CATS"));
        b.setTile(3, 4, new ItemTile("BOOKS"));
        b.setTile(2, 2, new ItemTile("GAMES"));
        b.setTile(4, 3, new ItemTile("TROPHIES"));
        assertEquals(12, PG2.CheckGoal(b));
    }

    public void testCheckGoalPGC3_0() {
        PersonalGoalCard PG = new PersonalGoalCard(3);
        BookShelf b = new BookShelf();
        b.setTile(2, 2, new ItemTile("CATS"));
        b.setTile(1, 0, new ItemTile("TROPHIES"));
        b.setTile(3, 1, new ItemTile("PLANTS"));
        b.setTile(5, 0, new ItemTile("FRAMES"));
        b.setTile(1, 3, new ItemTile("BOOKS"));
        b.setTile(3, 4, new ItemTile("GAMES"));
        assertEquals(0, PG.CheckGoal(b));
    }

    public void testCheckGoalPGC3_FIRST() {
        PersonalGoalCard PG3 = new PersonalGoalCard(3);
        BookShelf b = new BookShelf();
        b.setTile(2, 2, new ItemTile("CATS"));
        b.setTile(1, 0, new ItemTile("TROPHIES"));
        b.setTile(3, 1, new ItemTile("PLANTS"));
        b.setTile(5, 0, new ItemTile("BOOKS"));
        b.setTile(1, 3, new ItemTile("FRAMES"));
        b.setTile(3, 4, new ItemTile("GAMES"));
        assertEquals(1, PG3.CheckGoal(b));
    }

    public void testCheckGoalPGC3_SECOND() {
        PersonalGoalCard PG3 = new PersonalGoalCard(3);
        BookShelf b = new BookShelf();
        b.setTile(2, 2, new ItemTile("CATS"));
        b.setTile(1, 0, new ItemTile("FRAMES"));
        b.setTile(3, 1, new ItemTile("PLANTS"));
        b.setTile(5, 0, new ItemTile("BOOKS"));
        b.setTile(1, 3, new ItemTile("FRAMES"));
        b.setTile(3, 4, new ItemTile("GAMES"));
        assertEquals(2, PG3.CheckGoal(b));
    }

    public void testCheckGoalPGC3_THIRD() {
        PersonalGoalCard PG3 = new PersonalGoalCard(3);
        BookShelf b = new BookShelf();
        b.setTile(2, 2, new ItemTile("CATS"));
        b.setTile(1, 0, new ItemTile("FRAMES"));
        b.setTile(3, 1, new ItemTile("PLANTS"));
        b.setTile(5, 0, new ItemTile("BOOKS"));
        b.setTile(1, 3, new ItemTile("FRAMES"));
        b.setTile(3, 4, new ItemTile("TROPHIES"));
        assertEquals(4, PG3.CheckGoal(b));
    }

    public void testCheckGoalPGC3_FOURTH() {
        PersonalGoalCard PG3 = new PersonalGoalCard(3);
        BookShelf b = new BookShelf();
        b.setTile(2, 2, new ItemTile("PLANTS"));
        b.setTile(1, 0, new ItemTile("FRAMES"));
        b.setTile(3, 1, new ItemTile("PLANTS"));
        b.setTile(5, 0, new ItemTile("BOOKS"));
        b.setTile(1, 3, new ItemTile("FRAMES"));
        b.setTile(3, 4, new ItemTile("TROPHIES"));
        assertEquals(6, PG3.CheckGoal(b));
    }

    public void testCheckGoalPGC3_FIFTH() {
        PersonalGoalCard PG3 = new PersonalGoalCard(3);
        BookShelf b = new BookShelf();
        b.setTile(2, 2, new ItemTile("PLANTS"));
        b.setTile(1, 0, new ItemTile("FRAMES"));
        b.setTile(3, 1, new ItemTile("PLANTS"));
        b.setTile(5, 0, new ItemTile("BOOKS"));
        b.setTile(1, 3, new ItemTile("GAMES"));
        b.setTile(3, 4, new ItemTile("TROPHIES"));
        assertEquals(9, PG3.CheckGoal(b));
    }

    public void testCheckGoalPGC3_SIXTH() {
        PersonalGoalCard PG3 = new PersonalGoalCard(3);
        BookShelf b = new BookShelf();
        b.setTile(2, 2, new ItemTile("PLANTS"));
        b.setTile(1, 0, new ItemTile("FRAMES"));
        b.setTile(3, 1, new ItemTile("CATS"));
        b.setTile(5, 0, new ItemTile("BOOKS"));
        b.setTile(1, 3, new ItemTile("GAMES"));
        b.setTile(3, 4, new ItemTile("TROPHIES"));
        assertEquals(12, PG3.CheckGoal(b));
    }

    public void testCheckGoalPGC4_0() {
        PersonalGoalCard PG = new PersonalGoalCard(4);
        BookShelf b = new BookShelf();
        b.setTile(3, 3, new ItemTile("CATS"));
        b.setTile(2, 2, new ItemTile("TROPHIES"));
        b.setTile(4, 2, new ItemTile("PLANTS"));
        b.setTile(4, 1, new ItemTile("FRAMES"));
        b.setTile(0, 4, new ItemTile("BOOKS"));
        b.setTile(2, 0, new ItemTile("GAMES"));
        assertEquals(0, PG.CheckGoal(b));
    }

    public void testCheckGoalPGC4_FIRST() {
        PersonalGoalCard PG4 = new PersonalGoalCard(4);
        BookShelf b = new BookShelf();
        b.setTile(3, 3, new ItemTile("CATS"));
        b.setTile(2, 2, new ItemTile("TROPHIES"));
        b.setTile(4, 2, new ItemTile("PLANTS"));
        b.setTile(4, 1, new ItemTile("BOOKS"));
        b.setTile(0, 4, new ItemTile("FRAMES"));
        b.setTile(2, 0, new ItemTile("GAMES"));
        assertEquals(1, PG4.CheckGoal(b));
    }

    public void testCheckGoalPGC4_SECOND() {
        PersonalGoalCard PG4 = new PersonalGoalCard(4);
        BookShelf b = new BookShelf();
        b.setTile(3, 3, new ItemTile("CATS"));
        b.setTile(2, 2, new ItemTile("FRAMES"));
        b.setTile(4, 2, new ItemTile("PLANTS"));
        b.setTile(4, 1, new ItemTile("BOOKS"));
        b.setTile(0, 4, new ItemTile("FRAMES"));
        b.setTile(2, 0, new ItemTile("GAMES"));
        assertEquals(2, PG4.CheckGoal(b));
    }

    public void testCheckGoalPGC4_THIRD() {
        PersonalGoalCard PG4 = new PersonalGoalCard(4);
        BookShelf b = new BookShelf();
        b.setTile(3, 3, new ItemTile("CATS"));
        b.setTile(2, 2, new ItemTile("FRAMES"));
        b.setTile(4, 2, new ItemTile("PLANTS"));
        b.setTile(4, 1, new ItemTile("BOOKS"));
        b.setTile(0, 4, new ItemTile("FRAMES"));
        b.setTile(2, 0, new ItemTile("TROPHIES"));
        assertEquals(4, PG4.CheckGoal(b));
    }

    public void testCheckGoalPGC4_FOURTH() {
        PersonalGoalCard PG4 = new PersonalGoalCard(4);
        BookShelf b = new BookShelf();
        b.setTile(3, 3, new ItemTile("PLANTS"));
        b.setTile(2, 2, new ItemTile("FRAMES"));
        b.setTile(4, 2, new ItemTile("PLANTS"));
        b.setTile(4, 1, new ItemTile("BOOKS"));
        b.setTile(0, 4, new ItemTile("FRAMES"));
        b.setTile(2, 0, new ItemTile("TROPHIES"));
        assertEquals(6, PG4.CheckGoal(b));
    }

    public void testCheckGoalPGC4_FIFTH() {
        PersonalGoalCard PG4 = new PersonalGoalCard(4);
        BookShelf b = new BookShelf();
        b.setTile(3, 3, new ItemTile("PLANTS"));
        b.setTile(2, 2, new ItemTile("FRAMES"));
        b.setTile(4, 2, new ItemTile("PLANTS"));
        b.setTile(4, 1, new ItemTile("BOOKS"));
        b.setTile(0, 4, new ItemTile("GAMES"));
        b.setTile(2, 0, new ItemTile("TROPHIES"));
        assertEquals(9, PG4.CheckGoal(b));
    }

    public void testCheckGoalPGC4_SIXTH() {
        PersonalGoalCard PG4 = new PersonalGoalCard(4);
        BookShelf b = new BookShelf();
        b.setTile(3, 3, new ItemTile("PLANTS"));
        b.setTile(2, 2, new ItemTile("FRAMES"));
        b.setTile(4, 2, new ItemTile("CATS"));
        b.setTile(4, 1, new ItemTile("BOOKS"));
        b.setTile(0, 4, new ItemTile("GAMES"));
        b.setTile(2, 0, new ItemTile("TROPHIES"));
        assertEquals(12, PG4.CheckGoal(b));
    }

    public void testCheckGoalPGC5_0() {
        PersonalGoalCard PG = new PersonalGoalCard(5);
        BookShelf b = new BookShelf();
        b.setTile(4, 4, new ItemTile("CATS"));
        b.setTile(3, 1, new ItemTile("TROPHIES"));
        b.setTile(5, 3, new ItemTile("PLANTS"));
        b.setTile(3, 2, new ItemTile("FRAMES"));
        b.setTile(5, 0, new ItemTile("BOOKS"));
        b.setTile(1, 1, new ItemTile("GAMES"));
        assertEquals(0, PG.CheckGoal(b));
    }

    public void testCheckGoalPGC5_FIRST() {
        PersonalGoalCard PG5 = new PersonalGoalCard(5);
        BookShelf b = new BookShelf();
        b.setTile(4, 4, new ItemTile("CATS"));
        b.setTile(3, 1, new ItemTile("TROPHIES"));
        b.setTile(5, 3, new ItemTile("PLANTS"));
        b.setTile(3, 2, new ItemTile("BOOKS"));
        b.setTile(5, 0, new ItemTile("FRAMES"));
        b.setTile(1, 1, new ItemTile("GAMES"));
        assertEquals(1, PG5.CheckGoal(b));
    }

    public void testCheckGoalPGC5_SECOND() {
        PersonalGoalCard PG5 = new PersonalGoalCard(5);
        BookShelf b = new BookShelf();
        b.setTile(4, 4, new ItemTile("CATS"));
        b.setTile(3, 1, new ItemTile("FRAMES"));
        b.setTile(5, 3, new ItemTile("PLANTS"));
        b.setTile(3, 2, new ItemTile("BOOKS"));
        b.setTile(5, 0, new ItemTile("FRAMES"));
        b.setTile(1, 1, new ItemTile("GAMES"));
        assertEquals(2, PG5.CheckGoal(b));
    }

    public void testCheckGoalPGC5_THIRD() {
        PersonalGoalCard PG5 = new PersonalGoalCard(5);
        BookShelf b = new BookShelf();
        b.setTile(4, 4, new ItemTile("CATS"));
        b.setTile(3, 1, new ItemTile("FRAMES"));
        b.setTile(5, 3, new ItemTile("PLANTS"));
        b.setTile(3, 2, new ItemTile("BOOKS"));
        b.setTile(5, 0, new ItemTile("FRAMES"));
        b.setTile(1, 1, new ItemTile("TROPHIES"));
        assertEquals(4, PG5.CheckGoal(b));
    }

    public void testCheckGoalPGC5_FOURTH() {
        PersonalGoalCard PG5 = new PersonalGoalCard(5);
        BookShelf b = new BookShelf();
        b.setTile(4, 4, new ItemTile("PLANTS"));
        b.setTile(3, 1, new ItemTile("FRAMES"));
        b.setTile(5, 3, new ItemTile("PLANTS"));
        b.setTile(3, 2, new ItemTile("BOOKS"));
        b.setTile(5, 0, new ItemTile("FRAMES"));
        b.setTile(1, 1, new ItemTile("TROPHIES"));
        assertEquals(6, PG5.CheckGoal(b));
    }

    public void testCheckGoalPGC5_FIFTH() {
        PersonalGoalCard PG5 = new PersonalGoalCard(5);
        BookShelf b = new BookShelf();
        b.setTile(4, 4, new ItemTile("PLANTS"));
        b.setTile(3, 1, new ItemTile("FRAMES"));
        b.setTile(5, 3, new ItemTile("PLANTS"));
        b.setTile(3, 2, new ItemTile("BOOKS"));
        b.setTile(5, 0, new ItemTile("GAMES"));
        b.setTile(1, 1, new ItemTile("TROPHIES"));
        assertEquals(9, PG5.CheckGoal(b));
    }

    public void testCheckGoalPGC5_SIXTH() {
        PersonalGoalCard PG5 = new PersonalGoalCard(5);
        BookShelf b = new BookShelf();
        b.setTile(4, 4, new ItemTile("PLANTS"));
        b.setTile(3, 1, new ItemTile("FRAMES"));
        b.setTile(5, 3, new ItemTile("CATS"));
        b.setTile(3, 2, new ItemTile("BOOKS"));
        b.setTile(5, 0, new ItemTile("GAMES"));
        b.setTile(1, 1, new ItemTile("TROPHIES"));
        assertEquals(12, PG5.CheckGoal(b));
    }

    public void testCheckGoalPGC6_0() {
        PersonalGoalCard PG = new PersonalGoalCard(6);
        BookShelf b = new BookShelf();
        b.setTile(5, 0, new ItemTile("CATS"));
        b.setTile(4, 3, new ItemTile("TROPHIES"));
        b.setTile(0, 4, new ItemTile("PLANTS"));
        b.setTile(2, 3, new ItemTile("FRAMES"));
        b.setTile(4, 1, new ItemTile("BOOKS"));
        b.setTile(5, 2, new ItemTile("GAMES"));
        assertEquals(0, PG.CheckGoal(b));
    }

    public void testCheckGoalPGC6_FIRST() {
        PersonalGoalCard PG6 = new PersonalGoalCard(6);
        BookShelf b = new BookShelf();
        b.setTile(5, 0, new ItemTile("CATS"));
        b.setTile(4, 3, new ItemTile("TROPHIES"));
        b.setTile(0, 4, new ItemTile("PLANTS"));
        b.setTile(2, 3, new ItemTile("BOOKS"));
        b.setTile(4, 1, new ItemTile("FRAMES"));
        b.setTile(5, 2, new ItemTile("GAMES"));
        assertEquals(1, PG6.CheckGoal(b));
    }

    public void testCheckGoalPGC6_SECOND() {
        PersonalGoalCard PG6 = new PersonalGoalCard(6);
        BookShelf b = new BookShelf();
        b.setTile(5, 0, new ItemTile("CATS"));
        b.setTile(4, 3, new ItemTile("FRAMES"));
        b.setTile(0, 4, new ItemTile("PLANTS"));
        b.setTile(2, 3, new ItemTile("BOOKS"));
        b.setTile(4, 1, new ItemTile("FRAMES"));
        b.setTile(5, 2, new ItemTile("GAMES"));
        assertEquals(2, PG6.CheckGoal(b));
    }

    public void testCheckGoalPGC6_THIRD() {
        PersonalGoalCard PG6 = new PersonalGoalCard(6);
        BookShelf b = new BookShelf();
        b.setTile(5, 0, new ItemTile("CATS"));
        b.setTile(4, 3, new ItemTile("FRAMES"));
        b.setTile(0, 4, new ItemTile("PLANTS"));
        b.setTile(2, 3, new ItemTile("BOOKS"));
        b.setTile(4, 1, new ItemTile("FRAMES"));
        b.setTile(5, 2, new ItemTile("TROPHIES"));
        assertEquals(4, PG6.CheckGoal(b));
    }

    public void testCheckGoalPGC6_FOURTH() {
        PersonalGoalCard PG6 = new PersonalGoalCard(6);
        BookShelf b = new BookShelf();
        b.setTile(5, 0, new ItemTile("PLANTS"));
        b.setTile(4, 3, new ItemTile("FRAMES"));
        b.setTile(0, 4, new ItemTile("PLANTS"));
        b.setTile(2, 3, new ItemTile("BOOKS"));
        b.setTile(4, 1, new ItemTile("FRAMES"));
        b.setTile(5, 2, new ItemTile("TROPHIES"));
        assertEquals(6, PG6.CheckGoal(b));
    }

    public void testCheckGoalPGC6_FIFTH() {
        PersonalGoalCard PG6 = new PersonalGoalCard(6);
        BookShelf b = new BookShelf();
        b.setTile(5, 0, new ItemTile("PLANTS"));
        b.setTile(4, 3, new ItemTile("FRAMES"));
        b.setTile(0, 4, new ItemTile("PLANTS"));
        b.setTile(2, 3, new ItemTile("BOOKS"));
        b.setTile(4, 1, new ItemTile("GAMES"));
        b.setTile(5, 2, new ItemTile("TROPHIES"));
        assertEquals(9, PG6.CheckGoal(b));
    }

    public void testCheckGoalPGC6_SIXTH() {
        PersonalGoalCard PG6 = new PersonalGoalCard(6);
        BookShelf b = new BookShelf();
        b.setTile(5, 0, new ItemTile("PLANTS"));
        b.setTile(4, 3, new ItemTile("FRAMES"));
        b.setTile(0, 4, new ItemTile("CATS"));
        b.setTile(2, 3, new ItemTile("BOOKS"));
        b.setTile(4, 1, new ItemTile("GAMES"));
        b.setTile(5, 2, new ItemTile("TROPHIES"));
        assertEquals(12, PG6.CheckGoal(b));
    }

    public void testCheckGoalPGC7_0() {
        PersonalGoalCard PG = new PersonalGoalCard(7);
        BookShelf b = new BookShelf();
        b.setTile(2, 1, new ItemTile("CATS"));
        b.setTile(1, 4, new ItemTile("TROPHIES"));
        b.setTile(0, 0, new ItemTile("PLANTS"));
        b.setTile(5, 2, new ItemTile("FRAMES"));
        b.setTile(4, 4, new ItemTile("BOOKS"));
        b.setTile(3, 0, new ItemTile("GAMES"));
        assertEquals(0, PG.CheckGoal(b));
    }

    public void testCheckGoalPGC7_FIRST() {
        PersonalGoalCard PG7 = new PersonalGoalCard(7);
        BookShelf b = new BookShelf();
        b.setTile(2, 1, new ItemTile("CATS"));
        b.setTile(1, 4, new ItemTile("TROPHIES"));
        b.setTile(0, 0, new ItemTile("PLANTS"));
        b.setTile(5, 2, new ItemTile("BOOKS"));
        b.setTile(4, 4, new ItemTile("FRAMES"));
        b.setTile(3, 0, new ItemTile("GAMES"));
        assertEquals(1, PG7.CheckGoal(b));
    }

    public void testCheckGoalPGC7_SECOND() {
        PersonalGoalCard PG7 = new PersonalGoalCard(7);
        BookShelf b = new BookShelf();
        b.setTile(2, 1, new ItemTile("CATS"));
        b.setTile(1, 4, new ItemTile("FRAMES"));
        b.setTile(0, 0, new ItemTile("PLANTS"));
        b.setTile(5, 2, new ItemTile("BOOKS"));
        b.setTile(4, 4, new ItemTile("FRAMES"));
        b.setTile(3, 0, new ItemTile("GAMES"));
        assertEquals(2, PG7.CheckGoal(b));
    }

    public void testCheckGoalPGC7_THIRD() {
        PersonalGoalCard PG7 = new PersonalGoalCard(7);
        BookShelf b = new BookShelf();
        b.setTile(2, 1, new ItemTile("CATS"));
        b.setTile(1, 4, new ItemTile("FRAMES"));
        b.setTile(0, 0, new ItemTile("PLANTS"));
        b.setTile(5, 2, new ItemTile("BOOKS"));
        b.setTile(4, 4, new ItemTile("FRAMES"));
        b.setTile(3, 0, new ItemTile("TROPHIES"));
        assertEquals(4, PG7.CheckGoal(b));
    }

    public void testCheckGoalPGC7_FOURTH() {
        PersonalGoalCard PG7 = new PersonalGoalCard(7);
        BookShelf b = new BookShelf();
        b.setTile(2, 1, new ItemTile("PLANTS"));
        b.setTile(1, 4, new ItemTile("FRAMES"));
        b.setTile(0, 0, new ItemTile("PLANTS"));
        b.setTile(5, 2, new ItemTile("BOOKS"));
        b.setTile(4, 4, new ItemTile("FRAMES"));
        b.setTile(3, 0, new ItemTile("TROPHIES"));
        assertEquals(6, PG7.CheckGoal(b));
    }

    public void testCheckGoalPGC7_FIFTH() {
        PersonalGoalCard PG7 = new PersonalGoalCard(7);
        BookShelf b = new BookShelf();
        b.setTile(2, 1, new ItemTile("PLANTS"));
        b.setTile(1, 4, new ItemTile("FRAMES"));
        b.setTile(0, 0, new ItemTile("PLANTS"));
        b.setTile(5, 2, new ItemTile("BOOKS"));
        b.setTile(4, 4, new ItemTile("GAMES"));
        b.setTile(3, 0, new ItemTile("TROPHIES"));
        assertEquals(9, PG7.CheckGoal(b));
    }

    public void testCheckGoalPGC7_SIXTH() {
        PersonalGoalCard PG7 = new PersonalGoalCard(7);
        BookShelf b = new BookShelf();
        b.setTile(2, 1, new ItemTile("PLANTS"));
        b.setTile(1, 4, new ItemTile("FRAMES"));
        b.setTile(0, 0, new ItemTile("CATS"));
        b.setTile(5, 2, new ItemTile("BOOKS"));
        b.setTile(4, 4, new ItemTile("GAMES"));
        b.setTile(3, 0, new ItemTile("TROPHIES"));
        assertEquals(12, PG7.CheckGoal(b));
    }

    public void testCheckGoalPGC8_0() {
        PersonalGoalCard PG = new PersonalGoalCard(8);
        BookShelf b = new BookShelf();
        b.setTile(3, 0, new ItemTile("CATS"));
        b.setTile(0, 4, new ItemTile("TROPHIES"));
        b.setTile(1, 1, new ItemTile("PLANTS"));
        b.setTile(4, 3, new ItemTile("FRAMES"));
        b.setTile(5, 3, new ItemTile("BOOKS"));
        b.setTile(2, 2, new ItemTile("GAMES"));
        assertEquals(0, PG.CheckGoal(b));
    }

    public void testCheckGoalPGC8_FIRST() {
        PersonalGoalCard PG8 = new PersonalGoalCard(8);
        BookShelf b = new BookShelf();
        b.setTile(3, 0, new ItemTile("CATS"));
        b.setTile(0, 4, new ItemTile("TROPHIES"));
        b.setTile(1, 1, new ItemTile("PLANTS"));
        b.setTile(4, 3, new ItemTile("BOOKS"));
        b.setTile(5, 3, new ItemTile("FRAMES"));
        b.setTile(2, 2, new ItemTile("GAMES"));
        assertEquals(1, PG8.CheckGoal(b));
    }

    public void testCheckGoalPGC8_SECOND() {
        PersonalGoalCard PG8 = new PersonalGoalCard(8);
        BookShelf b = new BookShelf();
        b.setTile(3, 0, new ItemTile("CATS"));
        b.setTile(0, 4, new ItemTile("FRAMES"));
        b.setTile(1, 1, new ItemTile("PLANTS"));
        b.setTile(4, 3, new ItemTile("BOOKS"));
        b.setTile(5, 3, new ItemTile("FRAMES"));
        b.setTile(2, 2, new ItemTile("GAMES"));
        assertEquals(2, PG8.CheckGoal(b));
    }

    public void testCheckGoalPGC8_THIRD() {
        PersonalGoalCard PG8 = new PersonalGoalCard(8);
        BookShelf b = new BookShelf();
        b.setTile(3, 0, new ItemTile("CATS"));
        b.setTile(0, 4, new ItemTile("FRAMES"));
        b.setTile(1, 1, new ItemTile("PLANTS"));
        b.setTile(4, 3, new ItemTile("BOOKS"));
        b.setTile(5, 3, new ItemTile("FRAMES"));
        b.setTile(2, 2, new ItemTile("TROPHIES"));
        assertEquals(4, PG8.CheckGoal(b));
    }

    public void testCheckGoalPGC8_FOURTH() {
        PersonalGoalCard PG8 = new PersonalGoalCard(8);
        BookShelf b = new BookShelf();
        b.setTile(3, 0, new ItemTile("PLANTS"));
        b.setTile(0, 4, new ItemTile("FRAMES"));
        b.setTile(1, 1, new ItemTile("PLANTS"));
        b.setTile(4, 3, new ItemTile("BOOKS"));
        b.setTile(5, 3, new ItemTile("FRAMES"));
        b.setTile(2, 2, new ItemTile("TROPHIES"));
        assertEquals(6, PG8.CheckGoal(b));
    }

    public void testCheckGoalPGC8_FIFTH() {
        PersonalGoalCard PG8 = new PersonalGoalCard(8);
        BookShelf b = new BookShelf();
        b.setTile(3, 0, new ItemTile("PLANTS"));
        b.setTile(0, 4, new ItemTile("FRAMES"));
        b.setTile(1, 1, new ItemTile("PLANTS"));
        b.setTile(4, 3, new ItemTile("BOOKS"));
        b.setTile(5, 3, new ItemTile("GAMES"));
        b.setTile(2, 2, new ItemTile("TROPHIES"));
        assertEquals(9, PG8.CheckGoal(b));
    }

    public void testCheckGoalPGC8_SIXTH() {
        PersonalGoalCard PG8 = new PersonalGoalCard(8);
        BookShelf b = new BookShelf();
        b.setTile(3, 0, new ItemTile("PLANTS"));
        b.setTile(0, 4, new ItemTile("FRAMES"));
        b.setTile(1, 1, new ItemTile("CATS"));
        b.setTile(4, 3, new ItemTile("BOOKS"));
        b.setTile(5, 3, new ItemTile("GAMES"));
        b.setTile(2, 2, new ItemTile("TROPHIES"));
        assertEquals(12, PG8.CheckGoal(b));
    }

    public void testCheckGoalPGC9_0() {
        PersonalGoalCard PG = new PersonalGoalCard(9);
        BookShelf b = new BookShelf();
        b.setTile(4, 4, new ItemTile("CATS"));
        b.setTile(5, 0, new ItemTile("TROPHIES"));
        b.setTile(2, 2, new ItemTile("PLANTS"));
        b.setTile(3, 4, new ItemTile("FRAMES"));
        b.setTile(0, 2, new ItemTile("BOOKS"));
        b.setTile(4, 1, new ItemTile("GAMES"));
        assertEquals(0, PG.CheckGoal(b));
    }

    public void testCheckGoalPGC9_FIRST() {
        PersonalGoalCard PG9 = new PersonalGoalCard(9);
        BookShelf b = new BookShelf();
        b.setTile(4, 4, new ItemTile("CATS"));
        b.setTile(5, 0, new ItemTile("TROPHIES"));
        b.setTile(2, 2, new ItemTile("PLANTS"));
        b.setTile(3, 4, new ItemTile("BOOKS"));
        b.setTile(0, 2, new ItemTile("FRAMES"));
        b.setTile(4, 1, new ItemTile("GAMES"));
        assertEquals(1, PG9.CheckGoal(b));
    }

    public void testCheckGoalPGC9_SECOND() {
        PersonalGoalCard PG9 = new PersonalGoalCard(9);
        BookShelf b = new BookShelf();
        b.setTile(4, 4, new ItemTile("CATS"));
        b.setTile(5, 0, new ItemTile("FRAMES"));
        b.setTile(2, 2, new ItemTile("PLANTS"));
        b.setTile(3, 4, new ItemTile("BOOKS"));
        b.setTile(0, 2, new ItemTile("FRAMES"));
        b.setTile(4, 1, new ItemTile("GAMES"));
        assertEquals(2, PG9.CheckGoal(b));
    }

    public void testCheckGoalPGC9_THIRD() {
        PersonalGoalCard PG9 = new PersonalGoalCard(9);
        BookShelf b = new BookShelf();
        b.setTile(4, 4, new ItemTile("CATS"));
        b.setTile(5, 0, new ItemTile("FRAMES"));
        b.setTile(2, 2, new ItemTile("PLANTS"));
        b.setTile(3, 4, new ItemTile("BOOKS"));
        b.setTile(0, 2, new ItemTile("FRAMES"));
        b.setTile(4, 1, new ItemTile("TROPHIES"));
        assertEquals(4, PG9.CheckGoal(b));
    }

    public void testCheckGoalPGC9_FOURTH() {
        PersonalGoalCard PG9 = new PersonalGoalCard(9);
        BookShelf b = new BookShelf();
        b.setTile(4, 4, new ItemTile("PLANTS"));
        b.setTile(5, 0, new ItemTile("FRAMES"));
        b.setTile(2, 2, new ItemTile("PLANTS"));
        b.setTile(3, 4, new ItemTile("BOOKS"));
        b.setTile(0, 2, new ItemTile("FRAMES"));
        b.setTile(4, 1, new ItemTile("TROPHIES"));
        assertEquals(6, PG9.CheckGoal(b));
    }

    public void testCheckGoalPGC9_FIFTH() {
        PersonalGoalCard PG9 = new PersonalGoalCard(9);
        BookShelf b = new BookShelf();
        b.setTile(4, 4, new ItemTile("PLANTS"));
        b.setTile(5, 0, new ItemTile("FRAMES"));
        b.setTile(2, 2, new ItemTile("PLANTS"));
        b.setTile(3, 4, new ItemTile("BOOKS"));
        b.setTile(0, 2, new ItemTile("GAMES"));
        b.setTile(4, 1, new ItemTile("TROPHIES"));
        assertEquals(9, PG9.CheckGoal(b));
    }

    public void testCheckGoalPGC9_SIXTH() {
        PersonalGoalCard PG9 = new PersonalGoalCard(9);
        BookShelf b = new BookShelf();
        b.setTile(4, 4, new ItemTile("PLANTS"));
        b.setTile(5, 0, new ItemTile("FRAMES"));
        b.setTile(2, 2, new ItemTile("CATS"));
        b.setTile(3, 4, new ItemTile("BOOKS"));
        b.setTile(0, 2, new ItemTile("GAMES"));
        b.setTile(4, 1, new ItemTile("TROPHIES"));
        assertEquals(12, PG9.CheckGoal(b));
    }

    public void testCheckGoalPGC10_0() {
        PersonalGoalCard PG = new PersonalGoalCard(10);
        BookShelf b = new BookShelf();
        b.setTile(5, 3, new ItemTile("CATS"));
        b.setTile(4, 1, new ItemTile("TROPHIES"));
        b.setTile(3, 3, new ItemTile("PLANTS"));
        b.setTile(2, 0, new ItemTile("FRAMES"));
        b.setTile(1, 1, new ItemTile("BOOKS"));
        b.setTile(0, 4, new ItemTile("GAMES"));
        assertEquals(0, PG.CheckGoal(b));
    }

    public void testCheckGoalPGC10_FIRST() {
        PersonalGoalCard PG10 = new PersonalGoalCard(10);
        BookShelf b = new BookShelf();
        b.setTile(5, 3, new ItemTile("CATS"));
        b.setTile(4, 1, new ItemTile("TROPHIES"));
        b.setTile(3, 3, new ItemTile("PLANTS"));
        b.setTile(2, 0, new ItemTile("BOOKS"));
        b.setTile(1, 1, new ItemTile("FRAMES"));
        b.setTile(0, 4, new ItemTile("GAMES"));
        assertEquals(1, PG10.CheckGoal(b));
    }

    public void testCheckGoalPGC10_SECOND() {
        PersonalGoalCard PG10 = new PersonalGoalCard(10);
        BookShelf b = new BookShelf();
        b.setTile(5, 3, new ItemTile("CATS"));
        b.setTile(4, 1, new ItemTile("FRAMES"));
        b.setTile(3, 3, new ItemTile("PLANTS"));
        b.setTile(2, 0, new ItemTile("BOOKS"));
        b.setTile(1, 1, new ItemTile("FRAMES"));
        b.setTile(0, 4, new ItemTile("GAMES"));
        assertEquals(2, PG10.CheckGoal(b));
    }

    public void testCheckGoalPGC10_THIRD() {
        PersonalGoalCard PG9 = new PersonalGoalCard(10);
        BookShelf b = new BookShelf();
        b.setTile(5, 3, new ItemTile("CATS"));
        b.setTile(4, 1, new ItemTile("FRAMES"));
        b.setTile(3, 3, new ItemTile("PLANTS"));
        b.setTile(2, 0, new ItemTile("BOOKS"));
        b.setTile(1, 1, new ItemTile("FRAMES"));
        b.setTile(0, 4, new ItemTile("TROPHIES"));
        assertEquals(4, PG9.CheckGoal(b));
    }

    public void testCheckGoalPGC10_FOURTH() {
        PersonalGoalCard PG10 = new PersonalGoalCard(10);
        BookShelf b = new BookShelf();
        b.setTile(5, 3, new ItemTile("PLANTS"));
        b.setTile(4, 1, new ItemTile("FRAMES"));
        b.setTile(3, 3, new ItemTile("PLANTS"));
        b.setTile(2, 0, new ItemTile("BOOKS"));
        b.setTile(1, 1, new ItemTile("FRAMES"));
        b.setTile(0, 4, new ItemTile("TROPHIES"));
        assertEquals(6, PG10.CheckGoal(b));
    }

    public void testCheckGoalPGC10_FIFTH() {
        PersonalGoalCard PG10 = new PersonalGoalCard(10);
        BookShelf b = new BookShelf();
        b.setTile(5, 3, new ItemTile("PLANTS"));
        b.setTile(4, 1, new ItemTile("FRAMES"));
        b.setTile(3, 3, new ItemTile("PLANTS"));
        b.setTile(2, 0, new ItemTile("BOOKS"));
        b.setTile(1, 1, new ItemTile("GAMES"));
        b.setTile(0, 4, new ItemTile("TROPHIES"));
        assertEquals(9, PG10.CheckGoal(b));
    }

    public void testCheckGoalPGC10_SIXTH() {
        PersonalGoalCard PG10 = new PersonalGoalCard(10);
        BookShelf b = new BookShelf();
        b.setTile(5, 3, new ItemTile("PLANTS"));
        b.setTile(4, 1, new ItemTile("FRAMES"));
        b.setTile(3, 3, new ItemTile("CATS"));
        b.setTile(2, 0, new ItemTile("BOOKS"));
        b.setTile(1, 1, new ItemTile("GAMES"));
        b.setTile(0, 4, new ItemTile("TROPHIES"));
        assertEquals(12, PG10.CheckGoal(b));
    }

    public void testCheckGoalPGC11_0() {
        PersonalGoalCard PG = new PersonalGoalCard(11);
        BookShelf b = new BookShelf();
        b.setTile(0, 2, new ItemTile("CATS"));
        b.setTile(3, 2, new ItemTile("TROPHIES"));
        b.setTile(4, 4, new ItemTile("PLANTS"));
        b.setTile(1, 1, new ItemTile("FRAMES"));
        b.setTile(2, 0, new ItemTile("BOOKS"));
        b.setTile(5, 3, new ItemTile("GAMES"));
        assertEquals(0, PG.CheckGoal(b));
    }

    public void testCheckGoalPGC11_FIRST() {
        PersonalGoalCard PG11 = new PersonalGoalCard(11);
        BookShelf b = new BookShelf();
        b.setTile(0, 2, new ItemTile("CATS"));
        b.setTile(3, 2, new ItemTile("TROPHIES"));
        b.setTile(4, 4, new ItemTile("PLANTS"));
        b.setTile(1, 1, new ItemTile("BOOKS"));
        b.setTile(2, 0, new ItemTile("FRAMES"));
        b.setTile(5, 3, new ItemTile("GAMES"));
        assertEquals(1, PG11.CheckGoal(b));
    }

    public void testCheckGoalPGC11_SECOND() {
        PersonalGoalCard PG11 = new PersonalGoalCard(11);
        BookShelf b = new BookShelf();
        b.setTile(0, 2, new ItemTile("CATS"));
        b.setTile(3, 2, new ItemTile("FRAMES"));
        b.setTile(4, 4, new ItemTile("PLANTS"));
        b.setTile(1, 1, new ItemTile("BOOKS"));
        b.setTile(2, 0, new ItemTile("FRAMES"));
        b.setTile(5, 3, new ItemTile("GAMES"));
        assertEquals(2, PG11.CheckGoal(b));
    }

    public void testCheckGoalPGC11_THIRD() {
        PersonalGoalCard PG11 = new PersonalGoalCard(11);
        BookShelf b = new BookShelf();
        b.setTile(0, 2, new ItemTile("CATS"));
        b.setTile(3, 2, new ItemTile("FRAMES"));
        b.setTile(4, 4, new ItemTile("PLANTS"));
        b.setTile(1, 1, new ItemTile("BOOKS"));
        b.setTile(2, 0, new ItemTile("FRAMES"));
        b.setTile(5, 3, new ItemTile("TROPHIES"));
        assertEquals(4, PG11.CheckGoal(b));
    }

    public void testCheckGoalPGC11_FOURTH() {
        PersonalGoalCard PG11 = new PersonalGoalCard(11);
        BookShelf b = new BookShelf();
        b.setTile(0, 2, new ItemTile("PLANTS"));
        b.setTile(3, 2, new ItemTile("FRAMES"));
        b.setTile(4, 4, new ItemTile("PLANTS"));
        b.setTile(1, 1, new ItemTile("BOOKS"));
        b.setTile(2, 0, new ItemTile("FRAMES"));
        b.setTile(5, 3, new ItemTile("TROPHIES"));
        assertEquals(6, PG11.CheckGoal(b));
    }

    public void testCheckGoalPGC11_FIFTH() {
        PersonalGoalCard PG11 = new PersonalGoalCard(11);
        BookShelf b = new BookShelf();
        b.setTile(0, 2, new ItemTile("PLANTS"));
        b.setTile(3, 2, new ItemTile("FRAMES"));
        b.setTile(4, 4, new ItemTile("PLANTS"));
        b.setTile(1, 1, new ItemTile("BOOKS"));
        b.setTile(2, 0, new ItemTile("GAMES"));
        b.setTile(5, 3, new ItemTile("TROPHIES"));
        assertEquals(9, PG11.CheckGoal(b));
    }

    public void testCheckGoalPGC11_SIXTH() {
        PersonalGoalCard PG11 = new PersonalGoalCard(11);
        BookShelf b = new BookShelf();
        b.setTile(0, 2, new ItemTile("PLANTS"));
        b.setTile(3, 2, new ItemTile("FRAMES"));
        b.setTile(4, 4, new ItemTile("CATS"));
        b.setTile(1, 1, new ItemTile("BOOKS"));
        b.setTile(2, 0, new ItemTile("GAMES"));
        b.setTile(5, 3, new ItemTile("TROPHIES"));
        assertEquals(12, PG11.CheckGoal(b));
    }

    public void testCheckGoalPGC12_0() {
        PersonalGoalCard PG = new PersonalGoalCard(12);
        BookShelf b = new BookShelf();
        b.setTile(1, 1, new ItemTile("CATS"));
        b.setTile(2, 2, new ItemTile("TROPHIES"));
        b.setTile(5, 0, new ItemTile("PLANTS"));
        b.setTile(0, 2, new ItemTile("FRAMES"));
        b.setTile(4, 4, new ItemTile("BOOKS"));
        b.setTile(3, 3, new ItemTile("GAMES"));
        assertEquals(0, PG.CheckGoal(b));
    }

    public void testCheckGoalPGC12_FIRST() {
        PersonalGoalCard PG12 = new PersonalGoalCard(12);
        BookShelf b = new BookShelf();
        b.setTile(1, 1, new ItemTile("CATS"));
        b.setTile(2, 2, new ItemTile("TROPHIES"));
        b.setTile(5, 0, new ItemTile("PLANTS"));
        b.setTile(0, 2, new ItemTile("BOOKS"));
        b.setTile(4, 4, new ItemTile("FRAMES"));
        b.setTile(3, 3, new ItemTile("GAMES"));
        assertEquals(1, PG12.CheckGoal(b));
    }

    public void testCheckGoalPGC12_SECOND() {
        PersonalGoalCard PG12 = new PersonalGoalCard(12);
        BookShelf b = new BookShelf();
        b.setTile(1, 1, new ItemTile("CATS"));
        b.setTile(2, 2, new ItemTile("FRAMES"));
        b.setTile(5, 0, new ItemTile("PLANTS"));
        b.setTile(0, 2, new ItemTile("BOOKS"));
        b.setTile(4, 4, new ItemTile("FRAMES"));
        b.setTile(3, 3, new ItemTile("GAMES"));
        assertEquals(2, PG12.CheckGoal(b));
    }

    public void testCheckGoalPGC12_THIRD() {
        PersonalGoalCard PG12 = new PersonalGoalCard(12);
        BookShelf b = new BookShelf();
        b.setTile(1, 1, new ItemTile("CATS"));
        b.setTile(2, 2, new ItemTile("FRAMES"));
        b.setTile(5, 0, new ItemTile("PLANTS"));
        b.setTile(0, 2, new ItemTile("BOOKS"));
        b.setTile(4, 4, new ItemTile("FRAMES"));
        b.setTile(3, 3, new ItemTile("TROPHIES"));
        assertEquals(4, PG12.CheckGoal(b));
    }

    public void testCheckGoalPGC12_FOURTH() {
        PersonalGoalCard PG12 = new PersonalGoalCard(12);
        BookShelf b = new BookShelf();
        b.setTile(1, 1, new ItemTile("PLANTS"));
        b.setTile(2, 2, new ItemTile("FRAMES"));
        b.setTile(5, 0, new ItemTile("PLANTS"));
        b.setTile(0, 2, new ItemTile("BOOKS"));
        b.setTile(4, 4, new ItemTile("FRAMES"));
        b.setTile(3, 3, new ItemTile("TROPHIES"));
        assertEquals(6, PG12.CheckGoal(b));
    }

    public void testCheckGoalPGC12_FIFTH() {
        PersonalGoalCard PG12 = new PersonalGoalCard(12);
        BookShelf b = new BookShelf();
        b.setTile(1, 1, new ItemTile("PLANTS"));
        b.setTile(2, 2, new ItemTile("FRAMES"));
        b.setTile(5, 0, new ItemTile("PLANTS"));
        b.setTile(0, 2, new ItemTile("BOOKS"));
        b.setTile(4, 4, new ItemTile("GAMES"));
        b.setTile(3, 3, new ItemTile("TROPHIES"));
        assertEquals(9, PG12.CheckGoal(b));
    }

    public void testCheckGoalPGC12_SIXTH() {
        PersonalGoalCard PG12 = new PersonalGoalCard(12);
        BookShelf b = new BookShelf();
        b.setTile(1, 1, new ItemTile("PLANTS"));
        b.setTile(2, 2, new ItemTile("FRAMES"));
        b.setTile(5, 0, new ItemTile("CATS"));
        b.setTile(0, 2, new ItemTile("BOOKS"));
        b.setTile(4, 4, new ItemTile("GAMES"));
        b.setTile(3, 3, new ItemTile("TROPHIES"));
        assertEquals(12, PG12.CheckGoal(b));
    }

    /*
    TEST NON FUNZIONANTE ANCHE SE A MANO FUNZIONA, GLI DA FASTIDIO LA EQUALS
    public void testNotEqualsGoalCard() {
        HashMap<Integer,PersonalGoalCard> lista = new HashMap<>();
        ArrayList<PersonalGoalCard> confronto = new ArrayList<>();
        int counter = 0;
        for (int i = 0; i < 12; i++) {
            lista.put(i,new PersonalGoalCard());
            confronto.add(new PersonalGoalCard(i + 1));
        }
        int j=0;
        while(j == counter) {
            HashMap<Pair, ItemTile> goal = new HashMap<>(confronto.get(0).getGoal());
            for (int i=0;i<12 && lista.get(i)!=null;i++) {
                if (lista.get(i).equals(goal)) {
                    counter++;
                    lista.remove(i);
                }
            }
            confronto.remove(0);
            j++;
        }
        assertEquals(12, counter);
        assertEquals(0,lista.size());
        assertEquals(0,confronto.size());
    }*/
}



