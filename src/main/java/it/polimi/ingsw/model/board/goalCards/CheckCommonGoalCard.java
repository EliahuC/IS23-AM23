package it.polimi.ingsw.model.board.goalCards;
import it.polimi.ingsw.model.player.BookShelf;

/**
 * @author Eliahu Cohen
 * interface of the common goal card
 */

public interface CheckCommonGoalCard {
    /**
     * @author Eliahu Cohen
     * @param bs bookshelf of the player to check the goal
     */
    public void checkGoal(BookShelf bs);
}
