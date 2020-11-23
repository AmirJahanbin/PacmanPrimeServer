package com.company.hossein;

import java.io.*;
import java.net.Socket;

public class Client {
    private static final String SERVER_IP = "192.168.1.50";
    private static final int SERVER_PORT = 9000;


    private static byte[] request = new byte[256];

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(SERVER_IP , SERVER_PORT);

        ServerConnection serverConn = new ServerConnection(socket);

        BufferedReader keyboard  = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter out = new PrintWriter(socket.getOutputStream() , true);


        //DataInputStream dis=new DataInputStream(socket.getInputStream());
        DataOutputStream dout=new DataOutputStream(socket.getOutputStream());

        for(int i = 0 ; i < 256 ; i++)
            request[i] = 1;

        new Thread(serverConn).start();


        while (true)
        {
            System.out.println("> ");

            String command = keyboard.readLine();
            if (command.equals("quit")) break;
            if (command.equals("send"))
            {
                //dout.write(request);
                //dout.flush();
                //out.println(request);
                dout.write(request);
            }

            //out.println(command);


        }
    }

}
