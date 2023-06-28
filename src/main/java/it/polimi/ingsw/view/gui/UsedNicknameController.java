package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Network.Client.ConnectionClient;
import it.polimi.ingsw.Network.Client.RMI.ClientConnectionRMI;
import it.polimi.ingsw.Network.Client.TCP.ClientConnectionTCP;
import it.polimi.ingsw.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * @author Andrea Bricchi and Giovanni di Lorenzo
 * Same class as MenuController accessed when a nickname is already used
 */
public class UsedNicknameController {

    @FXML
    TextField name;
    private Stage stage;
    private Scene scene;
    private ServerMessage response;
    private String serverAddr;
    private int portNum;
    private Socket socket;
    private ConnectionClient connectionClient;
    private GUIEvent receiver;
    private String nickname;

    public void returnToMenu(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/is23am23/menu.fxml"));
        Parent root = loader.load();
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setResponse(ServerMessage response){
        this.response=response;
    }

    public void goTCP(ActionEvent event)throws IOException {
        nickname = name.getText();
        if (!nickname.isEmpty()) {
            if (TCPon(event)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/is23am23/lobbyChoice.fxml"));
                Parent root = loader.load();
                LobbyChoiceController lobbyController = loader.getController();
                receiver.setLobbyChoiceController(lobbyController);
                lobbyController.displayNickname(nickname);
                lobbyController.setConnection(connectionClient);
                lobbyController.setReceiver(receiver);
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
    }

    public boolean TCPon(ActionEvent event) {
        try {
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
            } else
                return false;
             }catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void goRMI(ActionEvent event) throws IOException {
        nickname = name.getText();
        if (!nickname.isEmpty()) {
            if (RMIon(event)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/is23am23/lobbyChoice.fxml"));
                Parent root = loader.load();

                LobbyChoiceController lobbyController = loader.getController();
                receiver.setLobbyChoiceController(lobbyController);
                lobbyController.displayNickname(nickname);
                lobbyController.setConnection(connectionClient);
                lobbyController.setReceiver(receiver);

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }
        }
    }

    public boolean RMIon(ActionEvent event) {
        try {
            connectionClient = new ClientConnectionRMI(nickname, receiver, serverAddr);
            new Thread(connectionClient).start();
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException iE) {
                iE.printStackTrace();
            }
            if(response.getCategory()==Message.MessageCategory.VALID_NICKNAME){
                return true;
            } else
                return false;
            }catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setReceiver(GUIEvent receiver) {
        this.receiver = receiver;
    }

}
