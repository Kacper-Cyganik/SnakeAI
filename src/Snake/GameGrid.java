package Snake;

import java.util.Random;

public class GameGrid {

    private GameObject[][] grid;
    private Integer width;
    private Integer height;
    private Integer emptyBlocksLeft;
    private Boolean appleExists;

    GameGrid(Integer width, Integer height) {
        this.width = width;
        this.height = height;
        this.appleExists = false;
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
        if (appleExists) {
            return false;
        } else if (emptyBlocksLeft <= 0) {
            return false;
        }
        AppleBlock newApple;
        if (emptyBlocksLeft < 0.3 * width * height) {
            newApple = createAppleInFilledGrid();
        } else {
            newApple = createAppleInSparseFilledGrid();
        }
        addToGrid(newApple);
        appleExists = true;
        return true;
    }

    private AppleBlock createAppleInFilledGrid() {
        Random random = new Random();
        Integer randomWidth = random.nextInt(width);
        Integer randomHeight = random.nextInt(height);
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                Integer newWidth = (randomWidth + w) % width;
                Integer newHeight = (randomHeight + h) % height;
                if (grid[newHeight][newWidth].getClass().equals(EmptyBlock.class)) {
                    return new AppleBlock(newWidth, newHeight);
                }
            }
        }
        System.out.println("Critical logic error"); // DEBUG
        return null;
    }

    private AppleBlock createAppleInSparseFilledGrid() {
        Random random = new Random();
        Integer newWidth;
        Integer newHeight;
        do {
            newWidth = random.nextInt(width);
            newHeight = random.nextInt(height);
        } while (!grid[newHeight][newWidth].getClass().equals(EmptyBlock.class));
        // (grid[i][j] == EmptyBlock) ^
        return new AppleBlock(newWidth, newHeight);
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

    public Boolean getAppleExists() {
        return appleExists;
    }

    public void setAppleExists(Boolean appleExists) {
        this.appleExists = appleExists;
    }

    public void appleCollected() {
        appleExists = false;
    }

}
