package it.polimi.ingsw.model.player;

import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.model.board.LivingRoom;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonalGoalCardPrintTest {

    @Test
    void print() {
        new PersonalGoalCard().print();
    }
}