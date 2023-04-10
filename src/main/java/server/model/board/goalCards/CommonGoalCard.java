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

    public int getPoints() {
        int points=0;
        switch (numCompleted) {
            case 1 -> points = 8;
            case 2 -> points = 6;
            case 3 -> points = 4;


        }
        return points;
    }

    public void initNumCompleted(){
        this.numCompleted = 0;
    }

    public abstract void checkGoal(BookShelf bs);
}

