package it.polimi.ingsw.Messages.ServerToClient;

import it.polimi.ingsw.model.Game;

/**
 * @author Simone Controguerra
 * Message that is sent from the server at the end of any turn
 */
public class UpdateStateMessage extends ServerMessage{
    private Game game;
    public UpdateStateMessage(Game game) {
        super(MessageCategory.UPDATE_STATE);
        this.game=game;
    }

    public Game getGame(){
        return game;
    }
}