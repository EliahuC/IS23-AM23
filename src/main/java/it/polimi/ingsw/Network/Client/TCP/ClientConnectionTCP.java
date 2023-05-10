package it.polimi.ingsw.Network.Client.TCP;

import com.google.gson.Gson;
import it.polimi.ingsw.Network.Client.ConnectionClient;
import it.polimi.ingsw.Network.Client.MoveSerializer;
import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.ClientToServer.PingToServer;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientConnectionTCP extends ConnectionClient {
    private MoveSerializer moveSerializer;
    private final Socket socket;
    private String IPAddress;
    private boolean clientIsActive;
    private String playerName;
    private Scanner input;
    private PrintWriter output;
    private Boolean GUIisActive=false;
    private final Gson gson=new Gson();

    public ClientConnectionTCP(Socket socket) {
        this.clientIsActive =true;
        this.socket = socket;
        try{
            this.output = new PrintWriter(socket.getOutputStream());
            this.input = new Scanner(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getAddress() {
        return IPAddress;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
        this.moveSerializer=new MoveSerializer();
    }

    public void setAddress(String address) {
        this.IPAddress = address;
    }

    @Override
    public void run() {

        //Scrivo reazione view all'evento
        while(clientIsActive){
            try {
                ServerMessage serverMessage = receiveMessage();
                if (serverMessage.getCategory() != Message.MessageCategory.PING) {
                    if (GUIisActive) {
                        //GUIEvent.recieveMessage(serverMessage);
                    } else; //CLIEvent.recieveMessage(serverMessage);
                } else if(serverMessage.getCategory()== Message.MessageCategory.PING){
                    System.out.println("Ping arrived");
                    sendPing();
                }
            } catch (IOException e){
                closeConnection();
                notifyDisconnection();
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private ServerMessage receiveMessage() throws IOException, ClassNotFoundException {
        String s=input.nextLine();
        //MoveSerializer moveSerializer1=new MoveSerializer(playerName);
       // moveSerializer1.getFromKeyboard(s);

        return  gson.fromJson(s,ServerMessage.class);
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
        String m=gson.toJson(message,ClientMessage.class);
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
}
