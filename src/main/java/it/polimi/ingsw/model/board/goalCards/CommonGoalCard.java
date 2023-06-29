package it.polimi.ingsw.model.board.goalCards;

import it.polimi.ingsw.model.player.BookShelf;

/**
 * abstract class that the specific goal card will implement
 * @author Eliahu Cohen
 *
 */
public class CommonGoalCard {
    private int numCompleted = 0;
    private int numPlayersCGC;
    protected int points;
    public CommonGoalCard(Integer I){
        this.numPlayersCGC =I;
        this.points=8;
    }
    protected static final String RESET = "\u001B[0m";
    protected static final String GREEN = "\u001b[48;2;145;165;65m";
    protected static final String WHITE = "\u001b[48;2;236;225;189m";
    protected static final String YELLOW = "\u001b[48;2;223;169;59m";
    protected static final String BLUE = "\u001b[48;2;0;104;146m";
    protected static final String CYAN = "\u001b[48;2;106;183;183m";
    protected static final String PINK = "\u001b[48;2;198;77;124m";
    protected static final String WOOD = "\u001b[48;2;140;68;28m";

    /**
     * @author Eliahu Cohen
     * @return people that have completed the goal
     */
    public int getNumCompleted() {
        return numCompleted;
    }

    /**
     * increase the number of players that have  reahced the goal
     * @author Eliahu Cohen
     *
     */
    public void increaseNumCompleted() {
        if (this.numCompleted < 4)
            this.numCompleted++;
    }

    public int getScore(){
        return points;
    }

    /**
     * @author Eliahu Cohen
     * @return points gained with the goal
     */
    public int getPoints() {
        int score = 0;
        switch (numPlayersCGC) {
            case 2 -> {
                switch (numCompleted) {
                    case 1 -> {
                        score = 8;
                        points = 4;
                    }
                    case 2 -> {
                        score = 4;
                        points = 0;
                    }
                }
            }
            case 3 -> {
                switch (numCompleted) {
                    case 1 -> {
                        score = 8;
                        points=6;
                    }
                    case 2 -> {
                        score = 6;
                        points=4;
                    }
                    case 3 -> {
                        score = 4;
                        points=0;
                    }
                }
            }
            case 4 -> {
                switch (numCompleted) {
                    case 1 -> {
                        score = 8;
                        points=6;
                    }
                    case 2 -> {
                        score = 6;
                        points=4;
                    }
                    case 3 -> {
                        score = 4;
                        points=2;
                    }
                    case 4 -> {
                        score = 2;
                        points=0;
                    }
                }
            }
        }
        return score;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void checkGoal(BookShelf bs) {

    }

    /**
     * initialization of numCompleated
     * @author Eliahu Cohen
     *
     */
    public void initNumCompleted(){
        this.numCompleted = 0;
    }


    /**
     * @author Simone Controguerra
     */
    public void print(){}
}

