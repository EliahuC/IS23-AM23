package it.polimi.ingsw.view.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;

public class MenuController {

    @FXML
    TextField textField;
    @FXML
    Button exitButton;
    AnchorPane scenePane;



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
    /*
    public boolean isTCPOn{

    }

    public boolean isRMIOn{

    }
*/

    public void exit(){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit the game");
        alert.setHeaderText("You are about to exit the game");
        alert.setContentText("Are you sure?");

        if(alert.showAndWait().get() == ButtonType.OK){
            stage = (Stage)scenePane.getScene().getWindow();
            stage.close();
        }
    }
}