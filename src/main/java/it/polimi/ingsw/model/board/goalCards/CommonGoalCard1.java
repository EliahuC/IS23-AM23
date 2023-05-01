package it.polimi.ingsw.model.board.goalCards;
import it.polimi.ingsw.model.board.ItemTileCategory;
import it.polimi.ingsw.model.player.BookShelf;
import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.Launcher;

import java.util.ArrayList;
import java.util.List;

public class CommonGoalCard1 extends CommonGoalCard implements CheckCommonGoalCard {
    private final List<ItemTile> validGroups = new ArrayList<>();
    private final Launcher L;
    private final static int Goal = 6;

    public CommonGoalCard1(Launcher L) {
        this.L = L;
    }

    @Override
    //Six groups of 2 adjacent tiles.
    public void checkGoal(BookShelf bs) {
        int count = 0;
        ItemTile it;
        int i = 0;
        while (bs.getUsedTiles().size() < bs.NumTiles()) {
            bs.SetFirstTile();
            do {
                it = bs.UpperTile(bs.getTileRow(), bs.getTileColumn());
                if (!bs.AlreadyUsed(it) && it != null && bs.CheckCategory(it)) {
                    bs.getUsedTiles().add(it);
                    validGroups.add(it);

                }
                it = bs.LowerTile(bs.getTileRow(), bs.getTileColumn());
                if (!bs.AlreadyUsed(it) && it != null && bs.CheckCategory(it)) {
                    bs.getUsedTiles().add(it);
                    validGroups.add(it);
                }
                it = bs.RightTile(bs.getTileRow(), bs.getTileColumn());
                if (!bs.AlreadyUsed(it) && it != null && bs.CheckCategory(it)) {
                    bs.getUsedTiles().add(it);
                    validGroups.add(it);
                }
                it = bs.LeftTile(bs.getTileRow(), bs.getTileColumn());
                if (!bs.AlreadyUsed(it) && it != null && bs.CheckCategory(it)) {
                    bs.getUsedTiles().add(it);
                    validGroups.add(it);
                }
                if (i < bs.getUsedTiles().size() - 1) {
                    bs.SetLocation(bs.getUsedTiles().get(i + 1));
                    i++;
                } else {
                    i++;
                    break;
                }
            } while (bs.getUsedTiles().iterator().hasNext());
            if(validGroups.size()==1)
                count++;
            validGroups.clear();
        }
        if(count>=Goal)
            increaseNumCompleted();
    }
}