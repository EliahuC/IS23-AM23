package server;
import java.util.ArrayList;
import server.model.player.Player;
public class Launcher {
private Player host;
private int numPlayers;

    public int getNumPlayers() {
        return numPlayers;
    }

    private ArrayList<Player> players= new ArrayList<>();

    public void addPlayer(Player p){
        players.add(p);
    }
}
