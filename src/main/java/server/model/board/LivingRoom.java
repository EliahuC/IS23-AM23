package server.model.board;
import server.Launcher;
import server.model.GameChecker;
import server.model.board.goalCards.*;
import server.model.player.BookShelf;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LivingRoom {
    private static final int MAX_Row=9;
    private static final int MAX_Column=9;

    private BoardToken[][] Board = new BoardToken[MAX_Row][MAX_Column];
    private final Launcher L;
    private final Bag bag;
    private GameChecker G;

    private final ArrayList<CommonGoalCard> CommonGoalCard = new ArrayList<>();
    private CommonGoalCard C1;
    private CommonGoalCard C2;

    public LivingRoom(Launcher L) {
        this.L = L;
        this.bag = new Bag();
        buildTiles();

        //SET UNAVAILABLE
        SetUnavailable();

        //SET THREE
        SetThree();

        //SET FOUR
        SetFour();

        SetCommonGoalCard();

        //Each board box gets row coordinate.
        SetRow();

        //Each board box gets column coordinate.
        SetCol();

        //Each board box gets board reference.
        SetBoard();
    }

    private void SetUnavailable() {
        //TOP LEFT
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                Board[i][j].setCategory(BoardToken.boardTokenCategory.UNAVAILABLE);
            }
        }
        //TOP RIGHT
        for (int i = 0; i < 2; i++) {
            for (int j = 6; j < MAX_Column; j++) {
                Board[i][j].setCategory(BoardToken.boardTokenCategory.UNAVAILABLE);
            }
        }
        //LOW LEFT
        for (int i = 7; i < MAX_Row; i++) {
            for (int j = 0; j < 3; j++) {
                Board[i][j].setCategory(BoardToken.boardTokenCategory.UNAVAILABLE);
            }
        }
        //LOW RIGHT
        for (int i = 7; i < MAX_Row; i++) {
            for (int j = 6; j < MAX_Column; j++) {
                Board[i][j].setCategory(BoardToken.boardTokenCategory.UNAVAILABLE);
            }
        }

        Board[2][0].setCategory(BoardToken.boardTokenCategory.UNAVAILABLE);
        Board[2][1].setCategory(BoardToken.boardTokenCategory.UNAVAILABLE);
        Board[2][7].setCategory(BoardToken.boardTokenCategory.UNAVAILABLE);
        Board[2][8].setCategory(BoardToken.boardTokenCategory.UNAVAILABLE);
        Board[6][0].setCategory(BoardToken.boardTokenCategory.UNAVAILABLE);
        Board[6][1].setCategory(BoardToken.boardTokenCategory.UNAVAILABLE);
        Board[6][7].setCategory(BoardToken.boardTokenCategory.UNAVAILABLE);
        Board[6][8].setCategory(BoardToken.boardTokenCategory.UNAVAILABLE);
        Board[3][0].setCategory(BoardToken.boardTokenCategory.UNAVAILABLE);
        Board[0][5].setCategory(BoardToken.boardTokenCategory.UNAVAILABLE);
        Board[5][8].setCategory(BoardToken.boardTokenCategory.UNAVAILABLE);
        Board[8][3].setCategory(BoardToken.boardTokenCategory.UNAVAILABLE);
    }

    private void SetThree() {
        Board[2][2].setCategory(BoardToken.boardTokenCategory.THREE);
        Board[2][6].setCategory(BoardToken.boardTokenCategory.THREE);
        Board[6][2].setCategory(BoardToken.boardTokenCategory.THREE);
        Board[6][6].setCategory(BoardToken.boardTokenCategory.THREE);
        Board[0][3].setCategory(BoardToken.boardTokenCategory.THREE);
        Board[3][8].setCategory(BoardToken.boardTokenCategory.THREE);
        Board[5][0].setCategory(BoardToken.boardTokenCategory.THREE);
        Board[8][5].setCategory(BoardToken.boardTokenCategory.THREE);
    }

    private void SetFour() {
        Board[0][4].setCategory(BoardToken.boardTokenCategory.FOUR);
        Board[1][5].setCategory(BoardToken.boardTokenCategory.FOUR);
        Board[3][1].setCategory(BoardToken.boardTokenCategory.FOUR);
        Board[4][0].setCategory(BoardToken.boardTokenCategory.FOUR);
        Board[4][8].setCategory(BoardToken.boardTokenCategory.FOUR);
        Board[5][7].setCategory(BoardToken.boardTokenCategory.FOUR);
        Board[7][3].setCategory(BoardToken.boardTokenCategory.FOUR);
        Board[8][4].setCategory(BoardToken.boardTokenCategory.FOUR);
    }

    private void SetCommonGoalCard(){
        CommonGoalCard.add(new CommonGoalCard1());
        CommonGoalCard.add(new CommonGoalCard2());
        CommonGoalCard.add(new CommonGoalCard3());
        CommonGoalCard.add(new CommonGoalCard4());
        CommonGoalCard.add(new CommonGoalCard5());
        CommonGoalCard.add(new CommonGoalCard6());
        CommonGoalCard.add(new CommonGoalCard7());
        CommonGoalCard.add(new CommonGoalCard8());
        CommonGoalCard.add(new CommonGoalCard9());
        CommonGoalCard.add(new CommonGoalCard10());
        CommonGoalCard.add(new CommonGoalCard11());
        CommonGoalCard.add(new CommonGoalCard12());

    }

    public void Start(int numPlayers) {
        for (int i = 0; i < MAX_Row; i++) {
            for (int j = 0; j < MAX_Column; j++) {
                putTile(i, j);
            }
        }
        int randIndex = new Random().nextInt(CommonGoalCard.size());
        C1 = CommonGoalCard.remove(randIndex);
        randIndex = new Random().nextInt(CommonGoalCard.size());
        C2 = CommonGoalCard.remove(randIndex);
    }

    public int checkCG(BookShelf bs){
        int score=0;
        C1.checkGoal(bs);
        C2.checkGoal(bs);
       score= C1.getPoints();
       score=C2.getPoints();
       return score;
    }


    public void restore() {
        if (G.isRestorable(Board)) {
            for (int i = 0; i < MAX_Row; i++) {
                for (int j = 0; j < MAX_Column; j++) {
                    if (Board[i][j].getTile() == null) {
                        Board=putTile(i, j);
                    }
                }
            }
        }
    }


    public BoardToken[][] putTile(int i, int j) {
        switch (L.getNumPlayers()) {
            case 2 -> {
                if (Board[i][j].getCategory() == BoardToken.boardTokenCategory.NORMAL)
                    Board[i][j].setTile(bag.extract());
            }
            case 3 -> {
                if (Board[i][j].getCategory() == (BoardToken.boardTokenCategory.NORMAL) || Board[i][j].getCategory() == (BoardToken.boardTokenCategory.THREE))
                    Board[i][j].setTile(bag.extract());
            }
            case 4 -> {
                if (Board[i][j].getCategory() != BoardToken.boardTokenCategory.UNAVAILABLE)
                    Board[i][j].setTile(bag.extract());
            }
        }
        return Board;
    }

    public Bag getBag() {
        return bag;
    }

    public ArrayList<ItemTile> getTiles(String x){
        return null;

    }
    public BoardToken[][] getBoard() {
        return Board;
    }

    private void SetRow(){
        for(int i = 0; i < MAX_Row; i++){
            for(int j = 0; j < MAX_Column; j++)
                this.Board[i][j].setRow(i);
        }
    }

    private void SetCol(){
        for(int i = 0; i < MAX_Row; i++){
            for(int j = 0; j < MAX_Column; j++)
                this.Board[i][j].setCol(j);
        }
    }

    private void SetBoard(){
        for(int i = 0; i < MAX_Row; i++){
            for(int j = 0; j < MAX_Column; j++)
                this.Board[i][j].setBoard(this.Board);
        }
    }

    public void buildTiles(){
        for(int i = 0; i < MAX_Row; i++){
            for(int j = 0; j < MAX_Column; j++)
                this.Board[i][j] = new BoardToken();
        }
    }
}
