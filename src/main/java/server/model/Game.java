package server.model;
import server.Launcher;
import server.model.board.*;
import server.model.player.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Game {
    private final LivingRoom LR;
    private final List<Player> Players;

    private int currPlaying;

    private final GameChecker GC;

    public Game(Launcher L){
        Players=new ArrayList<>();
        LR=new LivingRoom();
        GC=new GameChecker(L);
        this.currPlaying=1;
    }

    public synchronized void addPlayers(Player p){
      if(Players.size()<5)
        Players.add(p);
      else System.out.println("Game already full");
    }

    public synchronized void playGame(){
        LR.Start(Players.size());
        while(!GC.getLastRound()) {
          placeTiles();
          checkCGC();
      }
        checkPGC();
       Optional<Player> P=whoWins();
      System.out.println("AND THE WINNER IS...... PLAYER :"+ P.get().getNickName());
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
        Optional<Player> P=   Players.stream().reduce( (P1,P2) ->P1.getScore()>P2.getScore()? P1 : P2);
        if(!P.isPresent()) {
            System.out.println("2 Players with the same score");
            //Optional<Player> PW=Players.stream().reduce(P1->P1.getScore()=P.get().getScore()&&)
            //MI MANCA DA AGGIUNGERE QUALCOSA CHE MI FACCIA DISTINGUERE LA POSIZIONE DEL PLAYER PER RISOLVERE IL PROBLEMA

        }
        return P;
    }

    private Optional<Player> checkManually() {
        int s1=0,s2=0,s3=0,s4=0;
        s1=Players.get(0).getScore();
        s2=Players.get(1).getScore();
        s3=Players.get(2).getScore();
        s4=Players.get(3).getScore();

    }

    private void checkCGC(){
        Players.get(currPlaying-1).setScore(LR.checkCG(Players.get(currPlaying-1).getPlayerBookshelf()));
    }

    private void increseCurrPlaying() {
        if(currPlaying==4) currPlaying=1;
        else currPlaying++;
    }
}
