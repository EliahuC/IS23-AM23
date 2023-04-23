package it.polimi.ingsw.model.board.goalCards;
import it.polimi.ingsw.model.player.BookShelf;
import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.model.board.ItemTile;

import java.util.List;

public class CommonGoalCard11 extends CommonGoalCard implements CheckCommonGoalCard {
    private List<ItemTile> validGroups;
    private final Launcher L;

    public CommonGoalCard11(Launcher L) {
        this.L = L;
    }
    @Override
    public void checkGoal(BookShelf bs) {
    }
}