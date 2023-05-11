package it.polimi.ingsw;


import it.polimi.ingsw.model.board.LivingRoom;
import it.polimi.ingsw.model.board.goalCards.CommonGoalCard;
import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;

public class Savings {
    private Integer numPlayers;
    private final ArrayList<Player> players;
    private LivingRoom livingRoom=null;
    private CommonGoalCard commonGoalCard1=null;
    private CommonGoalCard commonGoalCard2=null;
    private Integer currPlaying=null;

    private boolean startedGame=false;

    private boolean finishedGame=false;


    public Savings(int numPlayers){
        this.numPlayers=numPlayers;
        players=new ArrayList<>();
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

    public CommonGoalCard getCommonGoalCard1() {
        return commonGoalCard1;
    }

    public void setCommonGoalCard1(CommonGoalCard commonGoalCard1) {
        this.commonGoalCard1 = commonGoalCard1;
    }

    public CommonGoalCard getCommonGoalCard2() {
        return commonGoalCard2;
    }

    public void setCommonGoalCard2(CommonGoalCard commonGoalCard2) {
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
