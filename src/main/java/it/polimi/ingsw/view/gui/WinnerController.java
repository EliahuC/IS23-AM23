package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.model.player.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller of the winning scene
 * @author Andrea Bricchi
 *
 */
public class WinnerController implements Initializable {

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
     * Method to display the ranking of the players
     * @author Andrea Bricchi and Giovanni Di Lorenzo
     * @param ranking of players
     *
     */
    public void displayLeaderbord(List<Player> ranking) {

        int i = -1;
        for (Player p : ranking) {
            if (i >= 0) {
                Label label = new Label();
                label.setText(p.getNickName() + "\n Points: " + p.getScore());
                label.setFont(new Font(30));
                leaderbord.add(label, i, 0);
            }
            else {
                displayWinner(p.getNickName() + "\n Points: " + p.getScore());
            }
            i++;
        }
    }

    /**
     * Method to display the name of the winner
     * @author Andrea Bricchi
     * @param winner name of the player who wins
     *
     */
    public void displayWinner(String winner){
        this.winner = winner;
        winnerLabel.setText(winner + "!");
    }

    /**
     * Method to return to the menu  scene
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
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
