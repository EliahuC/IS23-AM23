package it.polimi.ingsw.Network.Client.RMI;

import com.google.gson.Gson;
import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.ClientToServer.NickNameMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.MoveDeserializer;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Network.Client.ConnectionClient;
import it.polimi.ingsw.Network.Server.RMI.RemoteInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.concurrent.TimeUnit;

/**
 * @author Eliahu Cohen
 * RMI Connection of the client
 */
public class ClientConnectionRMI extends ConnectionClient implements RemoteInterfaceClient {
    private static RemoteInterface stub;
    private final String playerName;
    private boolean clientIsActive;
    private boolean GUIisActive;
    private Thread ping;

    /**
     * @author Eliahu Cohen
     * @param nickname of the client
     * @throws RemoteException if the connection couldn't start
     * Constructor to start the RMI connection and set the playerNick
     */
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

    /**
     * @author Eliahu Cohen
     * Method to start the Ping pong with the server
     */
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

    /**
     * @author Eliahu Cohen
     * @param message received from the server
     * Method that receive a message from the server and pass it to GUI/CLI handlers
     */
    public void receiveMessage(String message) {
        System.out.println(message);
        ServerMessage serverMessage= (ServerMessage) MoveDeserializer.deserializeOutput(message);
        if (serverMessage!= null && serverMessage.getCategory() != Message.MessageCategory.PINGFROMSERVER) {
            if (GUIisActive) {
                //GUIEvent.recieveMessage(serverMessage);
            } else; //CLIEvent.recieveMessage(serverMessage);
        }

    }


    /**
     * @author Eliahu Cohen
     * @param message to send to the server
     * Method to send a message to the server
     */
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

    /**
     * @author Eliahu Cohen
     * @return true to notify the client the ping-pong is still on
     */
    public boolean getPing() {
        return true;

    }

    /**
     * @author Eliahu Cohen
     * @throws InterruptedException
     * Method used to send a ping to the server
     */
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
