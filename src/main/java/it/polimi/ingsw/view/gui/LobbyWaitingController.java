package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.MoveSerializer;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Network.Client.ConnectionClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class LobbyWaitingController {
    private final ConnectionClient connectionClient;
    private final GUIEvent receiver;
    private ServerMessage response;
    private Boolean lock=true;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private int PlayersCounter;
    private Button button_NewGame;
    private Button button_JoinGame;

    public void returnToMenu(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
        root = loader.load();
        button_NewGame = new Button("New game");
        button_JoinGame = new Button("Join in a game");
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        //bisogna aggiungere i bottoni alla scena
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public LobbyWaitingController(ConnectionClient connectionClient, GUIEvent receiver) {
        this.connectionClient = connectionClient;
        this.receiver=receiver;
        this.receiver.setInLobbyWaiting(true);
        connectionClient.setListener(receiver);
        this.receiver.setLobbywaitingcontroller(this);
    }
    public void setResponse(ServerMessage response){
        this.response=response;
    }

    public void start() {
        GameControllerGUI gamecontrollerGUI = new GameControllerGUI();
        receiver.setGamecontrollerGUI(gamecontrollerGUI);
        ServerMessage serverMessage;





    }

}
