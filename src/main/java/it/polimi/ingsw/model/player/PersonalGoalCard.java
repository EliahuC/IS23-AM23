package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.board.ItemTile;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author Eliahu Cohen
 * Class that represent the Personal Goal Card
 * The card is stored in a HashMap built in the following method:
 * The key of the map is the coordinates (x,y) in the bookshelf of the Item Tile that may be in that position.
 */

public class PersonalGoalCard implements Serializable {
    private Integer completed;
    private Integer points;
    private transient final Integer NumeroCarta;
    private final HashMap<PGCKey, ItemTile> Goal;
    private static final int MAX_Row =6;
    private static final int MAX_Column =5;


    /**
     * @author Eliahu Cohen
     * @param x is the number of the personal goal card that I want to generate
     * Constructor that generate a specific personal goal card based
     */
    public  PersonalGoalCard(int x) {
        this.NumeroCarta = x;
        Goal=new HashMap<>();
        switch (NumeroCarta) {
            case 1 -> {
                Goal.put(new PGCKey(0, 0), new ItemTile("PLANTS"));
                Goal.put(new PGCKey(0, 2), new ItemTile("FRAMES"));
                Goal.put(new PGCKey(1, 4), new ItemTile("CATS"));
                Goal.put(new PGCKey(2, 3), new ItemTile("BOOKS"));
                Goal.put(new PGCKey(3, 1), new ItemTile("GAMES"));
                Goal.put(new PGCKey(5, 2), new ItemTile("TROPHIES"));
            }
            case 2 -> {
                Goal.put(new PGCKey(1, 1), new ItemTile("PLANTS"));
                Goal.put(new PGCKey(5, 4), new ItemTile("FRAMES"));
                Goal.put(new PGCKey(2, 0), new ItemTile("CATS"));
                Goal.put(new PGCKey(3, 4), new ItemTile("BOOKS"));
                Goal.put(new PGCKey(2, 2), new ItemTile("GAMES"));
                Goal.put(new PGCKey(4, 3), new ItemTile("TROPHIES"));
            }
            case 3 -> {
                Goal.put(new PGCKey(2, 2), new ItemTile("PLANTS"));
                Goal.put(new PGCKey(1, 0), new ItemTile("FRAMES"));
                Goal.put(new PGCKey(3, 1), new ItemTile("CATS"));
                Goal.put(new PGCKey(5, 0), new ItemTile("BOOKS"));
                Goal.put(new PGCKey(1, 3), new ItemTile("GAMES"));
                Goal.put(new PGCKey(3, 4), new ItemTile("TROPHIES"));
            }
            case 4 -> {
                Goal.put(new PGCKey(3, 3), new ItemTile("PLANTS"));
                Goal.put(new PGCKey(2, 2), new ItemTile("FRAMES"));
                Goal.put(new PGCKey(4, 2), new ItemTile("CATS"));
                Goal.put(new PGCKey(4, 1), new ItemTile("BOOKS"));
                Goal.put(new PGCKey(0, 4), new ItemTile("GAMES"));
                Goal.put(new PGCKey(2, 0), new ItemTile("TROPHIES"));
            }
            case 5 -> {
                Goal.put(new PGCKey(4, 4), new ItemTile("PLANTS"));
                Goal.put(new PGCKey(3, 1), new ItemTile("FRAMES"));
                Goal.put(new PGCKey(5, 3), new ItemTile("CATS"));
                Goal.put(new PGCKey(3, 2), new ItemTile("BOOKS"));
                Goal.put(new PGCKey(5, 0), new ItemTile("GAMES"));
                Goal.put(new PGCKey(1, 1), new ItemTile("TROPHIES"));
            }
            case 6 -> {
                Goal.put(new PGCKey(5, 0), new ItemTile("PLANTS"));
                Goal.put(new PGCKey(4, 3), new ItemTile("FRAMES"));
                Goal.put(new PGCKey(0, 4), new ItemTile("CATS"));
                Goal.put(new PGCKey(2, 3), new ItemTile("BOOKS"));
                Goal.put(new PGCKey(4, 1), new ItemTile("GAMES"));
                Goal.put(new PGCKey(5, 2), new ItemTile("TROPHIES"));
            }
            case 7 -> {
                Goal.put(new PGCKey(2, 1), new ItemTile("PLANTS"));
                Goal.put(new PGCKey(1, 4), new ItemTile("FRAMES"));
                Goal.put(new PGCKey(0, 0), new ItemTile("CATS"));
                Goal.put(new PGCKey(5, 2), new ItemTile("BOOKS"));
                Goal.put(new PGCKey(4, 4), new ItemTile("GAMES"));
                Goal.put(new PGCKey(3, 0), new ItemTile("TROPHIES"));
            }
            case 8 -> {
                Goal.put(new PGCKey(3, 0), new ItemTile("PLANTS"));
                Goal.put(new PGCKey(0, 4), new ItemTile("FRAMES"));
                Goal.put(new PGCKey(1, 1), new ItemTile("CATS"));
                Goal.put(new PGCKey(4, 3), new ItemTile("BOOKS"));
                Goal.put(new PGCKey(5, 3), new ItemTile("GAMES"));
                Goal.put(new PGCKey(2, 2), new ItemTile("TROPHIES"));
            }
            case 9 -> {
                Goal.put(new PGCKey(4, 4), new ItemTile("PLANTS"));
                Goal.put(new PGCKey(5, 0), new ItemTile("FRAMES"));
                Goal.put(new PGCKey(2, 2), new ItemTile("CATS"));
                Goal.put(new PGCKey(3, 4), new ItemTile("BOOKS"));
                Goal.put(new PGCKey(0, 2), new ItemTile("GAMES"));
                Goal.put(new PGCKey(4, 1), new ItemTile("TROPHIES"));
            }
            case 10 -> {
                Goal.put(new PGCKey(5, 3), new ItemTile("PLANTS"));
                Goal.put(new PGCKey(4, 1), new ItemTile("FRAMES"));
                Goal.put(new PGCKey(3, 3), new ItemTile("CATS"));
                Goal.put(new PGCKey(2, 0), new ItemTile("BOOKS"));
                Goal.put(new PGCKey(1, 1), new ItemTile("GAMES"));
                Goal.put(new PGCKey(0, 4), new ItemTile("TROPHIES"));
            }
            case 11 -> {
                Goal.put(new PGCKey(0, 2), new ItemTile("PLANTS"));
                Goal.put(new PGCKey(3, 2), new ItemTile("FRAMES"));
                Goal.put(new PGCKey(4, 4), new ItemTile("CATS"));
                Goal.put(new PGCKey(1, 1), new ItemTile("BOOKS"));
                Goal.put(new PGCKey(2, 0), new ItemTile("GAMES"));
                Goal.put(new PGCKey(5, 3), new ItemTile("TROPHIES"));
            }
            case 12 -> {
                Goal.put(new PGCKey(1, 1), new ItemTile("PLANTS"));
                Goal.put(new PGCKey(2, 2), new ItemTile("FRAMES"));
                Goal.put(new PGCKey(5, 0), new ItemTile("CATS"));
                Goal.put(new PGCKey(0, 2), new ItemTile("BOOKS"));
                Goal.put(new PGCKey(4, 4), new ItemTile("GAMES"));
                Goal.put(new PGCKey(3, 3), new ItemTile("TROPHIES"));
            }
        }
        this.completed = 0;
        this.points=0;
    }

    /**
     * @author Eliahu Cohen
     * Constructor that generate the personal goal card with the help of a generator
     * and using the singleton pattern
     */
    public PersonalGoalCard(){
        PersonalGoalCardGen istanza= PersonalGoalCardGen.getInstance();
        Goal=new HashMap<>(istanza.GetGoal());
        this.completed = 0;
        this.points=0;
        this.NumeroCarta=null;
    }

    /**
     * @author Eliahu Cohen
     * @param playerBS palyer bookshelf
     * @return the points that the player gained with the personal goal card
     */
    public int CheckGoal(BookShelf playerBS) {
        for (PGCKey key : Goal.keySet()) {
            if (playerBS.getTile(key.getX(), key.getY()).getCategory() == Goal.get(key).getCategory()) {
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

    public HashMap<PGCKey, ItemTile> getGoal() {
        return Goal;
    }


    public boolean equals(HashMap<PGCKey, ItemTile> mappa) {
        int counter=0;
      for(PGCKey k: mappa.keySet()){
          if(this.Goal.keySet().contains(k) && this.Goal.get(k)==mappa.get(k))counter++;
      }
      return counter==6;
    }

    public void print(){
        String[][] temp= new String[6][5];
        for(PGCKey k: Goal.keySet())
            temp[k.getX()][k.getY()] = Goal.get(k).getColor();

        for(int i=0; i<=MAX_Row;i++)
            System.out.print("\u001b[48;2;140;68;28m   \u001B[0m");
        System.out.print("\n");
        for(int i=0; i<MAX_Row; i++){
            System.out.print("  \u001b[48;2;140;68;28m \u001B[0m");
            for(int j=0; j<MAX_Column; j++) {
                if (temp[i][j] == null)
                    System.out.print("   ");
                else
                    System.out.print(temp[i][j]);
                if (j == MAX_Column - 1){
                    System.out.print("\u001b[48;2;140;68;28m \u001B[0m  ");
                    System.out.print("\n");
                }
            }
        }
        for(int i=0; i<=MAX_Row;i++)
            System.out.print("\u001b[48;2;140;68;28m   \u001B[0m");
        System.out.print("\n");
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

}