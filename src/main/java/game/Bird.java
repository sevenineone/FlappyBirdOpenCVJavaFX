package game;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.File;

class Bird extends Pane {

    ////////////////////////////
    private final int count = 9;
    private final int columns = 3;
    private final int offsetX = 0;
    private final int offsetY = 0;
    private final int width = 40;
    private final int height = 35;
    SpriteAnimation animation;

    //////////////////////////////

    Bird() {
        File file = new File("BirdSprite.png");
        Image image = new Image(file.toURI().toString());
        ImageView imageView = new ImageView(image);
        imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        animation = new SpriteAnimation(imageView, Duration.millis(500), count, columns, offsetX, offsetY, width, height);
        setTranslateX(100);
        setTranslateY(300);
        getChildren().addAll(imageView);
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

    double getY() {
        return getTranslateY();
    }

    boolean intersects(Wall wall) {
        return getBoundsInParent().intersects(wall.collision());
    }


}

