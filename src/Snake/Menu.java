package Snake;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Menu extends GridPane {
    Boolean pauseGame;
    Integer score;
    Label scoreLabel;
    public Menu(){
        initializeMenu();
    }

    public void initializeMenu(){
        score=0;
        pauseGame=false;

        Button restartButton = new Button("Restart");
        restartButton.setOnAction(event3 -> {
        });

        Button pauseButton = new Button("Pause");
        pauseButton.setOnAction(event3 -> {
            pauseGame = ! pauseGame;
        });

        scoreLabel = new Label(" Score: "+ score);
        Label infoLabel = new Label(" P - pause R - restart");

        this.add(restartButton,0,0);
        this.add(pauseButton,1, 0);
        this.add(scoreLabel,3,0);
        this.add(infoLabel,2,0);

        this.setHgap(2);
        this.setVgap(2);
    }
    public void updateScore(){
        scoreLabel.setText(" Score: "+ ++score);
    }
    public Boolean getPauseGame() {
        return pauseGame;
    }

    public void setPauseGame(Boolean pauseGame) {
        this.pauseGame = pauseGame;
    }
}
