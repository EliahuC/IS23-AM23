package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.board.ItemTile;

import java.util.HashMap;

public class PersonalGoalCard {
    private int completed;
    private int points;
    private final Integer NumeroCarta;
    private final HashMap<PGCKey, ItemTile> Goal;



    //METODO SWITCH
    public PersonalGoalCard(int x) {
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

    //METODO MAPPA DI MAPPA
    public PersonalGoalCard(){
        PersonalGoalCardGen istanza= PersonalGoalCardGen.getInstance();
        Goal=new HashMap<>(istanza.GetGoal());
        this.completed = 0;
        this.points=0;
        this.NumeroCarta=null;
    }


    public int CheckGoal(BookShelf playerBS) {
        for (PGCKey key : Goal.keySet()) {
            if (playerBS.getTile(key.getX(), key.getY()).getCategory() == Goal.get(key).getCategory()) {
                completed++;
            }

        }
        return GivePoints();
    }

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

}