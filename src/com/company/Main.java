package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(5000)) {
            List<Socket> clientSocketList = new ArrayList<>();
            int lastSocket = 0;
            while (true) {
                Socket currentClientSocket = serverSocket.accept();
                clientSocketList.add(currentClientSocket);
                new Server(clientSocketList, currentClientSocket).start();
            }
        } catch (IOException e) {
            System.out.println("an error occurred in server: " + e.getMessage());
        }
    }
}
