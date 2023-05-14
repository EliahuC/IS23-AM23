package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Server.RMI.RMIServerMain;
import it.polimi.ingsw.Network.Server.RMI.RMIparams;
import it.polimi.ingsw.Network.Server.TCP.Lobby;
import it.polimi.ingsw.Network.Server.TCP.TCPParams;
import it.polimi.ingsw.Network.Server.TCP.TCPServerMain;
import it.polimi.ingsw.Printer;

import java.util.ArrayList;

public class Server implements  Printer {
     public static final ArrayList<Lobby> lobbies=new ArrayList<>();
    public static final ArrayList<Lobby> startedLobbies=new ArrayList<>();
    public static void main(String args[]){

        TCPServerMain tcpServerMain =new TCPServerMain(TCPParams.PORT);
        RMIServerMain rmiServerMain=new RMIServerMain(RMIparams.PORT);

    }

    @Override
    public void showMessage(String s) {
        System.out.println(s);
    }
}
