package it.polimi.ingsw.model.board.goalCards;

import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.model.board.ItemTileCategory;
import it.polimi.ingsw.model.player.BookShelf;

import java.util.HashSet;

/**
 * @author Giovanni Di Lorenzo
 */
public class CommonGoalCard5 extends CommonGoalCard implements CheckCommonGoalCard {
    private final static int numColumnsToAchieve = 3;
    private final static int numDifferentCategoriesAllowed = 3;
    private final HashSet<ItemTileCategory> cat = new HashSet<>(); //I use an hashset to track the categories in each column
    private final Launcher L;                                       //of the bookshelf

    public CommonGoalCard5(Launcher L){
        this.L = L;

    }
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

    public int CategoriesAllowed(BookShelf bs, int a, HashSet<ItemTileCategory> cat) {
        for (int i = 0; i < BookShelf.getMAX_Row() && cat.size() <= numDifferentCategoriesAllowed; i++) {
            if(bs.getTile(i,a)==null) {
                return 7;
            }else cat.add(bs.getTile(i, a).getCategory());
        }
        return cat.size();
    }

    public void print(){
        System.out.print("THREE FULL COLUMNS. Make 3 columns each formed by 6 tiles of maximum three different types." +
                "One column can show the same or a different combination of another column. --> You can still get" + getPoints() + "POINTS\n");
    }
}
