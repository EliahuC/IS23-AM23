package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Network.Messages.ClientToServer.PingToServer;
import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.Network.Messages.ServerToClient.ServerMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientConnectionTCP extends ConnectionClient{
    private MoveSerializer moveSerializer;
    private final Socket socket;
    private String IPAddress;
    private boolean clientIsActive;
    private String playerName;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Boolean GUIisActive=false;

    public ClientConnectionTCP(Socket socket) {
        this.clientIsActive =true;
        this.socket = socket;


    }

    public String getAddress() {
        return IPAddress;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
        this.moveSerializer=new MoveSerializer(playerName);
    }

    public void setAddress(String address) {
        this.IPAddress = address;
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
      while(clientIsActive){
          try {
              ServerMessage serverMessage = receiveMessage();
              if (serverMessage.getCategory() != Message.MessageCategory.PING) {
                  if (GUIisActive) {
                      //GUIEvent.recieveMessage(serverMessage);
                  } else; //CLIEvent.recieveMessage(serverMessage);
              } else sendPing();
          } catch (IOException e){
              closeConnection();
              notifyDisconnection();
          }catch (ClassNotFoundException e){
              e.printStackTrace();
          }
      }

    }

    private ServerMessage receiveMessage() throws IOException, ClassNotFoundException {
        return (ServerMessage) input.readObject();
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
    private void sendMessage(ClientMessage message){
        try{
            output.writeObject(message);
            output.flush();
            output.reset();
        } catch (IOException e) {
            closeConnection();
            notifyDisconnection();
        }
    }


    private void sendPing() {
        PingToServer ping=new PingToServer(playerName);
        asyncSendPing(ping);
    }

    private void asyncSendPing(PingToServer ping) {
        new Thread(()-> sendMessage(ping)).start();
    }
}
