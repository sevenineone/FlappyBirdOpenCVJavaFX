package game;

import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

class View {
    private final int count = 9;
    private final int columns = 3;
    private final int offsetX = 0;
    private final int offsetY = 0;
    private final int width = 40;
    private final int height = 35;
    private ImageView imageView;
    private SpriteAnimation animation;
    /////////////////////////////////////////
    private Stage gameStage;
    private Pane gameRoot;
    private Pane appRoot;
    private Scene sceneGame;
    private Scene sceneGameOver;
    private GameModel gameModel;
    private boolean viewOnce = false;

    void init() {
        gameRoot = new Pane();
        appRoot = new Pane();
        gameRoot.setPrefSize(600, 600);
        sceneGame = new Scene(appRoot, 600, 600, Color.LIGHTBLUE);
        gameStage = new Stage();
        File file = new File("BirdSprite.png");
        Image image = new Image(file.toURI().toString());
        imageView = new ImageView(image);
        imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));

        animation = new SpriteAnimation(imageView, Duration.millis(500), count, columns, offsetX, offsetY, width, height);
    }

    void startView() {
        StartMenu startMenu = new StartMenu();
        Scene sceneMenu = startMenu.createStartMenu();
        sceneMenu.setOnKeyPressed(k -> {
            if (k.getCode() == KeyCode.SPACE) {
                gameModel = new GameModel();
                gameModel.init();
                show(sceneGame);
                AnimationTimer animationTimer = new AnimationTimer() {
                    @Override
                    public void handle(long l) {
                        if (!gameModel.getGameOver()) {
                            gameModel.update();
                            rewrite();
                        } else if (!viewOnce) {
                            GameOverMenu gameOverMenuModel = new GameOverMenu();
                            sceneGameOver = gameOverMenuModel.createGameOverMenu(gameModel.getFinalScore());
                            sceneGameOver.setOnKeyPressed(k -> {
                                if (k.getCode() == KeyCode.SPACE) {
                                    gameModel.setGameOverFalse();
                                    viewOnce = false;
                                    gameRoot.setLayoutX(0);
                                    show(sceneGame);
                                }
                            });
                            show(sceneGameOver);
                            viewOnce = true;
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

    private void rewrite() {
        gameRoot.getChildren().clear();
        appRoot.getChildren().clear();

        for (Wall wall : gameModel.getWalls()) {
            Rectangle rect = new Rectangle(40, wall.getH(), Color.GREEN);
            rect.setStroke(Color.BLACK);
            rect.setStrokeWidth(3);
            rect.setTranslateX(wall.getX());
            rect.setTranslateY(wall.getY());
            gameRoot.getChildren().add(rect);
        }
        Bird bird = gameModel.getBird();

        imageView.setTranslateX(bird.getX());
        imageView.setTranslateY(bird.getY());
        animation.play();
        gameRoot.getChildren().add(imageView);

        imageView.translateXProperty().addListener((ovs, old, newValue) -> {
            int offset = newValue.intValue();
            if (offset > 200) gameRoot.setLayoutX(-(offset - 200));
        });

        Score score = gameModel.getScoreLabel();
        Text scoreLabel = new Text("Score: " + score.get());
        scoreLabel.setFill(Color.GREEN);
        scoreLabel.setStroke(Color.BLACK);
        scoreLabel.setStrokeWidth(1);
        scoreLabel.setX(50);
        scoreLabel.setY(50);
        scoreLabel.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        Rectangle rect = new Rectangle(110, 25, Color.WHITE);
        rect.setStroke(Color.BLACK);
        rect.setStrokeWidth(2);
        rect.setX(48);
        rect.setY(30);

        appRoot.getChildren().addAll(gameRoot, rect, scoreLabel);

    }


}

