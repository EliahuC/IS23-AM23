package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;

/**
 * @author Eliahu Cohen
 * interface that will be implemented from RMI connection and TCP connection
 */
public interface ServerConnection  {
    String getNamePlayer();

     static void removeVoidLobby(Lobby lobby) {
        Server.lobbies.remove(lobby);
    }
    void sendMessage(ServerMessage serverMessage,String nickname);

     void receiveMessage(String s);

}
