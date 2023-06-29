package it.polimi.ingsw.model.board;

import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.Network.Server.VirtualView;
import it.polimi.ingsw.model.GameChecker;
import it.polimi.ingsw.model.board.goalCards.*;
import it.polimi.ingsw.model.player.BookShelf;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * class that rapresent the game board
 * @author Eliahu Cohen
 *
 */

public class LivingRoom implements Serializable {
    public static final int MAX_Row=9;
    public static final int MAX_Column=9;
    transient ArrayList<PropertyChangeListener> listeners=new ArrayList<>();

    private BoardToken[][] Board = new BoardToken[MAX_Row][MAX_Column];
    private transient Launcher L;
    private  final Bag bag;
    private transient GameChecker gameChecker;

    private transient final ArrayList<CGCKey> commonGoalCards = new ArrayList<>();
    private transient CommonGoalCard commonGoalCard1;
    private transient CommonGoalCard commonGoalCard2;

    private int pointsCGD1;
    private int pointsCGD2;
    private Integer idCGC1;
    private Integer idCGC2;

    /**
     * Constructor that builds the board and the other living room components
     * @author Eliahu Cohen
     * @param L launcher passed from the game
     *
     */
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


    }

    /**
     * set the unavailable spaces
     * @author Eliahu Cohen
     *
     */
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
    /**
     * set the unavailable spaces in the top left
     * @author Eliahu Cohen
     *
     */
    private void setUTopLeft(){
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                Board[i][j].setCategory(BoardToken.boardTokenCategory.UNAVAILABLE);
            }
        }
    }
    /**
     * set the unavailable spaces in the down left
     * @author Eliahu Cohen
     *
     */
    private void setUDownLeft(){
        for (int i = 7; i < MAX_Row; i++) {
            for (int j = 0; j < 3; j++) {
                Board[i][j].setCategory(BoardToken.boardTokenCategory.UNAVAILABLE);
            }
        }
    }
    /**
     * set the unavailable spaces in the top right
     * @author Eliahu Cohen
     *
     */
    private void setUTopRight(){
        for (int i = 0; i < 2; i++) {
            for (int j = 6; j < MAX_Column; j++) {
                Board[i][j].setCategory(BoardToken.boardTokenCategory.UNAVAILABLE);
            }
        }
    }
    /**
     * set the unavailable spaces in the down right
     * @author Eliahu Cohen
     *
     */
    private void setUDownRight(){
        for (int i = 7; i < MAX_Row; i++) {
            for (int j = 6; j < MAX_Column; j++) {
                Board[i][j].setCategory(BoardToken.boardTokenCategory.UNAVAILABLE);
            }
        }
    }

    /**
     * method that sets the 3 player spaces
     * @author Eliahu Cohen
     *
     */
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

    /**
     * method that sets the 4 player spaces
     * @author Eliahu Cohen
     *
     */
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

    /**
     * method that create the 12 common goal cards
     * @author Eliahu Cohen
     *
     */
    private void SetCommonGoalCard(){

        commonGoalCards.add(new CGCKey(new CommonGoalCard1(L.getNumPlayers()),1));
        commonGoalCards.add(new CGCKey(new CommonGoalCard2(L.getNumPlayers()),2));
        commonGoalCards.add(new CGCKey(new CommonGoalCard3(L.getNumPlayers()),3));
        commonGoalCards.add(new CGCKey(new CommonGoalCard4(L.getNumPlayers()),4));
        commonGoalCards.add(new CGCKey(new CommonGoalCard5(L.getNumPlayers()),5));
        commonGoalCards.add(new CGCKey(new CommonGoalCard6(L.getNumPlayers()),6));
        commonGoalCards.add(new CGCKey(new CommonGoalCard7(L.getNumPlayers()),7));
        commonGoalCards.add(new CGCKey(new CommonGoalCard8(L.getNumPlayers()),8));
        commonGoalCards.add(new CGCKey(new CommonGoalCard9(L.getNumPlayers()),9));
        commonGoalCards.add(new CGCKey(new CommonGoalCard10(L.getNumPlayers()),10));
        commonGoalCards.add(new CGCKey(new CommonGoalCard11(L.getNumPlayers()),11));
        commonGoalCards.add(new CGCKey(new CommonGoalCard12(L.getNumPlayers()),12));

    }

    /**
     * method that stars the living room based on the number of players
     * @author Eliahu Cohen
     * @param numPlayers that play the game
     *
     */
    public void start(int numPlayers) {
        for (int i = 0; i < MAX_Row; i++) {
            for (int j = 0; j < MAX_Column; j++) {
                putTile(i, j);
            }
        }
        int randIndex = new Random().nextInt(commonGoalCards.size());
        CGCKey key=commonGoalCards.remove(randIndex);
        commonGoalCard1 =key.getCommonGoalCard();
        idCGC1=key.getId();
        randIndex = new Random().nextInt(commonGoalCards.size());
        key=commonGoalCards.remove(randIndex);
        commonGoalCard2 = key.getCommonGoalCard();
        idCGC2=key.getId();
    }

    /**
     * @author Eliahu Cohen
     * @param bs player bookshelf
     * @return the score of the player after the check of the common goal cards
     */
    public int checkCG(BookShelf bs){
        int score=0;
        if(!bs.isCommonGoalCard1Completed()) {
            int score1=0;
            commonGoalCard1.checkGoal(bs);
            score1= commonGoalCard1.getPoints();
            if(score1!=0)bs.setCommonGoalCard1Completed(true);
            score+=score1;
        }
        if(!bs.isCommonGoalCard2Completed()) {
            int score2=0;
            commonGoalCard2.checkGoal(bs);
            score2= commonGoalCard2.getPoints();
            if(score2!=0)bs.setCommonGoalCard2Completed(true);
            score+=score2;
        }
        pointsCGD1= commonGoalCard1.getScore();
        pointsCGD2=commonGoalCard2.getScore();

       return score;
    }

    /**
     * method that restores the tiles on the living room
     * @author Eliahu Cohen
     *
     */
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

    /**
     * @author Eliahu Cohen
     * @param i row
     * @param j column
     * @return board
     */
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

    public int getPointsCGD1() {
        return pointsCGD1;
    }

    public int getPointsCGD2() {
        return pointsCGD2;
    }

    public Bag getBag() {
        return bag;
    }

    /**
     * @author Eliahu Cohen
     * @param requestedTiles tiles that the player want to extract from the living room
     * @return the tiles
     */
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

    public void SetBoard(){
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
        listeners=new ArrayList<>();
        listeners.clear();
        listeners.addAll(listener);
    }

    public void setLauncher(Launcher l) {
        L = l;
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

    public void setGameChecker(GameChecker gameChecker){
        this.gameChecker=gameChecker;
    }

    public void setCommonGoalCard1(CommonGoalCard commonGoalCard1) {
        this.commonGoalCard1 = commonGoalCard1;
    }

    public void setCommonGoalCard2(CommonGoalCard commonGoalCard2) {
        this.commonGoalCard2 = commonGoalCard2;
    }

    public Integer getIdCGC1() {
        return idCGC1;
    }

    public void setIdCGC1(Integer idCGC1) {
        this.idCGC1 = idCGC1;
    }

    public Integer getIdCGC2() {
        return idCGC2;
    }

    public void setIdCGC2(Integer idCGC2) {
        this.idCGC2 = idCGC2;
    }

    public void setBoard(BoardToken[][] board) {
        Board = board;
    }
}
