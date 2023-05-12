package it.polimi.ingsw.model.board;
import it.polimi.ingsw.Network.Server.TCP.VirtualView;
import it.polimi.ingsw.model.board.goalCards.*;
import it.polimi.ingsw.model.player.BookShelf;
import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.model.GameChecker;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Random;

public class LivingRoom {
    public static final int MAX_Row=9;
    public static final int MAX_Column=9;
    ArrayList<PropertyChangeListener> listeners=new ArrayList<>();

    private BoardToken[][] Board = new BoardToken[MAX_Row][MAX_Column];
    private final Launcher L;
    private final Bag bag;
    private final GameChecker gameChecker;

    private final ArrayList<CommonGoalCard> CommonGoalCard = new ArrayList<>();
    private CommonGoalCard commonGoalCard1;
    private CommonGoalCard commonGoalCard2;

    public LivingRoom(Launcher L) {
        this.L = L;
        this.bag = new Bag();
        this.gameChecker =new GameChecker(L);
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

        //Start(L.getNumPlayers());
    }

    private void SetUnavailable() {
        //TOP LEFT
       setUTopLeft();
        //TOP RIGHT
        setUTopRight();
        //DOWN LEFT
        setUDownLeft();
        //DOWN RIGHT
        setUDownRight();

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
    private void setUTopLeft(){
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                Board[i][j].setCategory(BoardToken.boardTokenCategory.UNAVAILABLE);
            }
        }
    }

    private void setUDownLeft(){
        for (int i = 7; i < MAX_Row; i++) {
            for (int j = 0; j < 3; j++) {
                Board[i][j].setCategory(BoardToken.boardTokenCategory.UNAVAILABLE);
            }
        }
    }
    private void setUTopRight(){
        for (int i = 0; i < 2; i++) {
            for (int j = 6; j < MAX_Column; j++) {
                Board[i][j].setCategory(BoardToken.boardTokenCategory.UNAVAILABLE);
            }
        }
    }
    private void setUDownRight(){
        for (int i = 7; i < MAX_Row; i++) {
            for (int j = 6; j < MAX_Column; j++) {
                Board[i][j].setCategory(BoardToken.boardTokenCategory.UNAVAILABLE);
            }
        }
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
        CommonGoalCard.add(new CommonGoalCard1(L));
        CommonGoalCard.add(new CommonGoalCard2(L));
        CommonGoalCard.add(new CommonGoalCard3(L));
        CommonGoalCard.add(new CommonGoalCard4(L));
        CommonGoalCard.add(new CommonGoalCard5(L));
        CommonGoalCard.add(new CommonGoalCard6(L));
        CommonGoalCard.add(new CommonGoalCard7(L));
        CommonGoalCard.add(new CommonGoalCard8(L));
        CommonGoalCard.add(new CommonGoalCard9(L));
        CommonGoalCard.add(new CommonGoalCard10(L));
        CommonGoalCard.add(new CommonGoalCard11(L));
        CommonGoalCard.add(new CommonGoalCard12(L));

    }

    public void Start(int numPlayers) {
        for (int i = 0; i < MAX_Row; i++) {
            for (int j = 0; j < MAX_Column; j++) {
                putTile(i, j);
            }
        }
        int randIndex = new Random().nextInt(CommonGoalCard.size());
        commonGoalCard1 = CommonGoalCard.remove(randIndex);
        randIndex = new Random().nextInt(CommonGoalCard.size());
        commonGoalCard2 = CommonGoalCard.remove(randIndex);
    }

    public int checkCG(BookShelf bs){
        int score=0;
        commonGoalCard1.checkGoal(bs);
        commonGoalCard2.checkGoal(bs);
       score= commonGoalCard1.getPoints();
       score= commonGoalCard2.getPoints();
       return score;
    }


    public void restore() {
        if (gameChecker.isRestorable(Board)) {
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

    public ArrayList<ItemTile> getTiles(ArrayList<Integer> requestedTiles) {
        PropertyChangeEvent evt = new PropertyChangeEvent(
                this,
                "BOARD_CHANGED",
                this.Board,
                Board);
        ArrayList<ItemTile> tiles = new ArrayList<>();
        int i = 0;
        while (i < requestedTiles.size()) {
            ItemTile tile = getBoardTile(requestedTiles.get(i), requestedTiles.get(i + 1)).getTile();
            getBoardTile(requestedTiles.get(i), requestedTiles.get(i + 1)).freeTile();
            tiles.add(tile);
            i = i + 2;
        }
        for(PropertyChangeListener l:listeners){
            l.propertyChange(evt);
        }
        return tiles;
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

    public BoardToken getBoardTile(int i, int j){
        return Board[i][j];
    }

    public ArrayList<PropertyChangeListener> getListener() {
        return listeners;
    }

    public void setListeners(ArrayList<VirtualView> listener) {
         listeners.addAll(listener);
    }

    public CommonGoalCard getCommonGoalCard1() {
        return commonGoalCard1;
    }

    public CommonGoalCard getCommonGoalCard2() {
        return commonGoalCard2;
    }

    public GameChecker getGameChecker() {
        return gameChecker;
    }
}
