package server.model.board.goalCards;
import server.model.board.ItemTile;
import server.model.player.BookShelf;

import java.util.List;

public class CommonGoalCard12 extends CommonGoalCard implements CheckCommonGoalCard {
    private List<ItemTile> validGroups;
    @Override
    public void checkGoal(BookShelf bs) {
        if(checkRtoL(bs)||checkLtoR(bs)) increaseNumCompleted();
    }

    private boolean checkRtoL(BookShelf bs){
        int lastColumn=0;
        for(int i=0; i<BookShelf.getMAX_Column();i++){
          if(countColumn(bs,i)!=lastColumn+1) return false;
          else lastColumn=countColumn(bs,i);
        }
        return true;
    }
    private boolean checkLtoR(BookShelf bs){
        int lastColumn=0;
        for(int i=0; i<BookShelf.getMAX_Column(); i++){
            if(countColumn(bs,4-i)!=lastColumn+1) return false;
            else lastColumn=countColumn(bs,i);
        }
        return true;
    }


    private int countColumn(BookShelf bs,int i){
        int counter =0;
        for (int j=0;j<BookShelf.getMAX_Row()&&bs.getTile(5-j,i)!=null;j++){
            counter++;
        }
        return counter;
    }
}