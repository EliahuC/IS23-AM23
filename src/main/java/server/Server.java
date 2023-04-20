package server;
import view.Printer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class Server implements Runnable, Printer {
    private ServerSocket serverSocket;

    private int port;

    public Server(){
        this.port=49521;
    }
    @Override
    public void run() {
        showMessage("INSERT NEW PORT OR PRESS ENTER TO USE THE DEFAULT PORT");
        Scanner input = new Scanner(System.in);
        String newPort = input.nextLine();
        if(newPort.length()!=0) port = Integer.parseInt(newPort);
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Accepting..");
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
       ;
        try {
            input = new Scanner(
                    new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showMessage(String s) {
        System.out.println("s");
    }
}
