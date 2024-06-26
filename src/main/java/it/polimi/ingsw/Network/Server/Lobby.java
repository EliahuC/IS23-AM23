package it.polimi.ingsw.Network.Server;

import com.google.gson.Gson;
import it.polimi.ingsw.GameSavings;
import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.ServerToClient.ErrorMessage;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Messages.ServerToClient.ValidMoveMessage;
import it.polimi.ingsw.Savings;
import it.polimi.ingsw.controller.ControllerCoordinator;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.board.goalCards.CommonGoalCard;
import it.polimi.ingsw.model.player.Player;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Lobby concept class
 * @author Eliahu Cohen
 *
 */
public class Lobby implements Serializable {
    private transient File myObj;
    private final Integer NumPlayersLobby;
    private transient ArrayList<ServerConnection> connections;
    private transient ControllerCoordinator controllerCoordinator;
    private final ArrayList<String> joinedUsers;
    private Boolean startedGame=false;
    private Boolean fullLobby=false;
    private transient String saveFilePath= "../Savings"; //TODO *
    //private transient String saveFilePath= "Savings";
    private final Integer idLobby;
    public Lobby(Integer numPlayersLobby,Integer ID){
        this.NumPlayersLobby=numPlayersLobby;
        this.idLobby=ID;
        connections=new ArrayList<>();
        this.joinedUsers=new ArrayList<>();
        this.controllerCoordinator=new ControllerCoordinator();
        setSavesOfTheLobby();
    }

    /**
     * method that adds a user to the lobby
     * @author Eliahu Cohen
     * @param serverConnection connection with the client
     * @param s nickname
     * @param view
     *
     */
    public synchronized void addUser(ServerConnection serverConnection, String s, VirtualView view){
        if(connections.size()==NumPlayersLobby) return;
        connections.add(serverConnection);
        joinedUsers.add(s);
        controllerCoordinator.joinPlayer(s,view);
        if(controllerCoordinator.getConnectedPlayers().size()==NumPlayersLobby){
            startGameLobby();
            fullLobby=true;
        }
    }

    public synchronized ArrayList<String> getJoinedUsers() {
        return joinedUsers;
    }

    /**
     * Method that react to the client move
     * @author Eliahu Cohen
     * @param message received from client
     * @return the server response to the client
     *
     */
    public synchronized Message receiveMessage(ClientMessage message){

        if(message.getCategory()== Message.MessageCategory.START_GAME){
            if(joinedUsers.size()!=1){
                startGameLobby();
                return new ValidMoveMessage();
            }
            return new ErrorMessage();
        }
        ServerMessage returnMessage= (ServerMessage) controllerCoordinator.setMessage(message);
        if(returnMessage.getCategory()== Message.MessageCategory.VALID_MESSAGE){
            saveGame((ValidMoveMessage) returnMessage);
        }
        if(returnMessage.getCategory()== Message.MessageCategory.END_GAME_MESSAGE){
            Server.startedLobbies.remove(this);
            return null;
        }
        return returnMessage;
    }


    /**
     * Method that saves the current status of the game
     * @author Eliahu Cohen
     * @param returnMessage return message
     *
     */
    private void saveGame(ValidMoveMessage returnMessage) {
        Gson gson=new Gson();
        if(returnMessage.getSavings()==null) return;
        try{
            Savings savings=new Savings(this);
            savings.saveGame(returnMessage.getSavings());
            String save =gson.toJson(savings);
            try (FileWriter writer = new FileWriter(myObj, false)) {
                writer.write(save);
            }
            System.out.println("The game is saved");
        }catch (IOException e){
            System.out.println("There is a problem with the savings");
        }
    }

    /**
     * Logout the player from the lobby
     * @author Eliahu Cohen
     * @param s nickname
     *
     */
    public synchronized void logoutFromLobby(String s){
        joinedUsers.remove(s);
        controllerCoordinator.getConnectedPlayers().remove(s);
    }

    /**
     * starts the game of the lobby
     * @author Eliahu Cohen
     *
     */
    public synchronized void startGameLobby(){
        startedGame=true;
        controllerCoordinator.startGame();


    }

    /**
     * Method that sets the saves of the game
     * @author Eliahu Cohen
     *
     */
    private void setSavesOfTheLobby() {
        String dirPath = "../Savings"; //TODO *
        //String dirPath = "Savings";
        if (!Files.exists(Path.of(dirPath))){
            try {
                Files.createDirectories(Path.of(dirPath));
            } catch (IOException e) {
                System.out.println("Could not create directory");
            }
        }
        String fileName="/Lobby"+ idLobby +".json";
        saveFilePath=dirPath+fileName;
        myObj=new File(saveFilePath);
    }

    public synchronized Boolean getStartedGame() {
        return startedGame;
    }

    public Integer getNumPlayersLobby() {
        return NumPlayersLobby;
    }

    public synchronized ArrayList<ServerConnection> getConnections() {
        return connections;
    }

    /**
     * send a message to all the lobby
     * @author Eliahu Cohen
     * @param message to send.
     *
     */
    public synchronized void sendMessageToAllTheLobby(ServerMessage message){
        for(ServerConnection s:connections){
            s.sendMessage(message,s.getNamePlayer());
        }
    }
    public ArrayList<Player> getDisconnectedPlayers(){
        return controllerCoordinator.getDisconnectedPlayers();
    }

    /**
     * method to delete the lobby
     * @author Eliahu Cohen
     *
     */
    protected synchronized void deleteLobby() {
        ServerConnection.removeVoidLobby(this);
    }

    public Player getPlayer(String nickname) {
        return controllerCoordinator.getConnectedPlayer(nickname);
    }

    public Boolean getFullLobby() {
        return fullLobby;
    }

    public void setFullLobby(Boolean fullLobby) {
        this.fullLobby = fullLobby;
    }

    public Integer getIdLobby() {
        return idLobby;
    }

    /**
     * @author Eliahu Cohen
     * @param serverConnection connection of the player
     * @param s name of the player
     */
    public void reconnectPlayer(ServerConnection serverConnection, String s){
        connections.add(serverConnection);
        joinedUsers.add(s);
    }

    /**
     * Method that ends the game if a player crash
     * @author Eliahu Cohen
     *
     */
    public void endGame() {
        controllerCoordinator.endgame();
    }

    /**
     * Method that reloads the game
     * @author Eliahu Cohen
     * @param gameSavings taken from the server
     *
     */
    public void reloadGame(GameSavings gameSavings) {
        controllerCoordinator=new ControllerCoordinator();
        connections=new ArrayList<>();
        setSavesOfTheLobby();
        controllerCoordinator.setGame(gameSavings);
    }

    /**
     * method to restart a lobby saved on the disk
     * @author Eliahu Cohen
     *
     */
    public void restartGame() {
        this.startedGame=true;
        Game game=controllerCoordinator.getGameController().getGame();
        game.getLivingRoom().SetBoard();
        game.getLivingRoom().setGameChecker(controllerCoordinator.getGameController().getGame().getGameChecker());
        game.getLivingRoom().setListeners(controllerCoordinator.getGameController().getGame().getListeners());
        game.getLivingRoom().setCommonGoalCard1(new CommonGoalCard(game.getLivingRoom().getIdCGC1()));
        game.getLivingRoom().setCommonGoalCard2(new CommonGoalCard(game.getLivingRoom().getIdCGC2()));
        controllerCoordinator.setStartedGame(controllerCoordinator.getGameController().getGame().isStartedGame());
        game.getLivingRoom().setLauncher(controllerCoordinator.getGameController().getLauncher());

        PropertyChangeEvent evt = new PropertyChangeEvent(
                this,
                "GAME_STARTED",
                null,
                game);

        for(int i=0;i<controllerCoordinator.getConnectedPlayers().size();i++){
            PropertyChangeListener l=controllerCoordinator.getConnectedPlayers().get(i).getListener();
            l.propertyChange(evt);
        }

    }

    /**
     * Method that remove a player when crash
     * @author Eliahu Cohen
     * @param serverConnection of the player
     * @param namePlayer name of the player
     *
     */
    public void removePlayer(ServerConnection serverConnection, String namePlayer) {
        for(Player p: controllerCoordinator.getConnectedPlayers())
            if(namePlayer.equals(p.getNickName())){
                controllerCoordinator.getConnectedPlayers().remove(p);
                joinedUsers.remove(namePlayer);
                connections.remove(serverConnection);
                return;
            }
    }
}
