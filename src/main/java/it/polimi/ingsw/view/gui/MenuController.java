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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MenuController {

    @FXML
    private TextField textField;
    @FXML
    private ImageView backgroundImage;
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
        if (!nickname.isEmpty()) {
            if (TCPon(event)) {
                File file = new File("src/main/resources/com/example/is23am23/lobbyChoice.fxml");
                URL url = file.toURI().toURL();
                FXMLLoader loader = new FXMLLoader(url);
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
                //stage.setFullScreen(true);

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

    public boolean TCPon(ActionEvent event) {

        try {
            receiver = new GUIEvent(this);
            receiver.setInStartGUI(true);
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
            File file = new File("src/main/resources/com/example/is23am23/usedNickname.fxml");
            URL url = file.toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
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

    public boolean RMIon(ActionEvent event) {
        try {
            receiver = new GUIEvent(this);
            receiver.setInStartGUI(true);
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
            File file = new File("src/main/resources/com/example/is23am23/usedNickname.fxml");
            URL url = file.toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
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

    public void goRMI(ActionEvent event) throws IOException {

        nickname = textField.getText();
        if (!nickname.isEmpty()) {
            if (RMIon(event)) {
                File file = new File("src/main/resources/com/example/is23am23/lobbyChoice.fxml");
                URL url = file.toURI().toURL();
                FXMLLoader loader = new FXMLLoader(url);
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

    /*private void loadGameFXML() throws IOException {
        File game = new File("src/main/resources/com/example/is23am23/game.fxml");
        URL urlGame = game.toURI().toURL();
        FXMLLoader loaderGame = new FXMLLoader(urlGame);
        AnchorPane rootGame=loaderGame.getRoot();
        GameControllerGUI gameControllerGUI = loaderGame.getController();
        gameControllerGUI.setConnectionClient(connectionClient);
        receiver.setGamecontrollerGUI(gameControllerGUI);
        gameControllerGUI.setReceiver(receiver);
        gameControllerGUI.setRoot(loaderGame);
    }*/

    public Stage getStage(){
        return stage;
    }

    public void setResponse(ServerMessage response) {
        this.response = response;
    }

    public String getNickname(){
        return nickname;
    }
}
