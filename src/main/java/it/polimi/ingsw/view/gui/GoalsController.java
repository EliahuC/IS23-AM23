package it.polimi.ingsw.view.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.IOException;

public class GoalsController {

    @FXML
    ImageView personalGoal;
    @FXML
    ImageView commonGoal1;
    @FXML
    ImageView commonGoal2;
    @FXML
    ImageView points1;
    @FXML
    ImageView points2;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void goToLivingroom(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        root = loader.load();
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /*
    public void displayPoints{
        //Reinserire gli id delle immagini
    }

    public void diplayPersonalGoal{
        //Reinserire gli id delle immagini

    }

    public void displayCommonGoal{
        //Reinserire gli id delle immagini

    }
    */
}
