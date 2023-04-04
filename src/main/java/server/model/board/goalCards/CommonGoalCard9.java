package server.model.board;
import server.model.player.BookShelf;

import java.util.ArrayList;
import java.util.List;
public class CommonGoalCard9 extends CommonGoalCard implements checkCommonGoalCard {
    private List<ItemTile> validGroups;
    private static int numColumnsToAchieve=2;
    private List<ItemTileCategory> cat = new ArrayList<>(); //I use an arrayList to track the categories in each column
                                                                //of the bookshelf
    @Override
    public void checkGoal(BookShelf bs) {
        int counter=0;
        boolean SameCategory=false;
        for(int j=0; j<bs.getColumns() && counter<numColumnsToAchieve; j++){
            cat.add(bs.getTile(0,j).getCategory());
            for(int i=1; i<bs.getRows() && !SameCategory; i++){
                if(cat.contains(bs.getTile(i,j).getCategory()))
                    SameCategory=true;
            }
            if (!SameCategory)
                counter++;
            cat.clear();
        }
        if (counter==numColumnsToAchieve)
            increaseNumCompleted();
    }
}
