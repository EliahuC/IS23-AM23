package it.polimi.ingsw.controller;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.ServerToClient.EndGameMessage;
import it.polimi.ingsw.model.player.Player;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Optional;

public class EndGameController implements PropertyChangeListener {
    private  Optional<Player> winner;
    public EndGameController(){

    }

    public Message endGameMessage(Optional<Player> p){
        this.winner=p;
        Message M=new EndGameMessage();
        M.setReturnMessage("The winner of this game is: "+ winner.get().getNickName());
        return M;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
