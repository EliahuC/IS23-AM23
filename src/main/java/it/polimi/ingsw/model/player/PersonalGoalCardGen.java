package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.board.ItemTile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class PersonalGoalCardGen {
    private static PersonalGoalCardGen generator=null;
    private static final ArrayList<HashMap<PGCKey, ItemTile>> Goals = new ArrayList<>();

    private PersonalGoalCardGen() {
        //CARTA 1
        HashMap<PGCKey, ItemTile> goal = new HashMap<>();
        goal.put(new PGCKey(0, 0), new ItemTile("PLANTS"));
        goal.put(new PGCKey(0, 2), new ItemTile("FRAMES"));
        goal.put(new PGCKey(1, 4), new ItemTile("CATS"));
        goal.put(new PGCKey(2, 3), new ItemTile("BOOKS"));
        goal.put(new PGCKey(3, 1), new ItemTile("GAMES"));
        goal.put(new PGCKey(5, 2), new ItemTile("TROPHIES"));
        Goals.add(goal);
        //CARTA 2
        goal =new HashMap<>();
        goal.put(new PGCKey(1, 1), new ItemTile("PLANTS"));
        goal.put(new PGCKey(5, 4), new ItemTile("FRAMES"));
        goal.put(new PGCKey(2, 0), new ItemTile("CATS"));
        goal.put(new PGCKey(3, 4), new ItemTile("BOOKS"));
        goal.put(new PGCKey(2, 2), new ItemTile("GAMES"));
        goal.put(new PGCKey(4, 3), new ItemTile("TROPHIES"));
        Goals.add(goal);
        //CARTA 3
        goal =new HashMap<>();
        goal.put(new PGCKey(2, 2), new ItemTile("PLANTS"));
        goal.put(new PGCKey(1, 0), new ItemTile("FRAMES"));
        goal.put(new PGCKey(3, 1), new ItemTile("CATS"));
        goal.put(new PGCKey(5, 0), new ItemTile("BOOKS"));
        goal.put(new PGCKey(1, 3), new ItemTile("GAMES"));
        goal.put(new PGCKey(3, 4), new ItemTile("TROPHIES"));
        Goals.add(goal);
        //CARTA 4
        goal =new HashMap<>();
        goal.put(new PGCKey(3, 3), new ItemTile("PLANTS"));
        goal.put(new PGCKey(2, 2), new ItemTile("FRAMES"));
        goal.put(new PGCKey(4, 2), new ItemTile("CATS"));
        goal.put(new PGCKey(4, 1), new ItemTile("BOOKS"));
        goal.put(new PGCKey(0, 4), new ItemTile("GAMES"));
        goal.put(new PGCKey(2, 0), new ItemTile("TROPHIES"));
        Goals.add(goal);
        //CARTA 5
        goal =new HashMap<>();
        goal.put(new PGCKey(4, 4), new ItemTile("PLANTS"));
        goal.put(new PGCKey(3, 1), new ItemTile("FRAMES"));
        goal.put(new PGCKey(5, 3), new ItemTile("CATS"));
        goal.put(new PGCKey(3, 2), new ItemTile("BOOKS"));
        goal.put(new PGCKey(5, 0), new ItemTile("GAMES"));
        goal.put(new PGCKey(1, 1), new ItemTile("TROPHIES"));
        Goals.add(goal);
        //CARTA 6
        goal =new HashMap<>();
        goal.put(new PGCKey(5, 0), new ItemTile("PLANTS"));
        goal.put(new PGCKey(4, 3), new ItemTile("FRAMES"));
        goal.put(new PGCKey(0, 4), new ItemTile("CATS"));
        goal.put(new PGCKey(2, 3), new ItemTile("BOOKS"));
        goal.put(new PGCKey(4, 1), new ItemTile("GAMES"));
        goal.put(new PGCKey(5, 2), new ItemTile("TROPHIES"));
        Goals.add(goal);
        //CARTA 7
        goal =new HashMap<>();
        goal.put(new PGCKey(2, 1), new ItemTile("PLANTS"));
        goal.put(new PGCKey(1, 4), new ItemTile("FRAMES"));
        goal.put(new PGCKey(0, 0), new ItemTile("CATS"));
        goal.put(new PGCKey(5, 2), new ItemTile("BOOKS"));
        goal.put(new PGCKey(4, 4), new ItemTile("GAMES"));
        goal.put(new PGCKey(3, 0), new ItemTile("TROPHIES"));
        Goals.add(goal);
        //CARTA 8
        goal =new HashMap<>();
        goal.put(new PGCKey(3, 0), new ItemTile("PLANTS"));
        goal.put(new PGCKey(0, 4), new ItemTile("FRAMES"));
        goal.put(new PGCKey(1, 1), new ItemTile("CATS"));
        goal.put(new PGCKey(4, 3), new ItemTile("BOOKS"));
        goal.put(new PGCKey(5, 3), new ItemTile("GAMES"));
        goal.put(new PGCKey(2, 2), new ItemTile("TROPHIES"));
        Goals.add(goal);
        //CARTA 9
        goal =new HashMap<>();
        goal.put(new PGCKey(4, 4), new ItemTile("PLANTS"));
        goal.put(new PGCKey(5, 0), new ItemTile("FRAMES"));
        goal.put(new PGCKey(2, 2), new ItemTile("CATS"));
        goal.put(new PGCKey(3, 4), new ItemTile("BOOKS"));
        goal.put(new PGCKey(0, 2), new ItemTile("GAMES"));
        goal.put(new PGCKey(4, 1), new ItemTile("TROPHIES"));
        Goals.add(goal);
        //CARTA 10
        goal =new HashMap<>();
        goal.put(new PGCKey(5, 3), new ItemTile("PLANTS"));
        goal.put(new PGCKey(4, 1), new ItemTile("FRAMES"));
        goal.put(new PGCKey(3, 3), new ItemTile("CATS"));
        goal.put(new PGCKey(2, 0), new ItemTile("BOOKS"));
        goal.put(new PGCKey(1, 1), new ItemTile("GAMES"));
        goal.put(new PGCKey(0, 4), new ItemTile("TROPHIES"));
        Goals.add(goal);
        //CARTA 11
        goal =new HashMap<>();
        goal.put(new PGCKey(0, 2), new ItemTile("PLANTS"));
        goal.put(new PGCKey(3, 2), new ItemTile("FRAMES"));
        goal.put(new PGCKey(4, 4), new ItemTile("CATS"));
        goal.put(new PGCKey(1, 1), new ItemTile("BOOKS"));
        goal.put(new PGCKey(2, 0), new ItemTile("GAMES"));
        goal.put(new PGCKey(5, 3), new ItemTile("TROPHIES"));
        Goals.add(goal);
        //CARTA 12
        goal =new HashMap<>();
        goal.put(new PGCKey(1, 1), new ItemTile("PLANTS"));
        goal.put(new PGCKey(2, 2), new ItemTile("FRAMES"));
        goal.put(new PGCKey(5, 0), new ItemTile("CATS"));
        goal.put(new PGCKey(0, 2), new ItemTile("BOOKS"));
        goal.put(new PGCKey(4, 4), new ItemTile("GAMES"));
        goal.put(new PGCKey(3, 3), new ItemTile("TROPHIES"));
        Goals.add(goal);
    }
    public static HashMap<PGCKey,ItemTile> GetGoal(){
        if(generator==null){
            generator=new PersonalGoalCardGen();
        }
        int randIndex = new Random().nextInt(Goals.size());
        return generator.getGoals(randIndex);
    }

    private HashMap<PGCKey, ItemTile> getGoals(int randIndex) {
        return Goals.remove(randIndex);
    }
}
