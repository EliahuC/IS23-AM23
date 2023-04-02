package server.model.board.goalCards;
import server.model.board.ItemTile;
import server.model.player.BookShelf;

import java.util.List;

public class CommonGoalCard2 extends CommonGoalCard implements checkCommonGoalCard{
    private List<ItemTile> validGroups;
    @Override
    public void checkGoal(BookShelf bs) {
        if((bs.getTile(0,0).getCategory()==bs.getTile(0,4).getCategory()
                &&(bs.getTile(0,0).getCategory()==bs.getTile(5,4).getCategory())
                &&(bs.getTile(0,0).getCategory()==bs.getTile(5,0).getCategory())))
            increaseNumCompleted();
    }
}