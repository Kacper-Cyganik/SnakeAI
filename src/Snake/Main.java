package Snake;

import javafx.application.Application;
import javafx.scene.Scene;

import javafx.scene.paint.Color;

import javafx.stage.Stage;

public class Main extends Application {

    Color backgroundColor = Color.BLACK;

    @Override
    public void start(Stage primaryStage) {
        Stage Window = new Stage();
        GameWindow gameWindow = new GameWindow();

        Window.setScene(
                new Scene(gameWindow, gameWindow.getWindowWidth(), gameWindow.getWindowHeight(), backgroundColor));
        Window.setTitle("Snake");
        Window.setResizable(false);

        Window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
