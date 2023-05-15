package it.polimi.ingsw.Network.Server.RMI;

import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Network.Client.RMI.ClientConnectionRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface RemoteInterface extends Remote {
     void sendMessage(ServerMessage message)throws RemoteException;


    void login(String username, ClientConnectionRMI client) throws RemoteException;
    void disconnectMe() throws RemoteException;
}
