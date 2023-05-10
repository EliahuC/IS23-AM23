package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Client.RMI.ClientRMIMain;
import it.polimi.ingsw.Network.Client.TCP.ClientConnectionTCP;
import it.polimi.ingsw.Printer;
import it.polimi.ingsw.view.*;


import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientMain implements Printer {
    private static View view;
    private static ConnectionClient connectionClient;




    public static void main(String[] args) throws IOException {
        argsParser(args);
        new Thread(view).start();
        new Thread(connectionClient).start();
    }

    private static void argsParser(String[] args) throws IOException {
        String temporaryStorage=args[0];
        temporaryStorage=temporaryStorage.toUpperCase();
        switch (temporaryStorage) {
            case "--CLI" -> view = new CLI();
            case "--GUI" -> view = new GUI();
            default -> System.out.println("View command isn't valid");
        }
        temporaryStorage=args[1];
        temporaryStorage=temporaryStorage.toUpperCase();
        switch (temporaryStorage){
            case "--RMI"-> connectionClient=new ClientRMIMain();
            case "--TCP"->{
                Socket socket=new Socket(InetAddress.getLocalHost(),2201);
                connectionClient=new ClientConnectionTCP(socket);
            }
            default -> System.out.println("Communication command isn't valid");
        }


    }


    @Override
    public void showMessage(String s) {
        System.out.println(s);
    }


}
