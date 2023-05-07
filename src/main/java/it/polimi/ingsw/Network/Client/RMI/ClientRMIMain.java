package it.polimi.ingsw.Network.Client.RMI;

import it.polimi.ingsw.Loggable;
import it.polimi.ingsw.Network.Client.ConnectionClient;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientRMIMain extends ConnectionClient {
    static int PORT = 1234;
    public void run() {
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", PORT);
            Loggable stub = (Loggable) registry.lookup("Loggable");
            Boolean logged = stub.login("Bob");
            System.out.println("Remote method invoked " + logged);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
                e.printStackTrace();
        }
    }
}

