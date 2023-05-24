package it.polimi.ingsw.model;

import it.polimi.ingsw.GameSavings;
import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.Network.Server.VirtualView;
import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.board.LivingRoom;
import it.polimi.ingsw.model.player.PersonalGoalCard;
import it.polimi.ingsw.model.player.Player;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Eliahu Cohen
 * Class that contains all the game logic and actions
 */


public class Game implements Serializable {
    private final LivingRoom livingRoom;
    private final ArrayList<Player> Players;
    private transient final ArrayList<Player> disconnectedPlayers;

    private Integer currPlaying;
    private transient final Integer gameNumPlayers;

    private transient final GameChecker gameChecker;
    private transient boolean startedGame=false;
    private transient final ArrayList<VirtualView> listeners ;
    private transient final Timer turnTimer=new Timer();

    private transient boolean finishedGame=false;



    public Game(Launcher L,ArrayList<Player> lobby){
        listeners=new ArrayList<>();
        this.Players=lobby;
        this.disconnectedPlayers=new ArrayList<>();
        this.livingRoom =new LivingRoom(L);
        setLivingRoomListener();
        this.gameChecker =livingRoom.getGameChecker();
        this.currPlaying=1;
        this.gameNumPlayers= lobby.size();
    }

    /**
     * @author Eliahu Cohen
     * method that sets to the livingroom all his listeners
     */
    private void setLivingRoomListener() {
        for(Player p:Players){
            listeners.add((VirtualView) p.getListener());
        }
        livingRoom.setListeners(listeners);
    }
    public void setDisconnectedPlayers(Player p){
        disconnectedPlayers.add(p);
    }

    /**
     * @author Eliahu Cohen
     * Method that sets what he needs and starts the game
     */
    public synchronized void startGame(){
        livingRoom.start(Players.size());
        this.startedGame=true;
        ArrayList<Player> mixedPlayers;
        mixedPlayers=mixPlayersOrder(Players);
        Players.clear();
        Players.addAll(mixedPlayers);
        Players.get(0).setFirstPlayerSeat(true);
        for(Player p:Players){
            p.setPersonalGoalCard(new PersonalGoalCard());
        }
        turnTimer(turnTimer);
        PropertyChangeEvent evt = new PropertyChangeEvent(
                this,
                "GAME_STARTED",
                null,
                this);
        for(PropertyChangeListener l:listeners){
            l.propertyChange(evt);
        }
    }

    /**
     * @author Eliahu Cohen
     * @param players connected in the game ordered by who connected first
     * @return players in random positions
     */
    private ArrayList<Player> mixPlayersOrder(List<Player> players) {
        ArrayList<Player> mixedPlayers=new ArrayList<>();
        int size=players.size();
        for(int i=0;i<size;i++){
            int randIndex = new Random().nextInt(players.size());
            mixedPlayers.add(i,players.remove(randIndex));
        }
        return mixedPlayers;
    }

    /**
     * @author Eliahu Cohen
     * @param commands array list that contains the coordinates of the tiles the player want to extract
     * @param column where the player wants to put the extracted tiles in his bookshelf
     * @param order of the tiles he wants to insert
     * @return the GameSavings to store for the persistence advanced function
     */
    public synchronized GameSavings playMove(ArrayList<Integer> commands,  Integer column, ArrayList<Integer> order){
        if(!finishedGame) {
              placeTiles(commands, column,order);
              checkCGC();
              turnTimer.cancel();
              increaseCurrPlaying();
             turnTimer(turnTimer);
              if (gameChecker.isRestorable(livingRoom.getBoard())) livingRoom.restore();
              return setGameSavings();
          }
          return null;
    }

    /**
     * @author Eliahu Cohen
     * @return the GameSavings set on the move of the currPlaying player
     */
    private GameSavings setGameSavings() {
        GameSavings savings=new GameSavings(Players);
        savings.setLivingRoom(livingRoom);
        savings.setFinishedGame(finishedGame);
        savings.setCommonGoalCard1(livingRoom.getIdCGC1());
        savings.setCommonGoalCard2(livingRoom.getIdCGC2());
        savings.setStartedGame(startedGame);
        savings.setCurrPlaying(currPlaying);
        savings.setNumPlayers(gameNumPlayers);
        return savings;
    }

    /**
     * @author Eliahu Cohen
     * @return the player with the highest score
     */
    public synchronized Optional<Player> endGame(){
        Optional<Player> P = Optional.empty();
         for (Player p : Players){
             p.endGamePoints();
         }
         P=whoWins();
        return P;
    }

    /**
     * @author Eliahu Cohen
     * @param commands coordinates of the tiles to extract
     * @param column where the player want to put the tiles
     * @param order of the tiles to put
     * Method that puts the tiles in the player bookshelf, checks if the player have fulled his bookshelf
     *              and if it's right sets the lastRound boolean to all the players
     */
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

    /**
     * @author Eliahu Cohen
     * @param temporaryStorage tiles extracted from the living room
     * @param order of the tiles that will be inserted in the bookshelf
     * @return ordered tiles
     */
    private ArrayList<ItemTile> sortMyTiles(ArrayList<ItemTile> temporaryStorage, ArrayList<Integer> order) {
        ArrayList<ItemTile> sortedTiles=new ArrayList<>();
        for (Integer integer : order) {
            sortedTiles.add(temporaryStorage.get(integer-1));
        }
        return sortedTiles;
    }

    /**
     * @author Eliahu Cohen
     * Method that checks if the game is finished and sets the last turn boolean to all the players
     */
    private synchronized void isLastTurn() {
        for(Player p:Players)p.setLastRound(true);
        if(currPlaying==4){
            finishedGame=true;
            PropertyChangeEvent evt = new PropertyChangeEvent(
                    this,
                    "GAME_ENDED",
                    null,
                    endGame());
            for(PropertyChangeListener l:listeners){
                l.propertyChange(evt);
            }
        }

    }

    /**
     * @author Eliahu Cohen
     * @param coordinates of the tiles the player wants to extract
     * @param size number of tiles the player wants to extract
     * @return true if the player selected legal tiles from the living room
     */
    public boolean checkLegalMove(ArrayList<Integer> coordinates, int size){
        ArrayList<Integer> commands = new ArrayList<>(coordinates);
        if(size>1) {
            commands = sortTiles(commands);
            if (!checkNumber(size)) return false;
        }
        switch (size) {
            case 1: {
                if(gameChecker.isLegalAction(livingRoom.getBoardTile(commands.get(0),commands.get(1))))
                    return true;
                break;


            }
            case 2:  {
                if(gameChecker.isLegalAction(livingRoom.getBoardTile(commands.get(0),commands.get(1)),
                        livingRoom.getBoardTile(commands.get(2),commands.get(3))))
                    return true;
                break;

            }
            case 3:{
                if(gameChecker.isLegalAction(livingRoom.getBoardTile(commands.get(0),commands.get(1)),
                        livingRoom.getBoardTile(commands.get(2),commands.get(3)),
                        livingRoom.getBoardTile(commands.get(4),commands.get(5))))
                    return true;
                break;
            }
        }
        return false;
    }

    /**
     * @author Eliahu Cohen
     * @param commands coordinates of the tiles to extract
     * @return the coordinates sorted to be checked in the Game Checker
     */
    private ArrayList<Integer> sortTiles(ArrayList<Integer> commands) {
        ArrayList<Integer> temporaryCommands=new ArrayList<>();
        switch(commands.size()) {
            case 4 : {
                //tiles on the same row
                if(Objects.equals(commands.get(0), commands.get(2)))
                    sortRow(commands,temporaryCommands);
                    //tiles on the same column
                else if(Objects.equals(commands.get(1), commands.get(3)))
                    sortColumn(commands,temporaryCommands);
                break;
            }

            case 6 : {
                //tiles on the same row
                if((Objects.equals(commands.get(0), commands.get(2))) && (Objects.equals(commands.get(2), commands.get(4))))
                    sortRow(commands,temporaryCommands);
                    //tiles on the same column
                else if((Objects.equals(commands.get(1), commands.get(3)))&& Objects.equals(commands.get(3), commands.get(5)))
                    sortColumn(commands,temporaryCommands);
                break;
            }

        }
      return temporaryCommands;
    }

    /**
     * @author Eliahu Cohen
     * @param commands coordinates of the tiles the player wants to extract
     * @param temporaryCommands array list I want to fill with the sorted coordinates
     * Method that returns to me the coordinates sorted based on the same column of the tiles
     */
    private void sortColumn(ArrayList<Integer> commands, ArrayList<Integer> temporaryCommands) {
        ArrayList<Integer> y=new ArrayList<>();
        Integer size=commands.size();
        Integer support=commands.get(1);
        y.add(commands.get(0));
        if(size>2) y.add(commands.get(2));
        if(size>4)y.add(commands.get(4));
        y= (ArrayList<Integer>) y.stream().sorted().collect(Collectors.toList());
        temporaryCommands.add(y.get(0));
        temporaryCommands.add(support);
        if (size>2){
            temporaryCommands.add(y.get(1));
            temporaryCommands.add(support);
        }
        if (size > 4) {
            temporaryCommands.add(y.get(2));
            temporaryCommands.add(support);
        }

    }
    /**
     * @author Eliahu Cohen
     * @param commands coordinates of the tiles the player wants to extract
     * @param temporaryCommands array list I want to fill with the sorted coordinates
     * Method that returns to me the coordinates sorted based on the same row of the tiles
     */
    private void sortRow(ArrayList<Integer> commands, ArrayList<Integer> temporaryCommands) {
        ArrayList<Integer> x=new ArrayList<>();
        Integer size=commands.size();
        Integer support=commands.get(0);
        x.add(commands.get(1));
        if(size>2)x.add(commands.get(3));
        if(size>4)x.add(commands.get(5));
        x= (ArrayList<Integer>) x.stream().sorted().collect(Collectors.toList());
        temporaryCommands.add(support);
        temporaryCommands.add(x.get(0));
        if(size>2){
            temporaryCommands.add(support);
            temporaryCommands.add(x.get(1));
        }
        if(size>4){
            temporaryCommands.add(support);
            temporaryCommands.add(x.get(2));
        }
    }

    /**
     * @author Eliahu Cohen
     * @param column to check
     * @param numOfTiles the player wants to put in that column
     * @return true if the Bookshelf column has at least numOfTiles space to put tiles
     */
    public boolean checkLegalColumn(int column,int numOfTiles){
        return gameChecker.checkColumn(Players.get(currPlaying-1).getPlayerBookshelf(),column,numOfTiles);
    }

    /*
    public boolean checkLegalColumn(int column,int numOfTiles){
        return Players.get(currPlaying-1).getPlayerBookshelf().getMaxPickableTiles()[column] >= numOfTiles;
    }
    */

    /**
     * @author Eliahu Cohen
     * @param numOfTiles to extract
     * @return true if there is enough space in the bookshelf for the tiles the player want to extract
     */
    private boolean checkNumber(int numOfTiles){
        return gameChecker.getMaxPickableTiles(Players.get(currPlaying - 1).getPlayerBookshelf()) >= numOfTiles;
    }


    /**
     * @author Eliahu Cohen
     * @return the player with the highest score
     * The method checks with a functional method who is the player with the highest score.
     * if there is a draw the player will be selected manually
     */
    private synchronized Optional<Player> whoWins(){
        Optional<Player> P=   Players.stream().reduce((P1,P2) ->P1.getScore()>P2.getScore()? P1 : P2);
        if(P.isEmpty()) {
            //System.out.println("2 Players with the same score");
             P= Optional.ofNullable(checkManually());

        }
        return P;
    }

    /**
     * @author Eliahu Cohen
     * @return manually selection of the winner
     */
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

    /**
     * @author Eliahu Cohen
     * Method that checks if the player have completed some common goal cards
     */
    private void checkCGC(){
        if(!Players.get(currPlaying-1).getPlayerBookshelf().isCommonGoalCard1Completed()||
                !Players.get(currPlaying-1).getPlayerBookshelf().isCommonGoalCard2Completed()){
            Integer score=Players.get(currPlaying-1).getScore();
            Players.get(currPlaying-1).setScore(livingRoom.checkCG(Players.get(currPlaying-1).getPlayerBookshelf()));
            if(score.equals(Players.get(currPlaying-1).getScore())){
                String s="PLAYER "+Players.get(currPlaying-1).getNickName()+" COMPLETED A COMMON GOAL CARD";
                PropertyChangeEvent evt= new PropertyChangeEvent(
                        this,
                        "COMMON COMPLETED",
                        null,
                        s);
                Players.get(currPlaying-1).getListener().propertyChange(evt);
            }
        }
    }

    /**
     * @author Eliahu Cohen
     * method that increase the value of the currPlaying variable checking if the game is finished
     */
    private synchronized void increaseCurrPlaying() {
        if(currPlaying==gameNumPlayers && !finishedGame) currPlaying=1;
        else if(currPlaying<gameNumPlayers && !finishedGame) currPlaying++;
        /*PropertyChangeEvent evt = new PropertyChangeEvent(
                this,
                "NEW_TURN",
                this.currPlaying,
                currPlaying);
        for(PropertyChangeListener l:listeners){
            l.propertyChange(evt);
        }*/
        PropertyChangeEvent evt = new PropertyChangeEvent(
                this,
                "UPDATE_STATE",
                null,
                this);
        for(PropertyChangeListener l:listeners){
            l.propertyChange(evt);
        }
    }

    public synchronized String getCurrPlaying(){
        return Players.get(currPlaying-1).getNickName();
    }

    public Integer getWhoIsPlaying(){
        return currPlaying;
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

    /**
     * @author Eliahu Cohen
     * method that increase currPlaying every 2 minutes
     */
    private void turnTimer(Timer timer)  {
            TimerTask timerTask=new TimerTask() {
                @Override
                public void run() {
                    increaseCurrPlaying();
                }
            };
            timer = new Timer();
            timer.schedule(timerTask,120000);
    }

    public void setFinishedGame(boolean finishedGame) {
        this.finishedGame = finishedGame;
    }
    public void terminateGame(){
        if(finishedGame){
            PropertyChangeEvent evt = new PropertyChangeEvent(
                    this,
                    "GAME_CRASHED",
                    null,
                    "Someone crashed");
            for(PropertyChangeListener l:listeners){
                l.propertyChange(evt);
            }
        }
    }


}



