package it.polimi.ingsw.model.board.goalCards;

import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.model.board.ItemTileCategory;
import it.polimi.ingsw.model.player.BookShelf;

import java.util.HashSet;

public class CommonGoalCard10 extends CommonGoalCard implements CheckCommonGoalCard {

    private final static int numRowsToAchieve=2;
    private final HashSet<ItemTileCategory> cat = new HashSet<>(); //I use an arrayList to track the categories in each row
    //of the bookshelf
    private final Launcher L;

    public CommonGoalCard10(Launcher L) {
        this.L = L;
    }
    @Override
    public void checkGoal(BookShelf bs) {
        int counter=0;
        for(int i=0; i< BookShelf.getMAX_Row() && counter<numRowsToAchieve; i++) {
            if (bs.getTile(i, 0) != null) {
                cat.add(bs.getTile(i, 0).getCategory());
                if (!SameCategory(bs, i, cat))
                    counter++;
                cat.clear();
            }
        }
        if (counter>=numRowsToAchieve)
            increaseNumCompleted();
    }

    public boolean SameCategory(BookShelf bs,int a, HashSet<ItemTileCategory> category){
        for(int j=1;j< BookShelf.getMAX_Column();j++) {
            if (bs.getTile(a, j) == null)
                return true;
            else {
                if (category.contains(bs.getTile(a, j).getCategory()))
                    return true;
                else
                    cat.add(bs.getTile(a, j).getCategory());
            }
        }
        return false;
    }

    public HashSet<ItemTileCategory> getCat(){
        return cat;
    }
}

