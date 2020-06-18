package game;

public class Score {
    private int score;

    public Score() {
        score = 0;
    }

    void update() {
        score++;
    }

    void setZero() {
        score = 0;
    }

    int get() {
        return score;
    }
}
