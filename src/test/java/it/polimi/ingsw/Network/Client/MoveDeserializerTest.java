package it.polimi.ingsw.Network.Client;

import com.google.gson.Gson;
import it.polimi.ingsw.Launcher;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.MoveDeserializer;
import it.polimi.ingsw.Messages.ServerToClient.*;
import it.polimi.ingsw.Network.Server.TCP.ServerConnectionTCP;
import it.polimi.ingsw.Network.Server.VirtualView;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.player.PGCKey;
import it.polimi.ingsw.model.player.Player;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
        PGCKey key=new PGCKey(2,1);
        String s4=gson.toJson(key);
        System.out.println(s);
        PGCKey key1=gson.fromJson(s4,PGCKey.class);
        BookshelfMessage bookshelfMessage= (BookshelfMessage) MoveDeserializer.deserializeOutput(s1);
        LivingRoomMessage livingRoomMessage= (LivingRoomMessage) MoveDeserializer.deserializeOutput(s2);
        PlayersMessage playersMessage= (PlayersMessage) MoveDeserializer.deserializeOutput(s3);
        GameIsStartingMessage message1= (GameIsStartingMessage) MoveDeserializer.deserializeOutput(s);
        assert message1 != null;
        assertEquals(message1.getPlayers(),L.getPlayers());
        assertEquals(message1.getLivingRoom(),G.getLivingRoom());
        assertEquals(message1.getCurrPlaying(),G.getCurrPlaying());
        assertEquals(message1.getCommonGoalCard1(),G.getLivingRoom().getCommonGoalCard1());
        assertEquals(message1.getCommonGoalCard2(),G.getLivingRoom().getCommonGoalCard2());
        assertEquals(message1.getCategory(), Message.MessageCategory.STARTING_GAME_MESSAGE);

    }
}