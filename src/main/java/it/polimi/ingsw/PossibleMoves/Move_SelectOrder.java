package it.polimi.ingsw.PossibleMoves;

import it.polimi.ingsw.model.board.ItemTileCategory;

import java.util.ArrayList;

public class Move_SelectOrder extends Move {

    //CLASSE DA DECIDERE, FORSE SI PUO FARE A MENO

    private ItemTileCategory colore1=null,colore2=null,colore3=null;




    public ItemTileCategory getColore1() {
        return colore1;
    }

    public void setColore1(ItemTileCategory colore1) {
        this.colore1 = colore1;
    }

    public ItemTileCategory getColore2() {
        return colore2;
    }


    public void setColore2(ItemTileCategory colore2) {
        this.colore2 = colore2;

    }
    public ItemTileCategory getColore3() {
        return colore3;
    }

    public void setColore3(ItemTileCategory colore3) {
        this.colore3 = colore3;
    }

    @Override
    public ArrayList<Integer> getMove() {
        return null;
    }
}
