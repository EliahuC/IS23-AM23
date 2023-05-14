package it.polimi.ingsw.Network.Server.TCP;

import com.google.gson.Gson;
import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.ServerToClient.ErrorMessage;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Messages.ServerToClient.GameIsStartingMessage;
import it.polimi.ingsw.Messages.ServerToClient.ValidMoveMessage;
import it.polimi.ingsw.Savings;
import it.polimi.ingsw.controller.ControllerCoordinator;
import it.polimi.ingsw.model.player.Player;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Lobby {

    private final Integer NumPlayersLobby;
    private final ArrayList<ServerConnectionTCP> connections;
    private final ControllerCoordinator controllerCoordinator;
    private final ArrayList<String> joinedUsers;
    private Boolean startedGame=false;
    private Boolean fullLobby=false;
    private String saveFilePath="c/Program Files (x86)/GitHub/IS23-AM23/SavedGames/";
    private final Integer idLobby;
    public Lobby(Integer numPlayersLobby,Integer ID){
        this.NumPlayersLobby=numPlayersLobby;
        this.idLobby=ID;
        connections=new ArrayList<>();
        this.joinedUsers=new ArrayList<>();
        this.controllerCoordinator=new ControllerCoordinator();
    }
    public synchronized void addUser(ServerConnectionTCP serverConnectionTCP, String s, VirtualView view){
        if(connections.size()==NumPlayersLobby) return;
        connections.add(serverConnectionTCP);
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

    public synchronized Message receiveMessage(ClientMessage message){
        for(Player p:getDisconnectedPlayers()) {
            if(p.getNickName().equals(message.getNickname()))
            {
                ErrorMessage errorMessage= new ErrorMessage();
                errorMessage.setReturnMessage("The player is disconnected from the game, please log in first.");
                return errorMessage;
            }
        }
        if(message.getCategory()== Message.MessageCategory.START_GAME){
            if(joinedUsers.size()!=1){
                startGameLobby();
                return new ValidMoveMessage();
            }
            return new ErrorMessage();
        }
        ValidMoveMessage returnMessage= (ValidMoveMessage) controllerCoordinator.setMessage(message);
        if(returnMessage.getCategory()== Message.MessageCategory.VALID_MESSAGE){
            saveGame(returnMessage);
        }
        if(returnMessage.getCategory()== Message.MessageCategory.END_GAME_MESSAGE){
            deleteFile();
        }
        return returnMessage;
    }

    private void deleteFile() {
        File file=new File(saveFilePath);
        if(!file.exists())
            System.out.println("There isn't any saved game");
        boolean success = file.delete();
        if(!success)
            throw new IllegalArgumentException("Delete failed");
        System.out.println("File correctly deleted");
    }

    private void saveGame(ValidMoveMessage returnMessage) {
        Gson gson=new Gson();
        if(returnMessage.getSavings()==null) return;
        try{
            Savings savings=new Savings(this);
            savings.saveGame(returnMessage.getSavings());
            Files.writeString(Path.of("./" +saveFilePath) ,gson.toJson(savings) );
            System.out.println("The game is saved");
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public synchronized void logoutFromLobby(String s){
        joinedUsers.remove(s);
        controllerCoordinator.getConnectedPlayers().remove(s);
    }

    public synchronized void startGameLobby(){
        startedGame=true;
        controllerCoordinator.startGame();
        setSavesOfTheLobby();
        sendMessageToAllTheLobby(new GameIsStartingMessage());
    }

    private void setSavesOfTheLobby() {
        String fileName="Lobby"+String.valueOf(idLobby)+".txt";
        saveFilePath=saveFilePath+fileName;
        File myObj=new File(saveFilePath);
    }

    public synchronized Boolean getStartedGame() {
        return startedGame;
    }

    public Integer getNumPlayersLobby() {
        return NumPlayersLobby;
    }

    public synchronized ArrayList<ServerConnectionTCP> getConnections() {
        return connections;
    }
    public synchronized void sendMessageToAllTheLobby(ServerMessage message){
        for(ServerConnectionTCP s:connections){
            s.sendMessage(message);
        }
    }
    public ArrayList<Player> getDisconnectedPlayers(){
        return controllerCoordinator.getDisconnectedPlayers();
    }


    protected synchronized void deleteLobby() {
        ServerConnectionTCP.removeVoidLobby(this);
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
}
