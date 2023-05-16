package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.Messages.ServerToClient.BookshelfMessage;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CLIEvent implements PropertyChangeListener {
    private final LobbyHandler lobbyHandler;
    public CLIEvent(LobbyHandler lobbyHandler){
        this.lobbyHandler=lobbyHandler;
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ServerMessage serverMessage=(ServerMessage) evt.getNewValue();
        switch(serverMessage.messageCategory ){
            case BOOKSHELF ->{

            }
            case LIVINGROOM -> {

            }
            case VALID_NICKNAME -> {

            }
        }
    }
}
