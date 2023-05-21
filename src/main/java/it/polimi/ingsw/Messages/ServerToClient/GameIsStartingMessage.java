package it.polimi.ingsw.Messages.ServerToClient;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.board.LivingRoom;
import it.polimi.ingsw.model.board.goalCards.CommonGoalCard;
import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;

/**
 * @author Eliahu Cohen
 * Message that returns that the game is starting
 */
public class GameIsStartingMessage extends ServerMessage{
    private final LivingRoom livingRoom;
    private final CommonGoalCard commonGoalCard1;
    private final CommonGoalCard commonGoalCard2;
    private final ArrayList<Player> players;
    private final String currPlaying;

    public GameIsStartingMessage(Game game) {
        super(MessageCategory.STARTING_GAME_MESSAGE);
        this.livingRoom=game.getLivingRoom();
        this.commonGoalCard1=livingRoom.getCommonGoalCard1();
        this.commonGoalCard2=livingRoom.getCommonGoalCard2();
        this.players=game.getPlayers();
        this.currPlaying=game.getCurrPlaying();
        setReturnMessage("The game is starting...");
    }

    public LivingRoom getLivingRoom() {
        return livingRoom;
    }

    public CommonGoalCard getCommonGoalCard1() {
        return commonGoalCard1;
    }

    public CommonGoalCard getCommonGoalCard2() {
        return commonGoalCard2;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public String getCurrPlaying() {
        return currPlaying;
    }
}
