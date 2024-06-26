package it.polimi.ingsw.Network.Server.RMI;

import it.polimi.ingsw.Network.Server.Server;


import java.io.IOException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


/**
 * RMI Server
 * @author Eliahu Cohen
 *
 */
public class RMIServerMain extends Server implements Runnable {
    private static int port = 22011;
    private static String IP;


    /**
     * Main of the server
     * @author Eliahu Cohen
     * @param args passed to the server
     *
     */
    public static void main(String[] args) {
         port = RMIparams.PORT;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        RMIServerMain serverMain = new RMIServerMain(port, IP);
        Thread thread = new Thread(serverMain);
        thread.start();
    }


    public RMIServerMain(int port, String IP) {
        RMIServerMain.port = port;
        RMIServerMain.IP =IP;
    }

    /**
     * Method to start the connection with the clients
     * @author Eliahu Cohen
     *
     */
    @Override
    public void run() {

        try {

            System.setProperty("java.rmi.server.hostname",IP);
            Registry registry = LocateRegistry.createRegistry(22011);
            showMessage("RMI server is ready on port: "+ port);
            ServerConnectionRMI rmiHandler = new ServerConnectionRMI();
            Naming.rebind("rmi://localhost:"+22011+"/RMIServer",rmiHandler);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
