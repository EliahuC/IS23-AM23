package server;

public class Message {

    private final String nickname;
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

    public String getNickname() {
        return nickname;
    }
}
