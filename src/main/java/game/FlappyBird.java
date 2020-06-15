package game;

import javafx.application.Application;
import javafx.stage.Stage;

import static game.StartMenuModel.createStartMenu;
import static game.View.showScene;

public class FlappyBird extends Application {


    @Override
    public void start(Stage stage) {
        showScene(createStartMenu());

    }


    public static void main(String[] args) {
        launch();
    }
}