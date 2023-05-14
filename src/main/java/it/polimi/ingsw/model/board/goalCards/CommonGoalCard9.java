package it.polimi.ingsw.model.board.goalCards;

import it.polimi.ingsw.Launcher;
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
    private final Launcher L;

    public CommonGoalCard9(Launcher L) {
        this.L = L;
    }
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
}
