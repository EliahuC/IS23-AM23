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
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.util.*;

import java.util.concurrent.TimeUnit;

/**
 * Controller of the game scene
 * @author Andrea Bricchi and Giovanni di Lorenzo
 *
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
    private ArrayList<ItemTile> tiles2 = new ArrayList<>();


    @FXML
    Label label_turn;
    @FXML
    Label label_me;
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
    private Boolean showContainer = true;


    /**
     * Method to display the scene
     * @throws IOException exception
     * @author Andrea Bricchi and Giovanni di Lorenzo
     */
    public void displayScene() throws IOException {
        /*if (getCurrentIstance().getGrids()) {
            // initializeGridPanes();
            getCurrentIstance().setGrids(false);
        }*/
        if (instance()) {
            GameControllerGUI.currentIstance = this;
            getCurrentIstance().setFlag(false);
            //getCurrentIstance().getReceiver().setGamecontrollerGUI(getCurrentIstance());
            //getCurrentIstance().setReceiver(receiver);
        }

        label_turn.setText("Now playing: " + getPlayers().get(getCurrPlaying()-1).getNickName());

        myGridPane_lr.getChildren().clear();
        for (int i = 0; i < LivingRoomSize; i++) {
            for (int j = 0; j < LivingRoomSize; j++) {
                if (getCurrentIstance().livingRoom.getBoardTile(i, j).getCategory() != BoardToken.boardTokenCategory.UNAVAILABLE) {
                    ItemTile tile = getCurrentIstance().livingRoom.getBoardTile(i, j).getTile();
                    ImageView imageView = new ImageView();
                    imageView.setImage(chooseCategoryImage(tile));
                    imageView.setFitWidth(60);
                    imageView.setFitHeight(60);
                    myGridPane_lr.setLayoutX(57);
                    myGridPane_lr.setLayoutY(103);
                    GridPane.setMargin(imageView, new Insets(0));
                    myGridPane_lr.add(imageView, j, i);
                }
            }
        }


        myGridPane_lr.setOnMouseClicked(mouseEvent -> {
            Node clickNode = mouseEvent.getPickResult().getIntersectedNode();
            if (clickNode instanceof ImageView && ((ImageView) clickNode).getImage() != null) {
                if (getCurrentIstance().getPlayer().getNickName().equals(getCurrentIstance().getPlayers().
                        get(getCurrPlaying()-1).getNickName())) {

                    getCurrentIstance().getCoordinates().add(GridPane.getRowIndex(clickNode));
                    getCurrentIstance().getCoordinates().add(GridPane.getColumnIndex(clickNode));

                    //clickNode.setStyle("-fx-border-color: yellow; -fx-border-width: 10px;");

                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("INVALID MOVE");
                    alert.setHeaderText("It's not your turn!");
                    if (alert.showAndWait().get() == ButtonType.OK) {

                    }
                }

            }
        });

        bookshelf = getCurrentIstance().getPlayer().getPlayerBookshelf();
        for (int i = 0; i < shelfRows; i++) {
            for (int j = 0; j < shelfCols; j++) {
                ItemTile tile = bookshelf.getTile(i, j);
                ImageView imageView = new ImageView();
                imageView.setImage(chooseCategoryImage(tile));
                imageView.setFitWidth(60);
                imageView.setFitHeight(60);
                myGridPane_bs.setLayoutX(859);
                myGridPane_bs.setLayoutY(268);
                GridPane.setMargin(imageView, new Insets(0, 5, 0, 5));
                myGridPane_bs.add(imageView, j, i);
            }
        }
            if(getCurrentIstance().getTiles().size()>0) {
                for (int i = 0; i < getCurrentIstance().getTiles().size(); i++) {
                    ImageView imageView = new ImageView();
                    imageView.setImage(chooseCategoryImage(getCurrentIstance().getTiles().get(i)));
                    imageView.setFitWidth(45);
                    imageView.setFitHeight(45);
                    myGridPane_container.setLayoutX(906);
                    myGridPane_container.setLayoutY(110);
                    myGridPane_container.add(imageView, i, 0);
                }
            }else{
                if(!showContainer){
                    Node nodeC = myGridPane_container.getChildren().get(0);
                    myGridPane_container.getChildren().clear();
                    myGridPane_container.getChildren().add(0,nodeC);
                    for (int i = 0; i < 3; i++) {
                        ImageView imageView = new ImageView();
                        imageView.setImage(null);
                        imageView.setFitWidth(45);
                        imageView.setFitHeight(45);
                        myGridPane_container.setLayoutX(906);
                        myGridPane_container.setLayoutY(110);
                        myGridPane_container.add(imageView, i, 0);
                }}
            }
        getCurrentIstance().stage.setScene(scene);
        getCurrentIstance().stage.setTitle("My Shelfie");
        Image icon = new Image("/com/example/is23am23/little_icon.png");
        getCurrentIstance().stage.getIcons().add(icon);
        getCurrentIstance().stage.show();
    }

    /**
     * @param tile received from the board
     * @return the image to use
     * @author Andrea Bricchi and Giovanni di Lorenzo
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
     * Method to show the goals scene
     * @param event click
     * @throws IOException exception
     * @author Andrea Bricchi and Giovanni di Lorenzo
     */
    public void goToGoals(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/is23am23/goals.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        GoalsController goalsController = loader.getController();
        if (goalsController.getGameControllerGUI() == null) {
            goalsController.setGameControllerGUI(getCurrentIstance());
        }
        goalsController.displayPersonalGoal();
        goalsController.displayCommonGoal();
        goalsController.displayPoints();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene1 = new Scene(root);
        stage.setScene(scene1);
        stage.show();
    }

    /**
     * Method to return to the Menu
     * @param event click
     * @throws IOException exception
     * @author Andrea Bricchi
     */
    public void returnToMenu(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("RETURN TO MENU");
        alert.setHeaderText("You are about to return to the menu, are you sure?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/is23am23/menu.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
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

    public void setSeed(Integer seed) {
        this.seed = seed;
    }

    public void setConnectionClient(ConnectionClient connectionClient) {
        this.connectionClient = connectionClient;
    }

    public void setReceiver(GUIEvent receiver) {
        this.receiver = receiver;
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

    /**
     * @return true if the flag is true
     * @author Giovanni di Lorenzo
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
     * Method to clean the selected tiles from the board on the scene
     * @param coordinates of the tiles
     * @throws IOException exception
     * @author Giovanni di Lorenzo
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

        ranking = getCurrentIstance().getPlayers().stream().sorted(Comparator.comparingInt(Player::getScore)).toList();
        if(checkRanking())return ranking;
        fixRanking();
        return ranking;
    }

    private void fixRanking() {
        int position=0;
        for(int i =0;i<ranking.size();i++){
            if(ranking.get(i).isWinner())
            {
                position=i;
                break;
            }
        }
        if (position!=0) {
            Player p = ranking.remove(position);
            ranking.add(0, p);
        }

    }

    private boolean checkRanking() {
        return ranking.get(0).isWinner();
    }


    /**
     * Method to go to the result
     * @throws IOException exception
     * @author Andrea Bricchi
     */
    public void goToResults() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/is23am23/winner.fxml"));
        AnchorPane rootWinner= null;
        rootWinner=loader.load();
        WinnerController winnerController = loader.getController();
        winnerController.displayLeaderbord(displayLeaderbord());
        Scene scene1 = new Scene(rootWinner);
        stage.setScene(scene1);
        stage.show();
    }


    public String getWinner() {
        return winner;
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
     * Method to send the selected tiles to the server
     * @param event click
     * @author Andrea Bricchi and Giovanni di Lorenzo
     */
    public void Endselection(ActionEvent event) {
        Boolean check = true;
        if ((getCurrentIstance().getPlayer().getNickName().equals(getCurrentIstance().getPlayers().get(getCurrPlaying()-1).getNickName()))
                && (getCurrentIstance().getCoordinates().size() > 0)) {
            String command = null;
            ClientMessage message = null;
            switch (getCurrentIstance().getCoordinates().size()) {
                case 2:
                    command = "/SELECT" + " " + getCurrentIstance().getCoordinates().get(0) + " " +
                            getCurrentIstance().getCoordinates().get(1);
                    break;
                case 4:
                    command = "/SELECT" + " " + getCurrentIstance().getCoordinates().get(0) + " " +
                            getCurrentIstance().getCoordinates().get(1) + " " +
                            getCurrentIstance().getCoordinates().get(2) + " " + getCurrentIstance().getCoordinates().get(3);
                    break;
                case 6:
                    command = "/SELECT" + " " + getCurrentIstance().getCoordinates().get(0) + " " +
                            getCurrentIstance().getCoordinates().get(1) + " " +
                            getCurrentIstance().getCoordinates().get(2) + " " + getCurrentIstance().getCoordinates().get(3) +
                            " " + getCurrentIstance().getCoordinates().get(4) + " " + getCurrentIstance().getCoordinates().get(5);
                    break;
                default:
                    check = false;
                    getCurrentIstance().getCoordinates().clear();
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("INVALID MOVE");
                    alert.setHeaderText("Your move is not valid!");
                    if (alert.showAndWait().get() == ButtonType.OK) {
                    }
                    break;
            }
            if (check) {
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
                    getCurrentIstance().getCoordinates().clear();
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("INVALID MOVE");
                    alert.setHeaderText("Your move is not valid!");
                    if (alert.showAndWait().get() == ButtonType.OK) {

                    }
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

    public ArrayList<Integer> getCoordinates() {
        return coordinates;
    }

    /**
     * Method to send the message order and column to the server
     * @param event click
     * @throws IOException exception
     * @author Andrea Bricchi and Giovanni di Lorenzo
     */
    private void PlaceTilesIntheBookshelf(MouseEvent event) throws IOException {
        if (getCurrentIstance().getTiles().size() > 0) {
            String command = null;
            ClientMessage message = null;
            Node clickNode = event.getPickResult().getIntersectedNode();
            if (clickNode instanceof ImageView && ((ImageView) clickNode).getImage() != null) {
                switch (getCurrentIstance().getTiles().size()) {

                    case 1:
                        command = "/ORDER 1";
                        break;
                    case 2:
                        command = "/ORDER 1 2";
                        break;
                    case 3:
                        command = "/ORDER 1 2 3";
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
                    try {
                        TimeUnit.MILLISECONDS.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (getCurrentIstance().getResponse().getCategory() != Message.MessageCategory.WARNING) {
                        cleanTiles2(getCurrentIstance().getTiles());
                        getCurrentIstance().tiles2.clear();
                        getCurrentIstance().order.clear();
                        if(getCurrPlaying() < getPlayers().size())
                        {
                            label_turn.setText("Now playing: " + getPlayers().get(getCurrPlaying()).getNickName());
                        }
                        else {
                            label_turn.setText("Now playing: " + getPlayers().get(0).getNickName());
                        }

                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("INVALID MOVE");
                        alert.setHeaderText("The chosen coloumn is too full!");
                        if (alert.showAndWait().get() == ButtonType.OK) {

                        }
                    }
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("INVALID MOVE");
                alert.setHeaderText("It's not your turn!");
                if (alert.showAndWait().get() == ButtonType.OK) {

                }
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("INVALID MOVE");
            alert.setHeaderText("You haven't selected any tiles yet!");
            if (alert.showAndWait().get() == ButtonType.OK) {

            }
        }
    }
    public void displayNickname(String nickname, Label label) {
        label.setText("My nickname: " + nickname);
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    private void cleanTiles2(ArrayList<ItemTile> tiles) {
        int i = 0;
        while (i < tiles.size()) {
            getCurrentIstance().getPlayer().getPlayerBookshelf().setTile(Bcolumn, tiles.get(i));
            i++;
        }
        tiles.clear();
        getCurrentIstance().setShowContainer(false);
       try {
            displayScene();
        } catch (IOException e) {
            throw new RuntimeException(e);
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
     * Method that loads the bookshelf on the stage
     * @author Eliahu Cohen
     *
     */
    public void launchBookshelf() {
        ImageView imageView = new ImageView();
        File file_bs = new File("/com/example/is23am23/bookshelf.png");
        image = new Image(String.valueOf(file_bs));
        imageView.setImage(image);
        imageView.setFitWidth(440);
        imageView.setFitHeight(440);
        bsImage.setLayoutX(810);
        bsImage.setLayoutY(243);
        bsImage.add(imageView, 0, 0);
        displayNickname(nickname, label_me);
        for (int i = 0; i < 5; i++) {
            imageView = new ImageView();

            //IMMAGINE FRECCIA DA INSERIRE
            File file = new File("/com/example/is23am23/arrow_image.png");
            image = new Image(String.valueOf(file));
            imageView.setImage(image);
            imageView.setFitWidth(64);
            imageView.setFitHeight(64);
            myGridPane_columns.setLayoutX(852);
            myGridPane_columns.setLayoutY(167);
            myGridPane_columns.add(imageView, i, 0);
            GridPane.setMargin(imageView, new Insets(25));
        }
        myGridPane_columns.setOnMouseClicked(mouseEvent -> {
            if (getCurrentIstance().getPlayer().getNickName().equals(getCurrentIstance().getPlayers().
                    get(getCurrPlaying()-1).getNickName())) {
                try {
                    PlaceTilesIntheBookshelf(mouseEvent);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }

        });
    }

    public void setShowContainer(Boolean showContainer) {
        this.showContainer = showContainer;
    }

    public int getCurrPlaying() {
        return currPlaying;
    }

    public void setCurrPlaying(int currPlaying) {
        this.currPlaying = currPlaying;
    }

}
