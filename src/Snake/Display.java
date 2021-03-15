package Snake;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;
import java.util.Map;

public class Display extends BorderPane {

    private Map<Class, Color> gameColors;
    private Map<Class, Color> gameOverColors;
    private GameGrid grid;
    private GridPane gridpane;
    private Integer rectSize = 16;
    private Integer gapSize = 1;
    private Menu menu;
    private Rectangle[][] gridPaneRectangles; // DEBUG

    public Display(GameGrid grid, Menu menu) {
        this.gameColors = new HashMap<Class, Color>();
        this.gameOverColors = new HashMap<Class, Color>();
        this.grid = grid;
        this.menu = menu;
        this.gridpane = new GridPane();
        setBlackBackGroundToGridPane();
        initializeGameColors();
        initializeGameOverColors();
        initializeDisplayedSquares();
        // setGapsBetweenBlocks();

        this.setCenter(gridpane);
        this.setTop(this.menu);
    }

    private void setBlackBackGroundToGridPane() {
        BackgroundFill background_fill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        this.gridpane.setBackground(background);
    }

    private void setGapsBetweenBlocks() {
        gridpane.setHgap(gapSize);
        gridpane.setVgap(gapSize);
    }

    private void initializeGameColors() {
        gameColors.put(EmptyBlock.class, Color.BLACK);
        gameColors.put(SnakeBlock.class, Color.GREEN);
        gameColors.put(AppleBlock.class, Color.WHITE);
    }

    private void initializeGameOverColors() {
        gameOverColors.put(EmptyBlock.class, Color.BLACK);
        gameOverColors.put(SnakeBlock.class, Color.RED);
        gameOverColors.put(AppleBlock.class, Color.DARKRED);
    }

    private void initializeDisplayedSquares() {
        gridPaneRectangles = new Rectangle[grid.getHeight()][grid.getWidth()]; // DEBUG
        for (int i = 0; i < grid.getHeight(); i++) {
            for (int j = 0; j < grid.getWidth(); j++) {
                Rectangle currentRect = new Rectangle(rectSize, rectSize);
                Color currentColor = gameColors.get(grid.getGrid()[i][j].getClass());
                currentRect.setFill(currentColor);
                gridpane.add(currentRect, j + 1, i + 1);
                gridPaneRectangles[i][j] = currentRect;
            }
        }
    }

    public void display() {
        for (int i = 0; i < grid.getHeight(); i++) {
            for (int j = 0; j < grid.getWidth(); j++) {
                Rectangle currentRect = gridPaneRectangles[i][j];
                if (doesColorHaveToBeChanged(currentRect, grid.getGrid()[i][j])) {
                    Color newCurrentColor = gameColors.get(grid.getGrid()[i][j].getClass());
                    currentRect.setFill(newCurrentColor);
                }
            }
        }
    }

    public void displayGameOver() {
        for (int i = 0; i < grid.getHeight(); i++) {
            for (int j = 0; j < grid.getWidth(); j++) {
                Rectangle currentRect = gridPaneRectangles[i][j];
                Color currentColor = gameOverColors.get(grid.getGrid()[i][j].getClass());
                currentRect.setFill(currentColor);
            }
        }
    }

    private Boolean doesColorHaveToBeChanged(Rectangle rectangle, GameObject respondingGameObject) {
        Color currentColor = (Color) rectangle.getFill();
        Color colorThatShouldBe = gameColors.get(respondingGameObject.getClass());
        return !currentColor.equals(colorThatShouldBe);
    }

    public Map<Class, Color> getGameColors() {
        return gameColors;
    }

    public void setGameColors(Map<Class, Color> gameColors) {
        this.gameColors = gameColors;
    }

    public Map<Class, Color> getGameOverColors() {
        return gameOverColors;
    }

    public void setGameOverColors(Map<Class, Color> gameOverColors) {
        this.gameOverColors = gameOverColors;
    }

    public Integer getRectSize() {
        return rectSize;
    }

    public void setRectSize(Integer rectSize) {
        this.rectSize = rectSize;
    }

    public Integer getGapSize() {
        return gapSize;
    }

    public void setGapSize(Integer gapSize) {
        this.gapSize = gapSize;
    }

}
