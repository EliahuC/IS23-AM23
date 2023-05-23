package it.polimi.ingsw.Messages;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.polimi.ingsw.Messages.ClientToServer.*;
import it.polimi.ingsw.Messages.ClientToServer.PossibleMoves.Move_SelectTiles;
import it.polimi.ingsw.Messages.ServerToClient.*;

/**
 * @author Eliahu Cohen
 * Class that deserialize the gson message received
 */
public class MoveDeserializer {
    /**
     * @author Eliahu Cohen
     * @param s message received
     * @return message deserialized or null if is an invalid string
     */
   public static Message deserializeOutput(String s){
        JsonObject o=new JsonParser().parse(s).getAsJsonObject();
        String category=o.get("messageCategory").getAsString();
        Gson gson=new Gson();
       switch (category) {
           case "PINGFROMSERVER" -> {
               return gson.fromJson(s, PingFromServer.class);
           }
           case "COORDINATES" -> {
               Move_SelectTiles move=gson.fromJson(o.get("move").getAsJsonObject(), Move_SelectTiles.class);
               CoordinatesMessage message=gson.fromJson(s, CoordinatesMessage.class);
               message.setMove(move);
               return message;
           }
           case "COLUMN" -> {
               return gson.fromJson(s, ColumnMessage.class);
           }
           case "ORDER" -> {
               return gson.fromJson(s, OrderMessage.class);
           }
           case "BOOKSHELF" -> {
               return gson.fromJson(s, BookshelfMessage.class);
           }
           case "LIVINGROOM" -> {
               return gson.fromJson(s, LivingRoomMessage.class);
           }
           case "VALID_MESSAGE" -> {
               return gson.fromJson(s, ValidMoveMessage.class);
           }
           case "STARTING_GAME_MESSAGE" -> {
               return gson.fromJson(s, GameIsStartingMessage.class);
           }
           case "LAST_TURN_MESSAGE" -> {
               return gson.fromJson(s, LastTurnMessage.class);
           }
           case "CREATE_LOBBY" -> {
               return gson.fromJson(s, LobbyCreationMessage.class);
           }
           case "SUCCESS" -> {
               return gson.fromJson(s, SuccessMessage.class);
           }
           case "START_GAME" -> {
               return gson.fromJson(s, StartGameMessage.class);
           }
           case "ENTER_LOBBY" -> {
               return gson.fromJson(s, LobbyEntranceMessage.class);
           }
           case "LOGOUT_LOBBY" -> {
               return gson.fromJson(s, LobbyLogoutMessage.class);
           }
           case "END_GAME_MESSAGE" -> {
               return gson.fromJson(s, EndGameMessage.class);
           }
           case "WARNING" -> {
               return gson.fromJson(s, ErrorMessage.class);
           }
           case "NICKNAME" -> {
               return gson.fromJson(s, NickNameMessage.class);
           }
           case "VALID_NICKNAME" -> {
               return gson.fromJson(s, ValidNicknameMessage.class);
           }
           case "RETURN_MESSAGE"->{
               return gson.fromJson(s, LobbyJoiningMessage.class);
           }
           case "CRASHED"->{
               return gson.fromJson(s, CrashedLobbyMessage.class);
           }
           case "CURRPLAYING"->{
               return gson.fromJson(s, CurrPlayingMessage.class);
           }
           case "COMMONGOAL"->{
               return gson.fromJson(s, CommonCompletedMessage.class);
           }
           case "SCORE"-> {
               return gson.fromJson(s, ScoreMessage.class);
           }
           case "PLAYER"->{
               return gson.fromJson(s, PlayersMessage.class);
           }
           case "UPDATE_STATE"->{
               return gson.fromJson(s, UpdateStateMessage.class);
           }
       }
        return null;
    }


}
