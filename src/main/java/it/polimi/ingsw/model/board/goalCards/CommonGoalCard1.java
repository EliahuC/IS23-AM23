package it.polimi.ingsw.model.board.goalCards;

import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.player.BookShelf;

import java.util.ArrayList;
import java.util.List;

public class CommonGoalCard1 extends CommonGoalCard implements CheckCommonGoalCard {
    private final List<ItemTile> validGroups = new ArrayList<>();

    private final static int Goal = 6;

    public CommonGoalCard1(int numPlayers) {
        super(numPlayers);
    }

    /**
     * this method checks if a player achieved the CommonGoalCard1 goal visiting the entire BookShelf
     * @author Giovanni Di Lorenzo
     * @param bs Player's bookshelf
     *
     */
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
    @Override
    public void print(){
        System.out.print("SIX GROUPS FORMED OF TWO TILES.\n\n");
        //                                      [0]             [1]             [2]             [3]            [4]
        System.out.println("                    " + WOOD + "                     " + RESET +
                "\n                      " + WOOD + " " + CYAN + "   " + CYAN + "   " + RESET + "   " + RESET + "   " + RESET + "   " + WOOD + " " + RESET +
                "\n                      " + WOOD + " " + RESET + "   " + RESET + "   " + RESET + "   " + BLUE + "   " + BLUE + "   " + WOOD + " " + RESET +
                "\n                      " + WOOD + " " + RESET + "   " + WHITE + "   " + WHITE + "   " + RESET + "   " + RESET + "   " + WOOD + " " + RESET +
                "\n                      " + WOOD + " " + RESET + "   " + RESET + "   " + RESET + "   " + RESET + "   " + CYAN + "   " + WOOD + " " + RESET +
                "\n                      " + WOOD + " " + BLUE + "   " + RESET + "   " + RESET + "   " + WHITE + "   " + CYAN + "   " + WOOD + " " + RESET +
                "\n                      " + WOOD + " " + BLUE + "   " + RESET + "   " + RESET + "   " + WHITE + "   " + RESET + "   " + WOOD + " " + RESET +
                "\n                    " + WOOD + "                     " + RESET);

        System.out.print("""
                
                > DESCRIPTION: Six groups each containing at least 2 tiles of the
                same type. The tiles of one group can be different from those of
                another group.
                """);
        System.out.print("> POINTS: " + points + "\n\n");
    }
}