package farmingdale.collocate;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;

/**
 * Controller for the login-view.fxml file, serving as both the landing page for the application and allowing the user
 * to either login to an existing account or navigate to the Register page to create a new account.
 * @author Aidan Rodriguez
 */
public class LoginController {

    @FXML
    private Button registerButton, loginButton;

    @FXML
    private TextField usernameField, passField;

    /**
     * Brings the user to the Register page.
     * @throws IOException Thrown if FXML file fails to load.
     * @author Aidan Rodriguez
     */
    @FXML
    private void goToRegister() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("register-view.fxml"));
        Main.scene = new Scene(fxmlLoader.load(), 1080, 720);
        Main.mainStage.setScene(Main.scene);
        Main.mainStage.show();
    }

    /**
     * Brings the user to the Calendar page.
     * @throws IOException Thrown if FXML file fails to load.
     * @author Aidan Rodriguez
     */
    @FXML
    private void goToCalendar() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainMenu/mainmenu.fxml"));
        Main.scene = new Scene(fxmlLoader.load(), 1920, 1080);
        Main.mainStage.setScene(Main.scene);
        Main.mainStage.show();
    }
}
