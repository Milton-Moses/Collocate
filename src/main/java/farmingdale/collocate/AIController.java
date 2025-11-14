package farmingdale.collocate;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AIController {
    private String promptText = "";

    @FXML
    private TextField promptField;

    @FXML
    private Button returnButton;

    /**
     * Returns the user to the Calendar page.
     * @throws IOException Thrown if FXML file fails to load.
     * @author Aidan Rodriguez
     */
    @FXML
    private void returnToCalendar() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("calendar-view.fxml"));
        Main.scene = new Scene(fxmlLoader.load(), 1080, 720);
        Main.mainStage.setScene(Main.scene);
        Main.mainStage.show();
    }
}
