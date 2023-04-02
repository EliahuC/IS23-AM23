package server.model.board.goalCards;
import server.model.board.ItemTile;
import server.model.player.BookShelf;

import java.util.List;

public class CommonGoalCard7 extends CommonGoalCard implements checkCommonGoalCard{
    private List<ItemTile> validGroups;

    @Override
    public void checkGoal(BookShelf bs) {
        //diagonale da (0,0)
        ItemTile T= bs.getTile(0,0);
        ItemTile K= bs.getTile(1,0);
        int Counter1=0;
        int Counter2=0;
        for(int i=1;i<5;i++){
            if(bs.getTile(i,i).getCategory()==T.getCategory()) Counter1++;
            if(bs.getTile(i+1,i).getCategory()==K.getCategory())Counter2++;
        }
        if (Counter1==4||Counter2==4) increaseNumCompleted();

    }
}