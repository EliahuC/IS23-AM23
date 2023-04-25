package it.polimi.ingsw.Network.Messages.ServerToClient;

public class LastTurnMessage extends ServerMessage{
    public LastTurnMessage() {
        super(MessageCategory.RETURN_MESSAGE);
        addReturnMessage("A player completed his bookshelf, the game will end when player 4 will finish his turn");
    }
}
