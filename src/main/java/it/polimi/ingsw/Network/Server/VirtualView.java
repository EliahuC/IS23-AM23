package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Messages.ServerToClient.*;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.board.BoardToken;
import it.polimi.ingsw.model.player.BookShelf;
import it.polimi.ingsw.model.player.Player;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * class that will be notificated when an event to display to the client will happen and send the message to the client
 * @author Eliahu Cohen
 *
 */

public class VirtualView implements PropertyChangeListener {
    private final ServerConnection clientConnection;
private  String nickName;
    public VirtualView(ServerConnection connection,String name) {
        this.clientConnection = connection;
        this.nickName=name;
    }


    /**
     * @author Eliahu Cohen
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ServerMessage serverMessage=messageParser(evt);
        if(clientConnection!=null)
      clientConnection.sendMessage(serverMessage, nickName);
    }

    /**
     * @author Eliahu Cohen
     * @param evt received from the game
     * @return Server message that will be sent to client
     */
    private ServerMessage messageParser(PropertyChangeEvent evt) {

        switch (evt.getPropertyName()){
            case "LAST_TURN"-> {
                return new LastTurnMessage();
            }
            case "BOOKSHELF_CHANGED"-> {
                return new BookshelfMessage((BookShelf)evt.getNewValue());
            }
            case "BOARD_CHANGED"-> {
                return new LivingRoomMessage((BoardToken[][]) evt.getNewValue());
            }
            case "GAME_ENDED"->{
                return new EndGameMessage((ArrayList<Player>) evt.getNewValue());
            }
            case "GAME_STARTED"->{
                return new GameIsStartingMessage((Game) evt.getNewValue());
            }
            case "NEW_TURN"-> {
                return new CurrPlayingMessage((Integer) evt.getNewValue());
            }
            case "GAME_CRASHED"-> {
                return new CrashedLobbyMessage((String) evt.getNewValue());
            }
            case "COMMON COMPLETED"->{
                return new CommonCompletedMessage((String) evt.getNewValue());
            }
            case "UPDATE_STATE"->{
                return new UpdateStateMessage((Game) evt.getNewValue());
            }
            default ->{
                return new ErrorMessage();
            }
        }
    }
    public String getName(){
        return clientConnection.getNamePlayer();
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }
}
