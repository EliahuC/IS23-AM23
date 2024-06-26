package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.Messages.ServerToClient.*;
import it.polimi.ingsw.Network.Client.ConnectionClient;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

/**
 * Listener class that pass the received messages from ClientConnection to the GUI
 * @author Andrea Bricchi and Giovanni di Lorenzo
 *
 */
public class GUIEvent implements PropertyChangeListener {
    private Boolean inStartGUI = false;
    private Boolean inGameControllerGUI = false;
    private Boolean inLobbyChoice = false;
    private Boolean inUsedNickname=false;
    private final MenuController menuController;
    private GameControllerGUI gamecontrollerGUI;
    private LobbyChoiceController lobbyChoiceController;
    private UsedNicknameController usedNicknameController;
    private URL url;
    private ServerMessage serverMessage;
    private Stage stage;
    private ConnectionClient connectionClient;


    public GUIEvent(MenuController menuController) {
        this.menuController = menuController;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        serverMessage = (ServerMessage) evt.getNewValue();
        forwardMessage(serverMessage);
        if (gamecontrollerGUI != null) {
            Platform.runLater(()-> {
                switch (serverMessage.getCategory()) {
                    case STARTING_GAME_MESSAGE:
                        GameIsStartingMessage temp_startingGameMessage = (GameIsStartingMessage) serverMessage;
                        gamecontrollerGUI.setPlayers(temp_startingGameMessage.getPlayers());
                        gamecontrollerGUI.setLivingRoom(temp_startingGameMessage.getLivingRoom());
                        gamecontrollerGUI.setCurrPlaying( temp_startingGameMessage.getIsPlaying());
                        gamecontrollerGUI.setPlayer(temp_startingGameMessage.getPlayers().stream().filter(player -> Objects.equals(player.getNickName(), gamecontrollerGUI.getConnectionClient().getPlayerName())).findFirst().orElseThrow(() -> new IllegalArgumentException("Player not found")));
                        gamecontrollerGUI.setCurrentPlayer(temp_startingGameMessage.getCurrPlaying());
                        gamecontrollerGUI.setSeed(gamecontrollerGUI.getPlayer().getPersonalGoalCard().getNumeroCarta());
                        gamecontrollerGUI.setNickname(menuController.getNickname());
                        gamecontrollerGUI.launchBookshelf();
                        stage = menuController.getStage();
                        gamecontrollerGUI.setStage(stage);
                        gamecontrollerGUI.setReceiver(this);
                        try {
                            gamecontrollerGUI.displayScene();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case UPDATE_STATE:
                        UpdateStateMessage temp_updateStateMessage = (UpdateStateMessage) serverMessage;
                        gamecontrollerGUI.setLivingRoom(temp_updateStateMessage.getGame().getLivingRoom());
                        gamecontrollerGUI.setFlag(true);
                        gamecontrollerGUI.setShowContainer(true);
                        //gamecontrollerGUI.setFirstTime(true);
                        gamecontrollerGUI.setPlayers(temp_updateStateMessage.getGame().getPlayers());
                        gamecontrollerGUI.setPlayer(temp_updateStateMessage.getGame().getPlayers().stream().filter(player -> Objects.equals(player.getNickName(), gamecontrollerGUI.getConnectionClient().getPlayerName())).findFirst().orElseThrow(() -> new IllegalArgumentException("Player not found")));
                        gamecontrollerGUI.setCurrPlaying(temp_updateStateMessage.getGame().getWhoIsPlaying());
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("TURN NOTIFY");
                        alert.setHeaderText("It's your turn!");
                        if (alert.showAndWait().get() == ButtonType.OK) {

                        }
                        try {
                            gamecontrollerGUI.displayScene();
                        } catch (IOException e) {
                            throw new RuntimeException();
                        }
                        break;
                    case CURRPLAYING:
                        CurrPlayingMessage temp_currPlayingMessage = (CurrPlayingMessage) serverMessage;
                        gamecontrollerGUI.setFlag(true);
                        gamecontrollerGUI.setCurrPlaying(temp_currPlayingMessage.getCurrPlaying());
                        try {
                            gamecontrollerGUI.displayScene();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case END_GAME_MESSAGE:
                        EndGameMessage temp_endGameMessage = (EndGameMessage) serverMessage;
                        gamecontrollerGUI.setPlayers(temp_endGameMessage.getPlayers());
                        //gamecontrollerGUI.setEndgame(true);
                        //gamecontrollerGUI.setWinner(temp_endGameMessage.getWinner().getNickName()); MANCA METODO GETWINNER()
                        try {
                            gamecontrollerGUI.goToResults();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case CRASHED:
                        CrashedLobbyMessage temp_crashedLobbyMessage = (CrashedLobbyMessage) serverMessage;
                        Alert alertCrashed = new Alert(Alert.AlertType.WARNING);
                        alertCrashed.setTitle("CONNECTION ERROR");
                        alertCrashed.setHeaderText("Server has crashed :(");
                        if (alertCrashed.showAndWait().get() == ButtonType.OK) {
                        }
                        break;

                }
            });
        }
    }

    /**
     * method to load the controller and the scene of game.fxml
     * @author Eliahu Cohen
     *
     */
    public void loadScene() {

        AnchorPane root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/is23am23/game.fxml"));
        try {
             root=loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        this.gamecontrollerGUI=loader.getController();
        gamecontrollerGUI.setRoot(root);
        }


    private void forwardMessage(ServerMessage response) {
        if (inStartGUI) {
            menuController.setResponse(response);
            setInStartGUI(false);
            return;
        } else if (inLobbyChoice) {
            lobbyChoiceController.setResponse(response);
            setInGameControllerGUI(true);
            setInLobbyChoice(false);
            return;
        } else if (inGameControllerGUI) {
            gamecontrollerGUI.setResponse(response);
        }else if(inUsedNickname) {
            usedNicknameController.setResponse(response);
            setInUsedNickname(false);
        }
    }

    public void setGamecontrollerGUI(GameControllerGUI gamecontrollerGUI) {
        this.gamecontrollerGUI = gamecontrollerGUI;
    }

    public void setLobbyChoiceController(LobbyChoiceController lobbyChoiceController) {
        this.lobbyChoiceController = lobbyChoiceController;
    }

    public void setInStartGUI(Boolean inStartGUI) {
        this.inStartGUI = inStartGUI;
    }

    public void setInGameControllerGUI(Boolean inGameControllerGUI) {
        this.inGameControllerGUI = inGameControllerGUI;
    }

    public void setInLobbyChoice(Boolean inLobbyChoice) {
        this.inLobbyChoice = inLobbyChoice;
    }

    public void setInUsedNickname(Boolean inUsedNickname) {
        this.inUsedNickname = inUsedNickname;
    }

    public void setUsedNicknameController(UsedNicknameController usedNicknameController) {
        this.usedNicknameController = usedNicknameController;
    }

    public void setConnectionClient(ConnectionClient connectionClient) {
        this.connectionClient = connectionClient;
    }

    public GameControllerGUI getGamecontrollerGUI() {
        return gamecontrollerGUI;
    }
}
