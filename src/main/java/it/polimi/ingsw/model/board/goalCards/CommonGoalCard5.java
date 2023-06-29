package it.polimi.ingsw.model.board.goalCards;

import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.board.ItemTileCategory;
import it.polimi.ingsw.model.player.BookShelf;

import java.util.HashSet;

/**
 * @author Giovanni Di Lorenzo
 */
public class CommonGoalCard5 extends CommonGoalCard implements CheckCommonGoalCard {
    private final static int numColumnsToAchieve = 3;
    private final static int numDifferentCategoriesAllowed = 3;
    private final HashSet<ItemTileCategory> cat = new HashSet<>();

    public CommonGoalCard5(Integer I){
        super(I);

    }
    /**
     * The method below checks each bookshelf's column, until three columns comply with the specifics
     * @author Giovaanni Di Lorenzo
     *@param bs Player's bookshelf
     *
     */
    @Override
    public void checkGoal(BookShelf bs) {
        int counter = 0;
        for (int j = 0; j < BookShelf.getMAX_Column() && counter < numColumnsToAchieve; j++) {
            if (bs.getTile(0, j) != null) {
                cat.add(bs.getTile(0, j).getCategory());
                if (CategoriesAllowed(bs, j, cat) <= numDifferentCategoriesAllowed)
                    counter++;
                cat.clear();
            }
        }
        if (counter >= numColumnsToAchieve)
            increaseNumCompleted();
    }

    public HashSet<ItemTileCategory> getCat() {
        return cat;
    }

    /**
     * The method below count the categories of the column with index a
     * @author Giovaanni Di Lorenzo
     *@param bs,a,cat Player's bookshelf, column index and HashSet in order to count the number of index a
     *                column categories
     *
     */
    public int CategoriesAllowed(BookShelf bs, int a, HashSet<ItemTileCategory> cat) {
        for (int i = 0; i < BookShelf.getMAX_Row() && cat.size() <= numDifferentCategoriesAllowed; i++) {
            if(bs.getTile(i,a)==null) {
                return 7;
            }else cat.add(bs.getTile(i, a).getCategory());
        }
        return cat.size();
    }
    @Override
    public void print(){
        System.out.print("THREE FULL COLUMNS.\n\n");

        //                                     [0]             [1]             [2]             [3]            [4]
        System.out.println("                    " + WOOD + "                     " + RESET +
                "\n                      " + WOOD + " " + YELLOW + "   " + RESET + "   " + PINK + "   " + RESET + "   " + CYAN + "   " + WOOD + " " + RESET +
                "\n                      " + WOOD + " " + YELLOW + "   " + RESET + "   " + PINK + "   " + RESET + "   " + CYAN + "   " + WOOD + " " + RESET +
                "\n                      " + WOOD + " " + WHITE + "   " + RESET + "   " + PINK + "   " + RESET + "   " + WHITE + "   " + WOOD + " " + RESET +
                "\n                      " + WOOD + " " + WHITE + "   " + RESET + "   " + BLUE + "   " + RESET + "   " + WHITE + "   " + WOOD + " " + RESET +
                "\n                      " + WOOD + " " + PINK + "   " + RESET + "   " + BLUE + "   " + RESET + "   " + PINK + "   " + WOOD + " " + RESET +
                "\n                      " + WOOD + " " + PINK + "   " + RESET + "   " + BLUE + "   " + RESET + "   " + PINK + "   " + WOOD + " " + RESET +
                "\n                    " + WOOD + "                     " + RESET);

        System.out.print("""
                
                > DESCRIPTION: Three columns each formed by 6 tiles of maximum
                three different types. One column can show the same or a different
                combination of another column.
                """);
        System.out.print("> POINTS: " + points + "\n\n");
    }
}
