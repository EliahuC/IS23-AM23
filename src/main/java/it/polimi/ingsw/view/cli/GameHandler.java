package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.ServerToClient.LivingRoomMessage;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Network.Client.ConnectionClient;
import it.polimi.ingsw.Messages.MoveSerializer;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.board.LivingRoom;
import it.polimi.ingsw.model.board.goalCards.*;
import it.polimi.ingsw.model.player.BookShelf;
import it.polimi.ingsw.model.player.Player;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class GameHandler {
    private ConnectionClient connectionClient;
    private CLIEvent receiver;
    private ServerMessage response;
    private LivingRoom livingRoom;
    private Player player;
    private List<Player> players;
    private List<ItemTile> tiles;
    private int currPlaying;
    private String currentPlayer;
    private static final String RESET = "\u001B[0m";
    private static final String FIRST = "\u001b[38;2;189;174;41m";
    private String winner;
    private CommonGoalCard commonGoalCard1;
    private CommonGoalCard commonGoalCard2;

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

    public void setLivingRoom(LivingRoom livingRoom) {
        this.livingRoom = livingRoom;
        createCommonGoalCard1(livingRoom.getIdCGC1());
        createCommonGoalCard2(livingRoom.getIdCGC2());
    }

    private void createCommonGoalCard2(Integer idCGC2) {
        switch (idCGC2){
            case 1->this.commonGoalCard2=new CommonGoalCard1(players.size());
            case 2->this.commonGoalCard2=new CommonGoalCard2(players.size());
            case 3->this.commonGoalCard2=new CommonGoalCard3(players.size());
            case 4->this.commonGoalCard2=new CommonGoalCard4(players.size());
            case 5->this.commonGoalCard2=new CommonGoalCard5(players.size());
            case 6->this.commonGoalCard2=new CommonGoalCard6(players.size());
            case 7->this.commonGoalCard2=new CommonGoalCard7(players.size());
            case 8->this.commonGoalCard2=new CommonGoalCard8(players.size());
            case 9->this.commonGoalCard2=new CommonGoalCard9(players.size());
            case 10->this.commonGoalCard2=new CommonGoalCard10(players.size());
            case 11->this.commonGoalCard2=new CommonGoalCard11(players.size());
            case 12->this.commonGoalCard2=new CommonGoalCard12(players.size());


        }
    }

    private void createCommonGoalCard1(Integer idCGC1) {
        switch (idCGC1){
            case 1->this.commonGoalCard1=new CommonGoalCard1(players.size());
            case 2->this.commonGoalCard1=new CommonGoalCard2(players.size());
            case 3->this.commonGoalCard1=new CommonGoalCard3(players.size());
            case 4->this.commonGoalCard1=new CommonGoalCard4(players.size());
            case 5->this.commonGoalCard1=new CommonGoalCard5(players.size());
            case 6->this.commonGoalCard1=new CommonGoalCard6(players.size());
            case 7->this.commonGoalCard1=new CommonGoalCard7(players.size());
            case 8->this.commonGoalCard1=new CommonGoalCard8(players.size());
            case 9->this.commonGoalCard1=new CommonGoalCard9(players.size());
            case 10->this.commonGoalCard1=new CommonGoalCard10(players.size());
            case 11->this.commonGoalCard1=new CommonGoalCard11(players.size());
            case 12->this.commonGoalCard1=new CommonGoalCard12(players.size());


        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public ConnectionClient getConnectionClient() {
        return connectionClient;
    }

    public void setCurrPlaying(int currPlaying) {
        this.currPlaying = currPlaying;
    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public void start(){
        while(true){
            if(/*player.getNickName()==currentPlayer || */player.getNickName()==players.get(currPlaying).getNickName()) {
                showBoard();
                showBookshelfOrder();
                showBookshelfColumn();
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException iE) {
                    iE.printStackTrace();
                }
                if (response != null && response.getCategory() == Message.MessageCategory.END_GAME_MESSAGE)
                    break;
                waiting();
            }else
                waiting();
        }
        showEnd();
    }

    private void waiting(){
        System.out.print("It's not your turn, yet. Wait for other players to finish their turn.\n\n");
        System.out.print("CURRENT PLAYING: ");
        if(players.get(currPlaying).isFirstPlayerSeat())
            System.out.print(FIRST+players.get(currPlaying).getNickName()+RESET+"\n");
        else
            System.out.print(players.get(currPlaying).getNickName()+"\n");
        try{
            TimeUnit.MILLISECONDS.sleep(200);
        }catch (InterruptedException iE){
            iE.printStackTrace();
        }
        if(response!=null && response.getCategory()==Message.MessageCategory.LAST_TURN_MESSAGE)
            System.out.print(response.getReturnMessage());
        while(!player.getNowPlaying()){
            /*System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.print("It's not your turn, yet. Wait for other players to finish their turn.\n\n");
            System.out.print("CURRENT PLAYING: ");
            if(players.get(currPlaying).isFirstPlayerSeat())
                System.out.print(FIRST+players.get(currPlaying).getNickName()+RESET+"\n");
            else
                System.out.print(players.get(currPlaying).getNickName()+"\n");
            try{
                TimeUnit.MILLISECONDS.sleep(200);
            }catch (InterruptedException iE){
                iE.printStackTrace();
            }
            if(response!=null && response.getCategory()==Message.MessageCategory.LAST_TURN_MESSAGE)
                System.out.print(response.getReturnMessage());
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException iE){
                iE.printStackTrace();
            }*/
        }
    }

    private void showBoard(){
        System.out.print("LIVING BOARD\n");
        livingRoom.print();
        try{
            TimeUnit.MILLISECONDS.sleep(200);
        }catch (InterruptedException iE){
            iE.printStackTrace();
        }
        System.out.print("\n\nPICK YOUR TILES! You can choose one, two or three tiles: use the command /SELECT\n" +
                "writing respectively the row's coordinate and the column's coordinate.\n" +
                "You must know that you can only pick adjacent tiles that are in the same row or in the same column,\n" +
                "plus you can only choose external tiles!\n\n" +
                "For example, to pick the two tiles in 8,5 and in 8,4, the right command is:\n" +
                "/SELECT 8 5 8 4\n\n" +
                "[Use the command /BOOKSHELF to see your personal bookshelf.]\n" +
                "[Use the command /GOALS to see the description of your personal or common goal cards.]\n");
        Scanner input = new Scanner(System.in);
        while (true){
            String command = input.nextLine();
            if(Objects.equals(command.toUpperCase(), "/GOALS")){
                showGoals("Living Board");
                break;
            }
            if(Objects.equals(command.toUpperCase(), "/BOOKSHELF")){
                showBookshelf();
                break;
            }
            ClientMessage message = (ClientMessage) MoveSerializer.serializeInput(command);
            connectionClient.sendMessage((ClientMessage) message);
            try{
                TimeUnit.MILLISECONDS.sleep(200);
            }catch (InterruptedException iE){
                iE.printStackTrace();
            }
            if(response!=null && response.getCategory()==Message.MessageCategory.VALID_MESSAGE){
                for(int i=1; command.split(" ")[i]!=null; i+=2)
                    tiles.add(livingRoom.getBoardTile(i,i+1).getTile());
                break;
            }
            System.out.print("Your move is not valid. Please, pick again and correctly your tiles.\n" +
                    "[You can still see your goal cards, using the command /GOALS, or your personal bookshelf using /BOOKSHELF]\n");
        }
    }

    private void showGoals(String scenario){
        Scanner input = new Scanner(System.in);
        String command;
        System.out.print("YOUR PERSONAL GOAL CARD\n\n");
        player.getPersonalGoalCard().print();
        System.out.print("\nCOMMON GOAL CARDS\n\n");
        System.out.print("(1): ");
        commonGoalCard1.print();
        System.out.print("\n(2): ");
        commonGoalCard2.print();
        System.out.print("\n\n[If you want to come back to the previous screen, use the command /BACK]\n");
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
        System.out.print("THE WINNER IS: " + winner + "\n");
        List<Player> ranking = players.stream().sorted(Comparator.comparingInt(Player::getScore)).toList();
        for(Player p : ranking){
            if(p.getNickName()!=winner){
                System.out.print(p.getNickName() + ": "+ p.getScore() + "points\n");
            }
        }
    }

    private void showBookshelfOrder(){
        Scanner input = new Scanner(System.in);
        System.out.print("YOUR BOOKSHELF\n");
        player.getPlayerBookshelf().print();
        System.out.print("ORDER YOUR TILES! The tiles you picked before from the board are shown above.\n" +
                "Use the command /ORDER to choose in which order you want to insert the tiles in your bookshelf.\n\n" +
                "For example: if you have three tiles to order, you could write: /ORDER 2 1 3 or /ORDER 3 2 1\n" +
                "(If you have just one picked tile, just type: /ORDER 1\n\n" +
                "[Use the command /GOALS to see the description of your personal or common goal cards.]\n");
        printSelection();
        while(true){
            String command = input.nextLine();
            if(Objects.equals(command.toUpperCase(), "/GOALS")){
                showGoals("BookshelfOrder");
                break;
            }
            Message message = MoveSerializer.serializeInput(command);
            connectionClient.sendMessage((ClientMessage) message);
            try{
                TimeUnit.MILLISECONDS.sleep(200);
            }catch (InterruptedException iE){
                iE.printStackTrace();
            }
            if(response!=null && response.getCategory()==Message.MessageCategory.VALID_MESSAGE)
                break;
            System.out.print("You didn't choose the order appropriately. Please, retry.\n");
        }
    }

    private void showBookshelfColumn(){
        Scanner input = new Scanner(System.in);
        System.out.print("YOUR BOOKSHELF\n");
        player.getPlayerBookshelf().print();
        System.out.print("CHOOSE THE COLUMN! Choose where you want to inserted the picked and order tiles,\n" +
                "using the command /COLUMN and the coordinate of the column.\n" +
                "For example: if you want to insert the tiles in the second column, you should write /COLUMN 1\n\n" +
                "[Use the command /GOALS to see the description of your personal or common goal cards.]\n");
        while(true){
            String command = input.nextLine();
            if(Objects.equals(command.toUpperCase(), "/GOALS")){
                showGoals("BookshelfColumn");
                break;
            }
            Message message = MoveSerializer.serializeInput(command);
            connectionClient.sendMessage((ClientMessage) message);
            try{
                TimeUnit.MILLISECONDS.sleep(200);
            }catch (InterruptedException iE){
                iE.printStackTrace();
            }
            if(response!=null && response.getCategory()==Message.MessageCategory.VALID_MESSAGE)
                break;
            System.out.print("The chosen column is too full. Please, choose another one.\n");
        }
        tiles.clear();
    }

    private void showBookshelf(){
        Scanner input = new Scanner(System.in);
        String command;
        System.out.print("YOUR BOOKSHELF\n\n");
        player.getPlayerBookshelf().print();
        System.out.print("\n\n[If you want to come back to the previous screen, use the command /BACK]\n");
        while (true){
            command = input.nextLine();
            if(Objects.equals(command.toUpperCase(), "/BACK"))
                break;
            System.out.print("Please, use the /BACK command correctly.\n");
        }
       showBoard();
    }

    private void printSelection(){
        for(int i=0; tiles.get(i)!=null; i++)
            System.out.print(tiles.get(i).getColor() + "   ");
        System.out.print("\n");
        for(int i=0; tiles.get(i)!=null; i++)
            System.out.print("(" + i+1 + ")   ");
        System.out.print("\n");
    }
}
