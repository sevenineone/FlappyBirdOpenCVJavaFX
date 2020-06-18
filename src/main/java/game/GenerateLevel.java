package game;

import java.util.ArrayList;
import java.util.Random;


class GenerateLevel {


    void generate(ArrayList<Wall> walls, Bird bird, Score scoreLabel) {

        for (int i = 0; i < 100; i++) {
            int enter = (int) (Math.random() * 100 + 60); // 60 to 100
            int height = new Random().nextInt(600 - enter);
            Wall wall = new Wall(height);
            wall.moveX(i * 350 + 600);
            wall.moveY(0);
            walls.add(wall);
            Wall wall2 = new Wall(600 - enter - height);
            wall2.moveX(i * 350 + 600);
            wall2.moveY(height + enter);
            walls.add(wall2);
        }
        Wall wall = new Wall(600);
        wall.moveX(100 * 350 + 600);
        wall.moveY(0);
        walls.add(wall);

    }






}
