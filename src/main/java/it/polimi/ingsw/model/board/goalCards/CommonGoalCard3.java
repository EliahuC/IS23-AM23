package it.polimi.ingsw.model.board.goalCards;

import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.player.BookShelf;

import java.util.ArrayList;
import java.util.List;

public class CommonGoalCard3 extends CommonGoalCard implements CheckCommonGoalCard {
    private final List<ItemTile> validGroups = new ArrayList<>();
    private final Launcher L;
    private final static int Goal = 4;

    public CommonGoalCard3(Launcher L){
        this.L = L;
    }

    /**
     * @author Giovanni Di Lorenzo
     * @param bs bookshelf of the player to check the goal
     */
    @Override
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
            if(validGroups.size()==3)
                count++;
            validGroups.clear();
        }
        if(count>=Goal)
            increaseNumCompleted();
    }

    public void print(){
        System.out.print("TILES ON THE CORNER. Insert 4 tiles of the same type in the four corners of the bookshelf. --> You can still get" + getPoints() + "POINTS\n");
    }
}
