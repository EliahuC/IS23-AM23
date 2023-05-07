package it.polimi.ingsw.Network.Server.RMI;

import it.polimi.ingsw.Loggable;
import it.polimi.ingsw.Network.Server.Server;

import java.rmi.AlreadyBoundException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerRMIMain extends Server implements Loggable {
    static int PORT = 1234;
    public static void main( String[] args )
    {
        System.out.println( "Hello from Server!" );
        Loggable stub = null;
        ServerRMIMain obj = new ServerRMIMain();
        try {
            stub = (Loggable) UnicastRemoteObject.exportObject(
                    obj, PORT);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        Registry registry = null;
        try {
            registry = LocateRegistry.createRegistry(PORT);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        try {
            registry.bind("Loggable", stub);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
        System.err.println("Server ready");
    }
    @Override
    public boolean login(String nick) throws RemoteException {
        System.out.println(nick + "is logging...");
        return false;
    }
    @Override
    public void logout(String nick) throws RemoteException {
        System.out.println("Player " + nick + " is logging out...");
    }

    @Override
    public void showMessage(String s) {

    }

    @Override
    public void run() {

    }
}
