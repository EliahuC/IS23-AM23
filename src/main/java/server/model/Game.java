package server.model;
import server.Launcher;
import server.model.board.*;
import server.model.player.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Game {
    private final LivingRoom LR;
    private final List<Player> Players;

    private int currPlaying;

    private final GameChecker GC;
    private boolean startedGame=false;

    public Game(Launcher L){
        this.Players=new ArrayList<>();
        this.LR=new LivingRoom(L);
        this.GC=new GameChecker(L);
        this.currPlaying=1;
    }

    public synchronized void addPlayers(String s) {
        if (Players.size() < 4 && (!startedGame)) {
            Player p = new Player(s);
            Players.add(p);
        }
    }
    public synchronized void startGame(){
        LR.Start(Players.size());
        this.startedGame=true;
    }
    public synchronized void playMove(ArrayList<Integer> commands, ArrayList<ItemTileCategory> order, Integer column){
          placeTiles(commands,order,column);
          checkCGC();
          if(GC.isRestorable(LR.getBoard())) LR.restore();
    }
    public synchronized Optional<Player> endGame(){
        Optional<Player> P = Optional.empty();
         for (Player p : Players) {
            checkPGC();
             P=whoWins();
            //System.out.println("AND THE WINNER IS...... PLAYER :"+ P.get().getNickName());
        }
        return P;
    }


    private void placeTiles(ArrayList<Integer> commands, ArrayList<ItemTileCategory> order, Integer column){
        ArrayList<ItemTile> temporaryStorage ;
        temporaryStorage=LR.getTiles(commands);
        Players.get(currPlaying-1).insertToken(temporaryStorage,order,column);
        increseCurrPlaying();

    }

    public boolean checkLegalMove(ArrayList<Integer> commands, int size){

        if(!checkNumber(size))return false;
        switch (size) {
            case 1: {
                if(GC.isLegalAction(LR.getBoardTile(commands.get(0),commands.get(1))))
                    return true;


            }
            case 2:  {
                if(GC.isLegalAction(LR.getBoardTile(commands.get(0),commands.get(1)),
                        LR.getBoardTile(commands.get(2),commands.get(3))))
                    return true;

            }
            case 3:{
                if(GC.isLegalAction(LR.getBoardTile(commands.get(0),commands.get(1)),
                        LR.getBoardTile(commands.get(2),commands.get(3)),
                        LR.getBoardTile(commands.get(4),commands.get(5))))
                    return true;

            }
        }
        return false;
    }

    public boolean checkLegalColumn(int column,int numOfTiles){
        return GC.checkColumn(Players.get(currPlaying-1).getPlayerBookshelf(),column,numOfTiles);
    }

    private boolean checkNumber(int i){
        return GC.getMaxPickableTiles(Players.get(currPlaying - 1).getPlayerBookshelf()) >= i;
    }

    private void checkPGC(){
        for(int i=0;i<3;i++ ){
           Players.get(i).endGamePoints();
        }
    }

    private Optional<Player> whoWins(){
        Optional<Player> P=   Players.stream().reduce((P1,P2) ->P1.getScore()>P2.getScore()? P1 : P2);
        if(P.isEmpty()) {
            //System.out.println("2 Players with the same score");
             P= Optional.ofNullable(checkManually());

        }
        return P;
    }

    private Player checkManually() {
        List<Integer> scores =new ArrayList<>();
        scores.add(Players.get(0).getScore());
        scores.add(Players.get(1).getScore());
        scores.add(Players.get(2).getScore());
        scores.add(Players.get(3).getScore());
        scores= scores.stream().sorted().collect(Collectors.toList());
        if(Players.get(3).getScore()==scores.get(0)) return Players.get(3);
        else if (Players.get(2).getScore()==scores.get(0)) return Players.get(2);
        else if (Players.get(1).getScore()==scores.get(0)) return Players.get(1);
        else return Players.get(0);

    }

    private void checkCGC(){
        Players.get(currPlaying-1).setScore(LR.checkCG(Players.get(currPlaying-1).getPlayerBookshelf()));
    }

    private void increseCurrPlaying() {
        if(currPlaying==4) currPlaying=1;
        else currPlaying++;
    }


}



