package ru.kpfu.itis.charntsev.samwork.snake.view;

import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import ru.kpfu.itis.charntsev.samwork.snake.client.GameClient;
import ru.kpfu.itis.charntsev.samwork.snake.model.pattern.GameObject;
import ru.kpfu.itis.charntsev.samwork.snake.util.Direction;
import ru.kpfu.itis.charntsev.samwork.snake.util.Timer;

public class GameView extends BaseView{
    private AnchorPane pane;
    private VBox box;
    private long fps;
    private Pane background;
    private Rectangle player;
    private double playerSpeed;
    private Direction direction;
    private Timer timer;
    private GameClient gameClient;

    private static GameView game;

    private final EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
//            if (event.getSource() == start) {
//
//                getGameApplication().startGame();
//                getGameApplication().setView(getGameApplication().getGameView());
//
//            }
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

        ImageView imageView = new ImageView();
        imageView.imageProperty().set(new Image(getClass().getResourceAsStream("/images/green_grass.jpg")));
        imageView.setFitWidth(800);
        imageView.setFitHeight(800);


        timer = new Timer();
        timer.setCallback(() -> {
            if (timer.getSecondsPassed() % 60 == 0) {
                increaseSpeed();
            }
        });

        StackPane.setMargin(timer, new Insets(10));
        StackPane.setAlignment(timer, Pos.TOP_CENTER);

        startGame();

        box.getChildren().addAll(imageView, timer, player);
        pane.getChildren().addAll(box);
    }

    public static GameView getGame() {
        return game;
    }

    public static void setGame(GameView game) {
        GameView.game = game;
    }

    public void startTimer() {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();
            }
        };
        animationTimer.start();
    }

    public void update() {
//        if (System.currentTimeMillis() - fps > 32) {
//            for (GameObject gameObject : gameObjects) {
//                gameObject.update();
//            }
//            fps = System.currentTimeMillis();
//        }
    }

    public void removeGameObject(GameObject gameObject) {

    }

    private void startGame() {
        direction = Direction.RIGHT;
        playerSpeed = 5;

        player.setWidth(20);
        player.setHeight(20);
        player.setX(390);
        player.setY(390);
        player.setFill(Color.web("#212121"));

//        apple = makeApple();
//        gameField.replaceApple(apple);
//
//        points.reset();

        timer.stop();
        timer.start();

        // gameLoop.playFromStart();
    }

    private void stopGame() {

    }

    private void increaseSpeed() {
        playerSpeed += 1;
    }

    private Rectangle makePlayer() {

        return player;
    }

    private Circle makeApple() {
        Circle apple = new Circle();
        return apple;
    }

    private Timeline makeGameLoop() {
        Timeline gameLoop = new Timeline();

        return gameLoop;
    }

    private void gameOver() {

    }
}



