package it.polimi.ingsw.model.board.goalCards;

import it.polimi.ingsw.model.player.BookShelf;
import it.polimi.ingsw.Launcher;


public class CommonGoalCard12 extends CommonGoalCard implements CheckCommonGoalCard {
    private final Launcher L;

    public CommonGoalCard12(Launcher L) {
        this.L = L;
    }

    @Override
    public void checkGoal(BookShelf bs) {
        if(checkRtoL(bs)||checkLtoR(bs)) increaseNumCompleted();
    }

    private boolean checkRtoL(BookShelf bs){
        int lastColumn=countColumnRtoL(bs,0);
        for(int i=1; i<BookShelf.getMAX_Column();i++){
          if(countColumnRtoL(bs,i)!=lastColumn+1) return false;
          else lastColumn=countColumnRtoL(bs,i);
        }
        return true;
    }
    private boolean checkLtoR(BookShelf bs){
        int lastColumn=countColumnLtoR(bs,BookShelf.getMAX_Row()-1);
        for(int i=BookShelf.getMAX_Column()-2; i>=0; i--){
            if(countColumnLtoR(bs,i)!=lastColumn+1) return false;
            else lastColumn=countColumnLtoR(bs,i);

        }
        return true;
    }


    private int countColumnRtoL(BookShelf bs,int i){
        int counter =0;
        for (int j=0;j<BookShelf.getMAX_Row()&&bs.getTile(BookShelf.getMAX_Row()-1-j,i)!=null;j++){
            counter++;
        }
        return counter;
    }

    private int countColumnLtoR(BookShelf bs, int i){
        int counter =0;
        for (int j=0;j<BookShelf.getMAX_Row()&&bs.getTile(BookShelf.getMAX_Row()-1-j,i)!=null;j++){
            counter++;
        }
        return counter;
    }
}