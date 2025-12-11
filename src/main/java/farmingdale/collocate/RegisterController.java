package farmingdale.collocate;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static farmingdale.collocate.Main.contextFirebase;
import static farmingdale.collocate.Main.fstore;

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

            Map<String, Object> userData = new HashMap<>();
            userData.put("fName", newAppUser.getFirstName());
            userData.put("lName", newAppUser.getLastName());
            userData.put("org", newAppUser.getOrgName());
            CollectionReference users = fstore.collection("userInfo");
            users.document(newAppUser.getEmail()).set(userData).get();

        }
        catch (FirebaseAuthException e) {
            e.printStackTrace();
            System.out.println("Failed to create user!");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Failed to add user information!");
        }
        catch (ExecutionException e) {
            e.printStackTrace();
            System.out.println("Failed to add user information!");
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
