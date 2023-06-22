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
import javafx.scene.control.Label;
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
    private List<Player> ranking;
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
    private boolean firstTime = true;
    private CommonGoalCard commonGoalCard1;
    private CommonGoalCard commonGoalCard2;
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
    //private ImageView[][] imageViews = new ImageView[LivingRoomSize][LivingRoomSize];
    private static GameControllerGUI currentIstance = new GameControllerGUI();
    private Boolean flag = true;
    private Integer rowIndex=null;
    private Integer columnIndex=null;
    private boolean endSelection=false;
    private Integer Bcolumn;

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
                    //imageViews[i][j] = imageView;
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
        if (getCurrentIstance().getTiles().size() > 0) {
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
        AnchorPane root = loader.load();
        root.getChildren().add(myGridPane_lr);
        root.getChildren().add(myGridPane_bs);
        root.getChildren().add(myGridPane_container);
        root.getChildren().add(bsImage);
        root.getChildren().add(myGridPane_columns);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        if (firstTime) {
            setOnMouseclicked();
            firstTime = false;
        }
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



    /*private void displayBookshelfColumn() {
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
*/

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
                get(getCurrentIstance().getCurrPlaying() - 1).getNickName())) {
            while (true) {
                while(!getCurrentIstance().getEndSelection()) {
                    if ((getCurrentIstance().getRowIndex() != null) && (getCurrentIstance().getColumnIndex() != null)) {
                        coordinates.add(Integer.toString(getCurrentIstance().getRowIndex()));            //WHILE INTERNO RIGA 545 E 546
                        coordinates.add(Integer.toString(getCurrentIstance().getColumnIndex()));
                        columnIndex = null;
                        rowIndex = null;
                    }
                }
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
                if(getCurrentIstance().getResponse().getCategory()==Message.MessageCategory.VALID_MESSAGE)
                    break;
                //else
                    //INSERIRE UNA LABEL CHE INFORMA CHE LA MOSSA NON E' VALIDA
            }
            cleanTiles(coordinates);
        }
    //INSERIRE LE TESSERE NELLA BOOKSHELF SCEGLIENDO LA COLONNA CON LA FRECCIA
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
    public void setOnMouseclicked(){
        ObservableList<Node> children = myGridPane_lr.getChildren();
            for(Node node : children){
                if (node instanceof ImageView){
                    ImageView imageView = (ImageView) node;
                    imageView.setOnMouseClicked(event -> {
                        rowIndex = myGridPane_lr.getRowIndex(imageView);
                        columnIndex = myGridPane_lr.getColumnIndex(imageView);
                    });
                }
            }
        }

    public void setFirstTime(boolean firstTime) {
        this.firstTime = firstTime;
    }

    public List<Player> displayLeaderbord(){

        ranking = players.stream().sorted(Comparator.comparingInt(Player::getScore)).toList();
        return ranking;
    }

    public void goToResults() throws IOException {

        File file = new File("src/main/resources/com/example/is23am23/winner.fxml");
        URL url = file.toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        WinnerController winnerController = loader.getController();
        winnerController.displayWinner(winner);
        winnerController.displayLeaderbord(ranking);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public String getWinner(){
        return winner;
    }

    public int getCurrPlaying() {
        return currPlaying;
    }

    public Integer getRowIndex() {
        return rowIndex;
    }

    public Integer getColumnIndex() {
        return columnIndex;
    }

    public boolean isEndSelection() {
        return endSelection;
    }
    public void setOnArrowclicked(){
        ObservableList<Node> children = myGridPane_columns.getChildren();
        for(Node node : children){
            if(node instanceof ImageView){
                ImageView imageView = (ImageView) node;
                imageView.setOnMouseClicked(event ->{
                   Bcolumn = myGridPane_columns.getColumnIndex(imageView);
                });
            }
        }
    }
    public void Endselection(ActionEvent event){
        endSelection=true;
    }

    public Integer getBcolumn() {
        return Bcolumn;
    }
    public boolean getEndSelection(){
        return endSelection;
    }

}




