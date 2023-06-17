package it.polimi.ingsw.model.board.goalCards;

import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.player.BookShelf;

import java.util.ArrayList;
import java.util.List;

public class CommonGoalCard3 extends CommonGoalCard implements CheckCommonGoalCard {
    private final List<ItemTile> validGroups = new ArrayList<>();

    private final static int Goal = 4;

    public CommonGoalCard3(Integer I){
        super(I);
    }

    /**
     * @author Giovanni Di Lorenzo
     * @param bs bookshelf of the player to check the goal
     *this method checks if a player achieved the CommonGoalCard3 goal visiting the entire BookShelf
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
    @Override
    public void print(){
        System.out.print("TILES ON THE CORNER.\n\n");

        //                                     [0]             [1]             [2]             [3]            [4]
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t " + WOOD + "                     " + RESET +
                "\n\t\t\t\t\t\t\t\t\t\t\t\t\t   " + WOOD + " " + YELLOW + "   " + RESET + "   " + RESET + "   " + RESET + "   " + CYAN + "   " + WOOD + " " + RESET +
                "\n\t\t\t\t\t\t\t\t\t\t\t\t\t   " + WOOD + " " + RESET + "   " + RESET + "   " + RESET + "   " + RESET + "   " + RESET + "   " + WOOD + " " + RESET +
                "\n\t\t\t\t\t\t\t\t\t\t\t\t\t   " + WOOD + " " + RESET + "   " + RESET + "   " + RESET + "   " + RESET + "   " + RESET + "   " + WOOD + " " + RESET +
                "\n\t\t\t\t\t\t\t\t\t\t\t\t\t   " + WOOD + " " + RESET + "   " + RESET + "   " + RESET + "   " + RESET + "   " + RESET + "   " + WOOD + " " + RESET +
                "\n\t\t\t\t\t\t\t\t\t\t\t\t\t   " + WOOD + " " + RESET + "   " + RESET + "   " + RESET + "   " + RESET + "   " + RESET + "   " + WOOD + " " + RESET +
                "\n\t\t\t\t\t\t\t\t\t\t\t\t\t   " + WOOD + " " + PINK + "   " + RESET + "   " + RESET + "   " + RESET + "   " + WHITE + "   " + WOOD + " " + RESET +
                "\n\t\t\t\t\t\t\t\t\t\t\t\t\t " + WOOD + "                     " + RESET);

        System.out.print("\n\t\t\t\t\t\t\t\t\tDESCRIPTION: Four tiles of the same type in the four" +
                "\n\t\t\t\t\t\t\t\t\tcorners of the bookshelf.\n");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPOINTS:" + points + "\n\n");
    }
}
