package ru.kpfu.itis.charntsev.classwork.fx.chat;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.kpfu.itis.charntsev.classwork.fx.chat.view.BaseView;
import ru.kpfu.itis.charntsev.classwork.fx.chat.view.UserConfigView;
import ru.kpfu.itis.charntsev.classwork.fx.chat.client.ChatClient;
import ru.kpfu.itis.charntsev.classwork.fx.chat.model.UserConfig;
import ru.kpfu.itis.charntsev.classwork.fx.chat.view.ChatView;

public class ChatApplication extends Application {

    private UserConfig userConfig;
    private BorderPane root;
    private UserConfigView configView;
    private ChatView chatView;
    private ChatClient chatClient;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Chat");
        primaryStage.setOnCloseRequest( e -> System.exit(0));

        BaseView.setChatApplication(this);

        configView = new UserConfigView();
        chatView = new ChatView();

        chatClient = new ChatClient(this);
        root = new BorderPane();
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();

        setView(configView);
    }

    public void appendMessage(String message) {
        chatView.appendMessage(message);
    }
    public void startChat() {
        chatClient.start();
    }

    public void setUserConfig(UserConfig userConfig) {
        this.userConfig = userConfig;
    }

    public UserConfig getUserConfig() {
        return userConfig;
    }

    public UserConfigView getConfigView() {
        return configView;
    }

    public void setView(BaseView view) {
        root.setCenter(view.getView());
    }

    public ChatView getChatView() {
        return chatView;
    }

    public ChatClient getChatClient() {
        return chatClient;
    }

    public void setChatClient(ChatClient chatClient) {
        this.chatClient = chatClient;
    }
}
