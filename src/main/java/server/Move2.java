package server;

import java.util.ArrayList;

public class Move2 extends Move{
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
