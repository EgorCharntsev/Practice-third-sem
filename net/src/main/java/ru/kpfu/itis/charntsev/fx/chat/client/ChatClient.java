package ru.kpfu.itis.charntsev.fx.chat.client;

import ru.kpfu.itis.charntsev.fx.chat.ChatApplication;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ChatClient {

    private Socket socket;
    private ChatApplication application;
    private ClientThread thread;

    public ChatClient(ChatApplication application) {
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

    public ChatApplication getApplication() {
        return application;
    }

    static class ClientThread implements Runnable {
        private final BufferedReader input;
        private final BufferedWriter output;
        private ChatClient chatClient;

        public ClientThread(BufferedReader input, BufferedWriter output, ChatClient chatClient) {
            this.input = input;
            this.output = output;
            this.chatClient = chatClient;
        }

        @Override
        public void run() {
            try {
                while(true) {
                    String message = input.readLine();
                    chatClient.getApplication().appendMessage(message);
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
