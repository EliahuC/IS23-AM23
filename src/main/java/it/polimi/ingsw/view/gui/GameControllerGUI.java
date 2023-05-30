package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.MoveSerializer;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Network.Client.ConnectionClient;
import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.board.BoardToken;
import it.polimi.ingsw.model.board.ItemTile;
import it.polimi.ingsw.model.board.LivingRoom;
import it.polimi.ingsw.model.player.BookShelf;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.view.cli.CLIEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class GameControllerGUI {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private ConnectionClient connectionClient;
    //private GUIEvent receiver;
    private ServerMessage response;
    private LivingRoom livingRoom;
    private Player player;
    private List<Player> players;
    private List<ItemTile> tiles;
    private int currPlaying;
    private String currentPlayer;
    private String winner;

    @FXML
    ImageView shelf_0_0;
    @FXML
    ImageView shelf_0_1;
    @FXML
    ImageView shelf_0_2;
    @FXML
    ImageView shelf_0_3;
    @FXML
    ImageView shelf_0_4;
    @FXML
    ImageView shelf_1_0;
    @FXML
    ImageView shelf_1_1;
    @FXML
    ImageView shelf_1_2;
    @FXML
    ImageView shelf_1_3;
    @FXML
    ImageView shelf_1_4;
    @FXML
    ImageView shelf_2_0;
    @FXML
    ImageView shelf_2_1;
    @FXML
    ImageView shelf_2_2;
    @FXML
    ImageView shelf_2_3;
    @FXML
    ImageView shelf_2_4;
    @FXML
    ImageView shelf_3_0;
    @FXML
    ImageView shelf_3_1;
    @FXML
    ImageView shelf_3_2;
    @FXML
    ImageView shelf_3_3;
    @FXML
    ImageView shelf_3_4;
    @FXML
    ImageView shelf_4_0;
    @FXML
    ImageView shelf_4_1;
    @FXML
    ImageView shelf_4_2;
    @FXML
    ImageView shelf_4_3;
    @FXML
    ImageView shelf_4_4;
    @FXML
    ImageView shelf_5_0;
    @FXML
    ImageView shelf_5_1;
    @FXML
    ImageView shelf_5_2;
    @FXML
    ImageView shelf_5_3;
    @FXML
    ImageView shelf_5_4;
    @FXML
    ImageView livingroom_0_3;
    @FXML
    ImageView livingroom_0_4;
    @FXML
    ImageView livingroom_1_3;
    @FXML
    ImageView livingroom_1_4;
    @FXML
    ImageView livingroom_1_5;
    @FXML
    ImageView livingroom_2_2;
    @FXML
    ImageView livingroom_2_3;
    @FXML
    ImageView livingroom_2_4;
    @FXML
    ImageView livingroom_2_5;
    @FXML
    ImageView livingroom_2_6;
    @FXML
    ImageView livingroom_3_1;
    @FXML
    ImageView livingroom_3_2;
    @FXML
    ImageView livingroom_3_3;
    @FXML
    ImageView livingroom_3_4;
    @FXML
    ImageView livingroom_3_5;
    @FXML
    ImageView livingroom_3_6;
    @FXML
    ImageView livingroom_3_7;
    @FXML
    ImageView livingroom_3_8;
    @FXML
    ImageView livingroom_4_0;
    @FXML
    ImageView livingroom_4_1;
    @FXML
    ImageView livingroom_4_2;
    @FXML
    ImageView livingroom_4_3;
    @FXML
    ImageView livingroom_4_4;
    @FXML
    ImageView livingroom_4_5;
    @FXML
    ImageView livingroom_4_6;
    @FXML
    ImageView livingroom_4_7;
    @FXML
    ImageView livingroom_4_8;
    @FXML
    ImageView livingroom_5_0;
    @FXML
    ImageView livingroom_5_1;
    @FXML
    ImageView livingroom_5_2;
    @FXML
    ImageView livingroom_5_3;
    @FXML
    ImageView livingroom_5_4;
    @FXML
    ImageView livingroom_5_5;
    @FXML
    ImageView livingroom_5_6;
    @FXML
    ImageView livingroom_5_7;
    @FXML
    ImageView livingroom_6_2;
    @FXML
    ImageView livingroom_6_3;
    @FXML
    ImageView livingroom_6_4;
    @FXML
    ImageView livingroom_6_5;
    @FXML
    ImageView livingroom_6_6;
    @FXML
    ImageView livingroom_7_3;
    @FXML
    ImageView livingroom_7_4;
    @FXML
    ImageView livingroom_7_5;
    @FXML
    ImageView livingroom_8_4;
    @FXML
    ImageView livingroom_8_5;
    @FXML
    ImageView container1;
    @FXML
    ImageView container2;
    @FXML
    ImageView container3;

    private Game game;

    public void setGame(Game game){
        this.game = game;
    }
    public void displayLivingroom(){

        LivingRoom livingroom = game.getLivingRoom();

        for(int i=0; i<9; i++)
        {
            for(int  j=0; j<9; j++)
            {
                ItemTile tile = livingroom.getBoardTile(i,j).getTile();
                if(i==0 && j == 3)
                {
                    displayImage(livingroom_0_3, chooseCategoryImage(tile));
                }
                else if(i==0 && j == 4)
                {
                    displayImage(livingroom_0_4, chooseCategoryImage(tile));
                }
                else if(i==1 && j == 3)
                {
                    displayImage(livingroom_1_3, chooseCategoryImage(tile));
                }
                else if(i==1 && j == 4)
                {
                    displayImage(livingroom_1_4, chooseCategoryImage(tile));
                }
                else if(i==1 && j == 5)
                {
                    displayImage(livingroom_1_5, chooseCategoryImage(tile));
                }
                else if(i==2 && j == 2)
                {
                    displayImage(livingroom_2_2, chooseCategoryImage(tile));
                }
                else if(i==2 && j == 3)
                {
                    displayImage(livingroom_2_3, chooseCategoryImage(tile));
                }
                else if(i==2 && j == 4)
                {
                    displayImage(livingroom_2_4, chooseCategoryImage(tile));
                }
                else if(i==2 && j == 5)
                {
                    displayImage(livingroom_2_5, chooseCategoryImage(tile));
                }
                else if(i==2 && j == 6)
                {
                    displayImage(livingroom_2_6, chooseCategoryImage(tile));
                }
                else if(i==3 && j == 1)
                {
                    displayImage(livingroom_3_1, chooseCategoryImage(tile));
                }
                else if(i==3 && j == 2)
                {
                    displayImage(livingroom_3_2, chooseCategoryImage(tile));
                }
                else if(i==3 && j == 3)
                {
                    displayImage(livingroom_3_3, chooseCategoryImage(tile));
                }
                else if(i==3 && j == 4)
                {
                    displayImage(livingroom_3_4, chooseCategoryImage(tile));
                }
                else if(i==3 && j == 5)
                {
                    displayImage(livingroom_3_5, chooseCategoryImage(tile));
                }
                else if(i==3 && j == 6)
                {
                    displayImage(livingroom_3_6, chooseCategoryImage(tile));
                }
                else if(i==3 && j == 7)
                {
                    displayImage(livingroom_3_7, chooseCategoryImage(tile));
                }
                else if(i==3 && j == 8)
                {
                    displayImage(livingroom_3_8, chooseCategoryImage(tile));
                }
                else if(i==4 && j == 0)
                {
                    displayImage(livingroom_4_0, chooseCategoryImage(tile));
                }
                else if(i==4 && j == 1)
                {
                    displayImage(livingroom_4_1, chooseCategoryImage(tile));
                }
                else if(i==4 && j == 2)
                {
                    displayImage(livingroom_4_2, chooseCategoryImage(tile));
                }
                else if(i==4 && j == 3)
                {
                    displayImage(livingroom_4_3, chooseCategoryImage(tile));
                }
                else if(i==4 && j == 4)
                {
                    displayImage(livingroom_4_4, chooseCategoryImage(tile));
                }
                else if(i==4 && j == 5)
                {
                    displayImage(livingroom_4_5, chooseCategoryImage(tile));
                }
                else if(i==4 && j == 6)
                {
                    displayImage(livingroom_4_6, chooseCategoryImage(tile));
                }
                else if(i==4 && j == 7)
                {
                    displayImage(livingroom_4_7, chooseCategoryImage(tile));
                }
                else if(i==4 && j == 8)
                {
                    displayImage(livingroom_4_8, chooseCategoryImage(tile));
                }
                else if(i==5 && j == 0)
                {
                    displayImage(livingroom_5_0, chooseCategoryImage(tile));
                }
                else if(i==5 && j == 1)
                {
                    displayImage(livingroom_5_1, chooseCategoryImage(tile));
                }
                else if(i==5 && j == 2)
                {
                    displayImage(livingroom_5_2, chooseCategoryImage(tile));
                }
                else if(i==5 && j == 3)
                {
                    displayImage(livingroom_5_3, chooseCategoryImage(tile));
                }
                else if(i==5 && j == 4)
                {
                    displayImage(livingroom_5_4, chooseCategoryImage(tile));
                }
                else if(i==5 && j == 5)
                {
                    displayImage(livingroom_5_5, chooseCategoryImage(tile));
                }
                else if(i==5 && j == 6)
                {
                    displayImage(livingroom_5_6, chooseCategoryImage(tile));
                }
                else if(i==5 && j == 7)
                {
                    displayImage(livingroom_5_7, chooseCategoryImage(tile));
                }
                else if(i==6 && j == 2)
                {
                    displayImage(livingroom_6_2, chooseCategoryImage(tile));
                }
                else if(i==6 && j == 3)
                {
                    displayImage(livingroom_6_3, chooseCategoryImage(tile));
                }
                else if(i==6 && j == 4)
                {
                    displayImage(livingroom_6_4, chooseCategoryImage(tile));
                }
                else if(i==6 && j == 5)
                {
                    displayImage(livingroom_6_5, chooseCategoryImage(tile));
                }
                else if(i==6 && j == 6)
                {
                    displayImage(livingroom_6_6, chooseCategoryImage(tile));
                }
                else if(i==7 && j == 3)
                {
                    displayImage(livingroom_7_3, chooseCategoryImage(tile));
                }
                else if(i==7 && j == 4)
                {
                    displayImage(livingroom_7_4, chooseCategoryImage(tile));
                }
                else if(i==7 && j == 5)
                {
                    displayImage(livingroom_7_5, chooseCategoryImage(tile));
                }
                else if(i==8 && j == 4)
                {
                    displayImage(livingroom_8_4, chooseCategoryImage(tile));
                }
                else if(i==8 && j == 5)
                {
                    displayImage(livingroom_8_5, chooseCategoryImage(tile));
                }
            }
        }
    }

    public void displayBookshelf(){
        BookShelf shelf = player.getPlayerBookshelf();

        for(int i=0; i<6; i++)
        {
            for(int  j=0; j<5; j++)
            {
                ItemTile tile = shelf.getTile(i,j);

                if(i==0 && j == 0)
                {
                    displayImage(shelf_0_0, chooseCategoryImage(tile));
                }
                else if(i==0 && j == 1)
                {
                    displayImage(shelf_0_1, chooseCategoryImage(tile));
                }
                else if(i==0 && j == 2)
                {
                    displayImage(shelf_0_2, chooseCategoryImage(tile));
                }
                else if(i==0 && j == 3)
                {
                    displayImage(shelf_0_3, chooseCategoryImage(tile));
                }
                else if(i==0 && j == 4)
                {
                    displayImage(shelf_0_4, chooseCategoryImage(tile));
                }
                else if(i==1 && j == 0)
                {
                    displayImage(shelf_1_0, chooseCategoryImage(tile));
                }
                else if(i==1 && j == 1)
                {
                    displayImage(shelf_1_1, chooseCategoryImage(tile));
                }
                else if(i==1 && j == 2)
                {
                    displayImage(shelf_1_2, chooseCategoryImage(tile));
                }
                else if(i==1 && j == 3)
                {
                    displayImage(shelf_1_3, chooseCategoryImage(tile));
                }
                else if(i==1 && j == 4)
                {
                    displayImage(shelf_1_4, chooseCategoryImage(tile));
                }
                else if(i==2 && j == 0)
                {
                    displayImage(shelf_2_0, chooseCategoryImage(tile));
                }
                else if(i==2 && j == 1)
                {
                    displayImage(shelf_2_1, chooseCategoryImage(tile));
                }
                else if(i==2 && j == 2)
                {
                    displayImage(shelf_2_2, chooseCategoryImage(tile));
                }
                else if(i==2 && j == 3)
                {
                    displayImage(shelf_2_3, chooseCategoryImage(tile));
                }
                else if(i==2 && j == 4)
                {
                    displayImage(shelf_2_4, chooseCategoryImage(tile));
                }
                else if(i==3 && j == 0)
                {
                    displayImage(shelf_3_0, chooseCategoryImage(tile));
                }
                else if(i==3 && j == 1)
                {
                    displayImage(shelf_3_1, chooseCategoryImage(tile));
                }
                else if(i==3 && j == 2)
                {
                    displayImage(shelf_3_2, chooseCategoryImage(tile));
                }
                else if(i==3 && j == 3)
                {
                    displayImage(shelf_3_3, chooseCategoryImage(tile));
                }
                else if(i==3 && j == 4)
                {
                    displayImage(shelf_3_4, chooseCategoryImage(tile));
                }
                else if(i==4 && j == 0)
                {
                    displayImage(shelf_4_0, chooseCategoryImage(tile));
                }
                else if(i==4 && j == 1)
                {
                    displayImage(shelf_4_1, chooseCategoryImage(tile));
                }
                else if(i==4 && j == 2)
                {
                    displayImage(shelf_4_2, chooseCategoryImage(tile));
                }
                else if(i==4 && j == 3)
                {
                    displayImage(shelf_4_3, chooseCategoryImage(tile));
                }
                else if(i==4 && j == 4)
                {
                    displayImage(shelf_4_4, chooseCategoryImage(tile));
                }
                else if(i==5 && j == 0)
                {
                    displayImage(shelf_5_0, chooseCategoryImage(tile));
                }
                else if(i==5 && j == 1)
                {
                    displayImage(shelf_5_1, chooseCategoryImage(tile));
                }
                else if(i==5 && j == 2)
                {
                    displayImage(shelf_5_2, chooseCategoryImage(tile));
                }
                else if(i==5 && j == 3)
                {
                    displayImage(shelf_5_3, chooseCategoryImage(tile));
                }
                else if(i==5 && j == 4)
                {
                    displayImage(shelf_5_4, chooseCategoryImage(tile));
                }
            }
        }
    }

    public void displayContainer(){

    }

    public void displayImage(ImageView view, Image image){
        view.setImage(image);
    }

    public Image chooseCategoryImage(ItemTile tile){

        Image image = null;
        switch(tile.getCategory()){
            case GAMES:
            {
                image = new Image(getClass().getResourceAsStream("Giochi.png"));
                break;
            }
            case CATS:
            {
                image = new Image(getClass().getResourceAsStream("Gatti.png"));
                break;
            }
            case BOOKS:
            {
                image = new Image(getClass().getResourceAsStream("Libri.png"));
                break;
            }
            case FRAMES:
            {
                image = new Image(getClass().getResourceAsStream("Cornici.png"));
                break;
            }
            case PLANTS:
            {
                image = new Image(getClass().getResourceAsStream("Piante.png"));
                break;
            }
            case TROPHIES: {
                image = new Image(getClass().getResourceAsStream("Trofei.png"));
                break;
            }
        }
        return image;
    }

    public void goToGoals(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("goals.fxml"));
        root = loader.load();
        GoalsController goalsController = loader.getController();
        goalsController.displayPersonalGoal();
        goalsController.displayCommonGoal();
        goalsController.displayPoints();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void returnToMenu(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
        root = loader.load();
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setResponse(ServerMessage response){
        this.response=response;
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

}
