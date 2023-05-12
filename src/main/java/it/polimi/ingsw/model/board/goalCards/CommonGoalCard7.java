package it.polimi.ingsw.model.board.goalCards;

import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.player.BookShelf;

public class CommonGoalCard7 extends CommonGoalCard implements CheckCommonGoalCard {
    private final Launcher L;

    public CommonGoalCard7(Launcher L){
        this.L = L;
    }

    @Override
    public void checkGoal(BookShelf bs) {
       if (checkRtoL(bs)|| checkLtoR(bs)) increaseNumCompleted();
    }
    private boolean checkLtoR(BookShelf bs) {
        //diagonale da (0,0) o (1,0)
        boolean start1 = false, start2 = false;
        if (bs.getTile(0, 0) != null)
            start1 = true;

        if (bs.getTile(1, 0) != null)
            start2 = true;

        if ((start1) || (start2)) {
            ItemTile T = bs.getTile(0, 0);
            ItemTile K = bs.getTile(1, 0);
            int Counter1 = 0;
            int Counter2 = 0;
            for (int i = 1; i < 5; i++) {
                if ((bs.getTile(i, i) != null) && (start1))
                    if (bs.getTile(i, i).getCategory() == T.getCategory()) Counter1++;
                if ((bs.getTile(i + 1, i) != null) && (start2))
                    if (bs.getTile(i + 1, i).getCategory() == K.getCategory()) Counter2++;
            }
            return Counter1 == 4 || Counter2 == 4;
        } else return false;
    }

    private boolean checkRtoL(BookShelf bs) {
        //diagonale da (0,4) o (1,4)
        boolean start3 = false, start4 = false;
        if (bs.getTile(0, 4) != null)
            start3 = true;

        if (bs.getTile(1, 4) != null)
            start4 = true;

        if ((start3) || (start4)) {
            ItemTile T = bs.getTile(0, 4);
            ItemTile K = bs.getTile(1, 4);
            int Counter1 = 0;
            int Counter2 = 0;
            for (int i = 1; i < 5; i++) {
                if((bs.getTile(i,4-i)!=null) &&(start3))
                    if (bs.getTile(i, 4 - i).getCategory() == T.getCategory()) Counter1++;
                if((bs.getTile(i+1,4-i)!=null) &&(start4))
                    if (bs.getTile(i + 1, 4 - i).getCategory() == K.getCategory()) Counter2++;
            }
            return Counter1 == 4 || Counter2 == 4;
        } else return false;
    }

}