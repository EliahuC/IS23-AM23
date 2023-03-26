package server.model.board;

public class LivingRoomSpace {

private ItemTile tile;

    public ItemTile getTile() {
        return tile;
    }

    public ItemTile.itemTileCategory getEnum(){
        return tile.getCategory();
    }



}
