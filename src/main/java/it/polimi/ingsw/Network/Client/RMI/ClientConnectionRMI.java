package it.polimi.ingsw.Network.Client.RMI;

import com.google.gson.Gson;
import it.polimi.ingsw.Messages.ClientToServer.ClientMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.MoveDeserializer;
import it.polimi.ingsw.Messages.ServerToClient.ServerMessage;
import it.polimi.ingsw.Network.Client.ConnectionClient;
import it.polimi.ingsw.Network.Server.RMI.RMIConnection;
import it.polimi.ingsw.Network.Server.RMI.ServerConnectionRMI;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class ClientConnectionRMI extends ConnectionClient implements Remote {
    private static ServerConnectionRMI stub;
    private boolean clientIsActive=true;
    private boolean GUIisActive;
    @Override
    public void run() {
        try{
            stub =(ServerConnectionRMI) Naming.lookup("rmi://localhost:"+22011+"/RMIServer");
        } catch (MalformedURLException | NotBoundException | RemoteException e) {
            throw new RuntimeException(e);
        }

    }

//Ricevo il messagio
    public void sendMessage(String message) {
        ServerMessage serverMessage= (ServerMessage) MoveDeserializer.deserializeOutput(message);
        if (serverMessage!= null && serverMessage.getCategory() != Message.MessageCategory.PINGFROMSERVER) {
            if (GUIisActive) {
                //GUIEvent.recieveMessage(serverMessage);
            } else; //CLIEvent.recieveMessage(serverMessage);
        } else if(serverMessage!= null && serverMessage.getCategory()== Message.MessageCategory.PINGFROMSERVER){
            //System.out.println("Ping arrived");
            //sendPing();
        }



    }

//Metodo che manda messaggio al server
    public void sendMessage(ClientMessage message){
        Gson gson=new Gson();
        String m=gson.toJson(message);
        stub.sendMessage(m);
    }

    @Override
    public String getPlayerName() {
        return null;
    }
}
