package it.polimi.ingsw.Network.Server;
import it.polimi.ingsw.Loggable;
import it.polimi.ingsw.Printer;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;
public class ServerMain implements Runnable, Printer, Loggable {
    private ServerSocket serverSocket;
    private final ArrayList<String> loggedUsers =new ArrayList<>();

    private int port;

    public ServerMain(){
        this.port=49521;
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
        ServerMain serverMain = new ServerMain();
        new Thread(serverMain).start();
    }
}
