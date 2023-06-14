package it.polimi.ingsw.model.board.goalCards;

import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.player.BookShelf;

/**
 * @author Eliahu Cohen and Giovanni di Lorenzo
 */

public class CommonGoalCard7 extends CommonGoalCard implements CheckCommonGoalCard {


    public CommonGoalCard7(Integer I){
        super(I);
    }

    @Override
    public void checkGoal(BookShelf bs) {
       if (checkRtoL(bs)|| checkLtoR(bs)) increaseNumCompleted();
    }
    /**
     * @param bs Player's bookshelf
     * The method below checks if there is, at least, one diagonal of same category itemtiles from left to right
     */
    private boolean checkLtoR(BookShelf bs) {
        //diagonale da (0,0) o (1,0)
        boolean start1 = false, start2 = false;
        if (bs.getTile(0, 0) != null)
            start1 = true;

        if (bs.getTile(1, 0) != null)
            start2 = true;

        if ((start1) || (start2)) {
            ItemTile T = bs.getTile(0, 0);
            ItemTile K = bs.getTile(1, 0);
            int Counter1 = 0;
            int Counter2 = 0;
            for (int i = 1; i < 5; i++) {
                if ((bs.getTile(i, i) != null) && (start1))
                    if (bs.getTile(i, i).getCategory() == T.getCategory()) Counter1++;
                if ((bs.getTile(i + 1, i) != null) && (start2))
                    if (bs.getTile(i + 1, i).getCategory() == K.getCategory()) Counter2++;
            }
            return Counter1 == 4 || Counter2 == 4;
        } else return false;
    }
    /**
     * @param bs Player's bookshelf
     * The method below checks if there is, at least, one diagonal of same category itemtiles from right to left
     */
    private boolean checkRtoL(BookShelf bs) {
        //diagonale da (0,4) o (1,4)
        boolean start3 = false, start4 = false;
        if (bs.getTile(0, 4) != null)
            start3 = true;

        if (bs.getTile(1, 4) != null)
            start4 = true;

        if ((start3) || (start4)) {
            ItemTile T = bs.getTile(0, 4);
            ItemTile K = bs.getTile(1, 4);
            int Counter1 = 0;
            int Counter2 = 0;
            for (int i = 1; i < 5; i++) {
                if((bs.getTile(i,4-i)!=null) &&(start3))
                    if (bs.getTile(i, 4 - i).getCategory() == T.getCategory()) Counter1++;
                if((bs.getTile(i+1,4-i)!=null) &&(start4))
                    if (bs.getTile(i + 1, 4 - i).getCategory() == K.getCategory()) Counter2++;
            }
            return Counter1 == 4 || Counter2 == 4;
        } else return false;
    }
    @Override
    public void print(){
        System.out.print("DIAGONAL TILES.\n\n");

        //                                     [0]             [1]             [2]             [3]            [4]
        System.out.print(WOOD + "                     " + RESET +
                "\n  " + WOOD + " " + RESET + "   " + RESET + "   " + RESET + "   " + RESET + "   " + RESET + "   " + WOOD + " " + RESET +
                "\n  " + WOOD + " " + GREEN + "   " + RESET + "   " + RESET + "   " + RESET + "   " + RESET + "   " + WOOD + " " + RESET +
                "\n  " + WOOD + " " + RESET + "   " + GREEN + "   " + RESET + "   " + RESET + "   " + RESET + "   " + WOOD + " " + RESET +
                "\n  " + WOOD + " " + RESET + "   " + RESET + "   " + GREEN + "   " + RESET + "   " + RESET + "   " + WOOD + " " + RESET +
                "\n  " + WOOD + " " + RESET + "   " + RESET + "   " + RESET + "   " + GREEN + "   " + RESET + "   " + WOOD + " " + RESET +
                "\n  " + WOOD + " " + RESET + "   " + RESET + "   " + RESET + "   " + RESET + "   " + GREEN + "   " + WOOD + " " + RESET +
                "\n" + WOOD + "                     " + RESET);

        System.out.print("\nDESCRIPTION: Five tiles of the same type forming a diagonal.\n");
        System.out.print("POINTS:" + points + "\n\n");
    }
}