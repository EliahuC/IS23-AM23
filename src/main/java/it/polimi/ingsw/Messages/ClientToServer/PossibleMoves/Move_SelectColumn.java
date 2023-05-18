package it.polimi.ingsw.Messages.ClientToServer.PossibleMoves;

import java.util.ArrayList;

/**
 * @author Eliahu Cohen
 * Move to select the column where to put the tiles
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
