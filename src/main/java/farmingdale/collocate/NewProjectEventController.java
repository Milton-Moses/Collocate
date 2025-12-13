package farmingdale.collocate;

import com.google.cloud.firestore.DocumentReference;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static farmingdale.collocate.Main.*;

public class NewProjectEventController {

    @FXML
    private Button createEventButton;

    @FXML
    private TextField nameField, dateField;

    @FXML
    private void generateEvent() throws ExecutionException, InterruptedException {
        Map<String, Object> newEventData = new HashMap<>();
        newEventData.put("associatedUser", currUser.getEmail());
        newEventData.put("date", LocalDate.parse(dateField.getText()).toString());
        newEventData.put("isCompleted", false);
        newEventData.put("projectName", currProject.getProjectName());
        newEventData.put("title", nameField.getText());
        DocumentReference docRef = fstore.collection("eventList").add(newEventData).get();
        currProject.getEvents().add(new Event(nameField.getText(),
                                              currProject.getProjectName(),
                                              LocalDate.parse(dateField.getText())));

        Stage stage = (Stage) createEventButton.getScene().getWindow();
        stage.close();
    }
}
