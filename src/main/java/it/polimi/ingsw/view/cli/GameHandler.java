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

/**
 * @author Simone Controguerra and Eliahu Cohen
 * CLI game page
 */
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

    private final static String CLS = "\u001B[2J\u001B[3J\u001B[H";
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

    /**
     * @author Eliahu Cohen
     * @param idCGC2 id of the common goal card 2
     * Method that generate the common goal card 2 of the game
     */
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

    /**
     * @author Eliahu Cohen
     * @param idCGC1 id of the common goal card 1
     * Method that generate the common goal card 1 of the game
     */
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

    /**
     * @author Simone Controguerra
     * Game page
     */
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

    /**
     * @author Simone Controguerra
     * Method that put you in waiting if it's not your turn
     */
    private void waiting(){
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException iE) {
            iE.printStackTrace();
        }
        System.out.println(CLS);
        System.out.flush();
        System.out.print("\n\n                                      It's NOT your turn!\n\n                                    CURRENTLY PLAYING: ");
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
            System.out.print(CLS); //TODO abilitare durante la creazione del JAR
            //System.out.flush();
            System.out.print("\n\n                                      It's NOT your turn!\n\n                                    CURRENTLY PLAYING: ");
            if(players.get(currPlaying-1).isFirstPlayerSeat())
                System.out.println(CHAIR +players.get(currPlaying-1).getNickName()+RESET);
            else
                System.out.println(players.get(currPlaying-1).getNickName());
            //*/
            try{
                TimeUnit.MILLISECONDS.sleep(500);
            }catch (InterruptedException iE){
                iE.printStackTrace();
            }
        }
    }

    /**
     * @author Simone Controguerra
     * Method to show the board and get the selection of the player
     */
    private void showBoard() {
        System.out.println(CLS);
        System.out.flush();
        System.out.println("\n                          LIVING BOARD\n");
        printBoard();
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException iE) {
            iE.printStackTrace();
        }
        System.out.println("""
                
                [RULES] You can only pick 1, 2 or 3 adjacent tiles from
                the board, just make sure they have at least one free side.
                
                [COMMAND] Use respectively the row and the column's coordinates!
                /SELECT <row#1> <column#1> <row#2> <column#2> <row#3> <column#3>
                
                [BOOKSHELF] -> /BOOKSHELF (So that you can check its capability)
                [GOALS] -> /GOALS (So that you can know how to score some points)
                
                """);
        Scanner input = new Scanner(System.in);
        String command;
        ClientMessage message=null;
        response=null;
        select:
        while (true) {
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
                System.out.println("                   Please, use the command /SELECT");
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
            }

            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException iE) {
                iE.printStackTrace();
            }
            if (response != null && response.getCategory() == Message.MessageCategory.VALID_MESSAGE) {
                for (int i = 1; i < Arrays.stream(Objects.requireNonNull(command).split(" ")).count(); i += 2)
                    tiles.add(livingRoom.getBoardTile(Integer.parseInt(command.split(" ")[i]), Integer.parseInt(command.split(" ")[i + 1])).getTile());
                break;
            }
            if ((response != null && response.getCategory() == Message.MessageCategory.WARNING) || Objects.requireNonNull(message).getMessageCategory()==Message.MessageCategory.WARNING) {
                System.out.println("""
                 
                 > Your move is not valid. Pick the tiles again correctly.
                   Also check your bookshelf's capability!
                """);
                try {
                    TimeUnit.MILLISECONDS.sleep(2500);
                } catch (InterruptedException iE) {
                    iE.printStackTrace();
                }
                System.out.println(CLS);
                System.out.flush();
                System.out.println("\n                          LIVING BOARD\n");
                printBoard();
                System.out.println("""
                
                [RULES] You can only pick 1, 2 or 3 adjacent tiles from
                the board, just make sure they have at least one free side.
                
                [COMMAND] Use respectively the row and the column's coordinates!
                /SELECT <row#1> <column#1> <row#2> <column#2> <row#3> <column#3>
                
                [BOOKSHELF] -> /BOOKSHELF (So that you can check its capability)
                [GOALS] -> /GOALS (So that you can know how to score some points)
                
                """);
            }
        }
    }

    /**
     * @author Simone Controguerra
     * @param scenario scenario the player is in when he calls the goals command
     * Method to show the Goals
     */
    private void showGoals(String scenario){
        System.out.println(CLS);
        System.out.flush();
        Scanner input = new Scanner(System.in);
        System.out.println(" > YOUR PERSONAL GOAL CARD <\n");
        printPersonalGoalCard();
        System.out.println("\n\n > COMMON GOAL CARDS <\n");
        System.out.print("    [1]: ");
        commonGoalCard1.print();
        System.out.print("\n    [2]: ");
        commonGoalCard2.print();
        System.out.print(" > /BACK? ");
        input.nextLine();
        switch (scenario) {
            case "Living Board" -> showBoard();
            case "BookshelfOrder" -> showBookshelfOrder();
            case "BookshelfColumn" -> showBookshelfColumn();
        }
    }

    /**
     * @author Simone Controguerra
     * Method that display the ranking of the game
     */
    private void showEnd(){
        System.out.println(CLS);
        System.out.flush();
        List<Player> ranking = players.stream().sorted(Comparator.comparingInt(Player::getScore)).toList();
        System.out.println("""
                                                							
                                                        					
                                        															
                                                       __  ___          _____  __           __ ____ _
                                                      /  |/  /__  __   / ___/ / /_   ___   / // __/(_)___
                                                     / /|_/ // / / /   \\__ \\ / __ \\ / _ \\ / // /_ / // _ \\
                                                    / /  / // /_/ /   ___/ // / / //  __// // __// //  __/
                                                   /_/  /_/ \\__, /   /____//_/ /_/ \\___//_//_/  /_/ \\___/
                                                           /____/
                                """);
        for (Player p : ranking) {
            if (p.isWinner()) {
                System.out.println("\n                      THE WINNER IS: " + p.getNickName() + " (" + p.getScore() + " points)\n");
                break;
            }

        }
        for (Player p : ranking) {
            if (!p.isWinner())
                System.out.println("                                     " + p.getNickName() + ": " + p.getScore() + " points");
        }

        System.out.println("\n\n                            > Close the window to play again <");
    }

    /**
     * @author Simone Controguerra
     * Method that gets the order command of the player
     */
    private void showBookshelfOrder() {
        System.out.println(CLS);
        System.out.flush();
        Scanner input = new Scanner(System.in);
        if(tiles.size()!=1) {
            System.out.println("\n                       YOUR BOOKSHELF\n");
            printBookshelf();
            System.out.println("""
                                        
                    [ORDER] Just decide the insertion order of tiles in your bookshelf!
                                        
                    [COMMAND] The first will be the first to be inserted.
                    /ORDER <#1> <#2> <#3>
                    
                    [GOALS] -> /GOALS (So that you can know how to score some points)
                    
                    """);
            printSelection();
            String command;
            ClientMessage message = null;
            response = null;
            order:
            while (true) {
                command = input.nextLine();
                if (command != null && Objects.equals(command.toUpperCase(), "/GOALS")) {
                    showGoals("BookshelfOrder");
                    break;
                }
                while (command != null && !Objects.equals(command.toUpperCase().split(" ")[0], "/ORDER")) {
                    System.out.println("                   Please, use the command /ORDER");
                    command = input.nextLine();
                    if (command != null && Objects.equals(command.toUpperCase(), "/GOALS")) {
                        showGoals("BookshelfOrder");
                        break order;
                    }
                }
                if (command != null) {
                    message = (ClientMessage) MoveSerializer.serializeInput(command);
                    if (message.getMessageCategory() != Message.MessageCategory.WARNING)
                        connectionClient.sendMessage(message);
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException iE) {
                    iE.printStackTrace();
                }
                if (response != null && response.getCategory() == Message.MessageCategory.VALID_MESSAGE)
                    break;

                if ((response != null && response.getCategory() == Message.MessageCategory.WARNING) || Objects.requireNonNull(message).getMessageCategory() == Message.MessageCategory.WARNING) {
                    System.out.println("""
                             
                             > You didn't choose the order appropriately!
                             
                            """);
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException iE) {
                        iE.printStackTrace();
                    }
                    System.out.println(CLS);
                    System.out.flush();
                    System.out.println("\n                       YOUR BOOKSHELF\n");
                    printBookshelf();
                    System.out.println("""
                                        
                    [ORDER] Just decide the insertion order of tiles in your bookshelf!
                                        
                    [COMMAND] The first will be the first to be inserted.
                    /ORDER <#1> <#2> <#3>
                    
                    [GOALS] -> /GOALS (So that you can know how to score some points)
                    
                    """);
                    printSelection();
                }
            }
        }else{
            connectionClient.sendMessage((ClientMessage) MoveSerializer.serializeInput("/ORDER 1"));
        }
    }

    /**
     * @author Simone Controguerra
     * Method that gets the column command of the player
     */
    private void showBookshelfColumn(){
        System.out.println(CLS);
        System.out.flush();
        Scanner input = new Scanner(System.in);
        System.out.println("                       YOUR BOOKSHELF\n");
        printBookshelf();
        System.out.println("""
                
                [INSERT] Choose the column where to insert the tiles.
                Just check to pick a column with some free space!
                
                [COMMAND] /COLUMN <#column>
                
                [GOALS] -> /GOALS (So that you can know how to score some points)
                
                """);
        String command;
        response=null;
        column:
        while(true){
            command = input.nextLine();
            if(command!=null&&Objects.equals(command.toUpperCase(), "/GOALS")){
                showGoals("BookshelfColumn");
                break;
            }
            while(command!=null && !Objects.equals(command.toUpperCase().split(" ")[0], "/COLUMN")) {
                System.out.println("                   Please, use the command /COLUMN");
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
            }
            try{
                TimeUnit.MILLISECONDS.sleep(200);
            }catch (InterruptedException iE){
                iE.printStackTrace();
            }
            if(response!=null && (response.getCategory()!= Message.MessageCategory.WARNING))
                break;

            if(response!=null&&response.getCategory()== Message.MessageCategory.WARNING){
                System.out.println("""
                        
                         > That column is too full!
                        
                        """);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException iE) {
                    iE.printStackTrace();
                }
                System.out.println(CLS);
                System.out.flush();
                System.out.println("                       YOUR BOOKSHELF\n");
                printBookshelf();
                System.out.println("""
                
                
                [INSERT] Choose the column where to insert the tiles.
                Just check to pick a column with some free space!
                
                [COMMAND] /COLUMN <#column>
                
                [GOALS] -> /GOALS (So that you can know how to score some points)
                
                """);
            }

        }
        tiles.clear();
    }

    /**
     * @author Simone Controguerra
     * Method that prints the player bookshelf
     */
    private void showBookshelf(){
        System.out.println(CLS);
        Scanner input = new Scanner(System.in);
        System.out.println("\n                       YOUR BOOKSHELF\n");
        printBookshelf();
        System.out.print("\n\n > /BACK? ");
        input.nextLine();
        showBoard();
    }

    /**
     * @author Simone Controguerra
     * Method that prints the selection of the player
     */
    private void printSelection(){
        System.out.print("\n                         ");
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
        System.out.print("\n                         ");
        for(int i=0; i< tiles.size(); i++)
            System.out.print("(" + (i+1) + ")   ");
        System.out.print("\n");
    }

    private void printBoard(){
        for (int i=0; i<LivingRoomSize;i++){
            System.out.print("                   ");
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
        System.out.print("                   ");
        for(int j=0; j<LivingRoomSize;j++)
            System.out.print("["+j+"]");
        System.out.print("\n\n");
    }

    private void printBookshelf(){
        System.out.print("                   ");
        for(int i=0; i<=shelfRows;i++)
            System.out.print(WOOD + "   "+ RESET);
        System.out.print("\n");
        for(int i=0; i<shelfRows; i++){
            System.out.print("                     " + WOOD + " "+ RESET);
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

        System.out.print("                   ");
        for(int i=0; i<=shelfRows;i++)
            System.out.print(WOOD + "   "+ RESET);
        System.out.print("\n");
        System.out.print("                      ");
        for(int j=0; j<shelfCols;j++)
            System.out.print("["+j+"]");
        System.out.print("\n");
    }

    private void printPersonalGoalCard(){
        System.out.print("                   ");
        for(int i=0; i<=shelfRows;i++)
            System.out.print(WOOD + "   "+ RESET);
        System.out.print("\n");
        for(int i=0; i<shelfRows; i++){
            System.out.print("                     " + WOOD + " "+ RESET);
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
        System.out.print("                   ");
        for(int i=0; i<=shelfRows;i++)
            System.out.print(WOOD + "   "+ RESET);
        System.out.print("\n");
        System.out.print("                      ");
        for(int j=0; j<shelfCols;j++)
            System.out.print("["+j+"]");
    }

    /**
     * @author Simone Controguerra
     * Method that builds the personal goal card
     */
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
