package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.Network.Client.ConnectionClient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;

public class GUIMain extends Application{
    
    public void start(Stage stage) throws IOException {
        try {
            File file=new File("src/main/resources/com/example/is23am23/menu.fxml");
            URL url=file.toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root=loader.load();
            Scene mainMenu = new Scene(root);
         //   String css = this.getClass().getResource("application.css").toExternalForm();
           // mainMenu.getStylesheets().add(css);
            stage.setTitle("My Shelfie");
            stage.setScene(mainMenu);
            stage.show();
            //Image icon = new Image("little_icon.jpg");
            //stage.getIcons().add(icon);
            //stage.setFullScreen(true);

          /*  stage.setOnCloseRequest(event ->{
                event.consume();
                exit(stage);
            });*/
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    /*public void exit(){

        Stage stage;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit the game");
        alert.setHeaderText("You are about to exit the game");
        alert.setContentText("Are you sure?");

        if(alert.showAndWait().get() == ButtonType.OK){
            stage = (Stage)main.getScene().getWindow();
            stage.close();
        }
    }*/
}

