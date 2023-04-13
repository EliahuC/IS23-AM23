package server.model.board.goalCards;
import server.Launcher;
import server.model.board.ItemTile;
import server.model.player.BookShelf;

import java.util.List;

public class CommonGoalCard4 extends CommonGoalCard implements CheckCommonGoalCard {
    private List<ItemTile> validGroups;
    private final Launcher L;

    public CommonGoalCard4(Launcher L){
        this.L = L;
    }
    @Override
    public void checkGoal(BookShelf bs) {
    }
}