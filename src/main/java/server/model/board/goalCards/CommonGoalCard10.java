package server.model.board.goalCards;
import server.model.board.ItemTile;
import server.model.board.ItemTileCategory;
import server.model.player.BookShelf;

import java.util.HashSet;
import java.util.List;
public class CommonGoalCard10 extends CommonGoalCard implements CheckCommonGoalCard {

    private final static int numRowsToAchieve=2;
    private final HashSet<ItemTileCategory> cat = new HashSet<>(); //I use an arrayList to track the categories in each row
    //of the bookshelf
    @Override
    public void checkGoal(BookShelf bs) {
        int counter=0;
        for(int i=0; i< BookShelf.getMAX_Row() && counter<numRowsToAchieve; i++){
            cat.add(bs.getTile(i,0).getCategory());
            if(!SameCategory(bs,i,cat))
                counter++;
            cat.clear();
        }
        if (counter==numRowsToAchieve)
            increaseNumCompleted();
    }

    public boolean SameCategory(BookShelf bs,int a, HashSet<ItemTileCategory> category){
        for(int j=1;j< BookShelf.getMAX_Column();j++){
            if(cat.contains(bs.getTile(a,j).getCategory()))
                return true;
            else
                cat.add(bs.getTile(a,j).getCategory());
        }
        return false;
    }


}

