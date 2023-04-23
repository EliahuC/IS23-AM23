package it.polimi.ingsw.controller;

public class ControllerCoordinator {
    private final GameController gameController =new GameController();
    private final ConnectionController connectionController = new ConnectionController();
    EndGameController endGameController=new EndGameController(gameController.endGame());
}
