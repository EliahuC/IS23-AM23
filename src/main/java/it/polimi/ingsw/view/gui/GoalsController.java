package it.polimi.ingsw.view.gui;

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
import java.io.IOException;

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

    public void goToLivingroom(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        root = loader.load();
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
/*
    public void displayPoints{

    }
*/
    public void displayPersonalGoal(){
        //Viene salvato l'indice della personal card
        int personalCard = 1;

        switch (personalCard) {
            case 1:
            {
                Image image = new Image(getClass().getResourceAsStream("PersonalGoalCards.jpg"));
                displayImage(personalGoal, image);
            }
            case 2:
            {
                Image image = new Image(getClass().getResourceAsStream("PersonalGoalCards2.jpg"));
                displayImage(personalGoal, image);
            }
            case 3:
            {
                Image image = new Image(getClass().getResourceAsStream("PersonalGoalCards3.jpg"));
                displayImage(personalGoal, image);
            }
            case 4:
            {
                Image image = new Image(getClass().getResourceAsStream("PersonalGoalCards4.jpg"));
                displayImage(personalGoal, image);
            }
            case 5:
            {
                Image image = new Image(getClass().getResourceAsStream("PersonalGoalCards5.jpg"));
                displayImage(personalGoal, image);
            }
            case 6:
            {
                Image image = new Image(getClass().getResourceAsStream("PersonalGoalCards6.jpg"));
                displayImage(personalGoal, image);
            }
            case 7:
            {
                Image image = new Image(getClass().getResourceAsStream("PersonalGoalCards7.jpg"));
                displayImage(personalGoal, image);
            }
            case 8:
            {
                Image image = new Image(getClass().getResourceAsStream("PersonalGoalCards8.jpg"));
                displayImage(personalGoal, image);
            }
            case 9:
            {
                Image image = new Image(getClass().getResourceAsStream("PersonalGoalCards9.jpg"));
                displayImage(personalGoal, image);
            }
            case 10:
            {
                Image image = new Image(getClass().getResourceAsStream("PersonalGoalCards10.jpg"));
                displayImage(personalGoal, image);
            }
            case 11:
            {
                Image image = new Image(getClass().getResourceAsStream("PersonalGoalCards11.jpg"));
                displayImage(commonGoal1, image);
            }
            case 12:
            {
                Image image = new Image(getClass().getResourceAsStream("PersonalGoalCards12.jpg"));
                displayImage(personalGoal, image);
            }
            default: System.out.println("è colpa di eliahu");

        }





    }

    public void displayCommonGoal() {

        //Viene salvato l'indice della common card
        int card1 = 1;
        int card2 = 2;

        switch (card1) {
            case 1: {
                Image image = new Image(getClass().getResourceAsStream("1.jpg"));
                displayImage(commonGoal1, image);
            }
            case 2: {
                Image image = new Image(getClass().getResourceAsStream("2.jpg"));
                displayImage(commonGoal1, image);
            }
            case 3: {
                Image image = new Image(getClass().getResourceAsStream("3.jpg"));
                displayImage(commonGoal1, image);
            }
            case 4: {
                Image image = new Image(getClass().getResourceAsStream("4.jpg"));
                displayImage(commonGoal1, image);
            }
            case 5: {
                Image image = new Image(getClass().getResourceAsStream("5.jpg"));
                displayImage(commonGoal1, image);
            }
            case 6: {
                Image image = new Image(getClass().getResourceAsStream("6.jpg"));
                displayImage(commonGoal1, image);
            }
            case 7: {
                Image image = new Image(getClass().getResourceAsStream("7.jpg"));
                displayImage(commonGoal1, image);
            }
            case 8: {
                Image image = new Image(getClass().getResourceAsStream("8.jpg"));
                displayImage(commonGoal1, image);
            }
            case 9: {
                Image image = new Image(getClass().getResourceAsStream("9.jpg"));
                displayImage(commonGoal1, image);
            }
            case 10: {
                Image image = new Image(getClass().getResourceAsStream("10.jpg"));
                displayImage(commonGoal1, image);
            }
            case 11: {
                Image image = new Image(getClass().getResourceAsStream("11.jpg"));
                displayImage(commonGoal1, image);
            }
            case 12: {
                Image image = new Image(getClass().getResourceAsStream("12.jpg"));
                displayImage(commonGoal1, image);
            }
            default: System.out.println("è colpa di eliahu");

        }

        switch (card2) {
            case 1: {
                Image image = new Image(getClass().getResourceAsStream("1.jpg"));
                displayImage(commonGoal2, image);
            }
            case 2: {
                Image image = new Image(getClass().getResourceAsStream("2.jpg"));
                displayImage(commonGoal2, image);
            }
            case 3: {
                Image image = new Image(getClass().getResourceAsStream("3.jpg"));
                displayImage(commonGoal2, image);
            }
            case 4: {
                Image image = new Image(getClass().getResourceAsStream("4.jpg"));
                displayImage(commonGoal2, image);
            }
            case 5: {
                Image image = new Image(getClass().getResourceAsStream("5.jpg"));
                displayImage(commonGoal2, image);
            }
            case 6: {
                Image image = new Image(getClass().getResourceAsStream("6.jpg"));
                displayImage(commonGoal2, image);
            }
            case 7: {
                Image image = new Image(getClass().getResourceAsStream("7.jpg"));
                displayImage(commonGoal2, image);
            }
            case 8: {
                Image image = new Image(getClass().getResourceAsStream("8.jpg"));
                displayImage(commonGoal2, image);
            }
            case 9: {
                Image image = new Image(getClass().getResourceAsStream("9.jpg"));
                displayImage(commonGoal2, image);
            }
            case 10: {
                Image image = new Image(getClass().getResourceAsStream("10.jpg"));
                displayImage(commonGoal2, image);
            }
            case 11: {
                Image image = new Image(getClass().getResourceAsStream("11.jpg"));
                displayImage(commonGoal2, image);
            }
            case 12: {
                Image image = new Image(getClass().getResourceAsStream("12.jpg"));
                displayImage(commonGoal2, image);
            }
            default: System.out.println("è colpa di eliahu");

        }
    }

    public void displayImage(ImageView commonGoal1,Image image){
        commonGoal1.setImage(image);
    }
}
