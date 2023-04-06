package server.model.board;
import server.model.board.ItemTile;
import server.model.player.BookShelf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
public class CommonGoalCard9 extends CommonGoalCard implements checkCommonGoalCard {
    private List<ItemTile> validGroups;
    private static int numColumnsToAchieve=2;
    private HashSet<ItemTileCategory> cat = new HashSet<>(); //I use an arrayList to track the categories in each column
    //of the bookshelf
    @Override
    public void checkGoal(BookShelf bs) {
        int counter=0;
        for(int j=0; j<Bookshelf_columns && counter<numColumnsToAchieve; j++){
            cat.add(bs.getTile(0,j).getCategory());
            if(!SameCategory(bs,j,cat)) {
                counter++;
            }
            cat.clear();
            }

        if (counter==numColumnsToAchieve)
            increaseNumCompleted();
        }

    public boolean SameCategory(BookShelf bs, int a, HashSet<ItemTileCategory> category){
        for(int i=1;i<Bookshelf_rows;i++){
            if(cat.contains(bs.getTile(i,a).getCategory()))
                return true;
            else
                cat.add(bs.getTile(i,a).getCategory());
        }
        return false;
    }

    public HashSet<ItemTileCategory> getCat(){
        return cat;
    }
}

