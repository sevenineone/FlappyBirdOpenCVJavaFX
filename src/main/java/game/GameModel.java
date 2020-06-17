package game;

import java.util.ArrayList;

class GameModel {
    private double lastY = 300;
    ArrayList<Wall> walls = new ArrayList<>();
    Bird bird;
    Score scoreLabel = new Score();
    //-//
    boolean gameOver = false;
    int finalScore = 0;
    boolean viewOnce = false;
    //-//
    private ArrayList<Double> coordinates = new ArrayList<>();
    FaceDetection faceDetection = new FaceDetection();

    GameModel() {
        faceDetection.start();
        bird = new Bird();
        coordinates.add(300.0);
        coordinates.add(300.0);
        coordinates.add(300.0);
        coordinates.add(300.0);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    void update(GenerateLevel generateLevel) {
        moveX();
        intersectionX(generateLevel);
        moveY();
        intersectionY(generateLevel);
        generateLevel.movePane(bird);
        bird.animation.play();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void moveX() {
        bird.moveX(bird.getX() + 2);
    }

    //-//

    private void intersectionX(GenerateLevel generateLevel) {
        for (Wall w : walls) {
            if (bird.intersects(w)) {
                if (bird.getX() + 20 == w.getX()) {
                    finalScore = scoreLabel.get();
                    gameOver = true;
                    bird.moveX(0);
                    scoreLabel.setZero();
                    generateLevel.moveZero();
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
        Double y = faceDetection.getY();
        if (y > 0 && y <= 600) {
            lastY = y;
        }
        bird.moveY((coordinates.get(0) + coordinates.get(1) + coordinates.get(2) + coordinates.get(3) + lastY) / 5);
        if (bird.getY() < 0) bird.moveY(0);
        if (bird.getY() > 580) bird.moveY(580);
        coordinates.add(0, lastY);
        coordinates.remove(4);
    }

    //-//

    private void intersectionY(GenerateLevel generateLevel) {
        for (Wall w : walls) {
            if (bird.intersects(w)) {
                finalScore = scoreLabel.get();
                scoreLabel.setZero();
                gameOver = true;
                bird.moveX(0);
                generateLevel.moveZero();
                return;
            }
        }

    }

}
