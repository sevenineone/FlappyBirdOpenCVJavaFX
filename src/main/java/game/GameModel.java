package game;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Random;

import static game.View.showScene;
import static game.GameOverMenuModel.createGameOverMenu;

class GameModel {
    private static Scene mainScene;
    private static Pane appRoot = new Pane();
    private Pane gameRoot = new Pane();
    ArrayList<Wall> walls = new ArrayList<>();
    private Bird bird = new Bird();
    private Score scoreLabel = new Score();
    //-//
    static boolean gameOver = false;
    private int finalScore = 0;
    //-// coordinates (заменю потом на стек) //-//
    static volatile int Y = 300;
    static int Y2 = 300;
    static int Y3 = 300;
    static int Y4 = 300;
    //-//---------------//-//
    static FaceDetection faceDetection;


    public void gameStart() {
        faceDetection = new FaceDetection();
        faceDetection.start();
        gameRoot.setPrefSize(600, 600);
        for (int i = 0; i < 100; i++) {
            int enter = (int) (Math.random() * 100 + 50); // 50 to 150
            int height = new Random().nextInt(600 - enter);
            Wall wall = new Wall(height);
            wall.setTranslateX(i * 350 + 600);
            wall.setTranslateY(0);
            walls.add(wall);
            Wall wall2 = new Wall(600 - enter - height);
            wall2.setTranslateX(i * 350 + 600);
            wall2.setTranslateY(height + enter);
            walls.add(wall2);
            gameRoot.getChildren().addAll(wall, wall2);
        }
        gameRoot.getChildren().add(bird);
        appRoot.getChildren().addAll(gameRoot, scoreLabel);
        mainScene = new Scene(appRoot, 600, 600);
        showGame();
        mainLoop();
    }

    public static void showGame() {
        showScene(mainScene);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void update() {
        moveX();
        intersectionX();
        moveY();
        intersectionY();
        bird.translateXProperty().addListener((ovs, old, newValue) -> {
            int offset = newValue.intValue();
            if (offset > 200) gameRoot.setLayoutX(-(offset - 200));
        });
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void moveX() {
        bird.setTranslateX(bird.getTranslateX() + 2);
    }

    //-//

    public void intersectionX() {
        for (Wall w : walls) {
            if (bird.getBoundsInParent().intersects(w.getBoundsInParent())) {
                if (bird.getTranslateX() + 20 == w.getTranslateX()) {
                    finalScore = scoreLabel.get();
                    gameOver = true;
                    bird.setTranslateX(0);
                    scoreLabel.setZero();
                    gameRoot.setLayoutX(0);
                    return;
                }
            }
            if (bird.getTranslateX() == w.getTranslateX()) {
                scoreLabel.update();
                return;
            }
        }
    }

    //-//

    public void moveY() {
        Double lastY = bird.getTranslateY();
        bird.setTranslateY((Y + Y2 + Y3 + Y4 + lastY) / 5);
        if (bird.getTranslateY() < 0) bird.setTranslateY(0);
        if (bird.getTranslateY() > 580) bird.setTranslateY(580);
        Y4 = Y3;
        Y3 = Y2;
        Y2 = Y;
    }

    //-//

    public void intersectionY() {
        for (Wall w : walls) {
            if (bird.getBoundsInParent().intersects(w.getBoundsInParent())) {
                finalScore = scoreLabel.get();
                scoreLabel.setZero();
                gameOver = true;
                bird.setTranslateX(0);
                gameRoot.setLayoutX(0);
                return;
            }
        }

    }

    public void mainLoop() {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (!gameOver) update();
                else {
                    showScene(createGameOverMenu(finalScore));
                }

            }
        };
        animationTimer.start();
    }


}
