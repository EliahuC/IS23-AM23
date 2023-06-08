package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.MoveSerializer;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Network.Client.ConnectionClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class LobbyChoiceController implements Initializable {
    @FXML
    private Label nameDisplay;
    @FXML
    private ChoiceBox <String> playerNumber;

    private String[] number ={"2","3","4"};

    private Stage stage;
    private Scene scene;
    private ServerMessage response;
    private Boolean lock=true;
    private Parent root;
    private int PlayersCounter;

    public void goToLobbyWaiting(ActionEvent event) throws IOException {

        File file = new File("src/main/resources/com/example/is23am23/lobbyWaiting.fxml");
        URL url = file.toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void returnToMenu(ActionEvent event) throws IOException {

        File file = new File("src/main/resources/com/example/is23am23/menu.fxml");
        URL url = file.toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void setPlayersCounter(int playersCounter) {
        PlayersCounter = playersCounter;
    }

    public void displayNickname(String nickname){
        nameDisplay.setText("Welcome " + nickname + "!");
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        playerNumber.getItems().addAll(number);
    }

}
