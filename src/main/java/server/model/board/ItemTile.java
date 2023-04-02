package server.model.board;


import java.util.Random;

public class ItemTile {


    private itemTileCategory category=null ;
    private boolean visited;

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public ItemTile(){
        itemTileCategory[] values = itemTileCategory.values();
        int length = values.length;
        int randIndex = new Random().nextInt(length);
        this.category=values[randIndex];
    }
    public ItemTile(String x) {
        switch(x){
            case "CATS":
                this.category=itemTileCategory.CATS;
                break;

            case "FRAMES":
                this.category=itemTileCategory.FRAMES;
                break;
            case "BOOKS":
                this.category=itemTileCategory.BOOKS;
                break;
            case "GAMES":
                this.category=itemTileCategory.GAMES;
                break;
            case "PLANTS":
                this.category=itemTileCategory.PLANTS;
                break;
            case "TROPHIES":
                this.category=itemTileCategory.TROPHIES;
                break;
        }
    }

    public itemTileCategory getCategory() {
        return category;
    }
}
