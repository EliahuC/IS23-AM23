package server.model.board.goalCards;
import server.model.player.BookShelf;
import server.model.board.ItemTile;
import server.model.board.ItemTileCategory;
import java.util.ArrayList;
import java.util.List;
public class CommonGoalCard10 extends CommonGoalCard implements CheckCommonGoalCard {
    private List<ItemTile> validGroups;
    private final static int numRowsToAchieve=2;
    private final List<ItemTileCategory> cat = new ArrayList<>(); //I use an arrayList to track the categories in each row
                                                                //of the bookshelf
    @Override
    public void checkGoal(BookShelf bs) {
        int counter=0;
        boolean SameCategory=false;
        for(int i=0; i<BookShelf.getMAX_Row() && counter<numRowsToAchieve; i++){
            cat.add(bs.getTile(i,0).getCategory());
            for(int j=1; j<BookShelf.getMAX_Column() && !SameCategory; j++){
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
