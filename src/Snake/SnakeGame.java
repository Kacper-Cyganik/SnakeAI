package Snake;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SnakeGame extends Application {
    private GameGrid grid;
    private Snake snake;
    private Integer snakeLength;
    private Boolean running;
    Pane pane;
    Scene scene;

    Snake.Direction direction = Snake.Direction.LEFT; // TODO

    public SnakeGame() {
        grid = new GameGrid(5, 5);
        snakeLength = 2;
    }

    public void start(Stage primaryStage) throws Exception {
        // primaryStage.setTitle("Snake");
        pane = new Pane(); // TODO
        scene = new Scene(pane, 300, 275); // TODO
        primaryStage.setScene(scene);
        primaryStage.show();

        snake = new Snake(grid, snakeLength);

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.RIGHT) {
                direction = Snake.Direction.RIGHT;
                System.out.println("PRAWO");
            } else if (e.getCode() == KeyCode.UP) {
                direction = Snake.Direction.UP;
                System.out.println("GORA");
            } else if (e.getCode() == KeyCode.LEFT) {
                direction = Snake.Direction.LEFT;
                System.out.println("LEWO");
            } else if (e.getCode() == KeyCode.DOWN) {
                direction = Snake.Direction.DOWN;
                System.out.println("DOL");
            }
        });

        AnimationTimer timer = new AnimationTimer() {
            long lastTick = 0;

            public void handle(long now) {
                if (lastTick == 0) {
                    lastTick = now;
                    return;
                }
                if (now - lastTick > 100000000) {
                    lastTick = now;
                    if (running) {
                        if (!snake.move(direction)) {
                            running = false;
                        }
                    }
                }
            }
        };
        running = true;
        timer.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
