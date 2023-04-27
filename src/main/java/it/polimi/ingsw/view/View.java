package it.polimi.ingsw.view;

import it.polimi.ingsw.model.player.Player;

public abstract class View {
    private Player player;
    public View(){}

    public Player getPlayer() {
        return player;
    }
}
