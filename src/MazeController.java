import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

public class MazeController {
    private Scene mazeScene;

    public void setMazeScene(Scene mazeScene) {
        this.mazeScene = mazeScene;
    }

    public void openInputScene(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(mazeScene);
    }
}
