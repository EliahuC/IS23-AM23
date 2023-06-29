package it.polimi.ingsw.Messages.ServerToClient;

import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;

/**
 * Message that returns the winner of the game
 * @author Eliahu Cohen
 *
 */
public class EndGameMessage extends ServerMessage {
    private final ArrayList<Player> players;
    public EndGameMessage(ArrayList<Player> players) {
        super(MessageCategory.END_GAME_MESSAGE);
        this.players=players;
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }
}
