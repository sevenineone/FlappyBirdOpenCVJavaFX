package game;

class Bird {

    ////////////////////////////

    private Double Y;
    private Double X;
    private Double H;
    private Double W;

    //////////////////////////////

    Bird() {
        X = 100.0;
        Y = 300.0;
        H = 35.0;
        W = 40.0;

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


    boolean intersects(Wall wall) {
        if ((Y <=wall.getY() + wall.getH()) && (Y + H> wall.getY()) && (X<=wall.getX() + 40) && (X + W >=wall.getX())) {
            return true;
        }
        else return false;

    }


}

