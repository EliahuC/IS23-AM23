package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author Eliahu Cohen
 * Abstract class extended from RMI and TCP client
 */
public abstract class ConnectionClient extends UnicastRemoteObject implements Runnable {
    public ConnectionClient() throws RemoteException {
        super();
    }

    public void sendMessage(ClientMessage message){};

    public void receiveMessage(String s){};

    public String getPlayerName() {
        return null;
    };
}
