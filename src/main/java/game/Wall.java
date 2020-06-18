package game;

class Wall {

    private Double Y = 0.0;
    private Double X = 0.0;
    private Double H;
    private Double W;

    Wall(double height) {
        W = 40.0;
        H = height;
    }

    void moveX(double x) {
        X = x;
    }

    void moveY(double y) {
        Y = y;
    }

    double getX() {
        return X;
    }

    double getY() {
        return Y;
    }

    double getH() {
        return H;
    }
}
