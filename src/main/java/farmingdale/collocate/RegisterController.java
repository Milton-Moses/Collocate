package farmingdale.collocate;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

import static farmingdale.collocate.Main.contextFirebase;

/**
 * Controller for the register-view.fxml file, allowing the user to create a new account.
 * @author Aidan Rodriguez
 */
public class RegisterController {
    @FXML
    private TextField fNameField, lNameField, orgField, passField, confirmPassField, emailField;

    @FXML
    private Button registerButton, backButton;

    /**
     * Returns the user to the Login page.
     * @throws IOException Thrown if FXML file fails to load.
     * @author Aidan Rodriguez
     */
    @FXML
    private void registerUser() {
        try {
            User newAppUser = new User(fNameField.getText(),
                    lNameField.getText(),
                    emailField.getText(),
                    orgField.getText(),
                    passField.getText());

            UserRecord.CreateRequest newUser = new UserRecord.CreateRequest();
            newUser.setDisplayName(newAppUser.getFullName());
            newUser.setEmail(newAppUser.getEmail());
            newUser.setPassword(newAppUser.getPass());
            FirebaseAuth auth = FirebaseAuth.getInstance();
            auth.createUser(newUser);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to create user!");
        }
    }
    @FXML
    private void goToLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
        Main.scene = new Scene(fxmlLoader.load(), 1080, 720);
        Main.mainStage.setScene(Main.scene);
        Main.mainStage.show();
    }
}
