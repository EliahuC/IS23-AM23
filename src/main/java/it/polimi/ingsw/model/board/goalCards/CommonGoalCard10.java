package it.polimi.ingsw.model.board.goalCards;

import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.board.ItemTileCategory;
import it.polimi.ingsw.model.player.BookShelf;

import java.util.HashSet;

/**
 * @author Giovanni Di Lorenzo
 */

public class CommonGoalCard10 extends CommonGoalCard implements CheckCommonGoalCard {

    private final static int numRowsToAchieve=2;
    private final HashSet<ItemTileCategory> cat = new HashSet<>(); //I use an arrayList to track the categories in each row
    //of the bookshelf


    public CommonGoalCard10(Integer I){
        super(I);
    }
    /**
     *@param bs Player's bookshelf
     * The method below checks each bookshelf's row, until two rows comply with the specifics
     */
    @Override
    public void checkGoal(BookShelf bs) {
        int counter=0;
        for(int i=0; i< BookShelf.getMAX_Row() && counter<numRowsToAchieve; i++) {
            if (bs.getTile(i, 0) != null) {
                cat.add(bs.getTile(i, 0).getCategory());
                if (!SameCategory(bs, i, cat))
                    counter++;
                cat.clear();
            }
        }
        if (counter>=numRowsToAchieve)
            increaseNumCompleted();
    }
    /**
     *@param bs,a,cat Player's bookshelf, row index and HashSet in order to count the number of index a
     *                row categories
     * The method below checks if in the row with index a there are not two itemtiles with the same category
     */
    public boolean SameCategory(BookShelf bs,int a, HashSet<ItemTileCategory> category){
        for(int j=1;j< BookShelf.getMAX_Column();j++) {
            if (bs.getTile(a, j) == null)
                return true;
            else {
                if (category.contains(bs.getTile(a, j).getCategory()))
                    return true;
                else
                    cat.add(bs.getTile(a, j).getCategory());
            }
        }
        return false;
    }

    public HashSet<ItemTileCategory> getCat(){
        return cat;
    }
    @Override
    public void print(){
        System.out.print("TWO ROWS WITH DIFFERENT TILES.\n\n");

        BookShelf example= new BookShelf();
        ItemTile green =new ItemTile("CATS");
        ItemTile white =new ItemTile("BOOKS");
        ItemTile yellow =new ItemTile("GAMES");
        ItemTile blue =new ItemTile("FRAMES");
        ItemTile cyan =new ItemTile("TROPHIES");
        ItemTile pink =new ItemTile("PLANTS");
        example.setTile(5,0, pink);
        example.setTile(5,1, white);
        example.setTile(5,2, yellow);
        example.setTile(5,3, green);
        example.setTile(5,4, blue);
        example.setTile(2,0, cyan);
        example.setTile(2,1, blue);
        example.setTile(2,2, white);
        example.setTile(2,3, yellow);
        example.setTile(2,4, green);

        example.printCGC();

        System.out.print("\nDESCRIPTION: Two lines each formed by 5 different types of tiles." +
                "One line can show the same or a different combination of the other line.\n");
        System.out.print("POINTS:" + getPoints() + "\n\n");
    }
}

