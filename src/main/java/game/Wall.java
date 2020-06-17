package game;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class Wall extends Pane {

    Wall(int height) {
        Rectangle rect = new Rectangle(40, height, Color.GREEN);
        rect.setStroke(Color.BLACK);
        rect.setStrokeWidth(3);
        getChildren().add(rect);
    }

    Bounds collision() {
        return getBoundsInParent();
    }

    void moveX(double x) {
        setTranslateX(x);
    }

    void moveY(double y) {
        setTranslateY(y);
    }

    double getX() {
        return getTranslateX();
    }
}
