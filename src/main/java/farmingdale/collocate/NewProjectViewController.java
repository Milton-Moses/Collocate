package farmingdale.collocate;

import com.google.cloud.firestore.DocumentReference;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static farmingdale.collocate.Main.currUser;
import static farmingdale.collocate.Main.fstore;

public class NewProjectViewController {

    @FXML
    private Button createProjectButton;

    @FXML
    private TextField projectNameField;

    @FXML
    private void createNewProject() throws ExecutionException, InterruptedException {
        Project project = new Project();
        project.setProjectName(projectNameField.getText());

        Map<String, Object> newProjectData = new HashMap<>();
        newProjectData.put("projectName", project.getProjectName());
        newProjectData.put("documentOwner", currUser.getEmail());
        DocumentReference docRef = fstore.collection("projects").add(newProjectData).get();
        currUser.addProject(project);
        Stage stage = (Stage) createProjectButton.getScene().getWindow();
        stage.close();
    }
}
