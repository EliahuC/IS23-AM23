package it.polimi.ingsw.model.board.goalCards;


import org.junit.jupiter.api.Test;


class CommonGoalCardPrintTest {

    @Test
    void print() {
        CommonGoalCard card;
        card = new CommonGoalCard1(4);
        card.print();
        card = new CommonGoalCard2(4);
        card.print();
        card = new CommonGoalCard3(4);
        card.print();
        card = new CommonGoalCard4(4);
        card.print();
        card = new CommonGoalCard5(4);
        card.print();
        card = new CommonGoalCard6(4);
        card.print();
        card = new CommonGoalCard7(4);
        card.print();
        card = new CommonGoalCard8(4);
        card.print();
        card = new CommonGoalCard9(4);
        card.print();
        card = new CommonGoalCard10(4);
        card.print();
        card = new CommonGoalCard11(4);
        card.print();
        card = new CommonGoalCard12(4);
        card.print();
    }
}