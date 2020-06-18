package game;

import java.util.ArrayList;

class GameModel {
    private double lastY = 300;
    private ArrayList<Wall> walls = new ArrayList<>();
    private Bird bird;
    private Score scoreLabel = new Score();
    //-//
    boolean gameOver = false;
    int finalScore = 0;
    boolean viewOnce = false;
    //-//
    private ArrayList<Double> coordinates = new ArrayList<>();
    private FaceDetection faceDetection = new FaceDetection();

    GameModel() {
        bird = new Bird();
        GenerateLevel generateLevel = new GenerateLevel();
        generateLevel.generate(walls, bird, scoreLabel);
        faceDetection.start();
        coordinates.add(300.0);
        coordinates.add(300.0);
        coordinates.add(300.0);
        coordinates.add(300.0);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    void update() {
        moveX();
        moveY();
        intersection();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void moveX() {
        bird.moveX(bird.getX() + 2);
    }

    //-//

    private void intersection() {
        for (Wall w : walls) {
            if (bird.intersects(w)) {
                finalScore = scoreLabel.get();
                gameOver = true;
                bird.moveX(100);
                scoreLabel.setZero();
                return;
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


    Bird getBird() {
        return bird;
    }

    ArrayList<Wall> getWalls() {
        return walls;
    }

    Score getScoreLabel() {
        return scoreLabel;
    }


}
