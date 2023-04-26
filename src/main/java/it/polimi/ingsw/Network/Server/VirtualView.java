package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Client.ClientConnectionToServer;

import java.net.http.WebSocket;

public class VirtualView implements WebSocket.Listener {
    private ClientConnectionToServer connection;
    public VirtualView(ClientConnectionToServer connection) {
        this.connection = connection;
    }

    //DA IMPLEMENTARE TUTTE LE REAZIONI VIEW AI MESSAGGI

}
