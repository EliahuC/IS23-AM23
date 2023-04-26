package it.polimi.ingsw.Network.Messages.ServerToClient.ClientToServer;

import it.polimi.ingsw.Network.Messages.ServerToClient.ClientToServer.PossibleMoves.Move_SelectTiles;

public class CoordinatesMessage extends ClientMessage {

    public CoordinatesMessage(Move_SelectTiles move, String nickname) {
        super(MessageCategory.COORDINATES, move, nickname);
    }

   public Integer getSpecificCoordinate(int i){
        return getMessageMove().getMove().get(i);
   }
}
