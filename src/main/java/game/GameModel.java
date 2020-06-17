package game;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import static game.View.showScene;
import static game.GameOverMenuModel.createGameOverMenu;

class GameModel {
    private static Scene mainScene;
    private static Pane appRoot = new Pane();
    private Pane gameRoot = new Pane();
    private ArrayList<Wall> walls = new ArrayList<>();
    private Bird bird;
    private Score scoreLabel = new Score();
    //-//
    static boolean gameOver = false;
    private int finalScore = 0;
    static boolean viewOnce = false;
    //-// coordinates (заменю потом на стек) //-//
    static volatile int Y = 300;
    private static int Y2 = 300;
    private static int Y3 = 300;
    private static int Y4 = 300;

    GameModel() {

        bird = new Bird();
    }


    void gameStart() {
        //-//---------------//-//
        FaceDetection faceDetection = new FaceDetection();
        faceDetection.start();
        gameRoot.setPrefSize(600, 600);
        for (int i = 0; i < 100; i++) {
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
        mainScene = new Scene(appRoot, 600, 600, Color.LIGHTBLUE);
        showGame();
        mainLoop();
    }

    static void showGame() {
        showScene(mainScene);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void update() {
        if (!gameOver) {
            moveX();
            intersectionX();
            moveY();
            intersectionY();
            bird.translateXProperty().addListener((ovs, old, newValue) -> {
                int offset = newValue.intValue();
                if (offset > 200) gameRoot.setLayoutX(-(offset - 200));
            });
            bird.animation.play();
        } else if (!viewOnce) {
            showScene(createGameOverMenu(finalScore));
            viewOnce = true;
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void moveX() {
        bird.moveX(bird.getX() + 2);
    }

    //-//

    private void intersectionX() {
        for (Wall w : walls) {
            if (bird.intersects(w)) {
                if (bird.getX() + 20 == w.getX()) {
                    finalScore = scoreLabel.get();
                    gameOver = true;
                    bird.moveX(0);
                    scoreLabel.setZero();
                    gameRoot.setLayoutX(0);
                    return;
                }
            }
            if (bird.getX() == w.getX()) {
                scoreLabel.update();
                return;
            }
        }
    }

    //-//

    private void moveY() {
        double lastY = bird.getY();
        bird.moveY((Y + Y2 + Y3 + Y4 + lastY) / 5);
        if (bird.getY() < 0) bird.moveY(0);
        if (bird.getY() > 580) bird.moveY(580);
        Y4 = Y3;
        Y3 = Y2;
        Y2 = Y;
    }

    //-//

    private void intersectionY() {
        for (Wall w : walls) {
            if (bird.intersects(w)) {
                finalScore = scoreLabel.get();
                scoreLabel.setZero();
                gameOver = true;
                bird.moveX(0);
                gameRoot.setLayoutX(0);
                return;
            }
        }

    }

    private void mainLoop() {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();

            }
        };
        animationTimer.start();
    }


}
