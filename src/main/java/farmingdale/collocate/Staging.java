package farmingdale.collocate;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Staging {
    public static Scene scene;
    public static Stage stage;

    public Staging() {
        scene = null;
    }

    public Staging(String filePath, int x, int y) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(filePath));
            scene = new Scene(fxmlLoader.load(), x, y);
        }
        catch (Exception e) {
            System.out.println("Failed to load stage.");
        }
    }
}
