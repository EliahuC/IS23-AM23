package it.polimi.ingsw.model.board;

import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.model.GameChecker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LivingRoomPrintTest {

    @Test
    void print() {
        Launcher l4 = new Launcher();
        l4.setNumPlayers(4);
        Launcher l2 = new Launcher();
        l2.setNumPlayers(2);
        Launcher l3 = new Launcher();
        l3.setNumPlayers(3);
        GameChecker gc4 = new GameChecker(l4);
        GameChecker gc2 = new GameChecker(l2);
        GameChecker gc3 = new GameChecker(l3);
        Bag bag = new Bag();
        LivingRoom board4 = new LivingRoom(l4);
        LivingRoom board2 = new LivingRoom(l2);
        LivingRoom board3 = new LivingRoom(l3);

        board4.Start(4);
        board4.print();

        System.out.print("\n\n\n\n");
        System.out.flush();

        board3.Start(4);
        board3.print();

        System.out.print("\n\n\n\n");
        System.out.flush();

        board2.Start(4);
        board2.print();
    }
}