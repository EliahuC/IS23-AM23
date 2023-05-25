package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.ServerToClient.*;
import it.polimi.ingsw.model.player.BookShelf;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

/**
 * @author Eliahu cohen and Simone Controguerra
 * Listener class that pass the received messages from ClientConnection to the CLI
 */
public class CLIEvent implements PropertyChangeListener {
    private Boolean inStartCLI=false;
    private Boolean inLobbyHandler=false;
    private Boolean inGameHandler=false;

    private StartCLI startCLI;
    private LobbyHandler lobbyHandler;
    private GameHandler gameHandler;

    public CLIEvent(StartCLI startCLI){
        this.startCLI=startCLI;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ServerMessage serverMessage=(ServerMessage) evt.getNewValue();
        if(gameHandler!=null){
            switch (serverMessage.getCategory()) {
                case STARTING_GAME_MESSAGE:
                    GameIsStartingMessage temp_startingGameMessage= (GameIsStartingMessage) serverMessage;
                    gameHandler.setPlayers(temp_startingGameMessage.getPlayers());
                    gameHandler.setLivingRoom(temp_startingGameMessage.getLivingRoom());
                    gameHandler.setPlayer(temp_startingGameMessage.getPlayers().stream().filter(player -> Objects.equals(player.getNickName(), gameHandler.getConnectionClient().getPlayerName())).findFirst().orElseThrow(() -> new IllegalArgumentException("Player not found")));
                    gameHandler.setCurrentPlayer(temp_startingGameMessage.getCurrPlaying());
                    break;
                case UPDATE_STATE:
                    UpdateStateMessage temp_updateStateMessage=(UpdateStateMessage) serverMessage;
                    gameHandler.setLivingRoom(temp_updateStateMessage.getGame().getLivingRoom());
                    gameHandler.setPlayers(temp_updateStateMessage.getGame().getPlayers());
                    gameHandler.setPlayer(temp_updateStateMessage.getGame().getPlayers().stream().filter(player -> Objects.equals(player.getNickName(), gameHandler.getConnectionClient().getPlayerName())).findFirst().orElseThrow(() -> new IllegalArgumentException("Player not found")));
                    gameHandler.setCurrPlaying(temp_updateStateMessage.getGame().getWhoIsPlaying());
                    break;
                case CURRPLAYING:
                    CurrPlayingMessage temp_currPlayingMessage=(CurrPlayingMessage) serverMessage;
                    gameHandler.setCurrPlaying(temp_currPlayingMessage.getCurrPlaying());
                    break;
                case END_GAME_MESSAGE:
                    EndGameMessage temp_endGameMessage = (EndGameMessage) serverMessage;
                    gameHandler.setWinner(temp_endGameMessage.getWinner().getNickName());
                    break;
            }
        }
        forwardMessage(serverMessage);
    }

    private void forwardMessage(ServerMessage response){
        if(inStartCLI){
            startCLI.setResponse(response);
            return;
        }
        if(inLobbyHandler) {
            lobbyHandler.setResponse(response);
            return;
        }
        if (inGameHandler) {
            gameHandler.setResponse(response);
        }
    }
    public void setLobbyHandler(LobbyHandler lobbyHandler){
        this.lobbyHandler=lobbyHandler;
    }
    public void setGameHandler(GameHandler gameHandler){
        this.gameHandler=gameHandler;
    }
    public void setInStartCLI(Boolean inStartCLI) {
        this.inStartCLI = inStartCLI;
    }
    public void setInLobbyHandler(Boolean inLobbyHandler) {
        this.inLobbyHandler = inLobbyHandler;
    }
    public void setInGameHandler(Boolean inGameHandler) {
        this.inGameHandler = inGameHandler;
    }
}
