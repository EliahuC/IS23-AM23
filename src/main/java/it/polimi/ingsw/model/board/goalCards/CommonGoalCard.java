package it.polimi.ingsw.model.board.goalCards;

import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.model.player.BookShelf;

/**
 * @author Eliahu Cohen
 * abstract class that the specific goal card will implement
 */
public abstract class CommonGoalCard {
    private int numCompleted = 0;
    private final Launcher L = new Launcher();

    /**
     * @author Eliahu Cohen
     * @return people that have completed the goal
     */
    public int getNumCompleted() {
        return numCompleted;
    }

    /**
     * @author Eliahu Cohen
     * increase the number of players that have  reahced the goal
     */
    public void increaseNumCompleted() {
        if (this.numCompleted < 4)
            this.numCompleted++;
    }

    /**
     * @author Eliahu Cohen
     * @return points gained with the goal
     */
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

    /**
     * @author Eliahu Cohen
     * initialization of numCompleated
     */
    public void initNumCompleted(){
        this.numCompleted = 0;
    }

    /**
     * @author Eliahu Cohen
     * @return Launcher
     */
    public Launcher getLauncher(){
        return L;
    }


}

