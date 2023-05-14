package it.polimi.ingsw.Network.Server.RMI;

import it.polimi.ingsw.Network.Client.RMI.ClientConnectionRMI;

public class RMIConnection {
    private final ClientConnectionRMI clientSession;
    private boolean connected = true;
    public RMIConnection( ClientConnectionRMI clientSession) {
        this.clientSession = clientSession;
    }
    public boolean isConnected() {
        return connected;
    }
}
