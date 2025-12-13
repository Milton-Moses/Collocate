package farmingdale.collocate;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.List;

import static farmingdale.collocate.Main.currUser;
import static farmingdale.collocate.Main.fstore;

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
                loadUserInfo();
                ApiFuture<QuerySnapshot> projectsQuery = fstore.collection("projects").get();
                ApiFuture<QuerySnapshot> clientsQuery = fstore.collection("clientList").get();
                ApiFuture<QuerySnapshot> eventsQuery = fstore.collection("eventList").get();
                try {
                    QuerySnapshot projectsQuerySnapshot = projectsQuery.get();
                    List<QueryDocumentSnapshot> projectDocuments = projectsQuerySnapshot.getDocuments();

                    QuerySnapshot clientsQuerySnapshot = clientsQuery.get();
                    List<QueryDocumentSnapshot> clientDocuments = clientsQuerySnapshot.getDocuments();

                    QuerySnapshot eventQuerySnapshot = eventsQuery.get();
                    List<QueryDocumentSnapshot> eventDocuments = eventQuerySnapshot.getDocuments();
                    for (QueryDocumentSnapshot pDocument : projectDocuments) {
                        if (pDocument.get("documentOwner").toString().equals(currUser.getEmail())) {
                            System.out.println("Found project belonging to " + currUser.getEmail());
                            Project newProject = new Project();
                            for (QueryDocumentSnapshot cDocument : clientDocuments) {
                                Client newClient = null;
                                if (cDocument.get("projectName").toString().equals(pDocument.get("projectName").toString())) {
                                    newClient = new Client(cDocument.get("clientName").toString(),
                                                           (boolean)cDocument.get("isCompany"),
                                                           cDocument.get("documentOwner").toString(),
                                                           cDocument.get("projectName").toString());
                                    currUser.addClient(newClient);
                                    newProject.addClient(newClient);
                                }
                            }

                            for (QueryDocumentSnapshot eDocument : eventDocuments) {
                                Event newEvent = null;
                                if (eDocument.get("projectName").toString().equals(pDocument.get("projectName").toString())) {
                                    newEvent = new Event(eDocument.get("title").toString(),
                                                         pDocument.getId().toString(),
                                                         LocalDate.parse(eDocument.get("date").toString()));
                                    newProject.addEvent(newEvent);
                                }
                            }
                            newProject.setProjectName(pDocument.get("projectName").toString());
                            currUser.addProject(newProject);
                        }
                    }
                    System.out.println("SUCCESS: User projects loaded successfully!");
                    loadMainMenu();
                }
                catch (Exception e) {
                    System.out.println("ERROR: Failed to load user projects!");
                }
            }
            else {
                throw new IOException("Firebase authentication failed with status code: " + statusCode);
            }
        }
        catch (Exception e) {
            showAlert("User not found", "User does not exist");
        }



    }

    private void loadUserInfo() {
        try {
            ApiFuture<QuerySnapshot> query = fstore.collection("userInfo").get();
            QuerySnapshot querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

            for (QueryDocumentSnapshot document : documents) {
                if (document.getId().toString().equals(usernameField.getText())) {
                    currUser = new User(document.get("fName").toString(),
                                        document.get("lName").toString(),
                                        document.getId().toString(),
                                        document.get("org").toString(),
                                        "hidden");
                }
            }
            if (currUser == null) {
                throw new RuntimeException();
            }
        }
        catch (Exception e) {
            System.out.println("ERROR: User failed to be applied to current session.");
        }
    }

    /**
     * Helper to load the main menu and PASS the user data to it.
     */
    private void loadMainMenu() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu/mainmenu.fxml"));
            Parent root = fxmlLoader.load();


            MainMenuController controller = fxmlLoader.getController();
            controller.initData(currUser);


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