package it.polimi.ingsw.view.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;

public class MenuController {

    @FXML
    TextField textField;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void goTCP(ActionEvent event) throws IOException {

        String nickname = textField.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("lobbyChoice.fxml"));
        root = loader.load();

        LobbyChoiceController lobbyController = loader.getController();
        lobbyController.displayNickname(nickname);

        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goRMI(ActionEvent event) throws IOException {

        String nickname = textField.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("lobbyChoice.fxml"));
        root = loader.load();

        LobbyChoiceController lobbyController = loader.getController();
        lobbyController.displayNickname(nickname);

        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
