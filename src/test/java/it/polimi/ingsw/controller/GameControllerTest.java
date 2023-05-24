package it.polimi.ingsw.controller;

import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.ClientToServer.CoordinatesMessage;
import it.polimi.ingsw.Messages.ClientToServer.PossibleMoves.Move_SelectColumn;
import it.polimi.ingsw.Messages.ClientToServer.PossibleMoves.Move_SelectOrder;
import it.polimi.ingsw.Messages.ClientToServer.PossibleMoves.Move_SelectTiles;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.ServerToClient.ErrorMessage;
import it.polimi.ingsw.Messages.ServerToClient.ValidMoveMessage;
import it.polimi.ingsw.Network.Server.TCP.ServerConnectionTCP;
import it.polimi.ingsw.Network.Server.VirtualView;
import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.player.PersonalGoalCard;
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
        p1.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p2.setListener(new VirtualView(new ServerConnectionTCP(null)));
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
        p1.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p2.setListener(new VirtualView(new ServerConnectionTCP(null)));
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
        p1.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p2.setListener(new VirtualView(new ServerConnectionTCP(null)));
        GameController GC = new GameController(Players);
        GC.startGame();
        assertFalse(GC.getGame().getPlayers().get(1).isFirstPlayerSeat());
    }
    public void testEndgameGC_FIRST() {
        Player p = new Player("Alice");
        Player p2 = new Player("Bob");
        ArrayList<Player> Players = new ArrayList<>();
        Players.add(p);
        Players.add(p2);
        p.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p2.setListener(new VirtualView(new ServerConnectionTCP(null)));
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
        p2.setPersonalGoalCard(new PersonalGoalCard(12));
        p.setPersonalGoalCard(new PersonalGoalCard(2));
        Optional<Player> player = Optional.ofNullable(p2);
        assertEquals(player, GC.endGame());
    }
    public void testEndgameGC_SECOND() {
        Player p = new Player("Alice");
        Player p2 = new Player("Bob");
        ArrayList<Player> Players= new ArrayList<>();
        Players.add(p);
        Players.add(p2);
        p.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p2.setListener(new VirtualView(new ServerConnectionTCP(null)));
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
        p2.setPersonalGoalCard(new PersonalGoalCard(12));
        p.setPersonalGoalCard(new PersonalGoalCard(2));
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
        p1.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p2.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p3.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p4.setListener(new VirtualView(new ServerConnectionTCP(null)));
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
        p1.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p2.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p3.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p4.setListener(new VirtualView(new ServerConnectionTCP(null)));
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
        p1.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p2.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p3.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p4.setListener(new VirtualView(new ServerConnectionTCP(null)));
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
        p1.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p2.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p3.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p4.setListener(new VirtualView(new ServerConnectionTCP(null)));
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
        p1.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p2.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p3.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p4.setListener(new VirtualView(new ServerConnectionTCP(null)));
        GameController Controller = new GameController(Players);
        Controller.startGame();
        ArrayList<Integer> E = new ArrayList<>();
        E.add(3);
        E.add(4);
        E.add(3);
        E.add(3);
        Move_SelectTiles M = new Move_SelectTiles();
        M.setCoordinates(E);
        ClientMessage m = new CoordinatesMessage(M);
        m.setNickname(Controller.getGame().getPlayers().get(0).getNickName());
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
        p1.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p2.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p3.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p4.setListener(new VirtualView(new ServerConnectionTCP(null)));
        GameController Controller = new GameController(Players);
        Controller.startGame();
        ArrayList<Integer> E = new ArrayList<>();
        E.add(0);
        E.add(4);
        E.add(0);
        E.add(3);
        Move_SelectTiles M = new Move_SelectTiles();
        M.setCoordinates(E);
        ClientMessage m = new CoordinatesMessage(M);
        m.setNickname(Controller.getGame().getPlayers().get(0).getNickName());
        ValidMoveMessage valid= new ValidMoveMessage();
        assertEquals(valid,Controller.readMessage(m));
    }
    public void testReadMessage_SIXTH(){
        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");
        Player p3 = new Player("Carlos");
        Player p4 = new Player("Diego");
        ArrayList<Player> Players = new ArrayList<>();
        Players.add(p1);
        Players.add(p2);
        Players.add(p3);
        Players.add(p4);
        p1.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p2.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p3.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p4.setListener(new VirtualView(new ServerConnectionTCP(null)));
        GameController Controller = new GameController(Players);
        Controller.startGame();
        Controller.getGame().getPlayers().get(0).getPlayerBookshelf().setTile(5, 0, new ItemTile());
        Controller.getGame().getPlayers().get(0).getPlayerBookshelf().setTile(4, 0, new ItemTile());
        Controller.getGame().getPlayers().get(0).getPlayerBookshelf().setTile(3, 0, new ItemTile());
        Controller.getGame().getPlayers().get(0).getPlayerBookshelf().setTile(2, 0, new ItemTile());
        Controller.getGame().getPlayers().get(0).getPlayerBookshelf().setTile(1, 0, new ItemTile());
        Controller.getCoordinates().add(0);
        Controller.getCoordinates().add(4);
        Controller.getCoordinates().add(0);
        Controller.getCoordinates().add(3);
        Move_SelectColumn M = new Move_SelectColumn();
        M.setYBookshelf(0);
        ClientMessage m = new ClientMessage(Message.MessageCategory.COLUMN,M,Controller.getGame().getPlayers().get(0).getNickName());
        Message error= new ErrorMessage();
        error.setReturnMessage("The move you made isn't a valid move");
        assertEquals(error,Controller.readMessage(m));
    }
    public void testReadMessage_SEVENTH(){
        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");
        Player p3 = new Player("Carlos");
        Player p4 = new Player("Diego");
        ArrayList<Player> Players = new ArrayList<>();
        Players.add(p1);
        Players.add(p2);
        Players.add(p3);
        Players.add(p4);
        p1.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p2.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p3.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p4.setListener(new VirtualView(new ServerConnectionTCP(null)));
        GameController Controller = new GameController(Players);
        Controller.startGame();
        Controller.getCoordinates().add(0);
        Controller.getCoordinates().add(4);
        Controller.getCoordinates().add(0);
        Controller.getCoordinates().add(3);
        Move_SelectColumn M = new Move_SelectColumn();
        M.setYBookshelf(0);
        ClientMessage m = new ClientMessage(Message.MessageCategory.COLUMN,M,Controller.getGame().getPlayers().get(0).getNickName());
        ValidMoveMessage valid= new ValidMoveMessage();
        assertEquals(valid,Controller.readMessage(m));
    }
    public void testReadMessage_EIGHTH(){
        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");
        Player p3 = new Player("Carlos");
        Player p4 = new Player("Diego");
        ArrayList<Player> Players = new ArrayList<>();
        Players.add(p1);
        Players.add(p2);
        Players.add(p3);
        Players.add(p4);
        p1.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p2.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p3.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p4.setListener(new VirtualView(new ServerConnectionTCP(null)));
        GameController Controller = new GameController(Players);
        Controller.startGame();
        Controller.getCoordinates().add(0);
        Controller.getCoordinates().add(4);
        Controller.getCoordinates().add(0);
        Controller.getCoordinates().add(3);
        ArrayList<Integer> E = new ArrayList<>();
        E.add(4);
        E.add(2);
        Move_SelectOrder M = new Move_SelectOrder();
        M.setOrder(E);
        ClientMessage m = new ClientMessage(Message.MessageCategory.ORDER,M,Controller.getGame().getPlayers().get(0).getNickName());
        Message error= new ErrorMessage();
        error.setReturnMessage("The move you made isn't a valid move");
        assertEquals(error,Controller.readMessage(m));
    }
    public void testReadMessage_NINTH(){
        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");
        Player p3 = new Player("Carlos");
        Player p4 = new Player("Diego");
        ArrayList<Player> Players = new ArrayList<>();
        Players.add(p1);
        Players.add(p2);
        Players.add(p3);
        Players.add(p4);
        p1.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p2.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p3.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p4.setListener(new VirtualView(new ServerConnectionTCP(null)));
        GameController Controller = new GameController(Players);
        Controller.startGame();
        Controller.getCoordinates().add(0);
        Controller.getCoordinates().add(4);
        Controller.getCoordinates().add(0);
        Controller.getCoordinates().add(3);
        ArrayList<Integer> E = new ArrayList<>();
        E.add(1);
        E.add(2);
        E.add(3);
        Move_SelectOrder M = new Move_SelectOrder();
        M.setOrder(E);
        ClientMessage m = new ClientMessage(Message.MessageCategory.ORDER,M,Controller.getGame().getPlayers().get(0).getNickName());
        Message error= new ErrorMessage();
        error.setReturnMessage("The move you made isn't a valid move");
        assertEquals(error,Controller.readMessage(m));
    }
    public void testReadMessage_TENTH(){
        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");
        Player p3 = new Player("Carlos");
        Player p4 = new Player("Diego");
        ArrayList<Player> Players = new ArrayList<>();
        Players.add(p1);
        Players.add(p2);
        Players.add(p3);
        Players.add(p4);
        p1.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p2.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p3.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p4.setListener(new VirtualView(new ServerConnectionTCP(null)));
        GameController Controller = new GameController(Players);
        Controller.startGame();
        Controller.getCoordinates().add(0);
        Controller.getCoordinates().add(4);
        Controller.getCoordinates().add(0);
        Controller.getCoordinates().add(3);
        ArrayList<Integer> E = new ArrayList<>();
        E.add(1);
        E.add(2);
        Move_SelectOrder M = new Move_SelectOrder();
        M.setOrder(E);
        Controller.setColumn(3);
        ClientMessage m = new ClientMessage(Message.MessageCategory.ORDER,M,Controller.getGame().getPlayers().get(0).getNickName());
        ValidMoveMessage valid= new ValidMoveMessage();
        assertEquals(valid,Controller.readMessage(m));
    }
    public void testReadMessage_ELEVENTH(){
        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");
        Player p3 = new Player("Carlos");
        Player p4 = new Player("Diego");
        ArrayList<Player> Players = new ArrayList<>();
        Players.add(p1);
        Players.add(p2);
        Players.add(p3);
        Players.add(p4);
        p1.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p2.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p3.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p4.setListener(new VirtualView(new ServerConnectionTCP(null)));
        GameController Controller = new GameController(Players);
        Controller.startGame();
        Controller.getCoordinates().add(0);
        Controller.getCoordinates().add(4);
        Controller.getCoordinates().add(0);
        Controller.getCoordinates().add(3);
        ArrayList<Integer> E = new ArrayList<>();
        E.add(1);
        E.add(2);
        Move_SelectOrder M = new Move_SelectOrder();
        M.setOrder(E);
        Controller.setColumn(3);
        ItemTile i = Controller.getGame().getLivingRoom().getBoardTile(0,4).getTile();
        ItemTile it = Controller.getGame().getLivingRoom().getBoardTile(0,3).getTile();
        ClientMessage m = new ClientMessage(Message.MessageCategory.ORDER,M,Controller.getGame().getPlayers().get(0).getNickName());
        Controller.readMessage(m);
        assertEquals(i,Controller.getGame().getPlayers().get(0).getPlayerBookshelf().getTile(5,3));
        assertEquals(it,Controller.getGame().getPlayers().get(0).getPlayerBookshelf().getTile(4,3));
    }
    public void testReadMessage_TWELFTH(){
        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");
        Player p3 = new Player("Carlos");
        Player p4 = new Player("Diego");
        ArrayList<Player> Players = new ArrayList<>();
        Players.add(p1);
        Players.add(p2);
        Players.add(p3);
        Players.add(p4);
        p1.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p2.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p3.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p4.setListener(new VirtualView(new ServerConnectionTCP(null)));
        GameController Controller = new GameController(Players);
        Controller.startGame();
        Controller.getCoordinates().add(0);
        Controller.getCoordinates().add(4);
        Controller.getCoordinates().add(0);
        Controller.getCoordinates().add(3);
        ArrayList<Integer> E = new ArrayList<>();
        E.add(2);
        E.add(1);
        Move_SelectOrder M = new Move_SelectOrder();
        M.setOrder(E);
        Controller.setColumn(3);
        ItemTile i = Controller.getGame().getLivingRoom().getBoardTile(0,4).getTile();
        ItemTile it = Controller.getGame().getLivingRoom().getBoardTile(0,3).getTile();
        ClientMessage m = new ClientMessage(Message.MessageCategory.ORDER,M,Controller.getGame().getPlayers().get(0).getNickName());
        Controller.readMessage(m);
        assertEquals(it,Controller.getGame().getPlayers().get(0).getPlayerBookshelf().getTile(5,3));
        assertEquals(i,Controller.getGame().getPlayers().get(0).getPlayerBookshelf().getTile(4,3));
    }
}