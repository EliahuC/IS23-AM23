package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;

/**
 * @author Eliahu Cohen
 * interface that will be implemented from RMI connection and TCP connection
 */
public interface ServerConnection  {

     static void removeVoidLobby(Lobby lobby) {
        Server.lobbies.remove(lobby);
    }
    void sendMessage(ServerMessage serverMessage);

     void receiveMessage(String s);

}
