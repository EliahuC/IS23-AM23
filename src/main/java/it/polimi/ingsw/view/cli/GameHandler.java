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
    private static final String WOOD = "\u001b[48;2;140;68;28m";

    private final static String CLS = "\u001B[8;46;123t" + "\u001B[2J\u001B[3J\u001B[H";
    public static final int LivingRoomSize=9;
    private static final int shelfRows = 6;
    private static final int shelfCols = 5;
    private String winner;
    private CommonGoalCard commonGoalCard1;
    private CommonGoalCard commonGoalCard2;
    private boolean endgame;

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

    public CommonGoalCard getCommonGoalCard1() {
        return commonGoalCard1;
    }

    public CommonGoalCard getCommonGoalCard2() {
        return commonGoalCard2;
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

    public void setEndgame(boolean endgame) {
        this.endgame = endgame;
    }

    public void start(){
        buildPersonalGoalCard();
        while(true){
            if(endgame)
                break;
            if(player.getNickName().equals(players.get(currPlaying-1).getNickName())) {
                showBoard();
                showBookshelfOrder();
                showBookshelfColumn();
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException iE) {
                    iE.printStackTrace();
                }
                if(endgame)
                    break;
                waiting();
            }else {
                waiting();
            }
        }
        showEnd();
    }

    private void waiting(){
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException iE) {
            iE.printStackTrace();
        }
        System.out.println(CLS);
        System.out.flush();
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t It's NOT your turn!\n\t\t\t\t\t\t\t\t\t\t\t\t   CURRENTLY PLAYING: ");
        if(players.get(currPlaying-1).isFirstPlayerSeat())
            System.out.println(CHAIR +players.get(currPlaying-1).getNickName()+RESET);
        else
            System.out.println(players.get(currPlaying-1).getNickName());
        try{
            TimeUnit.MILLISECONDS.sleep(500);
        }catch (InterruptedException iE){
            iE.printStackTrace();
        }
        while(!Objects.equals(player.getNickName(), players.get(currPlaying - 1).getNickName())){
            if(endgame)
                break;
            /*System.out.print("CLS");
            System.out.flush();
            System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t It's NOT your turn!\n\t\t\t\t\t\t\t\t\t\t\t\t   CURRENTLY PLAYING: ");
            if(players.get(currPlaying-1).isFirstPlayerSeat())
                System.out.println(CHAIR +players.get(currPlaying-1).getNickName()+RESET);
            else
                System.out.println(players.get(currPlaying-1).getNickName());
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException iE){
                iE.printStackTrace();
            }
            if(response!=null && response.getCategory()==Message.MessageCategory.LAST_TURN_MESSAGE)
                System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t" + response.getReturnMessage());*/
            try{
                TimeUnit.MILLISECONDS.sleep(500);
            }catch (InterruptedException iE){
                iE.printStackTrace();
            }
        }
    }

    private void showBoard() {
        System.out.println(CLS);
        System.out.flush();
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t  LIVING BOARD\n");
        printBoard();
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException iE) {
            iE.printStackTrace();
        }
        System.out.println("""
                					PICK YOUR TILES! You can choose one, two or three tiles: use the command /SELECT
                					writing respectively the row's coordinate and the column's coordinate.
                					You must know that you can only pick adjacent tiles that are in the same row or in the same column,
                					plus you can only choose external tiles!
                                
                						For example, to pick the two tiles in (8,5) and in (8,4), the right command is:
                						/SELECT 8 5 8 4
                                
                							[Use the command /BOOKSHELF to see your personal bookshelf.]
                				[Use the command /GOALS to see the description of your personal or common goal cards.]
                """);
        Scanner input = new Scanner(System.in);
        String command;
        ClientMessage message=null;
        response=null;
        select:
        while (true) {
            System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t");
            command = input.nextLine();
            if (command!=null&&Objects.equals(command.toUpperCase(), "/GOALS")) {
                showGoals("Living Board");
                break ;
            }
            if (command!=null&&Objects.equals(command.toUpperCase(), "/BOOKSHELF")) {
                showBookshelf();
                break;
            }
            while(command!=null && !Objects.equals(command.toUpperCase().split(" ")[0], "/SELECT")) {
                System.out.println("\t\t\t\t\t\t\t\t\t\tPlease, use the command /SELECT");
                System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t");
                command = input.nextLine();
                if (command!=null&&Objects.equals(command.toUpperCase(), "/GOALS")) {
                    showGoals("Living Board");
                    break select;
                }
                if (command!=null&&Objects.equals(command.toUpperCase(), "/BOOKSHELF")) {
                    showBookshelf();
                    break select;
                }
            }
            if(command!=null){
                message = (ClientMessage) MoveSerializer.serializeInput(command);
                if(message.getMessageCategory()!=Message.MessageCategory.WARNING)
                    connectionClient.sendMessage(message);
                /*else {
                    System.out.print("Your move is not valid. Please, pick again and correctly your tiles checking their legality and your bookshelf's capability.\n" +
                            "[You can still see your goal cards, using the command /GOALS, or your personal bookshelf using /BOOKSHELF]\n");
                    response=null;
                    try {
                        TimeUnit.SECONDS.sleep(4);
                    } catch (InterruptedException iE) {
                        iE.printStackTrace();
                    }
                    System.out.println(CLS);
                    System.out.flush();
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t  LIVING BOARD\n");
                    printBoard();
                    System.out.println("""
                					PICK YOUR TILES! You can choose one, two or three tiles: use the command /SELECT
                					writing respectively the row's coordinate and the column's coordinate.
                					You must know that you can only pick adjacent tiles that are in the same row or in the same column,
                					plus you can only choose external tiles!
                                
                						For example, to pick the two tiles in (8,5) and in (8,4), the right command is:
                						/SELECT 8 5 8 4
                                
                							[Use the command /BOOKSHELF to see your personal bookshelf.]
                				[Use the command /GOALS to see the description of your personal or common goal cards.]
                """);
                }*/
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
            if ((response != null && response.getCategory() == Message.MessageCategory.WARNING) || Objects.requireNonNull(message).getMessageCategory()==Message.MessageCategory.WARNING) {
                System.out.print("\t\t\t\t\tYour move is not valid. Please, pick again and correctly your tiles checking \n\t\t\t\t\t\t\t\t\ttheir legality and your bookshelf's capability.\n");
                try {
                    TimeUnit.SECONDS.sleep(4);
                } catch (InterruptedException iE) {
                    iE.printStackTrace();
                }
                System.out.println(CLS);
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t  LIVING BOARD\n");
                printBoard();
                System.out.println("""
                					PICK YOUR TILES! You can choose one, two or three tiles: use the command /SELECT
                					writing respectively the row's coordinate and the column's coordinate.
                					You must know that you can only pick adjacent tiles that are in the same row or in the same column,
                					plus you can only choose external tiles!
                                
                						For example, to pick the two tiles in (8,5) and in (8,4), the right command is:
                						/SELECT 8 5 8 4
                                
                							[Use the command /BOOKSHELF to see your personal bookshelf.]
                				[Use the command /GOALS to see the description of your personal or common goal cards.]
                """);
            }
        }
    }
    private void showGoals(String scenario){
        System.out.println(CLS);
        System.out.flush();
        Scanner input = new Scanner(System.in);
        String command;
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\tYOUR PERSONAL GOAL CARD\n");
        printPersonalGoalCard();
        System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t   COMMON GOAL CARDS\n");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t(1): ");
        commonGoalCard1.print();
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t(2): ");
        commonGoalCard2.print();
        System.out.println("\n\n\t\t\t\t\t\t\t[If you want to come back to the previous screen, use the command /BACK]");
        while (true){
            System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
            command = input.nextLine();
            if(Objects.equals(command.toUpperCase(), "/BACK"))
                break;
            System.out.println("\t\t\t\t\t\t\t\t\tPlease, use the /BACK command correctly.");
        }
        switch (scenario) {
            case "Living Board" -> showBoard();
            case "BookshelfOrder" -> showBookshelfOrder();
            case "BookshelfColumn" -> showBookshelfColumn();
        }

    }

    private void showEnd(){
        System.out.println(CLS);
        System.out.flush();
        List<Player> ranking = players.stream().sorted(Comparator.comparingInt(Player::getScore)).toList();
        System.out.println("""
                																															
                                                       __  ___          _____  __           __ ____ _                                      \s
                                                      /  |/  /__  __   / ___/ / /_   ___   / // __/(_)___                                  \s
                                                     / /|_/ // / / /   \\__ \\ / __ \\ / _ \\ / // /_ / // _ \\                                 \s
                                                    / /  / // /_/ /   ___/ // / / //  __// // __// //  __/                                 \s
                                                   /_/  /_/ \\__, /   /____//_/ /_/ \\___//_//_/  /_/ \\___/                                  \s
                                                           /____/                                                                          \s
                                                                                                                                           \s
                """);
        for (Player p : ranking) {
            if (p.isWinner()) {
                System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\tTHE WINNER IS: " + p.getNickName() + " (" + p.getScore() + " points)");
                break;
            }

        }
        for (Player p : ranking) {
            if (!p.isWinner())
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t" + p.getNickName() + ": " + p.getScore() + " points");
        }

        System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t\t[Close the window to play again.]");
    }

    private void showBookshelfOrder() {
        System.out.println(CLS);
        System.out.flush();
        Scanner input = new Scanner(System.in);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\tYOUR BOOKSHELF\n");
        printBookshelf();
        System.out.println("""
                					   
                					   
                					   ORDER YOUR TILES! The tiles you picked before from the board are shown above.							
                					   Use the command /ORDER to choose in which order you want to insert the tiles in your bookshelf.
                                
                							For example: if you have three tiles to order, you could write: /ORDER 2 1 3 or /ORDER 3 2 1
                							(If you have just one picked tile, just type: /ORDER 1
                                
                						[Use the command /GOALS to see the description of your personal or common goal cards.]
                """);
        printSelection();
        String command;
        ClientMessage message = null;
        response=null;
        order:
        while (true) {
            System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
            command = input.nextLine();
            if(command!=null&&Objects.equals(command.toUpperCase(), "/GOALS")){
                showGoals("BookshelfOrder");
                break;
            }
            while(command!=null && !Objects.equals(command.toUpperCase().split(" ")[0], "/ORDER")) {
                System.out.println("\t\t\t\t\t\t\t\t\t\t\tPlease, use the command /ORDER");
                System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                command = input.nextLine();
                if(command!=null&&Objects.equals(command.toUpperCase(), "/GOALS")){
                    showGoals("BookshelfOrder");
                    break order;
                }
            }
            if(command!=null){
                message = (ClientMessage) MoveSerializer.serializeInput(command);
                if(message.getMessageCategory()!=Message.MessageCategory.WARNING)
                    connectionClient.sendMessage(message);
                /*else
                    System.out.println("You didn't choose the order appropriately. Please, retry.");*/
            }
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException iE) {
                iE.printStackTrace();
            }
            if (response != null && response.getCategory() == Message.MessageCategory.VALID_MESSAGE)
                break;

            if ((response != null && response.getCategory() == Message.MessageCategory.WARNING) || Objects.requireNonNull(message).getMessageCategory()==Message.MessageCategory.WARNING) {
                System.out.println("\t\t\t\t\t\t\t\t\t\tYou didn't choose the order appropriately. Please, retry.");
                try {
                    TimeUnit.SECONDS.sleep(4);
                } catch (InterruptedException iE) {
                    iE.printStackTrace();
                }
                System.out.println(CLS);
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\tYOUR BOOKSHELF\n");
                printBookshelf();
                System.out.println("""
                					   
                					   
                					   ORDER YOUR TILES! The tiles you picked before from the board are shown above.						\t
                					   Use the command /ORDER to choose in which order you want to insert the tiles in your bookshelf.
                                
                							For example: if you have three tiles to order, you could write: /ORDER 2 1 3 or /ORDER 3 2 1
                							(If you have just one picked tile, just type: /ORDER 1
                                
                						[Use the command /GOALS to see the description of your personal or common goal cards.]
                """);
                printSelection();
            }
        }
    }
    private void showBookshelfColumn(){
        System.out.println(CLS);
        System.out.flush();
        Scanner input = new Scanner(System.in);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\tYOUR BOOKSHELF\n");
        printBookshelf();
        System.out.println("""
                					   
                					   
                					   CHOOSE THE COLUMN! Choose where you want to inserted the picked and order tiles,
                					   using the command /COLUMN and the coordinate of the column.
                					  \s
                							For example: if you want to insert the tiles in the second column, you should write /COLUMN 1
                                
                						[Use the command /GOALS to see the description of your personal or common goal cards.]
                """);
        String command;
        response=null;
        column:
        while(true){
            System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
            command = input.nextLine();
            if(command!=null&&Objects.equals(command.toUpperCase(), "/GOALS")){
                showGoals("BookshelfColumn");
                break;
            }
            while(command!=null && !Objects.equals(command.toUpperCase().split(" ")[0], "/COLUMN")) {
                System.out.println("\t\t\t\t\t\t\t\t\t\t\tPlease, use the command /COLUMN");
                System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                command = input.nextLine();
                if(command!=null&&Objects.equals(command.toUpperCase(), "/GOALS")){
                    showGoals("BookshelfColumn");
                    command=null;
                    break column;
                }
            }
            if(command!=null){
                ClientMessage message = (ClientMessage) MoveSerializer.serializeInput(command);
                if(message.getMessageCategory()!=Message.MessageCategory.WARNING)
                    connectionClient.sendMessage(message);
                /*else
                    System.out.println("Choose correctly the column: you must select 0, 1, 2, 3 or 4!");*/
            }
            try{
                TimeUnit.MILLISECONDS.sleep(200);
            }catch (InterruptedException iE){
                iE.printStackTrace();
            }
            if(response!=null && (response.getCategory()!= Message.MessageCategory.WARNING))
                break;

            if(response!=null&&response.getCategory()== Message.MessageCategory.WARNING){
                System.out.println("\t\t\t\t\t\t\t\t\t\tThe chosen column is too full. Please, choose another one.");
                try {
                    TimeUnit.SECONDS.sleep(4);
                } catch (InterruptedException iE) {
                    iE.printStackTrace();
                }
                System.out.println(CLS);
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\tYOUR BOOKSHELF\n");
                printBookshelf();
                System.out.println("""
                					   CHOOSE THE COLUMN! Choose where you want to inserted the picked and order tiles,
                					   using the command /COLUMN and the coordinate of the column.
                					  \s
                							For example: if you want to insert the tiles in the second column, you should write /COLUMN 1
                                
                						[Use the command /GOALS to see the description of your personal or common goal cards.]
                """);
            }

        }
        tiles.clear();
    }

    private void showBookshelf(){
        Scanner input = new Scanner(System.in);
        String command;
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\tYOUR BOOKSHELF\n");
        printBookshelf();
        System.out.println("\n\n\t\t\t\t\t\t\t[If you want to come back to the previous screen, use the command /BACK]");
        while (true){
            System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
            command = input.nextLine();
            if(Objects.equals(command.toUpperCase(), "/BACK"))
                break;
            System.out.println("\t\t\t\t\t\t\t\t\t\t\tPlease, use the /BACK command correctly.");
        }
       showBoard();
    }

    private void printSelection(){
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
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
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        for(int i=0; i< tiles.size(); i++)
            System.out.print("(" + (i+1) + ")   ");
        System.out.print("\n");
    }

    private void printBoard(){
        for (int i=0; i<LivingRoomSize;i++){
            System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
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
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        for(int j=0; j<LivingRoomSize;j++)
            System.out.print("["+j+"]");
        System.out.print("\n\n");
    }

    private void printBookshelf(){
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t");
        for(int i=0; i<=shelfRows;i++)
            System.out.print(WOOD + "   "+ RESET);
        System.out.print("\n");
        for(int i=0; i<shelfRows; i++){
            System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t  " + WOOD + " "+ RESET);
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
                if(j==shelfCols-1)
                    System.out.println(WOOD + " "+ RESET);
            }
        }

        System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t");
        for(int i=0; i<=shelfRows;i++)
            System.out.print(WOOD + "   "+ RESET);
        System.out.print("\n");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t   ");
        for(int j=0; j<shelfCols;j++)
            System.out.print("["+j+"]");
    }

    private void printPersonalGoalCard(){
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t");
        for(int i=0; i<=shelfRows;i++)
            System.out.print(WOOD + "   "+ RESET);
        System.out.print("\n");
        for(int i=0; i<shelfRows; i++){
            System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t  " + WOOD + " "+ RESET);
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
                    System.out.println(WOOD + " "+ RESET);
                }
            }
        }
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t");
        for(int i=0; i<=shelfRows;i++)
            System.out.print(WOOD + "   "+ RESET);
        System.out.print("\n");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t   ");
        for(int j=0; j<shelfCols;j++)
            System.out.print("["+j+"]");
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
