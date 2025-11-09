package farmingdale.collocate;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegisterController {
    @FXML
    private TextField fNameField, lNameField, orgField, passField, confirmPassField, emailField;

    @FXML
    private Button registerButton;

    @FXML
    private void goToLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
        Main.scene = new Scene(fxmlLoader.load(), 1420, 1080);
        Main.mainStage.setScene(Main.scene);
        Main.mainStage.show();
    }
}
