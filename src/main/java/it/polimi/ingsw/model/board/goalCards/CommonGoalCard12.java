package it.polimi.ingsw.model.board.goalCards;

import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.player.BookShelf;

/**
 *  @author Eliahu Cohen
 */

public class CommonGoalCard12 extends CommonGoalCard implements CheckCommonGoalCard {


    public CommonGoalCard12(Integer I){
        super(I);
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
    @Override
    public void print(){
        System.out.print("STAIRCASE OF TILES.\n\n");

        //                                     [0]             [1]             [2]             [3]            [4]
        System.out.println("                    " + WOOD + "                     " + RESET +
                "\n                      " + WOOD + " " + BLUE + "   " + RESET + "   " + RESET + "   " + RESET + "   " + RESET + "   " + WOOD + " " + RESET +
                "\n                      " + WOOD + " " + CYAN + "   " + BLUE + "   " + RESET + "   " + RESET + "   " + RESET + "   " + WOOD + " " + RESET +
                "\n                      " + WOOD + " " + WHITE + "   " + CYAN + "   " + BLUE + "   " + RESET + "   " + RESET + "   " + WOOD + " " + RESET +
                "\n                      " + WOOD + " " + CYAN + "   " + WHITE + "   " + CYAN + "   " + BLUE + "   " + RESET + "   " + WOOD + " " + RESET +
                "\n                      " + WOOD + " " + BLUE + "   " + CYAN + "   " + WHITE + "   " + CYAN + "   " + BLUE + "   " + WOOD + " " + RESET +
                "\n                      " + WOOD + " " + CYAN + "   " + BLUE + "   " + CYAN + "   " + WHITE + "   " + CYAN + "   " + WOOD + " " + RESET +
                "\n                    " + WOOD + "                     " + RESET);

        System.out.print("""
                
                > DESCRIPTION: Five columns of increasing or decreasing height.
                Starting from the first column on the left or on the right,
                each next column must be made of exactly one more tile.
                Tiles can be of any type.
                """);
        System.out.print("> POINTS: " + points + "\n\n");
    }
}