package it.polimi.ingsw.model.board.goalCards;

import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.player.BookShelf;

public class CommonGoalCard2 extends CommonGoalCard implements CheckCommonGoalCard {

    public CommonGoalCard2(Integer I){
        super(I);
    }

    /**
     * @author Eliahu Cohen and Giovanni di Lorenzo
     * @param bs bookshelf of the player to check the goal
     */
    public void checkGoal(BookShelf bs) {
        if (NoItemsNull(bs)) {
            if ((bs.getTile(0, 0).getCategory() == bs.getTile(0, 4).getCategory()
                    && (bs.getTile(0, 0).getCategory() == bs.getTile(5, 4).getCategory())
                    && (bs.getTile(0, 0).getCategory() == bs.getTile(5, 0).getCategory())))
                increaseNumCompleted();
        }
    }

    /**
     * @author Giovanni Di Lorenzo
     * @param bs Player's bookshelf
     * @return this method checks if the itemtiles on upper right, upper left, lower right and lower left corners are not
     * null
     */
    private boolean NoItemsNull(BookShelf bs){
        if((bs.getTile(0,0)!=null) && (bs.getTile(0,4)!=null) &&
                (bs.getTile(5,0)!=null) && (bs.getTile(5,4) != null))
            return true;
        else return false;
    }

    @Override
    public void print(){
        System.out.print("FOUR GROUPS FORMED OF FOUR TILES.\n\n");

        BookShelf example= new BookShelf();
        ItemTile green =new ItemTile("CATS");
        ItemTile white =new ItemTile("BOOKS");
        ItemTile yellow =new ItemTile("GAMES");
        ItemTile blue =new ItemTile("FRAMES");
        ItemTile cyan =new ItemTile("TROPHIES");
        ItemTile pink =new ItemTile("PLANTS");
        example.setTile(0,0, green);
        example.setTile(1,0, green);
        example.setTile(2,0, green);
        example.setTile(3,0, green);
        example.setTile(5,0, yellow);
        example.setTile(5,1, yellow);
        example.setTile(5,2, yellow);
        example.setTile(4,1, yellow);
        example.setTile(0,2, pink);
        example.setTile(0,3, pink);
        example.setTile(0,4, pink);
        example.setTile(1,4, pink);
        example.setTile(5,4, white);
        example.setTile(4,4, white);
        example.setTile(4,3, white);
        example.setTile(3,3, white);

        example.printCGC();

        System.out.print("\nDESCRIPTION: Four groups each containing at least 4 tiles of the same type.\n" +
                "The tiles of one group can be different from those of another group.\n");
        System.out.print("POINTS:" + getPoints() + "\n\n");
    }
}