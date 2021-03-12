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
    private final Integer snakeLength;
    private Boolean running;
    Scene scene;
    Menu menu;
    Color backgroundColor;
    Snake.Direction direction;
    Display display;
    AnimationTimer timer;
    Stage primaryStage;

    public SnakeGame() {
        backgroundColor = Color.BLACK;
        snakeLength = 5;
    }

    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        startGame();
    }

    public void startGame() {
        direction = Snake.Direction.LEFT;
        grid = new GameGrid(20, 20);
        menu = new Menu();
        display = new Display(grid, menu);
        // Height
        int blocksHeight = grid.getHeight() * display.getRectSize();
        int gapsInHeight = (grid.getHeight()) * display.getGapSize();
        int height = blocksHeight + gapsInHeight;
        // Width
        int blocksWidth = grid.getWidth() * display.getRectSize();
        int gapsInWidth = (grid.getWidth()) * display.getGapSize();
        int width = blocksWidth + gapsInWidth + 25;
        ////////
        scene = new Scene(display, height, width, backgroundColor);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Snake");
        primaryStage.setResizable(false);
        primaryStage.show();

        snake = new Snake(grid, snakeLength);
        grid.createNewApple();
        display.display();

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.D)
                direction = Snake.Direction.RIGHT;
            else if (e.getCode() == KeyCode.W)
                direction = Snake.Direction.UP;
            else if (e.getCode() == KeyCode.A)
                direction = Snake.Direction.LEFT;
            else if (e.getCode() == KeyCode.S)
                direction = Snake.Direction.DOWN;
            else if (e.getCode() == KeyCode.P)
                menu.setPauseGame(!menu.getPauseGame());
            else if (e.getCode() == KeyCode.R)
                restart();
        });

        timer = new AnimationTimer() {
            long lastTick = 0;

            public void handle(long now) {
                if (lastTick == 0) {
                    lastTick = now;
                    return;
                }
                if (now - lastTick > 100000000) {

                    lastTick = now;

                    if (!menu.getPauseGame()) {

                        if (running) {
                            if (!snake.move(direction)) {
                                running = false;
                            }
                            if (grid.createNewApple())
                                menu.updateScore();

                            display.display();
                        } else {
                            GameOver();
                        }
                    }
                }
            }
        };
        running = true;
        timer.stop();
        timer.start();

    }

    public void GameOver() {
        System.out.println("GAME OVER");
        timer.stop();
        display.getChildren().removeAll();

    }

    public void restart() {
        GameOver();
        startGame();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
