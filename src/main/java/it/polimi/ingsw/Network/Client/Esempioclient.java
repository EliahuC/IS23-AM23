package it.polimi.ingsw.Network.Client;

import com.google.gson.Gson;
import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.Messages.ServerToClient.GameIsStartingMessage;
import it.polimi.ingsw.Network.Client.RMI.ClientConnectionRMI;
import it.polimi.ingsw.Network.Server.TCP.ServerConnectionTCP;
import it.polimi.ingsw.Network.Server.VirtualView;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;

import java.rmi.RemoteException;

public class Esempioclient {
    public static void main(String args[]) throws RemoteException {
        //ClientConnectionRMI connectionRMI=new ClientConnectionRMI("Palle");
        //connectionRMI.run();
        // AS TRUE
        Gson gson=new Gson();
        Player p1 = new Player("Tom");
        Player p2 = new Player("Jerry");
        Launcher L = new Launcher();
        L.addPlayer(p1);
        L.addPlayer(p2);
        p1.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p2.setListener(new VirtualView(new ServerConnectionTCP(null)));
        Game G = new Game(L,L.getPlayers());
        G.startGame();
        System.out.println(gson.toJson(new GameIsStartingMessage(G)));
    }
}
