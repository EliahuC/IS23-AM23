package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CLIEvent implements PropertyChangeListener {

    private StartCLI startCLI;
    private LobbyHandler lobbyHandler;
    private GameHandler gameHandler;
    public CLIEvent(LobbyHandler lobbyHandler){
        this.lobbyHandler=lobbyHandler;
    }

    public CLIEvent(StartCLI startCLI){
        this.startCLI=startCLI;
    }

    public CLIEvent(LobbyHandler lobbyHandler, GameHandler gameHandler){
        this.gameHandler=gameHandler;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ServerMessage serverMessage=(ServerMessage) evt.getNewValue();
        switch(serverMessage.messageCategory){
            case WARNING:
                forwardMessage(serverMessage);
        }
    }

    private void forwardMessage(ServerMessage response){
        if(lobbyHandler!=null) {
            lobbyHandler.setResponse(response);
            return;
        }
        if(startCLI!=null){
            startCLI.setResponse(response);
            return;
        }
        if (gameHandler != null) {
            //gameHandler.setResponse(response);
            return;
        }
        System.out.println("no handler available");

    }
}
