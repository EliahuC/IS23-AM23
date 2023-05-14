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
    private MoveSerializer moveSerializer;

    public LobbyHandler(ConnectionClient connectionClient) {
        this.connectionClient = connectionClient;
        moveSerializer = new MoveSerializer();
    }

    public void start() {
        String command;
        Scanner input = new Scanner(System.in);
        ServerMessage serverMessage;

        while(true) {
            System.out.print("Do you want to look for a lobby to join or do you prefer to make a new one?\n" +
                    "Please use the following commands:\n" +
                    "/CREATE  <number of players> (Remember that the number of players can only be 2, 3 or 4!)\n" +
                    "/ENTER \n");

            command = input.nextLine().toUpperCase();
            if(Objects.equals(command.split(" ")[0], "/CREATE") || Objects.equals(command.split(" ")[0], "/ENTER"))
                break;
            System.out.print("Please, use the correct commands.\n");
        }
        Message message = moveSerializer.serializeInput(command);
        connectionClient.sendMessage((ClientMessage) message);
        while(true) {
            try {
                serverMessage = connectionClient.receiveMessage();
            }catch (IOException | ClassNotFoundException e){
                continue;
            }
            if(serverMessage.getCategory()==Message.MessageCategory.WARNING){
                System.out.print(serverMessage.getReturnMessage());
                command = input.nextLine();
                message = moveSerializer.serializeInput(command);
                connectionClient.sendMessage((ClientMessage) message);
            } else
                break;
        }

        System.out.print("Hi" + connectionClient.getPlayerName() + "! Let's wait for other players to begin the game...\n" +
                "If you want to exit from the game, please use the command: /EXIT\n");
        /*command = input.nextLine();
        message = moveSerializer.serializeInput(command);
        connectionClient.sendMessage(message);*/
        //}

        while(true) {
            try {
                serverMessage = connectionClient.receiveMessage();
            }catch (IOException | ClassNotFoundException e){
                continue;
            }
            if(serverMessage.getCategory()==Message.MessageCategory.RETURN_MESSAGE){
                System.out.print("The lobby is full! The game will begin soon!\n");
                break;
            }
        }

        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException iE){
            iE.printStackTrace();
        }
        new GameHandler(connectionClient).start();
    }
}
