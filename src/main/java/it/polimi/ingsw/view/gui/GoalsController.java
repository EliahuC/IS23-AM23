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
    private Image image;
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
                        File file = new File("com/example/is23am23/scoring_8.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(points1, image);

                        //Image image = new Image(getClass().getResourceAsStream("scoring_8.jpg"));
                        break;
                    }
                    case 1:
                    {
                        File file = new File("com/example/is23am23/scoring_4.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(points1, image);
                       // Image image = new Image(getClass().getResourceAsStream("scoring_4.jpg"));
                        break;
                    }
                    default:
                    {
                        File file = new File("com/example/is23am23/scoring_back_EMPTY.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(points1, image);
                        //Image image = new Image(getClass().getResourceAsStream("scoring_back_EMPTY.jpg"));
                        break;
                    }
                }
                break;
            }
            case 3: {
                switch (numCompleted1) {
                    case 0:
                    {
                        File file = new File("com/example/is23am23/scoring_8.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(points1, image);
                      //  Image image = new Image(getClass().getResourceAsStream("scoring_8.jpg"));
                        break;
                    }
                    case 1:
                    {
                        File file = new File("com/example/is23am23/scoring_6.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(points1, image);
                        //Image image = new Image(getClass().getResourceAsStream("scoring_6.jpg"));
                        break;
                    }
                    case 2:
                    {
                        File file = new File("com/example/is23am23/scoring_4.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(points1, image);
                        //Image image = new Image(getClass().getResourceAsStream("scoring_4.jpg"));
                        break;
                    }
                    default:
                    {
                        File file = new File("com/example/is23am23/scoring_back_EMPTY.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(points1, image);
                       // Image image = new Image(getClass().getResourceAsStream("scoring_back_EMPTY.jpg"));
                        break;
                    }
                }
                break;
            }
            case 4: {
                switch (numCompleted1) {
                    case 0:
                    {
                        File file = new File("com/example/is23am23/scoring_8.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(points1, image);
                        //Image image = new Image(getClass().getResourceAsStream("scoring_8.jpg"));
                        break;
                    }
                    case 1:
                    {
                        File file = new File("com/example/is23am23/scoring_6.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(points1, image);
                        //Image image = new Image(getClass().getResourceAsStream("scoring_6.jpg"));
                        break;
                    }
                    case 2:
                    {
                        File file = new File("com/example/is23am23/scoring_4.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(points1, image);
                        //Image image = new Image(getClass().getResourceAsStream("scoring_4.jpg"));
                        break;
                    }
                    case 3:  {
                        File file = new File("com/example/is23am23/scoring_2.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(points1, image);
                    // Image image = new Image(getClass().getResourceAsStream("scoring_2.jpg"));
                        break;
                    }
                    default:
                    {
                        File file = new File("com/example/is23am23/scoring_back_EMPTY.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(points1, image);
                        //Image image = new Image(getClass().getResourceAsStream("scoring_back_EMPTY.jpg"));
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
                        File file = new File("com/example/is23am23/scoring_8.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(points2, image);
                        //Image image = new Image(getClass().getResourceAsStream("scoring_8.jpg"));
                        break;
                    }
                    case 1:
                    {
                        File file = new File("com/example/is23am23/scoring_4.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(points2, image);
                        //Image image = new Image(getClass().getResourceAsStream("scoring_4.jpg"));
                        break;
                    }
                    default:
                    {
                        File file = new File("com/example/is23am23/scoring_back_EMPTY.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(points2, image);
                       // Image image = new Image(getClass().getResourceAsStream("scoring_back_EMPTY.jpg"));
                        break;
                    }
                }
                break;
            }
            case 3: {
                switch (numCompleted2) {
                    case 0:
                    {
                        File file = new File("com/example/is23am23/scoring_8.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(points2, image);
                      //  Image image = new Image(getClass().getResourceAsStream("scoring_8.jpg"));
                        break;
                    }
                    case 1:
                    {
                        File file = new File("com/example/is23am23/scoring_6.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(points2, image);
                        //Image image = new Image(getClass().getResourceAsStream("scoring_6.jpg"));
                        break;
                    }
                    case 2:
                    {
                        File file = new File("com/example/is23am23/scoring_4.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(points2, image);
                      //  Image image = new Image(getClass().getResourceAsStream("scoring_4.jpg"));
                        break;
                    }
                    default:
                    {
                        File file = new File("com/example/is23am23/scoring_back_EMPTY.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(points2, image);
                       // Image image = new Image(getClass().getResourceAsStream("scoring_back_EMPTY.jpg"));
                        break;
                    }
                }
                break;
            }
            case 4: {
                switch (numCompleted2) {
                    case 0:
                    {
                        File file = new File("com/example/is23am23/scoring_8.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(points2, image);
                        //Image image = new Image(getClass().getResourceAsStream("scoring_8.jpg"));
                        break;
                    }
                    case 1:
                    {
                        File file = new File("com/example/is23am23/scoring_6.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(points2, image);
                        //Image image = new Image(getClass().getResourceAsStream("scoring_6.jpg"));
                        break;
                    }
                    case 2:
                    {
                        File file = new File("com/example/is23am23/scoring_4.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(points2, image);
                       // Image image = new Image(getClass().getResourceAsStream("scoring_4.jpg"));
                        break;
                    }
                    case 3:  {
                        File file = new File("com/example/is23am23/scoring_2.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(points2, image);
                  //      Image image = new Image(getClass().getResourceAsStream("scoring_2.jpg"));
                        break;
                    }
                    default:
                    {
                        File file = new File("com/example/is23am23/scoring_back_EMPTY.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(points2, image);
                //        Image image = new Image(getClass().getResourceAsStream("scoring_back_EMPTY.jpg"));
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
                File file = new File("com/example/is23am23/Personal_Goals.png");
                image = new Image(String.valueOf(file));
                displayImage(personalGoal, image);
                //Image image = new Image(getClass().getResourceAsStream("com/example/is23am23/Personal_Goals.png"));

                break;
            }
            case 2:
            {
                File file = new File("com/example/is23am23/Personal_Goals2.png");
                image = new Image(String.valueOf(file));
                displayImage(personalGoal, image);
                //Image image = new Image(getClass().getResourceAsStream("Personal_Goals2.png"));
                break;
            }
            case 3:
            {
                File file = new File("com/example/is23am23/Personal_Goals3.png");
                image = new Image(String.valueOf(file));
                displayImage(personalGoal, image);
                //Image image = new Image(getClass().getResourceAsStream("Personal_Goals3.png"));
                break;
            }
            case 4:
            {
                File file = new File("com/example/is23am23/Personal_Goals4.png");
                image = new Image(String.valueOf(file));
                displayImage(personalGoal, image);
                //Image image = new Image(getClass().getResourceAsStream("Personal_Goals4.png"));

                break;
            }
            case 5:
            {
                File file = new File("com/example/is23am23/Personal_Goals5.png");
                image = new Image(String.valueOf(file));
                displayImage(personalGoal, image);
                //Image image = new Image(getClass().getResourceAsStream("Personal_Goals5.png"));
                break;
            }
            case 6:
            {
                File file = new File("com/example/is23am23/Personal_Goals6.png");
                image = new Image(String.valueOf(file));
                displayImage(personalGoal, image);
                //Image image = new Image(getClass().getResourceAsStream("Personal_Goals6.png"));
                break;
            }
            case 7:
            {
                File file = new File("com/example/is23am23/Personal_Goals7.png");
                image = new Image(String.valueOf(file));
                displayImage(personalGoal, image);
                //Image image = new Image(getClass().getResourceAsStream("Personal_Goals7.png"));
                break;
            }
            case 8:
            {
                File file = new File("com/example/is23am23/Personal_Goals8.png");
                image = new Image(String.valueOf(file));
                displayImage(personalGoal, image);
                //Image image = new Image(getClass().getResourceAsStream("Personal_Goals8.png"));
                break;
            }
            case 9:
            {
                File file = new File("com/example/is23am23/Personal_Goals9.png");
                image = new Image(String.valueOf(file));
                displayImage(personalGoal, image);
                //Image image = new Image(getClass().getResourceAsStream("Personal_Goals9.png"));
                break;
            }
            case 10:
            {
                File file = new File("com/example/is23am23/Personal_Goals10.png");
                image = new Image(String.valueOf(file));
                displayImage(personalGoal, image);
                //Image image = new Image(getClass().getResourceAsStream("Personal_Goals10.png"));
                break;
            }
            case 11:
            {
                File file = new File("com/example/is23am23/Personal_Goals11.png");
                image = new Image(String.valueOf(file));
                displayImage(personalGoal, image);
                //Image image = new Image(getClass().getResourceAsStream("Personal_Goals11.png"));
                break;
            }
            case 12:
            {
                File file = new File("com/example/is23am23/Personal_Goals12.png");
                image = new Image(String.valueOf(file));
                displayImage(personalGoal, image);
                //Image image = new Image(getClass().getResourceAsStream("Personal_Goals12.png"));
                break;
            }
        }

    }

    public void displayCommonGoal() {

        int card1 = gameControllerGUI.getLivingRoom().getIdCGC1();
        int card2 = gameControllerGUI.getLivingRoom().getIdCGC2();

        switch (card1) {
            case 1: {
                File file = new File("com/example/is23am23/4.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoal1, image);
                //Image image = new Image(getClass().getResourceAsStream("4.jpg"));
                break;
            }
            case 2: {
                File file = new File("com/example/is23am23/8.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoal1, image);
              //  Image image = new Image(getClass().getResourceAsStream("8.jpg"));
                break;
            }
            case 3: {
                File file = new File("com/example/is23am23/3.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoal1, image);
                break;
            }
            case 4: {
                File file = new File("com/example/is23am23/1.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoal1, image);
                break;
            }
            case 5: {
                File file = new File("com/example/is23am23/5.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoal1, image);
                break;
            }
            case 6: {
                File file = new File("com/example/is23am23/9.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoal1, image);
                break;
            }
            case 7: {
                File file = new File("com/example/is23am23/11.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoal1, image);
                break;
            }
            case 8: {
                File file = new File("com/example/is23am23/7.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoal1, image);
                break;
            }
            case 9: {
                File file = new File("com/example/is23am23/2.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoal1, image);
                break;
            }
            case 10: {
                File file = new File("com/example/is23am23/6.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoal1, image);
                break;
            }
            case 11: {
                File file = new File("com/example/is23am23/10.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoal1, image);
                break;
            }
            case 12: {
                File file = new File("com/example/is23am23/12.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoal1, image);
                break;
            }
        }

        switch (card2) {
            case 1: {
                File file = new File("com/example/is23am23/4.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoal2, image);
                break;
            }
            case 2: {
                File file = new File("com/example/is23am23/8.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoal2, image);
                break;
            }
            case 3: {
                File file = new File("com/example/is23am23/3.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoal2, image);
                break;
            }
            case 4: {
                File file = new File("com/example/is23am23/1.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoal2, image);
                break;
            }
            case 5: {
                File file = new File("com/example/is23am23/5.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoal2, image);
                break;
            }
            case 6: {
                File file = new File("com/example/is23am23/9.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoal2, image);
                break;
            }
            case 7: {
                File file = new File("com/example/is23am23/11.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoal2, image);
                break;
            }
            case 8: {
                File file = new File("com/example/is23am23/7.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoal2, image);
                break;
            }
            case 9: {
                File file = new File("com/example/is23am23/2.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoal2, image);
                break;
            }
            case 10: {
                File file = new File("com/example/is23am23/6.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoal2, image);
                break;
            }
            case 11: {
                File file = new File("com/example/is23am23/10.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoal2, image);
                break;
            }
            case 12: {
                File file = new File("com/example/is23am23/12.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoal2, image);
                break;
            }
        }
    }

    public void displayImage(ImageView commonGoal,Image image){
        commonGoal.setImage(image);
    }
    public void setGameControllerGUI(GameControllerGUI gameControllerGUI){
        this.gameControllerGUI=gameControllerGUI;
    }

    public GameControllerGUI getGameControllerGUI() {
        return gameControllerGUI;
    }
}
