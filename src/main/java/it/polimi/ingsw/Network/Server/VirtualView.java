package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Client.ClientConnectionToServer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class VirtualView implements PropertyChangeListener {
    private ClientConnectionToServer connection;
    public VirtualView(ClientConnectionToServer connection) {
        this.connection = connection;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    //DA IMPLEMENTARE TUTTE LE REAZIONI VIEW AI MESSAGGI

}
