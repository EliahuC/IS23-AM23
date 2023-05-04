package it.polimi.ingsw.Network.Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientConnectionTCP extends ConnectionClient{
    private final Socket socket;
    private String address;
    private boolean active;
    private String playerName;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    public ClientConnectionTCP(Socket socket) {
        this.active=true;
        this.socket = socket;
    }

    public String getAddress() {
        return address;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void run() {
        try{
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Scrivo reazione view all'evento
      while(active){


      }

    }
}
