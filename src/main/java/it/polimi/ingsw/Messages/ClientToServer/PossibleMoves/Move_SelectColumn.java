package it.polimi.ingsw.Messages.ClientToServer.PossibleMoves;

import java.util.ArrayList;

/**
 * Move to select the column where to put the tiles
 * @author Eliahu Cohen
 */
public class Move_SelectColumn extends Move {
    private Integer YBookshelf=null;

    public void setYBookshelf(Integer YBookshelf) {
        this.YBookshelf = YBookshelf;
    }

    @Override
    public ArrayList<Integer> getMove() {
        ArrayList<Integer> colonna =new ArrayList<>();
        colonna.add(YBookshelf);
        return colonna;
    }
}
