package server.model.board;
import server.model.player.BookShelf;
public class CommonGoalCard {
    private final int id;
    private final int numCompleted;

    public CommonGoalCard(int id) {
        this.id = id;
        this.numCompleted = 0;
    }

    public boolean checkGoal(BookShelf playerBS){

        return true;
    }
}
