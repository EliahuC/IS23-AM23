package it.polimi.ingsw.Network.Server.RMI;

import it.polimi.ingsw.Network.Server.Server;


import java.io.IOException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


/**
 * @author Eliahu Cohen
 * RMI Server
 */
public class RMIServerMain extends Server implements Runnable {
    private static int port = 22011;


    /**
     * @author Eliahu Cohen
     * @param args passed to the server
     * Main of the server
     */
    public static void main(String[] args) {
         port = RMIparams.PORT;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        RMIServerMain serverMain = new RMIServerMain(port);
        Thread thread = new Thread(serverMain);
        thread.start();
    }


    public RMIServerMain(int port) {
        RMIServerMain.port = port;
    }

    /**
     * @author Eliahu Cohen
     * Method to start the connection with the clients
     */
    @Override
    public void run() {

        try {
            Registry registry = LocateRegistry.createRegistry(22011);
            showMessage("RMI server is ready on port: "+ port);
            ServerConnectionRMI rmiHandler = new ServerConnectionRMI();
            Naming.rebind("rmi://localhost:"+22011+"/RMIServer",rmiHandler);
            //showMessage("Client successfully connected");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
