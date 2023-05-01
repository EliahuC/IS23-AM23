package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Client.ClientConnectionToServer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class VirtualView implements PropertyChangeListener {
    private ServerConnectionToClient clientConnection;

    public VirtualView(ServerConnectionToClient connection) {
        this.clientConnection = connection;
    }



    @Override
    public void propertyChange(PropertyChangeEvent evt) {

      clientConnection.sendMessage();
    }

    //DA IMPLEMENTARE TUTTE LE REAZIONI VIEW AI MESSAGGI

}
