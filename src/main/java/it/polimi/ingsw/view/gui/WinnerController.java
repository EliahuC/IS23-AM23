package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.model.player.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * @author Andrea Bricchi
 * Controller of the winning scene
 */
public class WinnerController {

    @FXML
    Button menuButton;
    @FXML
    private GridPane leaderbord;
    @FXML
    private Label winnerLabel;
    private String winner;
    private Scene scene;
    private Stage stage;

    /**
     * @author Andrea Bricchi and Giovanni Di Lorenzo
     * @param ranking of players
     * Method to display the ranking of the players
     */
    public void displayLeaderbord(List<Player> ranking) {

        leaderbord = new GridPane();
        int pos = 2;
        int i = -1;
        for (Player p : ranking) {
            if (i >= 0) {
                Label label = new Label();
                label.setText(pos + "° position: " + p.getNickName());
                leaderbord.add(label, i, 0);
            }
            i++;
        }
        for (Player p : ranking) {
            if (p.isWinner()) {
                displayWinner(p.getNickName());
                break;
            }
        }
    }

    /**
     * @author Andrea Bricchi
     * @param winner name of the player who wins
     * Method to disply the name of the winner
     */
    public void displayWinner(String winner){
        this.winner = winner;
        winnerLabel = new Label();
        winnerLabel.setText(winner + "!");
    }

    /**
     * @author Andrea Bricchi
     * @param event click
     * @throws IOException exception
     * Method to return to the menu  scene
     */
    public void returnToMenu(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("RETURN TO MENU");
        alert.setHeaderText("You are about to return to the menu, are you sure?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            File file = new File("src/main/resources/com/example/is23am23/menu.fxml");
            URL url = file.toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}
