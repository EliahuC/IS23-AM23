package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Printer;
import it.polimi.ingsw.model.player.Player;

import java.util.Objects;

/**
 * Disconnection handler
 * @author Eliahu Cohen
 *
 */
public class DisconnectionHandler implements Printer {
    private final Lobby lobby;

    public DisconnectionHandler(Lobby lobby) {
        this.lobby = lobby;
    }

    /**
     * Method that handle to the disconnection of a player
     * @author Eliahu Cohen
     * @param nickname of the player
     *
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
     * @param playerName name player
     * @return true if the player is trying to reconnect
     */
    public synchronized boolean checkReconnection(String playerName){
        for(Player p:lobby.getDisconnectedPlayers()){
            if (Objects.equals(p.getNickName(), playerName)) return true;
        }
        return false;
    }

    /**
     * Method that reconnect the player
     * @author Eliahu Cohen
     * @param playerName name player
     *
     */
    public synchronized void clientReconnection(String playerName){
        for(Player p:lobby.getDisconnectedPlayers()){
            if (Objects.equals(p.getNickName(), playerName)){
                lobby.getDisconnectedPlayers().remove(p);
            }
        }
    }

    /**
     * Method to delete a lobby
     * @author Eliahu Cohen
     *
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
