package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Printer;
import it.polimi.ingsw.model.player.Player;

import java.util.Objects;

public class DisconnectionHandler implements Printer {
    private final Lobby lobby;

    public DisconnectionHandler(Lobby lobby) {
        this.lobby = lobby;
    }

    public synchronized void disconnectionHandling(String nickname){
        lobby.getJoinedUsers().remove(nickname);
        if(lobby.getStartedGame()){
            lobby.getDisconnectedPlayers().add(lobby.getPlayer(nickname));
            lobby.setFullLobby(false);
        }
        showMessage(nickname + " is disconnected");
    }

    public synchronized boolean checkReconnection(String playerName){
        for(Player p:lobby.getDisconnectedPlayers()){
            if (Objects.equals(p.getNickName(), playerName)) return true;
        }
        return false;
    }

    public synchronized void clientReconnection(String playerName){
        for(Player p:lobby.getDisconnectedPlayers()){
            if (Objects.equals(p.getNickName(), playerName)){
                lobby.getDisconnectedPlayers().remove(p);
            }
        }
    }

    protected synchronized  void deleteLobby(){
        if(lobby.getConnections().size()==0)
            lobby.deleteLobby();
    }

    @Override
    public void showMessage(String s) {
        System.out.println(s);
    }
}
