package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.controller.GameController;
import  it.polimi.ingsw.model.board.goalCards.CommonGoalCard;
import  it.polimi.ingsw.model.board.LivingRoom;


import it.polimi.ingsw.model.player.Player;
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
import java.util.ArrayList;

public class GoalsController {

    @FXML
    ImageView personalGoal;
    @FXML
    ImageView commonGoal1;
    @FXML
    ImageView commonGoal2;
    @FXML
    ImageView points1;
    @FXML
    ImageView points2;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private LivingRoom livingRoom;
    private MenuController menuController;
    private ArrayList<Player> playersList;


    public void goToLivingroom(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("src/main/resources/com/example/is23am23/game.fxml"));
        root = loader.load();
        GameControllerGUI gameControllerGUI = loader.getController();
        gameControllerGUI.displayBookshelf();
        gameControllerGUI.displayContainer();
        gameControllerGUI.displayLivingroom();
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void displayPoints(){

        //Inserire il numero di giocatori della lobby, passare il parametro per n° volte goalcard completata(DA INSERIRE)
        int numPlayers = playersList.size();
        int numCompleted1 = livingRoom.getCommonGoalCard1().getNumCompleted();
        int numCompleted2 = livingRoom.getCommonGoalCard2().getNumCompleted();

        // score common goal card 1
        switch (numPlayers) {
            case 2: {
                switch (numCompleted1) {
                    case 0:
                    {
                        Image image = new Image(getClass().getResourceAsStream("scoring_8.jpg"));
                        displayImage(points1, image);
                        break;
                    }
                    case 1:
                    {
                        Image image = new Image(getClass().getResourceAsStream("scoring_4.jpg"));
                        displayImage(points1, image);
                        break;
                    }
                    default:
                    {
                        Image image = new Image(getClass().getResourceAsStream("scoring_back_EMPTY.jpg"));
                        displayImage(points1, image);
                        break;
                    }
                }
                break;
            }
            case 3: {
                switch (numCompleted1) {
                    case 0:
                    {
                        Image image = new Image(getClass().getResourceAsStream("scoring_8.jpg"));
                        displayImage(points1, image);
                        break;
                    }
                    case 1:
                    {
                        Image image = new Image(getClass().getResourceAsStream("scoring_6.jpg"));
                        displayImage(points1, image);
                        break;
                    }
                    case 2:
                    {
                        Image image = new Image(getClass().getResourceAsStream("scoring_4.jpg"));
                        displayImage(points1, image);
                        break;
                    }
                    default:
                    {
                        Image image = new Image(getClass().getResourceAsStream("scoring_back_EMPTY.jpg"));
                        displayImage(points1, image);
                        break;
                    }
                }
                break;
            }
            case 4: {
                switch (numCompleted1) {
                    case 0:
                    {
                        Image image = new Image(getClass().getResourceAsStream("scoring_8.jpg"));
                        displayImage(points1, image);
                        break;
                    }
                    case 1:
                    {
                        Image image = new Image(getClass().getResourceAsStream("scoring_6.jpg"));
                        displayImage(points1, image);
                        break;
                    }
                    case 2:
                    {
                        Image image = new Image(getClass().getResourceAsStream("scoring_4.jpg"));
                        displayImage(points1, image);
                        break;
                    }
                    case 3:  {
                        Image image = new Image(getClass().getResourceAsStream("scoring_2.jpg"));
                        displayImage(points1, image);
                        break;
                    }
                    default:
                    {
                        Image image = new Image(getClass().getResourceAsStream("scoring_back_EMPTY.jpg"));
                        displayImage(points1, image);
                        break;
                    }
                }
                break;
            }
        }

        // score common goal card 2
        switch (numPlayers) {
            case 2: {
                switch (numCompleted2) {
                    case 0:
                    {
                        Image image = new Image(getClass().getResourceAsStream("scoring_8.jpg"));
                        displayImage(points2, image);
                        break;
                    }
                    case 1:
                    {
                        Image image = new Image(getClass().getResourceAsStream("scoring_4.jpg"));
                        displayImage(points2, image);
                        break;
                    }
                    default:
                    {
                        Image image = new Image(getClass().getResourceAsStream("scoring_back_EMPTY.jpg"));
                        displayImage(points2, image);
                        break;
                    }
                }
                break;
            }
            case 3: {
                switch (numCompleted2) {
                    case 0:
                    {
                        Image image = new Image(getClass().getResourceAsStream("scoring_8.jpg"));
                        displayImage(points2, image);
                        break;
                    }
                    case 1:
                    {
                        Image image = new Image(getClass().getResourceAsStream("scoring_6.jpg"));
                        displayImage(points2, image);
                        break;
                    }
                    case 2:
                    {
                        Image image = new Image(getClass().getResourceAsStream("scoring_4.jpg"));
                        displayImage(points2, image);
                        break;
                    }
                    default:
                    {
                        Image image = new Image(getClass().getResourceAsStream("scoring_back_EMPTY.jpg"));
                        displayImage(points2, image);
                        break;
                    }
                }
                break;
            }
            case 4: {
                switch (numCompleted2) {
                    case 0:
                    {
                        Image image = new Image(getClass().getResourceAsStream("scoring_8.jpg"));
                        displayImage(points2, image);
                        break;
                    }
                    case 1:
                    {
                        Image image = new Image(getClass().getResourceAsStream("scoring_6.jpg"));
                        displayImage(points2, image);
                        break;
                    }
                    case 2:
                    {
                        Image image = new Image(getClass().getResourceAsStream("scoring_4.jpg"));
                        displayImage(points2, image);
                        break;
                    }
                    case 3:  {
                        Image image = new Image(getClass().getResourceAsStream("scoring_2.jpg"));
                        displayImage(points2, image);
                        break;
                    }
                    default:
                    {
                        Image image = new Image(getClass().getResourceAsStream("scoring_back_EMPTY.jpg"));
                        displayImage(points2, image);
                        break;
                    }
                }
                break;
            }
        }
    }

    public void setPlayersList(ArrayList<Player> list){

        playersList = list;

    }

    public void displayPersonalGoal(){

        for (Player player:  playersList) {
            if((player.getNickName().equals(menuController.getNickname()))){

                int personalCard = player.getPersonalGoalCard().getNumeroCarta();
                switch (personalCard) {
                    case 1: {
                        Image image = new Image(getClass().getResourceAsStream("Personal_Goals.png"));
                        displayImage(personalGoal, image);
                        break;
                    }
                    case 2: {
                        Image image = new Image(getClass().getResourceAsStream("Personal_Goals2.png"));
                        displayImage(personalGoal, image);
                        break;
                    }
                    case 3: {
                        Image image = new Image(getClass().getResourceAsStream("Personal_Goals3.png"));
                        displayImage(personalGoal, image);
                        break;
                    }
                    case 4: {
                        Image image = new Image(getClass().getResourceAsStream("Personal_Goals4.png"));
                        displayImage(personalGoal, image);
                        break;
                    }
                    case 5: {
                        Image image = new Image(getClass().getResourceAsStream("Personal_Goals5.png"));
                        displayImage(personalGoal, image);
                        break;
                    }
                    case 6: {
                        Image image = new Image(getClass().getResourceAsStream("Personal_Goals6.png"));
                        displayImage(personalGoal, image);
                        break;
                    }
                    case 7: {
                        Image image = new Image(getClass().getResourceAsStream("Personal_Goals7.png"));
                        displayImage(personalGoal, image);
                        break;
                    }
                    case 8: {
                        Image image = new Image(getClass().getResourceAsStream("Personal_Goals8.png"));
                        displayImage(personalGoal, image);
                        break;
                    }
                    case 9: {
                        Image image = new Image(getClass().getResourceAsStream("Personal_Goals9.png"));
                        displayImage(personalGoal, image);
                        break;
                    }
                    case 10: {
                        Image image = new Image(getClass().getResourceAsStream("Personal_Goals10.png"));
                        displayImage(personalGoal, image);
                        break;
                    }
                    case 11: {
                        Image image = new Image(getClass().getResourceAsStream("Personal_Goals11.png"));
                        displayImage(commonGoal1, image);
                        break;
                    }
                    case 12: {
                        Image image = new Image(getClass().getResourceAsStream("Personal_Goals12.png.jpg"));
                        displayImage(personalGoal, image);
                        break;
                    }
                    default:
                        System.out.println("è colpa di eliahu");
                        break;
                }
            }
        }
    }

    public void displayCommonGoal() {

        int card1 = livingRoom.getIdCGC1();
        int card2 = livingRoom.getIdCGC2();

        switch (card1) {
            case 1: {
                Image image = new Image(getClass().getResourceAsStream("1.jpg"));
                displayImage(commonGoal1, image);
                break;
            }
            case 2: {
                Image image = new Image(getClass().getResourceAsStream("2.jpg"));
                displayImage(commonGoal1, image);
                break;
            }
            case 3: {
                Image image = new Image(getClass().getResourceAsStream("3.jpg"));
                displayImage(commonGoal1, image);
                break;
            }
            case 4: {
                Image image = new Image(getClass().getResourceAsStream("4.jpg"));
                displayImage(commonGoal1, image);
                break;
            }
            case 5: {
                Image image = new Image(getClass().getResourceAsStream("5.jpg"));
                displayImage(commonGoal1, image);
                break;
            }
            case 6: {
                Image image = new Image(getClass().getResourceAsStream("6.jpg"));
                displayImage(commonGoal1, image);
                break;
            }
            case 7: {
                Image image = new Image(getClass().getResourceAsStream("7.jpg"));
                displayImage(commonGoal1, image);
                break;
            }
            case 8: {
                Image image = new Image(getClass().getResourceAsStream("8.jpg"));
                displayImage(commonGoal1, image);
                break;
            }
            case 9: {
                Image image = new Image(getClass().getResourceAsStream("9.jpg"));
                displayImage(commonGoal1, image);
                break;
            }
            case 10: {
                Image image = new Image(getClass().getResourceAsStream("10.jpg"));
                displayImage(commonGoal1, image);
                break;
            }
            case 11: {
                Image image = new Image(getClass().getResourceAsStream("11.jpg"));
                displayImage(commonGoal1, image);
                break;
            }
            case 12: {
                Image image = new Image(getClass().getResourceAsStream("12.jpg"));
                displayImage(commonGoal1, image);
                break;
            }
            default: System.out.println("è colpa di eliahu");
                break;
        }

        switch (card2) {
            case 1: {
                Image image = new Image(getClass().getResourceAsStream("1.jpg"));
                displayImage(commonGoal2, image);
                break;
            }
            case 2: {
                Image image = new Image(getClass().getResourceAsStream("2.jpg"));
                displayImage(commonGoal2, image);
                break;
            }
            case 3: {
                Image image = new Image(getClass().getResourceAsStream("3.jpg"));
                displayImage(commonGoal2, image);
                break;
            }
            case 4: {
                Image image = new Image(getClass().getResourceAsStream("4.jpg"));
                displayImage(commonGoal2, image);
                break;
            }
            case 5: {
                Image image = new Image(getClass().getResourceAsStream("5.jpg"));
                displayImage(commonGoal2, image);
                break;
            }
            case 6: {
                Image image = new Image(getClass().getResourceAsStream("6.jpg"));
                displayImage(commonGoal2, image);
                break;
            }
            case 7: {
                Image image = new Image(getClass().getResourceAsStream("7.jpg"));
                displayImage(commonGoal2, image);
                break;
            }
            case 8: {
                Image image = new Image(getClass().getResourceAsStream("8.jpg"));
                displayImage(commonGoal2, image);
                break;
            }
            case 9: {
                Image image = new Image(getClass().getResourceAsStream("9.jpg"));
                displayImage(commonGoal2, image);
                break;
            }
            case 10: {
                Image image = new Image(getClass().getResourceAsStream("10.jpg"));
                displayImage(commonGoal2, image);
                break;
            }
            case 11: {
                Image image = new Image(getClass().getResourceAsStream("11.jpg"));
                displayImage(commonGoal2, image);
                break;
            }
            case 12: {
                Image image = new Image(getClass().getResourceAsStream("12.jpg"));
                displayImage(commonGoal2, image);
                break;
            }
        }
    }

    public void displayImage(ImageView commonGoal1,Image image){
        commonGoal1.setImage(image);
    }

    public void setLivingroom(LivingRoom livingRoom){
        this.livingRoom = livingRoom;
    }
}
