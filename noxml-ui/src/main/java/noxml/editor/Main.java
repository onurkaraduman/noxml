package noxml.editor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Onur Karaduman
 * @since 03.11.17
 */
public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("sample.fxml"));

        Scene scene = new Scene(root, 700, 800);

        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();
    }
}
