package it.polimi.ingsw.Network.Client.RMI;

import com.google.gson.Gson;
import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.ClientToServer.NickNameMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.MoveDeserializer;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Network.Client.ConnectionClient;
import it.polimi.ingsw.Network.Server.RMI.ServerConnectionRMI;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ClientConnectionRMI extends ConnectionClient implements Remote {
    private static ServerConnectionRMI stub;
    private final String playerName;
    private boolean clientIsActive;
    private boolean GUIisActive;
    private Thread ping;
    public ClientConnectionRMI(String nickname) {
        this.playerName=nickname;
        this.clientIsActive =true;
        sendMessage(new NickNameMessage(playerName));
    }

    @Override
    public void run() {
        try{
            stub =(ServerConnectionRMI) Naming.lookup("rmi://localhost:"+22011+"/RMIServer");
        } catch (MalformedURLException | NotBoundException | RemoteException e) {
            throw new RuntimeException(e);
        }
        ping = new Thread(() -> {
            int pingCount=0;
            while (clientIsActive) {
                try {
                    //Metto a dormire thread per 5 secondi
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    sendPing();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


            }

        });

    }

//Ricevo il messagio
    public void sendMessage(String message) {
        ServerMessage serverMessage= (ServerMessage) MoveDeserializer.deserializeOutput(message);
        if (serverMessage!= null && serverMessage.getCategory() != Message.MessageCategory.PINGFROMSERVER) {
            if (GUIisActive) {
                //GUIEvent.recieveMessage(serverMessage);
            } else; //CLIEvent.recieveMessage(serverMessage);
        } else if(serverMessage!= null && serverMessage.getCategory()== Message.MessageCategory.PINGFROMSERVER){
            //System.out.println("Ping arrived");
            //sendPing();
        }



    }

//Metodo che manda messaggio al server
    public void sendMessage(ClientMessage message){
        Gson gson=new Gson();
        String m=gson.toJson(message);
        stub.sendMessage(m);
    }

    @Override
    public String getPlayerName() {
        return null;
    }


    public boolean getPing() {
        return true;

    }
    public void sendPing() throws InterruptedException {
        boolean pingIsOk=false;
        pingIsOk=stub.getPing();
        TimeUnit.SECONDS.sleep(3);
        if(pingIsOk){
            System.out.println("ping arrived");
            return;
        }
        System.out.println("connection crushed");
    }

    public boolean isGUIisActive() {
        return GUIisActive;
    }

    public void setGUIisActive(boolean GUIisActive) {
        this.GUIisActive = GUIisActive;
    }
}
