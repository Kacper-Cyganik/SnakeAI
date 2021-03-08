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
        this.emptyBlocksLeft = height * width;
        initializeGridWithEmptyBlocks();
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
    }

    public Boolean createNewApple() {
        if (emptyBlocksLeft <= 0) {
            return false;
        }
        Random random = new Random();
        int newWidth;
        int newHeight;
        do {
            newWidth = random.nextInt() % width;
            newHeight = random.nextInt() % height;
        } while (grid[newHeight][newWidth].getClass().equals(EmptyBlock.class));
        // grid[i][j] == EmptyBlock
        grid[newHeight][newWidth] = new Apple(newWidth, newHeight);
        emptyBlocksLeft--;
        return true;
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
}
