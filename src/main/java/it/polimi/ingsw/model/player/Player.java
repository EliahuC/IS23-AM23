package it.polimi.ingsw.model.player;



import it.polimi.ingsw.model.board.ItemTile;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class Player {

    private final String NickName;
    private int score ;
    private boolean firstPlayerSeat;
    private final BookShelf playerBookshelf;
    private PersonalGoalCard personalGoalCard;
    private final boolean nowPlaying;
    private boolean lastRound;

    PropertyChangeListener listener;


    public Player(String nickName) {
        NickName = nickName;
        this.score = 0;
        this.playerBookshelf = new BookShelf();
        this.nowPlaying = false;
        this.lastRound = false;


    }
    public Player(String nickName,int x) {      //THIS METHOD IS CREATED DUE TO MAKE TESTS
        NickName = nickName;

        this.score = 0;
        this.playerBookshelf = new BookShelf();
        this.personalGoalCard = new PersonalGoalCard(x);
        this.nowPlaying = false;
        this.lastRound = false;

    }

    public void setFirstPlayerSeat(boolean firstPlayerSeat) {
        this.firstPlayerSeat = firstPlayerSeat;
    }

    public String getNickName() {
        return NickName;
    }



    public int getScore() {
        return score;
    }

    public BookShelf getPlayerBookshelf() {
        return playerBookshelf;
    }

    public boolean isFirstPlayerSeat() {
        return firstPlayerSeat;
    }

    public void insertToken(ArrayList<ItemTile> selectedTokens , int column){
        PropertyChangeEvent evt = new PropertyChangeEvent(
                this,
                "BOOKSHELF_CHANGED",
                this.playerBookshelf,
                playerBookshelf);
        for (ItemTile tile : selectedTokens) {
            playerBookshelf.setTile(column, tile);
        }
        this.listener.propertyChange(evt);

    }




    private void comparePersonalGoalCardwithBookshelf (){

        score+= personalGoalCard.CheckGoal(playerBookshelf);

    }

    public void setScore(int score) {
        this.score = score;
    }

    public void endGamePoints(){
        comparePersonalGoalCardwithBookshelf();
        playerBookshelf.AdjacentScore();
        score+= playerBookshelf.getPoints();
    }

    public boolean getNowPlaying(){
        return nowPlaying;
    }

    public boolean isLastRound() {
        return lastRound;
    }

    public void setLastRound(boolean b){
        this.lastRound=b;
    }

    public PropertyChangeListener getListener() {
        return listener;
    }

    public void setListener(PropertyChangeListener listener) {
        this.listener = listener;
    }

    public PersonalGoalCard getPersonalGoalCard() {
        return personalGoalCard;
    }

    public void setPersonalGoalCard(PersonalGoalCard personalGoalCard) {
        this.personalGoalCard = personalGoalCard;
    }


}


