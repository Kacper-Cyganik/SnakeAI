package Snake;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Menu extends Pane {
    ImageView restartButtonIV;

    private final Image img_restart;
    Label score;
    public Menu(){

        img_restart = new Image("Snake/images/restartButton.png");

        //score = new Label();
        restartButtonIV = new ImageView(img_restart);
        restartButtonIV.setImage(img_restart);
        //restartButtonIV.setFitHeight(100);
        //restartButtonIV.setFitWidth(100);
        this.getChildren().add(restartButtonIV);

    }
}
