package it.polimi.ingsw.Network.Server.TCP;

import it.polimi.ingsw.Network.Messages.ServerToClient.*;
import it.polimi.ingsw.model.board.BoardToken;
import it.polimi.ingsw.model.player.BookShelf;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class VirtualView implements PropertyChangeListener {
    private final ServerConnectionToClient clientConnection;

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
            case "BOARD_CHANGED"-> serverMessage=new LivingRoomMessage((BoardToken[][]) evt.getNewValue());
            default -> serverMessage= new ErrorMessage();
        }
        return serverMessage;
    }

    //DA IMPLEMENTARE TUTTE LE REAZIONI VIEW AI MESSAGGI

}
