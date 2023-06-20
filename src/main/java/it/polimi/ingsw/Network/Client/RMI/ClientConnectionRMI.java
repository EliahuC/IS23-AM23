package it.polimi.ingsw.Network.Client.RMI;

import com.google.gson.Gson;
import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.ClientToServer.NickNameMessage;

import it.polimi.ingsw.Messages.MoveDeserializer;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Network.Client.ConnectionClient;
import it.polimi.ingsw.Network.Server.RMI.RemoteInterface;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author Eliahu Cohen
 * RMI Connection of the client
 */
public class ClientConnectionRMI extends ConnectionClient implements RemoteInterfaceClient {
    PropertyChangeListener listener;
    private static RemoteInterface stub;
    private final String playerName;
    private boolean clientIsActive;

    private Thread ping;
    private final ArrayList<ServerMessage> queue=new ArrayList<>();

    /**
     * @author Eliahu Cohen
     * @param nickname of the client
     * @throws RemoteException if the connection couldn't start
     * Constructor to start the RMI connection and set the playerNick
     */
    public ClientConnectionRMI(String nickname,PropertyChangeListener listener) throws RemoteException {
        super();
        this.playerName=nickname;
        this.listener=listener;
        this.clientIsActive =true;
        try{
            if(address=="")
                stub =(RemoteInterface) Naming.lookup("rmi://localhost:"+22011+"/RMIServer");
            else
                stub =(RemoteInterface) Naming.lookup("rmi://" + address + ":"+22011+"/RMIServer");

        } catch (MalformedURLException | NotBoundException | RemoteException e) {
            System.out.println("you couldn't establish the connection");
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
                    clientIsActive=false;
                    System.out.println("Timer crashed");
                }
                try {
                    sendPing();
                } catch (InterruptedException e) {
                   closeConnection();
                }

            }});
            ping.start();
            Thread receiver= new Thread(()->{
                while (clientIsActive) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(150);
                    } catch (InterruptedException e) {
                     closeConnection();
                    }
                    passToListener();
                }
        });
            receiver.start();
        }




    /**
     * @author Eliahu Cohen
     * @param message received from the server
     * Method that receive a message from the server and pass it to GUI/CLI handlers
     */
    public void receiveMessage(String message) {
        synchronized (queue){
            queue.add((ServerMessage) MoveDeserializer.deserializeOutput(message));
            queue.notifyAll();
        }
    }

    private void passToListener(){
       synchronized (queue){
           if(queue.size()!=0){
               ServerMessage serverMessage=queue.remove(0);
               PropertyChangeEvent evt= new PropertyChangeEvent(
                       this,
                       "MESSAGE RECEIVED",
                       null,
                       serverMessage);
               listener.propertyChange(evt);
               }
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
            closeConnection();
            ping.interrupt();
        }
        TimeUnit.SECONDS.sleep(3);


    }

    /**
     * @author Eliahu Cohen
     * method that close the connection and notify the client
     */
    public void closeConnection() {
        try {
            UnicastRemoteObject.unexportObject(stub , true);
        } catch (NoSuchObjectException ignored) {

        }
        clientIsActive=false;
        System.out.println("Someone crashed, please relaunch the application to play a new game");
    }
    public PropertyChangeListener getListener() {
        return listener;
    }

    public void setListener(PropertyChangeListener listener) {
        this.listener = listener;
    }
}
