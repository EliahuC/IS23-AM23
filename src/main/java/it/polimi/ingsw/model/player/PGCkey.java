package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.board.ItemTile;

import java.util.HashMap;

/**
 * Class that rapresent the pait < Pair , Goal >
 * @author Eliahu Cohen
 *
 */
public class PGCkey {
    private final Integer id;
    private final HashMap<Pair, ItemTile> goal;

    public PGCkey(Integer id,HashMap<Pair,ItemTile> goal) {
        this.id = id;
        this.goal=goal;
    }

    public Integer getId() {
        return id;
    }

    public HashMap<Pair, ItemTile> getGoal() {
        return goal;
    }
}
