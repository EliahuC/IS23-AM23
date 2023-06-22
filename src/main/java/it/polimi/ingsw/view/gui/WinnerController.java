package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.model.player.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class WinnerController {

    @FXML
    Button menuButton;
    @FXML
    private GridPane leaderbord;
    @FXML
    private Label winnerLabel;
    private String winner;
    private Stage stage;
    private Scene scene;

    private void printLeaderbord(){

        leaderbord = new GridPane();
        //SETTARE IL GAMECONTROLLERGUI CORRETTO(GUARDA PER GOALSCONTROLLER)
        //int numPlayers = gameControllerGUI.getPlayers().size();
        int numPlayers = 2;
        int pos = 0;

        switch (numPlayers) {
            case 2: {
                for (int i = 0; i < 2; i++) {
                    pos = i + 2;
                    Label label = new Label();
                    label.setText(pos + "° position: "); // + player.getNickname()
                    leaderbord.add(label, i, 0);
                }
            }
            case 3: {
                for (int i = 0; i < 3; i++) {
                    pos = i + 2;
                    Label label = new Label();
                    label.setText(pos + "° position: "); // + player.getNickname()
                    leaderbord.add(label, i, 0);
                }
            }
            case 4: {
                for (int i = 0; i < 4; i++) {
                    pos = i + 2;
                    Label label = new Label();
                    label.setText(pos + "° position: "); // + player.getNickname()
                    leaderbord.add(label, i, 0);
                }
            }
        }
    }

    public void printWinner(){

        winnerLabel = new Label();
        winnerLabel.setText("!"); // gameControllerGUI.getWinner().getNickname() +
    }

    public void returnToMenu(ActionEvent event) throws IOException {

        File file = new File("src/main/resources/com/example/is23am23/menu.fxml");
        URL url = file.toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
/*
    public List<Player> getLeaderbord(){
        return list;
    }*/

    public void setWinner(String string){
        this.winner = string;
    }

    public String getWinner(){
        return winner;
    }
}
