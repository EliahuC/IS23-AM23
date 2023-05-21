package it.polimi.ingsw.Messages.ServerToClient;

import it.polimi.ingsw.model.player.PersonalGoalCard;
import it.polimi.ingsw.model.player.Player;

import java.util.HashMap;
import java.util.List;

public class PlayersMessage extends ServerMessage{
    private final HashMap<String, PersonalGoalCard> personalGoalCardHashMap=new HashMap<>();
    public PlayersMessage(List<Player> players) {
        super(MessageCategory.PLAYER);
        for(Player p:players){
            personalGoalCardHashMap.put(p.getNickName(),p.getPersonalGoalCard());
        }
    }

    public HashMap<String, PersonalGoalCard> getPersonalGoalCardHashMap() {
        return personalGoalCardHashMap;
    }
}
