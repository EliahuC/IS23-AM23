package it.polimi.ingsw.model.board.goalCards;

import it.polimi.ingsw.model.player.BookShelf;
import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.board.ItemTileCategory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CommonGoalCard8 extends CommonGoalCard implements CheckCommonGoalCard {
    private final static int numRowsToAchieve=4;
    private static final int numDifferentCategoriesAllowed=3;
    private final HashSet<ItemTileCategory> cat = new HashSet<>(); //I use a hashset to track the categories in each row
    private final Launcher L;                                               //of the bookshelf;

    public CommonGoalCard8(Launcher L) {
        this.L = L;
    }

    @Override

    public void checkGoal(BookShelf bs) {
        int counter=0;
        for(int i=0; i< BookShelf.getMAX_Row() && counter<numRowsToAchieve; i++){
            cat.add(bs.getTile(i,0).getCategory());
            if (CategoriesAllowed(bs,i,cat)<=numDifferentCategoriesAllowed)
                counter++;
            cat.clear();
        }
        if (counter==numRowsToAchieve)
            increaseNumCompleted();
    }


    public int CategoriesAllowed(BookShelf bs, int a, HashSet<ItemTileCategory> cat){
        for(int j=0; j< BookShelf.getMAX_Column() && cat.size()<=numDifferentCategoriesAllowed; j++){
            cat.add(bs.getTile(a,j).getCategory());
        }
        return cat.size();
    }

    public HashSet<ItemTileCategory> getCat(){
        return cat;
    }
}


