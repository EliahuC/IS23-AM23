package it.polimi.ingsw.Network.Client;

import com.google.gson.Gson;
import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.MoveDeserializer;
import it.polimi.ingsw.Messages.ServerToClient.*;
import it.polimi.ingsw.Network.Server.TCP.ServerConnectionTCP;
import it.polimi.ingsw.Network.Server.VirtualView;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.Pair;
import it.polimi.ingsw.model.player.Player;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MoveDeserializerTest {

    @Test
    void deserializeOutput() throws IOException {
        Gson gson=new Gson();
        Player p1 = new Player("Tom");
        Player p2 = new Player("Jerry");
        Launcher L = new Launcher();
        L.addPlayer(p1);
        L.addPlayer(p2);
        p1.setListener(new VirtualView(new ServerConnectionTCP(null)));
        p2.setListener(new VirtualView(new ServerConnectionTCP(null)));
        Game G = new Game(L,L.getPlayers());
        G.startGame();
        GameIsStartingMessage message=new GameIsStartingMessage(G);
        BookshelfMessage message2=new BookshelfMessage(G.getPlayers().get(0).getPlayerBookshelf());
        LivingRoomMessage message3= new LivingRoomMessage(G.getLivingRoom().getBoard());
        PlayersMessage message4=new PlayersMessage(G.getPlayers());
        String s=gson.toJson(message);
        String s1=gson.toJson(message2);
        String s2=gson.toJson(message3);
        String s3=gson.toJson(message4);
        Pair key=new Pair(2,1);
        String s4=gson.toJson(key);
        System.out.println(s3);
        Pair key1=gson.fromJson(s4, Pair.class);
        BookshelfMessage bookshelfMessage= (BookshelfMessage) MoveDeserializer.deserializeOutput(s1);
        LivingRoomMessage livingRoomMessage= (LivingRoomMessage) MoveDeserializer.deserializeOutput(s2);
        PlayersMessage playersMessage= (PlayersMessage) MoveDeserializer.deserializeOutput(s3);
        GameIsStartingMessage message1= (GameIsStartingMessage) MoveDeserializer.deserializeOutput(s);
        assert message1 != null;

    }
}