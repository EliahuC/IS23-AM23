package it.polimi.ingsw.Messages.ServerToClient;

public class PingFromServer extends ServerMessage{
    private int pingCount;

    public PingFromServer(int pingCount) {
        super(MessageCategory.PING);
        this.pingCount=pingCount;

    }

    @Override
    public String toString() {
        return "PingFromServer{" +
                "MC=" + MC +
                "pingCount:"+ pingCount+
                '}';
    }

    public int getPingCount() {
        return pingCount;
    }


}
