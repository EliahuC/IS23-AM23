package it.polimi.ingsw.controller;

import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.ClientToServer.PossibleMoves.Move_SelectTiles;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.ServerToClient.ErrorMessage;
import it.polimi.ingsw.Messages.ServerToClient.ValidMoveMessage;
import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.player.Player;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class GameControllerTest extends TestCase {

    public void testStartGameGC_FIRST(){
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
    public void testStartGameGC_SECOND(){
        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");
        ArrayList<Player> Players = new ArrayList<>();
        Players.add(p1);
        Players.add(p2);
        GameController GC = new GameController(Players);
        GC.startGame();
        assertTrue(GC.getGame().getPlayers().get(0).isFirstPlayerSeat());
    }
    public void testStartGameGC_THIRD(){
        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");
        ArrayList<Player> Players = new ArrayList<>();
        Players.add(p1);
        Players.add(p2);
        GameController GC = new GameController(Players);
        GC.startGame();
        assertFalse(GC.getGame().getPlayers().get(1).isFirstPlayerSeat());
    }
    public void testEndgameGC_FIRST() {
        Player p = new Player("Alice", 2);
        Player p2 = new Player("Bob", 12);
        ArrayList<Player> Players = new ArrayList<>();
        Players.add(p);
        Players.add(p2);
        GameController GC = new GameController(Players);
        GC.startGame();
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
        assertEquals(player, GC.endGame());
    }
    public void testEndgameGC_SECOND() {
        Player p = new Player("Alice", 2);
        Player p2 = new Player("Bob", 12);
        ArrayList<Player> Players= new ArrayList<>();
        Players.add(p);
        Players.add(p2);
        GameController GC = new GameController(Players);
        GC.startGame();
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
        Optional<Player> player = Optional.ofNullable(GC.getGame().getPlayers().get(1));
        assertEquals(player, GC.endGame());
    }
    /*METODO COMMENTATO PERCHE IMPLEMENTATA PERSISTENZA
    public void testPlayMoveGC_FIRST(){
        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");
        Player p3 = new Player("Carlos");
        Player p4 = new Player("Diego");
        ArrayList<Player> Players = new ArrayList<>();
        Players.add(p1);
        Players.add(p2);
        Players.add(p3);
        Players.add(p4);
        GameController Controller = new GameController(Players);
        Controller.startGame();
        Controller.getCoordinates().add(3);
        Controller.getCoordinates().add(8);
        Controller.getCoordinates().add(4);
        Controller.getCoordinates().add(8);
        Controller.getOrder().add(1);
        Controller.getOrder().add(2);
        Controller.setColumn(3);
        Controller.playMove();
        assertTrue(Controller.getGame().playMove(Controller.getCoordinates(), Controller.getColumn(), Controller.getOrder()));
    }*/
    public void testPlayMoveGC_SECOND(){      //ONE MOVE
        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");
        Player p3 = new Player("Carlos");
        Player p4 = new Player("Diego");
        ArrayList<Player> Players = new ArrayList<>();
        Players.add(p1);
        Players.add(p2);
        Players.add(p3);
        Players.add(p4);
        GameController Controller = new GameController(Players);
        Controller.startGame();
        Controller.getCoordinates().add(3);
        Controller.getCoordinates().add(8);
        Controller.getCoordinates().add(4);
        Controller.getCoordinates().add(8);
        Controller.getOrder().add(1);
        Controller.getOrder().add(2);
        ItemTile i = Controller.getGame().getLivingRoom().getBoardTile(3,8).getTile();
        ItemTile it = Controller.getGame().getLivingRoom().getBoardTile(4,8).getTile();
        Controller.setColumn(3);
        Controller.playMove();
        assertEquals(i,Controller.getGame().getPlayers().get(0).getPlayerBookshelf().getTile(5,3));
        assertEquals(it,Controller.getGame().getPlayers().get(0).getPlayerBookshelf().getTile(4,3));
    }
    public void testReadMessage_FIRST(){
        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");
        Player p3 = new Player("Carlos");
        Player p4 = new Player("Diego");
        ArrayList<Player> Players = new ArrayList<>();
        Players.add(p1);
        Players.add(p2);
        Players.add(p3);
        Players.add(p4);
        GameController Controller = new GameController(Players);
        Controller.startGame();
        Move_SelectTiles M = new Move_SelectTiles();
        M.setCoordinates(new ArrayList<Integer>());
        ClientMessage m = new ClientMessage(Message.MessageCategory.COORDINATES,M,Controller.getGame().getPlayers().get(1).getNickName());
        Message error= new ErrorMessage();
        error.setReturnMessage("It's not your turn");
        assertEquals(error,Controller.readMessage(m));
    }
    public void testReadMessage_SECOND(){
        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");
        Player p3 = new Player("Carlos");
        Player p4 = new Player("Diego");
        ArrayList<Player> Players = new ArrayList<>();
        Players.add(p1);
        Players.add(p2);
        Players.add(p3);
        Players.add(p4);
        GameController Controller = new GameController(Players);
        Controller.startGame();
        Move_SelectTiles M = new Move_SelectTiles();
        M.setCoordinates(new ArrayList<Integer>());
        ClientMessage m = new ClientMessage(Message.MessageCategory.COLUMN,M,Controller.getGame().getPlayers().get(1).getNickName());
        Message error= new ErrorMessage();
        error.setReturnMessage("It's not your turn");
        assertEquals(error,Controller.readMessage(m));
    }
    public void testReadMessage_THIRD(){
        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");
        Player p3 = new Player("Carlos");
        Player p4 = new Player("Diego");
        ArrayList<Player> Players = new ArrayList<>();
        Players.add(p1);
        Players.add(p2);
        Players.add(p3);
        Players.add(p4);
        GameController Controller = new GameController(Players);
        Controller.startGame();
        Move_SelectTiles M = new Move_SelectTiles();
        M.setCoordinates(new ArrayList<Integer>());
        ClientMessage m = new ClientMessage(Message.MessageCategory.ORDER,M,Controller.getGame().getPlayers().get(1).getNickName());
        Message error= new ErrorMessage();
        error.setReturnMessage("It's not your turn");
        assertEquals(error,Controller.readMessage(m));
    }
    public void testReadMessage_FOURTH(){
        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");
        Player p3 = new Player("Carlos");
        Player p4 = new Player("Diego");
        ArrayList<Player> Players = new ArrayList<>();
        Players.add(p1);
        Players.add(p2);
        Players.add(p3);
        Players.add(p4);
        GameController Controller = new GameController(Players);
        Controller.startGame();
        ArrayList<Integer> E = new ArrayList<>();
        E.add(3);
        E.add(4);
        E.add(3);
        E.add(3);
        Move_SelectTiles M = new Move_SelectTiles();
        M.setCoordinates(E);
        ClientMessage m = new ClientMessage(Message.MessageCategory.COORDINATES,M,Controller.getGame().getPlayers().get(0).getNickName());
        Message error= new ErrorMessage();
        error.setReturnMessage("The move you made isn't a valid move");
        assertEquals(error,Controller.readMessage(m));
    }
    public void testReadMessage_FIFTH(){
        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");
        Player p3 = new Player("Carlos");
        Player p4 = new Player("Diego");
        ArrayList<Player> Players = new ArrayList<>();
        Players.add(p1);
        Players.add(p2);
        Players.add(p3);
        Players.add(p4);
        GameController Controller = new GameController(Players);
        Controller.startGame();
        ArrayList<Integer> E = new ArrayList<>();
        E.add(0);
        E.add(4);
        E.add(0);
        E.add(3);
        Move_SelectTiles M = new Move_SelectTiles();
        M.setCoordinates(E);
        ClientMessage m = new ClientMessage(Message.MessageCategory.COORDINATES,M,Controller.getGame().getPlayers().get(0).getNickName());
        ValidMoveMessage valid= new ValidMoveMessage();
        assertEquals(valid,Controller.readMessage(m));
    }
}