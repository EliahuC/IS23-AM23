package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.Messages.Message;
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
import java.io.IOException;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MenuController {

    @FXML
    TextField textField;
    @FXML
    Button exitButton;
    AnchorPane scenePane;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private String serverAddr;
    private int portNum;
    private Socket socket;
    private String nickname;
    private ConnectionClient connectionClient;
    private GUIEvent receiver;
/*
    public void goTCP(ActionEvent event) throws IOException {

        nickname = textField.getText();
        if(TCPOn()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("lobbyChoice.fxml"));
            root = loader.load();

            LobbyChoiceController lobbyController = loader.getController();
            lobbyController.displayNickname(nickname);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public boolean TCPOn(){

                    try {
                        serverAddr = Settings.SERVER_NAME;
                        portNum = Settings.PORT;
                        socket = new Socket(serverAddr, portNum);
                        connectionClient = new ClientConnectionTCP(socket, nickname);
                        receiver=new GUIEvent(this);
                        receiver.setInStartGUI(true);
                        connectionClient.setListener(receiver);
                        new Thread(connectionClient).start();
                        return true;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return false;
                    }
    }

     public boolean RMIOn(){
            try {
                receiver = new GUIEvent(this);
                receiver.setInStartGUI(true);
                connectionClient = new ClientConnectionRMI(nickname, receiver);

                    new Thread(connectionClient).start();
                    return true;
                } catch(IOException e){
                    e.printStackTrace();
                    return false;
                }
            }

    public void goRMI(ActionEvent event) throws IOException {

        String nickname = textField.getText();
        if(RMIOn()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("lobbyChoice.fxml"));
            root = loader.load();

            LobbyChoiceController lobbyController = loader.getController();
            lobbyController.displayNickname(nickname);

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
        }
    }*/
}
