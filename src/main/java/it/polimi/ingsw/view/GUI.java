package it.polimi.ingsw.view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;

public class GUI extends Application{
    Stage window;
    Scene scene1, scene2;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws IOException {
        window = primaryStage;

        Label label1 = new Label("Welcome!");
        Button button1 = new Button("Login");
        button1.setOnAction(e -> window.setScene(scene2));

        VBox layout1 = new VBox(100);
        layout1.setAlignment(Pos.CENTER);
        layout1.getChildren().addAll(label1, button1);
        scene1 = new Scene(layout1, 600,600);

        Button button2 = new Button("Main Menu");
        button2.setOnAction(e -> window.setScene(scene1));

        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        scene2 = new Scene(layout2, 600, 300);

        window.setScene(scene1);
        window.setTitle("My Shelfie");
        window.show();

         Image Icon = new Image("Icon 50x50px.png");
        window.getIcons().add(Icon);
        window.setFullScreen(true);
    }
}
