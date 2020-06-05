package game;


import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Application;


import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Random;


public class FlappyBird extends Application{
    public static Pane appRoot = new Pane();
    public static Pane gameroot = new Pane();
    public static int score = 0;
    public Text scoreLabel = new Text("Score: " + score);
    public static Boolean gameOver = false;
    public static Boolean gameStart = false;

    public static ArrayList<Wall> walls = new ArrayList<>();
    Bird bird = new Bird();

    public Parent createContent(){
        gameroot.setPrefSize(600, 600);
        for (int i = 0; i < 100; i++) {
            int enter = (int) (Math.random()*100+50); // 50 to 150
            int height = new Random().nextInt(600 - enter);
            Wall wall = new Wall(height);
            wall.setTranslateX(i*350+600);
            wall.setTranslateY(0);
            walls.add(wall);
            Wall wall2 = new Wall(600 - enter - height);
            wall2.setTranslateX(i * 350 + 600);
            wall2.setTranslateY(height + enter);
            walls.add(wall2);
            gameroot.getChildren().addAll(wall, wall2);

        }
        gameroot.getChildren().add(bird);
        appRoot.getChildren().addAll(gameroot, scoreLabel);
        return appRoot;
    }

    public void update(){
        bird.moveX();
        bird.moveY();
        scoreLabel.setText("Score: " + score);
        scoreLabel.setX(50);
        scoreLabel.setY(50);
        scoreLabel.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        bird.translateXProperty().addListener((ovs,old, newValue)->{
            int offset = newValue.intValue();
            if(offset > 200) gameroot.setLayoutX(-(offset - 200));
        });


    }
    static Bird.FaceDetection faceDetection;

    @Override
    public void start(Stage stage) throws Exception{

        Pane startPane = new Pane();
        Text startText = new Text("pres SPACE to START");
        startText.setX(100);
        startText.setY(300);
        startText.setFont(new Font(45));
        startPane.getChildren().add(startText);
        Scene startScene = new Scene(startPane, 600,600);
        stage.setScene(startScene);
        stage.show();
        Scene scene = new Scene(createContent());
        startScene.setOnKeyPressed(k -> {

            if(k.getCode() == KeyCode.SPACE){
                stage.setScene(scene);
                stage.show();
                faceDetection = new Bird.FaceDetection();
                faceDetection.start();

                AnimationTimer animationTimer = new AnimationTimer() {
                    @Override
                    public void handle(long l) {
                        update();
                    }
                };
                animationTimer.start();
            }
        });


    }

    public static void main(String[] args) {
        launch(args);

    }

}
