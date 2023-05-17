package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CLIEvent implements PropertyChangeListener {

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
        if(lobbyHandler!=null) {
            lobbyHandler.setResponse(response);
            //return;
        }
        if(startCLI!=null){
            startCLI.setResponse(response);
            //return;
        }
        if (gameHandler != null) {
            //gameHandler.setResponse(response);
            //return;
        }
    }

    public void setLobbyHandler(LobbyHandler lobbyHandler){
        this.lobbyHandler=lobbyHandler;
    }
}
