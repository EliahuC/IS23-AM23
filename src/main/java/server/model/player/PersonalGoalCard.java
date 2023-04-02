package server.model.player;

import server.model.board.ItemTile;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class PersonalGoalCard {
    private int completed;
    private int points;
    private final int NumeroCarta;
    private HashMap<PGCKey, ItemTile> Goal = new HashMap<>();
    private PersonalGoalCardGen Gen=new PersonalGoalCardGen();


    //METODO SWITCH
    public PersonalGoalCard(int x) {
        this.NumeroCarta = x;
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
            this.NumeroCarta=0;
            Goal=Gen.GetGoal();
            this.completed = 0;
            this.points=0;


    }


    public int CheckGoal(BookShelf playerBS) {
        for (PGCKey key : Goal.keySet()) {
            if (playerBS.getTile(key.getX(), key.getY()).getCategory() == Goal.get(key).getCategory()) {
                completed++;
            }

        }
        return completed;
    }

    public int GivePoints() {

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
}