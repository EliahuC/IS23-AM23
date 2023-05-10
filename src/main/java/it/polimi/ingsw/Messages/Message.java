package it.polimi.ingsw.Messages;

public abstract class Message {

    private final String nickname;
    private String returnMessage;


    public MessageCategory MC;

    public Message(MessageCategory MC,String n){
        this.MC=MC;
        this.nickname=n;
    }
    public enum MessageCategory{
        PING,
        COORDINATES,
        COLUMN,
        ORDER,
        BOOKSHELF,
        LIVINGROOM,
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



    public void setReturnMessage(String s){
        returnMessage= s;
    }

    public String getReturnMessage(){

        return returnMessage;
    }

    public String getNickname() {
        return nickname;
    }

    public MessageCategory getMC() {
        return MC;
    }

    @Override
    public String toString() {
        return "Message{" +
                "nickname='" + nickname + '\'' +
                ", returnMessage='" + returnMessage + '\'' +
                ", MC=" + MC +
                '}';
    }

    public void setMC(MessageCategory MC) {
        this.MC = MC;
    }


    public void dumpPingMessage() {
        System.out.println(this.toString());
    }
}
