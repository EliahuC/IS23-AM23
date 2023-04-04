package server.model.board;

import server.model.player.BookShelf;

import java.util.HashSet;
import java.util.List;

public class CommonGoalCard5 extends CommonGoalCard implements checkCommonGoalCard{
    private List<ItemTile> validGroups;
    private static int numColumnsToAchieve=3;
    private static int numDifferentCategoriesAllowed=3;
    private HashSet<ItemTileCategory> cat = new HashSet<>(); //I use an hashset to track the categories in each column
    @Override                                                   //of the bookshelf
    public void checkGoal(BookShelf bs) {
    int counter=0;
        for(int j=0; j<bs.getColumns() && counter<numColumnsToAchieve; j++){
            cat.add(bs.getTile(0,j).getCategory());
            for(int i=0; i<bs.getRows() && cat.size()<=numDifferentCategoriesAllowed; i++){
                cat.add(bs.getTile(i,j).getCategory());
            }
            if (cat.size()<=numDifferentCategoriesAllowed)
                counter++;
            cat.clear();
        }
        if (counter==numColumnsToAchieve)
            increaseNumCompleted();
    }
}
