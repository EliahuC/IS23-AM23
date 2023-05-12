package it.polimi.ingsw.model;
import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.board.ItemTile;
import java.util.ArrayList;
import java.util.Optional;

import junit.framework.TestCase;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest extends TestCase {
        public void testStartGameG_FIRST(){     //FIRST SIMPLE TEST, IN WHICH THE BOOLEAN PARAMETER "startedGame" IS SETTED
            // AS TRUE
            Player p1 = new Player("Tom");
            Player p2 = new Player("Jerry");
            Launcher L = new Launcher();
            L.addPlayer(p1);
            L.addPlayer(p2);
            Game G = new Game(L,L.getPlayers());
            G.startGame();
            assertTrue(G.isStartedGame());
        }
        public void testStartGameG_SECOND(){        //PLAYER IN FIRST POSITION IN L.getPlayers (after calling startGame()
            //method that mix the list changing the order of players) ACHIEVES
            Player p1 = new Player("Tom");      //THE SEAT
            Player p2 = new Player("Jerry");
            Launcher L = new Launcher();
            L.addPlayer(p1);
            L.addPlayer(p2);
            Game G = new Game(L,L.getPlayers());
            G.startGame();
            assertTrue(G.getPlayers().get(0).isFirstPlayerSeat());
        }
        public void testStartGameG_THIRD(){         //PLAYER NOT IN FIRST POSITION IN L.getPlayers (after calling startGame()
            //method that mix the list changing the order of players) NOT
            Player p1 = new Player("Tom");      //ACHIEVE THE SEAT
            Player p2 = new Player("Jerry");
            Player p3 = new Player("Spike");
            Player p4 = new Player("Butch");
            Launcher L = new Launcher();
            L.addPlayer(p1);
            L.addPlayer(p2);
            L.addPlayer(p3);
            L.addPlayer(p4);
            Game G = new Game(L,L.getPlayers());
            G.startGame();
            assertFalse(G.getPlayers().get(1).isFirstPlayerSeat());
            assertFalse(G.getPlayers().get(2).isFirstPlayerSeat());
            assertFalse(G.getPlayers().get(3).isFirstPlayerSeat());
        }
      /*  Metodo commentato perche implementata persistenza
      public void testPlayMove_FIRST(){
            Player p1 = new Player("Tom");
            Player p2 = new Player("Jerry");
            Player p3 = new Player("Spike");
            Player p4 = new Player("Butch");
            Launcher L = new Launcher();
            L.addPlayer(p1);
            L.addPlayer(p2);
            L.addPlayer(p3);
            L.addPlayer(p4);
            L.setNumPlayers(4);
            Game G = new Game(L,L.getPlayers());
            G.startGame();
            ArrayList<Integer> CoordinatesTiles = new ArrayList<>();
            CoordinatesTiles.add(3);
            CoordinatesTiles.add(8);
            CoordinatesTiles.add(4);
            CoordinatesTiles.add(8);
            ArrayList<Integer> OrderTiles = new ArrayList<>();
            OrderTiles.add(1);
            OrderTiles.add(2);
            assertTrue(G.playMove(CoordinatesTiles,3,OrderTiles));
        }*/
        public void testPlayMove_SECOND(){      //ONE MOVE
            Player p1 = new Player("Tom");
            Player p2 = new Player("Jerry");
            Player p3 = new Player("Spike");
            Player p4 = new Player("Butch");
            Launcher L = new Launcher();
            L.addPlayer(p1);
            L.addPlayer(p2);
            L.addPlayer(p3);
            L.addPlayer(p4);
            L.setNumPlayers(4);
            Game G = new Game(L,L.getPlayers());
            G.startGame();
            ArrayList<Integer> CoordinatesTiles = new ArrayList<>();
            CoordinatesTiles.add(3);
            CoordinatesTiles.add(8);
            CoordinatesTiles.add(4);
            CoordinatesTiles.add(8);
            ArrayList<Integer> OrderTiles = new ArrayList<>();
            OrderTiles.add(1);
            OrderTiles.add(2);
            ItemTile i = G.getLivingRoom().getBoardTile(3,8).getTile();
            ItemTile it = G.getLivingRoom().getBoardTile(4,8).getTile();
            G.playMove(CoordinatesTiles,3,OrderTiles);
            assertEquals(i,G.getPlayers().get(0).getPlayerBookshelf().getTile(5,3));
            assertEquals(it,G.getPlayers().get(0).getPlayerBookshelf().getTile(4,3));
        }
        public void testPlayMove_THIRD(){       //ONE MOVE BUT DIFFERENT INSERT ORDER
            Player p1 = new Player("Tom");
            Player p2 = new Player("Jerry");
            Player p3 = new Player("Spike");
            Player p4 = new Player("Butch");
            Launcher L = new Launcher();
            L.addPlayer(p1);
            L.addPlayer(p2);
            L.addPlayer(p3);
            L.addPlayer(p4);
            L.setNumPlayers(4);
            Game G = new Game(L,L.getPlayers());
            G.startGame();
            ArrayList<Integer> CoordinatesTiles = new ArrayList<>();
            CoordinatesTiles.add(3);
            CoordinatesTiles.add(8);
            CoordinatesTiles.add(4);
            CoordinatesTiles.add(8);
            ArrayList<Integer> OrderTiles = new ArrayList<>();
            OrderTiles.add(2);
            OrderTiles.add(1);
            ItemTile i = G.getLivingRoom().getBoardTile(3,8).getTile();
            ItemTile it = G.getLivingRoom().getBoardTile(4,8).getTile();
            G.playMove(CoordinatesTiles,3,OrderTiles);
            assertEquals(it,G.getPlayers().get(0).getPlayerBookshelf().getTile(5,3));
            assertEquals(i,G.getPlayers().get(0).getPlayerBookshelf().getTile(4,3));
        }
        public void testPlayMove_FOURTH(){      //TWO MOVES
            Player p1 = new Player("Tom");
            Player p2 = new Player("Jerry");
            Player p3 = new Player("Spike");
            Player p4 = new Player("Butch");
            Launcher L = new Launcher();
            L.addPlayer(p1);
            L.addPlayer(p2);
            L.addPlayer(p3);
            L.addPlayer(p4);
            L.setNumPlayers(4);
            Game G = new Game(L,L.getPlayers());
            G.startGame();
            ArrayList<Integer> CoordinatesTiles = new ArrayList<>();
            CoordinatesTiles.add(3);
            CoordinatesTiles.add(8);
            CoordinatesTiles.add(4);
            CoordinatesTiles.add(8);
            ArrayList<Integer> OrderTiles = new ArrayList<>();
            OrderTiles.add(2);
            OrderTiles.add(1);
            ItemTile i = G.getLivingRoom().getBoardTile(3,8).getTile();
            ItemTile it = G.getLivingRoom().getBoardTile(4,8).getTile();
            G.playMove(CoordinatesTiles,3,OrderTiles);
            assertEquals(it,G.getPlayers().get(0).getPlayerBookshelf().getTile(5,3));
            assertEquals(i,G.getPlayers().get(0).getPlayerBookshelf().getTile(4,3));
            CoordinatesTiles.clear();
            OrderTiles.clear();
            CoordinatesTiles.add(8);
            CoordinatesTiles.add(4);
            CoordinatesTiles.add(8);
            CoordinatesTiles.add(5);
            OrderTiles.add(1);
            OrderTiles.add(2);
            i = G.getLivingRoom().getBoardTile(8,4).getTile();
            it = G.getLivingRoom().getBoardTile(8,5).getTile();
            G.playMove(CoordinatesTiles,2,OrderTiles);
            assertEquals(i,G.getPlayers().get(1).getPlayerBookshelf().getTile(5,2));
            assertEquals(it,G.getPlayers().get(1).getPlayerBookshelf().getTile(4,2));
        }
        public void testPlayMove_FIFTH(){           //THREE MOVES
            Player p1 = new Player("Tom");
            Player p2 = new Player("Jerry");
            Player p3 = new Player("Spike");
            Player p4 = new Player("Butch");
            Launcher L = new Launcher();
            L.addPlayer(p1);
            L.addPlayer(p2);
            L.addPlayer(p3);
            L.addPlayer(p4);
            L.setNumPlayers(4);
            Game G = new Game(L,L.getPlayers());
            G.startGame();
            ArrayList<Integer> CoordinatesTiles = new ArrayList<>();
            CoordinatesTiles.add(3);
            CoordinatesTiles.add(8);
            CoordinatesTiles.add(4);
            CoordinatesTiles.add(8);
            ArrayList<Integer> OrderTiles = new ArrayList<>();
            OrderTiles.add(2);
            OrderTiles.add(1);
            ItemTile i = G.getLivingRoom().getBoardTile(3,8).getTile();
            ItemTile it = G.getLivingRoom().getBoardTile(4,8).getTile();
            G.playMove(CoordinatesTiles,3,OrderTiles);
            assertEquals(it,G.getPlayers().get(0).getPlayerBookshelf().getTile(5,3));
            assertEquals(i,G.getPlayers().get(0).getPlayerBookshelf().getTile(4,3));
            CoordinatesTiles.clear();
            OrderTiles.clear();
            CoordinatesTiles.add(8);
            CoordinatesTiles.add(4);
            CoordinatesTiles.add(8);
            CoordinatesTiles.add(5);
            OrderTiles.add(1);
            OrderTiles.add(2);
            i = G.getLivingRoom().getBoardTile(8,4).getTile();
            it = G.getLivingRoom().getBoardTile(8,5).getTile();
            G.playMove(CoordinatesTiles,2,OrderTiles);
            assertEquals(i,G.getPlayers().get(1).getPlayerBookshelf().getTile(5,2));
            assertEquals(it,G.getPlayers().get(1).getPlayerBookshelf().getTile(4,2));
            CoordinatesTiles.clear();
            OrderTiles.clear();
            CoordinatesTiles.add(0);
            CoordinatesTiles.add(4);
            CoordinatesTiles.add(0);
            CoordinatesTiles.add(3);
            OrderTiles.add(1);
            OrderTiles.add(2);
            i = G.getLivingRoom().getBoardTile(0,4).getTile();
            it = G.getLivingRoom().getBoardTile(0,3).getTile();
            G.playMove(CoordinatesTiles,1,OrderTiles);
            assertEquals(i,G.getPlayers().get(2).getPlayerBookshelf().getTile(5,1));
            assertEquals(it,G.getPlayers().get(2).getPlayerBookshelf().getTile(4,1));
        }
        public void testPlayMove_SIXTH(){           //FOUR MOVES
            Player p1 = new Player("Tom");
            Player p2 = new Player("Jerry");
            Player p3 = new Player("Spike");
            Player p4 = new Player("Butch");
            Launcher L = new Launcher();
            L.addPlayer(p1);
            L.addPlayer(p2);
            L.addPlayer(p3);
            L.addPlayer(p4);
            L.setNumPlayers(4);
            Game G = new Game(L,L.getPlayers());
            G.startGame();
            ArrayList<Integer> CoordinatesTiles = new ArrayList<>();
            CoordinatesTiles.add(3);
            CoordinatesTiles.add(8);
            CoordinatesTiles.add(4);
            CoordinatesTiles.add(8);
            ArrayList<Integer> OrderTiles = new ArrayList<>();
            OrderTiles.add(2);
            OrderTiles.add(1);
            ItemTile i = G.getLivingRoom().getBoardTile(3,8).getTile();
            ItemTile it = G.getLivingRoom().getBoardTile(4,8).getTile();
            G.playMove(CoordinatesTiles,3,OrderTiles);
            assertEquals(it,G.getPlayers().get(0).getPlayerBookshelf().getTile(5,3));
            assertEquals(i,G.getPlayers().get(0).getPlayerBookshelf().getTile(4,3));
            CoordinatesTiles.clear();
            OrderTiles.clear();
            CoordinatesTiles.add(8);
            CoordinatesTiles.add(4);
            CoordinatesTiles.add(8);
            CoordinatesTiles.add(5);
            OrderTiles.add(1);
            OrderTiles.add(2);
            i = G.getLivingRoom().getBoardTile(8,4).getTile();
            it = G.getLivingRoom().getBoardTile(8,5).getTile();
            G.playMove(CoordinatesTiles,2,OrderTiles);
            assertEquals(i,G.getPlayers().get(1).getPlayerBookshelf().getTile(5,2));
            assertEquals(it,G.getPlayers().get(1).getPlayerBookshelf().getTile(4,2));
            CoordinatesTiles.clear();
            OrderTiles.clear();
            CoordinatesTiles.add(0);
            CoordinatesTiles.add(4);
            CoordinatesTiles.add(0);
            CoordinatesTiles.add(3);
            OrderTiles.add(1);
            OrderTiles.add(2);
            i = G.getLivingRoom().getBoardTile(0,4).getTile();
            it = G.getLivingRoom().getBoardTile(0,3).getTile();
            G.playMove(CoordinatesTiles,1,OrderTiles);
            assertEquals(i,G.getPlayers().get(2).getPlayerBookshelf().getTile(5,1));
            assertEquals(it,G.getPlayers().get(2).getPlayerBookshelf().getTile(4,1));
            CoordinatesTiles.clear();
            OrderTiles.clear();
            CoordinatesTiles.add(3);
            CoordinatesTiles.add(7);
            CoordinatesTiles.add(4);
            CoordinatesTiles.add(7);
            CoordinatesTiles.add(5);
            CoordinatesTiles.add(7);
            OrderTiles.add(2);
            OrderTiles.add(3);
            OrderTiles.add(1);
            i = G.getLivingRoom().getBoardTile(4,7).getTile();
            it = G.getLivingRoom().getBoardTile(5,7).getTile();
            ItemTile y = G.getLivingRoom().getBoardTile(3,7).getTile();
            G.playMove(CoordinatesTiles,0,OrderTiles);
            assertEquals(i,G.getPlayers().get(3).getPlayerBookshelf().getTile(5,0));
            assertEquals(it,G.getPlayers().get(3).getPlayerBookshelf().getTile(4,0));
            assertEquals(y,G.getPlayers().get(3).getPlayerBookshelf().getTile(3,0));
        }
        public void testPlayMove_SEVENTH(){           //FIVE MOVES, THE "FIRST" PLAYER MAKES HIS/HER SECOND MOVE
            Player p1 = new Player("Tom");
            Player p2 = new Player("Jerry");
            Player p3 = new Player("Spike");
            Player p4 = new Player("Butch");
            Launcher L = new Launcher();
            L.addPlayer(p1);
            L.addPlayer(p2);
            L.addPlayer(p3);
            L.addPlayer(p4);
            L.setNumPlayers(4);
            Game G = new Game(L,L.getPlayers());
            G.startGame();
            ArrayList<Integer> CoordinatesTiles = new ArrayList<>();
            CoordinatesTiles.add(3);
            CoordinatesTiles.add(8);
            CoordinatesTiles.add(4);
            CoordinatesTiles.add(8);
            ArrayList<Integer> OrderTiles = new ArrayList<>();
            OrderTiles.add(2);
            OrderTiles.add(1);
            ItemTile i = G.getLivingRoom().getBoardTile(3,8).getTile();
            ItemTile it = G.getLivingRoom().getBoardTile(4,8).getTile();
            G.playMove(CoordinatesTiles,3,OrderTiles);
            assertEquals(it,G.getPlayers().get(0).getPlayerBookshelf().getTile(5,3));
            assertEquals(i,G.getPlayers().get(0).getPlayerBookshelf().getTile(4,3));
            CoordinatesTiles.clear();
            OrderTiles.clear();
            CoordinatesTiles.add(8);
            CoordinatesTiles.add(4);
            CoordinatesTiles.add(8);
            CoordinatesTiles.add(5);
            OrderTiles.add(1);
            OrderTiles.add(2);
            ItemTile j = G.getLivingRoom().getBoardTile(8,4).getTile();
            ItemTile u  = G.getLivingRoom().getBoardTile(8,5).getTile();
            G.playMove(CoordinatesTiles,2,OrderTiles);
            assertEquals(j,G.getPlayers().get(1).getPlayerBookshelf().getTile(5,2));
            assertEquals(u,G.getPlayers().get(1).getPlayerBookshelf().getTile(4,2));
            CoordinatesTiles.clear();
            OrderTiles.clear();
            CoordinatesTiles.add(0);
            CoordinatesTiles.add(4);
            CoordinatesTiles.add(0);
            CoordinatesTiles.add(3);
            OrderTiles.add(1);
            OrderTiles.add(2);
            j = G.getLivingRoom().getBoardTile(0,4).getTile();
            u = G.getLivingRoom().getBoardTile(0,3).getTile();
            G.playMove(CoordinatesTiles,1,OrderTiles);
            assertEquals(j,G.getPlayers().get(2).getPlayerBookshelf().getTile(5,1));
            assertEquals(u,G.getPlayers().get(2).getPlayerBookshelf().getTile(4,1));
            CoordinatesTiles.clear();
            OrderTiles.clear();
            CoordinatesTiles.add(3);
            CoordinatesTiles.add(7);
            CoordinatesTiles.add(4);
            CoordinatesTiles.add(7);
            CoordinatesTiles.add(5);
            CoordinatesTiles.add(7);
            OrderTiles.add(2);
            OrderTiles.add(3);
            OrderTiles.add(1);
            j = G.getLivingRoom().getBoardTile(4,7).getTile();
            u = G.getLivingRoom().getBoardTile(5,7).getTile();
            ItemTile y = G.getLivingRoom().getBoardTile(3,7).getTile();
            G.playMove(CoordinatesTiles,0,OrderTiles);
            assertEquals(j,G.getPlayers().get(3).getPlayerBookshelf().getTile(5,0));
            assertEquals(u,G.getPlayers().get(3).getPlayerBookshelf().getTile(4,0));
            assertEquals(y,G.getPlayers().get(3).getPlayerBookshelf().getTile(3,0));
            CoordinatesTiles.clear();
            OrderTiles.clear();
            CoordinatesTiles.add(5);
            CoordinatesTiles.add(0);
            CoordinatesTiles.add(4);
            CoordinatesTiles.add(0);
            OrderTiles.add(1);
            OrderTiles.add(2);
            j = G.getLivingRoom().getBoardTile(5,0).getTile();
            u = G.getLivingRoom().getBoardTile(4,0).getTile();
            G.playMove(CoordinatesTiles,4,OrderTiles);
            assertEquals(it,G.getPlayers().get(0).getPlayerBookshelf().getTile(5,3));
            assertEquals(i,G.getPlayers().get(0).getPlayerBookshelf().getTile(4,3));
            assertEquals(j,G.getPlayers().get(0).getPlayerBookshelf().getTile(5,4));
            assertEquals(u,G.getPlayers().get(0).getPlayerBookshelf().getTile(4,4));
        }
    public void testEndgame_FIRST() {
        Player p = new Player("Tom", 2);
        Player p2 = new Player("Butch", 12);
        Launcher L = new Launcher();
        L.addPlayer(p);
        L.addPlayer(p2);
        L.setNumPlayers(2);
        Game G = new Game(L, L.getPlayers());
        G.startGame();
        p.getPlayerBookshelf().setTile(1, 1, new ItemTile("PLANTS"));
        p.getPlayerBookshelf().setTile(5, 4, new ItemTile("FRAMES"));
        p.getPlayerBookshelf().setTile(2, 0, new ItemTile("PLANTS"));
        p.getPlayerBookshelf().setTile(3, 4, new ItemTile("BOOKS"));
        p.getPlayerBookshelf().setTile(2, 2, new ItemTile("GAMES"));
        p.getPlayerBookshelf().setTile(4, 3, new ItemTile("TROPHIES"));
        p.getPlayerBookshelf().setTile(0, 0, new ItemTile("PLANTS"));
        p.getPlayerBookshelf().setTile(0, 1, new ItemTile("PLANTS"));
        p.getPlayerBookshelf().setTile(1, 0, new ItemTile("PLANTS"));
        p.getPlayerBookshelf().setTile(0, 2, new ItemTile("BOOKS"));
        p.getPlayerBookshelf().setTile(0, 3, new ItemTile("CATS"));
        p.getPlayerBookshelf().setTile(0, 4, new ItemTile("GAMES"));
        p.getPlayerBookshelf().setTile(1, 2, new ItemTile("FRAMES"));
        p.getPlayerBookshelf().setTile(1, 3, new ItemTile("GAMES"));
        p.getPlayerBookshelf().setTile(1, 4, new ItemTile("GAMES"));
        p.getPlayerBookshelf().setTile(2, 1, new ItemTile("BOOKS"));
        p.getPlayerBookshelf().setTile(2, 3, new ItemTile("FRAMES"));
        p.getPlayerBookshelf().setTile(2, 4, new ItemTile("FRAMES"));
        p.getPlayerBookshelf().setTile(3, 0, new ItemTile("BOOKS"));
        p.getPlayerBookshelf().setTile(3, 1, new ItemTile("BOOKS"));
        p.getPlayerBookshelf().setTile(3, 2, new ItemTile("TROPHIES"));
        p.getPlayerBookshelf().setTile(3, 3, new ItemTile("CATS"));
        p.getPlayerBookshelf().setTile(4, 0, new ItemTile("CATS"));
        p.getPlayerBookshelf().setTile(4, 1, new ItemTile("BOOKS"));
        p.getPlayerBookshelf().setTile(4, 2, new ItemTile("CATS"));
        p.getPlayerBookshelf().setTile(4, 4, new ItemTile("BOOKS"));
        p.getPlayerBookshelf().setTile(5, 0, new ItemTile("BOOKS"));
        p.getPlayerBookshelf().setTile(5, 1, new ItemTile("CATS"));
        p.getPlayerBookshelf().setTile(5, 2, new ItemTile("TROPHIES"));
        p.getPlayerBookshelf().setTile(5, 3, new ItemTile("FRAMES"));
        p2.getPlayerBookshelf().setTile(1, 1, new ItemTile("PLANTS"));
        p2.getPlayerBookshelf().setTile(2, 2, new ItemTile("FRAMES"));
        p2.getPlayerBookshelf().setTile(5, 0, new ItemTile("CATS"));
        p2.getPlayerBookshelf().setTile(0, 2, new ItemTile("BOOKS"));
        p2.getPlayerBookshelf().setTile(4, 4, new ItemTile("GAMES"));
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
        Optional<Player> player = Optional.ofNullable(p2);
        assertEquals(player, G.endGame());
    }

    public void testEndgame_SECOND() {
        Player p = new Player("Spike", 2);
        Player p2 = new Player("Jerry", 12);
        Launcher L = new Launcher();
        L.addPlayer(p);
        L.addPlayer(p2);
        L.setNumPlayers(2);
        Game G = new Game(L, L.getPlayers());
        G.startGame();
        p.getPlayerBookshelf().setTile(1, 1, new ItemTile("PLANTS"));
        p.getPlayerBookshelf().setTile(5, 4, new ItemTile("FRAMES"));
        p.getPlayerBookshelf().setTile(2, 0, new ItemTile("PLANTS"));
        p.getPlayerBookshelf().setTile(3, 4, new ItemTile("BOOKS"));
        p.getPlayerBookshelf().setTile(2, 2, new ItemTile("GAMES"));
        p.getPlayerBookshelf().setTile(4, 3, new ItemTile("TROPHIES"));
        p.getPlayerBookshelf().setTile(0, 0, new ItemTile("PLANTS"));
        p.getPlayerBookshelf().setTile(0, 1, new ItemTile("PLANTS"));
        p.getPlayerBookshelf().setTile(1, 0, new ItemTile("PLANTS"));
        p.getPlayerBookshelf().setTile(0, 2, new ItemTile("BOOKS"));
        p.getPlayerBookshelf().setTile(0, 3, new ItemTile("CATS"));
        p.getPlayerBookshelf().setTile(0, 4, new ItemTile("GAMES"));
        p.getPlayerBookshelf().setTile(1, 2, new ItemTile("FRAMES"));
        p.getPlayerBookshelf().setTile(1, 3, new ItemTile("GAMES"));
        p.getPlayerBookshelf().setTile(1, 4, new ItemTile("GAMES"));
        p.getPlayerBookshelf().setTile(2, 1, new ItemTile("BOOKS"));
        p.getPlayerBookshelf().setTile(2, 3, new ItemTile("FRAMES"));
        p.getPlayerBookshelf().setTile(2, 4, new ItemTile("FRAMES"));
        p.getPlayerBookshelf().setTile(3, 0, new ItemTile("BOOKS"));
        p.getPlayerBookshelf().setTile(3, 1, new ItemTile("BOOKS"));
        p.getPlayerBookshelf().setTile(3, 2, new ItemTile("TROPHIES"));
        p.getPlayerBookshelf().setTile(3, 3, new ItemTile("CATS"));
        p.getPlayerBookshelf().setTile(4, 0, new ItemTile("CATS"));
        p.getPlayerBookshelf().setTile(4, 1, new ItemTile("BOOKS"));
        p.getPlayerBookshelf().setTile(4, 2, new ItemTile("CATS"));
        p.getPlayerBookshelf().setTile(4, 4, new ItemTile("BOOKS"));
        p.getPlayerBookshelf().setTile(5, 0, new ItemTile("BOOKS"));
        p.getPlayerBookshelf().setTile(5, 1, new ItemTile("CATS"));
        p.getPlayerBookshelf().setTile(5, 2, new ItemTile("TROPHIES"));
        p.getPlayerBookshelf().setTile(5, 3, new ItemTile("FRAMES"));
        p2.getPlayerBookshelf().setTile(1, 1, new ItemTile("PLANTS"));
        p2.getPlayerBookshelf().setTile(2, 2, new ItemTile("FRAMES"));
        p2.getPlayerBookshelf().setTile(5, 0, new ItemTile("CATS"));
        p2.getPlayerBookshelf().setTile(0, 2, new ItemTile("BOOKS"));
        p2.getPlayerBookshelf().setTile(4, 4, new ItemTile("GAMES"));
        p2.getPlayerBookshelf().setTile(3, 3, new ItemTile("TROPHIES"));
        p2.getPlayerBookshelf().setTile(0, 0, new ItemTile("PLANTS"));
        p2.getPlayerBookshelf().setTile(0, 1, new ItemTile("PLANTS"));
        p2.getPlayerBookshelf().setTile(0, 3, new ItemTile("BOOKS"));
        p2.getPlayerBookshelf().setTile(0, 4, new ItemTile("BOOKS"));
        p2.getPlayerBookshelf().setTile(1, 0, new ItemTile("GAMES"));
        p2.getPlayerBookshelf().setTile(1, 1, new ItemTile("PLANTS"));
        p2.getPlayerBookshelf().setTile(1, 2, new ItemTile("CATS"));
        p2.getPlayerBookshelf().setTile(1, 3, new ItemTile("FRAMES"));
        p2.getPlayerBookshelf().setTile(1, 4, new ItemTile("TROPHIES"));
        p2.getPlayerBookshelf().setTile(2, 0, new ItemTile("PLANTS"));
        p2.getPlayerBookshelf().setTile(2, 1, new ItemTile("PLANTS"));
        p2.getPlayerBookshelf().setTile(2, 3, new ItemTile("TROPHIES"));
        p2.getPlayerBookshelf().setTile(2, 4, new ItemTile("FRAMES"));
        p2.getPlayerBookshelf().setTile(3, 0, new ItemTile("GAMES"));
        p2.getPlayerBookshelf().setTile(3, 1, new ItemTile("BOOKS"));
        p2.getPlayerBookshelf().setTile(3, 2, new ItemTile("CATS"));
        p2.getPlayerBookshelf().setTile(3, 4, new ItemTile("GAMES"));
        p2.getPlayerBookshelf().setTile(4, 0, new ItemTile("GAMES"));
        p2.getPlayerBookshelf().setTile(4, 1, new ItemTile("CATS"));
        p2.getPlayerBookshelf().setTile(4, 2, new ItemTile("FRAMES"));
        p2.getPlayerBookshelf().setTile(4, 3, new ItemTile("BOOKS"));
        p2.getPlayerBookshelf().setTile(5, 1, new ItemTile("BOOKS"));
        p2.getPlayerBookshelf().setTile(5, 2, new ItemTile("BOOKS"));
        p2.getPlayerBookshelf().setTile(5, 3, new ItemTile("FRAMES"));
        p2.getPlayerBookshelf().setTile(5, 4, new ItemTile("TROPHIES"));
        Optional<Player> player = Optional.ofNullable(G.getPlayers().get(1));
        assertEquals(player, G.endGame());
    }
    public void testCheckLegalMove_FIRST(){
        Player y = new Player("Yoda");
        Player d = new Player("Darth Vader");
        Launcher L = new Launcher();
        L.addPlayer(y);
        L.addPlayer(d);
        L.setNumPlayers(2);
        GameChecker GC = new GameChecker(L);
        ArrayList<Integer> Coordinates = new ArrayList<>();
        Game G = new Game(L, L.getPlayers());
        G.startGame();
        G.getPlayers().get(0).getPlayerBookshelf().setTile(5,0,new ItemTile());
        G.getPlayers().get(0).getPlayerBookshelf().setTile(4,0,new ItemTile());
        G.getPlayers().get(0).getPlayerBookshelf().setTile(3,0, new ItemTile());
        G.getPlayers().get(0).getPlayerBookshelf().setTile(2,0, new ItemTile());
        G.getPlayers().get(0).getPlayerBookshelf().setTile(1,0, new ItemTile());
        G.getPlayers().get(0).getPlayerBookshelf().setTile(5,1,new ItemTile());
        G.getPlayers().get(0).getPlayerBookshelf().setTile(4,1,new ItemTile());
        G.getPlayers().get(0).getPlayerBookshelf().setTile(3,1,new ItemTile());
        G.getPlayers().get(0).getPlayerBookshelf().setTile(2,1,new ItemTile());
        G.getPlayers().get(0).getPlayerBookshelf().setTile(1,1,new ItemTile());
        G.getPlayers().get(0).getPlayerBookshelf().setTile(5,2,new ItemTile());
        G.getPlayers().get(0).getPlayerBookshelf().setTile(4,2,new ItemTile());
        G.getPlayers().get(0).getPlayerBookshelf().setTile(3,2,new ItemTile());
        G.getPlayers().get(0).getPlayerBookshelf().setTile(2,2,new ItemTile());
        G.getPlayers().get(0).getPlayerBookshelf().setTile(1,2,new ItemTile());
        G.getPlayers().get(0).getPlayerBookshelf().setTile(5,3,new ItemTile());
        G.getPlayers().get(0).getPlayerBookshelf().setTile(4,3,new ItemTile());
        G.getPlayers().get(0).getPlayerBookshelf().setTile(3,3,new ItemTile());
        G.getPlayers().get(0).getPlayerBookshelf().setTile(2,3,new ItemTile());
        G.getPlayers().get(0).getPlayerBookshelf().setTile(1,3,new ItemTile());
        G.getPlayers().get(0).getPlayerBookshelf().setTile(5,4,new ItemTile());
        G.getPlayers().get(0).getPlayerBookshelf().setTile(4,4,new ItemTile());
        G.getPlayers().get(0).getPlayerBookshelf().setTile(3,4,new ItemTile());
        G.getPlayers().get(0).getPlayerBookshelf().setTile(2,4,new ItemTile());
        G.getPlayers().get(0).getPlayerBookshelf().setTile(1,4,new ItemTile());
        Coordinates.add(1);
        Coordinates.add(3);
        Coordinates.add(1);
        Coordinates.add(4);
        GC.checkColumnCapability(G.getPlayers().get(0).getPlayerBookshelf());
        assertFalse(G.checkLegalMove(Coordinates,Coordinates.size()/2));
    }
    public void testCheckLegalMove_SECOND() {
        Player y = new Player("Yoda");
        Player d = new Player("Darth Vader");
        Launcher L = new Launcher();
        L.addPlayer(y);
        L.addPlayer(d);
        L.setNumPlayers(2);
        ArrayList<Integer> Coordinates = new ArrayList<>();
        Game G = new Game(L, L.getPlayers());
        G.startGame();
        Coordinates.add(7);
        Coordinates.add(5);
        assertTrue(G.checkLegalMove(Coordinates, Coordinates.size() / 2));
    }
    public void testCheckLegalMove_THIRD() {
        Player y = new Player("Yoda");
        Player d = new Player("Darth Vader");
        Launcher L = new Launcher();
        L.addPlayer(y);
        L.addPlayer(d);
        L.setNumPlayers(2);
        ArrayList<Integer> Coordinates = new ArrayList<>();
        Game G = new Game(L, L.getPlayers());
        G.startGame();
        Coordinates.add(3);
        Coordinates.add(3);
        assertFalse(G.checkLegalMove(Coordinates, Coordinates.size() / 2));
    }
    public void testCheckLegalMove_FOURTH() {
        Player y = new Player("Yoda");
        Player d = new Player("Darth Vader");
        Launcher L = new Launcher();
        L.addPlayer(y);
        L.addPlayer(d);
        L.setNumPlayers(2);
        ArrayList<Integer> Coordinates = new ArrayList<>();
        Game G = new Game(L, L.getPlayers());
        G.startGame();
        Coordinates.add(3);
        Coordinates.add(7);
        Coordinates.add(4);
        Coordinates.add(7);
        assertTrue(G.checkLegalMove(Coordinates, Coordinates.size() / 2));
    }
    public void testCheckLegalMove_FIFTH() {
        Player y = new Player("Yoda");
        Player d = new Player("Darth Vader");
        Launcher L = new Launcher();
        L.addPlayer(y);
        L.addPlayer(d);
        L.setNumPlayers(2);
        ArrayList<Integer> Coordinates = new ArrayList<>();
        Game G = new Game(L, L.getPlayers());
        G.startGame();
        Coordinates.add(3);
        Coordinates.add(6);
        Coordinates.add(4);
        Coordinates.add(6);
        assertFalse(G.checkLegalMove(Coordinates, Coordinates.size() / 2));
    }
    public void testCheckLegalMove_SIXTH() {
        Player y = new Player("Yoda");
        Player d = new Player("Darth Vader");
        Launcher L = new Launcher();
        L.addPlayer(y);
        L.addPlayer(d);
        L.setNumPlayers(2);
        ArrayList<Integer> Coordinates = new ArrayList<>();
        Game G = new Game(L, L.getPlayers());
        G.startGame();
        Coordinates.add(3);
        Coordinates.add(2);
        Coordinates.add(4);
        Coordinates.add(2);
        Coordinates.add(5);
        Coordinates.add(2);
        assertFalse(G.checkLegalMove(Coordinates, Coordinates.size() / 2));
    }
    public void testCheckLegalMove_SEVENTH() {
        Player y = new Player("Yoda");
        Player d = new Player("Darth Vader");
        Launcher L = new Launcher();
        L.addPlayer(y);
        L.addPlayer(d);
        L.setNumPlayers(2);
        ArrayList<Integer> Coordinates = new ArrayList<>();
        Game G = new Game(L, L.getPlayers());
        G.startGame();
        Coordinates.add(4);
        Coordinates.add(1);
        Coordinates.add(5);
        Coordinates.add(1);
        G.getLivingRoom().getTiles(Coordinates);
        Coordinates.clear();
        Coordinates.add(3);
        Coordinates.add(2);
        Coordinates.add(4);
        Coordinates.add(2);
        Coordinates.add(5);
        Coordinates.add(2);
        assertTrue(G.checkLegalMove(Coordinates, Coordinates.size() / 2));
    }
    public void testCheckColumn_FIRST(){
        Player y = new Player("Spiderman");
        Player d = new Player("Batman");
        Launcher L = new Launcher();
        L.addPlayer(y);
        L.addPlayer(d);
        L.setNumPlayers(2);
        GameChecker GC = new GameChecker(L);
        ArrayList<Integer> Coordinates = new ArrayList<>();
        Game G = new Game(L, L.getPlayers());
        G.startGame();
        G.getPlayers().get(0).getPlayerBookshelf().setTile(5, 0, new ItemTile());
        G.getPlayers().get(0).getPlayerBookshelf().setTile(4, 0, new ItemTile());
        G.getPlayers().get(0).getPlayerBookshelf().setTile(3, 0, new ItemTile());
        G.getPlayers().get(0).getPlayerBookshelf().setTile(2, 0, new ItemTile());
        G.getPlayers().get(0).getPlayerBookshelf().setTile(1, 0, new ItemTile());
        Coordinates.add(4);
        Coordinates.add(1);
        Coordinates.add(5);
        Coordinates.add(1);
        assertFalse(G.checkLegalColumn(0,Coordinates.size()/2));
    }
    public void testCheckColumn_SECOND(){
        Player y = new Player("Spiderman");
        Player d = new Player("Batman");
        Launcher L = new Launcher();
        L.addPlayer(y);
        L.addPlayer(d);
        L.setNumPlayers(2);
        GameChecker GC = new GameChecker(L);
        ArrayList<Integer> Coordinates = new ArrayList<>();
        Game G = new Game(L, L.getPlayers());
        G.startGame();
        Coordinates.add(4);
        Coordinates.add(1);
        Coordinates.add(5);
        Coordinates.add(1);
        assertTrue(G.checkLegalColumn(0,Coordinates.size()/2));
    }
}