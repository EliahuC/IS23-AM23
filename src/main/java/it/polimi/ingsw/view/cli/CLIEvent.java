package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
        forwardMessage(serverMessage);
    }

    private void forwardMessage(ServerMessage response){
        if(inLobbyHandler) {
            lobbyHandler.setResponse(response);
            return;
        }
        if(inStartCLI){
            startCLI.setResponse(response);
            return;
        }
        if (inGameHandler) {
            //gameHandler.setResponse(response);
            return;
        }
    }

    public Boolean getInStartCLI() {
        return inStartCLI;
    }

    public void setInStartCLI(Boolean inStartCLI) {
        this.inStartCLI = inStartCLI;
    }

    public Boolean getInLobbyHandler() {
        return inLobbyHandler;
    }

    public void setInLobbyHandler(Boolean inLobbyHandler) {
        this.inLobbyHandler = inLobbyHandler;
    }

    public Boolean getInGameHandler() {
        return inGameHandler;
    }

    public void setInGameHandler(Boolean inGameHandler) {
        this.inGameHandler = inGameHandler;
    }

    public void setLobbyHandler(LobbyHandler lobbyHandler){
        this.lobbyHandler=lobbyHandler;
    }
}
