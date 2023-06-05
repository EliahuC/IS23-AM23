package it.polimi.ingsw.view.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class UsedNicknameController {

    @FXML
    TextField name;

    private Stage stage;
    private Scene scene;
    private Parent root;

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

    public void goToLobbyWaiting(ActionEvent event) throws IOException {

        String nickname = name.getText();

        File file = new File("src/main/resources/com/example/is23am23/lobbyWaiting.fxml");
        URL url = file.toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        LobbyChoiceController lobbyController = loader.getController();
        lobbyController.displayNickname(nickname);

        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
