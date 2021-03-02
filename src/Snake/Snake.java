package Snake;

import java.util.List;
import java.util.LinkedList;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Snake {

    private List<Rectangle> snakeBlocks;
    private Color snakeColor;

    public Snake(Color snakeColor) {
        snakeBlocks = new LinkedList<Rectangle>();
        this.snakeColor = snakeColor;
    }

}
