package it.polimi.ingsw.controller;
import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.Network.Messages.ServerToClient.EndGameMessage;
import it.polimi.ingsw.model.player.Player;

import java.util.Optional;

public class EndGameController {
    private final Optional<Player> winner;
    public EndGameController(Optional<Player> p){
        this.winner=p;
    }

    public Message endGameMessage(){
        Message M=new EndGameMessage();
        M.addReturnMessage("The winner of this game is: "+ winner.get().getNickName());
        return M;
    }


}
