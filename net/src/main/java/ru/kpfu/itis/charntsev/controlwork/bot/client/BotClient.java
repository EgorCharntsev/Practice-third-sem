package ru.kpfu.itis.charntsev.controlwork.bot.client;

import ru.kpfu.itis.charntsev.controlwork.bot.BotApplication;
import ru.kpfu.itis.charntsev.controlwork.bot.util.WeatherUtil;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.SQLOutput;

public class BotClient {

    private BotApplication botApplication;

    private BotClientThread thread;

    public BotClient(BotApplication botApplication) {
        this.botApplication = botApplication;
    }

    public void sendCommand(String command) {
        try {
            thread.getOutput().write(command);
            thread.getOutput().flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        BufferedReader input;
        BufferedWriter output;

        input = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        output = new BufferedWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));

        thread = new BotClientThread(input, output, this);
        new Thread(thread).start();
    }

    public BotApplication getApplication() {
        return botApplication;
    }

    public String executeCommand(String command) {

        String message;
        switch (command) {
            case "/start":

                message = "Welcome to chat with bot!";
                getApplication().startBot();
                getApplication().setView(getApplication().getBotView());
                break;

            case "/end" :

                message = "Bye, bye my friend!";
                System.exit(0);
                break;

            case "/help" :

                message = "list of commands:\n" +
                        "/help - a command help, that give you a list of commands;\n" +
                        "/chat - a command connection to chat with your friends;\n" +
                        "/weather \"City name\" - a command showing the weather forecast in a given city;\n" +
                        "/currency \"Name of the currency\" - a command showing the currency rate.";
                break;

            case "/chat" :
                message = "Open connection to chat!";
                getApplication().setView(getApplication().getConfigView());

                //open chat;
                break;

            case "/weather" :
//                String[] names = command.split(" ");
                String name = "Kazan";
                WeatherUtil weatherUtil = new WeatherUtil(name);
                try {
                    message = weatherUtil.getWeather(name);
                } catch (IOException e) {
                    throw new RuntimeException();
                }
                //get weather;

                break;
            case "/currency" :

                message = "";

                //get exchange rate;
                break;
            default:
                message = "Unknown command =(, please enter command \"/help\"";
                //unknown command, please enter new command
                break;
        }
        return message;
    }

    static class BotClientThread implements Runnable {
        private final BufferedReader input;
        private final BufferedWriter output;
        private BotClient botClient;

        public BotClientThread(BufferedReader input, BufferedWriter output, BotClient botClient) {
            this.input = input;
            this.output = output;
            this.botClient = botClient;
        }
        @Override
        public void run() {
            try {
                while (true) {
                    String command = input.readLine();
                    botClient.getApplication().appendCommand(command);
                }
            } catch(IOException e) {
                throw new RuntimeException(e);
            }
        }

        public BufferedWriter getOutput() {
            return output;
        }
    }
}
