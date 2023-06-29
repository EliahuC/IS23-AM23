package it.polimi.ingsw.Messages.ServerToClient;

/**
 * Message that returns an error in players move
 * @author Eliahu Cohen
 *
 */
public class ErrorMessage extends ServerMessage {
    public ErrorMessage() {
        super(MessageCategory.WARNING);
    }
}
