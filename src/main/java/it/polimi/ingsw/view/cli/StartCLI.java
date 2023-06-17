package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Network.Client.ConnectionClient;
import it.polimi.ingsw.Network.Client.RMI.ClientConnectionRMI;
import it.polimi.ingsw.Network.Client.TCP.ClientConnectionTCP;

import java.io.IOException;
import java.net.ConnectException;
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
    private final static String CLS = "\u001B[8;46;123t" + "\u001B[2J\u001B[3J\u001B[H";
    public void setResponse(ServerMessage response){
        this.response=response;
    }

    public void startClient(){
        System.out.print("""
                                                                                                                                           \s
                																															
                                                                                                                                           \s
                																															
                                                                                                                                           \s
                																															
                                                                                                                                           \s
                																															
                                                       __  ___          _____  __           __ ____ _                                      \s
                                                      /  |/  /__  __   / ___/ / /_   ___   / // __/(_)___                                  \s
                                                     / /|_/ // / / /   \\__ \\ / __ \\ / _ \\ / // /_ / // _ \\                                 \s
                                                    / /  / // /_/ /   ___/ // / / //  __// // __// //  __/                                 \s
                                                   /_/  /_/ \\__, /   /____//_/ /_/ \\___//_//_/  /_/ \\___/                                  \s
                                                           /____/                                                                          \s
                                                                                                                                           \s
                																															
                                                                                                                                           \s
                																															
                                                                                                                                           \s
                																															
                                                                                                                                           \s
                																															
                """);
        try{
            TimeUnit.SECONDS.sleep(2);
        }catch (InterruptedException iE){
            iE.printStackTrace();
        }
        System.out.print(CLS);
        System.out.flush();
        System.out.print("""
                																															
                                                       __  ___          _____  __           __ ____ _                                      \s
                                                      /  |/  /__  __   / ___/ / /_   ___   / // __/(_)___                                  \s
                                                     / /|_/ // / / /   \\__ \\ / __ \\ / _ \\ / // /_ / // _ \\                                 \s
                                                    / /  / // /_/ /   ___/ // / / //  __// // __// //  __/                                 \s
                                                   /_/  /_/ \\__, /   /____//_/ /_/ \\___//_//_/  /_/ \\___/                                  \s
                                                           /____/                                                                          \s
                                                                                                                                           \s
                																															
                """ +
                "\t\t\t\t\t\t\t\tINSERT YOUR NICKNAME: ");
        do{
            Scanner input = new Scanner(System.in);
            nickname= input.nextLine();

            System.out.print("\n\t\t\t\t\t\t\t\tINSERT IP ADDRESS (PRESS ENTER FOR DEFAULT): ");
            serverAddr = input.nextLine();
            System.out.print("\n\t\t\t\t\t\t\t\tINSERT PORT NUMBER: ");
            String portNumber = input.nextLine();
            portNum = Integer.parseInt(portNumber);

            System.out.print("\n\t\t\t\t\t\t\t\tTYPE OF CONNECTION [TCP/RMI]: ");
            String connectionType = input.nextLine();
            switch (connectionType.toUpperCase()) {
                case "TCP":
                    try {
                        socket = new Socket(serverAddr, portNum);
                        connectionClient = new ClientConnectionTCP(socket, nickname);
                        receiver=new CLIEvent(this);
                        receiver.setInStartCLI(true);
                        connectionClient.setListener(receiver);
                        new Thread(connectionClient).start();
                        break;
                    } catch (IOException e) {
                        System.out.print(CLS);
                        System.out.flush();
                        System.out.print("""
                                																															
                                                                       __  ___          _____  __           __ ____ _                                      \s
                                                                      /  |/  /__  __   / ___/ / /_   ___   / // __/(_)___                                  \s
                                                                     / /|_/ // / / /   \\__ \\ / __ \\ / _ \\ / // /_ / // _ \\                                 \s
                                                                    / /  / // /_/ /   ___/ // / / //  __// // __// //  __/                                 \s
                                                                   /_/  /_/ \\__, /   /____//_/ /_/ \\___//_//_/  /_/ \\___/                                  \s
                                                                           /____/                                                                          \s
                                                                                                                                                           \s
                                																															
                                											  Please, insert a CORRECT address!		
                                """);
                        try{
                            TimeUnit.SECONDS.sleep(1);
                        }catch (InterruptedException iE){
                            iE.printStackTrace();
                        }
                        System.out.print(CLS);
                        System.out.flush();
                        System.out.print("""
                																															
                                                       __  ___          _____  __           __ ____ _                                      \s
                                                      /  |/  /__  __   / ___/ / /_   ___   / // __/(_)___                                  \s
                                                     / /|_/ // / / /   \\__ \\ / __ \\ / _ \\ / // /_ / // _ \\                                 \s
                                                    / /  / // /_/ /   ___/ // / / //  __// // __// //  __/                                 \s
                                                   /_/  /_/ \\__, /   /____//_/ /_/ \\___//_//_/  /_/ \\___/                                  \s
                                                           /____/                                                                          \s
                                                                                                                                           \s
                																															
                """ +
                                "\t\t\t\t\t\t\t\tINSERT YOUR NICKNAME: ");
                    }
                    try{
                        TimeUnit.SECONDS.sleep(3);
                    }catch (InterruptedException iE){
                        iE.printStackTrace();
                    }
                    break;
                case "RMI":
                    try {
                        receiver=new CLIEvent(this);
                        receiver.setInStartCLI(true);
                        connectionClient = new ClientConnectionRMI(nickname,receiver);
                        new Thread(connectionClient).start();
                        break;
                    } catch (RemoteException e){
                        System.out.print(CLS);
                        System.out.flush();
                        System.out.print("""
                                																															
                                                                       __  ___          _____  __           __ ____ _                                      \s
                                                                      /  |/  /__  __   / ___/ / /_   ___   / // __/(_)___                                  \s
                                                                     / /|_/ // / / /   \\__ \\ / __ \\ / _ \\ / // /_ / // _ \\                                 \s
                                                                    / /  / // /_/ /   ___/ // / / //  __// // __// //  __/                                 \s
                                                                   /_/  /_/ \\__, /   /____//_/ /_/ \\___//_//_/  /_/ \\___/                                  \s
                                                                           /____/                                                                          \s
                                                                                                                                                           \s
                                																															
                                											  Please, insert a CORRECT address!		
                                """);
                        try{
                            TimeUnit.SECONDS.sleep(2);
                        }catch (InterruptedException iE){
                            iE.printStackTrace();
                        }
                        System.out.print(CLS);
                        System.out.flush();
                        System.out.print("""
                																															
                                                       __  ___          _____  __           __ ____ _                                      \s
                                                      /  |/  /__  __   / ___/ / /_   ___   / // __/(_)___                                  \s
                                                     / /|_/ // / / /   \\__ \\ / __ \\ / _ \\ / // /_ / // _ \\                                 \s
                                                    / /  / // /_/ /   ___/ // / / //  __// // __// //  __/                                 \s
                                                   /_/  /_/ \\__, /   /____//_/ /_/ \\___//_//_/  /_/ \\___/                                  \s
                                                           /____/                                                                          \s
                                                                                                                                           \s
                																															
                """ +
                                "\t\t\t\t\t\t\t\tINSERT YOUR NICKNAME: ");
                    }
                    try{
                        TimeUnit.SECONDS.sleep(3);
                    }catch (InterruptedException iE){
                        iE.printStackTrace();
                    }
                    break;
                default:
                    System.out.print(CLS);
                    System.out.flush();
                    System.out.print("""
                                																															
                                                                       __  ___          _____  __           __ ____ _                                      \s
                                                                      /  |/  /__  __   / ___/ / /_   ___   / // __/(_)___                                  \s
                                                                     / /|_/ // / / /   \\__ \\ / __ \\ / _ \\ / // /_ / // _ \\                                 \s
                                                                    / /  / // /_/ /   ___/ // / / //  __// // __// //  __/                                 \s
                                                                   /_/  /_/ \\__, /   /____//_/ /_/ \\___//_//_/  /_/ \\___/                                  \s
                                                                           /____/                                                                          \s
                                                                                                                                                           \s
                                																															
                                											  Please, insert a CORRECT address!		
                                """);
                    try{
                        TimeUnit.SECONDS.sleep(2);
                    }catch (InterruptedException iE){
                        iE.printStackTrace();
                    }
                    System.out.print(CLS);
                    System.out.flush();
                    System.out.print("""
                																															
                                                       __  ___          _____  __           __ ____ _                                      \s
                                                      /  |/  /__  __   / ___/ / /_   ___   / // __/(_)___                                  \s
                                                     / /|_/ // / / /   \\__ \\ / __ \\ / _ \\ / // /_ / // _ \\                                 \s
                                                    / /  / // /_/ /   ___/ // / / //  __// // __// //  __/                                 \s
                                                   /_/  /_/ \\__, /   /____//_/ /_/ \\___//_//_/  /_/ \\___/                                  \s
                                                           /____/                                                                          \s
                                                                                                                                           \s
                																															
                """ +
                            "\t\t\t\t\t\t\t\tINSERT YOUR NICKNAME: ");
                    break;
            }
            try{
                TimeUnit.MILLISECONDS.sleep(500);
            }catch (InterruptedException iE){
                iE.printStackTrace();
            }
            if(response!=null && response.getCategory()==Message.MessageCategory.WARNING) {
                System.out.print("""
                																															
                                                       __  ___          _____  __           __ ____ _                                      \s
                                                      /  |/  /__  __   / ___/ / /_   ___   / // __/(_)___                                  \s
                                                     / /|_/ // / / /   \\__ \\ / __ \\ / _ \\ / // /_ / // _ \\                                 \s
                                                    / /  / // /_/ /   ___/ // / / //  __// // __// //  __/                                 \s
                                                   /_/  /_/ \\__, /   /____//_/ /_/ \\___//_//_/  /_/ \\___/                                  \s
                                                           /____/                                                                          \s
                                                                                                                                           \s
                																															
                """ +
                        "\t\t\t\t\t\t\t\t\t\t" + response.getReturnMessage());
                try{
                    TimeUnit.SECONDS.sleep(2);
                }catch (InterruptedException iE){
                    iE.printStackTrace();
                }
                System.out.print(CLS);
                System.out.flush();
                System.out.print("""
                																															
                                                       __  ___          _____  __           __ ____ _                                      \s
                                                      /  |/  /__  __   / ___/ / /_   ___   / // __/(_)___                                  \s
                                                     / /|_/ // / / /   \\__ \\ / __ \\ / _ \\ / // /_ / // _ \\                                 \s
                                                    / /  / // /_/ /   ___/ // / / //  __// // __// //  __/                                 \s
                                                   /_/  /_/ \\__, /   /____//_/ /_/ \\___//_//_/  /_/ \\___/                                  \s
                                                           /____/                                                                          \s
                                                                                                                                           \s
                																															
                """ +
                        "\t\t\t\t\t\t\t\tINSERT YOUR NICKNAME: ");
                response=null;
            }
        }while(response==null || (response.getCategory()!=Message.MessageCategory.VALID_NICKNAME && response!=null));
        if(connectionClient!=null){
            receiver.setInStartCLI(false);
            new LobbyHandler(connectionClient, receiver).start();
        }

    }
}
