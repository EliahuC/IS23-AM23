package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Server.Lobby;
import it.polimi.ingsw.Printer;
import it.polimi.ingsw.model.player.Player;

import java.util.Objects;

/**
 * @author Eliahu Cohen
 * Disconnection handler
 */
public class DisconnectionHandler implements Printer {
    private final Lobby lobby;

    public DisconnectionHandler(Lobby lobby) {
        this.lobby = lobby;
    }

    /**
     * @author Eliahu Cohen
     * @param nickname of the player
     * Method that handle to the disconnection of a player
     */
    public synchronized void disconnectionHandling(String nickname){
        lobby.getJoinedUsers().remove(nickname);
        if(lobby.getStartedGame()){
            lobby.getDisconnectedPlayers().add(lobby.getPlayer(nickname));
            lobby.setFullLobby(false);
        }
        showMessage(nickname + " is disconnected");
    }

    /**
     * @author Eliahu Cohen
     * @param playerName
     * @return true if the player is trying to reconnect
     */
    public synchronized boolean checkReconnection(String playerName){
        for(Player p:lobby.getDisconnectedPlayers()){
            if (Objects.equals(p.getNickName(), playerName)) return true;
        }
        return false;
    }

    /**
     * @author Eliahu Cohen
     * @param playerName
     * Method that reconnect the player
     */
    public synchronized void clientReconnection(String playerName){
        for(Player p:lobby.getDisconnectedPlayers()){
            if (Objects.equals(p.getNickName(), playerName)){
                lobby.getDisconnectedPlayers().remove(p);
            }
        }
    }

    /**
     * @author Eliahu Cohen
     * Method to delete a lobby
     */
    protected synchronized  void deleteLobby(){
        if(lobby.getConnections().size()==0)
            lobby.deleteLobby();
    }

    @Override
    public void showMessage(String s) {
        System.out.println(s);
    }
}
