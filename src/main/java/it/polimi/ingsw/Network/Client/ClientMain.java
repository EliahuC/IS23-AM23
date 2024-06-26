package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Printer;
import it.polimi.ingsw.view.*;
import it.polimi.ingsw.view.cli.CLI;
import it.polimi.ingsw.view.gui.GUIMain;

import java.io.IOException;
import java.util.Scanner;

/**
 * Class to start the Client view
 * @author Eliahu Cohen
 *
 */
public class ClientMain implements Printer {
    private static View view;


    public static void main(String[] args) throws IOException {
        argsParser(args);
        if(view instanceof GUIMain){
            System.out.println("INSERT THE IPv4 ADDRESS");
            Scanner input = new Scanner(System.in);
            String IP = input.nextLine();
            view.passIP(IP);
        }
        new Thread(view).start();
    }

    /**
     * Parse the argument received from shell and starts the right view
     * @author Eliahu Cohen
     * @param args in input
     *
     */
    private static void argsParser(String[] args) {
        String temporaryStorage=args[0];
        temporaryStorage=temporaryStorage.toUpperCase();
        switch (temporaryStorage) {
            case "--CLI" -> view = new CLI();
            case "--GUI" -> view = new GUIMain();
            default -> System.out.println("View command isn't valid");
        }


    }


    @Override
    public void showMessage(String s) {
        System.out.println(s);
    }


}
