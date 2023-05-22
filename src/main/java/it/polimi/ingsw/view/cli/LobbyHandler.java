package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Network.Client.ConnectionClient;
import it.polimi.ingsw.Messages.MoveSerializer;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class LobbyHandler {
    private ConnectionClient connectionClient;
    private CLIEvent receiver;
    private ServerMessage response;


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

    public void start() {
        GameHandler gameHandler= new GameHandler(connectionClient, receiver);
        receiver.setGameHandler(gameHandler);
        String command;
        Scanner input = new Scanner(System.in);
        ServerMessage serverMessage;
        /*System.out.print("\n" +
                "    __  ___          _____  __           __ ____ _     \n" +
                "   /  |/  /__  __   / ___/ / /_   ___   / // __/(_)___ \n" +
                "  / /|_/ // / / /   \\__ \\ / __ \\ / _ \\ / // /_ / // _ \\\n" +
                " / /  / // /_/ /   ___/ // / / //  __// // __// //  __/\n" +
                "/_/  /_/ \\__, /   /____//_/ /_/ \\___//_//_/  /_/ \\___/ \n" +
                "        /____/                                         \n\n\n\n");*/
        while(true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.print("Do you want to look for a lobby to join or do you prefer to make a new one?\n" +
                    "Please use the following commands:\n" +
                    "/CREATE <number of players> (Remember that the number of players can only be 2, 3 or 4!)\n" +
                    "/ENTER \n");

            command = input.nextLine();
            if((Objects.equals(command.split(" ")[0].toUpperCase(), "/CREATE")) || Objects.equals(command.split(" ")[0].toUpperCase(), "/ENTER"))
                break;
            System.out.print("The used command is NOT valid. Please, retry again.\n");
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
                System.out.println(response.getReturnMessage());
                while(true) {
                    command = input.nextLine();
                    message = MoveSerializer.serializeInput(command);
                    if((Objects.equals(command.split(" ")[0].toUpperCase(), "/CREATE"))){
                        connectionClient.sendMessage((ClientMessage) message);
                        break;
                    }else
                        System.out.print("The used command is NOT valid. Please, retry again.\n");
                }
            } else if (response!=null && response.getCategory()==Message.MessageCategory.RETURN_MESSAGE) {
                break;
            }
        }
        System.out.print("Hi " + connectionClient.getPlayerName() + "! Let's wait for other players to begin the game.\n");
        do{
            try{
                TimeUnit.MILLISECONDS.sleep(200);
            }catch (InterruptedException iE){
                iE.printStackTrace();
            }
            /*System.out.print("Hi " + connectionClient.getPlayerName() + "! Let's wait for other players to begin the game.\n");
            try{
                TimeUnit.MILLISECONDS.sleep(500);
            }catch (InterruptedException iE){
                iE.printStackTrace();
            }
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.print("Hi " + connectionClient.getPlayerName() + "! Let's wait for other players to begin the game .\n");
            try{
                TimeUnit.MILLISECONDS.sleep(500);
            }catch (InterruptedException iE){
                iE.printStackTrace();
            }
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.print("Hi " + connectionClient.getPlayerName() + "! Let's wait for other players to begin the game  .\n");
            try{
                TimeUnit.MILLISECONDS.sleep(500);
            }catch (InterruptedException iE){
                iE.printStackTrace();
            }
            System.out.print("\033[H\033[2J");
            System.out.flush();*/
        }while(response == null || response.getCategory() != Message.MessageCategory.STARTING_GAME_MESSAGE);
        //System.out.print(response.getReturnMessage());
        receiver.setInLobbyHandler(false);
        receiver.setInGameHandler(true);
        gameHandler.start();
    }
}
