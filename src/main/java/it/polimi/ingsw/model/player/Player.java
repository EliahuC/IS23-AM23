package it.polimi.ingsw.model.player;



import it.polimi.ingsw.model.board.ItemTile;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Eliahu Cohen,Giovanni di Lorenzo and Simone Controguerra
 */

public class Player implements Serializable {

    private final String NickName;
    private int score ;
    private boolean firstPlayerSeat;
    private final BookShelf playerBookshelf;
    private PersonalGoalCard personalGoalCard;
    private final boolean nowPlaying;
    private boolean lastRound;


    transient PropertyChangeListener listener;

    /**
     * @author Eliahu Cohen
     * @param nickName of the player
     * Standard constructor used in the other classes
     */
    public Player(String nickName) {
        NickName = nickName;
        this.score = 0;
        this.playerBookshelf = new BookShelf();
        this.nowPlaying = false;
        this.lastRound = false;


    }



    /**
     * @author Eliahu Cohen
     * @param firstPlayerSeat boolean that indicates if the player is the first one
     */
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

    /**
     * @author Eliahu Cohen
     * @param selectedTiles array of the tiles extracted from the living room
     * @param column where I want to put the tiles
     * method that insert the tiles in the bookshelf of the player
     * This method uses an event to notify to the listener that the bookshelf is changed
     */
    public void insertToken(ArrayList<ItemTile> selectedTiles , int column){
        PropertyChangeEvent evt = new PropertyChangeEvent(
                this,
                "BOOKSHELF_CHANGED",
                this.playerBookshelf,
                playerBookshelf);
        for (ItemTile tile : selectedTiles) {
            playerBookshelf.setTile(column, tile);
        }
        this.listener.propertyChange(evt);

    }


    /**
     * @author Eliahu Cohen
     * Method to compare the playerBookshelf with the personal goal card that he have
     */
    private void comparePersonalGoalCardwithBookshelf (){

        score+= personalGoalCard.CheckGoal(playerBookshelf);

    }

    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @author Eliahu Cohen
     * Method to add the final points gained with the adjacens and the personal goal card
     * it used only when the game is finished
     */
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


