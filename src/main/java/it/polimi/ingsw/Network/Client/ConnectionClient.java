package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;

import java.io.IOException;

public abstract class ConnectionClient implements Runnable {
    public void sendMessage(ClientMessage message){};

    public ServerMessage receiveMessage() throws IOException, ClassNotFoundException {
        return null;
    };

    public String getPlayerName() {
        return null;
    };
}
