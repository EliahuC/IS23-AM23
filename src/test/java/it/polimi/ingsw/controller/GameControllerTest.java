package it.polimi.ingsw.controller;

import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;
import junit.framework.TestCase;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GameControllerTest extends TestCase {

    public void testStartGame_FIRST(){
        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");
        ArrayList<Player> Players = new ArrayList<>();
        Players.add(p1);
        Players.add(p2);
        GameController GC = new GameController(Players);
        GC.startGame();
        assertTrue(GC.isStartedGame());
        assertTrue(GC.getGame().isStartedGame());
    }
    public void testStartGame_SECOND(){
        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");
        ArrayList<Player> Players = new ArrayList<>();
        Players.add(p1);
        Players.add(p2);
        GameController GC = new GameController(Players);
        GC.startGame();
        assertTrue(GC.getGame().getPlayers().get(0).isFirstPlayerSeat());
    }
    public void testStartGame_THIRD(){
        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");
        ArrayList<Player> Players = new ArrayList<>();
        Players.add(p1);
        Players.add(p2);
        GameController GC = new GameController(Players);
        GC.startGame();
        assertFalse(GC.getGame().getPlayers().get(1).isFirstPlayerSeat());
    }

}