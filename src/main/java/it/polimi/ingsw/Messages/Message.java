package it.polimi.ingsw.Messages;

import java.util.Objects;

public abstract class Message {

    public MessageCategory messageCategory;
    private final String nickname;
    private String returnMessage;



    public Message(MessageCategory messageCategory, String n){
        this.messageCategory = messageCategory;
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
        return messageCategory;
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

    public MessageCategory getMessageCategory() {
        return messageCategory;
    }

    @Override
    public String toString() {
        return "Message{" +
                "nickname='" + nickname + '\'' +
                ", returnMessage='" + returnMessage + '\'' +
                ", MC=" + messageCategory +
                '}';
    }

    public void setMessageCategory(MessageCategory messageCategory) {
        this.messageCategory = messageCategory;
    }


    public void dumpPingMessage() {
        System.out.println(this.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return nickname.equals(message.nickname) && returnMessage.equals(message.returnMessage) && messageCategory == message.messageCategory;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname, returnMessage, messageCategory);
    }
}
