package it.polimi.ingsw.view.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class UsedNicknameController {

    @FXML
    TextField name;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void returnToMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
        root = loader.load();
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToLobbyWaiting(ActionEvent event) throws IOException {

        String nickname = name.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("lobbyWaiting.fxml"));
        root = loader.load();

        LobbyChoiceController lobbyController = loader.getController();
        lobbyController.displayNickname(nickname);

        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
