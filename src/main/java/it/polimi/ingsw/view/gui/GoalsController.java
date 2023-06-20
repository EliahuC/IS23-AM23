package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.model.Game;
import  it.polimi.ingsw.model.board.goalCards.CommonGoalCard;
import  it.polimi.ingsw.model.board.LivingRoom;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

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
    private GameControllerGUI gameControllerGUI;

    public void goToLivingroom(ActionEvent event) throws IOException {

        File file = new File("src/main/resources/com/example/is23am23/game.fxml");
        URL url = file.toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        GameControllerGUI gameControllerGUI = loader.getController();
        //gameControllerGUI.displayBookshelf();
        //gameControllerGUI.displayContainer();
        gameControllerGUI.displayLivingroom();
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void displayPoints(){

        int numPlayers = gameControllerGUI.getPlayers().size();
        int numCompleted1 = gameControllerGUI.getLivingRoom().getCommonGoalCard1().getNumCompleted();
        int numCompleted2 = gameControllerGUI.getLivingRoom().getCommonGoalCard2().getNumCompleted();

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

    public void displayPersonalGoal(){

        int personalCard = gameControllerGUI.getSeed();

        switch (personalCard) {
            case 1:
            {
                Image image = new Image(getClass().getResourceAsStream("Personal_Goals.png"));
                displayImage(personalGoal, image);
                break;
            }
            case 2:
            {
                Image image = new Image(getClass().getResourceAsStream("Personal_Goals2.png"));
                displayImage(personalGoal, image);
                break;
            }
            case 3:
            {
                Image image = new Image(getClass().getResourceAsStream("Personal_Goals3.png"));
                displayImage(personalGoal, image);
                break;
            }
            case 4:
            {
                Image image = new Image(getClass().getResourceAsStream("Personal_Goals4.png"));
                displayImage(personalGoal, image);
                break;
            }
            case 5:
            {
                Image image = new Image(getClass().getResourceAsStream("Personal_Goals5.png"));
                displayImage(personalGoal, image);
                break;
            }
            case 6:
            {
                Image image = new Image(getClass().getResourceAsStream("Personal_Goals6.png"));
                displayImage(personalGoal, image);
                break;
            }
            case 7:
            {
                Image image = new Image(getClass().getResourceAsStream("Personal_Goals7.png"));
                displayImage(personalGoal, image);
                break;
            }
            case 8:
            {
                Image image = new Image(getClass().getResourceAsStream("Personal_Goals8.png"));
                displayImage(personalGoal, image);
                break;
            }
            case 9:
            {
                Image image = new Image(getClass().getResourceAsStream("Personal_Goals9.png"));
                displayImage(personalGoal, image);
                break;
            }
            case 10:
            {
                Image image = new Image(getClass().getResourceAsStream("Personal_Goals10.png"));
                displayImage(personalGoal, image);
                break;
            }
            case 11:
            {
                Image image = new Image(getClass().getResourceAsStream("Personal_Goals11.png"));
                displayImage(commonGoal1, image);
                break;
            }
            case 12:
            {
                Image image = new Image(getClass().getResourceAsStream("Personal_Goals12.png.jpg"));
                displayImage(personalGoal, image);
                break;
            }
        }

    }

    public void displayCommonGoal() {

        int card1 = gameControllerGUI.getLivingRoom().getIdCGC1();
        int card2 = gameControllerGUI.getLivingRoom().getIdCGC2();

        switch (card1) {
            case 1: {
                Image image = new Image(getClass().getResourceAsStream("4.jpg"));
                displayImage(commonGoal1, image);
                break;
            }
            case 2: {
                Image image = new Image(getClass().getResourceAsStream("8.jpg"));
                displayImage(commonGoal1, image);
                break;
            }
            case 3: {
                Image image = new Image(getClass().getResourceAsStream("3.jpg"));
                displayImage(commonGoal1, image);
                break;
            }
            case 4: {
                Image image = new Image(getClass().getResourceAsStream("1.jpg"));
                displayImage(commonGoal1, image);
                break;
            }
            case 5: {
                Image image = new Image(getClass().getResourceAsStream("5.jpg"));
                displayImage(commonGoal1, image);
                break;
            }
            case 6: {
                Image image = new Image(getClass().getResourceAsStream("9.jpg"));
                displayImage(commonGoal1, image);
                break;
            }
            case 7: {
                Image image = new Image(getClass().getResourceAsStream("11.jpg"));
                displayImage(commonGoal1, image);
                break;
            }
            case 8: {
                Image image = new Image(getClass().getResourceAsStream("7.jpg"));
                displayImage(commonGoal1, image);
                break;
            }
            case 9: {
                Image image = new Image(getClass().getResourceAsStream("2.jpg"));
                displayImage(commonGoal1, image);
                break;
            }
            case 10: {
                Image image = new Image(getClass().getResourceAsStream("6.jpg"));
                displayImage(commonGoal1, image);
                break;
            }
            case 11: {
                Image image = new Image(getClass().getResourceAsStream("10.jpg"));
                displayImage(commonGoal1, image);
                break;
            }
            case 12: {
                Image image = new Image(getClass().getResourceAsStream("12.jpg"));
                displayImage(commonGoal1, image);
                break;
            }
        }

        switch (card2) {
            case 1: {
                Image image = new Image(getClass().getResourceAsStream("4.jpg"));
                displayImage(commonGoal2, image);
                break;
            }
            case 2: {
                Image image = new Image(getClass().getResourceAsStream("8.jpg"));
                displayImage(commonGoal2, image);
                break;
            }
            case 3: {
                Image image = new Image(getClass().getResourceAsStream("3.jpg"));
                displayImage(commonGoal2, image);
                break;
            }
            case 4: {
                Image image = new Image(getClass().getResourceAsStream("1.jpg"));
                displayImage(commonGoal2, image);
                break;
            }
            case 5: {
                Image image = new Image(getClass().getResourceAsStream("5.jpg"));
                displayImage(commonGoal2, image);
                break;
            }
            case 6: {
                Image image = new Image(getClass().getResourceAsStream("9.jpg"));
                displayImage(commonGoal2, image);
                break;
            }
            case 7: {
                Image image = new Image(getClass().getResourceAsStream("11.jpg"));
                displayImage(commonGoal2, image);
                break;
            }
            case 8: {
                Image image = new Image(getClass().getResourceAsStream("7.jpg"));
                displayImage(commonGoal2, image);
                break;
            }
            case 9: {
                Image image = new Image(getClass().getResourceAsStream("2.jpg"));
                displayImage(commonGoal2, image);
                break;
            }
            case 10: {
                Image image = new Image(getClass().getResourceAsStream("6.jpg"));
                displayImage(commonGoal2, image);
                break;
            }
            case 11: {
                Image image = new Image(getClass().getResourceAsStream("10.jpg"));
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
    public void setGameControllerGUI(GameControllerGUI gameControllerGUI){
        this.gameControllerGUI=gameControllerGUI;
    }

    public GameControllerGUI getGameControllerGUI() {
        return gameControllerGUI;
    }
}
