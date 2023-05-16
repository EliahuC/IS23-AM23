package it.polimi.ingsw.model.board.goalCards;

import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.model.player.BookShelf;

public class CommonGoalCard2 extends CommonGoalCard implements CheckCommonGoalCard {
    private final Launcher L;
    public CommonGoalCard2(Launcher L){
        this.L = L;
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
     * @author Giovanni Di Lorenzo
     * @param bs Player's bookshelf
     * @return this method checks if the itemtiles on upper right, upper left, lower right and lower left corners are not
     * null
     */
    private boolean NoItemsNull(BookShelf bs){
        if((bs.getTile(0,0)!=null) && (bs.getTile(0,4)!=null) &&
                (bs.getTile(5,0)!=null) && (bs.getTile(5,4) != null))
            return true;
        else return false;
    }

    public void print(){
        System.out.print("TILES ON THE CORNER. Insert 4 tiles of the same type in the four corners of the bookshelf. --> You can still get" + getPoints() + "POINTS\n");
    }
}