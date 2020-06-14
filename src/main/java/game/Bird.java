package game;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;


public class Bird extends Pane {
    Rectangle rect;

    ////////////////////////////
    //ImageView imageView;
    //private static final int count = 14;
    //private static final int columns = 5;
    //private static final int offsetX = 0;
    //private static final int offsetY = 0;
    //private static final int width = 56;
    //private static final int height = 62;
    //SpriteAnimation animation;
    //////////////////////////////

    public Bird() {//ImageView imageView
        //this.imageView = imageView;
        //this.imageView.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
        //animation = new SpriteAnimation(imageView, Duration.millis(500),count,columns,offsetX,offsetY,width,height);

        rect = new Rectangle(20, 20, Color.RED);
        setTranslateX(100);
        setTranslateY(300);
        // getChildren().addAll(imageView);
        getChildren().addAll(rect);
    }

}

