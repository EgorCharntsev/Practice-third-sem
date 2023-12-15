package ru.kpfu.itis.charntsev.samwork.snake.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ru.kpfu.itis.charntsev.samwork.snake.model.UserConfig;
import ru.kpfu.itis.charntsev.samwork.snake.client.GameClient;

public class StartView extends BaseView{
    private AnchorPane pane;
    private VBox box;
    private TextField host;
    private TextField port;
    private TextField username;
    private Button start;

    private GameClient gameClient;

    private final EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == start) {
                UserConfig userConfig = new UserConfig();
                userConfig.setUsername(username.getText());
                userConfig.setPort(Integer.parseInt(port.getText()));
                userConfig.setHost(host.getText());

                getGameApplication().setUserConfig(userConfig);

                getGameApplication().startGame();
                getGameApplication().setView(getGameApplication().getGameView());

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

        Label hostLabel = new Label("host");
        host = new TextField();
        host.setText("127.0.0.1");

        Label portLabel = new Label("port");
        port = new TextField();
        port.setText("5555");

        start = new Button("Start play!");
        start.setOnAction(eventHandler);

        box.getChildren().addAll(usernameLabel, username, hostLabel, host, portLabel, port, start);
        pane.getChildren().addAll(box);

    }
}
