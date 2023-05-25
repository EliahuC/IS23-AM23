package it.polimi.ingsw.model.board.goalCards;

import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.player.BookShelf;

import java.util.ArrayList;
import java.util.List;

public class CommonGoalCard1 extends CommonGoalCard implements CheckCommonGoalCard {
    private final List<ItemTile> validGroups = new ArrayList<>();

    private final static int Goal = 6;

    public CommonGoalCard1(int numPlayers) {
        super(numPlayers);
    }

    /**
     * @author Giovanni Di Lorenzo
     * @param bs Player's bookshelf
     * this method checks if a player achieved the CommonGoalCard1 goal visiting the entire BookShelf
     */
    @Override
    //Six groups of 2 adjacent tiles.
    public void checkGoal(BookShelf bs) {
        int count = 0;
        ItemTile it;
        int i = 0;
        while (bs.getUsedTiles().size() < bs.NumTiles()) {
            bs.SetFirstTile();
            do {
                it = bs.UpperTile(bs.getTileRow(), bs.getTileColumn());
                if (!bs.AlreadyUsed(it) && it != null && bs.CheckCategory(it)) {
                    bs.getUsedTiles().add(it);
                    validGroups.add(it);

                }
                it = bs.LowerTile(bs.getTileRow(), bs.getTileColumn());
                if (!bs.AlreadyUsed(it) && it != null && bs.CheckCategory(it)) {
                    bs.getUsedTiles().add(it);
                    validGroups.add(it);
                }
                it = bs.RightTile(bs.getTileRow(), bs.getTileColumn());
                if (!bs.AlreadyUsed(it) && it != null && bs.CheckCategory(it)) {
                    bs.getUsedTiles().add(it);
                    validGroups.add(it);
                }
                it = bs.LeftTile(bs.getTileRow(), bs.getTileColumn());
                if (!bs.AlreadyUsed(it) && it != null && bs.CheckCategory(it)) {
                    bs.getUsedTiles().add(it);
                    validGroups.add(it);
                }
                if (i < bs.getUsedTiles().size() - 1) {
                    bs.SetLocation(bs.getUsedTiles().get(i + 1));
                    i++;
                } else {
                    i++;
                    break;
                }
            } while (bs.getUsedTiles().iterator().hasNext());
            if(validGroups.size()==1)
                count++;
            validGroups.clear();
        }
        if(count>=Goal)
            increaseNumCompleted();
    }
    @Override
    public void print(){
        System.out.print("SIX GROUPS FORMED OF TWO TILES.\n\n");

        /*BookShelf example= new BookShelf();
        ItemTile green =new ItemTile("CATS");
        ItemTile white =new ItemTile("BOOKS");
        ItemTile yellow =new ItemTile("GAMES");
        ItemTile blue =new ItemTile("FRAMES");
        ItemTile cyan =new ItemTile("TROPHIES");
        ItemTile pink =new ItemTile("PLANTS");
        example.setTile(5,0, blue);
        example.setTile(4,0, blue);
        example.setTile(3,1, blue);
        example.setTile(3,2, blue);
        example.setTile(5,2, cyan);
        example.setTile(5,3, cyan);
        example.setTile(3,4, green);
        example.setTile(2,4, green);
        example.setTile(0,2, yellow);
        example.setTile(0,3, yellow);
        example.setTile(0,0, white);
        example.setTile(1,0, white);

        example.printCGC();*/

        System.out.print("\nDESCRIPTION: Six groups each containing at least 2 tiles of the same type.\n" +
                "The tiles of one group can be different from those of another group.\n");
        System.out.print("POINTS:" + getPoints() + "\n\n");
    }
}