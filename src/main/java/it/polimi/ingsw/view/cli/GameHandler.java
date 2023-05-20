package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.ServerToClient.LivingRoomMessage;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Network.Client.ConnectionClient;
import it.polimi.ingsw.Messages.MoveSerializer;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Player;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GameHandler {
    private ConnectionClient connectionClient;
    private CLIEvent receiver;
    private ServerMessage response;
    private Game source;

    public GameHandler(ConnectionClient connectionClient, CLIEvent receiver) {
        this.connectionClient = connectionClient;
        this.receiver=receiver;
        this.receiver.setInGameHandler(true);
        connectionClient.setListener(receiver);
        this.receiver.setGameHandler(this);
    }

    public void setResponse(ServerMessage response){
        this.response=response;
    }

    public void start(){
        while(true){
            showBoard();
            showBookshelfOrder();
            showBookshelfColumn();
            try{
                TimeUnit.MILLISECONDS.sleep(200);
            }catch (InterruptedException iE){
                iE.printStackTrace();
            }
            if(response!=null && response.getCategory()==Message.MessageCategory.END_GAME_MESSAGE)
                break;
            waiting();
        }
        showEnd();
    }

    private void waiting(){
        //while(non è il tuo turno) //Esiste un messaggio che permette di capire chi sta giocando in questo momento?
            System.out.print("It's not your turn, yet. Wait for other players to finish their turn.\n\n");
            System.out.print("CURRENT PLAYING:"+"\n");
            System.out.print("Next playing:"+"\n");
            System.out.print("Next playing:"+"\u001b[189;174;41m\n");
        //}
        try{
            TimeUnit.MILLISECONDS.sleep(200);
        }catch (InterruptedException iE){
            iE.printStackTrace();
        }
        if(response!=null && response.getCategory()==Message.MessageCategory.LAST_TURN_MESSAGE)
            System.out.print(response.getReturnMessage());
    }

    private void showBoard(){
        try{
            TimeUnit.MILLISECONDS.sleep(200);
        }catch (InterruptedException iE){
            iE.printStackTrace();
        }
        System.out.print("LIVING BOARD\n");
        /*if(response!=null && response.getCategory()==Message.MessageCategory.LIVINGROOM){ //serve un messaggio che mandi una LivingRoom a ogni inizio turno
            LivingRoomMessage temp=(LivingRoomMessage) response;
            temp.getBoard().print(); //il messaggio LIVINGROOM deve ritornare una LivingRoom, non una BoardToken[][]!
        }*/ //STAMPA DELLA BOARD
        try{
            TimeUnit.MILLISECONDS.sleep(200);
        }catch (InterruptedException iE){
            iE.printStackTrace();
        }
        if(response!=null && response.getCategory()==Message.MessageCategory.LIVINGROOM)
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
            try{
                TimeUnit.MILLISECONDS.sleep(200);
            }catch (InterruptedException iE){
                iE.printStackTrace();
            }
            if(response!=null && response.getCategory()==Message.MessageCategory.RETURN_MESSAGE) //Messaggio che conferma la corretta scelta delle tessere
                break;
            System.out.print("Your move is not valid. Please, pick again and correctly your tiles.\n" +
                    "[You can still see your goal cards, using the command /GOALS\n");
        }
    }

    private void showGoals(String scenario){
        Scanner input = new Scanner(System.in);
        String command;
        //printer carte obiettivo
        System.out.print("YOUR PERSONAL GOAL CARD\n");
        Player temp= (Player) source.getPlayers().stream().filter(player -> Objects.equals(player.getNickName(), connectionClient.getPlayerName()));
        temp.getPersonalGoalCard().print();
        System.out.print("COMMON GOAL CARDS\n");
        System.out.print("(1): ");
        source.getLivingRoom().getCommonGoalCard1().print();
        System.out.print("(2): ");
        source.getLivingRoom().getCommonGoalCard2().print();
        System.out.print("[If you want to come back to the previous screen, use the command /BACK]\n");
        while (true){
            command = input.nextLine();
            if(Objects.equals(command.toUpperCase(), "/BACK"))
                break;
            System.out.print("Please, use the /BACK command correctly.\n");
        }
        switch (scenario) {
            case "Living Board" -> showBoard();
            case "BookshelfOrder" -> showBookshelfOrder();
            case "BookshelfColumn" -> showBookshelfColumn();
        }

    }

    private void showEnd(){
        System.out.print("THE WINNER IS: "+"");
    }

    private void showBookshelfOrder(){
        Scanner input = new Scanner(System.in);
        System.out.print("YOUR BOOKSHELF\n");
        Player temp= (Player) source.getPlayers().stream().filter(player -> Objects.equals(player.getNickName(), connectionClient.getPlayerName()));
        temp.getPlayerBookshelf().print();
        System.out.print("Now, choose the order for the tiles to be inserted into your bookshelf using the command /ORDER t1 t2 t3\n" +
                "Consider that during the selection of the tiles from the board, you respectively wrote the two coordinates first for the tile t1, then for the tile t2, finally for the tile t3.\n" +
                "For example: if you wrote down the coordinates for t1, t2, t3 and you want to insert first the tile t2, then t3 and finally t1 into your bookshelf, the correct use for the command is /ORDER t2 t3 t1\n\n" +
                "[If you want to see the description of your personal or common goal cards, use the command /GOALS\n]");
        //Per una comprensione migliore, sarebbe preferibile visualizzare le tre tessere selezionate ma non some recuperarne il riferimento
        while(true){
            String command = input.nextLine();
            if(Objects.equals(command.toUpperCase(), "/GOALS")){
                showGoals("BookshelfOrder");
            }
            Message message = MoveSerializer.serializeInput(command);
            connectionClient.sendMessage((ClientMessage) message);
            try{
                TimeUnit.MILLISECONDS.sleep(200);
            }catch (InterruptedException iE){
                iE.printStackTrace();
            }
            if(response!=null && response.getCategory()==Message.MessageCategory.RETURN_MESSAGE) //Messaggio che conferma la corretta scelta dell'ordine delle tessere (ovvero se ho scelto prima x tessere, ne ho ordinate x)
                break;
            System.out.print("You didn't choose the order appropriately. Please, retry.\n");
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
            try{
                TimeUnit.MILLISECONDS.sleep(200);
            }catch (InterruptedException iE){
                iE.printStackTrace();
            }
            if(response!=null && response.getCategory()==Message.MessageCategory.RETURN_MESSAGE) //Messaggio che conferma il corretto inserimento delle tessere nella libreria
                break;
            System.out.print("The chosen column is too full. Please, choose another one.\n");
        }

    }
}
