package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Network.Client.ConnectionClient;
import it.polimi.ingsw.Network.Client.RMI.ClientConnectionRMI;
import it.polimi.ingsw.Network.Client.TCP.ClientConnectionTCP;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GUILauncher {
    private String nickname;
    private String serverAddr;
    private int portNum;
    private Socket socket;
    private ConnectionClient connectionClient;
    private ServerMessage response;
    private GUIEvent receiver;
    //private Button button = null;
    public void setResponse(ServerMessage response){
        this.response=response;
    }

    public void startClient(){
        do{
            Scanner input = new Scanner(System.in);
            nickname= input.nextLine();
           System.out.print("INSERT IP ADDRESS (press ENTER for default address): ");
            serverAddr = input.nextLine();
            System.out.print("INSERT PORT NUMBER: ");
            String portNumber = input.nextLine();
            portNum = Integer.parseInt(portNumber);

            String connectionType = input.nextLine();
            switch (connectionType.toUpperCase()) {
                case "TCP":
                    try {
                        socket = new Socket(serverAddr, portNum);
                        connectionClient = new ClientConnectionTCP(socket, nickname);
                        receiver=new GUIEvent(this);
                        receiver.setInStartGUI(true);
                        connectionClient.setListener(receiver);
                        new Thread(connectionClient).start();
                        break;
                    } catch (IOException e) {
                        System.out.flush();
                        System.out.println("Please, insert a CORRECT address.\n");
                        System.out.flush();
                        System.out.print("INSERT YOUR NICKNAME: ");
                    }
                    try{
                        TimeUnit.SECONDS.sleep(3);
                    }catch (InterruptedException iE){
                        iE.printStackTrace();
                    }
                    break;
                case "RMI":
                    try {
                        receiver=new GUIEvent(this);
                        receiver.setInStartGUI(true);
                        connectionClient = new ClientConnectionRMI(nickname,receiver);
                        new Thread(connectionClient).start();
                        break;
                    } catch (RemoteException e){
                        System.out.flush();
                        System.out.println("Please, insert a CORRECT address.\n");
                        System.out.flush();
                        System.out.print("INSERT YOUR NICKNAME: ");
                    }
                default:
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("Please, insert a CORRECT address.\n");
                    System.out.flush();
                    System.out.print("INSERT YOUR NICKNAME: ");
                    break;
            }
            try{
                TimeUnit.MILLISECONDS.sleep(500);
            }catch (InterruptedException iE){
                iE.printStackTrace();
            }
            if(response!=null && response.getCategory()== Message.MessageCategory.WARNING) {
                System.out.print(response.getReturnMessage());
                response=null;
            }
        }while(response==null || (response.getCategory()!=Message.MessageCategory.VALID_NICKNAME && response!=null));
        if(connectionClient!=null){
            receiver.setInStartGUI(false);
            new LobbyWaitingController(connectionClient, receiver).start();
        }

    }
}
