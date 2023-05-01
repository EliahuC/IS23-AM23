package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Loggable;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientRMIMain {
    static int PORT = 1234;
    public static void main(String[] args) {
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

