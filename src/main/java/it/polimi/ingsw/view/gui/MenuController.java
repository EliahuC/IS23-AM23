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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * @author Andrea Bricchi and Giovanni di Lorenzo
 * Controller of the user login page
 */

public class MenuController {

    @FXML
    private TextField textField_nickname;
    @FXML
    private TextField textField_IP;
    private Stage stage;
    private Scene scene;
    private String serverAddr;
    private int portNum;
    private Socket socket;
    private String nickname;
    private ConnectionClient connectionClient;
    private GUIEvent receiver;
    private ServerMessage response;

    /**
     * @author Andrea Bricchi and Giovanni di Lorenzo
     * @param event click
     * @throws IOException
     * Method to start a Socket Connection
     */
    public void goTCP(ActionEvent event) throws IOException {
        nickname = textField_nickname.getText();
        if (!nickname.isEmpty()) {
            if (TCPon(event)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/is23am23/lobbyChoice.fxml"));
                Parent root = loader.load();
                LobbyChoiceController lobbyController = loader.getController();
                receiver.setLobbyChoiceController(lobbyController);
                lobbyController.displayNickname(nickname);
                lobbyController.setConnection(connectionClient);
                lobbyController.setReceiver(receiver);
                //loadGameFXML();
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("CONNECTION ERROR");
                alert.setHeaderText("Unable to connect to the server, check your connection!");
                if (alert.showAndWait().get() == ButtonType.OK) {

                }
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("INVALID NICKNAME");
            alert.setHeaderText("You forgot to insert your nickname!");
            if (alert.showAndWait().get() == ButtonType.OK) {

            }
        }
    }

    /**
     * @author Andrea Bricchi and Giovanni di Lorenzo
     * @param event click
     * @return true if connection starts
     */
    public boolean TCPon(ActionEvent event) {

        try {
            receiver = new GUIEvent(this);
            receiver.setInStartGUI(true);
            serverAddr = textField_IP.getText();
            if(serverAddr.isEmpty())
                serverAddr = Settings.SERVER_NAME;
            portNum = Settings.PORT;
            socket = new Socket(serverAddr, portNum);
            connectionClient = new ClientConnectionTCP(socket, nickname);
            connectionClient.setListener(receiver);
            new Thread(connectionClient).start();
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException iE) {
                iE.printStackTrace();
            }
            if(response.getCategory()==Message.MessageCategory.VALID_NICKNAME){
                return true;
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/is23am23/usedNickname.fxml"));
            Parent root = loader.load();
            UsedNicknameController usedNicknameController = loader.getController();
            receiver.setUsedNicknameController(usedNicknameController);
            receiver.setInUsedNickname(true);
            usedNicknameController.setReceiver(receiver);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            return false;
        }
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * @author Andrea Bricchi and Giovanni di Lorenzo
     * @param event click
     * @return true if connection starts
     */
    public boolean RMIon(ActionEvent event) {
        try {
            receiver = new GUIEvent(this);
            receiver.setInStartGUI(true);
            serverAddr = textField_IP.getText();
            if(serverAddr.isEmpty())
                serverAddr = Settings.SERVER_NAME;
            connectionClient = new ClientConnectionRMI(nickname, receiver, serverAddr);
            new Thread(connectionClient).start();
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException iE) {
                iE.printStackTrace();
            }
            if(response.getCategory()==Message.MessageCategory.VALID_NICKNAME){
                return true;
            } else {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/is23am23/usedNickname.fxml"));
            Parent root = loader.load();
            UsedNicknameController usedNicknameController = loader.getController();
            receiver.setUsedNicknameController(usedNicknameController);
            receiver.setInUsedNickname(true);
            usedNicknameController.setReceiver(receiver);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            return false;
        }
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * @author Andrea Bricchi and Giovanni di Lorenzo
     * @param event click
     * @throws IOException
     * Method to start a RMI Connection
     */
    public void goRMI(ActionEvent event) throws IOException {

        nickname = textField_nickname.getText();
        if (!nickname.isEmpty()) {
            if (RMIon(event)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/is23am23/lobbyChoice.fxml"));
                Parent root = loader.load();

                LobbyChoiceController lobbyController = loader.getController();
                receiver.setLobbyChoiceController(lobbyController);
                lobbyController.displayNickname(nickname);
                lobbyController.setConnection(connectionClient);
                lobbyController.setReceiver(receiver);
                //loadGameFXML();

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("CONNECTION ERROR");
                alert.setHeaderText("Unable to connect to the server, check your connection!");
                if (alert.showAndWait().get() == ButtonType.OK) {

                }
            }

        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("INVALID NICKNAME");
            alert.setHeaderText("You forgot to insert your nickname!");
            if (alert.showAndWait().get() == ButtonType.OK) {

            }
        }
    }



    public Stage getStage(){
        return stage;
    }

    public void setResponse(ServerMessage response) {
        this.response = response;
    }

    public String getNickname(){
        return nickname;
    }

    public void setIP(String ip) {
        this.serverAddr=ip;
    }
}
