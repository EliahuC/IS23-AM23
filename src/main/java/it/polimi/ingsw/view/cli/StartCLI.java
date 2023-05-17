package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Network.Client.ConnectionClient;
import it.polimi.ingsw.Network.Client.RMI.ClientConnectionRMI;
import it.polimi.ingsw.Network.Client.TCP.ClientConnectionTCP;

import java.io.IOException;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class StartCLI {

    private String nickname;
    private String serverAddr;
    private int portNum;
    private Socket socket;
    private ConnectionClient connectionClient;
    private ServerMessage response;
    private CLIEvent receiver;
    public void setResponse(ServerMessage response){
        this.response=response;
    }

    public void startClient(){
        //stampare schermata iniziale
        System.out.print("INSERT YOUR NICKNAME:");
        while(true){
            Scanner input = new Scanner(System.in);
            nickname= input.nextLine();

            System.out.print("INSERT IP ADDRESS:");
            serverAddr = input.nextLine();
            System.out.print("INSERT PORT NUMBER:");
            String portNumber = input.nextLine();
            portNum = Integer.parseInt(portNumber);

            System.out.print("Do you want to use a TCP or RMI connection?");
            String connectionType = input.nextLine();
            switch (connectionType.toUpperCase()) {
                case "TCP":
                    try {
                        socket = new Socket(serverAddr, portNum);
                        connectionClient = new ClientConnectionTCP(socket, nickname);
                        new Thread(connectionClient).start();
                        receiver=new CLIEvent(this);
                        connectionClient.setListener(receiver);
                        break;
                    } catch (IOException e) {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("Please, insert again a CORRECT address.\n");
                    }
                    try{
                        TimeUnit.SECONDS.sleep(3);
                    }catch (InterruptedException iE){
                        iE.printStackTrace();
                    }
                    break;
                case "RMI":
                    try {
                        connectionClient = new ClientConnectionRMI(nickname);
                        new Thread(connectionClient).start();
                        receiver=new CLIEvent(this);
                        connectionClient.setListener(receiver);
                        break;
                    } catch (RemoteException e){
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("Please, insert again a CORRECT address.\n");
                    }
                default:
                    System.out.println("Please, insert again a CORRECT address.\n");
                    break;
            }
            try{
                TimeUnit.SECONDS.sleep(3);
            }catch (InterruptedException iE){
                iE.printStackTrace();
            }
            if(connectionClient!=null && response!=null && response.getCategory()==Message.MessageCategory.VALID_NICKNAME)
                break;
            else if(response!=null && response.getCategory()!=Message.MessageCategory.WARNING)
                System.out.println(response.getReturnMessage());
        }
        new LobbyHandler(connectionClient).start();
    }
}
