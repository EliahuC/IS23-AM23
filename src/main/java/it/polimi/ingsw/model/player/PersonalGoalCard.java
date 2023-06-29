package it.polimi.ingsw.model.player;

import com.google.gson.Gson;
import it.polimi.ingsw.model.board.ItemTile;

import java.io.Serializable;
import java.util.HashMap;

/**
 *  * Class that represent the Personal Goal Card
 *  * The card is stored in a HashMap built in the following method:
 *  * The key of the map is the coordinates (x,y) in the bookshelf of the Item Tile that may be in that position.
 * @author Eliahu Cohen
 */

public class PersonalGoalCard implements Serializable {
    private Integer completed;
    private transient final HashMap<Pair, ItemTile> Goal;
    private Integer points;
    private  final Integer NumeroCarta;
    private static final int MAX_Row =6;
    private static final int MAX_Column =5;


    /**
     *  Constructor that generate a specific personal goal card based
     * @author Eliahu Cohen
     * @param x is the number of the personal goal card that I want to generate
     *
     */
    public  PersonalGoalCard(int x) {
        this.NumeroCarta = x;
        Goal=new HashMap<>();
        switch (NumeroCarta) {
            case 1 -> {
                Goal.put(new Pair(0, 0), new ItemTile("PLANTS"));
                Goal.put(new Pair(0, 2), new ItemTile("FRAMES"));
                Goal.put(new Pair(1, 4), new ItemTile("CATS"));
                Goal.put(new Pair(2, 3), new ItemTile("BOOKS"));
                Goal.put(new Pair(3, 1), new ItemTile("GAMES"));
                Goal.put(new Pair(5, 2), new ItemTile("TROPHIES"));
            }
            case 2 -> {
                Goal.put(new Pair(1, 1), new ItemTile("PLANTS"));
                Goal.put(new Pair(5, 4), new ItemTile("FRAMES"));
                Goal.put(new Pair(2, 0), new ItemTile("CATS"));
                Goal.put(new Pair(3, 4), new ItemTile("BOOKS"));
                Goal.put(new Pair(2, 2), new ItemTile("GAMES"));
                Goal.put(new Pair(4, 3), new ItemTile("TROPHIES"));
            }
            case 3 -> {
                Goal.put(new Pair(2, 2), new ItemTile("PLANTS"));
                Goal.put(new Pair(1, 0), new ItemTile("FRAMES"));
                Goal.put(new Pair(3, 1), new ItemTile("CATS"));
                Goal.put(new Pair(5, 0), new ItemTile("BOOKS"));
                Goal.put(new Pair(1, 3), new ItemTile("GAMES"));
                Goal.put(new Pair(3, 4), new ItemTile("TROPHIES"));
            }
            case 4 -> {
                Goal.put(new Pair(3, 3), new ItemTile("PLANTS"));
                Goal.put(new Pair(2, 2), new ItemTile("FRAMES"));
                Goal.put(new Pair(4, 2), new ItemTile("CATS"));
                Goal.put(new Pair(4, 1), new ItemTile("BOOKS"));
                Goal.put(new Pair(0, 4), new ItemTile("GAMES"));
                Goal.put(new Pair(2, 0), new ItemTile("TROPHIES"));
            }
            case 5 -> {
                Goal.put(new Pair(4, 4), new ItemTile("PLANTS"));
                Goal.put(new Pair(3, 1), new ItemTile("FRAMES"));
                Goal.put(new Pair(5, 3), new ItemTile("CATS"));
                Goal.put(new Pair(3, 2), new ItemTile("BOOKS"));
                Goal.put(new Pair(5, 0), new ItemTile("GAMES"));
                Goal.put(new Pair(1, 1), new ItemTile("TROPHIES"));
            }
            case 6 -> {
                Goal.put(new Pair(5, 0), new ItemTile("PLANTS"));
                Goal.put(new Pair(4, 3), new ItemTile("FRAMES"));
                Goal.put(new Pair(0, 4), new ItemTile("CATS"));
                Goal.put(new Pair(2, 3), new ItemTile("BOOKS"));
                Goal.put(new Pair(4, 1), new ItemTile("GAMES"));
                Goal.put(new Pair(5, 2), new ItemTile("TROPHIES"));
            }
            case 7 -> {
                Goal.put(new Pair(2, 1), new ItemTile("PLANTS"));
                Goal.put(new Pair(1, 4), new ItemTile("FRAMES"));
                Goal.put(new Pair(0, 0), new ItemTile("CATS"));
                Goal.put(new Pair(5, 2), new ItemTile("BOOKS"));
                Goal.put(new Pair(4, 4), new ItemTile("GAMES"));
                Goal.put(new Pair(3, 0), new ItemTile("TROPHIES"));
            }
            case 8 -> {
                Goal.put(new Pair(3, 0), new ItemTile("PLANTS"));
                Goal.put(new Pair(0, 4), new ItemTile("FRAMES"));
                Goal.put(new Pair(1, 1), new ItemTile("CATS"));
                Goal.put(new Pair(4, 3), new ItemTile("BOOKS"));
                Goal.put(new Pair(5, 3), new ItemTile("GAMES"));
                Goal.put(new Pair(2, 2), new ItemTile("TROPHIES"));
            }
            case 9 -> {
                Goal.put(new Pair(4, 4), new ItemTile("PLANTS"));
                Goal.put(new Pair(5, 0), new ItemTile("FRAMES"));
                Goal.put(new Pair(2, 2), new ItemTile("CATS"));
                Goal.put(new Pair(3, 4), new ItemTile("BOOKS"));
                Goal.put(new Pair(0, 2), new ItemTile("GAMES"));
                Goal.put(new Pair(4, 1), new ItemTile("TROPHIES"));
            }
            case 10 -> {
                Goal.put(new Pair(5, 3), new ItemTile("PLANTS"));
                Goal.put(new Pair(4, 1), new ItemTile("FRAMES"));
                Goal.put(new Pair(3, 3), new ItemTile("CATS"));
                Goal.put(new Pair(2, 0), new ItemTile("BOOKS"));
                Goal.put(new Pair(1, 1), new ItemTile("GAMES"));
                Goal.put(new Pair(0, 4), new ItemTile("TROPHIES"));
            }
            case 11 -> {
                Goal.put(new Pair(0, 2), new ItemTile("PLANTS"));
                Goal.put(new Pair(3, 2), new ItemTile("FRAMES"));
                Goal.put(new Pair(4, 4), new ItemTile("CATS"));
                Goal.put(new Pair(1, 1), new ItemTile("BOOKS"));
                Goal.put(new Pair(2, 0), new ItemTile("GAMES"));
                Goal.put(new Pair(5, 3), new ItemTile("TROPHIES"));
            }
            case 12 -> {
                Goal.put(new Pair(1, 1), new ItemTile("PLANTS"));
                Goal.put(new Pair(2, 2), new ItemTile("FRAMES"));
                Goal.put(new Pair(5, 0), new ItemTile("CATS"));
                Goal.put(new Pair(0, 2), new ItemTile("BOOKS"));
                Goal.put(new Pair(4, 4), new ItemTile("GAMES"));
                Goal.put(new Pair(3, 3), new ItemTile("TROPHIES"));
            }
        }
        this.completed = 0;
        this.points=0;
    }

    /**
     * Constructor that generate the personal goal card with the help of a generator
     * and using the singleton pattern
     * @author Eliahu Cohen
     */
    public PersonalGoalCard(){
        PersonalGoalCardGen istanza= PersonalGoalCardGen.getInstance();
        PGCkey card=istanza.GetGoal();
        Goal=new HashMap<>(card.getGoal());
        this.completed = 0;
        this.points=0;
        this.NumeroCarta=card.getId();
    }

    public PersonalGoalCard(HashMap<Pair, ItemTile> goal, Integer numeroCarta) {
        Goal = goal;
        NumeroCarta = numeroCarta;
    }

    /**
     * @author Eliahu Cohen
     * @param playerBS palyer bookshelf
     * @return the points that the player gained with the personal goal card
     */
    public int CheckGoal(BookShelf playerBS) {
        for (Pair key : Goal.keySet()) {
            if (playerBS.getTile(key.getX(), key.getY())!=null && playerBS.getTile(key.getX(), key.getY()).getCategory() == Goal.get(key).getCategory()) {
                completed++;
            }

        }
        return GivePoints();
    }

    /**
     * @author Eliahu Cohen
     * @return the points based on how much correct position tiles that the player gets
     */
     private int GivePoints() {

        switch (completed) {
            case 1 -> points = 1;
            case 2 -> points = 2;
            case 3 -> points = 4;
            case 4 -> points = 6;
            case 5 -> points = 9;
            case 6 -> points = 12;
        }
        return points;
    }

    public HashMap<Pair, ItemTile> getGoal() {
        return Goal;
    }


    public boolean equals(HashMap<Pair, ItemTile> mappa) {
        int counter=0;
      for(Pair k: mappa.keySet()){
          if(this.Goal.keySet().contains(k) && this.Goal.get(k)==mappa.get(k))counter++;
      }
      return counter==6;
    }

    public Integer getCompleted() {
        return completed;
    }

    public void setCompleted(Integer completed) {
        this.completed = completed;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getNumeroCarta() {
        return NumeroCarta;
    }
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}