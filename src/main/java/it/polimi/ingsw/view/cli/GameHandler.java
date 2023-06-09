package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Network.Client.ConnectionClient;
import it.polimi.ingsw.Messages.MoveSerializer;
import it.polimi.ingsw.model.board.BoardToken;
import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.board.LivingRoom;
import it.polimi.ingsw.model.board.goalCards.*;
import it.polimi.ingsw.model.player.BookShelf;
import it.polimi.ingsw.model.player.Pair;
import it.polimi.ingsw.model.player.PersonalGoalCard;
import it.polimi.ingsw.model.player.Player;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class GameHandler {
    private final ConnectionClient connectionClient;
    private ServerMessage response;
    private LivingRoom livingRoom;
    private Player player;
    private Integer seed;
    private final BookShelf personalGoalCard=new BookShelf();
    private ArrayList<Player> players;
    private final ArrayList<ItemTile> tiles = new ArrayList<>();
    private int currPlaying=1;
    private String currentPlayer;
    private static final String RESET = "\u001B[0m";
    private static final String CHAIR = "\u001b[38;2;189;174;41m";
    private static final String GREEN = "\u001b[48;2;145;165;65m";
    private static final String WHITE = "\u001b[48;2;236;225;189m";
    private static final String YELLOW = "\u001b[48;2;223;169;59m";
    private static final String BLUE = "\u001b[48;2;0;104;146m";
    private static final String CYAN = "\u001b[48;2;106;183;183m";
    private static final String PINK = "\u001b[48;2;198;77;124m";
    public static final int LivingRoomSize=9;
    private static final int shelfRows = 6;
    private static final int shelfCols = 5;
    private String winner;
    private CommonGoalCard commonGoalCard1;
    private CommonGoalCard commonGoalCard2;

    public GameHandler(ConnectionClient connectionClient, CLIEvent receiver) {
        this.connectionClient = connectionClient;
        receiver.setInGameHandler(true);
        connectionClient.setListener(receiver);
        receiver.setGameHandler(this);
    }

    public void setResponse(ServerMessage response){
        this.response=response;
    }

    public void setLivingRoom(LivingRoom livingRoom) {
        this.livingRoom = livingRoom;
        createCommonGoalCard1(livingRoom.getIdCGC1());
        createCommonGoalCard2(livingRoom.getIdCGC2());
    }
    public void setBoard(BoardToken[][] board){
        this.livingRoom.setBoard(board);
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

    public void setPlayers(ArrayList<Player> players) {
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
        buildPersonalGoalCard();
        while(true){
            if(player.getNickName().equals(players.get(currPlaying-1).getNickName())) {
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
                System.out.println("Your turn is finished");
                waiting();
            }else
                waiting();
        }
        showEnd();
    }

    private void waiting(){
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException iE) {
            iE.printStackTrace();
        }
        System.out.println("It's not your turn, yet. Wait for other players to finish their turn.\n");
        System.out.print("CURRENT PLAYING: ");
        if(players.get(currPlaying-1).isFirstPlayerSeat())
            System.out.println(CHAIR +players.get(currPlaying-1).getNickName()+RESET);
        else
            System.out.println(players.get(currPlaying-1).getNickName());
        try{
            TimeUnit.MILLISECONDS.sleep(500);
        }catch (InterruptedException iE){
            iE.printStackTrace();
        }
        if(response!=null && response.getCategory()==Message.MessageCategory.LAST_TURN_MESSAGE)
            System.out.println(response.getReturnMessage());
        while(!Objects.equals(player.getNickName(), players.get(currPlaying - 1).getNickName())){
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
            try{
                TimeUnit.MILLISECONDS.sleep(200);
            }catch (InterruptedException iE){
                iE.printStackTrace();
            }
        }
    }

    private void showBoard() {
        System.out.println("LIVING BOARD");
        printBoard();
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException iE) {
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
        String command;
        while (true) {
            command = input.nextLine();
            if (command!=null&&Objects.equals(command.toUpperCase(), "/GOALS")) {
                showGoals("Living Board");
                break;
            }
            if (command!=null&&Objects.equals(command.toUpperCase(), "/BOOKSHELF")) {
                showBookshelf();
                break;
            }
            while(command!=null && !Objects.equals(command.toUpperCase().split(" ")[0], "/SELECT")) {
                System.out.println("Please, use the command /SELECT");
                command = input.nextLine();
            }
            if(command!=null){
                ClientMessage message = (ClientMessage) MoveSerializer.serializeInput(command);
                connectionClient.sendMessage(message);
            }

            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException iE) {
                iE.printStackTrace();
            }
            if (response != null && response.getCategory() == Message.MessageCategory.VALID_MESSAGE) {
                for (int i = 1; i < Arrays.stream(command.split(" ")).count(); i += 2)
                    tiles.add(livingRoom.getBoardTile(Integer.parseInt(command.split(" ")[i]), Integer.parseInt(command.split(" ")[i + 1])).getTile());
                break;
            }
            command=null;
            if (response != null && response.getCategory() == Message.MessageCategory.WARNING) {
                System.out.print("Your move is not valid. Please, pick again and correctly your tiles.\n" +
                        "[You can still see your goal cards, using the command /GOALS, or your personal bookshelf using /BOOKSHELF]\n");
            }
        }
    }
    private void showGoals(String scenario){
        Scanner input = new Scanner(System.in);
        String command;
        System.out.println("YOUR PERSONAL GOAL CARD\n");
        printPersonalGoalCard();
        System.out.println("\nCOMMON GOAL CARDS\n");
        System.out.print("(1): ");
        commonGoalCard1.print();
        System.out.print("\n(2): ");
        commonGoalCard2.print();
        System.out.println("\n\n[If you want to come back to the previous screen, use the command /BACK]");
        while (true){
            command = input.nextLine();
            if(Objects.equals(command.toUpperCase(), "/BACK"))
                break;
            System.out.println("Please, use the /BACK command correctly.");
        }
        switch (scenario) {
            case "Living Board" -> showBoard();
            case "BookshelfOrder" -> showBookshelfOrder();
            case "BookshelfColumn" -> showBookshelfColumn();
        }

    }

    private void showEnd(){
        System.out.println("THE WINNER IS: " + winner);
        List<Player> ranking = players.stream().sorted(Comparator.comparingInt(Player::getScore)).toList();
        for(Player p : ranking){
            if(!Objects.equals(p.getNickName(), winner)){
                System.out.println(p.getNickName() + ": "+ p.getScore() + "points");
            }
        }
    }

    private void showBookshelfOrder() {
        Scanner input = new Scanner(System.in);
        System.out.println("YOUR BOOKSHELF\n");
        printBookshelf();
        System.out.print("\n\nORDER YOUR TILES! The tiles you picked before from the board are shown above.\n" +
                "Use the command /ORDER to choose in which order you want to insert the tiles in your bookshelf.\n\n" +
                "For example: if you have three tiles to order, you could write: /ORDER 2 1 3 or /ORDER 3 2 1\n" +
                "(If you have just one picked tile, just type: /ORDER 1\n\n" +
                "[Use the command /GOALS to see the description of your personal or common goal cards.]\n\n");
        printSelection();
        String command;
        while (true) {
            command = input.nextLine();
            if(command!=null&&Objects.equals(command.toUpperCase(), "/GOALS")){
                showGoals("BookshelfColumn");
                break;
            }
            while(command!=null && !Objects.equals(command.toUpperCase().split(" ")[0], "/ORDER")) {
                System.out.println("Please, use the command /ORDER");
                command = input.nextLine();
            }
            if(command!=null){
                ClientMessage message = (ClientMessage) MoveSerializer.serializeInput(command);
                connectionClient.sendMessage(message);
            }
            command=null;
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException iE) {
                iE.printStackTrace();
            }
            if (response != null && response.getCategory() == Message.MessageCategory.VALID_MESSAGE)
                break;

            if (response != null && response.getCategory() == Message.MessageCategory.WARNING) {
                System.out.println("You didn't choose the order appropriately. Please, retry.");
            }
        }
    }
    private void showBookshelfColumn(){
        Scanner input = new Scanner(System.in);
        System.out.println("YOUR BOOKSHELF\n");
        printBookshelf();
        System.out.print("\n\nCHOOSE THE COLUMN! Choose where you want to inserted the picked and order tiles,\n" +
                "using the command /COLUMN and the coordinate of the column.\n" +
                "For example: if you want to insert the tiles in the second column, you should write /COLUMN 1\n\n" +
                "[Use the command /GOALS to see the description of your personal or common goal cards.]\n");
        String command;
        while(true){
            command = input.nextLine();
            if(command!=null&&Objects.equals(command.toUpperCase(), "/GOALS")){
                showGoals("BookshelfColumn");
                break;
            }
            while(command!=null && !Objects.equals(command.toUpperCase().split(" ")[0], "/COLUMN")) {
                System.out.println("Please, use the command /COLUMN");
                command = input.nextLine();
            }
            if(command!=null){
                ClientMessage message = (ClientMessage) MoveSerializer.serializeInput(command);
                connectionClient.sendMessage(message);
            }
            command=null;
            try{
                TimeUnit.MILLISECONDS.sleep(200);
            }catch (InterruptedException iE){
                iE.printStackTrace();
            }
            if(response!=null && (response.getCategory()==Message.MessageCategory.VALID_MESSAGE || response.getCategory()==Message.MessageCategory.UPDATE_STATE))
                break;

            if(response!=null&&response.getCategory()== Message.MessageCategory.WARNING){
                System.out.println("The chosen column is too full. Please, choose another one.");
            }

        }
        tiles.clear();
        /*while(response!=null && response.getCategory()!=Message.MessageCategory.UPDATE_STATE){
            try{
                TimeUnit.MILLISECONDS.sleep(200);
            }catch (InterruptedException iE){
                iE.printStackTrace();
            }
        }*/
    }

    private void showBookshelf(){
        Scanner input = new Scanner(System.in);
        String command;
        System.out.println("YOUR BOOKSHELF\n");
        printBookshelf();
        System.out.println("\n\n[If you want to come back to the previous screen, use the command /BACK]");
        while (true){
            command = input.nextLine();
            if(Objects.equals(command.toUpperCase(), "/BACK"))
                break;
            System.out.println("Please, use the /BACK command correctly.");
        }
       showBoard();
    }

    private void printSelection(){
        for (ItemTile tile : tiles){
            if(tile!=null) {
                switch (tile.getCategory()) {
                    case CATS -> System.out.print(GREEN + "   " + RESET + "   ");
                    case FRAMES -> System.out.print(BLUE + "   " + RESET + "   ");
                    case BOOKS -> System.out.print(WHITE + "   " + RESET + "   ");
                    case GAMES -> System.out.print(YELLOW + "   " + RESET + "   ");
                    case PLANTS -> System.out.print(PINK + "   " + RESET + "   ");
                    case TROPHIES -> System.out.print(CYAN + "   " + RESET + "   ");
                }
            }
        }
        System.out.print("\n");
        for(int i=0; i< tiles.size(); i++)
            System.out.print("(" + (i+1) + ")   ");
        System.out.print("\n");
    }

    private void printBoard(){
        for (int i=0; i<LivingRoomSize;i++){
            for(int j=0; j<LivingRoomSize; j++){
                if(livingRoom.getBoardTile(i,j).getTile()!=null){
                    switch (livingRoom.getBoardTile(i, j).getTile().getCategory()) {
                        case CATS -> System.out.print(GREEN + "   " + RESET);
                        case FRAMES -> System.out.print(BLUE + "   " + RESET);
                        case BOOKS -> System.out.print(WHITE + "   " + RESET);
                        case GAMES -> System.out.print(YELLOW + "   " + RESET);
                        case PLANTS -> System.out.print(PINK + "   " + RESET);
                        case TROPHIES -> System.out.print(CYAN + "   " + RESET);
                    }
                }else
                    System.out.print("   ");
                if(j==LivingRoomSize-1)
                    System.out.print("  ["+i+"]\n");
            }
        }
        for(int j=0; j<LivingRoomSize;j++)
            System.out.print("["+j+"]");
    }

    private void printBookshelf(){
        for(int i=0; i<=shelfRows;i++)
            System.out.print("\u001b[48;2;140;68;28m   \u001B[0m");
        System.out.print("\n");
        for(int i=0; i<shelfRows; i++){
            System.out.print("  \u001b[48;2;140;68;28m \u001B[0m");
            for(int j=0; j<shelfCols; j++){
                if(player.getPlayerBookshelf().getTile(i,j)==null)
                    System.out.print("   ");
                else{
                    switch (player.getPlayerBookshelf().getTile(i, j).getCategory()) {
                        case CATS -> System.out.print(GREEN + "   " + RESET);
                        case FRAMES -> System.out.print(BLUE + "   " + RESET);
                        case BOOKS -> System.out.print(WHITE + "   " + RESET);
                        case GAMES -> System.out.print(YELLOW + "   " + RESET);
                        case PLANTS -> System.out.print(PINK + "   " + RESET);
                        case TROPHIES -> System.out.print(CYAN + "   " + RESET);
                    }
                }
                if(j==shelfCols-1) {
                    System.out.print("\u001b[48;2;140;68;28m \u001B[0m ");
                    System.out.print("  [" + i + "]\n");
                }
            }
        }
        for(int i=0; i<=shelfRows;i++)
            System.out.print("\u001b[48;2;140;68;28m   \u001B[0m");
        System.out.print("\n");
        System.out.print("   ");
        for(int j=0; j<shelfCols;j++)
            System.out.print("["+j+"]");
    }

    private void printPersonalGoalCard(){
        for(int i=0; i<=shelfRows;i++)
            System.out.print("\u001b[48;2;140;68;28m   \u001B[0m");
        System.out.print("\n");
        for(int i=0; i<shelfRows; i++){
            System.out.print("  \u001b[48;2;140;68;28m \u001B[0m");
            for(int j=0; j<shelfCols; j++){
                if(personalGoalCard.getTile(i,j)==null)
                    System.out.print("   ");
                else{
                    switch (personalGoalCard.getTile(i, j).getCategory()) {
                        case CATS -> System.out.print(GREEN + "   " + RESET);
                        case FRAMES -> System.out.print(BLUE + "   " + RESET);
                        case BOOKS -> System.out.print(WHITE + "   " + RESET);
                        case GAMES -> System.out.print(YELLOW + "   " + RESET);
                        case PLANTS -> System.out.print(PINK + "   " + RESET);
                        case TROPHIES -> System.out.print(CYAN + "   " + RESET);
                    }
                }
                if(j==shelfCols-1) {
                    System.out.print("\u001b[48;2;140;68;28m \u001B[0m \n");
                }
            }
        }
        for(int i=0; i<=shelfRows;i++)
            System.out.print("\u001b[48;2;140;68;28m   \u001B[0m");
        System.out.print("\n");
        System.out.print("   ");
    }

    public void buildPersonalGoalCard(){
        PersonalGoalCard stamp = new PersonalGoalCard(seed);
        for(Pair k: stamp.getGoal().keySet())
           personalGoalCard.setTile(k.getX(),k.getY(),stamp.getGoal().get(k));
    }

    public void setSeed (Integer seed){
        this.seed=seed;
    }

    public Player getPlayer() {
        return player;
    }
}
