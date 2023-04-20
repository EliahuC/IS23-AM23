package server;

import java.util.Optional;

public class Message {

    private final String nickname;
    private Optional<String> returnMessage;
    public final Move m;
    public MessageCategory MC;

    public Message(Move m,String n){
        this.m=m;
        this.nickname=n;
    }
    public enum MessageCategory{
        COORDINATES,
        COLUMN,
        ORDER,
        RETURN_MESSAGE,
        CREATE_LOBBY,
        START_GAME,
        WARNING;

    }

    public Move getMessageMove() {
        return m;
    }

    public MessageCategory getCategory(){
        return MC;
    }

    public void setCategory(MessageCategory c){
        this.MC=c;
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
