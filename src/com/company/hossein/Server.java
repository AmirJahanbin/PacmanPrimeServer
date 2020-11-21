package com.company.hossein;


import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final int PORT = 5000;
    private static InetAddress SERVER_IP ;

    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(4);

    private static ServerSocket listener;

    public static void main(String[] args) throws IOException {

        SERVER_IP = InetAddress.getByName("127.0.0.1");

        listener = new ServerSocket(PORT, 50, SERVER_IP);

        ServerListener serverListener = new ServerListener();

        new Thread(serverListener).start();

    }


    public static class ServerListener implements Runnable{

        @Override
        public void run() {
            while (true) {

                try {
                    System.out.println("[SERVER] waiting for client connection...");
                    Socket client = listener.accept();
                    System.out.println("[SERVER] connected to client");

                    ClientHandler clientThread = new ClientHandler(client, clients);
                    clients.add(clientThread);
                    pool.execute(clientThread);
                }
                catch (IOException e)
                {

                }
            }
        }
    }

}
