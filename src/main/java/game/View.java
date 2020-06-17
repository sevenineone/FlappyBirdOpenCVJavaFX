package game;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

class View {

    private Stage gameStage = new Stage();
    private Scene sceneGame;
    private Scene sceneGameOver;
    private GameModel gameModel;
    private GenerateLevel generateLevel;

    void startView() {
        StartMenu startMenu = new StartMenu();
        Scene sceneMenu = startMenu.createStartMenu();
        sceneMenu.setOnKeyPressed(k -> {
            if (k.getCode() == KeyCode.SPACE) {
                gameModel = new GameModel();
                generateLevel = new GenerateLevel();
                sceneGame = generateLevel.generate(gameModel.walls, gameModel.bird, gameModel.scoreLabel);
                show(sceneGame);
                AnimationTimer animationTimer = new AnimationTimer() {
                    @Override
                    public void handle(long l) {
                        if (!gameModel.gameOver) {
                            gameModel.update(generateLevel);
                        } else if (!gameModel.viewOnce) {

                            GameOverMenu gameOverMenuModel = new GameOverMenu();
                            sceneGameOver = gameOverMenuModel.createGameOverMenu(gameModel.finalScore);
                            sceneGameOver.setOnKeyPressed(k -> {
                                if (k.getCode() == KeyCode.SPACE) {
                                    gameModel.gameOver = false;
                                    gameModel.viewOnce = false;
                                    show(sceneGame);
                                }
                            });
                            show(sceneGameOver);
                            gameModel.viewOnce = true;
                        }
                    }
                };
                animationTimer.start();
            }
        });
        show(sceneMenu);
    }

    private void show(Scene scene) {
        gameStage.setScene(scene);
        gameStage.show();
    }


}

