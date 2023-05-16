package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Messages.ServerToClient.*;
import it.polimi.ingsw.Network.Server.ServerConnection;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.board.BoardToken;
import it.polimi.ingsw.model.player.BookShelf;
import it.polimi.ingsw.model.player.Player;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * @author Eliahu Cohen
 * class that will be notificated when an event to display to the client will happen and send the message to the client
 */

public class VirtualView implements PropertyChangeListener {
    private final ServerConnection clientConnection;

    public VirtualView(ServerConnection connection) {
        this.clientConnection = connection;
    }


    /**
     * @author Eliahu Cohen
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ServerMessage serverMessage=messageParser(evt);
      clientConnection.sendMessage(serverMessage, clientConnection.getNamePlayer());
    }

    /**
     * @author Eliahu Cohen
     * @param evt received from the game
     * @return Server message that will be sent to client
     */
    private ServerMessage messageParser(PropertyChangeEvent evt) {
       ServerMessage serverMessage;
        switch (evt.getPropertyName()){
            case "LAST_TURN"-> serverMessage=new LastTurnMessage();
            case "BOOKSHELF_CHANGED"-> serverMessage=new BookshelfMessage((BookShelf)evt.getNewValue());
            case "BOARD_CHANGED"-> serverMessage=new LivingRoomMessage((BoardToken[][]) evt.getNewValue());
            case "GAME_ENDED"->serverMessage =new EndGameMessage((Player) evt.getNewValue());
            case "GAME_STARTED"->serverMessage=new GameIsStartingMessage((Game) evt.getNewValue());
            case "NEW_TURN"-> serverMessage=new CurrPlayingMessage((Integer) evt.getNewValue());
            default -> serverMessage= new ErrorMessage();
        }
        return serverMessage;
    }

    //TODO Reazione viwe messaggi

}
