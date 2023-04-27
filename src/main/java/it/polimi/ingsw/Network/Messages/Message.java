package it.polimi.ingsw.Network.Messages;

import java.util.Optional;

public abstract class Message {

    private final String nickname;
    private Optional<String> returnMessage;

    public MessageCategory MC;

    public Message(MessageCategory MC,String n){
        this.MC=MC;
        this.nickname=n;
    }
    public enum MessageCategory{
        COORDINATES,
        COLUMN,
        RETURN_MESSAGE,
        CREATE_LOBBY,
        SUCCESS,
        START_GAME,
        ENTER_LOBBY,
        LOGOUT_LOBBY,
        END_GAME_MESSAGE,
        WARNING;

    }



    public MessageCategory getCategory(){
        return MC;
    }



    public void addReturnMessage(String s){
        returnMessage= s.describeConstable();
    }

    public Optional<String> getReturnMessage(){
        if(returnMessage.isPresent())return returnMessage;
        returnMessage= "there isn't any return message".describeConstable();
        return returnMessage;
    }

    public String getNickname() {
        return nickname;
    }
}
