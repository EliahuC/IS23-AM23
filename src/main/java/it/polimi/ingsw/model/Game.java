package it.polimi.ingsw.model;
import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.board.ItemTileCategory;
import it.polimi.ingsw.model.board.LivingRoom;
import it.polimi.ingsw.model.player.BookShelf;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.Launcher;

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

    private boolean finishedGame=false;

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
          if(!Players.get(currPlaying-1).isLastRound()) {
              placeTiles(commands, order, column);
              checkCGC();
              if (GC.isRestorable(LR.getBoard())) LR.restore();
          }
    }
    public synchronized Optional<Player> endGame(){
        Optional<Player> P = Optional.empty();
         for (Player p : Players){
             p.endGamePoints();
         }
         P=whoWins();
        return P;
    }


    private synchronized void placeTiles(ArrayList<Integer> commands, ArrayList<ItemTileCategory> order, Integer column){
        ArrayList<ItemTile> temporaryStorage ;
        temporaryStorage=LR.getTiles(commands);
        Players.get(currPlaying-1).insertToken(temporaryStorage,order,column);
        GC.isBookShelfFull(Players.get(currPlaying-1).getPlayerBookshelf());
        if (GC.getLastRound())isLastTurn();
        increseCurrPlaying();

    }

    private synchronized void isLastTurn() {
        for(Player p:Players)p.setLastRound(true);
        if(currPlaying==4)finishedGame=true;
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



    private synchronized Optional<Player> whoWins(){
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

    private synchronized void increseCurrPlaying() {
        if(currPlaying==4&&!finishedGame) currPlaying=1;
        else if(currPlaying<4&&!finishedGame) currPlaying++;
    }



}



