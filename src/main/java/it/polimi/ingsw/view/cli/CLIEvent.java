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
                case STARTING_GAME_MESSAGE: {
                    GameIsStartingMessage temp_startingGameMessage = (GameIsStartingMessage) serverMessage;
                    gameHandler.setPlayers(temp_startingGameMessage.getPlayers());
                    gameHandler.setLivingRoom(temp_startingGameMessage.getLivingRoom());
                    gameHandler.setPlayer(temp_startingGameMessage.getPlayers().stream().filter(player -> Objects.equals(player.getNickName(), gameHandler.getConnectionClient().getPlayerName())).findFirst().orElseThrow(() -> new IllegalArgumentException("Player not found")));
                    gameHandler.setCurrentPlayer(temp_startingGameMessage.getCurrPlaying());
                    gameHandler.setSeed(gameHandler.getPlayer().getPersonalGoalCard().getNumeroCarta());
                    break;
                }
                case END_GAME_MESSAGE:{
                    EndGameMessage temp_endGameMessage = (EndGameMessage) serverMessage;
                    //gameHandler.setWinner(temp_endGameMessage.getWinner().getNickName());
                    gameHandler.setPlayers(temp_endGameMessage.getPlayers());
                    gameHandler.setEndgame(true);
                    break;
                }
                case UPDATE_STATE:{
                    UpdateStateMessage temp_updateStateMessage=(UpdateStateMessage) serverMessage;
                    gameHandler.setCurrPlaying(temp_updateStateMessage.getGame().getWhoIsPlaying());
                    gameHandler.setLivingRoom(temp_updateStateMessage.getGame().getLivingRoom());
                    gameHandler.setPlayers(temp_updateStateMessage.getGame().getPlayers());
                    gameHandler.setPlayer(temp_updateStateMessage.getGame().getPlayers().stream().filter(player -> Objects.equals(player.getNickName(), gameHandler.getConnectionClient().getPlayerName())).findFirst().orElseThrow(() -> new IllegalArgumentException("Player not found")));
                    gameHandler.getCommonGoalCard1().setPoints(temp_updateStateMessage.getGame().getLivingRoom().getPointsCGD1());
                    gameHandler.getCommonGoalCard2().setPoints(temp_updateStateMessage.getGame().getLivingRoom().getPointsCGD2());
                    break;
                }
                case CURRPLAYING:{
                    CurrPlayingMessage temp_currPlayingMessage=(CurrPlayingMessage) serverMessage;
                    gameHandler.setCurrPlaying(temp_currPlayingMessage.getCurrPlaying());
                    break;
                }
                case LIVINGROOM:{
                    LivingRoomMessage newServerMessage=(LivingRoomMessage) serverMessage;
                    gameHandler.setBoard(newServerMessage.getBoard());
                    break;
                }
                case CRASHED:{
                    startCLI=null;
                    lobbyHandler=null;
                    inGameHandler=null;
                }
            }
        }
        forwardMessage(serverMessage);
    }

    private void forwardMessage(ServerMessage response){
        try {
            if (inStartCLI) {
                startCLI.setResponse(response);
                return;
            }
            if (inLobbyHandler) {
                lobbyHandler.setResponse(response);
                return;
            }
            if (inGameHandler) {
                gameHandler.setResponse(response);
            }
        }catch (NullPointerException e){
            System.out.println("\n\t\t\t\t\t\t\t\t\tSomeone crashed. Please, restart the application!");
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
