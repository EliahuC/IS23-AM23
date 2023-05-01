package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Server.*;
import it.polimi.ingsw.Printer;
import it.polimi.ingsw.view.*;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientMain implements Printer {
    private static View view;
    private static Server server;




    public static void main(String[] args) {
        argsParser(args);
        new Thread(view).start();
        new Thread(server).start();
    }

    private static void argsParser(String[] args) {
        String temporaryStorage=args[0];
        temporaryStorage.toUpperCase();
        switch (temporaryStorage) {
            case "--CLI" -> view = new CLI();
            case "--GUI" -> view = new GUI();
            default -> System.out.println("View command isn't valid");
        }
        temporaryStorage=args[1];
        switch (temporaryStorage){
            case "--RMI"-> server=new ServerRMIMain();
            case "--TCP"-> server=new ServerTCPMain();
            default -> System.out.println("Communication command isn't valid");
        }


    }


    @Override
    public void showMessage(String s) {
        System.out.println(s);
    }


}
