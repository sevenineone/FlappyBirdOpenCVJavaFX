package tests;

import game.GameModel;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


public class FlappyBirdTest {

    @Test
    public void test1() {
        double ans1 = 102;
        GameModel gameModel = new GameModel();
        gameModel.initForTest();
        gameModel.moveY(300);
        gameModel.moveX(100);
        gameModel.moveX();
        double res1 = gameModel.getBirdX();
        assertEquals(ans1, res1);
    }

    @Test
    public void test2() {
        double ans2 = 565;
        GameModel gameModel = new GameModel();
        gameModel.initForTest();
        gameModel.moveX(100);
        gameModel.moveY(566);
        gameModel.intersection();
        double res2 = gameModel.getBirdY();
        assertEquals(ans2, res2);
    }

    @Test
    public void test3() {
        double ans3 = 0;
        GameModel gameModel = new GameModel();
        gameModel.initForTest();
        gameModel.moveX(100);
        gameModel.moveY(-2);
        gameModel.intersection();
        double res3 = gameModel.getBirdY();
        assertEquals(ans3, res3);
    }

    @Test
    public void test4() {
        boolean ans4 = true;
        GameModel gameModel = new GameModel();
        gameModel.initForTest();
        gameModel.moveY(300);
        gameModel.moveX(599);
        gameModel.moveX();
        gameModel.moveX();
        gameModel.intersection();
        boolean res4 = gameModel.gameOver;
        assertEquals(ans4, res4);
    }

    @Test
    public void test5() {
        boolean ans5 = false;
        GameModel gameModel = new GameModel();
        gameModel.initForTest();
        gameModel.moveY(130);
        gameModel.moveX(599);
        gameModel.moveX();
        gameModel.intersection();
        boolean res5 = gameModel.gameOver;
        assertEquals(ans5, res5);
    }








}