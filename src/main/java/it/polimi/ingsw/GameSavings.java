package it.polimi.ingsw;


import it.polimi.ingsw.model.board.LivingRoom;
import it.polimi.ingsw.model.board.goalCards.CommonGoalCard;
import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;

/**
 * Class that rapresent the game savings
 * @author Eliahu Cohen
 *
 */
public class GameSavings {
    private Integer numPlayers;
    private final ArrayList<Player> players;
    private LivingRoom livingRoom=null;
    private Integer commonGoalCard1;
    private Integer commonGoalCard2=null;
    private Integer currPlaying=null;

    private boolean startedGame=false;

    private boolean finishedGame=false;


    public GameSavings(ArrayList<Player> players){
        this.players=players;
    }

    public Integer getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(Integer numPlayers) {
        this.numPlayers = numPlayers;
    }

    public LivingRoom getLivingRoom() {
        return livingRoom;
    }

    public void setLivingRoom(LivingRoom livingRoom) {
        this.livingRoom = livingRoom;
    }

    public Integer getCommonGoalCard1() {
        return commonGoalCard1;
    }

    public void setCommonGoalCard1(Integer commonGoalCard1) {
        this.commonGoalCard1 = commonGoalCard1;
    }

    public Integer getCommonGoalCard2() {
        return commonGoalCard2;
    }

    public void setCommonGoalCard2(Integer commonGoalCard2) {
        this.commonGoalCard2 = commonGoalCard2;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getCurrPlaying() {
        return currPlaying;
    }

    public void setCurrPlaying(int currPlaying) {
        this.currPlaying = currPlaying;
    }

    public boolean isStartedGame() {
        return startedGame;
    }

    public void setStartedGame(boolean startedGame) {
        this.startedGame = startedGame;
    }

    public boolean isFinishedGame() {
        return finishedGame;
    }

    public void setFinishedGame(boolean finishedGame) {
        this.finishedGame = finishedGame;
    }
}
