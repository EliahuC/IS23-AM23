package it.polimi.ingsw.Messages.ServerToClient;

/**
 * @author Eliahu Cohen
 * Message that returns an error in players move
 */
public class ErrorMessage extends ServerMessage {
    public ErrorMessage() {
        super(MessageCategory.WARNING);
    }
}
