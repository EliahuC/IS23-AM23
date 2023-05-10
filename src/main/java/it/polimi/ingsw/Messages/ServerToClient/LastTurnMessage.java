package it.polimi.ingsw.Messages.ServerToClient;

import it.polimi.ingsw.Messages.Message;

public class LastTurnMessage extends ServerMessage{
    public LastTurnMessage() {
        super(Message.MessageCategory.RETURN_MESSAGE);
        setReturnMessage("A player completed his bookshelf, the game will end when player 4 will finish his turn");
    }
}
