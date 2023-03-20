package server;
import java.util.ArrayList;

public class Launcher {
private Player host;
private int numPlayers;
private Player players= new ArrayList<>();

    public void addPlayer(Player p){
        players.add(p);
    }
}
