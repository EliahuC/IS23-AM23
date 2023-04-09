package server;
import java.util.ArrayList;
import server.model.player.Player;
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

    public void addPlayer(Player p){
        players.add(p);
    }
}
