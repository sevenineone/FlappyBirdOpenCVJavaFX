package game;

import java.util.ArrayList;

public class GameModel {
    private double lastY = 300;
    private ArrayList<Wall> walls = new ArrayList<>();
    private Bird bird;
    private Score scoreLabel = new Score();
    //-//
    private boolean gameOver = false;
    private int finalScore = 0;
    //-//
    private ArrayList<Double> coordinates = new ArrayList<>();
    private FaceDetection faceDetection = new FaceDetection();

    void init() {
        bird = new Bird();
        GenerateLevel generateLevel = new GenerateLevel();
        generateLevel.generate(walls);
        faceDetection.start();
        coordinates.add(300.0);
        coordinates.add(300.0);
        coordinates.add(300.0);
        coordinates.add(300.0);
    }

    public void initForTest() {
        bird = new Bird();
        GenerateLevel generateLevel = new GenerateLevel();
        generateLevel.generateForTest(walls);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    void update() {
        moveX();
        moveY();
        intersection();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void moveX() {
        bird.moveX(bird.getX() + 2);
    }

    //-//

    public void intersection() {
        if (bird.getY() < 0) bird.moveY(0);
        if (bird.getY() > 565) bird.moveY(565);
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
        coordinates.add(0, lastY);
        coordinates.remove(4);
    }

    //////////-FOR-TEST-////////////
    public void moveY(double Y) {
        bird.moveY(Y);
    }

    public void moveX(double X) {
        bird.moveX(X);
    }

    public double getBirdX() {
        return bird.getX();
    }

    public double getBirdY() {
        return bird.getY();
    }
//////////////////////////////////

    Bird getBird() {
        return bird;
    }

    ArrayList<Wall> getWalls() {
        return walls;
    }

    Score getScoreLabel() {
        return scoreLabel;
    }

    public boolean getGameOver() {
        return gameOver;
    }

    void setGameOverFalse() {
        gameOver = false;
    }

    int getFinalScore() {
        return finalScore;
    }

}
