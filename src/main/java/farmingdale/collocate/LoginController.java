package farmingdale.collocate;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
/**
 * Controller for the login-view.fxml.
 * @author Milton Moses
 */
public class LoginController {
    private HttpClient client;

    @FXML
    private Button registerButton, loginButton;

    @FXML
    private TextField usernameField, passField;

    @FXML
    private void initialize() {
        client = HttpClient.newBuilder().build();
    }

    /**
     * Brings the user to the Register page.
     */
    @FXML
    private void goToRegister() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register-view.fxml"));
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
        if (inputUser.isEmpty() || inputPass.isEmpty()) {
            showAlert("Login Error", "Please enter both username and password.");
            return;
        }
        try {
            String requestBody = String.format(
                    "{\"email\":\"%s\",\"password\":\"%s\",\"returnSecureToken\":true}",
                    inputUser, inputPass
            );

            System.out.println("DEBUG: HttpRequest: " + requestBody);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=AIzaSyCrw3S4uDwnOnS7mdJRRgbjpxbXbc2JknY"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();


            if (statusCode >= 200 && statusCode < 300) {
                System.out.println("Login Successful");
            }
            else {
                throw new IOException("Firebase authentication failed with status code: " + statusCode);
            }
        }
        catch (Exception e) {
            showAlert("User not found", "User does not exist");
        }

        //loadMainMenu(validUser);

    }

    /**
     * Helper to load the main menu and PASS the user data to it.
     */
    private void loadMainMenu() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu/mainmenu.fxml"));
            Parent root = fxmlLoader.load();


            MainMenuController controller = fxmlLoader.getController();
            //controller.initData(loggedInUser);


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