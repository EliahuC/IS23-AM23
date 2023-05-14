package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;

public interface ServerConnection extends Runnable {
    public abstract void sendMessage(ServerMessage message);

}
