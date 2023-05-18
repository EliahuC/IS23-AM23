package it.polimi.ingsw.controller;

import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.ClientToServer.PossibleMoves.Move_SelectTiles;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.ServerToClient.ErrorMessage;
import it.polimi.ingsw.Network.Server.TCP.ServerConnectionTCP;
import it.polimi.ingsw.Network.Server.VirtualView;
import it.polimi.ingsw.model.player.Player;
import junit.framework.TestCase;

import java.util.ArrayList;


public class ControllerCoordinatorTest extends TestCase {

    public void testJoinPlayers_FIRST() {
        ControllerCoordinator controllerCoordinator = new ControllerCoordinator();
        assertTrue(controllerCoordinator.getConnectedPlayers().isEmpty());
    }

    public void testJoinPlayers_SECOND() {
        ControllerCoordinator controllerCoordinator = new ControllerCoordinator();
        controllerCoordinator.joinPlayer("Bob", new VirtualView(new ServerConnectionTCP(null)));
        assertEquals(1, controllerCoordinator.getConnectedPlayers().size());
    }

    public void testJoinPlayers_THIRD() {
        ControllerCoordinator controllerCoordinator = new ControllerCoordinator();
        controllerCoordinator.joinPlayer("Bob", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Alice", new VirtualView(new ServerConnectionTCP(null)));
        assertEquals(2, controllerCoordinator.getConnectedPlayers().size());
    }

    public void testJoinPlayers_FOURTH() {
        ControllerCoordinator controllerCoordinator = new ControllerCoordinator();
        controllerCoordinator.joinPlayer("Bob", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Alice", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Alice", new VirtualView(new ServerConnectionTCP(null)));
        assertEquals(2, controllerCoordinator.getConnectedPlayers().size());
    }

    public void testJoinPlayers_FIFTH() {
        ControllerCoordinator controllerCoordinator = new ControllerCoordinator();
        controllerCoordinator.joinPlayer("Bob", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Alice", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Stefano", new VirtualView(new ServerConnectionTCP(null)));
        assertEquals(3, controllerCoordinator.getConnectedPlayers().size());
    }

    public void testJoinPlayers_SIXTH() {
        ControllerCoordinator controllerCoordinator = new ControllerCoordinator();
        controllerCoordinator.joinPlayer("Bob", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Alice", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Stefano", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Stefaniel", new VirtualView(new ServerConnectionTCP(null)));
        assertEquals(4, controllerCoordinator.getConnectedPlayers().size());
    }

    public void testJoinPlayers_SEVENTH() {
        ControllerCoordinator controllerCoordinator = new ControllerCoordinator();
        controllerCoordinator.joinPlayer("Bob", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Alice", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Stefano", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Stefaniel", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Marco", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Diego", new VirtualView(new ServerConnectionTCP(null)));
        assertEquals(6, controllerCoordinator.getConnectedPlayers().size());
    }

    public void testStartGame_FIRST() {
        ControllerCoordinator controllerCoordinator = new ControllerCoordinator();
        controllerCoordinator.joinPlayer("Bob", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Alice", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Stefano", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Stefaniel", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.startGame();
        assertTrue(controllerCoordinator.getStartedGame());
        assertTrue(controllerCoordinator.getGameController().isStartedGame());
        assertTrue(controllerCoordinator.getGameController().getGame().isStartedGame());
    }

    public void testStartGame_SECOND() {
        ControllerCoordinator controllerCoordinator = new ControllerCoordinator();
        controllerCoordinator.joinPlayer("Bob", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Alice", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Stefano", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Stefaniel", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.startGame();
        assertTrue(controllerCoordinator.getGameController().getGame().getPlayers().get(0).isFirstPlayerSeat());
    }

    public void testStartGame_THIRD() {
        ControllerCoordinator controllerCoordinator = new ControllerCoordinator();
        controllerCoordinator.joinPlayer("Bob", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Alice", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Stefano", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Stefaniel", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.startGame();
        assertFalse(controllerCoordinator.getGameController().getGame().getPlayers().get(1).isFirstPlayerSeat());
        assertFalse(controllerCoordinator.getGameController().getGame().getPlayers().get(2).isFirstPlayerSeat());
        assertFalse(controllerCoordinator.getGameController().getGame().getPlayers().get(3).isFirstPlayerSeat());
    }

    public void testSetMessage_FIRST() {
        ControllerCoordinator controllerCoordinator = new ControllerCoordinator();
        controllerCoordinator.joinPlayer("Bob", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Alice", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Stefano", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Stefaniel", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.startGame();
        Move_SelectTiles M = new Move_SelectTiles();
        M.setCoordinates(new ArrayList<Integer>());
        ClientMessage m = new ClientMessage(Message.MessageCategory.COORDINATES, M, controllerCoordinator.getGameController()
                .getGame().getPlayers().get(1).getNickName());
        Message error = new ErrorMessage();
        error.setReturnMessage("It's not your turn");
        assertEquals(error, controllerCoordinator.getGameController().readMessage(m));
    }

    public void testSetMessage_SECOND() {
        ControllerCoordinator controllerCoordinator = new ControllerCoordinator();
        controllerCoordinator.joinPlayer("Bob", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Alice", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Stefano", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Stefaniel", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.startGame();
        Move_SelectTiles M = new Move_SelectTiles();
        M.setCoordinates(new ArrayList<Integer>());
        ClientMessage m = new ClientMessage(Message.MessageCategory.COLUMN, M, controllerCoordinator.getGameController()
                .getGame().getPlayers().get(1).getNickName());
        Message error = new ErrorMessage();
        error.setReturnMessage("It's not your turn");
        assertEquals(error, controllerCoordinator.getGameController().readMessage(m));
    }

    public void testSetMessage_THIRD() {
        ControllerCoordinator controllerCoordinator = new ControllerCoordinator();
        controllerCoordinator.joinPlayer("Bob", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Alice", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Stefano", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Stefaniel", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.startGame();
        Move_SelectTiles M = new Move_SelectTiles();
        M.setCoordinates(new ArrayList<Integer>());
        ClientMessage m = new ClientMessage(Message.MessageCategory.ORDER, M, controllerCoordinator.getGameController()
                .getGame().getPlayers().get(1).getNickName());
        Message error = new ErrorMessage();
        error.setReturnMessage("It's not your turn");
        assertEquals(error, controllerCoordinator.getGameController().readMessage(m));
    }

    public void testSetMessage_FOURTH() {
        ControllerCoordinator controllerCoordinator = new ControllerCoordinator();
        controllerCoordinator.joinPlayer("Bob", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Alice", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Stefano", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Stefaniel", new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.startGame();
        ArrayList<Integer> E = new ArrayList<>();
        E.add(3);
        E.add(4);
        E.add(3);
        E.add(3);
        Move_SelectTiles M = new Move_SelectTiles();
        M.setCoordinates(E);
        ClientMessage m = new ClientMessage(Message.MessageCategory.COORDINATES, M, controllerCoordinator.getGameController()
                .getGame().getPlayers().get(0).getNickName());
        Message error = new ErrorMessage();
        error.setReturnMessage("The move you made isn't a valid move");
        assertEquals(error, controllerCoordinator.getGameController().readMessage(m));
    }
}