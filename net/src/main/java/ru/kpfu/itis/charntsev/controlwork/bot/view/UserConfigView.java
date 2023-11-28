package ru.kpfu.itis.charntsev.controlwork.bot.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ru.kpfu.itis.charntsev.controlwork.bot.model.User;
import ru.kpfu.itis.charntsev.controlwork.bot.model.UserConfig;
import ru.kpfu.itis.charntsev.controlwork.bot.view.BaseView;

public class UserConfigView extends BaseView {

    private AnchorPane pane;
    private VBox box;
    private TextField host;
    private TextField port;
    private TextField username;
    private Button start;

    private final EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == start) {
                UserConfig userConfig = new UserConfig();
                User user = new User();
                user.setUsername(username.getText());
                userConfig.setHost(host.getText());
                userConfig.setUser(user);
                userConfig.setPort(Integer.parseInt(port.getText()));

                getBotApplication().setUserConfig(userConfig);

                getBotApplication().startChat();

                getBotApplication().setView(getBotApplication().getChatView());
            }
        }
    };

    @Override
    public Parent getView(){
        if (pane == null) {
            createView();
        }
        return pane;
    }

    private void createView() {
        pane = new AnchorPane();

        box = new VBox(10);

        Label usernameLabel = new Label("username");
        username = new TextField();
        username.setText(getBotApplication().getUser().getUsername());

        Label hostLabel = new Label("host");
        host = new TextField();
        host.setText("127.0.0.1");

        Label portLabel = new Label("port");
        port = new TextField();
        port.setText("5555");

        start = new Button("Start chat");
        start.setOnAction(eventHandler);

        box.getChildren().addAll(usernameLabel, username, hostLabel, host, portLabel, port, start);
        pane.getChildren().addAll(box);

    }
}

