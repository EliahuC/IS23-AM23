package it.polimi.ingsw.controller;

import it.polimi.ingsw.Network.Server.TCP.ServerConnectionTCP;
import it.polimi.ingsw.Network.Server.VirtualView;
import junit.framework.TestCase;


public class ControllerCoordinatorTest extends TestCase {

    public void testJoinPlayers_FIRST(){
        ControllerCoordinator controllerCoordinator = new ControllerCoordinator();
        assertTrue(controllerCoordinator.getConnectedPlayers().isEmpty());
    }
    public void testJoinPlayers_SECOND(){
        ControllerCoordinator controllerCoordinator = new ControllerCoordinator();
        controllerCoordinator.joinPlayer("Bob",new VirtualView(new ServerConnectionTCP(null)));
        assertEquals(1,controllerCoordinator.getConnectedPlayers().size());
    }
    public void testJoinPlayers_THIRD(){
        ControllerCoordinator controllerCoordinator = new ControllerCoordinator();
        controllerCoordinator.joinPlayer("Bob",new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Alice",new VirtualView(new ServerConnectionTCP(null)));
        assertEquals(2,controllerCoordinator.getConnectedPlayers().size());
    }
    public void testJoinPlayers_FOURTH(){
        ControllerCoordinator controllerCoordinator = new ControllerCoordinator();
        controllerCoordinator.joinPlayer("Bob",new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Alice",new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Alice",new VirtualView(new ServerConnectionTCP(null)));
        assertEquals(2,controllerCoordinator.getConnectedPlayers().size());
    }
    public void testJoinPlayers_FIFTH(){
        ControllerCoordinator controllerCoordinator = new ControllerCoordinator();
        controllerCoordinator.joinPlayer("Bob",new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Alice",new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Stefano",new VirtualView(new ServerConnectionTCP(null)));
        assertEquals(3,controllerCoordinator.getConnectedPlayers().size());
    }
    public void testJoinPlayers_SIXTH(){
        ControllerCoordinator controllerCoordinator = new ControllerCoordinator();
        controllerCoordinator.joinPlayer("Bob",new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Alice",new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Stefano",new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Stefaniel",new VirtualView(new ServerConnectionTCP(null)));
        assertEquals(4,controllerCoordinator.getConnectedPlayers().size());
    }
    public void testJoinPlayers_SEVENTH(){
        ControllerCoordinator controllerCoordinator = new ControllerCoordinator();
        controllerCoordinator.joinPlayer("Bob",new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Alice",new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Stefano",new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Stefaniel",new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Marco",new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Diego",new VirtualView(new ServerConnectionTCP(null)));
        assertEquals(6,controllerCoordinator.getConnectedPlayers().size());
    }
    public void testStartGame_FIRST(){
        ControllerCoordinator controllerCoordinator = new ControllerCoordinator();
        controllerCoordinator.joinPlayer("Bob",new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Alice",new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Stefano",new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.joinPlayer("Stefaniel",new VirtualView(new ServerConnectionTCP(null)));
        controllerCoordinator.startGame();
        assertTrue(controllerCoordinator.getStartedGame());
    }
}