package game;

import javafx.scene.Scene;
import javafx.stage.Stage;

class View {

    private static Stage gameStage = new Stage();

    static void showScene(Scene scene) {
        gameStage.setScene(scene);
        gameStage.show();
    }

}
