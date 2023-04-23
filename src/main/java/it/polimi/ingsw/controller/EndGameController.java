package it.polimi.ingsw.controller;
import it.polimi.ingsw.Message;
import it.polimi.ingsw.model.player.Player;

import java.util.Optional;

public class EndGameController {
    private final Optional<Player> winner;
    public EndGameController(Optional<Player> p){
        this.winner=p;
    }

    public Message endGameMessage(){
        Message M=new Message(null,"GameMaster");
        M.addReturnMessage("The winner of this game is: "+ winner.get().getNickName());
        M.setCategory(Message.MessageCategory.END_GAME_MESSAGE);
        return M;
    }


}
