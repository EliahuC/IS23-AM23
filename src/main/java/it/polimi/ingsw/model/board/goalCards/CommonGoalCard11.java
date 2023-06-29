package it.polimi.ingsw.model.board.goalCards;

import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.player.BookShelf;

/**
 * @author Giovanni Di Lorenzo
 */
public class CommonGoalCard11 extends CommonGoalCard implements CheckCommonGoalCard {




    public CommonGoalCard11(Integer I){
        super(I);
    }
    /**
     * The method below checks if there is a X scheme of same category itemtiles in the bookshelf
     * @param bs Player's bookshelf
     *
     */
    @Override
    public void checkGoal(BookShelf bs) {
        boolean flag=false;
        for(int i=0; i<BookShelf.getMAX_Row()-2;i++){
            for(int j=0;j< BookShelf.getMAX_Column()-2;j++) {
                if (bs.getTile(i, j) != null) {
                    if (X(bs, i, j)) {
                        flag = true;
                    }
                    if (flag)
                        break;
                }
            }
            if(flag)
                break;
        }
        if(flag)
            increaseNumCompleted();
    }
    /**
     * The method below checks if Item Tiles with coordinates (k,l),(k,l+2),(k+1,l+1),(k+2,l),(k+2,l+2) create an X,
     * complying the specifics
     * @param bs Player's bookshelf
     * @param k furthermore itemtile column
     * @param l row indexes
     */
    private boolean X(BookShelf bs, int k, int l) {
        if (NoItemsNull(bs, k, l)) {
            if ((IsEqualCategory(bs.getTile(k, l), bs.getTile(k, l + 2))) && (IsEqualCategory(bs.getTile(k, l), bs.getTile(k + 1, l + 1)))
                    && (IsEqualCategory(bs.getTile(k, l), bs.getTile(k + 2, l))) && (IsEqualCategory(bs.getTile(k, l), bs.getTile(k + 2, l + 2))))
                return true;
            else return false;
        } else return false;
    }
    /**
     * The method below checks if the input parameters have the same category
     * @param i ItemTile1
     * @param tile ItemTile2
     *
     */
    private boolean IsEqualCategory(ItemTile i, ItemTile tile){
        return i.getCategory()==tile.getCategory();
    }

    /**
     * The method below checks if the itemtiles with coordinates (k,l+2),(k+2,l),(k+1,l+1),(k+2,l+2) are not null
     * @param bs Players'bookshelf
     * @param l furthermore itemtile column
     * @param k row indexes
     */
    private boolean NoItemsNull(BookShelf bs, int k, int l){
        if((bs.getTile(k,l+2)!=null) && (bs.getTile(k+1,l+1)!=null) &&
                (bs.getTile(k+2,l)!=null) && (bs.getTile(k+2,l+2) != null))
            return true;
        else return false;
    }
    @Override
    public void print(){
        System.out.print("CROSS TILES.\n\n");

        //                                     [0]             [1]             [2]             [3]            [4]
        System.out.println("                    " + WOOD + "                     " + RESET +
                "\n                      " + WOOD + " " + RESET + "   " + RESET + "   " + RESET + "   " + RESET + "   " + RESET + "   " + WOOD + " " + RESET +
                "\n                      " + WOOD + " " + RESET + "   " + RESET + "   " + RESET + "   " + RESET + "   " + RESET + "   " + WOOD + " " + RESET +
                "\n                      " + WOOD + " " + RESET + "   " + CYAN + "   " + RESET + "   " + CYAN + "   " + RESET + "   " + WOOD + " " + RESET +
                "\n                      " + WOOD + " " + RESET + "   " + RESET + "   " + CYAN + "   " + RESET + "   " + RESET + "   " + WOOD + " " + RESET +
                "\n                      " + WOOD + " " + RESET + "   " + CYAN + "   " + RESET + "   " + CYAN + "   " + RESET + "   " + WOOD + " " + RESET +
                "\n                      " + WOOD + " " + RESET + "   " + RESET + "   " + RESET + "   " + RESET + "   " + RESET + "   " + WOOD + " " + RESET +
                "\n                    " + WOOD + "                     " + RESET);

        System.out.print("""
                
                > DESCRIPTION: Five tiles of the same type forming an X.
                """);
        System.out.print("> POINTS: " + points + "\n\n");
    }
}