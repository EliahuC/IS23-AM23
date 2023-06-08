package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.Messages.ServerToClient.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

public class GUIEvent implements PropertyChangeListener {
    private Boolean inStartGUI=false;
    private Boolean inLobbyWaiting =false;
    private Boolean inGameControllerGUI =false;

    private final MenuController menuController;
    private LobbyWaitingController lobbywaitingcontroller;
    private GameControllerGUI gamecontrollerGUI;

    public GUIEvent(MenuController menuController){
        this.menuController= menuController;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ServerMessage serverMessage=(ServerMessage) evt.getNewValue();
        forwardMessage(serverMessage);
        if(gamecontrollerGUI !=null){
            switch (serverMessage.getCategory()) {
                case STARTING_GAME_MESSAGE:
                    GameIsStartingMessage temp_startingGameMessage= (GameIsStartingMessage) serverMessage;
                    gamecontrollerGUI.setLivingRoom(temp_startingGameMessage.getLivingRoom());
                    gamecontrollerGUI.setPlayers(temp_startingGameMessage.getPlayers());
                    gamecontrollerGUI.setPlayer(temp_startingGameMessage.getPlayers().stream().filter(player -> Objects.equals(player.getNickName(), gamecontrollerGUI.getConnectionClient().getPlayerName())).findFirst().orElseThrow(() -> new IllegalArgumentException("Player not found")));
                    gamecontrollerGUI.setCurrentPlayer(temp_startingGameMessage.getCurrPlaying());
                    gamecontrollerGUI.setSeed(gamecontrollerGUI.getPlayer().getPersonalGoalCard().getNumeroCarta());
                    break;
                case UPDATE_STATE:
                    UpdateStateMessage temp_updateStateMessage=(UpdateStateMessage) serverMessage;
                    gamecontrollerGUI.setLivingRoom(temp_updateStateMessage.getGame().getLivingRoom());
                    gamecontrollerGUI.setPlayers(temp_updateStateMessage.getGame().getPlayers());
                    gamecontrollerGUI.setPlayer(temp_updateStateMessage.getGame().getPlayers().stream().filter(player -> Objects.equals(player.getNickName(), gamecontrollerGUI.getConnectionClient().getPlayerName())).findFirst().orElseThrow(() -> new IllegalArgumentException("Player not found")));
                    gamecontrollerGUI.setCurrPlaying(temp_updateStateMessage.getGame().getWhoIsPlaying());
                    break;
                case CURRPLAYING:
                    CurrPlayingMessage temp_currPlayingMessage=(CurrPlayingMessage) serverMessage;
                    gamecontrollerGUI.setCurrPlaying(temp_currPlayingMessage.getCurrPlaying());
                    break;
                case END_GAME_MESSAGE:
                    EndGameMessage temp_endGameMessage = (EndGameMessage) serverMessage;
                    gamecontrollerGUI.setWinner(temp_endGameMessage.getWinner().getNickName());
                    break;
            }
        }
    }

    private void forwardMessage(ServerMessage response){
        if(inStartGUI){
            menuController.setResponse(response);
            return;
        }
        if(inLobbyWaiting) {
            lobbywaitingcontroller.setResponse(response);
            return;
        }
        if (inGameControllerGUI) {
            gamecontrollerGUI.setResponse(response);
        }
    }
    public void setLobbyWaitingcontroller(LobbyWaitingController lobbywaitingcontroller){
        this.lobbywaitingcontroller=lobbywaitingcontroller;
    }
    public void setGamecontrollerGUI(GameControllerGUI gamecontrollerGUI){
        this.gamecontrollerGUI = gamecontrollerGUI;
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
}
