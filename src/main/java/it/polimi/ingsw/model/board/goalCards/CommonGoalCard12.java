package it.polimi.ingsw.model.board.goalCards;

import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.model.player.BookShelf;

/**
 *  @author Eliahu Cohen
 */

public class CommonGoalCard12 extends CommonGoalCard implements CheckCommonGoalCard {
    private final Launcher L;

    public CommonGoalCard12(Launcher L) {
        this.L = L;
    }

    @Override
    public void checkGoal(BookShelf bs) {
        if(checkRtoL(bs)||checkLtoR(bs)) increaseNumCompleted();
    }
    /**
     * @author Eliahu Cohen
     * @param bs player bookshelf
     * @return if he goal is made form right to left
     */
    private boolean checkRtoL(BookShelf bs) {
        int lastColumn = countColumnRtoL(bs, BookShelf.getMAX_Column() - 1);
        if (lastColumn >= 5) {
            for (int i = 3; i >=0; i--) {
                if (countColumnRtoL(bs, i) != lastColumn - 1) return false;
                else lastColumn = countColumnRtoL(bs, i);
            }
            return true;
        } else return false;
    }

    /**
     * @author Eliahu Cohen
     * @param bs player bookshelf
     * @return if he goal is made form left to right
     */
    private boolean checkLtoR(BookShelf bs) {
        int lastColumn = countColumnLtoR(bs, 0);
        if (lastColumn >= 5) {
            for (int i = 1; i <= BookShelf.getMAX_Column()-1; i++) {
                if (countColumnLtoR(bs, i) != lastColumn - 1) return false;
                else lastColumn = countColumnLtoR(bs, i);

            }
            return true;
        }else return false;
    }
/**
 * @author Eliahu Cohen
 * @param bs player bookshelf
 * @param i column to count
 */
    private int countColumnRtoL(BookShelf bs,int i){
        int counter =0;
        for (int j=BookShelf.getMAX_Row()-1;j>=0&&bs.getTile(j,i)!=null;j--){
            counter++;
        }
        return counter;
    }
    /**
     * @author Eliahu Cohen
     * @param bs player bookshelf
     * @param i column to count
     */
    private int countColumnLtoR(BookShelf bs, int i){
        int counter =0;
        for (int j=BookShelf.getMAX_Row()-1;j>=0&&bs.getTile(j,i)!=null;j--){
            counter++;
        }
        return counter;
    }

    public void print(){
        System.out.print("STAIRCASE OF TILES. Insert 5 columns of increasing or decreasing height." +
                "Starting from the first column on the left or on the right, each next column\n" +
                "must be made of exactly one more tile. Tiles can be of any type. --> You can still get" + getPoints() + "POINTS\n");
    }
}