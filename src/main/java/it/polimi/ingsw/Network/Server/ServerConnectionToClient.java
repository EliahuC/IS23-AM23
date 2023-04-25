package it.polimi.ingsw.Network.Server;

import com.google.gson.Gson;
import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.Network.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Printer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.TimeUnit;

public class ServerConnectionToClient implements Runnable {
    private final Socket clientSocket;
    private final Thread ping;
    private boolean serverIsActive;
    private String namePlayer;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private VirtualView virtualView;
    private Gson gson;

    public ServerConnectionToClient(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.serverIsActive = true;

        try {
            output = new ObjectOutputStream(clientSocket.getOutputStream());
            input = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            clientSocket.setSoTimeout(3600);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        ping = new Thread(() -> {
            while (serverIsActive) {
                try {
                    //Metto a dormire thread per 30 secondi
                    TimeUnit.SECONDS.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        ping.start();
    }

    private void closeConnection() {
        serverIsActive = false;
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public synchronized void sendMessage(ServerMessage message){
        String sms;
        sms=gson.toJson(message);
        try{
            output.reset();
            output.writeObject(sms);
            output.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void reciveMessage(Message message){

    }



    @Override
    public void run() {

    }
}
