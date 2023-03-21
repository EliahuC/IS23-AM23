package server.model;

public class GameChecker {

    private boolean restorable;
    private int maxPickableTiles;
    private boolean legalSelection;
    private boolean lastRound;
    private boolean isYourTurn;

    public GameChecker() {
        this.restorable = false;
        this.maxPickableTiles = 0;
        this.legalSelection = false;
        this.lastRound = false;
        this.isYourTurn = false;
    }


    public boolean isRestorable(LivingRoom board) {
        if(/*algoritmo per vedere se è da fare refresh*/){
            restorable = true;
        }
        else restorable = false;

        return restorable;
    }

    public boolean getRestorable(){
        return restorable;
    }

    public int checkColumnCapability(BookShelf s){
        if(isBookShelfFull() == true){
            maxPickableTiles = 0;
        }
        else {};

        return maxPickableTiles;
    }

    public int getMaxPickableTiles(){
        return maxPickableTiles;
    }

    public boolean isLastRound() {
        return lastRound;
    }

    public boolean isLegalAction() {
        return legalSelection;
    }

    public boolean isBookShelfFull(BookShelf s){
        if(/**/){
            lastRound = true;
        }
        else lastRound = false;

        return lastRound;
    }

    public boolean GetIsYourTurn(Player p) {
        return isYourTurn;
    }

    public int getLastRound(){
        return lastRound;

    }

    public void checkRound(){
        if (lastRound == true)
            System.out.println("Ultimo turno!")  }

}

