package ru.kpfu.itis.charntsev.samwork.snake.model;

import javafx.scene.image.Image;
import ru.kpfu.itis.charntsev.samwork.snake.model.pattern.Destroyable;
import ru.kpfu.itis.charntsev.samwork.snake.model.pattern.GameObject;
import ru.kpfu.itis.charntsev.samwork.snake.util.Direction;
import ru.kpfu.itis.charntsev.samwork.snake.view.GameView;

import static ru.kpfu.itis.charntsev.samwork.snake.view.BaseView.getGameApplication;

public class Snake extends GameObject implements Destroyable {

    private Direction direction;

    public Snake(String key, double x, double y, Direction direction) {
        super(key);
        Image image = new Image(getClass().getResourceAsStream("resources/images/snake.svg"));
        imageProperty().set(image);
        setFitHeight(120);
        setFitWidth(120);
        setLayoutX(x);
        setLayoutY(y);
        this.direction = direction;
        updateSnakeDirection();
    }

    @Override
    public void update() {

    }

    void updateSnakeDirection() {
        switch (direction) {
            case UP: setRotate(-90); break;
            case DOWN: setRotate(90); break;
            case RIGHT: setRotate(0); break;
            case LEFT: setRotate(180); break;
        }
    }

    @Override
    public void destroy() {
        GameView.getGame().removeGameObject(this);
    }
}
