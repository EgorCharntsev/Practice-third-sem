package ru.kpfu.itis.charntsev.samwork.snake.view;

import javafx.scene.Parent;
import ru.kpfu.itis.charntsev.samwork.snake.GameApplication;

public abstract class BaseView {

    private static GameApplication gameApplication;

    public static GameApplication getGameApplication() {
        if (gameApplication != null) {
            return gameApplication;
        }
        throw new RuntimeException("No app in base view");
    }

    public static void setGameApplication(GameApplication gameApplication) {
        BaseView.gameApplication = gameApplication;
    }

    public abstract Parent getView();
}
