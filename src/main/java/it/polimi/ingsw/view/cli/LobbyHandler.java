package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Network.Client.ConnectionClient;
import it.polimi.ingsw.Messages.MoveSerializer;


import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Lobby entrance / creation page
 * @author Simone Controguerra
 *
 *
 */
public class LobbyHandler {
    private final ConnectionClient connectionClient;
    private final CLIEvent receiver;
    private ServerMessage response;
    private Boolean lock=true;
    private final static String CLS = "\u001B[2J\u001B[3J\u001B[H";


    public LobbyHandler(ConnectionClient connectionClient, CLIEvent receiver) {
        this.connectionClient = connectionClient;
        this.receiver=receiver;
        this.receiver.setInLobbyHandler(true);
        connectionClient.setListener(receiver);
        this.receiver.setLobbyHandler(this);
    }

    public void setResponse(ServerMessage response){
        this.response=response;
    }

    /**
     * Joining Lobby Page
     * @author Simone Controguerra and Eliahu Cohen
     *
     */
    public void start() {
        GameHandler gameHandler= new GameHandler(connectionClient, receiver);
        receiver.setGameHandler(gameHandler);
        String command;
        Scanner input = new Scanner(System.in);

        while(true) {
            System.out.print(CLS);
            System.out.flush();
            System.out.println("""
                                                							
                                                        					
                                        															
                                                       __  ___          _____  __           __ ____ _
                                                      /  |/  /__  __   / ___/ / /_   ___   / // __/(_)___
                                                     / /|_/ // / / /   \\__ \\ / __ \\ / _ \\ / // /_ / // _ \\
                                                    / /  / // /_/ /   ___/ // / / //  __// // __// //  __/
                                                   /_/  /_/ \\__, /   /____//_/ /_/ \\___//_//_/  /_/ \\___/
                                                           /____/
                                """);
            System.out.println("""
                                      [NEW LOBBY] -> /CREATE <number of players> (2, 3, 4)
                                      [JOIN A LOBBY] -> /ENTER
                    """);
            System.out.print("                                  ");
            command = input.nextLine();
            if(Objects.equals(command.split(" ")[0].toUpperCase(), "/CREATE") && command.split(" ").length == 2){
                if(Integer.valueOf(command.split(" ")[1])<=4 && Integer.valueOf(command.split(" ")[1])>1)
                    break;
            } else if (Objects.equals(command.split(" ")[0].toUpperCase(), "/ENTER")) {
                break;
            }
            System.out.println("                   The used command is NOT valid. Please, retry again!\n");
            try{
                TimeUnit.MILLISECONDS.sleep(1200);
            }catch (InterruptedException iE){
                iE.printStackTrace();
            }
        }
        Message message = MoveSerializer.serializeInput(command);
        connectionClient.sendMessage((ClientMessage) message);
        while(true) {
            try{
                TimeUnit.MILLISECONDS.sleep(200);
            }catch (InterruptedException iE){
                iE.printStackTrace();
            }
            if(response!=null && response.getCategory()==Message.MessageCategory.WARNING){
                System.out.println("                   " + response.getReturnMessage());
                System.out.print("                                  ");
                command=input.nextLine();
                message= MoveSerializer.serializeInput(command);
                if(message.messageCategory!= Message.MessageCategory.WARNING)
                    connectionClient.sendMessage((ClientMessage) message);
            } else if (response!=null && (response.getCategory()==Message.MessageCategory.RETURN_MESSAGE || response.getCategory() == Message.MessageCategory.STARTING_GAME_MESSAGE)) {
                if (response.getCategory() == Message.MessageCategory.STARTING_GAME_MESSAGE) {
                    receiver.setInLobbyHandler(false);
                    receiver.setInGameHandler(true);
                    gameHandler.start();
                    lock=false;
                    return;
                }
                else
                    break;
            }
        }
        System.out.println(CLS);
        System.out.flush();
        System.out.println("""
                                                							
                                                        					
                                        															
                                                       __  ___          _____  __           __ ____ _
                                                      /  |/  /__  __   / ___/ / /_   ___   / // __/(_)___
                                                     / /|_/ // / / /   \\__ \\ / __ \\ / _ \\ / // /_ / // _ \\
                                                    / /  / // /_/ /   ___/ // / / //  __// // __// //  __/
                                                   /_/  /_/ \\__, /   /____//_/ /_/ \\___//_//_/  /_/ \\___/
                                                           /____/
                                """);
        System.out.print("\n\n                  Hi " + connectionClient.getPlayerName() + "! Let's wait for other players to begin the game.");
        if(lock){
            while(response == null || response.getCategory() != Message.MessageCategory.STARTING_GAME_MESSAGE){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException iE) {
                    iE.printStackTrace();
                }
                System.out.println(CLS); //TODO abilitare durante la creazione del JAR
                System.out.flush();
                System.out.println("""
                                                							
                                                        					
                                        															
                                                       __  ___          _____  __           __ ____ _
                                                      /  |/  /__  __   / ___/ / /_   ___   / // __/(_)___
                                                     / /|_/ // / / /   \\__ \\ / __ \\ / _ \\ / // /_ / // _ \\
                                                    / /  / // /_/ /   ___/ // / / //  __// // __// //  __/
                                                   /_/  /_/ \\__, /   /____//_/ /_/ \\___//_//_/  /_/ \\___/
                                                           /____/
                                """);
                System.out.print("\n\n                  Hi " + connectionClient.getPlayerName() + "! Let's wait for other players to begin the game .");
                try{
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException iE){
                    iE.printStackTrace();
                }
                System.out.println(CLS);
                System.out.flush();
                System.out.println("""
                                                							
                                                        					
                                        															
                                                       __  ___          _____  __           __ ____ _
                                                      /  |/  /__  __   / ___/ / /_   ___   / // __/(_)___
                                                     / /|_/ // / / /   \\__ \\ / __ \\ / _ \\ / // /_ / // _ \\
                                                    / /  / // /_/ /   ___/ // / / //  __// // __// //  __/
                                                   /_/  /_/ \\__, /   /____//_/ /_/ \\___//_//_/  /_/ \\___/
                                                           /____/
                                """);
                System.out.print("\n\n                  Hi " + connectionClient.getPlayerName() + "! Let's wait for other players to begin the game  .");
                try{
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException iE){
                    iE.printStackTrace();
                }
                System.out.println(CLS);
                System.out.flush();
                System.out.println("""
                                                							
                                                        					
                                        															
                                                       __  ___          _____  __           __ ____ _
                                                      /  |/  /__  __   / ___/ / /_   ___   / // __/(_)___
                                                     / /|_/ // / / /   \\__ \\ / __ \\ / _ \\ / // /_ / // _ \\
                                                    / /  / // /_/ /   ___/ // / / //  __// // __// //  __/
                                                   /_/  /_/ \\__, /   /____//_/ /_/ \\___//_//_/  /_/ \\___/
                                                           /____/
                                """);
                System.out.print("\n\n                  Hi " + connectionClient.getPlayerName() + "! Let's wait for other players to begin the game.");//*/
            }
        }
        receiver.setInLobbyHandler(false);
        receiver.setInGameHandler(true);
        gameHandler.start();
    }
}
