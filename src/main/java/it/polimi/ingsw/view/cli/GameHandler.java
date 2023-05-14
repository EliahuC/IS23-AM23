package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Network.Client.ConnectionClient;
import it.polimi.ingsw.Messages.MoveSerializer;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class GameHandler {
    private ConnectionClient connectionClient;


    public GameHandler(ConnectionClient connectionClient) {
        this.connectionClient = connectionClient;
    }

    public void start(){
        ServerMessage serverMessage;
        while(true){
            showBoard();
            showBookshelfOrder();
            showBookshelfColumn();
            try {
                serverMessage = connectionClient.receiveMessage();
            }catch (IOException | ClassNotFoundException e){
                continue;
            }
            if(serverMessage.getCategory()==Message.MessageCategory.END_GAME_MESSAGE)
                break;
            waiting();
        }
        showEnd();
    }

    private void waiting(){
        //while(non è il tuo turno) //Il player è associato alla classe?
            System.out.print("It's not your turn, yet. Wait for other players to finish their turn.\n\n");
            System.out.print("CURRENT PLAYING:"+"");
            System.out.print("Next playing:"+"");
        //}
    }

    private void showBoard(){
        ServerMessage serverMessage = new ServerMessage(Message.MessageCategory.PINGFROMSERVER);
        try {
            serverMessage = connectionClient.receiveMessage();
        }catch (IOException | ClassNotFoundException e){};

        System.out.print("LIVING BOARD\n");
        //printer della board
        if(serverMessage.getCategory()==Message.MessageCategory.LIVINGROOM)
            System.out.print("THE BOARD HAS BEEN RESTORED!\n\n");
        System.out.print("If you want to see the description of your personal or common goal cards, use the command /GOALS\n" +
                "You can only choose one, two or three adjacent tiles that are in the same column or in the same row. You can only choose tiles on the border.\n\n");
        System.out.print("It's time for you to make your move and pick the tiles you want to insert into your library. You can pick one, two or three tiles.\n" +
                "Assure to respect the rules of the game and the capability of your bookshelf!\n" +
                "Use the command /SELECT <row of tile one> <column of tile one> <row of tile two> <column of tile two> <row of tile three> <column of tile three>\n");
        Scanner input = new Scanner(System.in);
        while (true){
            String command = input.nextLine();
            if(Objects.equals(command.toUpperCase(), "/GOALS")){
                showGoals("Living Board");
            }
            Message message = MoveSerializer.serializeInput(command);
            connectionClient.sendMessage((ClientMessage) message);
            try {
                serverMessage = connectionClient.receiveMessage();
            }catch (IOException | ClassNotFoundException e){
                continue;
            }
            if(serverMessage.getCategory()==Message.MessageCategory.RETURN_MESSAGE)
                break;
            System.out.print("Your move is not valid. Please, pick again and correctly your tiles.\n");
        }
    }

    private void showGoals(String scenario){
        Scanner input = new Scanner(System.in);
        String command;
        //printer carte obiettivo
        System.out.print("[If you want to come back to the previous screen, use the command /BACK]\n");
        while (true){
            command = input.nextLine();
            if(Objects.equals(command.toUpperCase(), "/BACK"))
                break;
        }
        switch (scenario){
            case "Living Board": showBoard();
            break;
            case "BookshelfOrder": showBookshelfOrder();
            break;
            case "BookshelfColumn": showBookshelfColumn();
            break;
        }

    }

    private void showEnd(){
        System.out.print("THE WINNER IS: "+"");
    }

    private void showBookshelfOrder(){
        Scanner input = new Scanner(System.in);
        System.out.print("YOUR BOOKSHELF\n");
        //printer libreria
        System.out.print("Now, choose the order for the tiles to be inserted into your bookshelf using the command /ORDER t1 t2 t3\n" +
                "Consider that during the selection of the tiles from the board, you respectively wrote the two coordinates first for the tile t1, then for the tile t2, finally for the tile t3.\n" +
                "For example: if you wrote down the coordinates for t1, t2, t3 and you want to insert first the tile t2, then t3 and finally t1 into your bookshelf, the correct use for the command is /ORDER t2 t3 t1\n\n" +
                "[If you want to see the description of your personal or common goal cards, use the command /GOALS\n]");
        while(true){
            String command = input.nextLine();
            if(Objects.equals(command.toUpperCase(), "/GOALS")){
                showGoals("BookshelfOrder");
            }
            Message message = MoveSerializer.serializeInput(command);
            connectionClient.sendMessage((ClientMessage) message);
            //if(il comando è stato scritto correttamente) Non so se esiste il controllo on server
            break;
            //System.out.print("You didn't choose the order appropriately. Please, retry.\n");
        }
    }

    private void showBookshelfColumn(){
        Scanner input = new Scanner(System.in);
        System.out.print("Now, it's time to choose the column where you want to insert the picked tiles using the command /COLUMN <column>\n" +
                "<column> value can be 0 (the first column on the left), 1, 2, 3 or 4 (the first column on the right).\n\n" +
                "[If you want to see the description of your personal or common goal cards, use the command /GOALS\n]");
        while(true){
            String command = input.nextLine();
            if(Objects.equals(command.toUpperCase(), "/GOALS")){
                showGoals("BookshelfColumn");
            }
            Message message = MoveSerializer.serializeInput(command);
            connectionClient.sendMessage((ClientMessage) message);
            //if(la colonna scelta non è piena) Non so se sia compreso in un messaggio.
            break;
            //System.out.print("The chosen column is too full. Please, choose another one.\n");
        }
    }
}
