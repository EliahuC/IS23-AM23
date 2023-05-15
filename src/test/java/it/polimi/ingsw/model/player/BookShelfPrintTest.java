package it.polimi.ingsw.model.player;

import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.model.GameChecker;
import it.polimi.ingsw.model.board.Bag;
import it.polimi.ingsw.model.board.ItemTile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookShelfPrintTest {

    @Test
    void print() {
        Launcher l = new Launcher();
        l.setNumPlayers(4);
        BookShelf bs = new BookShelf();
        Bag b=new Bag();
        for(int i=5; i>0; i--){
            for(int j=5-i; j<5; j++)
                bs.setTile(i,j,b.extract());
        }
        bs.print();
    }
}