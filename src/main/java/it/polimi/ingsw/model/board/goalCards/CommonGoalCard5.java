package it.polimi.ingsw.model.board.goalCards;

import it.polimi.ingsw.model.player.BookShelf;
import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.board.ItemTileCategory;

import java.util.HashSet;
import java.util.List;

public class CommonGoalCard5 extends CommonGoalCard implements CheckCommonGoalCard {
    private List<ItemTile> validGroups;
    private static int numColumnsToAchieve = 3;
    private static int numDifferentCategoriesAllowed = 3;
    private HashSet<ItemTileCategory> cat = new HashSet<>(); //I use an hashset to track the categories in each column
    private final Launcher L;

    public CommonGoalCard5(Launcher L){
        this.L = L;
    }
    @Override                                                   //of the bookshelf
    public void checkGoal(BookShelf bs) {
        int counter = 0;
        for (int j = 0; j < BookShelf.getMAX_Column() && counter < numColumnsToAchieve; j++) {
            cat.add(bs.getTile(0, j).getCategory());
            if (CategoriesAllowed(bs,j,cat)<= numDifferentCategoriesAllowed)
                counter++;
            cat.clear();
            }

        if (counter == numColumnsToAchieve)
            increaseNumCompleted();
    }

    public HashSet<ItemTileCategory> getCat() {
        return cat;
    }

    public int CategoriesAllowed(BookShelf bs, int a, HashSet<ItemTileCategory> cat) {
        for (int i = 0; i < BookShelf.getMAX_Row() && cat.size() <= numDifferentCategoriesAllowed; i++) {
            cat.add(bs.getTile(i, a).getCategory());
        }
        return cat.size();
    }
}
