package it.polimi.ingsw.Network.Client.TCP;

import com.google.gson.Gson;
import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.ClientToServer.NickNameMessage;
import it.polimi.ingsw.Messages.ClientToServer.PingToServer;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.MoveDeserializer;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Network.Client.ConnectionClient;
import it.polimi.ingsw.Messages.MoveSerializer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientConnectionTCP extends ConnectionClient {

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

    public ClientConnectionTCP(Socket socket,String nickname) {
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
            try {
                ServerMessage serverMessage = receiveMessage();
                if (serverMessage.getCategory() != Message.MessageCategory.PINGFROMSERVER) {
                    if (GUIisActive) {
                        //GUIEvent.recieveMessage(serverMessage);
                    } else; //CLIEvent.recieveMessage(serverMessage);
                } else if(serverMessage.getCategory()== Message.MessageCategory.PINGFROMSERVER){
                    //System.out.println("Ping arrived");
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

    public ServerMessage receiveMessage() throws IOException, ClassNotFoundException {
        String s=input.nextLine();

        return (ServerMessage) MoveDeserializer.deserializeOutput(s);
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
}
