package it.polimi.ingsw.Network.Messages.ServerToClient;

import it.polimi.ingsw.Network.Messages.Message;

public class EndGameMessage extends Message {
    public EndGameMessage() {
        super(MessageCategory.END_GAME_MESSAGE, "GameMaster");
    }
}
