package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.board.ItemTile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * @author Eliahu Cohen
 * Generator of the personal goal cards based on singleton pattern
 */
public class PersonalGoalCardGen implements Serializable {
    private static PersonalGoalCardGen generator=null;
    private static final ArrayList<HashMap<PGCKey, ItemTile>> Goals = new ArrayList<>();

    private PersonalGoalCardGen() {
        //CARD 1
        HashMap<PGCKey, ItemTile> goal = new HashMap<>();
        goal.put(new PGCKey(0, 0), new ItemTile("PLANTS"));
        goal.put(new PGCKey(0, 2), new ItemTile("FRAMES"));
        goal.put(new PGCKey(1, 4), new ItemTile("CATS"));
        goal.put(new PGCKey(2, 3), new ItemTile("BOOKS"));
        goal.put(new PGCKey(3, 1), new ItemTile("GAMES"));
        goal.put(new PGCKey(5, 2), new ItemTile("TROPHIES"));
        Goals.add(goal);
        //CARD 2
        goal =new HashMap<>();
        goal.put(new PGCKey(1, 1), new ItemTile("PLANTS"));
        goal.put(new PGCKey(5, 4), new ItemTile("FRAMES"));
        goal.put(new PGCKey(2, 0), new ItemTile("CATS"));
        goal.put(new PGCKey(3, 4), new ItemTile("BOOKS"));
        goal.put(new PGCKey(2, 2), new ItemTile("GAMES"));
        goal.put(new PGCKey(4, 3), new ItemTile("TROPHIES"));
        Goals.add(goal);
        //CARD 3
        goal =new HashMap<>();
        goal.put(new PGCKey(2, 2), new ItemTile("PLANTS"));
        goal.put(new PGCKey(1, 0), new ItemTile("FRAMES"));
        goal.put(new PGCKey(3, 1), new ItemTile("CATS"));
        goal.put(new PGCKey(5, 0), new ItemTile("BOOKS"));
        goal.put(new PGCKey(1, 3), new ItemTile("GAMES"));
        goal.put(new PGCKey(3, 4), new ItemTile("TROPHIES"));
        Goals.add(goal);
        //CARD 4
        goal =new HashMap<>();
        goal.put(new PGCKey(3, 3), new ItemTile("PLANTS"));
        goal.put(new PGCKey(2, 2), new ItemTile("FRAMES"));
        goal.put(new PGCKey(4, 2), new ItemTile("CATS"));
        goal.put(new PGCKey(4, 1), new ItemTile("BOOKS"));
        goal.put(new PGCKey(0, 4), new ItemTile("GAMES"));
        goal.put(new PGCKey(2, 0), new ItemTile("TROPHIES"));
        Goals.add(goal);
        //CARD 5
        goal =new HashMap<>();
        goal.put(new PGCKey(4, 4), new ItemTile("PLANTS"));
        goal.put(new PGCKey(3, 1), new ItemTile("FRAMES"));
        goal.put(new PGCKey(5, 3), new ItemTile("CATS"));
        goal.put(new PGCKey(3, 2), new ItemTile("BOOKS"));
        goal.put(new PGCKey(5, 0), new ItemTile("GAMES"));
        goal.put(new PGCKey(1, 1), new ItemTile("TROPHIES"));
        Goals.add(goal);
        //CARD 6
        goal =new HashMap<>();
        goal.put(new PGCKey(5, 0), new ItemTile("PLANTS"));
        goal.put(new PGCKey(4, 3), new ItemTile("FRAMES"));
        goal.put(new PGCKey(0, 4), new ItemTile("CATS"));
        goal.put(new PGCKey(2, 3), new ItemTile("BOOKS"));
        goal.put(new PGCKey(4, 1), new ItemTile("GAMES"));
        goal.put(new PGCKey(5, 2), new ItemTile("TROPHIES"));
        Goals.add(goal);
        //CARD 7
        goal =new HashMap<>();
        goal.put(new PGCKey(2, 1), new ItemTile("PLANTS"));
        goal.put(new PGCKey(1, 4), new ItemTile("FRAMES"));
        goal.put(new PGCKey(0, 0), new ItemTile("CATS"));
        goal.put(new PGCKey(5, 2), new ItemTile("BOOKS"));
        goal.put(new PGCKey(4, 4), new ItemTile("GAMES"));
        goal.put(new PGCKey(3, 0), new ItemTile("TROPHIES"));
        Goals.add(goal);
        //CARD 8
        goal =new HashMap<>();
        goal.put(new PGCKey(3, 0), new ItemTile("PLANTS"));
        goal.put(new PGCKey(0, 4), new ItemTile("FRAMES"));
        goal.put(new PGCKey(1, 1), new ItemTile("CATS"));
        goal.put(new PGCKey(4, 3), new ItemTile("BOOKS"));
        goal.put(new PGCKey(5, 3), new ItemTile("GAMES"));
        goal.put(new PGCKey(2, 2), new ItemTile("TROPHIES"));
        Goals.add(goal);
        //CARD 9
        goal =new HashMap<>();
        goal.put(new PGCKey(4, 4), new ItemTile("PLANTS"));
        goal.put(new PGCKey(5, 0), new ItemTile("FRAMES"));
        goal.put(new PGCKey(2, 2), new ItemTile("CATS"));
        goal.put(new PGCKey(3, 4), new ItemTile("BOOKS"));
        goal.put(new PGCKey(0, 2), new ItemTile("GAMES"));
        goal.put(new PGCKey(4, 1), new ItemTile("TROPHIES"));
        Goals.add(goal);
        //CARD 10
        goal =new HashMap<>();
        goal.put(new PGCKey(5, 3), new ItemTile("PLANTS"));
        goal.put(new PGCKey(4, 1), new ItemTile("FRAMES"));
        goal.put(new PGCKey(3, 3), new ItemTile("CATS"));
        goal.put(new PGCKey(2, 0), new ItemTile("BOOKS"));
        goal.put(new PGCKey(1, 1), new ItemTile("GAMES"));
        goal.put(new PGCKey(0, 4), new ItemTile("TROPHIES"));
        Goals.add(goal);
        //CARD 11
        goal =new HashMap<>();
        goal.put(new PGCKey(0, 2), new ItemTile("PLANTS"));
        goal.put(new PGCKey(3, 2), new ItemTile("FRAMES"));
        goal.put(new PGCKey(4, 4), new ItemTile("CATS"));
        goal.put(new PGCKey(1, 1), new ItemTile("BOOKS"));
        goal.put(new PGCKey(2, 0), new ItemTile("GAMES"));
        goal.put(new PGCKey(5, 3), new ItemTile("TROPHIES"));
        Goals.add(goal);
        //CARD 12
        goal =new HashMap<>();
        goal.put(new PGCKey(1, 1), new ItemTile("PLANTS"));
        goal.put(new PGCKey(2, 2), new ItemTile("FRAMES"));
        goal.put(new PGCKey(5, 0), new ItemTile("CATS"));
        goal.put(new PGCKey(0, 2), new ItemTile("BOOKS"));
        goal.put(new PGCKey(4, 4), new ItemTile("GAMES"));
        goal.put(new PGCKey(3, 3), new ItemTile("TROPHIES"));
        Goals.add(goal);
    }

    /**
     * @author Eliahu Cohen
     * @return the instance of the class, common for all the classes that invoke the method
     */
    public static synchronized PersonalGoalCardGen getInstance(){
        if(generator==null){
            generator=new PersonalGoalCardGen();
        }
        return generator;
    }

    /**
     * @author Eliahu Cohen
     * @return Hashmap that contains the personal goal card
     */
    public synchronized HashMap<PGCKey,ItemTile> GetGoal(){
      if(Goals.size()==0)generator=new PersonalGoalCardGen();
      int randIndex = new Random().nextInt(Goals.size());
      return generator.getGoals(randIndex);
    }

    /**
     * @author Eliahu Cohen
     * @param randIndex that represent the number of the personal goal card
     * @return the personal goal card
     */
    private HashMap<PGCKey, ItemTile> getGoals(int randIndex) {
        return Goals.remove(randIndex);
    }
}
