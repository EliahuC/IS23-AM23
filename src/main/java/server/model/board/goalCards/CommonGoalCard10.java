package server.model.board;
import server.model.board.ItemTile;
import server.model.player.BookShelf;

import java.util.ArrayList;
import java.util.List;
public class CommonGoalCard10 extends CommonGoalCard implements checkCommonGoalCard {
    private List<ItemTile> validGroups;
    private final static int numRowsToAchieve=2;
    private final List<itemTileCategory> cat = new ArrayList<>(); //I use an arrayList to track the categories in each row
                                                                //of the bookshelf
    @Override
    public void checkGoal(BookShelf bs) {
        int counter=0;
        boolean SameCategory=false;
        for(int i=0; i<bs.getRows() && counter<numRowsToAchieve; i++){
            cat.add(bs.getTile(i,0).getCategory());
            for(int j=1; j<bs.getColumns() && !SameCategory; j++){
                if(cat.contains(bs.getTile(i,j).getCategory()))
                    SameCategory=true;
            }
            if (!SameCategory)
                counter++;
            cat.clear();
        }
        if (counter==numRowsToAchieve)
            increaseNumCompleted();
    }
}
