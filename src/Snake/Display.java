package Snake;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;
import java.util.Map;

public class Display extends GridPane {

    Map<Class, Color> dictionary;
    GameGrid grid;
    Integer rectSize = 16;
    Integer gapSize = 1;
    Rectangle[][] gridPaneRectangles; // DEBUG

    public Display(GameGrid grid) {
        this.dictionary = new HashMap<Class, Color>();
        this.grid = grid;
        initializeDictionaryWithColors();
        initializeDisplayedSquares();
        setGapsBetweenBlocks();
    }

    private void setGapsBetweenBlocks() {
        this.setHgap(gapSize);
        this.setVgap(gapSize);
    }

    private void initializeDictionaryWithColors() {
        dictionary.put(EmptyBlock.class, Color.BLUE);
        dictionary.put(SnakeBlock.class, Color.GREEN);
        dictionary.put(Apple.class, Color.RED);
    }

    private void initializeDisplayedSquares() {
        gridPaneRectangles = new Rectangle[grid.getHeight()][grid.getWidth()]; // DEBUG
        for (int i = 0; i < grid.getHeight(); i++) {
            for (int j = 0; j < grid.getWidth(); j++) {
                Rectangle currentRect = new Rectangle(rectSize, rectSize);
                Color currentColor = dictionary.get(grid.getGrid()[i][j].getClass());
                currentRect.setFill(currentColor);
                this.add(currentRect, i + 1, j + 1);
                gridPaneRectangles[i][j] = currentRect;
            }
        }
    }

    public void display() {
        for (int i = 0; i < grid.getHeight(); i++) {
            for (int j = 0; j < grid.getWidth(); j++) {
                Rectangle currentRect = gridPaneRectangles[i][j];
                if (doesColorHaveToBeChanged(currentRect, grid.getGrid()[i][j])) {
                    Color newCurrentColor = dictionary.get(grid.getGrid()[i][j].getClass());
                    currentRect.setFill(newCurrentColor);
                }
            }
        }
    }

    private Boolean doesColorHaveToBeChanged(Rectangle rectangle, GameObject respondingGameObject) {
        Color currentColor = (Color) rectangle.getFill();
        Color colorThatShouldBe = dictionary.get(respondingGameObject.getClass());
        return !currentColor.equals(colorThatShouldBe);
    }

    public void displayGameOver() {

    }

    public Map<Class, Color> getDictionary() {
        return dictionary;
    }

    public void setDictionary(Map<Class, Color> dictionary) {
        this.dictionary = dictionary;
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
