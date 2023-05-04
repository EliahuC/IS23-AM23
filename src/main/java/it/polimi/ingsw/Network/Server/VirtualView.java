package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Messages.ServerToClient.BookshelfMessage;
import it.polimi.ingsw.Network.Messages.ServerToClient.ErrorMessage;
import it.polimi.ingsw.Network.Messages.ServerToClient.LastTurnMessage;
import it.polimi.ingsw.Network.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.model.player.BookShelf;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class VirtualView implements PropertyChangeListener {
    private ServerConnectionToClient clientConnection;

    public VirtualView(ServerConnectionToClient connection) {
        this.clientConnection = connection;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ServerMessage serverMessage=messageParser(evt);
      clientConnection.sendMessage(serverMessage);
    }

    private ServerMessage messageParser(PropertyChangeEvent evt) {
       ServerMessage serverMessage;
        switch (evt.getPropertyName()){
            case "LAST_TURN"-> serverMessage=new LastTurnMessage();
            case "BOOKSHELF_CHANGED"-> serverMessage=new BookshelfMessage((BookShelf)evt.getNewValue());
            default -> serverMessage= new ErrorMessage();
        }
        return serverMessage;
    }

    //DA IMPLEMENTARE TUTTE LE REAZIONI VIEW AI MESSAGGI

}
