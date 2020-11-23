
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;

public class MazeVisual extends Application {

    private static Maze setUp;
    private Cell[][] maze = setUp.getMaze();

    public static void main(String... args) {
        setUp = new Maze(10, 10);
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group g = new Group();
        int width = 10;
        int length = 10;
        Scene scene = new Scene(g, (width + 1) * 50, (length + 1) * 50, Color.WHITE);
        stage.setTitle("Labirints");
        stage.show();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                if (maze[i][j] != null && maze[i][j].getTop()) {
                    g.getChildren().add(drawTop(i, j));
                }
                if (maze[i][j] != null && maze[i][j].getBottom()) {
                    g.getChildren().add(drawBottom(i, j));
                }
                if (maze[i][j] != null && maze[i][j].getLeft()) {
                    g.getChildren().add(drawLeft(i, j));
                }
                if (maze[i][j] != null && maze[i][j].getRight()) {
                    g.getChildren().add(drawRight(i, j));
                }

            }
        }
//        Text text = new Text(62, 80, "Start");
//        Ellipse ellipse = new Ellipse(75, 75, 20, 20);
//        ellipse.setFill(Color.BLUE);
//        g.getChildren().add(ellipse);
//        g.getChildren().add(text);

        stage.setScene(scene);
        stage.show();
    }

    private Line drawTop(int x, int y) {
        Cell cell = maze[x][y];
        if (cell.getTop()) {
            Line top = new Line(50 * x, y * 50, (50 * x + 50), 50 * y);
            top.setStroke(Color.BLACK);
            return top;
        }
        return null;
    }

    private Line drawBottom(int x, int y) {
        Cell cell = maze[x][y];
        if (cell.getBottom()) {
            Line bottom = new Line(50 * x, (50 * y + 50), (50 * x + 50), (50 * y + 50));
            bottom.setStroke(Color.BLACK);
            return bottom;
        }
        return null;
    }

    private Line drawLeft(int x, int y) {
        Cell cell = maze[x][y];
        if (cell.getLeft()) {
            Line left = new Line(x * 50, y * 50, x * 50, (50 * y + 50));
            left.setStroke(Color.BLACK);
            return left;
        }
        return null;
    }

    private Line drawRight(int x, int y) {
        Cell cell = maze[x][y];
        if (cell.getRight()) {
            Line right = new Line((50 * x + 50), y * 50, (50 * x + 50), (50 * y + 50));
            right.setStroke(Color.BLACK);
            return right;
        }
        return null;
    }
}