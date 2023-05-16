package it.polimi.ingsw.Network.Client.RMI;

import com.google.gson.Gson;
import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.ClientToServer.NickNameMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.MoveDeserializer;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Network.Client.ConnectionClient;
import it.polimi.ingsw.Network.Server.RMI.RemoteInterface;
import it.polimi.ingsw.Network.Server.RMI.RemoteInterfaceClient;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.concurrent.TimeUnit;

public class ClientConnectionRMI extends ConnectionClient implements RemoteInterfaceClient {
    private static RemoteInterface stub;
    private final String playerName;
    private boolean clientIsActive;
    private boolean GUIisActive;
    private Thread ping;
    public ClientConnectionRMI(String nickname) throws RemoteException {
        super();
        this.playerName=nickname;
        this.clientIsActive =true;
        try{
            stub =(RemoteInterface) Naming.lookup("rmi://localhost:"+22011+"/RMIServer");

        } catch (MalformedURLException | NotBoundException | RemoteException e) {
            throw new RuntimeException(e);
        }
        sendMessage(new NickNameMessage(playerName));
    }

    @Override
    public void run() {


        ping = new Thread(() -> {
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
    public void receiveMessage(String message) {
        ServerMessage serverMessage= (ServerMessage) MoveDeserializer.deserializeOutput(message);
        System.out.println(serverMessage);
        if (serverMessage!= null && serverMessage.getCategory() != Message.MessageCategory.PINGFROMSERVER) {
            if (GUIisActive) {
                //GUIEvent.recieveMessage(serverMessage);
            } else; //CLIEvent.recieveMessage(serverMessage);
        }



    }

    @Override
    public void login(String username, ClientConnectionRMI client) throws RemoteException {

    }

    @Override
    public void disconnectMe() throws RemoteException {

    }

    //Metodo che manda messaggio al server
    public void sendMessage(ClientMessage message){
        message.setNickname(playerName);
        Gson gson=new Gson();
        String m=gson.toJson(message);
        try {
            stub.receiveMessage(m, this);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getPlayerName() {
        return playerName;
    }


    public boolean getPing() {
        return true;

    }
    public void sendPing() throws InterruptedException {
        boolean pingIsOk=false;
        try {
            pingIsOk=stub.getPing();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
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
