package ru.kpfu.itis.charntsev.samwork.snake.client;

import ru.kpfu.itis.charntsev.controlwork.bot.BotApplication;
import ru.kpfu.itis.charntsev.controlwork.bot.client.ChatClient;
import ru.kpfu.itis.charntsev.samwork.snake.GameApplication;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class GameClient {

    private Socket socket;
    private GameApplication application;
    private ClientThread thread;

    public GameClient(GameApplication application) {
        this.application = application;
    }

    public void sendMessage(String message) {
        try {
            thread.getOutput().write(message);
            thread.getOutput().flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        String host = application.getUserConfig().getHost();
        int port = application.getUserConfig().getPort();

        BufferedReader input;
        BufferedWriter output;
        try {
            socket = new Socket(host, port);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        thread = new ClientThread(input, output, this);
        new Thread(thread).start();
    }

    public GameApplication getApplication() {
        return application;
    }

    static class ClientThread implements Runnable {
        private final BufferedReader input;
        private final BufferedWriter output;
        private GameClient gameClient;

        public ClientThread(BufferedReader input, BufferedWriter output, GameClient gameClient) {
            this.input = input;
            this.output = output;
            this.gameClient = gameClient;
        }

        @Override
        public void run() {
            try {
                while(true) {
                    String message = input.readLine();
                    gameClient.getApplication().appendMessage(message);
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
