package Snake;

import java.util.List;

import javax.swing.border.EmptyBorder;

import java.util.LinkedList;

public class Snake {

    private GameGrid grid;
    private List<SnakeBlock> snakeBody;
    private Integer length;

    public enum Direction {
        UP, DOWN, LEFT, RIGHT;
    }

    public Snake(GameGrid grid, Integer length) {
        this.grid = grid;
        initializeLength(length);
        snakeBody = new LinkedList<SnakeBlock>();
        initializeSnake();
    }

    private void initializeLength(Integer length) {
        final Integer minSnakeLength = 2;
        this.length = Math.max(length, minSnakeLength);
        final Integer maxSnakeLength = grid.getWidth() * grid.getHeight() - 1;
        this.length = Math.min(this.length, maxSnakeLength);
    }

    private void initializeSnake() {
        Integer snakePositionX = grid.getWidth() / 2;
        Integer snakePositionY = grid.getHeight() / 2;
        for (int i = 0; i < length; i++) {
            SnakeBlock snakeBlock = new SnakeBlock(snakePositionX + i, snakePositionY);
            snakeBody.add(snakeBlock);
        }
    }

    public Boolean move(Direction direction) {
        // TODO - zabezpieczyć przed głupimi danymi
        SnakeBlock snakeHead = snakeBody.get(0);
        Integer newX;
        Integer newY;
        if (direction == Direction.LEFT) {
            newX = snakeHead.getX() - 1;
            newY = snakeHead.getY();
        } else if (direction == Direction.RIGHT) {
            newX = snakeHead.getX() + 1;
            newY = snakeHead.getY();
        } else if (direction == Direction.UP) {
            newX = snakeHead.getX();
            newY = snakeHead.getY() - 1;
        } else { // (direction == Direction.DOWN)
            newX = snakeHead.getX();
            newY = snakeHead.getY() + 1;
        }
        if (checkCollision(newX, newY)) {
            return false;
        }
        SnakeBlock newSnakeHead = new SnakeBlock(newX, newY);
        grid.getGrid()[newY][newX] = newSnakeHead;
        snakeBody.add(0, newSnakeHead);
        if (snakeBody.size() >= length) {
            SnakeBlock removedTail = snakeBody.remove(snakeBody.size() - 1);
            Integer lastX = removedTail.getX();
            Integer lastY = removedTail.getY();
            grid.getGrid()[lastY][lastX] = new EmptyBlock(lastX, lastY);
        }
        return true;
    }

    private Boolean checkCollision(Integer newX, Integer newY) {
        if ((newX < 0 || newX >= grid.getWidth()) || (newY < 0 || newY >= grid.getHeight())) {
            return true;
        } else if (grid.getGrid()[newY][newX].getClass().equals(Snake.class)) {
            return true;
        } else {
            return false;
        }
    }

    public void setLength(Integer length) {
        final Integer minSnakeLength = 2;
        this.length = Math.max(length, minSnakeLength);
        final Integer maxSnakeLength = grid.getWidth() - grid.getWidth() / 2;
        this.length = Math.min(this.length, maxSnakeLength);
    }

    public List<SnakeBlock> getSnakeBody() {
        return snakeBody;
    }
}
