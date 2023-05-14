package it.polimi.ingsw.Network.Client.RMI;

import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Network.Client.ConnectionClient;

import java.io.IOException;
import java.rmi.Remote;

public class ClientConnectionRMI extends ConnectionClient implements Remote {
    @Override
    public void run() {

    }

    @Override
    public void sendMessage(ClientMessage message) {

    }

    @Override
    public ServerMessage receiveMessage() throws IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getPlayerName() {
        return null;
    }
}
