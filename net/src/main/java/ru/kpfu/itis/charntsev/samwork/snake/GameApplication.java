package ru.kpfu.itis.charntsev.samwork.snake;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.kpfu.itis.charntsev.samwork.snake.listener.KeyListener;
import ru.kpfu.itis.charntsev.samwork.snake.model.UserConfig;
import ru.kpfu.itis.charntsev.samwork.snake.client.GameClient;
import ru.kpfu.itis.charntsev.samwork.snake.view.BaseView;
import ru.kpfu.itis.charntsev.samwork.snake.view.GameView;
import ru.kpfu.itis.charntsev.samwork.snake.view.StartView;

public class GameApplication extends Application {

    private StartView startView;
    private GameView gameView;
    private GameClient  gameClient;
    private UserConfig userConfig;
    private KeyListener keyListener;
    private BorderPane root;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Snake game");
        primaryStage.setOnCloseRequest(e -> System.exit(0));

        BaseView.setGameApplication(this);

        startView = new StartView();
        gameView = new GameView();
        GameView.setGame(gameView);

        gameClient = new GameClient(this);

        keyListener = new KeyListener();

        root = new BorderPane();
        Scene scene = new Scene(root, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();

        setView(startView);
    }

    public void startGame() {
        gameClient.start();
    }

    public void setView(BaseView baseView) {
        root.setCenter(baseView.getView());
    }

    public UserConfig getUserConfig() {
        return userConfig;
    }

    public StartView getStartView() {
        return startView;
    }

    public void appendMessage(String message) {
        //chatView.appendMessage(message);
    }

    public KeyListener getKeyListener() {
        return keyListener;
    }

    public void setKeyListener(KeyListener keyListener) {
        this.keyListener = keyListener;
    }

    public void setStartView(StartView startView) {
        this.startView = startView;
    }

    public GameClient getGameClient() {
        return gameClient;
    }

    public void setGameClient(GameClient gameClient) {
        this.gameClient = gameClient;
    }

    public void setUserConfig(UserConfig userConfig) {
        this.userConfig = userConfig;
    }

    public GameView getGameView() {
        return gameView;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

}
