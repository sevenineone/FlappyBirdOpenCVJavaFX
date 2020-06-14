package game;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class View {

    public static Stage gameStage = new Stage();

    public static void showScene(Scene scene){
        gameStage.setScene(scene);
        gameStage.show();
    }

}
