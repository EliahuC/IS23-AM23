package it.polimi.ingsw.view.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.io.IOException;

/**
 * @author Andrea Bricchi and Giovanni di Lorenzo
 * Controller of the goals
 */
public class GoalsController {

    @FXML
    ImageView personalGoalCard;
    @FXML
    ImageView commonGoalCard1;
    @FXML
    ImageView commonGoalCard2;
    @FXML
    ImageView pointsCGC1;
    @FXML
    ImageView pointsCGC2;
    private Image image;
    private GameControllerGUI gameControllerGUI;

    public void goToLivingroom(ActionEvent event) throws IOException {
            gameControllerGUI.displayScene();
    }

    /**
     * @author Andrea Bricchi and Giovanni di Lorenzo
     * Method to display the scores of the common goal cards
     */
    public void displayPoints(){

        int numPlayers = gameControllerGUI.getPlayers().size();
        int numCompleted1 = gameControllerGUI.getLivingRoom().getPointsCGD1();
        int numCompleted2 = gameControllerGUI.getLivingRoom().getPointsCGD2();

        // score common goal card 1
        switch (numPlayers) {
            case 2: {
                switch (numCompleted1) {
                    case 0:
                    {
                        File file = new File("com/example/is23am23/scoring_8.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(pointsCGC1, image);
                        break;
                    }
                    case 1:
                    {
                        File file = new File("com/example/is23am23/scoring_4.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(pointsCGC1, image);
                        break;
                    }
                    default:
                    {
                        File file = new File("com/example/is23am23/scoring_back_EMPTY.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(pointsCGC1, image);
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
                        displayImage(pointsCGC1, image);
                        break;
                    }
                    case 1:
                    {
                        File file = new File("com/example/is23am23/scoring_6.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(pointsCGC1, image);
                        break;
                    }
                    case 2:
                    {
                        File file = new File("com/example/is23am23/scoring_4.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(pointsCGC1, image);
                        break;
                    }
                    default:
                    {
                        File file = new File("com/example/is23am23/scoring_back_EMPTY.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(pointsCGC1, image);
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
                        displayImage(pointsCGC1, image);
                        break;
                    }
                    case 1:
                    {
                        File file = new File("com/example/is23am23/scoring_6.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(pointsCGC1, image);
                        break;
                    }
                    case 2:
                    {
                        File file = new File("com/example/is23am23/scoring_4.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(pointsCGC1, image);
                        break;
                    }
                    case 3:  {
                        File file = new File("com/example/is23am23/scoring_2.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(pointsCGC1, image);
                        break;
                    }
                    default:
                    {
                        File file = new File("com/example/is23am23/scoring_back_EMPTY.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(pointsCGC1, image);
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
                        displayImage(pointsCGC2, image);
                        break;
                    }
                    case 1:
                    {
                        File file = new File("com/example/is23am23/scoring_4.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(pointsCGC2, image);
                        break;
                    }
                    default:
                    {
                        File file = new File("com/example/is23am23/scoring_back_EMPTY.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(pointsCGC2, image);
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
                        displayImage(pointsCGC2, image);
                        break;
                    }
                    case 1:
                    {
                        File file = new File("com/example/is23am23/scoring_6.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(pointsCGC2, image);
                        break;
                    }
                    case 2:
                    {
                        File file = new File("com/example/is23am23/scoring_4.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(pointsCGC2, image);
                        break;
                    }
                    default:
                    {
                        File file = new File("com/example/is23am23/scoring_back_EMPTY.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(pointsCGC2, image);
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
                        displayImage(pointsCGC2, image);
                        break;
                    }
                    case 1:
                    {
                        File file = new File("com/example/is23am23/scoring_6.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(pointsCGC2, image);
                        break;
                    }
                    case 2:
                    {
                        File file = new File("com/example/is23am23/scoring_4.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(pointsCGC2, image);
                        break;
                    }
                    case 3:  {
                        File file = new File("com/example/is23am23/scoring_2.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(pointsCGC2, image);
                        break;
                    }
                    default:
                    {
                        File file = new File("com/example/is23am23/scoring_back_EMPTY.jpg");
                        image = new Image(String.valueOf(file));
                        displayImage(pointsCGC2, image);
                        break;
                    }
                }
                break;
            }
        }
    }

    /**
     * @author Andrea Bricchi and Giovanni di Lorenzo
     * Method to display the personal goal card
     */
    public void displayPersonalGoal(){

        int personalCard = gameControllerGUI.getSeed();

        switch (personalCard) {
            case 1:
            {
                File file = new File("com/example/is23am23/Personal_Goals.png");
                image = new Image(String.valueOf(file));
                displayImage(personalGoalCard, image);
                break;
            }
            case 2:
            {
                File file = new File("com/example/is23am23/Personal_Goals2.png");
                image = new Image(String.valueOf(file));
                displayImage(personalGoalCard, image);
                break;
            }
            case 3:
            {
                File file = new File("com/example/is23am23/Personal_Goals3.png");
                image = new Image(String.valueOf(file));
                displayImage(personalGoalCard, image);
                break;
            }
            case 4:
            {
                File file = new File("com/example/is23am23/Personal_Goals4.png");
                image = new Image(String.valueOf(file));
                displayImage(personalGoalCard, image);
                break;
            }
            case 5:
            {
                File file = new File("com/example/is23am23/Personal_Goals5.png");
                image = new Image(String.valueOf(file));
                displayImage(personalGoalCard, image);
                break;
            }
            case 6:
            {
                File file = new File("com/example/is23am23/Personal_Goals6.png");
                image = new Image(String.valueOf(file));
                displayImage(personalGoalCard, image);
                break;
            }
            case 7:
            {
                File file = new File("com/example/is23am23/Personal_Goals7.png");
                image = new Image(String.valueOf(file));
                displayImage(personalGoalCard, image);
                break;
            }
            case 8:
            {
                File file = new File("com/example/is23am23/Personal_Goals8.png");
                image = new Image(String.valueOf(file));
                displayImage(personalGoalCard, image);
                break;
            }
            case 9:
            {
                File file = new File("com/example/is23am23/Personal_Goals9.png");
                image = new Image(String.valueOf(file));
                displayImage(personalGoalCard, image);
                break;
            }
            case 10:
            {
                File file = new File("com/example/is23am23/Personal_Goals10.png");
                image = new Image(String.valueOf(file));
                displayImage(personalGoalCard, image);
                break;
            }
            case 11:
            {
                File file = new File("com/example/is23am23/Personal_Goals11.png");
                image = new Image(String.valueOf(file));
                displayImage(personalGoalCard, image);
                break;
            }
            case 12:
            {
                File file = new File("com/example/is23am23/Personal_Goals12.png");
                image = new Image(String.valueOf(file));
                displayImage(personalGoalCard, image);
                break;
            }
        }

    }

    /**
     * @author Andrea Bricchi and Giovanni di Lorenzo
     * Method to display the two personal goal card
     */
    public void displayCommonGoal() {

        int card1 = gameControllerGUI.getLivingRoom().getIdCGC1();
        int card2 = gameControllerGUI.getLivingRoom().getIdCGC2();

        switch (card1) {
            case 1: {
                File file = new File("com/example/is23am23/4.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoalCard1, image);
                break;
            }
            case 2: {
                File file = new File("com/example/is23am23/8.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoalCard1, image);
                break;
            }
            case 3: {
                File file = new File("com/example/is23am23/3.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoalCard1, image);
                break;
            }
            case 4: {
                File file = new File("com/example/is23am23/1.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoalCard1, image);
                break;
            }
            case 5: {
                File file = new File("com/example/is23am23/5.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoalCard1, image);
                break;
            }
            case 6: {
                File file = new File("com/example/is23am23/9.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoalCard1, image);
                break;
            }
            case 7: {
                File file = new File("com/example/is23am23/11.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoalCard1, image);
                break;
            }
            case 8: {
                File file = new File("com/example/is23am23/7.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoalCard1, image);
                break;
            }
            case 9: {
                File file = new File("com/example/is23am23/2.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoalCard1, image);
                break;
            }
            case 10: {
                File file = new File("com/example/is23am23/6.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoalCard1, image);
                break;
            }
            case 11: {
                File file = new File("com/example/is23am23/10.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoalCard1, image);
                break;
            }
            case 12: {
                File file = new File("com/example/is23am23/12.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoalCard1, image);
                break;
            }
        }

        switch (card2) {
            case 1: {
                File file = new File("com/example/is23am23/4.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoalCard2, image);
                break;
            }
            case 2: {
                File file = new File("com/example/is23am23/8.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoalCard2, image);
                break;
            }
            case 3: {
                File file = new File("com/example/is23am23/3.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoalCard2, image);
                break;
            }
            case 4: {
                File file = new File("com/example/is23am23/1.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoalCard2, image);
                break;
            }
            case 5: {
                File file = new File("com/example/is23am23/5.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoalCard2, image);
                break;
            }
            case 6: {
                File file = new File("com/example/is23am23/9.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoalCard2, image);
                break;
            }
            case 7: {
                File file = new File("com/example/is23am23/11.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoalCard2, image);
                break;
            }
            case 8: {
                File file = new File("com/example/is23am23/7.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoalCard2, image);
                break;
            }
            case 9: {
                File file = new File("com/example/is23am23/2.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoalCard2, image);
                break;
            }
            case 10: {
                File file = new File("com/example/is23am23/6.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoalCard2, image);
                break;
            }
            case 11: {
                File file = new File("com/example/is23am23/10.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoalCard2, image);
                break;
            }
            case 12: {
                File file = new File("com/example/is23am23/12.jpg");
                image = new Image(String.valueOf(file));
                displayImage(commonGoalCard2, image);
                break;
            }
        }
    }

    public void displayImage(ImageView imageView,Image image){
        imageView.setImage(image);
    }

    public void setGameControllerGUI(GameControllerGUI gameControllerGUI){
        this.gameControllerGUI=gameControllerGUI;
    }

    public GameControllerGUI getGameControllerGUI() {
        return gameControllerGUI;
    }
}
