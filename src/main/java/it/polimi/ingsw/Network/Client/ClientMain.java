package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Printer;
import it.polimi.ingsw.view.*;
import it.polimi.ingsw.view.cli.CLI;
import it.polimi.ingsw.view.gui.GUIMain;

import java.io.IOException;

/**
 * @author Eliahu Cohen
 * Class to start the Client view
 */
public class ClientMain implements Printer {
    private static View view;





    public static void main(String[] args) throws IOException {
        argsParser(args);
        new Thread(view).start();
    }

    /**
     * @author Eliahu Cohen
     * @param args in input
     * Parse the argument received from shell and starts the right view
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
