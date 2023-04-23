package it.polimi.ingsw.model.board.goalCards;
import it.polimi.ingsw.model.player.BookShelf;
import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.model.board.ItemTile;

import java.util.List;

public class CommonGoalCard2 extends CommonGoalCard implements CheckCommonGoalCard {
    private List<ItemTile> validGroups;
    private final Launcher L;

    public CommonGoalCard2(Launcher L){
        this.L = L;
    }
    @Override
    public void checkGoal(BookShelf bs) {
        if((bs.getTile(0,0).getCategory()==bs.getTile(0,4).getCategory()
                &&(bs.getTile(0,0).getCategory()==bs.getTile(5,4).getCategory())
                &&(bs.getTile(0,0).getCategory()==bs.getTile(5,0).getCategory())))
            increaseNumCompleted();
    }
}