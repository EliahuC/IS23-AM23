package server;

import server.model.board.ItemTileCategory;

public class Move {
    private Integer Xmossa1=null,Xmossa2=null,Xmossa3=null,Ymossa1=null,Ymossa2=null,Ymossa3=null;
    private Integer YBookshelf=null;
    private ItemTileCategory colore1=null,colore2=null,colore3=null;

    public Integer getXmossa1() {
        return Xmossa1;
    }

    public void setXmossa1(Integer xmossa1) {
        Xmossa1 = xmossa1;
    }

    public Integer getXmossa2() {
        return Xmossa2;
    }

    public void setXmossa2(Integer xmossa2) {
        Xmossa2 = xmossa2;
    }

    public Integer getXmossa3() {
        return Xmossa3;
    }

    public void setXmossa3(Integer xmossa3) {
        Xmossa3 = xmossa3;
    }

    public Integer getYmossa1() {
        return Ymossa1;
    }

    public void setYmossa1(Integer ymossa1) {
        Ymossa1 = ymossa1;
    }

    public Integer getYmossa2() {
        return Ymossa2;
    }

    public void setYmossa2(Integer ymossa2) {
        Ymossa2 = ymossa2;
    }

    public Integer getYmossa3() {
        return Ymossa3;
    }
    public void setYmossa3(Integer ymossa3) {
        Ymossa3 = ymossa3;

    }
    public Integer getYBookshelf() {
        return YBookshelf;
    }

    public void setYBookshelf(Integer YBookshelf) {
        this.YBookshelf = YBookshelf;
    }

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

}
