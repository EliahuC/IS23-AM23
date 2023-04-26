package it.polimi.ingsw.model.board.goalCards;
import it.polimi.ingsw.model.player.BookShelf;
import it.polimi.ingsw.Launcher;
public abstract class CommonGoalCard {
    private int numCompleted = 0;
    private final Launcher L = new Launcher();


    public int getNumCompleted() {
        return numCompleted;
    }

    public void increaseNumCompleted() {
        if (this.numCompleted < 4)
            this.numCompleted++;
    }

    public int getPoints() {
        int points = 0;
        switch (L.getNumPlayers()) {
            case 2: {
                switch (numCompleted) {
                    case 1 -> points = 8;
                    case 2 -> points = 4;
                }
                break;
            }
            case 3: {
                switch (numCompleted) {
                    case 1 -> points = 8;
                    case 2 -> points = 6;
                    case 3 -> points = 4;
                }
                break;
            }
            case 4: {
                switch (numCompleted) {
                    case 1 -> points = 8;
                    case 2 -> points = 6;
                    case 3 -> points = 4;
                    case 4 -> points = 2;
                }
                break;
            }
        }
        return points;
    }
    public abstract void checkGoal (BookShelf bs);
    public void initNumCompleted(){
        this.numCompleted = 0;
    }

    public Launcher getLauncher(){
        return L;
    }


}

