package Snake;

import java.util.Random;

public class GameGrid {

    private GameObject[][] grid;
    private Integer width;
    private Integer height;
    private Integer emptyBlocksLeft;

    GameGrid(Integer width, Integer height) {
        this.width = width;
        this.height = height;
        this.grid = new GameObject[height][width];
        initializeGridWithEmptyBlocks();
        this.emptyBlocksLeft = height * width;
    }

    private void initializeGridWithEmptyBlocks() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = new EmptyBlock(j, i);
            }
        }
    }

    public void cleanGrid() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (!grid[i][j].getClass().equals(EmptyBlock.class)) {
                    // not grid[i][j] == EmptyBlock
                    grid[i][j] = new EmptyBlock(j, i);
                }
            }
        }
        this.emptyBlocksLeft = height * width;
    }

    public Boolean createNewApple() {
        Apple newApple;
        if (emptyBlocksLeft <= 0) {
            return false;
        }
        if (emptyBlocksLeft < 0.3 * width * height) {
            newApple = createAppleInFilledGrid();
        } else {
            newApple = createAppleInSparseFilledGrid();
        }
        addToGrid(newApple);
        return true;
    }

    private Apple createAppleInFilledGrid() {
        Random random = new Random();
        Integer randomWidth = random.nextInt() % width;
        Integer randomHeight = random.nextInt() % height;
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                Integer newWidth = (randomWidth + w) % width;
                Integer newHeight = (randomHeight + h) % height;
                if (grid[newHeight][newWidth].getClass().equals(EmptyBlock.class)) {
                    return new Apple(newWidth, newHeight);
                }
            }
        }
        System.out.println("Critical logic error"); // DEBUG
        return null;
    }

    private Apple createAppleInSparseFilledGrid() {
        Random random = new Random();
        Integer newWidth;
        Integer newHeight;
        do {
            newWidth = random.nextInt() % width;
            newHeight = random.nextInt() % height;
        } while (grid[newHeight][newWidth].getClass().equals(EmptyBlock.class));
        // (grid[i][j] == EmptyBlock) ^
        return new Apple(newWidth, newHeight);
    }

    public void addToGrid(GameObject newGameObject) {
        Integer x = newGameObject.getX();
        Integer y = newGameObject.getY();
        if (grid[y][x].getClass().equals(EmptyBlock.class) && !newGameObject.getClass().equals(EmptyBlock.class)) {
            emptyBlocksLeft--;
        } else if (!grid[y][x].getClass().equals(EmptyBlock.class)
                && newGameObject.getClass().equals(EmptyBlock.class)) {
            emptyBlocksLeft++;
        }
        grid[y][x] = newGameObject;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public GameObject[][] getGrid() {
        return grid;
    }

    public void setEmptyBlocksLeft(Integer emptyBlocksLeft) {
        this.emptyBlocksLeft = emptyBlocksLeft;
    }

    public Integer getEmptyBlocksLeft() {
        return emptyBlocksLeft;
    }

}
