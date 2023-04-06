package server.model.board;
import server.model.player.BookShelf;
public abstract class CommonGoalCard {
    private int numCompleted = 0;
    protected static int Bookshelf_rows=6;
    protected static int Bookshelf_columns=5;


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

