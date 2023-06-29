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

/**
 * First page of the cli where the connection starts
 * @author Simone Controguerra
 *
 */
public class StartCLI {

    private String nickname;
    private String serverAddr;
    private int portNum;
    private Socket socket;
    private ConnectionClient connectionClient;
    private ServerMessage response;
    private CLIEvent receiver;
    private final static String CLS = "\u001B[2J\u001B[3J\u001B[H";
    public void setResponse(ServerMessage response){
        this.response=response;
    }

    /**
     * Player Login page
     * @author Simone Controguerra
     *
     */
    public void startClient(){
        boolean lock = false;
        System.out.print(CLS);
        System.out.print("""
                					
                					
                					
                					
                					
                					
                					
                					
                	
                																							
                                       __  ___          _____  __           __ ____ _
                                      /  |/  /__  __   / ___/ / /_   ___   / // __/(_)___
                                     / /|_/ // / / /   \\__ \\ / __ \\ / _ \\ / // /_ / // _ \\
                                    / /  / // /_/ /   ___/ // / / //  __// // __// //  __/
                                   /_/  /_/ \\__, /   /____//_/ /_/ \\___//_//_/  /_/ \\___/
                                           /____/
                """);
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException iE){
            iE.printStackTrace();
        }
        System.out.print(CLS);
        System.out.flush();
        System.out.print("""
                					
                					
                																							
                                       __  ___          _____  __           __ ____ _
                                      /  |/  /__  __   / ___/ / /_   ___   / // __/(_)___
                                     / /|_/ // / / /   \\__ \\ / __ \\ / _ \\ / // /_ / // _ \\
                                    / /  / // /_/ /   ___/ // / / //  __// // __// //  __/
                                   /_/  /_/ \\__, /   /____//_/ /_/ \\___//_//_/  /_/ \\___/
                                           /____/
                """);
        System.out.print("\n                INSERT YOU NICKNAME: ");
        do{
            Scanner input = new Scanner(System.in);
            nickname= input.nextLine();

            System.out.print("\n                INSERT IP ADDRESS (PRESS ENTER FOR DEFAULT): ");
            serverAddr = input.nextLine();
            System.out.print("\n                INSERT PORT NUMBER: ");
            String connectionType;
            try {
                String portNumber = input.nextLine();
                portNum = Integer.parseInt(portNumber);
                System.out.print("\n                TYPE OF CONNECTION [TCP/RMI]: ");
                connectionType = input.nextLine();
            }catch (NumberFormatException e){
                connectionType = "other";
            };
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
                                                							
                                                        					
                                        															
                                                       __  ___          _____  __           __ ____ _
                                                      /  |/  /__  __   / ___/ / /_   ___   / // __/(_)___
                                                     / /|_/ // / / /   \\__ \\ / __ \\ / _ \\ / // /_ / // _ \\
                                                    / /  / // /_/ /   ___/ // / / //  __// // __// //  __/
                                                   /_/  /_/ \\__, /   /____//_/ /_/ \\___//_//_/  /_/ \\___/
                                                           /____/
                                """);
                        System.out.println("\n                              Please, insert a CORRECT address ...");
                        try{
                            TimeUnit.SECONDS.sleep(2);
                        }catch (InterruptedException iE){
                            iE.printStackTrace();
                        }
                        System.out.print(CLS);
                        System.out.flush();
                        System.out.print("""
                                                							
                                                        					
                                        															
                                                       __  ___          _____  __           __ ____ _
                                                      /  |/  /__  __   / ___/ / /_   ___   / // __/(_)___
                                                     / /|_/ // / / /   \\__ \\ / __ \\ / _ \\ / // /_ / // _ \\
                                                    / /  / // /_/ /   ___/ // / / //  __// // __// //  __/
                                                   /_/  /_/ \\__, /   /____//_/ /_/ \\___//_//_/  /_/ \\___/
                                                           /____/
                                """);
                        System.out.print("\n                INSERT YOU NICKNAME: ");
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
                        connectionClient = new ClientConnectionRMI(nickname,receiver, serverAddr);
                        new Thread(connectionClient).start();
                        break;
                    } catch (RemoteException | NullPointerException e){
                        System.out.print(CLS);
                        System.out.flush();
                        System.out.print("""
                                                							
                                                        					
                                        															
                                                       __  ___          _____  __           __ ____ _
                                                      /  |/  /__  __   / ___/ / /_   ___   / // __/(_)___
                                                     / /|_/ // / / /   \\__ \\ / __ \\ / _ \\ / // /_ / // _ \\
                                                    / /  / // /_/ /   ___/ // / / //  __// // __// //  __/
                                                   /_/  /_/ \\__, /   /____//_/ /_/ \\___//_//_/  /_/ \\___/
                                                           /____/
                                """);
                        System.out.println("\n                              Please, insert a CORRECT address ...");
                        try{
                            TimeUnit.SECONDS.sleep(2);
                        }catch (InterruptedException iE){
                            iE.printStackTrace();
                        }
                        System.out.print(CLS);
                        System.out.flush();
                        System.out.print("""
                                                							
                                                        					
                                        															
                                                       __  ___          _____  __           __ ____ _
                                                      /  |/  /__  __   / ___/ / /_   ___   / // __/(_)___
                                                     / /|_/ // / / /   \\__ \\ / __ \\ / _ \\ / // /_ / // _ \\
                                                    / /  / // /_/ /   ___/ // / / //  __// // __// //  __/
                                                   /_/  /_/ \\__, /   /____//_/ /_/ \\___//_//_/  /_/ \\___/
                                                           /____/
                                """);
                        System.out.print("\n                INSERT YOU NICKNAME: ");
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
                                                							
                                                        					
                                        															
                                                       __  ___          _____  __           __ ____ _
                                                      /  |/  /__  __   / ___/ / /_   ___   / // __/(_)___
                                                     / /|_/ // / / /   \\__ \\ / __ \\ / _ \\ / // /_ / // _ \\
                                                    / /  / // /_/ /   ___/ // / / //  __// // __// //  __/
                                                   /_/  /_/ \\__, /   /____//_/ /_/ \\___//_//_/  /_/ \\___/
                                                           /____/
                                """);
                    System.out.println("\n                              Please, insert a CORRECT address ...");
                    try{
                        TimeUnit.SECONDS.sleep(2);
                    }catch (InterruptedException iE){
                        iE.printStackTrace();
                    }
                    System.out.print(CLS);
                    System.out.flush();
                    System.out.print("""
                                                							
                                                        					
                                        															
                                                       __  ___          _____  __           __ ____ _
                                                      /  |/  /__  __   / ___/ / /_   ___   / // __/(_)___
                                                     / /|_/ // / / /   \\__ \\ / __ \\ / _ \\ / // /_ / // _ \\
                                                    / /  / // /_/ /   ___/ // / / //  __// // __// //  __/
                                                   /_/  /_/ \\__, /   /____//_/ /_/ \\___//_//_/  /_/ \\___/
                                                           /____/
                                """);
                    System.out.print("\n                INSERT YOU NICKNAME: ");
                    break;
            }
            try{
                TimeUnit.MILLISECONDS.sleep(500);
            }catch (InterruptedException iE){
                iE.printStackTrace();
            }
            if(response!=null && response.getCategory()==Message.MessageCategory.WARNING) {
                System.out.print(CLS);
                System.out.flush();
                System.out.print("""
                                                							
                                                        					
                                        															
                                                       __  ___          _____  __           __ ____ _
                                                      /  |/  /__  __   / ___/ / /_   ___   / // __/(_)___
                                                     / /|_/ // / / /   \\__ \\ / __ \\ / _ \\ / // /_ / // _ \\
                                                    / /  / // /_/ /   ___/ // / / //  __// // __// //  __/
                                                   /_/  /_/ \\__, /   /____//_/ /_/ \\___//_//_/  /_/ \\___/
                                                           /____/
                                """);

                System.out.print("\n                        " + response.getReturnMessage());
                try{
                    TimeUnit.SECONDS.sleep(2);
                }catch (InterruptedException iE){
                    iE.printStackTrace();
                }
                System.out.print(CLS);
                System.out.flush();
                System.out.print("""
                                                							
                                                        					
                                        															
                                                       __  ___          _____  __           __ ____ _
                                                      /  |/  /__  __   / ___/ / /_   ___   / // __/(_)___
                                                     / /|_/ // / / /   \\__ \\ / __ \\ / _ \\ / // /_ / // _ \\
                                                    / /  / // /_/ /   ___/ // / / //  __// // __// //  __/
                                                   /_/  /_/ \\__, /   /____//_/ /_/ \\___//_//_/  /_/ \\___/
                                                           /____/
                                """);
                System.out.print("\n                INSERT YOU NICKNAME: ");
                response=null;
            }
        }while(response==null || (response.getCategory()!=Message.MessageCategory.VALID_NICKNAME && response!=null));
        if(connectionClient!=null){
            receiver.setInStartCLI(false);
            new LobbyHandler(connectionClient, receiver).start();
        }

    }
}
