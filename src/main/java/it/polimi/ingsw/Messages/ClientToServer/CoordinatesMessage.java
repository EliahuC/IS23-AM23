package it.polimi.ingsw.Messages.ClientToServer;

import it.polimi.ingsw.Messages.ClientToServer.PossibleMoves.Move_SelectTiles;

public class CoordinatesMessage extends ClientMessage {

    public CoordinatesMessage(Move_SelectTiles move) {
        super(MessageCategory.COORDINATES, move,"Client");
    }

   public Integer getSpecificCoordinate(int i){
        return getMessageMove().getMove().get(i);
   }

}