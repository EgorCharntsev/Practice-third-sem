package ru.kpfu.itis.charntsev.controlwork.bot;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.kpfu.itis.charntsev.controlwork.bot.client.BotClient;
import ru.kpfu.itis.charntsev.controlwork.bot.model.User;
import ru.kpfu.itis.charntsev.controlwork.bot.model.UserConfig;
import ru.kpfu.itis.charntsev.controlwork.bot.view.*;
import ru.kpfu.itis.charntsev.controlwork.bot.client.ChatClient;

public class BotApplication extends Application {

    private User user;
    private UserConfig userConfig;
    private StartView startView;
    private BotView botView;
    private UserConfigView configView;
    private ChatView chatView;
    private BorderPane root;
    private BotClient botClient;
    private ChatClient chatClient;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Bot");
        primaryStage.setOnCloseRequest(e -> botClient.executeCommand("/end"));

        BaseView.setBotApplication(this);

        startView = new StartView();
        botView = new BotView();
        configView = new UserConfigView();
        chatView = new ChatView();

        botClient = new BotClient(this);
        chatClient = new ChatClient(this);

        root = new BorderPane();
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();

        setView(startView);
    }

    public void setView(BaseView baseView) {
        root.setCenter(baseView.getView());
    }

    public void startBot() {
        botClient.start();
    }

    public void startChat() {
        chatClient.start();
    }

    public void appendCommand(String command) {
        botView.appendCommand(command);
    }

    public void appendMessage(String message) {
        chatView.appendMessage(message);
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BotView getBotView() {
        return botView;
    }

    public BotClient getBotClient() {
        return botClient;
    }

    public UserConfig getUserConfig() {
        return userConfig;
    }

    public UserConfigView getConfigView() {
        return configView;
    }

    public ChatClient getChatClient() {
        return chatClient;
    }

    public ChatView getChatView() {
        return chatView;
    }

    public void setUserConfig(UserConfig userConfig) {
        this.userConfig = userConfig;
    }
}
