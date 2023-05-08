package it.polimi.ingsw.model;
import it.polimi.ingsw.Network.Server.TCP.VirtualView;
import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.board.LivingRoom;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.Launcher;

import java.util.*;
import java.util.stream.Collectors;

public class Game {
    private final LivingRoom livingRoom;
    private final ArrayList<Player> Players;
    private final ArrayList<Player> disconnectedPlayers;

    private int currPlaying;
    private final Integer gameNumPlayers;

    private final GameChecker gameChecker;
    private boolean startedGame=false;

    private boolean finishedGame=false;

    public Game(Launcher L,ArrayList<Player> lobby){
        this.Players=lobby;
        this.disconnectedPlayers=new ArrayList<>();
        this.livingRoom =new LivingRoom(L);
        setLivingRoomListener();
        this.gameChecker =new GameChecker(L);
        this.currPlaying=1;
        this.gameNumPlayers= lobby.size();
    }

    private void setLivingRoomListener() {
        ArrayList<VirtualView> listeners =new ArrayList<>();
        for(Player p:Players){
            listeners.add((VirtualView) p.getListener());
        }
        livingRoom.setListeners(listeners);
    }
    public void setDisconnectedPlayers(Player p){
        disconnectedPlayers.add(p);
    }


    public synchronized void startGame(){

        livingRoom.Start(Players.size());
        this.startedGame=true;
        ArrayList<Player> mixedPlayers;
        mixedPlayers=mixPlayersOrder(Players);
        Players.clear();
        Players.addAll(mixedPlayers);
        Players.get(0).setFirstPlayerSeat(true);
    }

    private ArrayList<Player> mixPlayersOrder(List<Player> players) {
        ArrayList<Player> mixedPlayers=new ArrayList<>();
        int size=players.size();
        for(int i=0;i<size;i++){
            int randIndex = new Random().nextInt(players.size());
            mixedPlayers.add(i,players.remove(randIndex));
        }
        return mixedPlayers;
    }

    public synchronized boolean playMove(ArrayList<Integer> commands,  Integer column, ArrayList<Integer> order){
          if(!finishedGame) {
              placeTiles(commands, column,order);
              checkCGC();
              increaseCurrPlaying();
              if (gameChecker.isRestorable(livingRoom.getBoard())) livingRoom.restore();
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


    private synchronized void placeTiles(ArrayList<Integer> commands, Integer column,ArrayList<Integer> order){
        if(disconnectedPlayers.contains(Players.get(currPlaying-1))){
            return;
        }
        ArrayList<ItemTile> temporaryStorage ;
        temporaryStorage= livingRoom.getTiles(commands);
        temporaryStorage=sortMyTiles(temporaryStorage,order);
        Players.get(currPlaying-1).insertToken(temporaryStorage,column);
        gameChecker.isBookShelfFull(Players.get(currPlaying-1).getPlayerBookshelf());
        if (gameChecker.getLastRound())isLastTurn();

    }

    private ArrayList<ItemTile> sortMyTiles(ArrayList<ItemTile> temporaryStorage, ArrayList<Integer> order) {
        ArrayList<ItemTile> sortedTiles=new ArrayList<>();
        for (Integer integer : order) {
            sortedTiles.add(temporaryStorage.get(integer-1));
        }
        return sortedTiles;
    }

    private synchronized void isLastTurn() {
        for(Player p:Players)p.setLastRound(true);
        if(currPlaying==4)finishedGame=true;

    }

    public boolean checkLegalMove(ArrayList<Integer> coordinates, int size){
        ArrayList<Integer> commands = new ArrayList<>(coordinates);
        commands=sortTiles(commands);
        if(!checkNumber(size))return false;
        switch (size) {
            case 1: {
                if(gameChecker.isLegalAction(livingRoom.getBoardTile(commands.get(0),commands.get(1))))
                    return true;


            }
            case 2:  {
                if(gameChecker.isLegalAction(livingRoom.getBoardTile(commands.get(0),commands.get(1)),
                        livingRoom.getBoardTile(commands.get(2),commands.get(3))))
                    return true;

            }
            case 3:{
                if(gameChecker.isLegalAction(livingRoom.getBoardTile(commands.get(0),commands.get(1)),
                        livingRoom.getBoardTile(commands.get(2),commands.get(3)),
                        livingRoom.getBoardTile(commands.get(4),commands.get(5))))
                    return true;

            }
        }
        return false;
    }

    private ArrayList<Integer> sortTiles(ArrayList<Integer> commands) {
        ArrayList<Integer> comandi=new ArrayList<>();
        //tessere sulla stessa riga
        if((Objects.equals(commands.get(0), commands.get(2)) && Objects.equals(commands.get(2), commands.get(4)))
        ||(Objects.equals(commands.get(0), commands.get(2)) && Objects.equals(null, commands.get(4)))){
            sortRow(commands,comandi);
        }
        //tessere sulla stessa colonna
        else if((Objects.equals(commands.get(1), commands.get(3)) && Objects.equals(commands.get(3), commands.get(5)))
            ||(Objects.equals(commands.get(1), commands.get(3)) && Objects.equals(null, commands.get(5)))){
           sortColumn(commands,comandi);
        }
      return comandi;
    }

    private void sortColumn(ArrayList<Integer> commands, ArrayList<Integer> comandi) {
        ArrayList<Integer> y=new ArrayList<>();
        Integer size=commands.size();
        Integer support=commands.get(1);
        y.add(commands.get(0));
        if(size>2) y.add(commands.get(2));
        if(size>4)y.add(commands.get(4));
        y= (ArrayList<Integer>) y.stream().sorted().collect(Collectors.toList());
        comandi.add(y.get(0));
        comandi.add(support);
        if (size>2){
            comandi.add(y.get(1));
            comandi.add(support);
        }
        if (size > 4) {
            comandi.add(y.get(2));
            comandi.add(support);
        }

    }

    private void sortRow(ArrayList<Integer> commands, ArrayList<Integer> comandi) {
        ArrayList<Integer> x=new ArrayList<>();
        Integer size=commands.size();
        Integer support=commands.get(0);
        x.add(commands.get(1));
        if(size>2)x.add(commands.get(3));
        if(size>4)x.add(commands.get(5));
        x= (ArrayList<Integer>) x.stream().sorted().collect(Collectors.toList());
        comandi.add(support);
        comandi.add(x.get(0));
        if(size>2){
            comandi.add(support);
            comandi.add(x.get(1));
        }
        if(size>4){
            comandi.add(support);
            comandi.add(x.get(2));
        }
    }

    public boolean checkLegalColumn(int column,int numOfTiles){
        return gameChecker.checkColumn(Players.get(currPlaying-1).getPlayerBookshelf(),column,numOfTiles);
    }

    /*
    public boolean checkLegalColumn(int column,int numOfTiles){
        return Players.get(currPlaying-1).getPlayerBookshelf().getMaxPickableTiles()[column] >= numOfTiles;
    }
    */

    private boolean checkNumber(int i){
        return gameChecker.getMaxPickableTiles(Players.get(currPlaying - 1).getPlayerBookshelf()) >= i;
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

    private synchronized void increaseCurrPlaying() {
        if(currPlaying==gameNumPlayers && !finishedGame) currPlaying=1;
        else if(currPlaying<gameNumPlayers && !finishedGame) currPlaying++;
    }

    public synchronized String getCurrPlaying(){
        return Players.get(currPlaying-1).getNickName();
    }

    public ArrayList<Player> getDisconnectedPlayers() {
        return disconnectedPlayers;
    }

    public boolean isStartedGame() {
        return startedGame;
    }

    public ArrayList<Player> getPlayers() {
        return Players;
    }

    public LivingRoom getLivingRoom() {
        return livingRoom;
    }
}



