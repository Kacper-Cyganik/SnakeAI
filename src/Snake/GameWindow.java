package Snake;

import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameWindow extends BorderPane {
    Color backgroundColor = Color.RED;
    public GameWindow(){
        //Grid grid = new Grid(40,40);
        //this.setCenter(grid);

        Menu menu = new Menu();
        this.setRight(menu);
    }

}
