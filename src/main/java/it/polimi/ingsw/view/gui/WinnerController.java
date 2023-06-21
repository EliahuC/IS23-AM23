package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.model.player.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.util.List;

public class WinnerController {

    @FXML
    private GridPane leaderbord;
    @FXML
    private Label winnerLabel;
    private String winner;

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
