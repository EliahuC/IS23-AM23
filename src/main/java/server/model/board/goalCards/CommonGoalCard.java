package server.model.board.goalCards;
import server.model.player.BookShelf;
public abstract class CommonGoalCard {
    private int numCompleted = 0;



    public int getNumCompleted(){
        return numCompleted;
    }

    public void increaseNumCompleted() {
        if(this.numCompleted < 4)
            this.numCompleted++;
    }

    public void initNumCompleted(){
        this.numCompleted = 0;
    }

    public abstract void checkGoal(BookShelf bs);
}

