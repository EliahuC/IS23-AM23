package server.model.player;



import server.model.board.ItemTile;
import server.model.board.ItemTileCategory;

import java.awt.*;
import java.util.ArrayList;

public class Player {

    private final String NickName;
    private final int IPaddres[];
    private int score ;
    private final boolean firstPlayerSeat;
    private final BookShelf playerBookshelf;
    private final PersonalGoalCard PersonalGoalCard;
    private final boolean nowPlaying;
    private final boolean lastRound;


    public Player(String nickName) {
        NickName = nickName;
        this.IPaddres = null;
        this.score = 0;
        this.firstPlayerSeat = false;
        this.playerBookshelf = new BookShelf();
        this.PersonalGoalCard = new PersonalGoalCard();
        this.nowPlaying = false;
        this.lastRound = false;
    }

    public String getNickName() {
        return NickName;
    }

    public int[] getIPaddres() {
        return IPaddres;
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

    public void insertToken(ArrayList<ItemTile> selectedTokens, ArrayList<ItemTileCategory> order , int column){
      for(int i=0;i<order.size();i++){
          ItemTile tile=searchTile(selectedTokens,order.get(i));
          playerBookshelf.setTile(column,tile);
      }
    }

    private ItemTile searchTile(ArrayList<ItemTile> selectedTokens, ItemTileCategory category) {
        ItemTile tile;
        if(selectedTokens.get(0).getCategory()==category) tile=selectedTokens.remove(0);
        else if (selectedTokens.get(1).getCategory()==category) tile=selectedTokens.remove(1);
        else tile=selectedTokens.remove(3);
        return tile;
    }


    private void comparePersonalGoalCardwithBookshelf (){

        score+=PersonalGoalCard.CheckGoal(playerBookshelf);

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
}


