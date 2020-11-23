package com.company.hossein;

import com.company.Client;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private ArrayList<ClientHandler> clients;

    DataInputStream dis;
    DataOutputStream dout;

    byte[] request = new byte[1000];

    byte[] response = new byte[256];

    public  ClientHandler(Socket clientSocket , ArrayList<ClientHandler> clients) throws IOException
    {
        this.client = clientSocket;
        this.clients = clients;
        //in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        //out = new PrintWriter(clientSocket.getOutputStream() , true);

        dis=new DataInputStream(client.getInputStream());
        dout=new DataOutputStream(client.getOutputStream());

        for(int i = 0 ; i < 1000 ; i++)
            request[i] = 3;

        for(int i = 0 ; i < 256 ; i++)
            request[i] = 4;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int count = dis.read(request);

                System.out.println("count = " + count);
                for (int i = 0 ; i < count ; i++)
                {
                    System.out.println(request[i]);
                }
                //dout.write(response, 0, response.length);
                /*String request = in.readLine();
                System.out.println(request);
                if (request.contains("name")) {
                    out.println(getRandomName());
                } else if (request.startsWith("say")) {
                    int firstSpace = request.indexOf(" ");
                    if (firstSpace != -1) {
                        outToAll(request.substring(firstSpace + 1));
                    }
                } else {
                    out.println("Type 'tell me a name' to get a random name");

                }*/
            }
        }
        catch (IOException e) {
            System.err.println("IO exception in client handler");
            System.err.println(e.getStackTrace());
        }
        finally {
            out.close();
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void outToAll(String msg) {
        for (ClientHandler aClient: clients)
        {
            aClient.out.println(msg);
        }
    }

    public static String getRandomName()
    {
        return "hossein";
    }
}
