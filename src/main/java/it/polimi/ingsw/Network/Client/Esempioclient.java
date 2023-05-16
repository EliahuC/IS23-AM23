package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Client.RMI.ClientConnectionRMI;

import java.rmi.RemoteException;

public class Esempioclient {
    public static void main(String args[]) throws RemoteException {
        ClientConnectionRMI connectionRMI=new ClientConnectionRMI("Palle");
        connectionRMI.run();
    }
}
