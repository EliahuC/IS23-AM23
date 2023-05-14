package it.polimi.ingsw;

import it.polimi.ingsw.Network.Server.TCP.Lobby;

/**
 * @author Eliahu Cohen
 * Class that rapresent the savings of a particular lobby
 */
public class Savings {
    private Integer savingNumber;
    private final Lobby lobby;
    private GameSavings gameSavings=null;

    public Savings(Lobby lobby){
        this.lobby=lobby;
        this.savingNumber=0;
    }

    /**
     * @author Eliahu Cohen
     * @param savings
     * method to save a game
     */
    public void saveGame(GameSavings savings){
       this.gameSavings=savings;
       savingNumber++;
    }

    public Lobby getLobby() {
        return lobby;
    }

    public GameSavings getGameSavings() {
        return gameSavings;
    }

    public void setGameSavings(GameSavings gameSavings) {
        this.gameSavings = gameSavings;
    }

    public Integer getSavingNumber() {
        return savingNumber;
    }

    public void setSavingNumber(Integer savingNumber) {
        this.savingNumber = savingNumber;
    }
}
