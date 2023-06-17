package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.MoveSerializer;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Network.Client.ConnectionClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class LobbyWaitingController {
    private ServerMessage response;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label Display;

    private ConnectionClient connectionClient;
    private GUIEvent receiver;

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

    /*public LobbyWaitingController(ConnectionClient connectionClient, GUIEvent receiver) {
        this.connectionClient = connectionClient;
        this.receiver = receiver;
        this.receiver.setInLobbyWaiting(true);
        connectionClient.setListener(receiver);
        this.receiver.setLobbyWaitingcontroller(this);
    }*/
    public void waiting(GUIEvent receiver) throws IOException {
             File file = new File("src/main/resources/com/example/is23am23/game.fxml");
             URL url = file.toURI().toURL();
             FXMLLoader loader = new FXMLLoader(url);
             Parent root = loader.load();
         }
              //sennò aspetto


            //gamecontrollerGUI.start();


    public void setResponse(ServerMessage response) {
        this.response = response;
    }
    public void displayNickname(String nickname) {
        Display.setText(nickname);
    }
    public void setReceiver(GUIEvent receiver){
        this.receiver=receiver;
    }

}
