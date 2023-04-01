package server.model.player;



import java.awt.*;

public class Player {

    private final String NickName;
    private final int IPaddres[];
    private final int score ;
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
        PersonalGoalCard = new PersonalGoalCard(1);
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

    public PersonalGoalCard getPersonalGoalCard() {
        return PersonalGoalCard;
    }

    public void insertToken(List selectedTokens, int numTokens, int column){}

    public boolean compareGoalCardwithBookshelf (){

        return false;
    }

    public boolean getNowPlaying(){
        return nowPlaying;
    }
}


