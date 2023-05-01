package it.polimi.ingsw.Network.Server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;
public class ServerTCPMain extends Server {
    private ServerSocket serverSocket;
    private final ArrayList<String> loggedUsers =new ArrayList<>();
    private final ArrayList<VirtualView> virtualViews=new ArrayList<>();

    private int port;

    public ServerTCPMain(){
        this.port=2201;
    }
    @Override
    public void run() {
        showMessage("INSERT NEW PORT OR PRESS ENTER TO USE THE DEFAULT PORT");
        Scanner input = new Scanner(System.in);
        String newPort = input.nextLine();
        if (newPort.length() != 0) port = Integer.parseInt(newPort);
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        showMessage("Server is done!");
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
            //da aggiungere listener e richiesta
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    }





    @Override
    public void showMessage(String s) {
        System.out.println("s");
    }

    @Override
    public boolean login(String nick) throws RemoteException {
        if(loggedUsers.contains(nick))return false;
        loggedUsers.add(nick);
        return true;
    }

    @Override
    public void logout(String nick) throws RemoteException {
        loggedUsers.remove(nick);
    }

    public static void main(String[] args) {
        ServerTCPMain serverMain = new ServerTCPMain();
        new Thread(serverMain).start();
    }
}