package com.company.hossein;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static final String SERVER_IP = "192.168.1.54";
    private static final int SERVER_PORT = 9000;


    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(SERVER_IP , SERVER_PORT);

        ServerConnection serverConn = new ServerConnection(socket);

        BufferedReader keyboard  = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream() , true);


        new Thread(serverConn).start();


        while (true)
        {
            System.out.println("> ");

            String command = keyboard.readLine();
            if (command.equals("quit")) break;

            out.println(command);


        }
    }

}
