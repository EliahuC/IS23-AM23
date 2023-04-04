package server.model.board.goalCards;

import server.model.player.BookShelf;
import server.model.board.ItemTile;
import server.model.board.ItemTileCategory;
import java.util.HashSet;
import java.util.List;

public class CommonGoalCard8 extends CommonGoalCard implements CheckCommonGoalCard{
    private List<ItemTile> validGroups;
    private static int numRowsToAchieve=4;
    private static int numDifferentCategoriesAllowed=3;
    private HashSet<ItemTileCategory> cat = new HashSet<>(); //I use an hashset to track the categories in each row
    @Override                                                   //of the bookshelf
    public void checkGoal(BookShelf bs) {
        int counter=0;
        for(int i=0; i<BookShelf.getMAX_Row() && counter<numRowsToAchieve; i++){
            cat.add(bs.getTile(i,0).getCategory());
            for(int j = 0; j< BookShelf.getMAX_Column() && cat.size()<=numDifferentCategoriesAllowed; j++){
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

