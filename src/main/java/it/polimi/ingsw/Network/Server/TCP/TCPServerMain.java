package it.polimi.ingsw.Network.Server.TCP;


import it.polimi.ingsw.Network.Server.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TCPServerMain extends Server implements Runnable {
    private ServerSocket serverSocket;

    private final ArrayList<VirtualView> virtualViews=new ArrayList<>();

    private final int port;

    public TCPServerMain(int port){
        this.port = port;
    }

    public void run() {
        //seleziono port alternativa per server

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        showMessage("Server is started !");

        //Accettazione client
        while(true){
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
                showMessage("Client successfully connected");
                ServerConnectionTCP serverConnectionTCP = new ServerConnectionTCP(clientSocket);
                new Thread(serverConnectionTCP).start();
                VirtualView virtualView=new VirtualView(serverConnectionTCP);
                virtualViews.add(virtualView);
                serverConnectionTCP.addVirtualView(virtualView);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    @Override
    public void showMessage(String s) {
        System.out.println(s);
    }



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
