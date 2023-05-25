package it.polimi.ingsw.Network.Server;

import com.google.gson.Gson;
import it.polimi.ingsw.Network.Server.RMI.RMIServerMain;
import it.polimi.ingsw.Network.Server.RMI.RMIparams;
import it.polimi.ingsw.Network.Client.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.Network.Server.TCP.TCPParams;
import it.polimi.ingsw.Network.Server.TCP.TCPServerMain;
import it.polimi.ingsw.Printer;
import it.polimi.ingsw.Savings;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Eliahu Cohen
 * Server that contains RMI and TCP servers
 */
public class Server implements Printer {
    public static Integer idLobbies = 0;
    public static final ArrayList<Lobby> lobbies=new ArrayList<>();
    public static final ArrayList<Lobby> startedLobbies=new ArrayList<>();
    public static final ArrayList<String> connectedPlayers=new ArrayList<>();
    public static final HashMap<String, RemoteInterfaceClient> rmiConnections = new HashMap<>();
    public static void main(String[] args){
        loadSaves();
        TCPServerMain tcpServerMain =new TCPServerMain(TCPParams.PORT);
        RMIServerMain rmiServerMain=new RMIServerMain(RMIparams.PORT);
        Thread thread1=new Thread(tcpServerMain);
        Thread thread2=new Thread(rmiServerMain);
        thread1.start();
        thread2.start();
    }

    private static void loadSaves() {
        Reader reader;
        Gson gson=new Gson();
        Savings savings;
        try{
            while (true){
                int i=0;
                String path="Savings"+"/Lobby"+i+".json";
                reader= Files.newBufferedReader(Paths.get(path));
                savings=gson.fromJson(reader, Savings.class);
                Lobby lobby=savings.getLobby();
                lobby.reloadGame(savings.getGameSavings());
                startedLobbies.add(lobby);
            }
        } catch (IOException e) {
            System.out.println("Savings completely loaded");
        }

    }

    @Override
    public void showMessage(String s) {
        System.out.println(s);
    }



    //TODO ricaricamento dei salvataggi delle lobby
}
