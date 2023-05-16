package it.polimi.ingsw.model.board.goalCards;

import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.board.ItemTileCategory;
import it.polimi.ingsw.model.player.BookShelf;

import java.util.HashSet;

/**
 * @author Giovanni Di Lorenzo
 */
public class CommonGoalCard5 extends CommonGoalCard implements CheckCommonGoalCard {
    private final static int numColumnsToAchieve = 3;
    private final static int numDifferentCategoriesAllowed = 3;
    private final HashSet<ItemTileCategory> cat = new HashSet<>(); //I use an hashset to track the categories in each column
    private final Launcher L;                                       //of the bookshelf

    public CommonGoalCard5(Launcher L){
        this.L = L;

    }
    /**
     *@param bs Player's bookshelf
     * The method below checks each bookshelf's column, until three columns comply with the specifics
     */
    @Override
    public void checkGoal(BookShelf bs) {
        int counter = 0;
        for (int j = 0; j < BookShelf.getMAX_Column() && counter < numColumnsToAchieve; j++) {
            if (bs.getTile(0, j) != null) {
                cat.add(bs.getTile(0, j).getCategory());
                if (CategoriesAllowed(bs, j, cat) <= numDifferentCategoriesAllowed)
                    counter++;
                cat.clear();
            }
        }
        if (counter >= numColumnsToAchieve)
            increaseNumCompleted();
    }

    public HashSet<ItemTileCategory> getCat() {
        return cat;
    }
    /**
     *@param bs,a,cat Player's bookshelf, column index and HashSet in order to count the number of index a
     *                column categories
     * The method below count the categories of the column with index a
     */
    public int CategoriesAllowed(BookShelf bs, int a, HashSet<ItemTileCategory> cat) {
        for (int i = 0; i < BookShelf.getMAX_Row() && cat.size() <= numDifferentCategoriesAllowed; i++) {
            if(bs.getTile(i,a)==null) {
                return 7;
            }else cat.add(bs.getTile(i, a).getCategory());
        }
        return cat.size();
    }

    public void print(){
        System.out.print("THREE FULL COLUMNS.\n\n");

        BookShelf example= new BookShelf();
        ItemTile green =new ItemTile("CATS");
        ItemTile white =new ItemTile("BOOKS");
        ItemTile yellow =new ItemTile("GAMES");
        ItemTile blue =new ItemTile("FRAMES");
        ItemTile cyan =new ItemTile("TROPHIES");
        ItemTile pink =new ItemTile("PLANTS");
        example.setTile(5,0, cyan);
        example.setTile(4,0, cyan);
        example.setTile(3,0, cyan);
        example.setTile(2,0, cyan);
        example.setTile(1,0, cyan);
        example.setTile(0,0, cyan);
        example.setTile(5,2, green);
        example.setTile(4,2, green);
        example.setTile(3,2, yellow);
        example.setTile(2,2, white);
        example.setTile(1,2, green);
        example.setTile(0,2, yellow);
        example.setTile(5,4, blue);
        example.setTile(4,4, cyan);
        example.setTile(3,4, cyan);
        example.setTile(2,4, blue);
        example.setTile(1,4, pink);
        example.setTile(0,4, pink);

        example.printCGC();

        System.out.print("\nDESCRIPTION: Three columns each formed by 6 tiles of maximum three different types.\n" +
                "One column can show the same or a different combination of another column.\n");
        System.out.print("POINTS:" + getPoints() + "\n\n");
    }
}
