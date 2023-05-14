package it.polimi.ingsw.Network.Server.RMI;

import it.polimi.ingsw.Loggable;
import it.polimi.ingsw.Network.Server.Server;
import it.polimi.ingsw.Network.Server.TCP.TCPParams;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class RMIServerMain extends Server implements Loggable,Runnable {
    private static int PORT = 22011;

    public static void main(String[] args) {
        int port = TCPParams.PORT;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        RMIServerMain serverMain = new RMIServerMain(port);
        new Thread(serverMain).start();
    }

    @Override
    public boolean login(String nick) throws RemoteException {
        if (Server.connectedPlayers.contains(nick)) {
            return false;
        }
        Server.connectedPlayers.add(nick);
        return true;

    }

    @Override
    public void logout(String nick) throws RemoteException {
        Server.connectedPlayers.remove(nick);
    }

    @Override
    public void showMessage(String s) {

    }

    public RMIServerMain(int port) {
        PORT = port;
    }


    @Override
    public void run() {
        try {
            ServerConnectionRMI rmiHandler = new ServerConnectionRMI();
            Registry registry = LocateRegistry.createRegistry(PORT);
            registry.bind("AdrenalineServer", rmiHandler);
        } catch (IOException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
