package Snake;

import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class GameWindow extends BorderPane {

    private Color backgroundColor = Color.RED;
    private Color gridColor = Color.BLUE;
    private Grid grid;

    public GameWindow() {
        this.grid = new Grid(40, 40, gridColor);
        this.setCenter(grid);
        Menu menu = new Menu();
        this.setTop(menu);
    }

    public int getWindowHeight() {
        int menuHeight = 0; // TODO
        int blocksHeight = grid.getHeightInBlocks() * grid.getBlockSize();
        int gapsInHeight = grid.getHeightInBlocks() * grid.getGapSize();
        int gridHeight = blocksHeight + gapsInHeight;
        return menuHeight + gridHeight;
    }

    public int getWindowWidth() {
        int gapsInWidth = grid.getHeightInBlocks() * grid.getGapSize();
        int blocksWidth = grid.getWidthInBlocks() * grid.getBlockSize();
        return blocksWidth + gapsInWidth;
    }

    Color getBackgroundColor() {
        return backgroundColor;
    }

    void getBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    Color getBoardColor() {
        return gridColor;
    }

    void setBoardColor(Color gridColor) {
        this.gridColor = gridColor;
    }
}
