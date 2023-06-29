package it.polimi.ingsw.Messages.ServerToClient;

/**
 * Message that represent the ping from the server to the client
 * @author Eliahu Cohen
 *
 */
public class PingFromServer extends ServerMessage{

    public PingFromServer(int pingCount) {
        super(MessageCategory.PINGFROMSERVER);
        this.pingCount=pingCount;

    }
    private int pingCount;
    @Override
    public String toString() {
        return "PingFromServer{" +
                "MC=" + messageCategory +
                "pingCount:"+ pingCount+
                '}';
    }

    public int getPingCount() {
        return pingCount;
    }


}
