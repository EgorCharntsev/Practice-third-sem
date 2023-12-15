package ru.kpfu.itis.charntsev.samwork.snake.listener;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyListener {

    public boolean up = false;
    public boolean down = false;
    public boolean left = false;
    public boolean right = false;
    public boolean w = false;
    public boolean s = false;
    public boolean a = false;
    public boolean d = false;

    public KeyListener() {}

    public EventHandler<KeyEvent> onKeyPressed = event -> {
        switchDirection();
        switch (event.getCode()) {
            case UP: up = true; break;
            case DOWN: down = true; break;
            case LEFT: left = true; break;
            case RIGHT: right = true; break;
            case W: w = true; break;
            case S: s = true; break;
            case A: a = true; break;
            case D: d = true; break;
            case ESCAPE: Runtime.getRuntime().exit(0);
        }
    };

    private void switchDirection() {
        up = false;
        down = false;
        right = false;
        left = false;
        w = false;
        s = false;
        a = false;
        d = false;
    }
}
