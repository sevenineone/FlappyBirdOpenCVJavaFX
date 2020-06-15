package game;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

class StartMenuModel {

    static Scene createStartMenu() {
        Pane startPane = new Pane();
        Text startText = new Text("press SPACE to START");
        startText.setFill(Color.GREEN);
        startText.setStroke(Color.BLACK);
        startText.setStrokeWidth(1);
        startText.setX(60);
        startText.setY(300);
        startText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
        startPane.getChildren().add(startText);
        Scene scene = new Scene(startPane, 600, 600, Color.LIGHTBLUE);
        scene.setOnKeyPressed(k -> {
            if (k.getCode() == KeyCode.SPACE) {
                GameModel gm = new GameModel();
                startPane.getChildren().removeAll();
                gm.gameStart();
            }
        });
        return scene;
    }
}
