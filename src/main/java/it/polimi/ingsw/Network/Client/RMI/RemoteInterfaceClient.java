package it.polimi.ingsw.Network.Client.RMI;

import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Network.Client.RMI.ClientConnectionRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface implemented in client connection RMI to use RMI
 * @author Eliahu Cohen
 *
 */
public interface RemoteInterfaceClient extends Remote {
    void sendMessage(ClientMessage message)throws RemoteException;

    void receiveMessage(String msg) throws RemoteException;


    boolean getPing() throws RemoteException;
}