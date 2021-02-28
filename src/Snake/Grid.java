package Snake;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Grid extends GridPane{



    private int width_in_blocks; //szerokosc w blokach
    private int height_in_blocks; //wysokosc w blokach
    private Rectangle [][] rect_arr;
    private int rect_width=16;
    private int rect_height=16;
    Color blue = Color.BLUE;
    Color green = Color.GREEN;

    public Grid(int width_in_blocks, int height_in_blocks){
        this.width_in_blocks=width_in_blocks;
        this.height_in_blocks=height_in_blocks;
        this.setHgap(1);
        this.setVgap(1);

        rect_arr = new Rectangle [height_in_blocks][width_in_blocks];
        System.out.println("lol\n");
        draw_grid();
    }

    public int getWidth_in_blocks() {
        return width_in_blocks;
    }

    public void setWidth_in_blocks(int width_in_blocks) {
        this.width_in_blocks = width_in_blocks;
    }

    public int getHeight_in_blocks() {
        return height_in_blocks;
    }

    public void setHeight_in_blocks(int height_in_blocks) {
        this.height_in_blocks = height_in_blocks;
    }


    public void draw_grid(){
        for(int i=0; i<height_in_blocks; i++){
            for(int j=0; j<width_in_blocks; j++){
                Rectangle rectangle = new Rectangle(rect_width, rect_height);
                rectangle.setFill(blue);
                rect_arr[i][j]=rectangle;
                this.add(rectangle, i+1, j+1);

            }
        }
        }
    }
