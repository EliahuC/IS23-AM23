package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.MoveSerializer;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Network.Client.ConnectionClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

/**
 * @author Andrea Bricchi and Giovanni di Lorenzo
 * Controller that handle the player joining to a lobby
 */
public class LobbyChoiceController implements Initializable {

    @FXML
    private Label nameDisplay;
    @FXML
    private ChoiceBox<String> playerNumber;
    private final String[] number = {"2", "3", "4"};
    private Stage stage;
    private Scene scene;
    private ServerMessage response;
    private Parent root;
    private Integer PlayersCounter = null;
    private String nickname;
    String command = null;
    String lobby_size=null;
    private ConnectionClient connectionClient;
    private GUIEvent receiver;
    private GameControllerGUI gameControllerGUI;
    private MenuController menuController = new MenuController();

    /**
     * @author Andrea Bricchi and Giovanni di Lorenzo
     * @param event click
     * @throws IOException exception
     * Method to enter a lobby
     */
    public void joinLobby(ActionEvent event) throws IOException {
        receiver.setInStartGUI(false);
        receiver.setInLobbyChoice(true);
        gameControllerGUI = new GameControllerGUI();
        gameControllerGUI.setConnectionClient(connectionClient);
        receiver.setGamecontrollerGUI(gameControllerGUI);
        receiver.setConnectionClient(connectionClient);
        gameControllerGUI.setReceiver(receiver);
        command = "/ENTER";
        Message message = MoveSerializer.serializeInput(command);
        connectionClient.sendMessage((ClientMessage) message);
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException iE) {
            iE.printStackTrace();
        }
        if (checklobbies()) {
            if (response.getCategory() == Message.MessageCategory.RETURN_MESSAGE) {
                File file = new File("src/main/resources/com/example/is23am23/lobbyWaiting.fxml");
                URL url = file.toURI().toURL();
                FXMLLoader loader = new FXMLLoader(url);
                root = loader.load();
                LobbyWaitingController lobbyWaitingController = loader.getController();
                lobbyWaitingController.displayNickname(nickname);
                lobbyWaitingController.setConnectionClient(connectionClient);
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("LOBBY ERROR");
            alert.setHeaderText("No lobby available, create one!");
            if (alert.showAndWait().get() == ButtonType.OK) {

            }
        }
    }

    /**
     * @author Andrea Bricchi and Giovanni di Lorenzo
     * @param event click
     * @throws IOException exception
     * Method to create a game
     */
    public void createLobby(ActionEvent event) throws IOException {
        receiver.setInStartGUI(false);
        receiver.setInLobbyChoice(true);
        receiver.setConnectionClient(connectionClient);
        lobby_size = playerNumber.getValue();
        if (lobby_size != null) {
            switch (lobby_size) {
                case "2" -> setPlayersCounter(2);
                case "3" -> setPlayersCounter(3);
                case "4" -> setPlayersCounter(4);
            }
            command = "/CREATE " + lobby_size;
            Message message = MoveSerializer.serializeInput(command);
            connectionClient.sendMessage((ClientMessage) message);
            File file = new File("src/main/resources/com/example/is23am23/lobbyWaiting.fxml");
            URL url = file.toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            LobbyWaitingController lobbyWaitingController = loader.getController();
            lobbyWaitingController.displayNickname(nickname);
            lobbyWaitingController.setConnectionClient(connectionClient);
            gameControllerGUI = new GameControllerGUI();
            gameControllerGUI.setConnectionClient(connectionClient);
            receiver.setGamecontrollerGUI(gameControllerGUI);
            gameControllerGUI.setReceiver(receiver);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * @author Andrea Bricchi
     * @param event click
     * @throws IOException exception
     * Method to return to the Menu
     */
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

    public void setPlayersCounter(int playersCounter) {
        PlayersCounter = playersCounter;
    }

    public void displayNickname(String nickname) {
        this.nickname=nickname;
        nameDisplay.setText("Welcome " + nickname + "!");
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        playerNumber.getItems().addAll(number);
    }

    public Integer getPlayersCounter() {
        return PlayersCounter;
    }

    private boolean checklobbies() {
        if (response.getCategory() == Message.MessageCategory.WARNING){
            return false;
        } else return true;
    }

    public void setResponse(ServerMessage response) {
        this.response = response;
    }

    public void setConnection(ConnectionClient connectionClient){
        this.connectionClient=connectionClient;
    }

    public void setReceiver(GUIEvent receiver){this.receiver = receiver;}
}
