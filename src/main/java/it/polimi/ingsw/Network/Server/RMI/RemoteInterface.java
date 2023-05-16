package it.polimi.ingsw.Network.Server.RMI;

import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Network.Client.RMI.ClientConnectionRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface RemoteInterface extends Remote {

     void receiveMessage(String msg, RemoteInterfaceClient client) throws RemoteException;


    boolean getPing() throws RemoteException;
}
