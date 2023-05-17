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


    public LobbyHandler(ConnectionClient connectionClient) {
        this.connectionClient = connectionClient;
        receiver=new CLIEvent(this);
    }

    public void setResponse(ServerMessage response){
        this.response=response;
    }

    public void start() {
        String command;
        Scanner input = new Scanner(System.in);
        ServerMessage serverMessage;

        while(true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.print("Do you want to look for a lobby to join or do you prefer to make a new one?\n" +
                    "Please use the following commands:\n" +
                    "/CREATE  <number of players> (Remember that the number of players can only be 2, 3 or 4!)\n" +
                    "/ENTER \n");

            command = input.nextLine();
            if(Objects.equals(command.split(" ")[0].toUpperCase(), "/CREATE") || Objects.equals(command.split(" ")[0].toUpperCase(), "/ENTER"))
                break;
            System.out.print("Please, use the correct commands.\n");
        }
        Message message = MoveSerializer.serializeInput(command);
        connectionClient.sendMessage((ClientMessage) message);
        while(true) {
            try{
                TimeUnit.SECONDS.sleep(3);
            }catch (InterruptedException iE){
                iE.printStackTrace();
            }
            if(response!=null && response.getCategory()==Message.MessageCategory.WARNING){
                System.out.print(response.getReturnMessage());
                command = input.nextLine();
                message = MoveSerializer.serializeInput(command);
                connectionClient.sendMessage((ClientMessage) message);
            } else if (response!=null) {
                break;
            }
            System.out.print("LOOP");
        }

        System.out.print("Hi" + connectionClient.getPlayerName() + "! Let's wait for other players to begin the game...\n" +
                "If you want to exit from the game, please use the command: /EXIT\n");
        command = input.nextLine();
        message = MoveSerializer.serializeInput(command);
        connectionClient.sendMessage((ClientMessage) message);

        while(true) {
            if(response!=null && response.getCategory()==Message.MessageCategory.RETURN_MESSAGE){
                System.out.print(response.getReturnMessage());
                break;
            }
        }

        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException iE){
            iE.printStackTrace();
        }
        //new GameHandler(connectionClient).start();
    }
}
