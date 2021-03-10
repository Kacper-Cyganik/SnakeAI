package Snake;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;
import java.util.Map;

public class Display extends GridPane {

    Map<Class, Color> dictionary;

    GameGrid grid;
    Integer rectSize=16;
    public Display(GameGrid grid){

        this.dictionary = new HashMap<Class, Color>();
        this.grid = grid;
        InitializeDictColor();

        showAll();
    }

    public void showAll(){
        for(int i=0; i<grid.getHeight(); i++){
            for(int j=0; j<grid.getWidth(); j++){
                Rectangle currentRect = new Rectangle(rectSize,rectSize);
                Color currentColor = dictionary.get(grid.getGrid()[i][j].getClass());
                currentRect.setFill(currentColor);
                this.add(currentRect,i+1,j+1);
            }

        }
        this.setHgap(1);
        this.setVgap(1);
    }
    private void InitializeDictColor(){
        dictionary.put(EmptyBlock.class, Color.BLUE);
        dictionary.put(SnakeBlock.class, Color.GREEN);
        dictionary.put(Apple.class, Color.RED);

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
}
