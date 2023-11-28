package ru.kpfu.itis.charntsev.controlwork.bot.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ru.kpfu.itis.charntsev.controlwork.bot.client.BotClient;
import ru.kpfu.itis.charntsev.controlwork.bot.model.User;
import ru.kpfu.itis.charntsev.fx.chat.model.UserConfig;

public class StartView extends BaseView {

    private AnchorPane pane;
    private VBox box;
    private TextField host;
    private TextField port;
    private TextField username;
    private Button start;

    private BotClient botClient;

    private final EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == start) {
                User user = new User();
                user.setUsername(username.getText());

                getBotApplication().setUser(user);

                getBotApplication().getBotClient().executeCommand("/start");

            }
        }
    };

    @Override
    public Parent getView() {
        if (pane == null){
            createView();
        }
        return pane;
    }

    private void createView() {
        pane = new AnchorPane();

        box = new VBox(20);

        Label usernameLabel = new Label("username");
        username = new TextField();

        start = new Button("Start bot!");
        start.setOnAction(eventHandler);

        box.getChildren().addAll(usernameLabel, username, start);
        pane.getChildren().addAll(box);


    }
}
