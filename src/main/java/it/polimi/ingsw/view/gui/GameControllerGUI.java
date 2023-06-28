package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.MoveSerializer;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Network.Client.ConnectionClient;
import it.polimi.ingsw.model.board.BoardToken;
import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.board.LivingRoom;
import it.polimi.ingsw.model.player.BookShelf;
import it.polimi.ingsw.model.player.Player;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import java.util.concurrent.TimeUnit;

/**
 * @author Andrea Bricchi and Giovanni di Lorenzo
 * Controller of the game scene
 */
public class GameControllerGUI implements Initializable {
    private Scene scene;
    private AnchorPane root;
    private Stage stage;
    private ConnectionClient connectionClient;
    private String nickname;
    private GUIEvent receiver;
    private BookShelf bookshelf = new BookShelf();
    private ServerMessage response;
    private LivingRoom livingRoom;
    private Player player;
    private List<Player> players;
    private List<Player> ranking;
    private ArrayList<ItemTile> tiles = new ArrayList<>();
    private int currPlaying=1;
    private String currentPlayer;
    private String winner;
    private Integer seed;
    private Image image;
    public static final int LivingRoomSize = 9;
    private static final int shelfRows = 6;
    private static final int shelfCols = 5;
    private boolean endgame;
    private boolean switchOrder = false;
    private ArrayList<ItemTile> tiles2 = new ArrayList<>();


    @FXML
    GridPane myGridpane_turn;
    @FXML
    GridPane myGridpane_me;
    @FXML
    GridPane myGridPane_choice;
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
    private static GameControllerGUI currentIstance = new GameControllerGUI();
    private Boolean flag = true;
    private Integer rowIndex = null;
    private Integer columnIndex = null;
    private Integer Bcolumn;
    private ArrayList<Integer> order = new ArrayList<>();

    private ArrayList<Integer> coordinates = new ArrayList<>();
    private Boolean grids = true;
    private Boolean fxmlLoad = true;
    FXMLLoader loader;


    /**
     * @author Andrea Bricchi and Giovanni di Lorenzo
     * @throws IOException exception
     * Method to display the scene
     */
    public void displayScene() throws IOException {
        if(getCurrentIstance().getGrids()){
           // initializeGridPanes();
            getCurrentIstance().setGrids(false);
        }
        if (instance()) {
            GameControllerGUI.currentIstance = this;
            getCurrentIstance().setFlag(false);
            //getCurrentIstance().getReceiver().setGamecontrollerGUI(getCurrentIstance());
            //getCurrentIstance().setReceiver(receiver);
        }

        myGridPane_lr.getChildren().clear();
        for (int i = 0; i < LivingRoomSize; i++) {
            for (int j = 0; j < LivingRoomSize; j++) {
                if (getCurrentIstance().livingRoom.getBoardTile(i, j).getCategory() != BoardToken.boardTokenCategory.UNAVAILABLE) {
                    ItemTile tile = getCurrentIstance().livingRoom.getBoardTile(i, j).getTile();
                    ImageView imageView = new ImageView();
                    imageView.setImage(chooseCategoryImage(tile));
                    imageView.setFitWidth(34);
                    imageView.setFitHeight(34);
                    myGridPane_lr.setLayoutX(29);
                    myGridPane_lr.setLayoutY(61);
                    GridPane.setMargin(imageView, new Insets(0));
                    myGridPane_lr.add(imageView, j, i);
                }
            }
        }
        myGridPane_lr.setOnMouseClicked(mouseEvent -> {
            Node clickNode = mouseEvent.getPickResult().getIntersectedNode();
            if (clickNode instanceof ImageView && ((ImageView) clickNode).getImage() != null) {
                if (getCurrentIstance().getPlayer().getNickName().equals(getCurrentIstance().getPlayers().get
                        (getCurrentIstance().getCurrPlaying() - 1).getNickName())) {
                    getCurrentIstance().getCoordinates().add(GridPane.getRowIndex(clickNode));
                    getCurrentIstance().getCoordinates().add(GridPane.getColumnIndex(clickNode));
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("INVALID MOVE");
                    alert.setHeaderText("It's not your turn!");
                    if (alert.showAndWait().get() == ButtonType.OK) {

                    }
                }

            }
        });

        bookshelf = player.getPlayerBookshelf();
        for (int i = 0; i < shelfRows; i++) {
            for (int j = 0; j < shelfCols; j++) {
                ItemTile tile = bookshelf.getTile(i, j);
                ImageView imageView = new ImageView();
                imageView.setImage(chooseCategoryImage(tile));
                imageView.setFitWidth(33);
                imageView.setFitHeight(33);
                myGridPane_bs.setLayoutX(386);
                myGridPane_bs.setLayoutY(158);
                GridPane.setMargin(imageView, new Insets(0, 2, 0, 2));
                myGridPane_bs.add(imageView, j, i);
            }
        }


        if (getCurrentIstance().getTiles().size() > 0) {
            for (int i = 0; i < getCurrentIstance().getTiles().size(); i++) {
                ImageView imageView = new ImageView();
                imageView.setImage(chooseCategoryImage(getCurrentIstance().getTiles().get(i)));
                imageView.setFitWidth(30);
                imageView.setFitHeight(30);
                myGridPane_container.setLayoutX(384);
                myGridPane_container.setLayoutY(66);
                myGridPane_container.add(imageView, i, 0);
            }
        }else{
            for(int i = 0; i<3;i++){
                ImageView imageView = new ImageView();
                imageView.setImage(null);
                imageView.setFitWidth(30);
                imageView.setFitHeight(30);
                myGridPane_container.setLayoutX(384);
                myGridPane_container.setLayoutY(66);
                myGridPane_container.add(imageView, i, 0);
            }
        }

        // myGridPane_choice = new GridPane();
        for (int i = 0; i < 2; i++) {
            ImageView imageView = new ImageView();

            //IMMAGINE FRECCIA DA INSERIRE
            File file = new File("/com/example/is23am23/arrow_image.png");
            image = new Image(String.valueOf(file));
            imageView.setImage(image);
            imageView.setFitWidth(20);
            imageView.setFitHeight(20);
            imageView.setRotate(-90);
            myGridPane_choice.setLayoutX(405);
            myGridPane_choice.setLayoutY(35);
            myGridPane_choice.add(imageView, i, 0);
            //GridPane.setMargin(imageView, new Insets(5));
        }
        myGridPane_choice.setOnMouseClicked(mouseEvent -> {
            try {
                if (getCurrentIstance().getPlayer().getNickName().equals(getCurrentIstance().getPlayers().get
                        (getCurrentIstance().getCurrPlaying() - 1).getNickName()))
                    setOnOrderClickEvent(mouseEvent);
                else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("INVALID MOVE");
                    alert.setHeaderText("It's not your turn!");
                    if (alert.showAndWait().get() == ButtonType.OK) {

                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        /*
        File file = new File("src/main/resources/com/example/is23am23/game.fxml");
        URL url = file.toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        getCurrentIstance().setGrids(false);
        */



        getCurrentIstance().stage.setScene(scene);
        getCurrentIstance().stage.setTitle("My Shelfie");
        Image icon = new Image("com/example/is23am23/little_icon.png");
        getCurrentIstance().stage.getIcons().add(icon);
        getCurrentIstance().stage.show();
    }

    /**
     * @author Andrea Bricchi and Giovanni di Lorenzo
     * @param tile received from the board
     * @return the image to use
     */
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

    /**
     * @author Andrea Bricchi and Giovanni di Lorenzo
     * @param event click
     * @throws IOException exception
     * Method to show the goals scene
     */
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

    /**
     * @author Andrea Bricchi
     * @param event click
     * @throws IOException exception
     * Method to return to the Menu
     */
    public void returnToMenu(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("RETURN TO MENU");
        alert.setHeaderText("You are about to return to the menu, are you sure?");

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



    public void setSeed(Integer seed) {
        this.seed = seed;
    }

    public void setConnectionClient(ConnectionClient connectionClient) {
        this.connectionClient = connectionClient;
    }

    public void setReceiver(GUIEvent receiver) {
        this.receiver = receiver;
    }


/*public void startGame() throws IOException {
        ArrayList<String> coordinates = new ArrayList<>();
        String command = null;
        ClientMessage message = null;
        displayScene();
        if (getCurrentIstance().getPlayer().getNickName().equals(getCurrentIstance().getPlayers().
                get(getCurrentIstance().getCurrPlaying() - 1).getNickName())) {
            while (true) {
                //while(!getCurrentIstance().getendSelection()) {
                    if ((getCurrentIstance().getRowIndex() != null) && (getCurrentIstance().getColumnIndex() != null)) {
                        coordinates.add(Integer.toString((getCurrentIstance().getRowIndex())));
                        coordinates.add(Integer.toString(getCurrentIstance().getColumnIndex()));
                        columnIndex = null;
                        rowIndex = null;
                    }
                //}
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
    }*/

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

    /**
     * @author Giovanni di Lorenzo
     * @return true if the flag is true
     */
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

    /**
     * @author Giovanni di Lorenzo
     * @param coordinates of the tiles
     * @throws IOException exception
     * Method to clean the selected tiles from the board on the scene
     */
    public void cleanTiles(ArrayList<Integer> coordinates) throws IOException {
        int i = 0;
        while (i < coordinates.size()) {
            getCurrentIstance().getTiles().add(getCurrentIstance().getLivingRoom().
                    getBoardTile(coordinates.get(i), (coordinates.get(i + 1))).getTile());
            getCurrentIstance().tiles2.add(getCurrentIstance().getLivingRoom().
                    getBoardTile(coordinates.get(i), (coordinates.get(i + 1))).getTile());
            getCurrentIstance().getLivingRoom().getBoardTile
                            (coordinates.get(i), coordinates.get(i + 1)).
                    setTile(null);
            i = i + 2;
        }
        displayScene();
    }

    public ArrayList<ItemTile> getTiles() {
        return tiles;
    }

    public List<Player> displayLeaderbord() {

        ranking = players.stream().sorted(Comparator.comparingInt(Player::getScore)).toList();
        return ranking;
    }

    /**
     * @author Andrea Bricchi
     * @throws IOException exception
     * Method to go to the result
     */
    public void goToResults() throws IOException {

        File file = new File("src/main/resources/com/example/is23am23/winner.fxml");
        URL url = file.toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        WinnerController winnerController = loader.getController();
        winnerController.displayWinner(winner);
        winnerController.displayLeaderbord(ranking);
        //Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public String getWinner() {
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

    //public boolean isEndSelection() {
    //  return endSelection;
    //}
   /* public void setOnArrowclicked() {
        ObservableList<Node> children = myGridPane_columns.getChildren();
        for (Node node : children) {
            if (node instanceof ImageView) {
                ImageView imageView = (ImageView) node;
                imageView.setOnMouseClicked(event -> {
                    Bcolumn = myGridPane_columns.getColumnIndex(imageView);
                });
            }
        }
    }*/

    /**
     * @author Andrea Bricchi and Giovanni di Lorenzo
     * @param event click
     * Method to send the selected tiles to the server
     */
    public void Endselection(ActionEvent event) {
        if (getCurrentIstance().getPlayer().getNickName().equals(getCurrentIstance().getPlayers().
                get(getCurrentIstance().getCurrPlaying() - 1).getNickName())) {
            String command = null;
            ClientMessage message = null;
            switch (getCurrentIstance().getCoordinates().size()) {
                case 2 -> command = "/SELECT" + " " + getCurrentIstance().getCoordinates().get(0) + " " +
                        getCurrentIstance().getCoordinates().get(1);
                case 4 -> command = "/SELECT" + " " + getCurrentIstance().getCoordinates().get(0) + " " +
                        getCurrentIstance().getCoordinates().get(1) + " " +
                        getCurrentIstance().getCoordinates().get(2) + " " + getCurrentIstance().getCoordinates().get(3);
                case 6 -> command = "/SELECT" + " " + getCurrentIstance().getCoordinates().get(0) + " " +
                        getCurrentIstance().getCoordinates().get(1) + " " +
                        getCurrentIstance().getCoordinates().get(2) + " " + getCurrentIstance().getCoordinates().get(3) +
                        " " + getCurrentIstance().getCoordinates().get(4) + " " + getCurrentIstance().getCoordinates().get(5);
            }
            message = (ClientMessage) MoveSerializer.serializeInput(command);
            getCurrentIstance().getConnectionClient().sendMessage(message);
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (getCurrentIstance().getResponse().getCategory() == Message.MessageCategory.VALID_MESSAGE) {
                try {
                    cleanTiles(getCurrentIstance().getCoordinates());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("INVALID MOVE");
                alert.setHeaderText("Your move is not valid!");
                if (alert.showAndWait().get() == ButtonType.OK) {
                    getCurrentIstance().getCoordinates().clear();

                }
            }
        }
    }


    public Integer getBcolumn() {
        return Bcolumn;
    }
    /*public boolean getendSelection(){
        return endSelection;
    }*/

    /*public void pickTiles(ArrayList<String> coordinates){
         if(getCurrentIstance().getPlayer().getNickName().equals(getCurrentIstance().getPlayers().
                 get(getCurrentIstance().getCurrPlaying()-1).getNickName())){
             while(!getCurrentIstance().getendSelection()){
                 if((getCurrentIstance().getRowIndex()!=null)&&(getCurrentIstance().getColumnIndex()!=null)){
                     //coordinates.add();
                 }
             }
         }
    }*/

    /**
     * @author Giovanni di Lorenzo
     * @param e mouse click
     * @throws IOException exception
     * Method to set the order greed
     */
    private void setOnOrderClickEvent(MouseEvent e) throws IOException {
        ItemTile temp;
        Node clickNode = e.getPickResult().getIntersectedNode();
        switch (getCurrentIstance().getTiles().size()) {
            case 2:
                if (clickNode instanceof ImageView && ((ImageView) clickNode).getImage() != null) {
                    if (GridPane.getColumnIndex(clickNode) == 0) {
                        if (!getCurrentIstance().switchOrder) {
                            temp = getCurrentIstance().getTiles().get(0);
                            getCurrentIstance().getTiles().set(0,getCurrentIstance().getTiles().get(1));
                            getCurrentIstance().getTiles().set(1,temp);
                            switchOrder = true;
                        } else {
                            temp = getCurrentIstance().getTiles().get(0);
                            getCurrentIstance().getTiles().set(0,getCurrentIstance().getTiles().get(1));
                            getCurrentIstance().getTiles().set(1,temp);
                            switchOrder = false;
                        }
                        displayScene();
                        break;
                    }
                }
            case 3:
                //TO DO

        }
    }

    public ArrayList<Integer> getCoordinates() {
        return coordinates;
    }

    /**
     * @author Andrea Bricchi and Giovanni di Lorenzo
     * @param event click
     * @throws IOException exception
     * Method to send the message order and column to the server
     */
    private void PlaceTilesIntheBookshelf(MouseEvent event) throws IOException {
        String command = null;
        ClientMessage message = null;
        Node clickNode = event.getPickResult().getIntersectedNode();
        if (clickNode instanceof ImageView && ((ImageView) clickNode).getImage() != null) {
            switch (getCurrentIstance().getTiles().size()) {

                case 1:
                    command = "/ORDER 1";
                    break;
                case 2:
                    if (!getCurrentIstance().switchOrder)
                        command = "/ORDER 1 2";
                    else
                        command = "/ORDER 2 1";
                    break;
                case 3 :
                    defineOrder();
                    command = "/ORDER" + " " + getCurrentIstance().order.get(0) + " " + getCurrentIstance().order.get(1)
                            + " " + getCurrentIstance().order.get(2);
                    break;
            }

            message = (ClientMessage) MoveSerializer.serializeInput(command);
            getCurrentIstance().getConnectionClient().sendMessage(message);
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException iE) {
                iE.printStackTrace();
            }
            if (getCurrentIstance().getResponse().getCategory() == Message.MessageCategory.VALID_MESSAGE) {
                Bcolumn = GridPane.getColumnIndex(clickNode);
                command = "/COLUMN" + " " + Bcolumn;
                message = (ClientMessage) MoveSerializer.serializeInput(command);
                getCurrentIstance().getConnectionClient().sendMessage(message);
                getCurrentIstance().getCoordinates().clear();
                getCurrentIstance().getTiles().clear();
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (getCurrentIstance().getResponse().getCategory() != Message.MessageCategory.WARNING) {
                    cleanTiles2(getCurrentIstance().getTiles());
                    getCurrentIstance().getTiles().clear();
                    getCurrentIstance().tiles2.clear();
                    getCurrentIstance().getCoordinates().clear();
                    getCurrentIstance().order.clear();
                    displayScene();
                    //SPOSTANENTO TESSERE DAL CONTAINER ALLA BOOKSHELF + DISPLAY SCENE

                    //SI PUO MOSTRARE UN POP-UP CHE DICE "TURNO COMPLETATO"
                } else {
                    //SI MOSTRA POP-UP CHE DICE "CHOSEN COLUMN TOO FULL"
                }
            }

        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("INVALID MOVE");
            alert.setHeaderText("It's not your turn!");
            if (alert.showAndWait().get() == ButtonType.OK) {

            }
        }
    }
    public void displayNickname(String nickname, Label label) {
        label.setText(nickname);
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    private void defineOrder() {
        int i = 0, j = 0;
        while (i < getCurrentIstance().getTiles().size()) {
            while (j < getCurrentIstance().tiles2.size()) {
                if (getCurrentIstance().tiles2.get(i).equals(getCurrentIstance().getTiles().get(j))) {
                    getCurrentIstance().order.add(j + 1);
                    break;
                } else
                    j++;
            }
            j=0;
            i++;
        }
    }

    public ArrayList<ItemTile> getTiles2() {
        return tiles2;
    }

    public Boolean getGrids() {
        return grids;
    }

    public void setGrids(Boolean grids) {
        this.grids = grids;
    }

    public GUIEvent getReceiver() {
        return receiver;
    }
    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public Boolean getFxmlLoad() {
        return fxmlLoad;
    }

    public void setFxmlLoad(Boolean fxmlLoad) {
        this.fxmlLoad = fxmlLoad;
    }

    public FXMLLoader getLoader() {
        return loader;
    }
    private void cleanTiles2(ArrayList<ItemTile> tiles){
        int i = 0;
        while (i < tiles.size()) {
            getCurrentIstance().getPlayer().getPlayerBookshelf().setTile(Bcolumn,tiles.get(i));
            i++;
        }
    }

    public void setRoot(AnchorPane root) {
        this.root = root;
        scene = new Scene(this.root);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * @author Eliahu Cohen
     * Method that loads the bookshelf on the stage
     */
    public void launchBookshekf() {
        ImageView imageView = new ImageView();
        File file_bs = new File("/com/example/is23am23/bookshelf.png");
        image = new Image(String.valueOf(file_bs));
        imageView.setImage(image);
        imageView.setFitWidth(240);
        imageView.setFitHeight(240);
        bsImage.setLayoutX(360);
        bsImage.setLayoutY(146);
        bsImage.add(imageView, 0, 0);
        Label label = new Label();
        label.setText("Now playing: " + getCurrentIstance().getCurrentPlayer());
        label.setFont(new Font(14));
        myGridpane_turn.setLayoutX(14);
        myGridpane_turn.setLayoutY(16);
        myGridpane_turn.add(label, 0, 0);
        Label labelMe = new Label();
        displayNickname(getCurrentIstance().nickname, labelMe);
        labelMe.setFont(new Font(14));
        myGridpane_me.setLayoutX(258);
        myGridpane_me.setLayoutY(16);
        myGridpane_me.add(labelMe, 0, 0);
        for (int i = 0; i < 5; i++) {
            imageView= new ImageView();

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
        myGridPane_columns.setOnMouseClicked(mouseEvent -> {
            if (getCurrentIstance().getPlayer().getNickName().equals(getCurrentIstance().getPlayers().get
                    (getCurrentIstance().getCurrPlaying() - 1).getNickName())) {
                try {
                    PlaceTilesIntheBookshelf(mouseEvent);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        });
    }
}
