package it.polimi.ingsw.CLICommands;

import java.util.ArrayList;

public class CLICommandList {


    public static ArrayList<String> getCommands() {
        ArrayList<String> commands= new ArrayList<>();
        commands.add("/create numPlayers (numPlayers can only be 2/3/4)");
        commands.add("/enter");
        commands.add("/select x1 y1 x2 y2 x3 y3");
        commands.add("/column y");
        commands.add("/order t1 t2 t3 (t1 t2 t3 must be numbers of your previous selection" +
                     " for example if you have selected 2 tiles t1 == tile with coordinates(x1,y1)  " +
                     "and t2 == tile with coordinates (x2,y2) and the order you want is  t2 t1 you have to write 2 1");
        return commands;
    }
}
