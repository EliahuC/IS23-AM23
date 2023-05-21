package it.polimi.ingsw.Messages.ServerToClient;

public class CommonCompletedMessage extends ServerMessage{
    public CommonCompletedMessage(String s) {
        super(MessageCategory.COMMONGOAL);
    }
}
