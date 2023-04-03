package server.model.board;

import server.model.board.ItemTile;
import server.model.player.BookShelf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CommonGoalCard8 extends CommonGoalCard implements checkCommonGoalCard{
    private List<ItemTile> validGroups;
    private static int numRowsToAchieve=4;
    private static int numDifferentCategoriesAllowed=3;
    private HashSet<itemTileCategory> cat = new HashSet<>(); //I use an hashset to track the categories in each row
    @Override                                                   //of the bookshelf
    public void checkGoal(BookShelf bs) {
        int counter=0;
        for(int i=0; i<bs.getRows() && counter<numRowsToAchieve; i++){
            cat.add(bs.getTile(i,0).getCategory());
            for(int j=0; j<bs.getColumns() && cat.size()<=numDifferentCategoriesAllowed; j++){
                cat.add(bs.getTile(i,j).getCategory());
            }
            if (cat.size()<=numDifferentCategoriesAllowed)
                counter++;
            cat.clear();
        }
        if (counter==numRowsToAchieve)
            increaseNumCompleted();
    }
}

