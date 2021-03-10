package Snake;

import javafx.util.Pair;

import java.util.List;
import java.util.LinkedList;

public class Snake {

    private GameGrid grid;
    private List<SnakeBlock> snakeBody;
    private Integer length;
    private Direction lastDirection;

    public enum Direction {
        UP, DOWN, LEFT, RIGHT;

        public static Direction oppositeDirectionTo(Direction direction) {
            if (direction == UP) {
                return DOWN;
            } else if (direction == DOWN) {
                return UP;
            } else if (direction == LEFT) {
                return RIGHT;
            } else // (direction == RIGHT){
                return LEFT;
        }
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
        SnakeBlock snakeBlock = new SnakeBlock(snakePositionX, snakePositionY);
        snakeBody.add(snakeBlock);
        grid.addToGrid(snakeBlock);
    }

    public Boolean move(Direction direction) {
        if (direction == Direction.oppositeDirectionTo(lastDirection)) {
            direction = lastDirection;
        }
        Pair<Integer, Integer> newHeadPlace = getCoordinatesForHeadAfterMove(direction);
        if (checkIfOccuresCollision(newHeadPlace)) {
            return false;
        } else {
            makeMove(newHeadPlace);
            lastDirection = direction;
            return true;
        }
    }


    private Pair<Integer, Integer> getCoordinatesForHeadAfterMove(final Direction direction) {
        SnakeBlock snakeHead = snakeBody.get(0);
        //System.out.println(snakeHead.getX()+" "+snakeHead.getY());
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
        return new Pair<Integer, Integer>(newX, newY);
    }

    private Boolean checkIfOccuresCollision(final Pair<Integer, Integer> newHeadPlace) {
        Integer newX = newHeadPlace.getKey();
        Integer newY = newHeadPlace.getValue();
        if ((newX < 0 || newX >= grid.getWidth()) || (newY < 0 || newY >= grid.getHeight())) {
            return true;
        } else if (grid.getGrid()[newY][newX].getClass().equals(Snake.class)) {
            return true;
        } else {
            return false;
        }
    }

    private void makeMove(final Pair<Integer, Integer> newHeadPlace) {
        Integer newX = newHeadPlace.getKey();
        Integer newY = newHeadPlace.getValue();
        SnakeBlock newSnakeHead = new SnakeBlock(newX, newY);
        grid.addToGrid(newSnakeHead);
        snakeBody.add(0, newSnakeHead);
        if (snakeBody.size() > length) {
            SnakeBlock removedTail = snakeBody.remove(snakeBody.size() - 1);
            Integer lastX = removedTail.getX();
            Integer lastY = removedTail.getY();
            EmptyBlock newEmptyBlock = new EmptyBlock(lastX, lastY);
            grid.addToGrid(newEmptyBlock);
        }
    }

    public void setLength(Integer length) {
        final Integer minSnakeLength = 2;
        this.length = Math.max(length, minSnakeLength);
        final Integer maxSnakeLength = grid.getWidth() * grid.getHeight() - 1;
        this.length = Math.min(this.length, maxSnakeLength);
    }

    public List<SnakeBlock> getSnakeBody() {
        return snakeBody;
    }
}
