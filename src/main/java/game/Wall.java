package game;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Wall extends Pane {
    Rectangle rect;

    public int height;
    public Wall(int height){
        this.height = height;
        rect = new Rectangle(40, height, Color.GREEN);
        rect.setStroke(Color.BLACK);
        rect.setStrokeWidth(3);
        DropShadow shadow = new DropShadow();
        rect.setEffect(shadow);
        getChildren().add(rect);
    }
}
