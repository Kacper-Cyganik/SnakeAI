package Snake;

import javafx.application.Application;
import javafx.scene.Scene;

import javafx.scene.paint.Color;

import javafx.stage.Stage;

public class Main extends Application {

    private Color backgroundColor = Color.BLACK;
    private Snake snake;

    @Override
    public void start(Stage primaryStage) {
        Stage Window = new Stage();
        GameWindow gameWindow = new GameWindow();

        Window.setScene(
                new Scene(gameWindow, gameWindow.getWindowWidth(), gameWindow.getWindowHeight(), backgroundColor));
        Window.setTitle("Snake");
        Window.setResizable(false);

        Window.show();
        snake = new Snake(grid);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
