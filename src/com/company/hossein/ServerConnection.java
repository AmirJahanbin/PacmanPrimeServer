package com.company.hossein;

import java.io.*;
import java.net.Socket;

public class ServerConnection  implements Runnable{

    private Socket server;
    //private BufferedReader in;

    private DataInputStream dis;

    public ServerConnection(Socket s) throws IOException
    {
        server = s;
        //in = new BufferedReader(new InputStreamReader(server.getInputStream()));

        dis=new DataInputStream(s.getInputStream());
    }

    @Override
    public void run() {

        try {
            while (true) {
                byte[] data = new byte[1000];
                int count = dis.read(data);

                for (int i = 0 ;i <count ; i++)
                {
                    System.out.print(unsignedToBytes(data[i]) + " - ");
                }
                System.out.println(" ");
                /*String serverResponse = in.readLine();
                if (serverResponse == null)
                {
                    System.out.println("here");
                    break;
                }
                System.out.println("Server says: " + serverResponse);*/
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private  int unsignedToBytes(byte b) {
        return b & 0xFF;
    }

}
