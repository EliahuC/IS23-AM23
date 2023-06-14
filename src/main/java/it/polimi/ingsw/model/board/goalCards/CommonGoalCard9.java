package it.polimi.ingsw.model.board.goalCards;

import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.board.ItemTileCategory;
import it.polimi.ingsw.model.player.BookShelf;

import java.util.HashSet;

/**
 * @author Giovanni Di Lorenzo
 */
public class CommonGoalCard9 extends CommonGoalCard implements CheckCommonGoalCard {
    private final static int numColumnsToAchieve=2;
    private final HashSet<ItemTileCategory> cat = new HashSet<>(); //I use an arrayList to track the categories in each column
    //of the bookshelf




    public CommonGoalCard9(Integer I){
        super(I);
    }
    /**
     *@param bs Player's bookshelf
     * The method below checks each bookshelf's column, until two columns comply with the specifics
     */
    @Override
    public void checkGoal(BookShelf bs) {
        int counter=0;
        for(int j=0; j< BookShelf.getMAX_Column() && counter<numColumnsToAchieve; j++) {
            if (bs.getTile(0, j) != null) {
                cat.add(bs.getTile(0, j).getCategory());
                if (!SameCategory(bs, j, cat)) {
                    counter++;
                }
                cat.clear();
            }
        }
        if (counter>=numColumnsToAchieve)
            increaseNumCompleted();
    }
    /**
     *@param bs,a,cat Player's bookshelf, column index and HashSet in order to count the number of index a
     *                column categories
     * The method below checks if in the column with index a there are not two itemtiles with the same category
     */
    public boolean SameCategory(BookShelf bs, int a, HashSet<ItemTileCategory> category) {
        for (int i = 1; i < BookShelf.getMAX_Row(); i++) {
            if (bs.getTile(i, a) == null)
                return true;
            else {
                if (cat.contains(bs.getTile(i, a).getCategory()))
                    return true;
                else
                    cat.add(bs.getTile(i, a).getCategory());
            }
        }
        return false;
    }

    public HashSet<ItemTileCategory> getCat(){
        return cat;
    }
    @Override
    public void print(){
        System.out.print("TWO COLUMNS WITH DIFFERENT TILES.\n\n");

        //                                     [0]             [1]             [2]             [3]            [4]
        System.out.print(WOOD + "                     " + RESET +
                "\n  " + WOOD + " " + RESET + "   " + PINK + "   " + RESET + "   " + BLUE + "   " + RESET + "   " + WOOD + " " + RESET +
                "\n  " + WOOD + " " + RESET + "   " + YELLOW + "   " + RESET + "   " + CYAN + "   " + RESET + "   " + WOOD + " " + RESET +
                "\n  " + WOOD + " " + RESET + "   " + WHITE + "   " + RESET + "   " + GREEN + "   " + RESET + "   " + WOOD + " " + RESET +
                "\n  " + WOOD + " " + RESET + "   " + GREEN + "   " + RESET + "   " + WHITE + "   " + RESET + "   " + WOOD + " " + RESET +
                "\n  " + WOOD + " " + RESET + "   " + CYAN + "   " + RESET + "   " + YELLOW + "   " + RESET + "   " + WOOD + " " + RESET +
                "\n  " + WOOD + " " + RESET + "   " + BLUE + "   " + RESET + "   " + PINK + "   " + RESET + "   " + WOOD + " " + RESET +
                "\n" + WOOD + "                     " + RESET);

        System.out.print("\nDESCRIPTION: Two columns each formed by 6 different types of tiles.\n");
        System.out.print("POINTS:" + points + "\n\n");
    }
}

