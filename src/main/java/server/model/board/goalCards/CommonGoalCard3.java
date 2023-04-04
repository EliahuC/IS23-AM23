package server.model.board.goalCards;
import server.model.board.ItemTile;
import server.model.player.BookShelf;

import java.util.List;

public class CommonGoalCard3 extends CommonGoalCard implements CheckCommonGoalCard {
    private List<ItemTile> validGroups;
    @Override
    public void checkGoal(BookShelf bs) {
    }
}