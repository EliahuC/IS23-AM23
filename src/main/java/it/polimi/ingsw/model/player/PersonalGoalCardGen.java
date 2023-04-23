package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.board.ItemTile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class PersonalGoalCardGen {
    private HashMap<PGCKey, ItemTile> Goal = new HashMap<>();
    private final ArrayList<HashMap<PGCKey, ItemTile>> Goals = new ArrayList<>();

    public PersonalGoalCardGen() {
        //CARTA 1
        Goal.put(new PGCKey(0, 0), new ItemTile("PLANTS"));
        Goal.put(new PGCKey(0, 2), new ItemTile("FRAMES"));
        Goal.put(new PGCKey(1, 4), new ItemTile("CATS"));
        Goal.put(new PGCKey(2, 3), new ItemTile("BOOKS"));
        Goal.put(new PGCKey(3, 1), new ItemTile("GAMES"));
        Goal.put(new PGCKey(5, 2), new ItemTile("TROPHIES"));
        Goals.add(Goal);
        //CARTA 2
        Goal=new HashMap<>();
        Goal.put(new PGCKey(1, 1), new ItemTile("PLANTS"));
        Goal.put(new PGCKey(5, 4), new ItemTile("FRAMES"));
        Goal.put(new PGCKey(2, 0), new ItemTile("CATS"));
        Goal.put(new PGCKey(3, 4), new ItemTile("BOOKS"));
        Goal.put(new PGCKey(2, 2), new ItemTile("GAMES"));
        Goal.put(new PGCKey(4, 3), new ItemTile("TROPHIES"));
        Goals.add(Goal);
        //CARTA 3
        Goal=new HashMap<>();
        Goal.put(new PGCKey(2, 2), new ItemTile("PLANTS"));
        Goal.put(new PGCKey(1, 0), new ItemTile("FRAMES"));
        Goal.put(new PGCKey(3, 1), new ItemTile("CATS"));
        Goal.put(new PGCKey(5, 0), new ItemTile("BOOKS"));
        Goal.put(new PGCKey(1, 3), new ItemTile("GAMES"));
        Goal.put(new PGCKey(3, 4), new ItemTile("TROPHIES"));
        Goals.add(Goal);
        //CARTA 4
        Goal=new HashMap<>();
        Goal.put(new PGCKey(3, 3), new ItemTile("PLANTS"));
        Goal.put(new PGCKey(2, 2), new ItemTile("FRAMES"));
        Goal.put(new PGCKey(4, 2), new ItemTile("CATS"));
        Goal.put(new PGCKey(4, 1), new ItemTile("BOOKS"));
        Goal.put(new PGCKey(0, 4), new ItemTile("GAMES"));
        Goal.put(new PGCKey(2, 0), new ItemTile("TROPHIES"));
        Goals.add(Goal);
        //CARTA 5
        Goal=new HashMap<>();
        Goal.put(new PGCKey(4, 4), new ItemTile("PLANTS"));
        Goal.put(new PGCKey(3, 1), new ItemTile("FRAMES"));
        Goal.put(new PGCKey(5, 3), new ItemTile("CATS"));
        Goal.put(new PGCKey(3, 2), new ItemTile("BOOKS"));
        Goal.put(new PGCKey(5, 0), new ItemTile("GAMES"));
        Goal.put(new PGCKey(1, 1), new ItemTile("TROPHIES"));
        Goals.add(Goal);
        //CARTA 6
        Goal=new HashMap<>();
        Goal.put(new PGCKey(5, 0), new ItemTile("PLANTS"));
        Goal.put(new PGCKey(4, 3), new ItemTile("FRAMES"));
        Goal.put(new PGCKey(0, 4), new ItemTile("CATS"));
        Goal.put(new PGCKey(2, 3), new ItemTile("BOOKS"));
        Goal.put(new PGCKey(4, 1), new ItemTile("GAMES"));
        Goal.put(new PGCKey(5, 2), new ItemTile("TROPHIES"));
        Goals.add(Goal);
        //CARTA 7
        Goal=new HashMap<>();
        Goal.put(new PGCKey(2, 1), new ItemTile("PLANTS"));
        Goal.put(new PGCKey(1, 4), new ItemTile("FRAMES"));
        Goal.put(new PGCKey(0, 0), new ItemTile("CATS"));
        Goal.put(new PGCKey(5, 2), new ItemTile("BOOKS"));
        Goal.put(new PGCKey(4, 4), new ItemTile("GAMES"));
        Goal.put(new PGCKey(3, 0), new ItemTile("TROPHIES"));
        Goals.add(Goal);
        //CARTA 8
        Goal=new HashMap<>();
        Goal.put(new PGCKey(3, 0), new ItemTile("PLANTS"));
        Goal.put(new PGCKey(0, 4), new ItemTile("FRAMES"));
        Goal.put(new PGCKey(1, 1), new ItemTile("CATS"));
        Goal.put(new PGCKey(4, 3), new ItemTile("BOOKS"));
        Goal.put(new PGCKey(5, 3), new ItemTile("GAMES"));
        Goal.put(new PGCKey(2, 2), new ItemTile("TROPHIES"));
        Goals.add(Goal);
        //CARTA 9
        Goal=new HashMap<>();
        Goal.put(new PGCKey(4, 4), new ItemTile("PLANTS"));
        Goal.put(new PGCKey(5, 0), new ItemTile("FRAMES"));
        Goal.put(new PGCKey(2, 2), new ItemTile("CATS"));
        Goal.put(new PGCKey(3, 4), new ItemTile("BOOKS"));
        Goal.put(new PGCKey(0, 2), new ItemTile("GAMES"));
        Goal.put(new PGCKey(4, 1), new ItemTile("TROPHIES"));
        Goals.add(Goal);
        //CARTA 10
        Goal=new HashMap<>();
        Goal.put(new PGCKey(5, 3), new ItemTile("PLANTS"));
        Goal.put(new PGCKey(4, 1), new ItemTile("FRAMES"));
        Goal.put(new PGCKey(3, 3), new ItemTile("CATS"));
        Goal.put(new PGCKey(2, 0), new ItemTile("BOOKS"));
        Goal.put(new PGCKey(1, 1), new ItemTile("GAMES"));
        Goal.put(new PGCKey(0, 4), new ItemTile("TROPHIES"));
        Goals.add(Goal);
        //CARTA 11
        Goal=new HashMap<>();
        Goal.put(new PGCKey(0, 2), new ItemTile("PLANTS"));
        Goal.put(new PGCKey(3, 2), new ItemTile("FRAMES"));
        Goal.put(new PGCKey(4, 4), new ItemTile("CATS"));
        Goal.put(new PGCKey(1, 1), new ItemTile("BOOKS"));
        Goal.put(new PGCKey(2, 0), new ItemTile("GAMES"));
        Goal.put(new PGCKey(5, 3), new ItemTile("TROPHIES"));
        Goals.add(Goal);
        //CARTA 12
        Goal=new HashMap<>();
        Goal.put(new PGCKey(1, 1), new ItemTile("PLANTS"));
        Goal.put(new PGCKey(2, 2), new ItemTile("FRAMES"));
        Goal.put(new PGCKey(5, 0), new ItemTile("CATS"));
        Goal.put(new PGCKey(0, 2), new ItemTile("BOOKS"));
        Goal.put(new PGCKey(4, 4), new ItemTile("GAMES"));
        Goal.put(new PGCKey(3, 3), new ItemTile("TROPHIES"));
        Goals.add(Goal);
    }
    public HashMap<PGCKey,ItemTile> GetGoal(){
        int randIndex = new Random().nextInt(Goals.size());
        return Goals.get(randIndex);
    }
}
