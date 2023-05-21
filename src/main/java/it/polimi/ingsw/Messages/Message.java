package it.polimi.ingsw.Messages;

import java.util.Objects;

/**
 * @author Eliahu Cohen
 * abstract class that represent the concept of message
 */
public abstract class Message {

    public MessageCategory messageCategory;
    private  String nickname;
    private String returnMessage;



    public Message(MessageCategory messageCategory, String n){
        this.messageCategory = messageCategory;
        this.nickname=n;
    }

    /**
     * @author Eliahu Cohen
     * enum that indicates the type of the message
     */
    public enum MessageCategory{
        PINGTOSERVER,
        CURRPLAYING,
        PINGFROMSERVER,
        COORDINATES,
        COLUMN,
        ORDER,
        BOOKSHELF,
        LIVINGROOM,
        LAST_TURN_MESSAGE,
        VALID_MESSAGE,
        STARTING_GAME_MESSAGE,
        CREATE_LOBBY,
        SUCCESS,
        START_GAME,
        ENTER_LOBBY,
        LOGOUT_LOBBY,
        END_GAME_MESSAGE,
        RETURN_MESSAGE,
        NICKNAME,
        VALID_NICKNAME,
        CLOSE,
        CRUSHED,
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

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname, returnMessage, messageCategory);
    }

}
