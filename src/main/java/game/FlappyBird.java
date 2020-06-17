package game;

import javafx.application.Application;
import javafx.stage.Stage;

public class FlappyBird extends Application {


    @Override
    public void start(Stage stage) {
        View view = new View();
        view.startView();

    }

    public static void main(String[] args) {
        launch();
    }
}