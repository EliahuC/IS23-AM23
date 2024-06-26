package it.polimi.ingsw.model;

import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.model.board.BoardToken;
import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.player.BookShelf;
import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  Class that checks all the legal/illegal moves of the game
 * @author Simone Controguerra
 *
 */
public class GameChecker {

    private boolean restorable;
    private final int maxPickableTiles;
    private boolean legalSelection;
    private boolean lastRound;
    private boolean isYourTurn;
    private final int numPlayers ;
    private final Launcher L;
    private static final int boardWidth = 9;
    private static final int shelfRows = 6;
    private static final int shelfCols = 5;

    public GameChecker(Launcher L) {
        this.restorable = false;
        this.maxPickableTiles = 3;
        this.legalSelection = false;
        this.lastRound = false;
        this.isYourTurn = false;
        this.L = L;
        this.numPlayers= L.getNumPlayers();
    }

    /**
     * @author Simone Controguerra
     * @param board is the Living Room's board
     * @return true if the board needs to be restored, according to game's rules
     */
    public boolean isRestorable(BoardToken[][] board) {
        if(boardIsEmpty(board)){
            restorable=true;
            return true;
        }
        restorable=true;
        for (int i = 0; i < boardWidth; i++){
            for (int j = 0; j < boardWidth; j++){
                if (!boardBoxIsEmpty(board[i][j]) && boardBoxIsValid(board[i][j])){
                    if (!hasNotAdjacentTiles(board[i][j])) {
                        restorable = false;
                    }
                }
            }
        }
        return restorable;
    }

    /**
     * @author Simone Controguerra
     * @param board is the living room's board
     * @return true if the board is currently empty, with no tiles on it
     */
    private boolean boardIsEmpty(BoardToken[][] board){
        int counter = 0;
        for (int i = 0; i < boardWidth; i++){
            for (int j = 0; j < boardWidth; j++) {
                if(boardBoxIsEmpty(board[i][j]))
                    counter++;
            }
        }
        return counter == boardWidth * boardWidth;
    }

    public boolean getRestorable(){
        return restorable;
    }

    /**
     * @author Simone Controguerra
     * @param bs is the player's bookshelf
     */
    public void checkColumnCapability(BookShelf bs){
        if (fourthRowIsFull(bs)){
            if (fifthRowIsFull(bs)){
                sixthRowIsFull(bs);
            }
        }
    }

    /**
     * @author Simone Controguerra
     * @param bs is the player's bookshelf
     * @return the maximum number the player can pick from the board, according to their bookshelf's capability
     */
    public int getMaxPickableTiles(BookShelf bs){
        return Arrays.stream(bs.getMaxPickableTiles()).max().getAsInt();
    }

    public int getMaxPickableTiles(){
        return maxPickableTiles;
    }

    /**
     * @author Simone Controguerra
     * @param t1 is the board's space hosting the tile chosen by the player
     * @return true if the tile is in a legal position on the board
     */
    public boolean isLegalAction(BoardToken t1){
        legalSelection = isExternal(t1) && !boardBoxIsEmpty(t1);
        return legalSelection;
    }

    /**
     * @author Simone Controguerra
     * @param t1 is the board's space hosting the tile chosen by the player
     * @param t2 is the board's space hosting the tile chosen by the player
     * @return true if the tiles are in a legal position on the board
     */
    public boolean isLegalAction(BoardToken t1, BoardToken t2){
        if (boardBoxIsValid(t1) && boardBoxIsValid(t2) && !boardBoxIsEmpty(t1) && !boardBoxIsEmpty(t2)){
            //Checking if tiles are adjacent and in the same column.
            if (sameColumn(t1, t2) && verticallyAdjacent(t1, t2) && isExternal(t1, t2)) {
                legalSelection = true;
                return true;
            }
            //Checking if tiles are adjacent and in the same row.
            if (sameRow(t1, t2) && horizontallyAdjacent(t1, t2) && isExternal(t1, t2)) {
                legalSelection = true;
                return true;
            }
        }
        //It was not a legal move.
        legalSelection = false;
        return false;
    }

    /**
     * @author Simone Controguerra
     * @param t1 is the board's space hosting the tile chosen by the player
     * @param t2 is the board's space hosting the tile chosen by the player
     * @param t3 is the board's space hosting the tile chosen by the player
     * @return true if the tiles are in a legal position on the board
     */
    public boolean isLegalAction(BoardToken t1, BoardToken t2, BoardToken t3){
        if (boardBoxIsValid(t1) && boardBoxIsValid(t2) && boardBoxIsValid(t3) && !boardBoxIsEmpty(t1) && !boardBoxIsEmpty(t2) && !boardBoxIsEmpty(t3)){
            //Checking if tiles are adjacent and in the same column.
            if (sameColumn(t1, t2, t3) && verticallyAdjacent(t1, t2, t3) && isExternal(t1, t2, t3)) {
                legalSelection = true;
                return true;
            }
            //Checking if tiles are adjacent and in the same row.
            if (sameRow(t1, t2, t3) && horizontallyAdjacent(t1, t2, t3) && isExternal(t1, t2, t3)) {
                legalSelection = true;
                return true;
            }
        }
        //It was not a legal move.
        legalSelection = false;
        return false;
    }

    public boolean getLegalSelection() {
        return legalSelection;
    }

    /**
     * @author Simone Controguerra
     * @param bs is the player's bookshelf
     */
    public void isBookShelfFull(BookShelf bs) {
        if (sixthRowIsFull(bs))
            lastRound = true;
    }

    public boolean getLastRound(){
        return lastRound;
    }



    /**
     * @author Simone Controguerra
     * @param t is the board's space hosting the tile chosen by the player
     * @return true if the tile has no adjacent tiles on the board
     */
    private boolean hasNotAdjacentTiles(BoardToken t){
        //Tile is on the left side.
        if (boardTileLeft(t)){
             //Checking the upper tile.                                && Checking the right tile.                                && Checking the lower tile.
            if(boardBoxIsEmpty(t.getBoard()[t.getRow()-1][t.getCol()]) && boardBoxIsEmpty(t.getBoard()[t.getRow()][t.getCol()+1]) && boardBoxIsEmpty(t.getBoard()[t.getRow()+1][t.getCol()]))
                return true;
            }
        //Tile is on the right side.
        if (boardTileRight(t)){
             //Checking the upper tile.                                &&  Checking the lower tile.                               && Checking the left tile.
            if(boardBoxIsEmpty(t.getBoard()[t.getRow()-1][t.getCol()]) && boardBoxIsEmpty(t.getBoard()[t.getRow()+1][t.getCol()]) && boardBoxIsEmpty(t.getBoard()[t.getRow()][t.getCol()-1]))
                return true;
        }
        //Tile is on the upper side.
        if (boardTileUpper(t)){
             //Checking the right tile.                                && Checking the lower tile.                                && Checking the left tile.
            if(boardBoxIsEmpty(t.getBoard()[t.getRow()][t.getCol()+1]) && boardBoxIsEmpty(t.getBoard()[t.getRow()+1][t.getCol()]) && boardBoxIsEmpty(t.getBoard()[t.getRow()][t.getCol()-1]))
                return true;
        }
        //Tile is on the lower side.
        if (boardTileLower(t)){
             //Checking the upper tile.                                && Checking the right tile.                                && Checking the left tile.
            if(boardBoxIsEmpty(t.getBoard()[t.getRow()-1][t.getCol()]) && boardBoxIsEmpty(t.getBoard()[t.getRow()][t.getCol()+1]) && boardBoxIsEmpty(t.getBoard()[t.getRow()][t.getCol()-1]))
                return true;
        }
        //Tile is not in any particular position.
        if (boardTileNoBorderline(t)){
             //Checking the upper tile.                                && Checking the right tile.                                && Checking the lower tile.                                && Checking the left tile.
            if(boardBoxIsEmpty(t.getBoard()[t.getRow()-1][t.getCol()]) && boardBoxIsEmpty(t.getBoard()[t.getRow()][t.getCol()+1]) && boardBoxIsEmpty(t.getBoard()[t.getRow()+1][t.getCol()]) && boardBoxIsEmpty(t.getBoard()[t.getRow()][t.getCol()-1])){
                return true;
            }
        }
        return false;
    }

    /**
     * @author Simone Controguerra
     * @param t is the board's space hosting the tile chosen by the player
     * @return true if the tile is in an external position on the board
     */
    private boolean isExternal (BoardToken t) {
        if(boardBoxIsValid(t))
            return checkFreeSides(t);
        return false;
    }

    /**
     * @author Simone Controguerra
     * @param t1 is the board's space hosting the tile chosen by the player
     * @param t2 is the board's space hosting the tile chosen by the player
     * @return true if the tiles are in an external position on the board
     */
    private boolean isExternal (BoardToken t1, BoardToken t2){
        if(boardBoxIsValid(t1) && boardBoxIsValid(t2))
            return checkFreeSides(t1) && checkFreeSides(t2);
        return false;
    }


    /**
     * @author Simone Controguerra
     * @param t1 is the board's space hosting the tile chosen by the player
     * @param t2 is the board's space hosting the tile chosen by the player
     * @param t3 is the board's space hosting the tile chosen by the player
     * @return true if the tiles are in an external position on the board
     */
    private boolean isExternal (BoardToken t1, BoardToken t2, BoardToken t3){
        if(boardBoxIsValid(t1) && boardBoxIsValid(t2) && boardBoxIsValid(t3))
            return checkFreeSides(t1) && checkFreeSides(t2) && checkFreeSides(t3);
        return false;
    }

    /**
     * @author Simone Controguerra
     * @param t is the board's space hosting the tile chosen by the player
     * @return true if the tile is in a legit position on the board, according to empty spaces and number of players
     */
    private boolean boardBoxIsValid(BoardToken t){
        BoardToken.boardTokenCategory category = t.getCategory();
        if(category == BoardToken.boardTokenCategory.NORMAL || (category == BoardToken.boardTokenCategory.THREE && numPlayers >= 3) || (category == BoardToken.boardTokenCategory.FOUR && numPlayers == 4))
            return true;
        return false;
    }

    /**
     * @author Simone Controguerra
     * @param t is the board's space hosting the tile chosen by the player
     * @return true if there's no tile in that living room's space
     */
    private boolean boardBoxIsEmpty(BoardToken t){
        return t.getTile() == null;
    }

    /**
     * @author Simone Controguerra
     * @param t is a bookshelf's space which can host a tile
     * @return true if there's no tile in that bookshelf's space
     */
    private boolean shelfBoxIsEmpty(ItemTile t){
        return t == null;
    }

    /*private void firstRowsAreFull(BookShelf bs){
        for(int i = 5; i > shelfBoundRowForMaxCapability; i--){
            for(int j = 0; j < shelfCols; j++){
                if (shelfBoxIsEmpty(bs.getTile(i,j)));
            }
        }
    }*/

    /**
     * @author Simone Controguerra
     * @param bs is the player's bookshelf
     * @return true if the first four rows of the bookshelf are full
     */
    private boolean fourthRowIsFull(BookShelf bs) {
        boolean notYetFull = false;
        for (int i = 0; i < shelfCols; i++) {
            if (shelfBoxIsEmpty(bs.getTile(2, i))) {
                notYetFull = true;
            }else{
                bs.setCapability(i,2);
            }
            if (notYetFull && i == shelfCols - 1)
                return false;
        }
        return true;
    }

    /**
     * @author Simone Controguerra
     * @param bs is the player's bookshelf
     * @return true if the first five rows of the bookshelf are full
     */
    private boolean fifthRowIsFull(BookShelf bs) {
        boolean notYetFull = false;
        for (int i = 0; i < shelfCols; i++) {
            if (shelfBoxIsEmpty(bs.getTile(1, i))) {
                notYetFull = true;
            }else{
                bs.setCapability(i, 1);
            }
            if (notYetFull && i == shelfCols - 1)
                return false;
        }
        return true;
    }
    /**
     * @author Simone Controguerra
     * @param bs is the player's bookshelf
     * @return true if the bookshelf is completely full
     */
    private boolean sixthRowIsFull(BookShelf bs) {
        boolean notYetFull = false;
        for (int i = 0; i < shelfCols; i++) {
            if (shelfBoxIsEmpty(bs.getTile(0, i))) {
                notYetFull = true;
            }else{
                bs.setCapability(i, 0);
            }
            if (notYetFull && i == shelfCols - 1)
                return false;
        }
        return true;
    }

    /**
     * @author Simone Controguerra
     * @param t1 is the board's space hosting the tile chosen by the player
     * @param t2 is the board's space hosting the tile chosen by the player
     * @return true if the tiles are in the same column on the board
     */
    private boolean sameColumn(BoardToken t1, BoardToken t2){
        return t1.getCol() == t2.getCol();
    }

    /**
     * @author Simone Controguerra
     * @param t1 is the board's space hosting the tile chosen by the player
     * @param t2 is the board's space hosting the tile chosen by the player
     * @param t3 is the board's space hosting the tile chosen by the player
     * @return true if the tiles are in the same column on the board
     */
    private boolean sameColumn(BoardToken t1, BoardToken t2, BoardToken t3){
        return t1.getCol() == t2.getCol() && t1.getCol() == t3.getCol();
    }

    /**
     * @author Simone Controguerra
     * @param t1 is the board's space hosting the tile chosen by the player
     * @param t2 is the board's space hosting the tile chosen by the player
     * @return true if the tiles are in the same row on the board
     */
    private boolean sameRow(BoardToken t1, BoardToken t2){
        return t1.getRow() == t2.getRow();
    }

    /**
     * @author Simone Controguerra
     * @param t1 is the board's space hosting the tile chosen by the player
     * @param t2 is the board's space hosting the tile chosen by the player
     * @param t3 is the board's space hosting the tile chosen by the player
     * @return true if the tiles are in the same row on the board
     */
    private boolean sameRow(BoardToken t1, BoardToken t2, BoardToken t3){
        return t1.getRow() == t2.getRow() && t1.getRow() == t3.getRow();
    }

    /**
     * @author Simone Controguerra
     * @param t1 is the board's space hosting the tile chosen by the player
     * @param t2 is the board's space hosting the tile chosen by the player
     * @return true if the tiles are adjacent in a vertical way
     */
    private boolean verticallyAdjacent(BoardToken t1, BoardToken t2){
        return t1.getRow() == t2.getRow() - 1;
    }

    /**
     * @author Simone Controguerra
     * @param t1 is the board's space hosting the tile chosen by the player
     * @param t2 is the board's space hosting the tile chosen by the player
     * @param t3 is the board's space hosting the tile chosen by the player
     * @return true if the tiles are adjacent in a vertical way
     */
    private boolean verticallyAdjacent(BoardToken t1, BoardToken t2, BoardToken t3){
        return t1.getRow() == t2.getRow() - 1 && t1.getRow() == t3.getRow() - 2;
    }

    /**
     * @author Simone Controguerra
     * @param t1 is the board's space hosting the tile chosen by the player
     * @param t2 is the board's space hosting the tile chosen by the player
     * @return true if the tiles are adjacent in a horizontal way
     */
    private boolean horizontallyAdjacent(BoardToken t1, BoardToken t2){
        return t1.getCol() == t2.getCol() - 1;
    }

    /**
     * @author Simone Controguerra
     * @param t1 is the board's space hosting the tile chosen by the player
     * @param t2 is the board's space hosting the tile chosen by the player
     * @param t3 is the board's space hosting the tile chosen by the player
     * @return true if the tiles are adjacent in a horizontal way
     */
    private boolean horizontallyAdjacent(BoardToken t1, BoardToken t2, BoardToken t3){
        return t1.getCol() == t2.getCol() - 1 && t1.getCol() == t3.getCol() - 2;
    }

    /**
     * @author Simone Controguerra
     * @param t is the board's space hosting the tile chosen by the player
     * @return true if the tile is on the upper side corner of the board
     */
    private boolean boardTileUpper(BoardToken t){
        return t.getRow() == 0;
    }

    /**
     * @author Simone Controguerra
     * @param t is the board's space hosting the tile chosen by the player
     * @return true if the tile is on the lower side corner of the board
     */
    private boolean boardTileLower(BoardToken t){
        return t.getRow() == boardWidth-1;
    }

    /**
     * @author Simone Controguerra
     * @param t is the board's space hosting the tile chosen by the player
     * @return true if the tile is on the left side corner of the board
     */
    private boolean boardTileLeft(BoardToken t){
        return t.getCol() == 0;
    }

    /**
     * @author Simone Controguerra
     * @param t is the board's space hosting the tile chosen by the player
     * @return true if the tile is on the right side corner of the board
     */
    private boolean boardTileRight(BoardToken t){
        return t.getCol() == boardWidth-1;
    }

    /**
     * @author Simone Controguerra
     * @param t is the board's space hosting the tile chosen by the player
     * @return true if the tile is in any side position on the board
     */
    private boolean boardTileNoBorderline(BoardToken t){
        return t.getRow() != 0 && t.getRow() != boardWidth-1 && t.getCol() != 0 && t.getCol() != boardWidth-1;
    }

    /*private boolean isInBorderlinePosition(BoardToken t){
        return boardTileLeft(t) || boardTileRight(t) || boardTileLower(t) || boardTileUpper(t);
    }*/

    /*private boolean checkSides(BoardToken t){
        return verticalCheck(t) && horizontalCheck(t);
    }

    private boolean checkSides(BoardToken t1, BoardToken t2){
        if(sameColumn(t1,t2))
            return verticalCheck(t1,t2);
        if(sameRow(t1,t2))
            return horizontalCheck(t1,t2);
        return false;
    }

    private boolean checkSides(BoardToken t1, BoardToken t2, BoardToken t3){
        if(sameColumn(t1,t2,t3))
            return verticalCheck(t1,t2,t3);
        if(sameRow(t1,t2))
            return horizontalCheck(t1,t2,t3);
        return false;
    }*/

    /*private boolean verticalCheck(BoardToken t){
        if (!boardTileUpper(t) && !boardTileLower(t)){
            if(!boardBoxIsEmpty(t.getBoard()[t.getRow()-1][t.getCol()])){
                if(boardBoxIsEmpty(t.getBoard()[t.getRow()+1][t.getCol()]))
                    return true;
            }else{
                if(!boardBoxIsEmpty(t.getBoard()[t.getRow()+1][t.getCol()]))
                    return true;
            }
            if(boardBoxIsEmpty(t.getBoard()[t.getRow()-1][t.getCol()]) && boardBoxIsEmpty(t.getBoard()[t.getRow()+1][t.getCol()]))
                return true;
        }
        return boardTileUpper(t) || boardTileLower(t);
    }

    private boolean horizontalCheck(BoardToken t){
        if (!boardTileLeft(t) && !boardTileRight(t)){
            if(!boardBoxIsEmpty(t.getBoard()[t.getRow()][t.getCol()-1])){
                if(boardBoxIsEmpty(t.getBoard()[t.getRow()][t.getCol()+1]))
                    return true;
            }else{
                if(!boardBoxIsEmpty(t.getBoard()[t.getRow()][t.getCol()+1]))
                    return true;
            }
            if(boardBoxIsEmpty(t.getBoard()[t.getRow()][t.getCol()-1]) && boardBoxIsEmpty(t.getBoard()[t.getRow()][t.getCol()+1]))
                return true;
        }
        return boardTileLeft(t) || boardTileRight(t);
    }

    private boolean verticalCheck(BoardToken t1, BoardToken t2){
        if(!isInBorderlinePosition(t1) && !isInBorderlinePosition(t2)){
            if((!boardBoxIsEmpty(t1.getBoard()[t1.getRow()][t1.getCol()-1]) && boardBoxIsEmpty(t1.getBoard()[t1.getRow()][t1.getCol()+1]) && !boardBoxIsEmpty(t2.getBoard()[t2.getRow()][t2.getCol()-1]) && boardBoxIsEmpty(t2.getBoard()[t2.getRow()][t2.getCol()+1])) || (!boardBoxIsEmpty(t1.getBoard()[t1.getRow()][t1.getCol()+1]) && boardBoxIsEmpty(t1.getBoard()[t1.getRow()][t1.getCol()-1]) && !boardBoxIsEmpty(t2.getBoard()[t2.getRow()][t2.getCol()+1]) && boardBoxIsEmpty(t2.getBoard()[t2.getRow()][t2.getCol()-1]))){
                if(boardBoxIsEmpty(t1.getBoard()[t1.getRow()-1][t1.getCol()]) || boardBoxIsEmpty(t2.getBoard()[t2.getRow()+1][t2.getCol()]))
                    return true;
            }
        }
        if(!boardTileLeft(t1) && !boardTileLeft(t2) && !boardTileRight(t1) && !boardTileRight(t2)) {
            if ((boardBoxIsEmpty(t1.getBoard()[t1.getRow()][t1.getCol() - 1]) && boardBoxIsEmpty(t1.getBoard()[t1.getRow()][t1.getCol() + 1])) || (boardBoxIsEmpty(t2.getBoard()[t2.getRow()][t2.getCol() - 1]) && boardBoxIsEmpty(t2.getBoard()[t2.getRow()][t2.getCol() + 1])))
                return true;
        }
        if(boardTileLower(t2)){
            if((boardBoxIsEmpty(t1.getBoard()[t1.getRow()][t1.getCol()-1]) && boardBoxIsEmpty(t2.getBoard()[t2.getRow()][t2.getCol()-1])) || (boardBoxIsEmpty(t1.getBoard()[t1.getRow()][t1.getCol()+1]) && boardBoxIsEmpty(t2.getBoard()[t2.getRow()][t2.getCol()+1])))
                return true;
        }
        if(boardTileUpper(t1)){
            if((boardBoxIsEmpty(t1.getBoard()[t1.getRow()][t1.getCol()-1]) && boardBoxIsEmpty(t2.getBoard()[t2.getRow()][t2.getCol()-1])) || (boardBoxIsEmpty(t1.getBoard()[t1.getRow()][t1.getCol()+1]) && boardBoxIsEmpty(t2.getBoard()[t2.getRow()][t2.getCol()+1])))
                return true;
        }
        return (boardTileLeft(t1) && boardTileLeft(t2)) || (boardTileRight(t1) && boardTileRight(t2));
    }

    private boolean horizontalCheck(BoardToken t1, BoardToken t2){
        if(!isInBorderlinePosition(t1) && !isInBorderlinePosition(t2)){
            if((!boardBoxIsEmpty(t1.getBoard()[t1.getRow()-1][t1.getCol()]) && boardBoxIsEmpty(t1.getBoard()[t1.getRow()+1][t1.getCol()]) && !boardBoxIsEmpty(t2.getBoard()[t2.getRow()-1][t2.getCol()]) && boardBoxIsEmpty(t2.getBoard()[t2.getRow()+1][t2.getCol()])) || (!boardBoxIsEmpty(t1.getBoard()[t1.getRow()+1][t1.getCol()]) && boardBoxIsEmpty(t1.getBoard()[t1.getRow()-1][t1.getCol()]) && !boardBoxIsEmpty(t2.getBoard()[t2.getRow()+1][t2.getCol()]) && boardBoxIsEmpty(t2.getBoard()[t2.getRow()-1][t2.getCol()]))){
                if(boardBoxIsEmpty(t1.getBoard()[t1.getRow()][t1.getCol()-1]) || boardBoxIsEmpty(t2.getBoard()[t2.getRow()][t2.getCol()+1]))
                    return true;
            }
        }
        if(!boardTileUpper(t1) && !boardTileUpper(t2) && !boardTileLower(t1) && !boardTileLower(t2)) {
            if ((boardBoxIsEmpty(t1.getBoard()[t1.getRow() - 1][t1.getCol()]) && boardBoxIsEmpty(t1.getBoard()[t1.getRow() + 1][t1.getCol()])) || (boardBoxIsEmpty(t2.getBoard()[t2.getRow() - 1][t2.getCol()]) && boardBoxIsEmpty(t2.getBoard()[t2.getRow() + 1][t2.getCol()])))
                return true;
        }
        if(boardTileRight(t2)){
            if((boardBoxIsEmpty(t1.getBoard()[t1.getRow()-1][t1.getCol()]) && boardBoxIsEmpty(t2.getBoard()[t2.getRow()-1][t2.getCol()])) || (boardBoxIsEmpty(t1.getBoard()[t1.getRow()+1][t1.getCol()]) && boardBoxIsEmpty(t2.getBoard()[t2.getRow()+1][t2.getCol()])))
                return true;
        }
        if(boardTileLeft(t1)){
            if((boardBoxIsEmpty(t1.getBoard()[t1.getRow()-1][t1.getCol()]) && boardBoxIsEmpty(t2.getBoard()[t2.getRow()-1][t2.getCol()])) || (boardBoxIsEmpty(t1.getBoard()[t1.getRow()+1][t1.getCol()]) && boardBoxIsEmpty(t2.getBoard()[t2.getRow()+1][t2.getCol()])))
                return true;
        }
        return (boardTileUpper(t1) && boardTileUpper(t2)) || (boardTileLower(t1) && boardTileLower(t2));
    }

    private boolean verticalCheck(BoardToken t1, BoardToken t2, BoardToken t3){
        if(!isInBorderlinePosition(t1) && !isInBorderlinePosition(t2) && !isInBorderlinePosition(t3)){
            if((!boardBoxIsEmpty(t1.getBoard()[t1.getRow()][t1.getCol()-1]) && boardBoxIsEmpty(t1.getBoard()[t1.getRow()][t1.getCol()+1]) && !boardBoxIsEmpty(t2.getBoard()[t2.getRow()][t2.getCol()-1]) && boardBoxIsEmpty(t2.getBoard()[t2.getRow()][t2.getCol()+1]) && !boardBoxIsEmpty(t3.getBoard()[t3.getRow()][t3.getCol()-1]) && boardBoxIsEmpty(t3.getBoard()[t3.getRow()][t3.getCol()+1])) || (!boardBoxIsEmpty(t1.getBoard()[t1.getRow()][t1.getCol()+1]) && boardBoxIsEmpty(t1.getBoard()[t1.getRow()][t1.getCol()-1]) && !boardBoxIsEmpty(t2.getBoard()[t2.getRow()][t2.getCol()+1]) && boardBoxIsEmpty(t2.getBoard()[t2.getRow()][t2.getCol()-1])  && !boardBoxIsEmpty(t3.getBoard()[t3.getRow()][t3.getCol()+1]) && boardBoxIsEmpty(t3.getBoard()[t3.getRow()][t3.getCol()-1]))){
                if(boardBoxIsEmpty(t1.getBoard()[t1.getRow()-1][t1.getCol()]) || boardBoxIsEmpty(t3.getBoard()[t3.getRow()+1][t3.getCol()]))
                    return true;
            }
        }
        *//*if(!boardTileLeft(t1) && !boardTileLeft(t2) && !boardTileLeft(t3) && !boardTileRight(t1) && !boardTileRight(t2) && !boardTileRight(t3)) {
            if ((boardBoxIsEmpty(t1.getBoard()[t1.getRow()][t1.getCol() - 1]) && boardBoxIsEmpty(t1.getBoard()[t1.getRow()][t1.getCol() + 1])) || (boardBoxIsEmpty(t2.getBoard()[t2.getRow()][t2.getCol() - 1]) && boardBoxIsEmpty(t2.getBoard()[t2.getRow()][t2.getCol() + 1]))  || (boardBoxIsEmpty(t3.getBoard()[t3.getRow()][t3.getCol() - 1]) && boardBoxIsEmpty(t3.getBoard()[t3.getRow()][t3.getCol() + 1])))
                return true;
        }*//*
        if(boardTileLower(t3)){
            if (horizontalSidesAreFree(t1, t2, t3)) return true;
        }
        if(boardTileUpper(t1)){
            if (horizontalSidesAreFree(t1, t2, t3)) return true;
        }
        return false;
    }*/

    /*private boolean horizontalSidesAreFree(BoardToken t1, BoardToken t2, BoardToken t3) {
        if((boardBoxIsEmpty(t1.getBoard()[t1.getRow()][t1.getCol()-1]) && boardBoxIsEmpty(t2.getBoard()[t2.getRow()][t2.getCol()-1]) && boardBoxIsEmpty(t3.getBoard()[t3.getRow()][t3.getCol()-1])) || (boardBoxIsEmpty(t1.getBoard()[t1.getRow()][t1.getCol()+1]) && boardBoxIsEmpty(t2.getBoard()[t2.getRow()][t2.getCol()+1]) && boardBoxIsEmpty(t3.getBoard()[t3.getRow()][t3.getCol()+1])))
            return true;
        return false;
    }

    private boolean horizontalCheck(BoardToken t1, BoardToken t2, BoardToken t3){
        if(!isInBorderlinePosition(t1) && !isInBorderlinePosition(t2) && !isInBorderlinePosition(t3)){
            if((!boardBoxIsEmpty(t1.getBoard()[t1.getRow()-1][t1.getCol()]) && boardBoxIsEmpty(t1.getBoard()[t1.getRow()+1][t1.getCol()]) && !boardBoxIsEmpty(t2.getBoard()[t2.getRow()-1][t2.getCol()]) && boardBoxIsEmpty(t2.getBoard()[t2.getRow()+1][t2.getCol()]) && !boardBoxIsEmpty(t3.getBoard()[t3.getRow()-1][t3.getCol()]) && boardBoxIsEmpty(t3.getBoard()[t3.getRow()+1][t3.getCol()])) || (!boardBoxIsEmpty(t1.getBoard()[t1.getRow()+1][t1.getCol()]) && boardBoxIsEmpty(t1.getBoard()[t1.getRow()-1][t1.getCol()]) && !boardBoxIsEmpty(t2.getBoard()[t2.getRow()+1][t2.getCol()]) && boardBoxIsEmpty(t2.getBoard()[t2.getRow()-1][t2.getCol()]) && !boardBoxIsEmpty(t3.getBoard()[t3.getRow()+1][t3.getCol()]) && boardBoxIsEmpty(t3.getBoard()[t3.getRow()-1][t3.getCol()]))){
                if(boardBoxIsEmpty(t1.getBoard()[t1.getRow()][t1.getCol()-1]) || boardBoxIsEmpty(t3.getBoard()[t3.getRow()][t3.getCol()+1]))
                    return true;
            }
        }
        *//*if(!boardTileUpper(t1) && !boardTileUpper(t2) && !boardTileUpper(t3) && !boardTileLower(t1) && !boardTileLower(t2) && !boardTileLower(t3)) {
            if ((boardBoxIsEmpty(t1.getBoard()[t1.getRow() - 1][t1.getCol()]) && boardBoxIsEmpty(t1.getBoard()[t1.getRow() + 1][t1.getCol()])) || (boardBoxIsEmpty(t2.getBoard()[t2.getRow() - 1][t2.getCol()]) && boardBoxIsEmpty(t2.getBoard()[t2.getRow() + 1][t2.getCol()])) || (boardBoxIsEmpty(t3.getBoard()[t3.getRow() - 1][t3.getCol()]) && boardBoxIsEmpty(t3.getBoard()[t3.getRow() + 1][t3.getCol()])))
                return true;
        }*//*
        if(boardTileRight(t3)){
            if (verticalSidesAreFree(t1, t2, t3)) return true;
        }
        if(boardTileLeft(t1)){
            if (verticalSidesAreFree(t1, t2, t3)) return true;
        }
        return false;
    }*/

    /*private boolean verticalSidesAreFree(BoardToken t1, BoardToken t2, BoardToken t3) {
        if((boardBoxIsEmpty(t1.getBoard()[t1.getRow()-1][t1.getCol()]) && boardBoxIsEmpty(t2.getBoard()[t2.getRow()-1][t2.getCol()]) && boardBoxIsEmpty(t3.getBoard()[t3.getRow()-1][t3.getCol()])) || (boardBoxIsEmpty(t1.getBoard()[t1.getRow()+1][t1.getCol()]) && boardBoxIsEmpty(t2.getBoard()[t2.getRow()+1][t2.getCol()]) && boardBoxIsEmpty(t3.getBoard()[t3.getRow()+1][t3.getCol()])))
            return true;
        return false;
    }*/

    /**
     * @author Eliahu Cohen
     * @param b bookshelf to check
     * @param column to check
     * @param numberOfTiles to insert
     * @return true if you can insert that number of tiles in the selected column
     */
    public boolean checkColumn(BookShelf b,int column,int numberOfTiles){
        int counter=0;
        for(int row=0;row<BookShelf.getMAX_Row();row++){
            if(b.getTile(row,column)==null)counter++;
        }
        return counter >= numberOfTiles;
    }

    /**
     * @author Simone Controguerra
     * @param t is the board's space hosting the tile chosen by the player
     * @return true if the tile has at least one free side on the board
     */
    private boolean checkFreeSides(BoardToken t){
        int freeSides = 0;
        if(!boardTileUpper(t)){
            if(t.getBoard()[t.getRow()-1][t.getCol()].getTile()==null)
                freeSides++;
        }else
            freeSides++;
        if(!boardTileLower(t)){
            if(t.getBoard()[t.getRow()+1][t.getCol()].getTile()==null)
                freeSides++;
        }else
            freeSides++;
        if(!boardTileLeft(t)){
            if(t.getBoard()[t.getRow()][t.getCol()-1].getTile()==null)
                freeSides++;
        }else
            freeSides++;
        if(!boardTileRight(t)){
            if(t.getBoard()[t.getRow()][t.getCol()+1].getTile()==null)
                freeSides++;
        }else
            freeSides++;
        return freeSides >= 1;
    }
}
