package it.polimi.ingsw.Network.Server.RMI;

import it.polimi.ingsw.Network.Server.Server;
import it.polimi.ingsw.Network.Server.VirtualView;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

/**
 * @author Eliahu Cohen
 * RMI Server
 */
public class RMIServerMain extends Server implements Runnable {
    private static int PORT = 22011;
    private final ArrayList<VirtualView> virtualViews=new ArrayList<>();

    /**
     * @author Eliahu Cohen
     * @param args passed to the server
     * Main of the server
     */
    public static void main(String[] args) {
        int port = RMIparams.PORT;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        RMIServerMain serverMain = new RMIServerMain(port);
        Thread thread = new Thread(serverMain);
        thread.start();
    }



    @Override
    public void showMessage(String s) {
        System.out.println(s);

    }

    public RMIServerMain(int port) {
        PORT = port;
    }

    /**
     * @author Eliahu Cohen
     * Method to start the connection with the clients
     */
    @Override
    public void run() {

        try {
            Registry registry = LocateRegistry.createRegistry(PORT);
            showMessage("Server is ready!!");
            ServerConnectionRMI rmiHandler = new ServerConnectionRMI();
            Naming.rebind("rmi://localhost:"+22011+"/RMIServer",rmiHandler);
            //showMessage("Client successfully connected");
            VirtualView virtualView=new VirtualView(rmiHandler);
            virtualViews.add(virtualView);
            rmiHandler.addVirtualView(virtualView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
