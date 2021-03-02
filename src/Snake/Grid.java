package Snake;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Grid extends GridPane {

    private Rectangle[][] gameBoard;
    private int widthInBlocks;
    private int heightInBlocks;
    private Color gridColor;
    private final int blockSize = 16;
    private final int gapSize = 1;

    public Grid(int widthInBlocks, int heightInBlocks, Color gridColor) {
        this.widthInBlocks = widthInBlocks;
        this.heightInBlocks = heightInBlocks;
        this.gridColor = gridColor;
        this.initializeGameBoard();
    }

    private void initializeGameBoard() {
        this.gameBoard = new Rectangle[this.heightInBlocks][this.widthInBlocks];
        addBlocksToBoard();
        setGapsBetweenBlocks();
    }

    public void addBlocksToBoard() {
        for (int i = 0; i < heightInBlocks; i++) {
            for (int j = 0; j < widthInBlocks; j++) {
                Rectangle rectangle = new Rectangle(blockSize, blockSize);
                rectangle.setFill(gridColor);
                gameBoard[i][j] = rectangle;
                this.add(rectangle, i + 1, j + 1);
            }
        }
    }

    private void setGapsBetweenBlocks() {
        this.setHgap(gapSize);
        this.setVgap(gapSize);
    }

    public int getWidthInBlocks() {
        return widthInBlocks;
    }

    public void setWidthInBlocks(int widthInBlocks) {
        this.widthInBlocks = widthInBlocks;
    }

    public int getHeightInBlocks() {
        return heightInBlocks;
    }

    public void setHeightInBlocks(int heightInBlocks) {
        this.heightInBlocks = heightInBlocks;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public int getGapSize(){
        return gapSize;
    }

}
