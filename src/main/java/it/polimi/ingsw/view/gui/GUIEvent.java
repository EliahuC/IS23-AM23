package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.Messages.ServerToClient.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

import static java.awt.SystemColor.window;

public class GUIEvent implements PropertyChangeListener {
    private Boolean inStartGUI = false;
    private Boolean inLobbyWaiting = false;
    private Boolean inGameControllerGUI = false;
    private Boolean inLobbyChoice = false;

    private final MenuController menuController;
    private LobbyWaitingController lobbywaitingcontroller;
    private GameControllerGUI gamecontrollerGUI;
    private LobbyChoiceController lobbyChoiceController;
    private URL url;
    private ServerMessage serverMessage;
    private Stage stage;


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
                        File file = new File("src/main/resources/com/example/is23am23/game.fxml");
                        try {
                            url = file.toURI().toURL();
                        } catch (MalformedURLException e) {
                            throw new RuntimeException(e);
                        }
                        FXMLLoader loader = new FXMLLoader(url);
                        try {
                            Parent root = loader.load();
                            Scene scene = new Scene(root);
                            if (stage.isShowing()) {
                                stage.hide();
                                stage.setScene(scene);
                                stage.show();
                            } else {
                                stage.setScene(scene);
                                stage.show();
                            }

                            GameIsStartingMessage temp_startingGameMessage = (GameIsStartingMessage) serverMessage;
                            gamecontrollerGUI.setLivingRoom(temp_startingGameMessage.getLivingRoom());
                            gamecontrollerGUI.setPlayers(temp_startingGameMessage.getPlayers());
                            gamecontrollerGUI.setPlayer(temp_startingGameMessage.getPlayers().stream().filter(player -> Objects.equals(player.getNickName(), gamecontrollerGUI.getConnectionClient().getPlayerName())).findFirst().orElseThrow(() -> new IllegalArgumentException("Player not found")));
                            gamecontrollerGUI.setCurrentPlayer(temp_startingGameMessage.getCurrPlaying());
                            gamecontrollerGUI.setSeed(gamecontrollerGUI.getPlayer().getPersonalGoalCard().getNumeroCarta());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case UPDATE_STATE:
                        UpdateStateMessage temp_updateStateMessage = (UpdateStateMessage) serverMessage;
                        gamecontrollerGUI.setLivingRoom(temp_updateStateMessage.getGame().getLivingRoom());
                        gamecontrollerGUI.setPlayers(temp_updateStateMessage.getGame().getPlayers());
                        gamecontrollerGUI.setPlayer(temp_updateStateMessage.getGame().getPlayers().stream().filter(player -> Objects.equals(player.getNickName(), gamecontrollerGUI.getConnectionClient().getPlayerName())).findFirst().orElseThrow(() -> new IllegalArgumentException("Player not found")));
                        gamecontrollerGUI.setCurrPlaying(temp_updateStateMessage.getGame().getWhoIsPlaying());
                        break;
                    case CURRPLAYING:
                        CurrPlayingMessage temp_currPlayingMessage = (CurrPlayingMessage) serverMessage;
                        gamecontrollerGUI.setCurrPlaying(temp_currPlayingMessage.getCurrPlaying());
                        break;
                    case END_GAME_MESSAGE:
                        EndGameMessage temp_endGameMessage = (EndGameMessage) serverMessage;
                        //gamecontrollerGUI.setWinner(temp_endGameMessage.getWinner().getNickName());
                        break;
                }
            });
        }
    }

    private void forwardMessage(ServerMessage response) {
        if (inStartGUI) {
            menuController.setResponse(response);
            return;
        } else if (inLobbyChoice) {
            lobbyChoiceController.setResponse(response);
            setInLobbyWaiting(true);
            setInLobbyChoice(false);
            return;
        } else if (inLobbyWaiting) {
            lobbywaitingcontroller.setResponse(response);
            setInLobbyWaiting(false);
            setInGameControllerGUI(true);
            return;
        } else if (inGameControllerGUI) {
            gamecontrollerGUI.setResponse(response);
        }
    }

    public void setLobbyWaitingcontroller(LobbyWaitingController lobbywaitingcontroller) {
        this.lobbywaitingcontroller = lobbywaitingcontroller;
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

    public void setInLobbyWaiting(Boolean inLobbyWaiting) {
        this.inLobbyWaiting = inLobbyWaiting;
    }

    public void setInGameControllerGUI(Boolean inGameControllerGUI) {
        this.inGameControllerGUI = inGameControllerGUI;
    }

    public void setInLobbyChoice(Boolean inLobbyChoice) {
        this.inLobbyChoice = inLobbyChoice;
    }
    public void setStage(Stage stage){
        this.stage=stage;
    }
}
