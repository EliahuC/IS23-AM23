package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.Printer;
import it.polimi.ingsw.view.View;

public class CLI implements Printer,View {
    @Override
    public void showMessage(String s) {
    }

    @Override
    public void run() {
        new StartCLI().startClient();
    }

    @Override
    public void passIP(String ip) {

    }
}
