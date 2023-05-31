package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.model.player.Player;
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
import java.net.URL;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LobbyChoiceController implements Initializable {
    @FXML
    Label nameDisplay;
    @FXML
    ChoiceBox<Integer> playerNumber;

    String number[] = {"2", "3", "4"};

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void goToLobbyWaiting(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("src/main/resources/com/example/is23am23/lobbyWaiting.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void returnToMenu(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("src/main/resources/com/example/is23am23/menu.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void displayNickname(String nickname) {
        nameDisplay.setText("Welcome " + nickname + "!");
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        //playerNumber.getItems().addAll(number);
        //int numPlayers =
        //setPlayersCounter(numPlayers);
    }
/*
    public void setPlayersCounter(int playersCounter) {
        PlayersCounter = playersCounter;
    }

    public ArrayList<Player> getPlayers() {
        return playerList;
    }*/

}


