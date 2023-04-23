package it.polimi.ingsw.model.board.goalCards;
import it.polimi.ingsw.model.player.BookShelf;
import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.model.board.ItemTile;

import java.util.List;

public class CommonGoalCard3 extends CommonGoalCard implements CheckCommonGoalCard {
    private List<ItemTile> validGroups;
    private final Launcher L;

    public CommonGoalCard3(Launcher L){
        this.L = L;
    }
    @Override
    public void checkGoal(BookShelf bs) {
    }
}