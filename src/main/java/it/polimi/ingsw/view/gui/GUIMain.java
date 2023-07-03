package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.view.View;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Class to starts the GUI
 * @author Andrea Bricchi
 *
 */
public class GUIMain extends Application implements View {
    private static String IP;

    /**
     * @author Andrea Bricchi
     * @param stage stage
     * @throws IOException if there is a IO problem
     */
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/is23am23/menu.fxml"));
            Parent root = loader.load();
            MenuController controller=loader.getController();
            controller.setIP(IP);
            Scene mainMenu = new Scene(root);
            stage.setTitle("My Shelfie");
            stage.setScene(mainMenu);
            stage.setResizable(false);
            stage.show();
            stage.centerOnScreen();
            Image icon = new Image("com/example/is23am23/little_icon.png");
            stage.getIcons().add(icon);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void run() {
        main(null);
    }

    /**
     * @author Eliahu Cohen
     * @param ip of the server to pass to the MenuController
     */
    @Override
    public void passIP(String ip) {
        IP=ip;
    }
}

