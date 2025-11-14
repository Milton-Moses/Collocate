package farmingdale.collocate;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

public class CalendarController {
    @FXML
    private Button aiButton, logoutButton, clientButton, newEventButton;

    @FXML
    private void logOut() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
        Main.scene = new Scene(fxmlLoader.load(), 1420, 1080);
        Main.mainStage.setScene(Main.scene);
        Main.mainStage.show();
    }
}
