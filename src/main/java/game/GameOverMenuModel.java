package game;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import static game.GameModel.showGame;

public class GameOverMenuModel {

    public static Scene createGameOverMenu(int score) {
        Pane gameOverPane = new Pane();
        Text gameOverText = new Text("GAME OVER! Press SPACE");
        gameOverText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
        gameOverText.setX(15);
        gameOverText.setY(300);
        Text finalScore = new Text("Your score: " + score);
        finalScore.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        finalScore.setY(400);
        finalScore.setX(180);
        gameOverPane.getChildren().addAll(gameOverText, finalScore);
        Scene scene = new Scene(gameOverPane, 600, 600);
        scene.setOnKeyPressed(k -> {
            if (k.getCode() == KeyCode.SPACE) {
                GameModel.gameOver = false;
                showGame();
            }
        });
        return scene;
    }

}
