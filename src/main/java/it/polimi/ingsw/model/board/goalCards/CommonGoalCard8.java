package it.polimi.ingsw.model.board.goalCards;

import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.board.ItemTileCategory;
import it.polimi.ingsw.model.player.BookShelf;

import java.util.HashSet;

/**
 * @author Giovanni Di Lorenzo
 */
public class CommonGoalCard8 extends CommonGoalCard implements CheckCommonGoalCard {
    private final static int numRowsToAchieve=4;
    private static final int numDifferentCategoriesAllowed=3;
    private final HashSet<ItemTileCategory> cat = new HashSet<>();



    public CommonGoalCard8(Integer I){
        super(I);
    }

    /**
     * The method below checks each bookshelf's row, until four rows comply with the specifics
     *@param bs Player's bookshelf
     *
     */

    @Override
    public void checkGoal(BookShelf bs) {
        int counter=0;
        for(int i=0; i< BookShelf.getMAX_Row() && counter<numRowsToAchieve; i++) {
            if (bs.getTile(i,0) != null) {
                cat.add(bs.getTile(i, 0).getCategory());
                if (CategoriesAllowed(bs, i, cat) <= numDifferentCategoriesAllowed)
                    counter++;
                cat.clear();
            }
        }
        if (counter>=numRowsToAchieve)
            increaseNumCompleted();
    }
    /**
     * The method below count the categories of the row with index a
     * @param bs,a,cat Player's bookshelf, row index and HashSet in order to count the number of index a
     *                row categories
     *
     */

    public int CategoriesAllowed(BookShelf bs, int a, HashSet<ItemTileCategory> cat){
        for(int j=0; j< BookShelf.getMAX_Column() && cat.size()<=numDifferentCategoriesAllowed; j++){
            if(bs.getTile(a,j)==null) {
                return 7;
            }else cat.add(bs.getTile(a,j).getCategory());
        }
        return cat.size();
    }

    public HashSet<ItemTileCategory> getCat(){
        return cat;
    }
    @Override
    public void print(){
        System.out.print("FOUR FULL ROWS.\n\n");

        //                                     [0]             [1]             [2]             [3]            [4]
        System.out.println("                    " + WOOD + "                     " + RESET +
                "\n                      " + WOOD + " " + RESET + "   " + RESET + "   " + RESET + "   " + RESET + "   " + RESET + "   " + WOOD + " " + RESET +
                "\n                      " + WOOD + " " + RESET + "   " + RESET + "   " + RESET + "   " + RESET + "   " + RESET + "   " + WOOD + " " + RESET +
                "\n                      " + WOOD + " " + GREEN + "   " + GREEN + "   " + WHITE + "   " + WHITE + "   " + CYAN + "   " + WOOD + " " + RESET +
                "\n                      " + WOOD + " " + YELLOW + "   " + WHITE + "   " + PINK + "   " + PINK + "   " + PINK + "   " + WOOD + " " + RESET +
                "\n                      " + WOOD + " " + CYAN + "   " + CYAN + "   " + WHITE + "   " + BLUE + "   " + BLUE + "   " + WOOD + " " + RESET +
                "\n                      " + WOOD + " " + PINK + "   " + PINK + "   " + YELLOW + "   " + CYAN + "   " + CYAN + "   " + WOOD + " " + RESET +
                "\n                    " + WOOD + "                     " + RESET);

        System.out.print("""
                
                > DESCRIPTION: Four lines each formed by 5 tiles of maximum three different
                types. One line can show the same or a different combination of another line.
                """);
        System.out.print("> POINTS: " + points + "\n\n");
    }
}


