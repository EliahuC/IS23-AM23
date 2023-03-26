package server.model.board;


import java.util.Random;

public class ItemTile {
     enum itemTileCategory {
        CATS,
        BOOKS,
        GAMES,
        FRAMES,
        TROPHIES,
        PLANTS;
    }

    private final itemTileCategory category ;

    public ItemTile(){
        itemTileCategory[] values = itemTileCategory.values();
        int length = values.length;
        int randIndex = new Random().nextInt(length);
        category=values[randIndex];
    }

    public itemTileCategory getCategory() {
        return category;
    }
}
