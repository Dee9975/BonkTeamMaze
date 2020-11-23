import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

public class DataInputController {
    private Scene inputScene;

    public void setInputScene(Scene inputScene) {
        this.inputScene = inputScene;
    }

    public void openInputScene(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(inputScene);
    }
}
