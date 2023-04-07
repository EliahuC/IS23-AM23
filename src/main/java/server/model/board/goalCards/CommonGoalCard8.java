package server.model.board.goalCards;

import server.model.board.ItemTile;
import server.model.board.ItemTileCategory;
import server.model.player.BookShelf;

import java.util.HashSet;
import java.util.List;

public class CommonGoalCard8 extends CommonGoalCard implements CheckCommonGoalCard {
    private List<ItemTile> validGroups;
    private static int numRowsToAchieve=4;
    private static int numDifferentCategoriesAllowed=3;
    private HashSet<ItemTileCategory> cat = new HashSet<>(); //I use an hashset to track the categories in each row
    @Override                                                   //of the bookshelf
    public void checkGoal(BookShelf bs) {
        int counter=0;
        for(int i=0; i<Bookshelf_rows && counter<numRowsToAchieve; i++){
            cat.add(bs.getTile(i,0).getCategory());
            if (CategoriesAllowed(bs,i,cat)<=numDifferentCategoriesAllowed)
                counter++;
            cat.clear();
        }
        if (counter==numRowsToAchieve)
            increaseNumCompleted();
    }
    public HashSet<ItemTileCategory> getCat(){
        return cat;
    }

    public int CategoriesAllowed(BookShelf bs, int a, HashSet<ItemTileCategory> cat){
        for(int j=0; j<Bookshelf_columns && cat.size()<=numDifferentCategoriesAllowed; j++){
            cat.add(bs.getTile(a,j).getCategory());
        }
        return cat.size();
    }
}


