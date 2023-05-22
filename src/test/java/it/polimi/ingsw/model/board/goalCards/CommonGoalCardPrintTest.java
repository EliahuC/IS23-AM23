package it.polimi.ingsw.model.board.goalCards;

import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.model.Game;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonGoalCardPrintTest {

    @Test
    void print() {
        Launcher l = new Launcher();
        l.setNumPlayers(4);
        CommonGoalCard card;
        /*new CommonGoalCard1(l).print();
        new CommonGoalCard2(l).print();
        new CommonGoalCard3(l).print();
        new CommonGoalCard4(l).print();
        new CommonGoalCard5(l).print();
        new CommonGoalCard6(l).print();
        new CommonGoalCard7(l).print();
        new CommonGoalCard8(l).print();
        new CommonGoalCard9(l).print();
        new CommonGoalCard10(l).print();
        new CommonGoalCard11(l).print();
        new CommonGoalCard12(l).print();*/
        card = new CommonGoalCard1(l);
        card.print();
        card = new CommonGoalCard2(l);
        card.print();
        card = new CommonGoalCard3(l);
        card.print();
        card = new CommonGoalCard4(l);
        card.print();
        card = new CommonGoalCard5(l);
        card.print();
        card = new CommonGoalCard6(l);
        card.print();
        card = new CommonGoalCard7(l);
        card.print();
        card = new CommonGoalCard8(l);
        card.print();
        card = new CommonGoalCard9(l);
        card.print();
        card = new CommonGoalCard10(l);
        card.print();
        card = new CommonGoalCard11(l);
        card.print();
        card = new CommonGoalCard12(l);
        card.print();
    }
}