package it.polimi.ingsw;

import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;
public class Launcher {
private Player host;
private int numPlayers;

    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers){
        this.numPlayers=numPlayers;
    }

    private ArrayList<Player> players= new ArrayList<>();




    public void addPlayers(ArrayList<Player> players){
        this.players.addAll(players);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player p1) {
        players.add(p1);
    }
}
