package ru.kpfu.itis.charntsev.samwork.snake.server;

import ru.kpfu.itis.charntsev.samwork.snake.client.GameClient;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class GameServer {
    private ServerSocket serverSocket;
    private List<Client> clients = new ArrayList<>();

    public void start() {
        try {
            serverSocket = new ServerSocket(5555);
            while(true) {
                Socket clientSocket = serverSocket.accept();
                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
                BufferedWriter output = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8));

                Client client = new Client(input,output,this);
                clients.add(client);

                new Thread(client).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void sendMessage(String message, Client client) {
        for(Client c : clients) {
            if(c.equals(client)) {
                continue;
            }

            try {
                c.getOutput().write(message);
                c.getOutput().flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        GameServer gameServer = new GameServer();
        gameServer.start();
    }

    static class Client implements Runnable {

        private BufferedReader input;
        private BufferedWriter output;
        private GameServer server;
        public Client (BufferedReader input, BufferedWriter output, GameServer gameServer) {
            this.input = input;
            this.output = output;
            this.server = gameServer;

        }

        @Override
        public void run() {
            try {
                while (true) {
                    String message = input.readLine();
                    server.sendMessage(message, this);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public BufferedWriter getOutput() {
            return output;
        }
    }
}
