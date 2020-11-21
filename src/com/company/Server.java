package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread {
    public List<Socket> clientSocketList;
    final Socket currentSocket;
    public List<PrintWriter> outputList = new ArrayList<>();
    final String echoString;
    public Server(List<Socket> clientSocketList, Socket currentClientSocket) {
        this.clientSocketList = clientSocketList;
        this.currentSocket = currentClientSocket;
        setOutputList();
        this.echoString = "";

    }

    public void setOutputList() {
        try {
            for(Socket socket : clientSocketList) {
                outputList.add(new PrintWriter(socket.getOutputStream(), true));
            }
            System.out.println("this is list of client sockets connected to the server: " + outputList);
        } catch (IOException e) {
            System.out.println("an error occurred: " + e.getMessage());
        }
    }

    public void sendMessageToAllClients (String message) {
        for(PrintWriter output : outputList) {
            //System.out.println(message);
            output.println("Salam to all :), this is message: " + message);
        }
    }

    @Override
    public void run() {
        try{
            BufferedReader input = new BufferedReader(new InputStreamReader(currentSocket.getInputStream()));
            System.out.println("client connected");
            while (true) {
                String echoString = input.readLine();
                if(echoString.equals("exit")) {
                    break;
                }
                sendMessageToAllClients(echoString);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                currentSocket.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
