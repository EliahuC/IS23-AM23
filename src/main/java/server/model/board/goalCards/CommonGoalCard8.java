package server.model.board.goalCards;
import server.model.board.ItemTile;
import server.model.player.BookShelf;

import java.util.List;

public class CommonGoalCard8 extends CommonGoalCard implements checkCommonGoalCard{
    private List<ItemTile> validGroups;
    @Override
    public void checkGoal(BookShelf bs) {
    }
}