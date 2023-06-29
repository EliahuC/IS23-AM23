package it.polimi.ingsw.Messages.ServerToClient;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.board.LivingRoom;
import it.polimi.ingsw.model.board.goalCards.CommonGoalCard;
import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;

/**
 * Message that returns that the game is starting
 * @author Eliahu Cohen
 *
 */
public class GameIsStartingMessage extends ServerMessage{
    private LivingRoom livingRoom;
    private  CommonGoalCard commonGoalCard1;
    private  CommonGoalCard commonGoalCard2;
    private  ArrayList<Player> players;
    private  String currPlaying;
    private Integer isPlaying;

    public GameIsStartingMessage(Game game) {
        super(MessageCategory.STARTING_GAME_MESSAGE);
        this.livingRoom=game.getLivingRoom();
        this.commonGoalCard1=livingRoom.getCommonGoalCard1();
        this.commonGoalCard2=livingRoom.getCommonGoalCard2();
        this.players=game.getPlayers();
        this.currPlaying=game.getCurrPlaying();
        this.isPlaying=game.getWhoIsPlaying();
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

    public void setLivingRoom(LivingRoom livingRoom) {
        this.livingRoom = livingRoom;
    }

    public void setCurrPlaying(String currPlaying) {
        this.currPlaying = currPlaying;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setCommonGoalCard2(CommonGoalCard commonGoalCard2) {
        this.commonGoalCard2 = commonGoalCard2;
    }

    public void setCommonGoalCard1(CommonGoalCard commonGoalCard1) {
        this.commonGoalCard1 = commonGoalCard1;
    }

    public Integer getIsPlaying() {
        return isPlaying;
    }
}
