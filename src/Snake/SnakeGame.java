package Snake;

public class SnakeGame {
    private GameGrid grid;
    private Snake snake;
    private Boolean running;
    private Integer snakeLength;

    SnakeGame(Integer width, Integer height) {
        grid = new GameGrid(width, height);
        snakeLength = 2;
    }

    void run() {
        running = true;
        snake = new Snake(grid, snakeLength);
        while (running) {

        }
    }

    public Boolean getRunning() {
        return running;
    }

    public Integer getSnakeLength() {
        return snakeLength;
    }

    public void setSnakeLength(Integer snakeLength) {
        this.snakeLength = snakeLength;
    }

}
