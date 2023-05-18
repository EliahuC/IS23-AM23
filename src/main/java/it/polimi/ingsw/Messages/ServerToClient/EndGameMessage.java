package it.polimi.ingsw.Messages.ServerToClient;

import it.polimi.ingsw.model.player.Player;

/**
 * @author Eliahu Cohen
 * Message that returns the winner of the game
 */
public class EndGameMessage extends ServerMessage {
    private final Player winner;
    public EndGameMessage(Player player) {
        super(MessageCategory.END_GAME_MESSAGE);
        this.winner=player;
    }
}
