package it.polimi.ingsw.model.board.goalCards;

import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.player.BookShelf;

public class CommonGoalCard11 extends CommonGoalCard implements CheckCommonGoalCard {
    private final Launcher L;

    public CommonGoalCard11(Launcher L) {
        this.L = L;
    }
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
    private boolean X(BookShelf bs, int k, int l) {
        if (NoItemsNull(bs, k, l)) {
            if ((IsEqualCategory(bs.getTile(k, l), bs.getTile(k, l + 2))) && (IsEqualCategory(bs.getTile(k, l), bs.getTile(k + 1, l + 1)))
                    && (IsEqualCategory(bs.getTile(k, l), bs.getTile(k + 2, l))) && (IsEqualCategory(bs.getTile(k, l), bs.getTile(k + 2, l + 2))))
                return true;
            else return false;
        } else return false;
    }

    private boolean IsEqualCategory(ItemTile i, ItemTile tile){
        return i.getCategory()==tile.getCategory();
    }

    private boolean NoItemsNull(BookShelf bs, int k, int l){
        if((bs.getTile(k,l+2)!=null) && (bs.getTile(k+1,l+1)!=null) &&
                (bs.getTile(k+2,l)!=null) && (bs.getTile(k+2,l+2) != null))
            return true;
        else return false;
    }
}