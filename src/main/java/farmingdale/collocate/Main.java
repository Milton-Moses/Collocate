package farmingdale.collocate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static Scene scene;
    public static Stage mainStage;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
        mainStage = stage;
        scene = new Scene(fxmlLoader.load(), 1080, 720);

        mainStage.setTitle("Collocate");
        mainStage.setScene(scene);
        mainStage.show();
    }
}
