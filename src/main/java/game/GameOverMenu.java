package game;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


class GameOverMenu {

    Scene createGameOverMenu(int score) {
        Pane gameOverPane = new Pane();
        Text gameOverText = new Text("GAME OVER! Press SPACE");
        gameOverText.setFill(Color.GREEN);
        gameOverText.setStroke(Color.BLACK);
        gameOverText.setStrokeWidth(1);
        gameOverText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
        gameOverText.setX(15);
        gameOverText.setY(300);
        Text finalScore = new Text("Your score: " + score);
        finalScore.setFill(Color.GREEN);
        finalScore.setStroke(Color.BLACK);
        finalScore.setStrokeWidth(1);
        finalScore.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        finalScore.setY(400);
        finalScore.setX(180);
        gameOverPane.getChildren().addAll(gameOverText, finalScore);
        Scene scene = new Scene(gameOverPane, 600, 600, Color.LIGHTBLUE);
        return scene;
    }

}
