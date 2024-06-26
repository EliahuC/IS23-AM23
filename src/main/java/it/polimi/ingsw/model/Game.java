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
import java.util.stream.Collectors;

/**
 * Class that contains all the game logic and actions
 * @author Eliahu Cohen
 *
 */


public class Game implements Serializable {
    private LivingRoom livingRoom;
    private ArrayList<Player> Players;
    private transient final ArrayList<Player> disconnectedPlayers;

    private Integer currPlaying;
    private transient  Integer gameNumPlayers;

    private transient GameChecker gameChecker;
    private transient boolean startedGame=false;
    private transient final ArrayList<VirtualView> listeners ;
    private transient boolean finishedGame=false;


    /**
     * Constructor used when server starts a new game
     * @author Eliahu Cohen
     * @param L launcher to pass to the game checker
     * @param lobby of players that are part of the game
     *
     */
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
     * Constructor used when server starts an already existing game
     * @author Eliahu Cohen
     * @param lobby of players that are part of the game
     *
     */
   public Game(ArrayList<Player> lobby){
       this.Players=lobby;
       this.listeners = new ArrayList<>();
       this.disconnectedPlayers=new ArrayList<>();
       this.currPlaying=1;
       this.gameNumPlayers= lobby.size();
   }
    /**
     * method that sets to the livingroom all his listeners
     * @author Eliahu Cohen
     *
     */
    private void setLivingRoomListener() {
        for(Player p:Players){
            listeners.add((VirtualView) p.getListener());
        }
        livingRoom.setListeners(listeners);
    }

    public GameChecker getGameChecker() {
        return gameChecker;
    }

    public ArrayList<VirtualView> getListeners() {
        listeners.clear();
        for(Player p:Players){
            listeners.add((VirtualView) p.getListener());
        }
        return listeners;
    }

    /**
     * Method that sets what he needs and starts the game
     * @author Eliahu Cohen
     *
     */
    public synchronized void startGame(){
        livingRoom.start(Players.size());
        this.startedGame=true;
        ArrayList<Player> mixedPlayers;
        mixedPlayers=mixPlayersOrder(Players);
        Players.clear();
        Players.addAll(mixedPlayers);
        Players.get(0).setFirstPlayerSeat(true);
        for(int i = 1; i < Players.size(); i++){
            Players.get(i).setFirstPlayerSeat(false);
            Players.get(i).setLastRound(false);
        }
        for(Player p:Players){
            p.setPersonalGoalCard(new PersonalGoalCard());
        }

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
            //  turnTimer.cancel();
             //turnTimer(turnTimer);
              if (gameChecker.isRestorable(livingRoom.getBoard())){
                  livingRoom.restore();
                  PropertyChangeEvent evt = new PropertyChangeEvent(
                          this,
                          "BOARD_CHANGED",
                          null,
                          livingRoom.getBoard());
                  for(PropertyChangeListener l:listeners){
                      l.propertyChange(evt);
                  }
              }
              increaseCurrPlaying();
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
    public synchronized ArrayList<Player> endGame(){
        Player P;
         for (Player p : Players){
             p.endGamePoints();
         }
         P=whoWins();
         for(Player p : Players)
             if(Objects.equals(p.getNickName(), P.getNickName()))
                 p.setWinner(true);
        return Players;
    }

    /**
     * Method that puts the tiles in the player bookshelf, checks if the player have fulled his bookshelf
     * and if it's right sets the lastRound boolean to all the players
     * @author Eliahu Cohen
     * @param commands coordinates of the tiles to extract
     * @param column where the player want to put the tiles
     * @param order of the tiles to put
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
        if (gameChecker.getLastRound()) {
            isLastTurn();
        }
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
     * Method that checks if the game is finished and sets the last turn boolean to all the players
     * @author Eliahu Cohen
     *
     */
    private synchronized void isLastTurn() {
        if(currPlaying == Players.size()){
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
        for(int i=0; i<size; i++){
            if(coordinates.get(i) < 0 || coordinates.get(i) > 8)
                return false;
        }
        switch (size) {
            case 1 -> {
                if (gameChecker.isLegalAction(livingRoom.getBoardTile(commands.get(0), commands.get(1))))
                    return true;


            }
            case 2 -> {
                if (gameChecker.isLegalAction(livingRoom.getBoardTile(commands.get(0), commands.get(1)),
                        livingRoom.getBoardTile(commands.get(2), commands.get(3))))
                    return true;

            }
            case 3 -> {
                if (gameChecker.isLegalAction(livingRoom.getBoardTile(commands.get(0), commands.get(1)),
                        livingRoom.getBoardTile(commands.get(2), commands.get(3)),
                        livingRoom.getBoardTile(commands.get(4), commands.get(5))))
                    return true;
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
        switch (commands.size()) {
            case 4 -> {
                //tiles on the same row
                if (Objects.equals(commands.get(0), commands.get(2)))
                    sortRow(commands, temporaryCommands);
                    //tiles on the same column
                else if (Objects.equals(commands.get(1), commands.get(3)))
                    sortColumn(commands, temporaryCommands);
            }
            case 6 -> {
                //tiles on the same row
                if ((Objects.equals(commands.get(0), commands.get(2))) && (Objects.equals(commands.get(2), commands.get(4))))
                    sortRow(commands, temporaryCommands);
                    //tiles on the same column
                else if ((Objects.equals(commands.get(1), commands.get(3))) && Objects.equals(commands.get(3), commands.get(5)))
                    sortColumn(commands, temporaryCommands);
            }
        }
      return temporaryCommands;
    }

    /**
     * Method that returns to me the coordinates sorted based on the same column of the tiles
     * @author Eliahu Cohen
     * @param commands coordinates of the tiles the player wants to extract
     * @param temporaryCommands array list I want to fill with the sorted coordinates
     *
     */
    private void sortColumn(ArrayList<Integer> commands, ArrayList<Integer> temporaryCommands) {
        ArrayList<Integer> y=new ArrayList<>();
        int size=commands.size();
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
     * Method that returns to me the coordinates sorted based on the same row of the tiles
     * @author Eliahu Cohen
     * @param commands coordinates of the tiles the player wants to extract
     * @param temporaryCommands array list I want to fill with the sorted coordinates
     *
     */
    private void sortRow(ArrayList<Integer> commands, ArrayList<Integer> temporaryCommands) {
        ArrayList<Integer> x=new ArrayList<>();
        int size=commands.size();
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


    /**
     * @author Eliahu Cohen
     * @param numOfTiles to extract
     * @return true if there is enough space in the bookshelf for the tiles the player want to extract
     */
    private boolean checkNumber(int numOfTiles){
        gameChecker.checkColumnCapability(Players.get(currPlaying-1).getPlayerBookshelf());
        return gameChecker.getMaxPickableTiles(Players.get(currPlaying - 1).getPlayerBookshelf()) >= numOfTiles;
    }


    /**
     * The method checks with a functional method who is the player with the highest score.
     * if there is a draw the player will be selected manually
     * @author Eliahu Cohen
     * @return the player with the highest score
     */
    private synchronized Player whoWins(){
        Optional<Player> P=Players.stream().reduce((P1,P2) ->P1.getScore()>P2.getScore()? P1 : P2);
        if(P.isEmpty()) {

             P= Optional.ofNullable(checkManually());

        }
        return P.get();
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
     *  Method that checks if the player have completed some common goal cards
     * @author Eliahu Cohen
     *
     */
    private void checkCGC(){
        if(!Players.get(currPlaying-1).getPlayerBookshelf().isCommonGoalCard1Completed()||
                !Players.get(currPlaying-1).getPlayerBookshelf().isCommonGoalCard2Completed()){
            int completed;
            completed=checkAlreadyCompleted();
            int score=Players.get(currPlaying-1).getScore();
            Players.get(currPlaying-1).setScore(livingRoom.checkCG(Players.get(currPlaying-1).getPlayerBookshelf()));
            if(score<(Players.get(currPlaying-1).getScore())){
                if(completed==1) completed=2;
                else completed=1;
                String s="PLAYER "+Players.get(currPlaying-1).getNickName()+" COMPLETED THE COMMON GOAL CARD "+completed ;
                PropertyChangeEvent evt= new PropertyChangeEvent(
                        this,
                        "COMMON COMPLETED",
                        null,
                        s);
                Players.get(currPlaying-1).getListener().propertyChange(evt);
            }
            else{
                Players.get(currPlaying-1).setScore(score);
            }
        }
    }

    private int checkAlreadyCompleted() {
        if(Players.get(currPlaying-1).getPlayerBookshelf().isCommonGoalCard1Completed())return 1;

        return 2;
    }

    /**
     * method that increase the value of the currPlaying variable checking if the game is finished
     * @author Eliahu Cohen
     *
     */
    private synchronized void increaseCurrPlaying() {
        if(Objects.equals(currPlaying, gameNumPlayers) && !finishedGame) currPlaying=1;
        else if(currPlaying<gameNumPlayers && !finishedGame) currPlaying++;
        if(!finishedGame) {
            PropertyChangeEvent evt = new PropertyChangeEvent(
                    this,
                    "UPDATE_STATE",
                    null,
                    this);
            for (PropertyChangeListener l : listeners) {
                l.propertyChange(evt);
            }
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



    public void setFinishedGame(boolean finishedGame) {
        this.finishedGame = finishedGame;
    }

    /**
     * Method that ends the game when a player crash
     * @author Eliahu Cohen
     *
     */
    public void terminateGame(){
        if(finishedGame){
            PropertyChangeEvent evt = new PropertyChangeEvent(
                    this,
                    "GAME_CRASHED",
                    null,
                    "Someone crashed");
            for(PropertyChangeListener l:listeners){
                try {
                    l.propertyChange(evt);
                }catch (NullPointerException ignored){}
            }
        }
    }

    public void setLivingRoom(LivingRoom livingRoom) {
        this.livingRoom = livingRoom;
    }

    public void setPlayers(ArrayList<Player> players) {
        Players = players;
    }


    public void setStartedGame(boolean startedGame) {
        this.startedGame = startedGame;
    }

    public void setCurrPlaying(Integer currPlaying) {
        this.currPlaying = currPlaying;
    }

    public boolean isFinishedGame() {
        return finishedGame;
    }
}




