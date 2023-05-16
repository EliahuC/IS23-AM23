package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.Network.Client.ConnectionClient;
import it.polimi.ingsw.Settings;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;
import java.net.Socket;

public class GUIMain extends Application{
    Stage window;
    Scene homePage, lobbyChoice;
    private String serverName;
    private int portNum;
    private Socket socket;
    private ConnectionClient connectionClient;


    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws IOException {
        window = primaryStage;

        portNum = Settings.PORT;
        serverName = Settings.SERVER_NAME;

        //Elementi pagina iniziale
        Label label1 = new Label("Choose your preferred connection method");
        Button tcp = new Button("TCP");
        Button rmi = new Button("RMI");
        //BackgroundImage background = new BackgroundImage("sfondo parquet.png");

        //Elementi ricerca lobby
        Label label2 = new Label("Do you want to create a lobby or join an already existing one?");
        Button join = new Button("Join a lobby");
        Button create = new Button("Create a lobby");

        //Layout pagina iniziale
        VBox layout1 = new VBox(100);
        layout1.setAlignment(Pos.CENTER);
        layout1.getChildren().addAll(label1, tcp, rmi);
        homePage = new Scene(layout1, 600,600);


        //Layout scelta lobby
        VBox layout2 = new VBox(100);
        layout2.setAlignment(Pos.CENTER);
        layout2.getChildren().addAll(label2, join, create);
        lobbyChoice = new Scene(layout1, 600,600);


/*
        tcp.setOnAction(e -> {
            try {
                socket = new Socket(serverName, portNum);
                connectionClient = new ClientConnectionTCP(socket,nickname);
                new Thread(connectionClient).start();
            } catch (IOException e) {
                //AlarmBox con messaggio di errore "Impossible to connect to the server!"
            }
            try{
                TimeUnit.SECONDS.sleep(3);
            }catch (InterruptedException iE){
                iE.printStackTrace();
            }
            window.setScene(lobbyChoice);
        });


        rmi.setOnAction(e -> {
            connectionClient = new ClientConnectionRMI();
            new Thread(connectionClient).start();
            window.setScene(lobbyChoice);

        });
*/
        window.setScene(homePage);
        window.setTitle("My Shelfie");
        window.show();
        //Image Icon = new Image("Icon 50x50px.png");
        //window.getIcons().add(Icon);
        //window.setFullScreen(true);

    }
}
