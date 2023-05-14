package it.polimi.ingsw.Network.Server.RMI;

import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Network.Client.RMI.ClientConnectionRMI;
import it.polimi.ingsw.Network.Server.ServerConnection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerConnectionRMI extends UnicastRemoteObject implements RemoteInterface, ServerConnection {
    private RMIConnection rmiConnection;
    protected ServerConnectionRMI() throws RemoteException {
    }

    @Override
    public void sendMessage(ServerMessage message) {

    }

    @Override
    public void receiveMessage() {

    }

    @Override
    public void login(String username, ClientConnectionRMI client) throws RemoteException {
       rmiConnection=new RMIConnection(client);

    }

    @Override
    public void disconnectMe() throws RemoteException {

    }

    @Override
    public void run() {

    }
}
