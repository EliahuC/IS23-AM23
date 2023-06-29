package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;

/**
 * interface that will be implemented from RMI connection and TCP connection
 * @author Eliahu Cohen
 *
 */
public interface ServerConnection extends Runnable {
    String getNamePlayer();

     static void removeVoidLobby(Lobby lobby) {
        Server.lobbies.remove(lobby);
    }
    void sendMessage(ServerMessage serverMessage,String nickname);



}
