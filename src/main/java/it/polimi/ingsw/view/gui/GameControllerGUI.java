package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.MoveSerializer;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Network.Client.ConnectionClient;
import it.polimi.ingsw.model.board.BoardToken;
import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.board.LivingRoom;
import it.polimi.ingsw.model.board.goalCards.*;
import it.polimi.ingsw.model.player.BookShelf;
import it.polimi.ingsw.model.player.Player;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class GameControllerGUI {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private ConnectionClient connectionClient;
    private GUIEvent receiver;
    private BookShelf bookshelf = new BookShelf();
    private ServerMessage response;
    private LivingRoom livingRoom;
    private Player player;
    private List<Player> players;
    private List<ItemTile> tiles = new ArrayList<>();
    private int currPlaying = 1;
    private String currentPlayer;
    private String winner;
    private Integer seed;
    private Image image;
    private int currPlayer = 1;
    public static final int LivingRoomSize = 9;
    private static final int shelfRows = 6;
    private static final int shelfCols = 5;
    private boolean endgame;
    private CommonGoalCard commonGoalCard1;
    private CommonGoalCard commonGoalCard2;
    @FXML
    AnchorPane myAnchorPane;
    @FXML
    GridPane myGridPane_lr;
    @FXML
    GridPane bsImage;
    @FXML
    GridPane myGridPane_columns;
    @FXML
    GridPane myGridPane_container;
    @FXML
    GridPane myGridPane_bs;
    private ImageView[][] imageViews = new ImageView[LivingRoomSize][LivingRoomSize];
    private static GameControllerGUI currentIstance = new GameControllerGUI();
    private Boolean flag = true;

    public void displayScene() throws IOException {
        if (instance()) {
            GameControllerGUI.currentIstance = this;
            flag = false;
        }
        receiver.setGamecontrollerGUI(getCurrentIstance());
        myGridPane_lr = new GridPane();
        for (int i = 0; i < LivingRoomSize; i++) {
            for (int j = 0; j < LivingRoomSize; j++) {
                if (livingRoom.getBoardTile(i, j).getCategory() != BoardToken.boardTokenCategory.UNAVAILABLE) {
                    ItemTile tile = livingRoom.getBoardTile(i, j).getTile();
                    ImageView imageView = new ImageView();
                    imageView.setImage(chooseCategoryImage(tile));
                    imageViews[i][j] = imageView;
                    imageView.setFitWidth(34);
                    imageView.setFitHeight(34);
                    myGridPane_lr.setLayoutX(29);
                    myGridPane_lr.setLayoutY(61);

                    // Opzionale: Imposta il padding o altre proprietà per gli ImageView
                    GridPane.setMargin(imageView, new Insets(0));
                    myGridPane_lr.add(imageView, j, i);
                }
            }
        }

        bookshelf = player.getPlayerBookshelf();
        myGridPane_bs = new GridPane();
        for (int i = 0; i < shelfRows; i++) {
            for (int j = 0; j < shelfCols; j++) {
                ItemTile tile = bookshelf.getTile(i, j);
                ImageView imageView = new ImageView();
                imageView.setImage(chooseCategoryImage(tile));
                //File file = new File("/com/example/is23am23/Cornici.png");
               // image = new Image(String.valueOf(file));
                //imageView.setImage(image);

                    //imageView.setImage(chooseCategoryImage(tile));
                    imageView.setFitWidth(33);
                    imageView.setFitHeight(33);
                    myGridPane_bs.setLayoutX(386);
                    myGridPane_bs.setLayoutY(158);

                    // Opzionale: Imposta il padding o altre proprietà per gli ImageView
                    GridPane.setMargin(imageView, new Insets(0, 2, 0, 2));
                    myGridPane_bs.add(imageView, j, i);
                    //}
                }
            }

        bsImage = new GridPane();
        ImageView view = new ImageView();
        File file_bs = new File("/com/example/is23am23/bookshelf.png");
        image = new Image(String.valueOf(file_bs));
        view.setImage(image);
        view.setFitWidth(240);
        view.setFitHeight(240);
        bsImage.setLayoutX(360);
        bsImage.setLayoutY(146);
        bsImage.add(view, 0, 0);


        myGridPane_columns = new GridPane();
        for (int i = 0; i < 5; i++) {
            ImageView imageView = new ImageView();

            //IMMAGINE FRECCIA DA INSERIRE
            File file = new File("/com/example/is23am23/arrow_image.png");
            image = new Image(String.valueOf(file));
            imageView.setImage(image);
            imageView.setFitWidth(33);
            imageView.setFitHeight(33);
            myGridPane_columns.setLayoutX(385);
            myGridPane_columns.setLayoutY(105);
            myGridPane_columns.add(imageView, i, 0);
            GridPane.setMargin(imageView, new Insets(2));
        }


            //MANCA IL RIFERIMENTO ALLE TESSERE GIA' PRESE(PER ORA IMMAGINI IMPOSTATE)
            myGridPane_container = new GridPane();
            if(getCurrentIstance().getTiles().size()>0){
            for (int i = 0; i < 3; i++) {
                ImageView imageView = new ImageView();
                //File file = new File("/com/example/is23am23/Cornici.png");
                //image = new Image(String.valueOf(file));
                imageView.setImage(chooseCategoryImage(getCurrentIstance().getTiles().get(i)));
                imageView.setFitWidth(30);
                imageView.setFitHeight(30);
                myGridPane_container.setLayoutX(384);
                myGridPane_container.setLayoutY(66);
                myGridPane_container.add(imageView, i, 0);
            }
        }

        File file = new File("src/main/resources/com/example/is23am23/game.fxml");
        URL url = file.toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        myAnchorPane = loader.load();
        myAnchorPane.getChildren().add(myGridPane_lr);
        myAnchorPane.getChildren().add(myGridPane_bs);
        myAnchorPane.getChildren().add(myGridPane_container);
        myAnchorPane.getChildren().add(bsImage);
        myAnchorPane.getChildren().add(myGridPane_columns);
        Scene scene = new Scene(myAnchorPane);
        stage.setScene(scene);
        Image icon = new Image("com/example/is23am23/little_icon.png");
        stage.getIcons().add(icon);
        stage.setTitle("My Shelfie");
        stage.show();

    }

    public Image chooseCategoryImage(ItemTile tile) {

        image = null;
        if (tile != null) {
            switch (tile.getCategory()) {
                case GAMES: {
                    File file = new File("/com/example/is23am23/Giochi.png");
                    image = new Image(String.valueOf(file));
                    break;
                }
                case CATS: {
                    File file = new File("/com/example/is23am23/Gatti.png");
                    image = new Image(String.valueOf(file));
                    break;
                }
                case BOOKS: {
                    File file = new File("/com/example/is23am23/Libri.png");
                    image = new Image(String.valueOf(file));
                    break;
                }
                case FRAMES: {
                    File file = new File("/com/example/is23am23/Cornici.png");
                    image = new Image(String.valueOf(file));
                    break;
                }
                case PLANTS: {
                    File file = new File("/com/example/is23am23/Piante.png");
                    image = new Image(String.valueOf(file));
                    break;
                }
                case TROPHIES: {
                    File file = new File("/com/example/is23am23/Trofei.png");
                    image = new Image(String.valueOf(file));
                    break;
                }
            }
        }
        return image;
    }

    public void goToGoals(ActionEvent event) throws IOException {

        File file = new File("src/main/resources/com/example/is23am23/goals.fxml");
        URL url = file.toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        GoalsController goalsController = loader.getController();
        if (goalsController.getGameControllerGUI() == null) {
            goalsController.setGameControllerGUI(getCurrentIstance());
        }
        goalsController.displayPersonalGoal();
        goalsController.displayCommonGoal();
        goalsController.displayPoints();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void returnToMenu(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Return to menu");
        alert.setHeaderText("You are about to return to the menu");
        alert.setContentText("Are you sure?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            File file = new File("src/main/resources/com/example/is23am23/menu.fxml");
            URL url = file.toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            currentIstance.getConnectionClient().closeConnection();
        }
    }

    public void setResponse(ServerMessage response) {
        this.response = response;
    }

    public void setLivingRoom(LivingRoom livingRoom) {

        this.livingRoom = livingRoom;
        createCommonGoalCard1(livingRoom.getIdCGC1());
        createCommonGoalCard2(livingRoom.getIdCGC2());
    }

    private void createCommonGoalCard1(Integer idCGC1) {
        switch (idCGC1) {
            case 1 -> this.commonGoalCard1 = new CommonGoalCard1(players.size());
            case 2 -> this.commonGoalCard1 = new CommonGoalCard2(players.size());
            case 3 -> this.commonGoalCard1 = new CommonGoalCard3(players.size());
            case 4 -> this.commonGoalCard1 = new CommonGoalCard4(players.size());
            case 5 -> this.commonGoalCard1 = new CommonGoalCard5(players.size());
            case 6 -> this.commonGoalCard1 = new CommonGoalCard6(players.size());
            case 7 -> this.commonGoalCard1 = new CommonGoalCard7(players.size());
            case 8 -> this.commonGoalCard1 = new CommonGoalCard8(players.size());
            case 9 -> this.commonGoalCard1 = new CommonGoalCard9(players.size());
            case 10 -> this.commonGoalCard1 = new CommonGoalCard10(players.size());
            case 11 -> this.commonGoalCard1 = new CommonGoalCard11(players.size());
            case 12 -> this.commonGoalCard1 = new CommonGoalCard12(players.size());
        }
    }

    private void createCommonGoalCard2(Integer idCGC2) {
        switch (idCGC2) {
            case 1 -> this.commonGoalCard2 = new CommonGoalCard1(players.size());
            case 2 -> this.commonGoalCard2 = new CommonGoalCard2(players.size());
            case 3 -> this.commonGoalCard2 = new CommonGoalCard3(players.size());
            case 4 -> this.commonGoalCard2 = new CommonGoalCard4(players.size());
            case 5 -> this.commonGoalCard2 = new CommonGoalCard5(players.size());
            case 6 -> this.commonGoalCard2 = new CommonGoalCard6(players.size());
            case 7 -> this.commonGoalCard2 = new CommonGoalCard7(players.size());
            case 8 -> this.commonGoalCard2 = new CommonGoalCard8(players.size());
            case 9 -> this.commonGoalCard2 = new CommonGoalCard9(players.size());
            case 10 -> this.commonGoalCard2 = new CommonGoalCard10(players.size());
            case 11 -> this.commonGoalCard2 = new CommonGoalCard11(players.size());
            case 12 -> this.commonGoalCard2 = new CommonGoalCard12(players.size());

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
/*
    public void start(){
        while(true){
            if(player.getNickName()==currentPlayer || player.getNickName()==players.get(currPlaying).getNickName()) {
                displayBoard();
                displayBookshelfOrder();
                displayBookshelfColumn();
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
        displayEnd();
    }*/

    private void waiting() {
        System.out.print("It's not your turn, yet. Wait for other players to finish their turn.\n\n");
        System.out.print("CURRENT PLAYING: ");
        if (players.get(currPlaying).isFirstPlayerSeat())
            System.out.print(players.get(currPlaying).getNickName() + " SEAT MASTER\n");
        else
            System.out.print(players.get(currPlaying).getNickName() + "\n");
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException iE) {
            iE.printStackTrace();
        }
        if (response != null && response.getCategory() == Message.MessageCategory.LAST_TURN_MESSAGE)
            System.out.print(response.getReturnMessage());
        while (!player.getNowPlaying()) {
        }
    }

    public LivingRoom getLivingRoom() {
        return livingRoom;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Player> getPlayersList() {
        return players;
    }

    private void displayBoard() {
        System.out.print("LIVING BOARD\n");
        //livingRoom.print();
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
        while (true) {
            String command = input.nextLine();
            if (Objects.equals(command.toUpperCase(), "/GOALS")) {
                //displayGoals("Living Board");
                break;
            }
            if (Objects.equals(command.toUpperCase(), "/BOOKSHELF")) {
                //displayBookshelf();
                break;
            }
            ClientMessage message = (ClientMessage) MoveSerializer.serializeInput(command);
            connectionClient.sendMessage((ClientMessage) message);
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException iE) {
                iE.printStackTrace();
            }
            if (response != null && response.getCategory() == Message.MessageCategory.VALID_MESSAGE) {
                for (int i = 1; command.split(" ")[i] != null; i += 2)
                    tiles.add(livingRoom.getBoardTile(i, i + 1).getTile());
                break;
            }
            System.out.print("Your move is not valid. Please, pick again and correctly your tiles.\n" +
                    "[You can still see your goal cards, using the command /GOALS, or your personal bookshelf using /BOOKSHELF]\n");
        }
    }

    private void displayEnd() {
        System.out.print("THE WINNER IS: " + winner + "\n");
        List<Player> ranking = players.stream().sorted(Comparator.comparingInt(Player::getScore)).toList();
        for (Player p : ranking) {
            if (p.getNickName() != winner) {
                System.out.print(p.getNickName() + ": " + p.getScore() + "points\n");
            }
        }
    }

    private void displayBookshelfOrder() {
        Scanner input = new Scanner(System.in);
        System.out.print("YOUR BOOKSHELF\n");
        //player.getPlayerBookshelf().print();
        System.out.print("ORDER YOUR TILES! The tiles you picked before from the board are shown above.\n" +
                "Use the command /ORDER to choose in which order you want to insert the tiles in your bookshelf.\n\n" +
                "For example: if you have three tiles to order, you could write: /ORDER 2 1 3 or /ORDER 3 2 1\n" +
                "(If you have just one picked tile, just type: /ORDER 1\n\n" +
                "[Use the command /GOALS to see the description of your personal or common goal cards.]\n");
        printSelection();
        while (true) {
            String command = input.nextLine();
            if (Objects.equals(command.toUpperCase(), "/GOALS")) {
                //displayGoals("BookshelfOrder");
                break;
            }
            Message message = MoveSerializer.serializeInput(command);
            connectionClient.sendMessage((ClientMessage) message);
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException iE) {
                iE.printStackTrace();
            }
            if (response != null && response.getCategory() == Message.MessageCategory.VALID_MESSAGE)
                break;
            System.out.print("You didn't choose the order appropriately. Please, retry.\n");
        }
    }

    private void displayBookshelfColumn() {
        Scanner input = new Scanner(System.in);
        System.out.print("YOUR BOOKSHELF\n");
        //player.getPlayerBookshelf().print();
        System.out.print("CHOOSE THE COLUMN! Choose where you want to inserted the picked and order tiles,\n" +
                "using the command /COLUMN and the coordinate of the column.\n" +
                "For example: if you want to insert the tiles in the second column, you should write /COLUMN 1\n\n" +
                "[Use the command /GOALS to see the description of your personal or common goal cards.]\n");
        while (true) {
            String command = input.nextLine();
            if (Objects.equals(command.toUpperCase(), "/GOALS")) {
                //displayGoals("BookshelfColumn");
                break;
            }
            Message message = MoveSerializer.serializeInput(command);
            connectionClient.sendMessage((ClientMessage) message);
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException iE) {
                iE.printStackTrace();
            }
            if (response != null && response.getCategory() == Message.MessageCategory.VALID_MESSAGE)
                break;
            System.out.print("The chosen column is too full. Please, choose another one.\n");
        }
    }

    /*private void displayBookshelf(){
        Scanner input = new Scanner(System.in);
        String command;
        System.out.print("YOUR BOOKSHELF\n\n");
        //player.getPlayerBookshelf().print();
        System.out.print("\n\n[If you want to come back to the previous screen, use the command /BACK]\n");
        while (true){
            command = input.nextLine();
            if(Objects.equals(command.toUpperCase(), "/BACK"))
                break;
            System.out.print("Please, use the /BACK command correctly.\n");
        }
        displayBoard();
    }*/

    private void printSelection() {
        for (int i = 0; tiles.get(i) != null; i++)
            //System.out.print(tiles.get(i).getColor() + "   ");
            System.out.print("\n");
        for (int i = 0; tiles.get(i) != null; i++)
            System.out.print("(" + i + 1 + ")   ");
        System.out.print("\n");
    }

    public void setSeed(Integer seed) {
        this.seed = seed;
    }

    public void setConnectionClient(ConnectionClient connectionClient) {
        this.connectionClient = connectionClient;
    }

    public void setReceiver(GUIEvent receiver) {
        this.receiver = receiver;
    }

    public void startGame() throws IOException {
        ArrayList<String> coordinates = new ArrayList<>();
        String command = null;
        ClientMessage message = null;
        displayScene();
        if (getCurrentIstance().getPlayer().getNickName().equals(getCurrentIstance().getPlayers().
                get(currPlaying - 1).getNickName())) {
            ObservableList<Node> children = myGridPane_lr.getChildren();
            while ((getCurrentIstance().getResponse() == null) || (getCurrentIstance().getResponse().getCategory() != Message.MessageCategory.VALID_MESSAGE)) {
                for (Node node : children) {            //OTTENGO TUTTE LE COORDINATE, CAPISCO COME USCIRE
                    if (node instanceof ImageView) {
                        ImageView imageView = (ImageView) node;
                        imageView.setOnMouseClicked(event -> {
                            int rowIndex = myGridPane_lr.getRowIndex(imageView);
                            int columnIndex = myGridPane_lr.getColumnIndex(imageView);
                            coordinates.add(Integer.toString(rowIndex));
                            coordinates.add(Integer.toString(columnIndex));

                        });

                    }
                }
                //APPENA ESCO FACCIO PARTIRE IL COMANDO
                switch (coordinates.size()) {
                    case 2 -> command = "/SELECT" + "\t" + coordinates.get(0) + "\t" + coordinates.get(1);
                    case 4 -> command = "/SELECT" + "\t" + coordinates.get(0) + "\t" + coordinates.get(1) +
                            "\t" + coordinates.get(2) + "\t" + coordinates.get(3);
                    case 6 -> command = "/SELECT" + "\t" + coordinates.get(0) + "\t" + coordinates.get(1) +
                            "\t" + coordinates.get(2) + "\t" + coordinates.get(3) + "\t" + coordinates.get(4) +
                            "\t" + coordinates.get(5);
                }
                message = (ClientMessage) MoveSerializer.serializeInput(command);
                getCurrentIstance().getConnectionClient().sendMessage(message);
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //POSSO DISPLAYARE IL CONTAINER
            cleanTiles(coordinates);

        }
    }


    public Integer getSeed() {
        return seed;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private static GameControllerGUI getCurrentIstance() {
        return currentIstance;
    }

    public boolean instance() {
        if (getCurrentIstance().getFlag() == null) {
            return false;
        } else {
            if (getCurrentIstance().getFlag())
                return true;
            else
                return false;
        }
    }

    public Boolean getFlag() {
        return flag;
    }

    public ServerMessage getResponse() {
        return response;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public void cleanTiles(ArrayList<String> coordinates) throws IOException {
        int i = 0;
        while (coordinates.get(i) != null) {
            tiles.add(getCurrentIstance().getLivingRoom().
                    getBoardTile(Integer.parseInt(coordinates.get(i)),Integer.parseInt(coordinates.get(i+1))).getTile());
            getCurrentIstance().getLivingRoom().getBoardTile
                            (Integer.parseInt(coordinates.get(i)), Integer.parseInt(coordinates.get(i + 1))).
                    setTile(null);
            i = i + 2;
        }
        displayScene();
    }

    public List<ItemTile> getTiles() {
        return tiles;
    }
}




