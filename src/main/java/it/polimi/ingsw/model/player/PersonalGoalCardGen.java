package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.board.ItemTile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *  Generator of the personal goal cards based on singleton pattern
 * @author Eliahu Cohen
 *
 */
public class PersonalGoalCardGen implements Serializable {
    private static PersonalGoalCardGen generator=null;
    private static final ArrayList<PGCkey> Goals = new ArrayList<>();

    private PersonalGoalCardGen() {
        //CARD 1
        HashMap<Pair, ItemTile> goal = new HashMap<>();
        goal.put(new Pair(0, 0), new ItemTile("PLANTS"));
        goal.put(new Pair(0, 2), new ItemTile("FRAMES"));
        goal.put(new Pair(1, 4), new ItemTile("CATS"));
        goal.put(new Pair(2, 3), new ItemTile("BOOKS"));
        goal.put(new Pair(3, 1), new ItemTile("GAMES"));
        goal.put(new Pair(5, 2), new ItemTile("TROPHIES"));

        Goals.add(new PGCkey(1,goal));
        //CARD 2
        goal =new HashMap<>();
        goal.put(new Pair(1, 1), new ItemTile("PLANTS"));
        goal.put(new Pair(5, 4), new ItemTile("FRAMES"));
        goal.put(new Pair(2, 0), new ItemTile("CATS"));
        goal.put(new Pair(3, 4), new ItemTile("BOOKS"));
        goal.put(new Pair(2, 2), new ItemTile("GAMES"));
        goal.put(new Pair(4, 3), new ItemTile("TROPHIES"));
        Goals.add(new PGCkey(2,goal));
        //CARD 3
        goal =new HashMap<>();
        goal.put(new Pair(2, 2), new ItemTile("PLANTS"));
        goal.put(new Pair(1, 0), new ItemTile("FRAMES"));
        goal.put(new Pair(3, 1), new ItemTile("CATS"));
        goal.put(new Pair(5, 0), new ItemTile("BOOKS"));
        goal.put(new Pair(1, 3), new ItemTile("GAMES"));
        goal.put(new Pair(3, 4), new ItemTile("TROPHIES"));
        Goals.add(new PGCkey(3,goal));
        //CARD 4
        goal =new HashMap<>();
        goal.put(new Pair(3, 3), new ItemTile("PLANTS"));
        goal.put(new Pair(2, 2), new ItemTile("FRAMES"));
        goal.put(new Pair(4, 2), new ItemTile("CATS"));
        goal.put(new Pair(4, 1), new ItemTile("BOOKS"));
        goal.put(new Pair(0, 4), new ItemTile("GAMES"));
        goal.put(new Pair(2, 0), new ItemTile("TROPHIES"));
        Goals.add(new PGCkey(4,goal));
        //CARD 5
        goal =new HashMap<>();
        goal.put(new Pair(4, 4), new ItemTile("PLANTS"));
        goal.put(new Pair(3, 1), new ItemTile("FRAMES"));
        goal.put(new Pair(5, 3), new ItemTile("CATS"));
        goal.put(new Pair(3, 2), new ItemTile("BOOKS"));
        goal.put(new Pair(5, 0), new ItemTile("GAMES"));
        goal.put(new Pair(1, 1), new ItemTile("TROPHIES"));
        Goals.add(new PGCkey(5,goal));
        //CARD 6
        goal =new HashMap<>();
        goal.put(new Pair(5, 0), new ItemTile("PLANTS"));
        goal.put(new Pair(4, 3), new ItemTile("FRAMES"));
        goal.put(new Pair(0, 4), new ItemTile("CATS"));
        goal.put(new Pair(2, 3), new ItemTile("BOOKS"));
        goal.put(new Pair(4, 1), new ItemTile("GAMES"));
        goal.put(new Pair(5, 2), new ItemTile("TROPHIES"));
        Goals.add(new PGCkey(6,goal));
        //CARD 7
        goal =new HashMap<>();
        goal.put(new Pair(2, 1), new ItemTile("PLANTS"));
        goal.put(new Pair(1, 4), new ItemTile("FRAMES"));
        goal.put(new Pair(0, 0), new ItemTile("CATS"));
        goal.put(new Pair(5, 2), new ItemTile("BOOKS"));
        goal.put(new Pair(4, 4), new ItemTile("GAMES"));
        goal.put(new Pair(3, 0), new ItemTile("TROPHIES"));
        Goals.add(new PGCkey(7,goal));
        //CARD 8
        goal =new HashMap<>();
        goal.put(new Pair(3, 0), new ItemTile("PLANTS"));
        goal.put(new Pair(0, 4), new ItemTile("FRAMES"));
        goal.put(new Pair(1, 1), new ItemTile("CATS"));
        goal.put(new Pair(4, 3), new ItemTile("BOOKS"));
        goal.put(new Pair(5, 3), new ItemTile("GAMES"));
        goal.put(new Pair(2, 2), new ItemTile("TROPHIES"));
        Goals.add(new PGCkey(8,goal));
        //CARD 9
        goal =new HashMap<>();
        goal.put(new Pair(4, 4), new ItemTile("PLANTS"));
        goal.put(new Pair(5, 0), new ItemTile("FRAMES"));
        goal.put(new Pair(2, 2), new ItemTile("CATS"));
        goal.put(new Pair(3, 4), new ItemTile("BOOKS"));
        goal.put(new Pair(0, 2), new ItemTile("GAMES"));
        goal.put(new Pair(4, 1), new ItemTile("TROPHIES"));
        Goals.add(new PGCkey(9,goal));
        //CARD 10
        goal =new HashMap<>();
        goal.put(new Pair(5, 3), new ItemTile("PLANTS"));
        goal.put(new Pair(4, 1), new ItemTile("FRAMES"));
        goal.put(new Pair(3, 3), new ItemTile("CATS"));
        goal.put(new Pair(2, 0), new ItemTile("BOOKS"));
        goal.put(new Pair(1, 1), new ItemTile("GAMES"));
        goal.put(new Pair(0, 4), new ItemTile("TROPHIES"));
        Goals.add(new PGCkey(10,goal));
        //CARD 11
        goal =new HashMap<>();
        goal.put(new Pair(0, 2), new ItemTile("PLANTS"));
        goal.put(new Pair(3, 2), new ItemTile("FRAMES"));
        goal.put(new Pair(4, 4), new ItemTile("CATS"));
        goal.put(new Pair(1, 1), new ItemTile("BOOKS"));
        goal.put(new Pair(2, 0), new ItemTile("GAMES"));
        goal.put(new Pair(5, 3), new ItemTile("TROPHIES"));
        Goals.add(new PGCkey(11,goal));
        //CARD 12
        goal =new HashMap<>();
        goal.put(new Pair(1, 1), new ItemTile("PLANTS"));
        goal.put(new Pair(2, 2), new ItemTile("FRAMES"));
        goal.put(new Pair(5, 0), new ItemTile("CATS"));
        goal.put(new Pair(0, 2), new ItemTile("BOOKS"));
        goal.put(new Pair(4, 4), new ItemTile("GAMES"));
        goal.put(new Pair(3, 3), new ItemTile("TROPHIES"));
        Goals.add(new PGCkey(12,goal));
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
    public synchronized PGCkey GetGoal(){
      if(Goals.size()==0)generator=new PersonalGoalCardGen();
      int randIndex = new Random().nextInt(Goals.size());
      return generator.getGoals(randIndex);
    }

    /**
     * @author Eliahu Cohen
     * @param randIndex that represent the number of the personal goal card
     * @return the personal goal card
     */
    private PGCkey getGoals(int randIndex) {
        return Goals.remove(randIndex);
    }
}
