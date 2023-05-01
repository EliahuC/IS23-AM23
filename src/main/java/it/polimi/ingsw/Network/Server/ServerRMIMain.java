package it.polimi.ingsw.Network.Server;

import java.rmi.RemoteException;

public class ServerRMIMain extends Server{
    @Override
    public boolean login(String nick) throws RemoteException {
        return false;
    }

    @Override
    public void logout(String nick) throws RemoteException {

    }

    @Override
    public void showMessage(String s) {

    }

    @Override
    public void run() {

    }
}
