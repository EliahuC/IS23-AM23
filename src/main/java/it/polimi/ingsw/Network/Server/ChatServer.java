package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Client.ChatClient;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServer extends Remote{

    void login(ChatClient cc) throws RemoteException;

    void send(String message) throws RemoteException;
}
