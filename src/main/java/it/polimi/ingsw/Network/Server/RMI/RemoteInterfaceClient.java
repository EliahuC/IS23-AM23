package it.polimi.ingsw.Network.Server.RMI;

import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Network.Client.RMI.ClientConnectionRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteInterfaceClient extends Remote {
    void sendMessage(ClientMessage message)throws RemoteException;

    void receiveMessage(String msg) throws RemoteException;
    void login(String username, ClientConnectionRMI client) throws RemoteException;
    void disconnectMe() throws RemoteException;

    boolean getPing() throws RemoteException;
}