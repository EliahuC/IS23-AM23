package it.polimi.ingsw.Network.Client;

import com.google.gson.Gson;
import it.polimi.ingsw.CLICommands.CLICommandList;
import it.polimi.ingsw.Messages.Message;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveSerializerTest {


    @Test
    void getFromKeyboard() {
        Gson gson=new Gson();

        String s1= CLICommandList.getCommands().get(1);
        MoveSerializer ms1= new MoveSerializer();
        Message m1= ms1.getFromKeyboard(s1);
        String sj1=gson.toJson(m1);
        assertEquals("{\"nickname\":\"NICKNAME\",\"MC\":\"ENTER_LOBBY\"}",sj1);

        /*String s2= CLICommandList.getCommands().get(2);
        MoveSerializer ms2= new MoveSerializer();
        Message m2= ms2.getFromKeyboard(s2);
        String sj2=gson.toJson(m2);
        assertEquals("{\"MC\":\"LOGOUT_LOBBY\"}",sj2);

*/
    }
}
