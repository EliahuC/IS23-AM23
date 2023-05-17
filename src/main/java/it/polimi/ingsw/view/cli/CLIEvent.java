package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CLIEvent implements PropertyChangeListener {
    private LobbyHandler lobbyHandler;
    private GameHandler gameHandler;
    public CLIEvent(LobbyHandler lobbyHandler){
        this.lobbyHandler=lobbyHandler;
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
                System.out.println("I'M WORKING");
        }
    }

    private void forwardMessage(ServerMessage response){
        if(lobbyHandler!=null)
            lobbyHandler.setResponse(response);
    }
}
