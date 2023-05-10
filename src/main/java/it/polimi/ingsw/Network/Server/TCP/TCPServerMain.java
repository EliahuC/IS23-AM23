package it.polimi.ingsw.Network.Server.TCP;


import it.polimi.ingsw.Network.Server.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TCPServerMain extends Server {
    private ServerSocket serverSocket;

    private final ArrayList<VirtualView> virtualViews=new ArrayList<>();

    private int port;

    public TCPServerMain(){
        this.port=2201;
    }
    @Override
    public void run() {
        //seleziono port alternativa per server
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        showMessage("Server is done!");
        //Accettazione client
        while(true){
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
                showMessage("Client successfully connected");
                ServerConnectionToClient serverConnectionToClient = new ServerConnectionToClient(clientSocket);
                new Thread(serverConnectionToClient).start();
                VirtualView virtualView=new VirtualView(serverConnectionToClient);
                virtualViews.add(virtualView);
                serverConnectionToClient.addVirtualView(virtualView);
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
        TCPServerMain serverMain = new TCPServerMain();
        new Thread(serverMain).start();
    }
}
