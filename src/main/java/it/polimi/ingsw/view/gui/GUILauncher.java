package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Network.Client.ConnectionClient;
import it.polimi.ingsw.Network.Client.RMI.ClientConnectionRMI;
import it.polimi.ingsw.Network.Client.TCP.ClientConnectionTCP;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GUILauncher {
    private String nickname;
    private String serverAddr;
    private int portNum;
    private Socket socket;
    private ConnectionClient connectionClient;
    private ServerMessage response;
    private GUIEvent receiver;

    //private Button button = null;
    public void setResponse(ServerMessage response) {
        this.response = response;
    }

    public static void main(String[] args) {
        GUIMain.main(args);
    }
}
