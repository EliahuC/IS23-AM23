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
import java.util.Scanner;

public class ClientConnectionTCP extends ConnectionClient {
    PropertyChangeListener listener;
    private final Socket socket;
    private String IPAddress;
    private boolean clientIsActive;

    public String getPlayerName() {
        return playerName;
    }

    private String playerName;
    private Scanner input;
    private PrintWriter output;
    private Boolean GUIisActive=false;
    private final Gson gson=new Gson();

    public ClientConnectionTCP(Socket socket,String nickname) throws RemoteException {
        super();
        this.playerName=nickname;
        this.clientIsActive =true;
        this.socket = socket;
        try{
            this.output = new PrintWriter(socket.getOutputStream());
            this.input = new Scanner(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        sendMessage(new NickNameMessage(playerName));

    }

    public String getAddress() {
        return IPAddress;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setAddress(String address) {
        this.IPAddress = address;
    }

    @Override
    public void run() {

        //Scrivo reazione view all'evento
        while(clientIsActive){
            String s = input.nextLine();
            receiveMessage(s);
        }

    }
@Override
        public void receiveMessage(String s) {
            ServerMessage serverMessage=null;
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
            throw new RuntimeException(e);
        }
        }

    private void notifyDisconnection() {
        if(GUIisActive){
            //GUIEvent.alertDisconnection();
        }
        //CLIEvent.alertDisconnection();
    }

    public void closeConnection(){
        try{
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        clientIsActive = false;
    }
    public void sendMessage(ClientMessage message){
        message.setNickname(playerName);
        String m=gson.toJson(message);
        //       output.reset();
        output.println(m);
        output.flush();
    }


    private void sendPing() throws InterruptedException {
        PingToServer ping=new PingToServer(playerName);
        //TimeUnit.SECONDS.sleep(5);
        sendMessage(ping);
    }

    private void asyncSendPing(PingToServer ping) {
        new Thread(()-> sendMessage(ping)).start();
    }

    public Boolean getGUIisActive() {
        return GUIisActive;
    }

    public void setGUIisActive(Boolean GUIisActive) {
        this.GUIisActive = GUIisActive;
    }

    public PropertyChangeListener getListener() {
        return listener;
    }

    public void setListener(PropertyChangeListener listener) {
        this.listener = listener;
    }
}
