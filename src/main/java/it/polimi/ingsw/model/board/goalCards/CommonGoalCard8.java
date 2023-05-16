package it.polimi.ingsw.model.board.goalCards;

import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.board.ItemTileCategory;
import it.polimi.ingsw.model.player.BookShelf;

import java.util.HashSet;

/**
 * @author Giovanni Di Lorenzo
 */
public class CommonGoalCard8 extends CommonGoalCard implements CheckCommonGoalCard {
    private final static int numRowsToAchieve=4;
    private static final int numDifferentCategoriesAllowed=3;
    private final HashSet<ItemTileCategory> cat = new HashSet<>(); //I use a hashset to track the categories in each row
    private transient final Launcher L;                                               //of the bookshelf;

    public CommonGoalCard8(Launcher L) {
        this.L = L;
    }
    /**
     *@param bs Player's bookshelf
     * The method below checks each bookshelf's row, until four rows comply with the specifics
     */

    @Override
    public void checkGoal(BookShelf bs) {
        int counter=0;
        for(int i=0; i< BookShelf.getMAX_Row() && counter<numRowsToAchieve; i++) {
            if (bs.getTile(i,0) != null) {
                cat.add(bs.getTile(i, 0).getCategory());
                if (CategoriesAllowed(bs, i, cat) <= numDifferentCategoriesAllowed)
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
     * The method below count the categories of the row with index a
     */

    public int CategoriesAllowed(BookShelf bs, int a, HashSet<ItemTileCategory> cat){
        for(int j=0; j< BookShelf.getMAX_Column() && cat.size()<=numDifferentCategoriesAllowed; j++){
            if(bs.getTile(a,j)==null) {
                return 7;
            }else cat.add(bs.getTile(a,j).getCategory());
        }
        return cat.size();
    }

    public HashSet<ItemTileCategory> getCat(){
        return cat;
    }

    public void print(){
        System.out.print("FOUR FULL ROWS.\n\n");

        BookShelf example= new BookShelf();
        ItemTile green =new ItemTile("CATS");
        ItemTile white =new ItemTile("BOOKS");
        ItemTile yellow =new ItemTile("GAMES");
        ItemTile blue =new ItemTile("FRAMES");
        ItemTile cyan =new ItemTile("TROPHIES");
        ItemTile pink =new ItemTile("PLANTS");
        example.setTile(5,0, pink);
        example.setTile(5,1, pink);
        example.setTile(5,2, pink);
        example.setTile(5,3, pink);
        example.setTile(5,4, pink);
        example.setTile(3,0, blue);
        example.setTile(3,1, cyan);
        example.setTile(3,2, cyan);
        example.setTile(3,3, blue);
        example.setTile(3,4, cyan);
        example.setTile(2,0, yellow);
        example.setTile(2,1, yellow);
        example.setTile(2,2, white);
        example.setTile(2,3, yellow);
        example.setTile(2,4, green);
        example.setTile(0,0, cyan);
        example.setTile(0,1, green);
        example.setTile(0,2, green);
        example.setTile(0,3, cyan);
        example.setTile(0,4, cyan);

        example.printCGC();

        System.out.print("\nDESCRIPTION: Four lines each formed by 5 tiles of maximum three different types.\n" +
                "One line can show the same or a different combination of another line.\n");
        System.out.print("POINTS:" + getPoints() + "\n\n");
    }
}


