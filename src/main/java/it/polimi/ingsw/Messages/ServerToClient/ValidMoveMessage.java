package it.polimi.ingsw.Messages.ServerToClient;

import it.polimi.ingsw.GameSavings;

public class ValidMoveMessage extends ServerMessage{
    private GameSavings savings=null;
    public ValidMoveMessage() {
        super(MessageCategory.VALID_MESSAGE);
        setReturnMessage("The move that you made is valid");
    }

    public GameSavings getSavings() {
        return savings;
    }

    public void setSavings(GameSavings savings) {
        this.savings = savings;
    }
}
