package it.polimi.ingsw.Network.Client.TCP;

import com.google.gson.Gson;
import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.ClientToServer.NickNameMessage;
import it.polimi.ingsw.Messages.ClientToServer.PingToServer;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.MoveDeserializer;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Network.Client.ConnectionClient;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Eliahu Cohen
 * Socket connection of the client
 */
public class ClientConnectionTCP extends ConnectionClient {
    PropertyChangeListener listener;
    private final Socket socket;

    private boolean clientIsActive;

    public String getPlayerName() {
        return playerName;
    }

    private final String playerName;
    private Scanner input;
    private PrintWriter output;
    private final Gson gson=new Gson();

    /**
     *
     * @author Eliahu Cohen
     * @param socket connection with the server
     * @param nickname of the client
     * @throws RemoteException if the connection couldn't start
     * Constructor to start the Socket connection and set the playerNick
     */
    public ClientConnectionTCP(Socket socket,String nickname) throws RemoteException {
        super();
        this.playerName=nickname;
        this.clientIsActive =true;
        this.socket = socket;
        try{
            this.output = new PrintWriter(socket.getOutputStream());
            this.input = new Scanner(socket.getInputStream());
        } catch (IOException e) {
            closeConnection();
        }
        sendMessage(new NickNameMessage(playerName));

    }


    /**
     * @author Eliahu Cohen
     * method that start the receiving of the messages
     */
    @Override
    public void run() {

        //Scrivo reazione view all'evento
        while(clientIsActive){
            try {
                String s = input.nextLine();
                receiveMessage(s);
            } catch (NoSuchElementException e) {
                closeConnection();

            }
        }

    }

    /**
     * @author Eliahu Cohen
     * @param s message received
     * Method thar receive the message from the server and pass it to the listener
     */
    @Override
    public void receiveMessage(String s) {
        ServerMessage serverMessage;

        serverMessage= (ServerMessage) MoveDeserializer.deserializeOutput(s);
        PropertyChangeEvent evt= new PropertyChangeEvent(
        this,
        "MESSAGE RECEIVED",
        null,
        serverMessage);
        try{
            if (listener != null && serverMessage!= null && serverMessage.getCategory() != Message.MessageCategory.PINGFROMSERVER) {
                listener.propertyChange(evt);
    } else if(serverMessage!= null && serverMessage.getCategory()== Message.MessageCategory.PINGFROMSERVER){
        //System.out.println("Ping arrived")
                sendPing();
            }
    } catch (InterruptedException e) {
            closeConnection();
        }
    }


    /**
     * @author Eliahu Cohen
     * method that close the connection and notify the client
     */
    public void closeConnection(){
        try{
            socket.close();
        } catch (IOException e) {
            System.out.println("Problem closing the connection");
        }
        System.out.println("Someone crashed, please relaunch the application to play a new game");
        clientIsActive = false;
    }

    /**
     * @author Eliahu Cohen
     * @param message to send to the server
     * Method that sends a message to the server
     */
    public void sendMessage(ClientMessage message){
        message.setNickname(playerName);
        String m=gson.toJson(message);
        //       output.reset();
        output.println(m);
        output.flush();
    }

    /**
     * @author Eliahu Cohen
     * @throws InterruptedException if the connection crash
     * Method to send the ping to the server
     */
    private void sendPing() throws InterruptedException {
        PingToServer ping=new PingToServer(playerName);
        sendMessage(ping);
    }




    public PropertyChangeListener getListener() {
        return listener;
    }

    public void setListener(PropertyChangeListener listener) {
        this.listener = listener;
    }
}
