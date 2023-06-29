package it.polimi.ingsw.model.board.goalCards;

import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.player.BookShelf;
import it.polimi.ingsw.model.player.Player;

public class CommonGoalCard2 extends CommonGoalCard implements CheckCommonGoalCard {

    public CommonGoalCard2(Integer I){
        super(I);
    }

    /**
     * @author Eliahu Cohen and Giovanni di Lorenzo
     * @param bs bookshelf of the player to check the goal
     */
    public void checkGoal(BookShelf bs) {
        if (NoItemsNull(bs)) {
            if ((bs.getTile(0, 0).getCategory() == bs.getTile(0, 4).getCategory()
                    && (bs.getTile(0, 0).getCategory() == bs.getTile(5, 4).getCategory())
                    && (bs.getTile(0, 0).getCategory() == bs.getTile(5, 0).getCategory())))
                increaseNumCompleted();
        }
    }

    /**
     *  this method checks if the Item Tiles on upper right, upper left, lower right and lower left corners are not
     *  null
     * @author Giovanni Di Lorenzo
     * @param bs Player's bookshelf
     * @return true if the tile is not null
     */
    private boolean NoItemsNull(BookShelf bs){
        if((bs.getTile(0,0)!=null) && (bs.getTile(0,4)!=null) &&
                (bs.getTile(5,0)!=null) && (bs.getTile(5,4) != null))
            return true;
        else return false;
    }

    @Override
    public void print(){
        System.out.print("FOUR GROUPS FORMED OF FOUR TILES.\n\n");
        //                                     [0]             [1]             [2]             [3]            [4]
        System.out.println("                    " + WOOD + "                     " + RESET +
                "\n                      " + WOOD + " " + RESET + "   " + WHITE + "   " + WHITE + "   " + PINK + "   " + PINK + "   " + WOOD + " " + RESET +
                "\n                      " + WOOD + " " + RESET + "   " + RESET + "   " + WHITE + "   " + WHITE + "   " + PINK + "   " + WOOD + " " + RESET +
                "\n                      " + WOOD + " " + RESET + "   " + RESET + "   " + RESET + "   " + RESET + "   " + PINK + "   " + WOOD + " " + RESET +
                "\n                      " + WOOD + " " + RESET + "   " + RESET + "   " + RESET + "   " + RESET + "   " + RESET + "   " + WOOD + " " + RESET +
                "\n                      " + WOOD + " " + CYAN + "   " + WHITE + "   " + WHITE + "   " + WHITE + "   " + RESET + "   " + WOOD + " " + RESET +
                "\n                      " + WOOD + " " + CYAN + "   " + CYAN + "   " + CYAN + "   " + WHITE + "   " + RESET + "   " + WOOD + " " + RESET +
                "\n                    " + WOOD + "                     " + RESET);
        System.out.print("""
                
                > DESCRIPTION: Four groups each containing at least 4 tiles of the
                same type. The tiles of one group can be different from those of
                another group.
                """);
        System.out.print("> POINTS: " + points + "\n\n");
    }
}