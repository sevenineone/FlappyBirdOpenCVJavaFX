package game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;

public class Score extends Pane {
    private int score = 0;
    private Text scoreLabel;

    public Score() {
        scoreLabel = new Text("Score: " + score);
        scoreLabel.setFill(Color.GREEN);
        scoreLabel.setStroke(Color.BLACK);
        scoreLabel.setStrokeWidth(1);
        scoreLabel.setX(50);
        scoreLabel.setY(50);
        scoreLabel.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        Rectangle rect = new Rectangle(110, 25, Color.WHITE);
        rect.setStroke(Color.BLACK);
        rect.setStrokeWidth(2);
        rect.setX(48);
        rect.setY(30);
        getChildren().addAll(rect, scoreLabel);
    }

    void update() {
        score++;
        scoreLabel.setText("Score: " + score);
    }

    void setZero() {
        score = 0;
        scoreLabel.setText("Score: " + score);
    }

    int get() {
        return score;
    }
}
