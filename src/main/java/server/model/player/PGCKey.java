package server.model.player;

public class PGCKey {
    private final int x;
    private final int y;


    public PGCKey(int row, int column) {
        if(row<=5 && column<=4){
        this.x =row;
        this.y=column;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
