package it.polimi.ingsw;

import it.polimi.ingsw.Network.Server.TCP.Lobby;

public class Savings {
    private Integer savingNumber;
    private Lobby lobby=null;
    private GameSavings gameSavings=null;

    public Savings(){
        this.savingNumber=0;
    }

    public void saveGame(GameSavings savings,Lobby lobby){
       this.lobby=lobby;
       this.gameSavings=gameSavings;
       savingNumber++;
    }
}
