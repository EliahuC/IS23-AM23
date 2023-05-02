package it.polimi.ingsw.model;
import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.board.ItemTileCategory;
import it.polimi.ingsw.model.board.LivingRoom;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.Launcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Game {
    private final LivingRoom livingRoom;
    private final List<Player> Players;

    private int currPlaying;
    private final Integer gameNumPlayers;

    private final GameChecker GC;
    private boolean startedGame=false;

    private boolean finishedGame=false;

    public Game(Launcher L,ArrayList<Player> lobby){
        this.Players=lobby;
        this.livingRoom =new LivingRoom(L);
        this.GC=new GameChecker(L);
        this.currPlaying=1;
        this.gameNumPlayers= lobby.size();
    }


    public synchronized void startGame(){
        livingRoom.Start(Players.size());
        this.startedGame=true;

    }
    public synchronized boolean playMove(ArrayList<Integer> commands,  Integer column){
          if(!finishedGame) {
              placeTiles(commands, column);
              checkCGC();
              if (GC.isRestorable(livingRoom.getBoard())) livingRoom.restore();
              return true;
          }
          return false;
    }
    public synchronized Optional<Player> endGame(){
        Optional<Player> P = Optional.empty();
         for (Player p : Players){
             p.endGamePoints();
         }
         P=whoWins();
        return P;
    }


    private synchronized void placeTiles(ArrayList<Integer> commands, Integer column){
        ArrayList<ItemTile> temporaryStorage ;
        temporaryStorage= livingRoom.getTiles(commands);
        Players.get(currPlaying-1).insertToken(temporaryStorage,column);
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
                if(GC.isLegalAction(livingRoom.getBoardTile(commands.get(0),commands.get(1))))
                    return true;


            }
            case 2:  {
                if(GC.isLegalAction(livingRoom.getBoardTile(commands.get(0),commands.get(1)),
                        livingRoom.getBoardTile(commands.get(2),commands.get(3))))
                    return true;

            }
            case 3:{
                if(GC.isLegalAction(livingRoom.getBoardTile(commands.get(0),commands.get(1)),
                        livingRoom.getBoardTile(commands.get(2),commands.get(3)),
                        livingRoom.getBoardTile(commands.get(4),commands.get(5))))
                    return true;

            }
        }
        return false;
    }

    public boolean checkLegalColumn(int column,int numOfTiles){
        return GC.checkColumn(Players.get(currPlaying-1).getPlayerBookshelf(),column,numOfTiles);
    }

    /*
    public boolean checkLegalColumn(int column,int numOfTiles){
        return Players.get(currPlaying-1).getPlayerBookshelf().getMaxPickableTiles()[column] >= numOfTiles;
    }
    */

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
        Players.get(currPlaying-1).setScore(livingRoom.checkCG(Players.get(currPlaying-1).getPlayerBookshelf()));
    }

    private synchronized void increseCurrPlaying() {
        if(currPlaying==gameNumPlayers && !finishedGame) currPlaying=1;
        else if(currPlaying<gameNumPlayers && !finishedGame) currPlaying++;
    }



}



