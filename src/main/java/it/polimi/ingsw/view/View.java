package it.polimi.ingsw.view;

import it.polimi.ingsw.model.player.Player;

public interface View extends Runnable {
    Player player = null;

     default Player getPlayer() {
        return player;
    }
}
