package it.polimi.ingsw.Network.Server.TCP;


import it.polimi.ingsw.Network.Server.Server;
import it.polimi.ingsw.Network.Server.VirtualView;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author Eliahu Cohen
 * TCP SERVER
 */
public class TCPServerMain extends Server implements Runnable {
    private ServerSocket serverSocket;

    private final ArrayList<VirtualView> virtualViews=new ArrayList<>();

    private final int port;

    public TCPServerMain(int port){
        this.port = port;
    }

    /**
     * @author Eliahu Cohen
     * Method to start the server
     */
    public void run() {

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        showMessage("TCPServer is started !");

        //Client Connection
        while(true){
            Socket clientSocket ;
            try {
                clientSocket = serverSocket.accept();
                showMessage("Client TCP successfully connected");
                ServerConnectionTCP serverConnectionTCP = new ServerConnectionTCP(clientSocket);
                new Thread(serverConnectionTCP).start();
                VirtualView virtualView=new VirtualView(serverConnectionTCP,null);
                virtualViews.add(virtualView);
                serverConnectionTCP.addVirtualView(virtualView);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * @author Eliahu Cohen
     * @param args to pass to the main.
     */
    public static void main(String[] args) {

        int port = TCPParams.PORT;
        if (args.length > 0) {
            port = Integer.parseInt( args[0] );
        }
        TCPServerMain serverMain = new TCPServerMain(port);
        new Thread(serverMain).start();
// NO new Thread(serverMain).start();
    }
}
