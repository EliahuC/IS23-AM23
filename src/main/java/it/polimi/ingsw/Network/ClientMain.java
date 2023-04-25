package it.polimi.ingsw.Network;

import it.polimi.ingsw.Printer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientMain implements Printer {




    public static void main(String[] args) {

    }


    @Override
    public void showMessage(String s) {
        System.out.println(s);
    }


}
