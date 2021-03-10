package Snake;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SnakeGame extends Application {
    private GameGrid grid;
    private Snake snake;
    private Integer snakeLength;
    private Boolean running;
    private Scene scene;
    private Color backgroundColor = Color.BLACK;
    private Snake.Direction direction = Snake.Direction.LEFT; // TODO
    private Display display;

    public SnakeGame() {
        grid = new GameGrid(25, 25);
        snakeLength = 24;
    }

    public void start(Stage primaryStage) throws Exception {
        // primaryStage.setTitle("Snake");
        display = new Display(grid);
        // Height
        int blocksHeight = grid.getHeight() * display.getRectSize();
        int gapsInHeight = (grid.getHeight() - 3) * display.getGapSize();
        int gridHeight = blocksHeight + gapsInHeight;
        // Width
        int blocksWidth = grid.getWidth() * display.getRectSize();
        int gapsInWidth = (grid.getWidth() - 3) * display.getGapSize();
        ////////
        int gridWidth = blocksWidth + gapsInWidth;
        scene = new Scene(display, gridHeight, gridWidth, backgroundColor); // TODO
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        snake = new Snake(grid, snakeLength);
        grid.createNewApple();
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.RIGHT) {
                direction = Snake.Direction.RIGHT;
                // System.out.println("PRAWO");
            } else if (e.getCode() == KeyCode.UP) {
                direction = Snake.Direction.UP;
                // System.out.println("GORA");
            } else if (e.getCode() == KeyCode.LEFT) {
                direction = Snake.Direction.LEFT;
                // System.out.println("LEWO");
            } else if (e.getCode() == KeyCode.DOWN) {
                direction = Snake.Direction.DOWN;
                // System.out.println("DOL");
            }
        });

        AnimationTimer timer = new AnimationTimer() {
            long lastTick = 0;

            public void handle(long now) {
                if (lastTick == 0) {
                    lastTick = now;
                    display.display();
                    return;
                }

                if (now - lastTick > 100000000) {
                    display.display();
                    lastTick = now;
                    if (running) {
                        if (!snake.move(direction)) {
                            running = false;
                        } else {
                            grid.createNewApple();
                        }
                    } else {
                        display.displayGameOver();
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
