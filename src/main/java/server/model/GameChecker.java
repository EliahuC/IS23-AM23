package server.model;

import server.model.board.LivingRoom;
import server.model.player.BookShelf;
import server.model.player.Player;
import server.Launcher;
import server.model.board.boardToken;

import java.util.Arrays;

public class GameChecker {

    private boolean restorable;
    private int maxPickableTiles;
    private boolean legalSelection;
    private boolean lastRound;
    private boolean isYourTurn;

    public GameChecker() {
        this.restorable = false;
        this.maxPickableTiles = 3;
        this.legalSelection = false;
        this.lastRound = false;
        this.isYourTurn = false;
    }


    public boolean isRestorable(LivingRoom board) {
        for (int i = 0; i < 9 && !restorable; i++){
            for (int j = 0; j < 9 && !restorable; j++){
                if (board.getBoard()[i][j].getTile() != null && boardBoxIsValid(board.getBoard()[i][j])){
                    if (!hasAdjacentTiles(board.getBoard()[i][j])) {
                        restorable = true;
                        return true;
                    }
                }
            }
        }
        restorable = false;
        return false;
    }

    public boolean getRestorable(){
        return restorable;
    }

    public int[] checkColumnCapability(BookShelf s){
        boolean firstRows = true;
        boolean fourthRow = true;
        boolean fifthRow = true;
        int maxPickableTilesArray[] = {3,3,3,3,3};
        for (int i=5; i>2 && firstRows == true; i--){
            for (int j=0; j<5 && firstRows == true; j++){
                if (s.getTile(i,j) == null)
                    firstRows = false;
            }
        }
        if (firstRows){
            for(int i=0; i<5; i++){
                if (s.getTile(i,2) == null)
                    fourthRow = false;
                else
                    maxPickableTilesArray[i] = 2;
            }
            if (fourthRow){
                for(int i=0; i<5; i++){
                    if (s.getTile(i,1) == null)
                        fifthRow = false;
                    else
                        maxPickableTilesArray[i] = 1;
                }
                if (fifthRow){
                    for(int i=0; i<5; i++){
                        if (s.getTile(i,0) != null)
                            maxPickableTilesArray[i] = 0;
                    }
                }
            }
        }
        return maxPickableTilesArray;
    }

    public int getMaxPickableTiles(int[] pickableArray){
        maxPickableTiles = Arrays.stream(pickableArray).max().getAsInt();
        return maxPickableTiles;
    }

    public int getMaxPickableTiles(){
        return maxPickableTiles;
    }

    public boolean isLegalAction(boardToken t1){
        if (isExternal(t1) && boardBoxIsValid(t1)) {
            legalSelection = true;
            return true;
        }else{
            legalSelection = false;
            return false;
        }
    }

    public boolean isLegalAction(boardToken t1, boardToken t2){
        if (boardBoxIsValid(t1) && boardBoxIsValid(t2)){
            //Checking if tiles are adjacent and in the same column.
            if (t1.getCol() == t2.getCol()) {
                if (t1.getRow() == t2.getRow() - 1) {
                    if (isExternal(t1, t2)) {
                        legalSelection = true;
                        return true;
                    }
                }
            }
            //Checking if tiles are adjacent and in the same row.
            if (t1.getRow() == t2.getRow()) {
                if (t1.getCol() == t2.getCol() - 1) {
                    if (isExternal(t1, t2)) {
                        legalSelection = true;
                        return true;
                    }
                }
            }
        }
        //It was not a legal move.
        legalSelection = false;
        return false;
    }

    public boolean isLegalAction(boardToken t1, boardToken t2, boardToken t3){
        if (boardBoxIsValid(t1) && boardBoxIsValid(t2) && boardBoxIsValid(t3)){
            //Checking if tiles are adjacent and in the same column.
            if(t1.getCol() == t2.getCol() && t2.getCol() == t3.getCol()){
                if(t1.getRow() == t2.getRow()-1 && t1.getRow() == t3.getRow()-2){
                    if(isExternal(t1, t2, t3)){
                        legalSelection = true;
                        return true;
                    }
                }
            }
            //Checking if tiles are adjacent and in the same row.
            if(t1.getRow() == t2.getRow() && t2.getRow() == t3.getRow()){
                if(t1.getCol() == t2.getCol()-1 && t1.getCol() == t3.getCol()-2){
                    if(isExternal(t1, t2, t3)){
                        legalSelection = true;
                        return true;
                    }
                }
            }
        }
        //It was not a legal move.
        legalSelection = false;
        return false;
    }

    public boolean getLegalSelection() {
        return legalSelection;
    }

    public void isBookShelfFull(BookShelf s) {
        int countTiles = 0;
        for (int i = 0; i <5; i++){
            if (s.getTile(i, 0) != null)
                countTiles++;
        }
        if (countTiles == 5)
            lastRound = true;
        lastRound = false;
    }

    public boolean getLastRound(){
        return lastRound;
    }

    public void checkRound(Player p){
        isYourTurn = p.getNowPlaying();
    }

    public boolean GetIsYourTurn() {
        return isYourTurn;
    }

    public boolean hasAdjacentTiles(boardToken t){
        //Tile is in the upper left corner.
        if (t.getCol() == 0 && t.getRow() == 0){
            if(t.getBoard()[t.getCol()+1][t.getRow()].getTile() != null && t.getBoard()[t.getCol()][t.getRow()+1].getTile() != null){
                return true;
            }
        }
        //Tile is in the lower left corner.
        if (t.getCol() == 0 && t.getRow() == 8){
            if(t.getBoard()[t.getCol()][t.getRow()-1].getTile() != null && t.getBoard()[t.getCol()+1][t.getRow()].getTile() != null){
                return true;
            }
        }
        //Tile is on the left side.
        if (t.getCol() == 0 && t.getRow() > 0 && t.getRow() < 8){
            if(t.getBoard()[t.getCol()][t.getRow()-1].getTile() != null && t.getBoard()[t.getCol()+1][t.getRow()].getTile() != null && t.getBoard()[t.getCol()][t.getRow()+1].getTile() != null){
                return true;
            }
        }
        //Tile is in the upper right corner.
        if (t.getCol() == 8 && t.getRow() == 0){
            if(t.getBoard()[t.getCol()][t.getRow()+1].getTile() != null && t.getBoard()[t.getCol()-1][t.getRow()].getTile() != null){
                return true;
            }
        }
        //Tile is in the lower right corner.
        if (t.getCol() == 8 && t.getRow() == 8){
            if(t.getBoard()[t.getCol()][t.getRow()-1].getTile() != null && t.getBoard()[t.getCol()-1][t.getRow()].getTile() != null){
                return true;
            }
        }
        //Tile is on the right side.
        if (t.getCol() == 8 && t.getRow() > 0 && t.getRow() < 8){
            if(t.getBoard()[t.getCol()][t.getRow()-1].getTile() != null && t.getBoard()[t.getCol()][t.getRow()+1].getTile() != null && t.getBoard()[t.getCol()-1][t.getRow()].getTile() != null){
                return true;
            }
        }
        //Tile is on the upper side.
        if (t.getCol() > 0 && t.getCol() < 8 && t.getRow() == 0){
            if(t.getBoard()[t.getCol()+1][t.getRow()].getTile() != null && t.getBoard()[t.getCol()][t.getRow()+1].getTile() != null && t.getBoard()[t.getCol()-1][t.getRow()].getTile() != null){
                return true;
            }
        }
        //Tile is on the lower side.
        if (t.getCol() > 0 && t.getCol() < 8 && t.getRow() == 8){
            if(t.getBoard()[t.getCol()][t.getRow()-1].getTile() != null && t.getBoard()[t.getCol()+1][t.getRow()].getTile() != null && t.getBoard()[t.getCol()-1][t.getRow()].getTile() != null){
                return true;
            }
        }
        //Tile is not in any particular position.
        if (t.getCol() > 0 && t.getCol() < 8 && t.getRow() > 0 && t.getRow() < 8){
            if(t.getBoard()[t.getCol()][t.getRow()-1].getTile() != null && t.getBoard()[t.getCol()+1][t.getRow()].getTile() != null && t.getBoard()[t.getCol()][t.getRow()+1].getTile() != null && t.getBoard()[t.getCol()-1][t.getRow()].getTile() != null){
                return true;
            }
        }
        return false;
    }

    public boolean isExternal (boardToken t){
        int freeSides = 0;
        //Tile is on the left side.
        if(t.getCol() == 0){
            if (t.getBoard()[t.getCol()][t.getRow()-1] == null)
                freeSides++;
            if (t.getBoard()[t.getCol()+1][t.getRow()] == null)
                freeSides++;
            if (t.getBoard()[t.getCol()][t.getRow()+1] == null)
                freeSides++;
        }
        //Tile is on the right side.
        if(t.getCol() == 8){
            if (t.getBoard()[t.getCol()][t.getRow()-1] == null)
                freeSides++;
            if (t.getBoard()[t.getCol()][t.getRow()+1] == null)
                freeSides++;
            if (t.getBoard()[t.getCol()-1][t.getRow()] == null)
                freeSides++;
        }
        //Tile is on the upper side.
        if(t.getRow() == 0){
            if (t.getBoard()[t.getCol()+1][t.getRow()] == null)
                freeSides++;
            if (t.getBoard()[t.getCol()][t.getRow()+1] == null)
                freeSides++;
            if (t.getBoard()[t.getCol()-1][t.getRow()] == null)
                freeSides++;
        }
        //Tile is on the lower side.
        if(t.getRow() == 8){
            if (t.getBoard()[t.getCol()][t.getRow()-1] == null)
                freeSides++;
            if (t.getBoard()[t.getCol()+1][t.getRow()] == null)
                freeSides++;
            if (t.getBoard()[t.getCol()-1][t.getRow()] == null)
                freeSides++;
        }
        //Tile is not in any particular position.
        if(t.getCol() > 0 && t.getCol() <8 && t.getRow() > 0 && t.getRow() <8){
            if (t.getBoard()[t.getCol()][t.getRow()-1] == null)
                freeSides++;
            if (t.getBoard()[t.getCol()+1][t.getRow()] == null)
                freeSides++;
            if (t.getBoard()[t.getCol()][t.getRow()+1] == null)
                freeSides++;
            if (t.getBoard()[t.getCol()-1][t.getRow()] == null)
                freeSides++;
        }
        if (freeSides >= 2)
            return true;
        return false;
    }

    public boolean isExternal (boardToken t1, boardToken t2){
        int freeSides = 0;
        //Couple has vertical orientation and is on the left side.
        if(t1.getCol() == 0 && t2.getCol() == 0){
            //Upside T1 && Upside T2.
            if (t1.getBoard()[t1.getCol()][t1.getRow()-1] == null && t2.getBoard()[t2.getCol()][t2.getRow()-1] == t1)
                freeSides++;
            //Right side T1 && Right side T2.
            if (t1.getBoard()[t1.getCol()+1][t1.getRow()] == null && t2.getBoard()[t2.getCol()+1][t2.getRow()] == null)
                freeSides++;
            //Downside T1 && Downside T2.
            if (t1.getBoard()[t1.getCol()][t1.getRow()+1] == t2 && t2.getBoard()[t2.getCol()][t2.getRow()+1] == null)
                freeSides++;
        }
        //Couple has vertical orientation and is on the right side.
        if(t1.getCol() == 8 && t2.getCol() == 8){
            //Upside T1 && Upside T2.
            if (t1.getBoard()[t1.getCol()][t1.getRow()-1] == null && t2.getBoard()[t2.getCol()][t2.getRow()-1] == t1)
                freeSides++;
            //Downside T1 && Downside T2.
            if (t1.getBoard()[t1.getCol()][t1.getRow()+1] == t2 && t2.getBoard()[t2.getCol()][t2.getRow()+1] == null)
                freeSides++;
            //Left side T1 && Left side T2.
            if (t1.getBoard()[t1.getCol()-1][t1.getRow()] == null && t2.getBoard()[t2.getCol()-1][t2.getRow()] == null)
                freeSides++;
        }
        //Couple has horizontal orientation and is on the upper side.
        if(t1.getRow() == 0 && t2.getRow() == 0){
            //Right side T1 && Right side T2.
            if (t1.getBoard()[t1.getCol()+1][t1.getRow()] == t2 && t2.getBoard()[t2.getCol()+1][t2.getRow()] == null)
                freeSides++;
            //Downside T1 && Downside T2.
            if (t1.getBoard()[t1.getCol()][t1.getRow()+1] == null && t2.getBoard()[t2.getCol()][t2.getRow()+1] == null)
                freeSides++;
            //Left side T1 && Left side T2.
            if (t1.getBoard()[t1.getCol()-1][t1.getRow()] == null && t2.getBoard()[t2.getCol()+1][t2.getRow()] == t1)
                freeSides++;
        }
        //Couple has horizontal orientation and is on the lower side.
        if(t1.getRow() == 8 && t2.getRow() == 8){
            //Upside T1 && Upside T2.
            if (t1.getBoard()[t1.getCol()][t1.getRow()-1] == null && t2.getBoard()[t2.getCol()][t2.getRow()-1] == null)
                freeSides++;
            //Right side T1 && Right side T2.
            if (t1.getBoard()[t1.getCol()+1][t1.getRow()] == t2 && t2.getBoard()[t2.getCol()+1][t2.getRow()] == null)
                freeSides++;
            //Left side T1 && Left side T2.
            if (t1.getBoard()[t1.getCol()-1][t1.getRow()] == null && t2.getBoard()[t2.getCol()-1][t2.getRow()] == t1)
                freeSides++;
        }
        //Couple has horizontal orientation and is not in any particular position.
        if(t1.getRow() == t2.getRow() && t1.getCol() > 0 && t1.getCol() <8 && t1.getRow() > 0 && t1.getRow() <8){
            //Upside T1 && Upside T2.
            if (t1.getBoard()[t1.getCol()][t1.getRow()-1] == null && t2.getBoard()[t2.getCol()][t2.getRow()-1] == null)
                freeSides++;
            //Right side T1 && Right side T2.
            if (t1.getBoard()[t1.getCol()+1][t1.getRow()] == t2 && t2.getBoard()[t2.getCol()+1][t2.getRow()] == null)
                freeSides++;
            //Downside T1 && Downside T2.
            if (t1.getBoard()[t1.getCol()][t1.getRow()+1] == null && t2.getBoard()[t2.getCol()][t2.getRow()+1] == null)
                freeSides++;
            //Left side T1 && Left side T2.
            if (t1.getBoard()[t1.getCol()-1][t1.getRow()] == null && t2.getBoard()[t2.getCol()-1][t2.getRow()] == t1)
                freeSides++;
        }
        //Couple has vertical orientation and is not in any particular position.
        if(t1.getCol() == t2.getCol() && t1.getCol() > 0 && t1.getCol() <8 && t1.getRow() > 0 && t1.getRow() <8){
            //Upside T1 && Upside T2.
            if (t1.getBoard()[t1.getCol()][t1.getRow()-1] == null && t2.getBoard()[t2.getCol()][t2.getRow()-1] == t1)
                freeSides++;
            //Right side T1 && Right side T2.
            if (t1.getBoard()[t1.getCol()+1][t1.getRow()] == null && t2.getBoard()[t2.getCol()+1][t2.getRow()] == null)
                freeSides++;
            //Downside T1 && Downside T2.
            if (t1.getBoard()[t1.getCol()][t1.getRow()+1] == t2 && t2.getBoard()[t2.getCol()][t2.getRow()+1] == null)
                freeSides++;
            //Left side T1 && Left side T2.
            if (t1.getBoard()[t1.getCol()-1][t1.getRow()] == null && t2.getBoard()[t2.getCol()-1][t2.getRow()] == null)
                freeSides++;
        }
        if (freeSides >= 2)
            return true;
        return false;
    }

    public boolean isExternal (boardToken t1, boardToken t2, boardToken t3){
        int freeSides = 0;
        //Couple has vertical orientation and is on the left side.
        if(t1.getCol() == 0 && t2.getCol() == 0){
            //Upside T1 && Upside T2 && Upside T3.
            if (t1.getBoard()[t1.getCol()][t1.getRow()-1] == null && t2.getBoard()[t2.getCol()][t2.getRow()-1] == t1 && t3.getBoard()[t3.getCol()][t3.getRow()-1] == t2)
                freeSides++;
            //Right side T1 && Right side T2 && Right side T3.
            if (t1.getBoard()[t1.getCol()+1][t1.getRow()] == null && t2.getBoard()[t2.getCol()+1][t2.getRow()] == null && t3.getBoard()[t3.getCol()+1][t3.getRow()] == null)
                freeSides++;
            //Downside T1 && Downside T2 && Downside T3.
            if (t1.getBoard()[t1.getCol()][t1.getRow()+1] == t2 && t2.getBoard()[t2.getCol()][t2.getRow()+1] == t3 && t3.getBoard()[t3.getCol()][t3.getRow()+1] == null)
                freeSides++;
        }
        //Couple has vertical orientation and is on the right side.
        if(t1.getCol() == 8 && t2.getCol() == 8){
            //Upside T1 && Upside T2 && Upside T3.
            if (t1.getBoard()[t1.getCol()][t1.getRow()-1] == null && t2.getBoard()[t2.getCol()][t2.getRow()-1] == t1 && t3.getBoard()[t3.getCol()][t3.getRow()-1] == t2)
                freeSides++;
            //Downside T1 && Downside T2 && Downside T3.
            if (t1.getBoard()[t1.getCol()][t1.getRow()+1] == t2 && t2.getBoard()[t2.getCol()][t2.getRow()+1] == t3 && t3.getBoard()[t3.getCol()][t3.getRow()+1] == null)
                freeSides++;
            //Left side T1 && Left side T2 && Left side T3.
            if (t1.getBoard()[t1.getCol()+1][t1.getRow()] == null && t2.getBoard()[t2.getCol()+1][t2.getRow()] == null && t3.getBoard()[t3.getCol()+1][t3.getRow()] == null)
                freeSides++;
        }
        //Couple has horizontal orientation and is on the upper side.
        if(t1.getRow() == 0 && t2.getRow() == 0){
            //Right side T1 && Right side T2 && Right side T3.
            if (t1.getBoard()[t1.getCol()+1][t1.getRow()] == t2 && t2.getBoard()[t2.getCol()+1][t2.getRow()] == t3 && t3.getBoard()[t3.getCol()+1][t3.getRow()] == null)
                freeSides++;
            //Downside T1 && Downside T2 && Downside T3.
            if (t1.getBoard()[t1.getCol()][t1.getRow()+1] == null && t2.getBoard()[t2.getCol()][t2.getRow()+1] == null && t3.getBoard()[t3.getCol()][t3.getRow()+1] == null)
                freeSides++;
            //Left side T1 && Left side T2 && Left side T3.
            if (t1.getBoard()[t1.getCol()-1][t1.getRow()] == null && t2.getBoard()[t2.getCol()+1][t2.getRow()] == t1 && t3.getBoard()[t3.getCol()+1][t3.getRow()] == t2)
                freeSides++;
        }
        //Couple has horizontal orientation and is on the lower side.
        if(t1.getRow() == 8 && t2.getRow() == 8){
            //Upside T1 && Upside T2 && Upside T3.
            if (t1.getBoard()[t1.getCol()][t1.getRow()+1] == null && t2.getBoard()[t2.getCol()][t2.getRow()+1] == null && t3.getBoard()[t3.getCol()][t3.getRow()+1] == null)
                freeSides++;
            //Right side T1 && Right side T2 && Right side T3.
            if (t1.getBoard()[t1.getCol()+1][t1.getRow()] == t2 && t2.getBoard()[t2.getCol()+1][t2.getRow()] == t3 && t3.getBoard()[t3.getCol()+1][t3.getRow()] == null)
                freeSides++;
            //Left side T1 && Left side T2 && Left side T3.
            if (t1.getBoard()[t1.getCol()-1][t1.getRow()] == null && t2.getBoard()[t2.getCol()+1][t2.getRow()] == t1 && t3.getBoard()[t3.getCol()+1][t3.getRow()] == t2)
                freeSides++;
        }
        //Couple has horizontal orientation and is not in any particular position.
        if(t1.getRow() == t2.getRow() && t1.getCol() > 0 && t1.getCol() <8 && t1.getRow() > 0 && t1.getRow() <8){
            //Upside T1 && Upside T2 && Upside T3.
            if (t1.getBoard()[t1.getCol()][t1.getRow()+1] == null && t2.getBoard()[t2.getCol()][t2.getRow()+1] == null && t3.getBoard()[t3.getCol()][t3.getRow()+1] == null)
                freeSides++;
            //Right side T1 && Right side T2 && Right side T3.
            if (t1.getBoard()[t1.getCol()+1][t1.getRow()] == t2 && t2.getBoard()[t2.getCol()+1][t2.getRow()] == t3 && t3.getBoard()[t3.getCol()+1][t3.getRow()] == null)
                freeSides++;
            //Downside T1 && Downside T2 && Downside T3.
            if (t1.getBoard()[t1.getCol()][t1.getRow()+1] == null && t2.getBoard()[t2.getCol()][t2.getRow()+1] == null && t3.getBoard()[t3.getCol()][t3.getRow()+1] == null)
                freeSides++;
            //Left side T1 && Left side T2 && Left side T3.
            if (t1.getBoard()[t1.getCol()-1][t1.getRow()] == null && t2.getBoard()[t2.getCol()+1][t2.getRow()] == t1 && t3.getBoard()[t3.getCol()+1][t3.getRow()] == t2)
                freeSides++;
        }
        //Couple has vertical orientation and is not in any particular position.
        if(t1.getCol() == t2.getCol() && t1.getCol() > 0 && t1.getCol() <8 && t1.getRow() > 0 && t1.getRow() <8){
            //Upside T1 && Upside T2 && Upside T3.
            if (t1.getBoard()[t1.getCol()][t1.getRow()-1] == null && t2.getBoard()[t2.getCol()][t2.getRow()-1] == t1 && t3.getBoard()[t3.getCol()][t3.getRow()-1] == t2)
                freeSides++;
            //Right side T1 && Right side T2 && Right side T3.
            if (t1.getBoard()[t1.getCol()+1][t1.getRow()] == null && t2.getBoard()[t2.getCol()+1][t2.getRow()] == null && t3.getBoard()[t3.getCol()+1][t3.getRow()] == null)
                freeSides++;
            //Downside T1 && Downside T2 && Downside T3.
            if (t1.getBoard()[t1.getCol()+1][t1.getRow()] == null && t2.getBoard()[t2.getCol()+1][t2.getRow()] == null && t3.getBoard()[t3.getCol()+1][t3.getRow()] == null)
                freeSides++;
            //Left side T1 && Left side T2 && Left side T3.
            if (t1.getBoard()[t1.getCol()+1][t1.getRow()] == null && t2.getBoard()[t2.getCol()+1][t2.getRow()] == null && t3.getBoard()[t3.getCol()+1][t3.getRow()] == null)
                freeSides++;
        }
        if (freeSides >= 2)
            return true;
        return false;
    }

    public boolean boardBoxIsValid(boardToken t){
        boardToken.boardTokenCategory category = t.getCategory();
        if(category == boardToken.boardTokenCategory.NORMAL || (category == boardToken.boardTokenCategory.THREE && numPlayers >= 3) || (category == boardToken.boardTokenCategory.FOUR && numPlayers == 4))
            return true;
        return false;
    }
}
