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

    public synchronized void addPlayers(Player p){
      if(Players.size()<5&& (!startedGame))
        Players.add(p);
      else System.out.println("Game already full");
    }

    public synchronized void startGame(){
        LR.Start(Players.size());
        this.startedGame=true;
    }
    public synchronized void playMove(){
        //IPOTETICO
        //JSON.GetString();
          placeTiles();
          checkCGC();
    }
    public synchronized void endGame(){
        for (Player p : Players) {
            checkPGC();
            Optional<Player> P=whoWins();
            System.out.println("AND THE WINNER IS...... PLAYER :"+ P.get().getNickName());
        }
    }


    private void placeTiles(){
        String s="MI SERVE IL JSON";
        ArrayList<ItemTile> temporaryStorage ;
        temporaryStorage=LR.getTiles(s);
        switch (temporaryStorage.size()) {
            case 1: {
                if(GC.isLegalAction(LR.getBoard(s)))
                    Players.get(currPlaying-1).insertToken(temporaryStorage,temporaryStorage.size(),column);

            }
            case 2:  {
                if(GC.isLegalAction(LR.getBoard(s), LR.getBoard(s)))
                    Players.get(currPlaying-1).insertToken(temporaryStorage,temporaryStorage.size(),column);
            }
            case 3:{
                if(GC.isLegalAction(LR.getBoard(s),LR.getBoard(s),LR.getBoard(s)))
                    Players.get(currPlaying-1).insertToken(temporaryStorage,temporaryStorage.size(),column);
            }
        }
        increseCurrPlaying();
    }

    private void checkPGC(){
        for(int i=0;i<3;i++ ){
           Players.get(i).endGamePoints();
        }
    }

    private Optional<Player> whoWins(){
        Optional<Player> P=   Players.stream().reduce((P1,P2) ->P1.getScore()>P2.getScore()? P1 : P2);
        if(P.isEmpty()) {
            System.out.println("2 Players with the same score");
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
        scores.stream().sorted().collect(Collectors.toList());
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

    public LivingRoom getLR() {
        return LR;
    }
}
