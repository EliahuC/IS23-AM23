package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.Network.Client.ConnectionClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Controller of the waiting scene
 * @author Andrea Bricchi and Giovanni di Lorenzo
 *
 */
public class LobbyWaitingController {

    private Stage stage;
    private Scene scene;
    @FXML
    private Label Display;
    private ConnectionClient connectionClient;

    /**
     * Method to go back to the menu
     * @author Andrea Bricchi
     * @param event click
     * @throws IOException exception
     *
     */
    public void returnToMenu(ActionEvent event) throws IOException {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("RETURN TO MENU");
            alert.setHeaderText("You are about to return to the menu, are you sure?");

            if (alert.showAndWait().get() == ButtonType.OK) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/is23am23/menu.fxml"));
                Parent root = loader.load();
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                connectionClient.closeConnection();
            }
    }

    public void displayNickname(String nickname) {
        Display.setText(nickname);
    }

    public void setConnectionClient(ConnectionClient connectionClient) {
        this.connectionClient=connectionClient;
    }
}
