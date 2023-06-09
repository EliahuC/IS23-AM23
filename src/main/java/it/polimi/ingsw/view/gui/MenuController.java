package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Network.Client.ConnectionClient;
import it.polimi.ingsw.Network.Client.RMI.ClientConnectionRMI;
import it.polimi.ingsw.Settings;
import it.polimi.ingsw.Network.Client.TCP.ClientConnectionTCP;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MenuController {

    @FXML
    private TextField textField;
    @FXML
    private Button exitButton;
    private AnchorPane scenePane;

    private Stage stage;
    private Scene scene;
    private String serverAddr;
    private int portNum;
    private Socket socket;
    private String nickname;
    private ConnectionClient connectionClient;
    private GUIEvent receiver;
    private ServerMessage response;



    public void goTCP(ActionEvent event) throws IOException {

        nickname = textField.getText();
        if (TCPOn()) {
            File file = new File("src/main/resources/com/example/is23am23/lobbyChoice.fxml");
            URL url = file.toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            LobbyChoiceController lobbyController = loader.getController();
            lobbyController.displayNickname(nickname);
            lobbyController.setConnection(connectionClient);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public boolean TCPOn() {

        try {
            serverAddr = Settings.SERVER_NAME;
            portNum = Settings.PORT;
            socket = new Socket("127.0.0.1", portNum);
            connectionClient = new ClientConnectionTCP(socket, nickname);
            receiver = new GUIEvent(this);
            receiver.setInStartGUI(true);
            connectionClient.setListener(receiver);
            new Thread(connectionClient).start();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean RMIOn() {
        try {
            receiver = new GUIEvent(this);
            receiver.setInStartGUI(true);
            connectionClient = new ClientConnectionRMI(nickname, receiver);
            new Thread(connectionClient).start();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void goRMI(ActionEvent event) throws IOException {

        String nickname = textField.getText();
        if (RMIOn()) {
            File file = new File("src/main/resources/com/example/is23am23/lobbyChoice.fxml");
            URL url = file.toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            LobbyChoiceController lobbyController = loader.getController();
            lobbyController.displayNickname(nickname);
            lobbyController.setConnection(connectionClient);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
    }

    public void exit(){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit the game");
        alert.setHeaderText("You are about to exit the game");
        alert.setContentText("Are you sure?");

        if(alert.showAndWait().get() == ButtonType.OK){
            stage = (Stage)scenePane.getScene().getWindow();
            stage.close();
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setResponse(ServerMessage response) {
        this.response = response;
    }
}

