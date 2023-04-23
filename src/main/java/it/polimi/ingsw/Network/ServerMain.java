package it.polimi.ingsw.Network;
import it.polimi.ingsw.Loggable;
import it.polimi.ingsw.view.Printer;


import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;
public class ServerMain implements Runnable, Printer, Loggable {
    private ServerSocket serverSocket;
    private ArrayList<String> loggedUser=new ArrayList<>();

    private int port;

    public ServerMain(){
        this.port=49521;
    }
    @Override
    public void run() {
        showMessage("INSERT NEW PORT OR PRESS ENTER TO USE THE DEFAULT PORT");
        Scanner input = new Scanner(System.in);
        String newPort = input.nextLine();
        if(newPort.length()!=0) port = Integer.parseInt(newPort);
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        showMessage("Accepting..");
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
            showMessage("Client successfully connected");
            // Operazioni da aggiungere
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            input = new Scanner(
                    new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showMessage(String s) {
        System.out.println("s");
    }

    @Override
    public boolean login(String nick) throws RemoteException {
        if(loggedUser.contains(nick))return false;
        loggedUser.add(nick);
        return true;
    }

    @Override
    public void logout(String nick) throws RemoteException {
        loggedUser.remove(nick);
    }

    public static void main(String[] args) {
        ServerMain serverMain = new ServerMain();
        new Thread(serverMain).start();
    }
}
