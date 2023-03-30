package server.model.board;


public class boardToken {
    private ItemTile Tile ;
    private boardTokenCategory category;

    public boardToken() {
        this.Tile = null;
        this.category=boardTokenCategory.NORMAL;
    }

    public boardTokenCategory getCategory() {
        return category;
    }

    public void setCategory(boardTokenCategory category) {
        this.category = category;
    }

    public ItemTile getTile() {
        return Tile;
    }

    public void setTile(ItemTile tile) {
        Tile = tile;
    }

    enum boardTokenCategory {
        NORMAL,
        THREE,
        FOUR,
        UNAVAILABLE
    }
}