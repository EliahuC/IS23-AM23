package it.polimi.ingsw.Network.Server.RMI;

import it.polimi.ingsw.Loggable;
import it.polimi.ingsw.Network.Server.Server;
import it.polimi.ingsw.Network.Server.TCP.TCPParams;
import it.polimi.ingsw.Network.Server.TCP.TCPServerMain;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServerMain extends Server implements Loggable,Runnable {
    private static int PORT =22011;
    public static void main( String[] args )
    {
        int port = TCPParams.PORT;
        if (args.length > 0) {
            port = Integer.parseInt( args[0] );
        }
        RMIServerMain serverMain = new RMIServerMain(port);
        new Thread(serverMain).start();
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
    public RMIServerMain(int port){
        PORT=port;
    }


    @Override
    public void run() {
        System.out.println( "Hello from Server!" );
        Loggable stub = null;
        RMIServerMain server = new RMIServerMain(PORT);
        try {
            stub = (Loggable) UnicastRemoteObject.exportObject(
                    server, PORT);
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
            if(registry!=null)
                registry.bind("Loggable", stub);
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
        System.err.println("Server ready");
    }
}
