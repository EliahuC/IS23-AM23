package server.model.board.goalCards;

import server.Launcher;
import server.model.player.BookShelf;



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
        int lastColumn=0;
        for(int i=0; i<BookShelf.getMAX_Column();i++){
          if(countColumn(bs,i)!=lastColumn+1) return false;
          else lastColumn=countColumn(bs,i);
        }
        return true;
    }
    private boolean checkLtoR(BookShelf bs){
        int lastColumn=0;
        for(int i=0; i<BookShelf.getMAX_Column(); i++){
            if(countColumn(bs,i)!=lastColumn-1) return false;
            else lastColumn=countColumn(bs,i);
        }
        return true;
    }


    private int countColumn(BookShelf bs,int i){
        int counter =0;
        for (int j=0;j<BookShelf.getMAX_Row()&&bs.getTile(5-j,i)!=null;j++){
            counter++;
        }
        return counter;
    }
}