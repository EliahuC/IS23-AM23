package server.model.board;

public class LivingRoomSpace {

private ItemTile tile;

    public ItemTile getTile() {
        return tile;
    }

    public itemTileCategory getEnum(){
        return tile.getCategory();
    }



}
