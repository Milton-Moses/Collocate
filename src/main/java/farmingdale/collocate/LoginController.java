package farmingdale.collocate;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Controller for the login-view.fxml.
 * Handles authentication (IEEE 4.1) and navigation.
 */
public class LoginController {

    @FXML
    private Button registerButton, loginButton;

    @FXML
    private TextField usernameField, passField;

    // Represents the "Database" of registered users
    private ArrayList<TesterPerson> registeredUsers;

    /**
     * Initialize is called automatically when the view loads.
     * We use this to load our dummy data from the DataSeeder.
     */
    @FXML
    public void initialize() {
        // Load 10 random users + the specific 'admin' account
        this.registeredUsers = DataSeeder.generateUsers(10);

        // Debugging: Print valid users to console so you know who to login as
        System.out.println("--- Valid Login Credentials ---");
        for (TesterPerson p : registeredUsers) {
            System.out.println("User: " + p.getUsername() + " | Pass: " + p.getPassword());
        }
    }



    /**
     * Brings the user to the Register page.
     */
    @FXML
    private void goToRegister() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register-view.fxml"));
        // Using getClass().getResource() is generally safer than Main.class in larger projects
        Main.scene = new Scene(fxmlLoader.load(), 1080, 720);
        Main.mainStage.setScene(Main.scene);
        Main.mainStage.show();
    }

    /**
     * Authenticates the user.
     */
    @FXML
    private void goToCalendar() {
        String inputUser = usernameField.getText();
        String inputPass = passField.getText();

        // 1. Validate Input (Basic empty check)
        if (inputUser.isEmpty() || inputPass.isEmpty()) {
            showAlert("Login Error", "Please enter both username and password.");
            return;
        }

        // 2. Search for the user in our "Database"
        TesterPerson validUser = null;
        for (TesterPerson user : registeredUsers) {
            // Using the verifyPassword method we added to TesterPerson
            if (user.getUsername().equals(inputUser) && user.verifyPassword(inputPass)) {
                validUser = user;
                break;
            }
        }

        // 3. Handle Result
        if (validUser != null) {
            System.out.println("Login Successful for: " + validUser.getUsername());
            loadMainMenu(validUser);
        } else {
            // Requirement 4.1.2 - Deny access
            showAlert("Login Failed", "Invalid username or password.");
        }
    }

    /**
     * Helper to load the main menu and PASS the user data to it.
     */
    private void loadMainMenu(TesterPerson loggedInUser) {
        try {
            // Note: Update path if your mainmenu.fxml is in a subfolder like /MainMenu/
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu/mainmenu.fxml"));
            Parent root = fxmlLoader.load();


            MainMenuController controller = fxmlLoader.getController();
            controller.initData(loggedInUser);


            Main.scene = new Scene(root, 1920, 1080);
            Main.mainStage.setScene(Main.scene);
            Main.mainStage.centerOnScreen(); // Good practice for large windows
            Main.mainStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("System Error", "Could not load the Main Menu.\nCheck console for FXML path errors.");
        }
    }

    /**
     * Utility method to display error alerts.
     */
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}