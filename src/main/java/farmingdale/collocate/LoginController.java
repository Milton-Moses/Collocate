package farmingdale.collocate;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button registerButton, loginButton;

    @FXML
    private TextField usernameField, passField;

    @FXML
    private void goToRegister() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("register-view.fxml"));
        Main.scene = new Scene(fxmlLoader.load(), 1420, 1080);
        Main.mainStage.setScene(Main.scene);
        Main.mainStage.show();
    }

    @FXML
    private void goToCalendar() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("calendar-view.fxml"));
        Main.scene = new Scene(fxmlLoader.load(), 1420, 1080);
        Main.mainStage.setScene(Main.scene);
        Main.mainStage.show();
    }
}
