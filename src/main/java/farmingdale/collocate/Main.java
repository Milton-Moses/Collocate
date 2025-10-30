package farmingdale.collocate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Staging mainStage = new Staging("login-view.fxml", 1420, 1080);
        stage.setTitle("Collocate");
        stage.setScene(Staging.scene);
        stage.show();
    }
}
