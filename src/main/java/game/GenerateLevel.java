package game;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;


class GenerateLevel {


    private Pane appRoot = new Pane();
    private Pane gameRoot = new Pane();

    Scene generate(ArrayList<Wall> walls, Bird bird, Score scoreLabel) {
        gameRoot.setPrefSize(600, 600);
        for (int i = 0; i < 300; i++) {
            int enter = (int) (Math.random() * 100 + 60); // 50 to 150
            int height = new Random().nextInt(600 - enter);
            Wall wall = new Wall(height);
            wall.moveX(i * 350 + 600);
            wall.moveY(0);
            walls.add(wall);
            Wall wall2 = new Wall(600 - enter - height);
            wall2.moveX(i * 350 + 600);
            wall2.moveY(height + enter);
            walls.add(wall2);
            gameRoot.getChildren().addAll(wall, wall2);
        }
        gameRoot.getChildren().add(bird);
        appRoot.getChildren().addAll(gameRoot, scoreLabel);
        return new Scene(appRoot, 600, 600, Color.LIGHTBLUE);
    }

    void movePane(Bird bird) {
        bird.translateXProperty().addListener((ovs, old, newValue) -> {
            int offset = newValue.intValue();
            if (offset > 200) gameRoot.setLayoutX(-(offset - 200));
        });
    }

    void moveZero() {
        gameRoot.setLayoutX(0);
    }
}
