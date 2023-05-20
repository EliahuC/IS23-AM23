package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.Network.Client.ConnectionClient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;
import java.net.Socket;

public class GUIMain extends Application{
   /* private String serverName;
    private int portNum;
    private Socket socket;
    private ConnectionClient connectionClient;
    */
    public void start(Stage stage) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
            Scene mainMenu = new Scene(root);
            String css = this.getClass().getResource("application.css").toExternalForm();
            mainMenu.getStylesheets().add(css);
            stage.setTitle("My Shelfie");
            stage.setScene(mainMenu);
            stage.show();
            //Image icon = new Image("little_icon.jpg");
            //stage.getIcons().add(icon);
            //stage.setFullScreen(true);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

