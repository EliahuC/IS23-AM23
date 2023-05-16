package it.polimi.ingsw.Network.Server.RMI;

import it.polimi.ingsw.Network.Client.RMI.RemoteInterfaceClient;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Eliahu Cohen
 * Interface used to implement RMI server connection methods
 */
public interface RemoteInterface extends Remote {

     void receiveMessage(String msg, RemoteInterfaceClient client) throws RemoteException;


    boolean getPing() throws RemoteException;
}
