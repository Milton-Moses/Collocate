package farmingdale.collocate;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static farmingdale.collocate.Main.currUser;
import static farmingdale.collocate.Main.fstore;

public class ClientEditController {

    @FXML
    private TextField clientNameField;

    @FXML
    private CheckBox isCompanyCheck;

    @FXML
    private Button saveChangesButton;

    @FXML
    private ComboBox<String> projectDropdown;

    private Client viewedClient;

    public void getProjectsFromUser() {
        for (Project project : currUser.getAllProjectsAsSource()) {
            projectDropdown.getItems().add(project.getProjectName());
        }
    }
    public void getClientDetails(Client client) {
        getProjectsFromUser();
        viewedClient = client;
        clientNameField.setText(client.getName());
        isCompanyCheck.setSelected(client.isCompany());
        projectDropdown.setValue(client.getProjectName());
    }

    public void setupNewClient() {
        getProjectsFromUser();
        viewedClient = new Client();
        viewedClient.setName("");
        viewedClient.setIsCompany(false);
        viewedClient.setOwner(currUser.getEmail());
    }

    @FXML
    public void saveCurrClient() throws ExecutionException, InterruptedException {
        String oldName = viewedClient.getName();
        viewedClient.setIsCompany(isCompanyCheck.isSelected());
        viewedClient.setName(clientNameField.getText());
        if (currUser.getClientByName(viewedClient.getName()) != null) {
            currUser.getClientListAsSource().remove(currUser.getClientByName(viewedClient.getName()));

            Query query = fstore.collection("clientList").whereEqualTo("clientName", oldName);
            Map<String, Object> editClientData = new HashMap<>();
            editClientData.put("clientName", viewedClient.getName());
            editClientData.put("documentOwner", currUser.getEmail());
            editClientData.put("isCompany", viewedClient.isCompany());
            editClientData.put("projectName", viewedClient.getProjectName());
            ApiFuture<QuerySnapshot> querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();

            for (QueryDocumentSnapshot document : documents) {
                DocumentReference documentReference = document.getReference();
                ApiFuture<WriteResult> writeResult = documentReference.update(editClientData);
            }
        }
        else {
            viewedClient.setName(clientNameField.getText());
            viewedClient.setIsCompany(isCompanyCheck.isSelected());
            viewedClient.setProjectName(projectDropdown.getValue());

            Map<String, Object> newClientData = new HashMap<>();
            newClientData.put("clientName", viewedClient.getName());
            newClientData.put("documentOwner", currUser.getEmail());
            newClientData.put("isCompany", viewedClient.isCompany());
            newClientData.put("projectName", viewedClient.getProjectName());
            DocumentReference docRef = fstore.collection("clientList").add(newClientData).get();
            for (Project project : currUser.getAllProjectsAsSource()) {
                if (project.getProjectName().equals(projectDropdown.getValue())) {
                    project.getClients().add(new Client(viewedClient.getName(),
                            viewedClient.isCompany(),
                            viewedClient.getOwner(),
                            projectDropdown.getValue()
                    ));
                    break;
                }
            }
        }
        currUser.addClient(viewedClient);

        Stage stage = (Stage) saveChangesButton.getScene().getWindow();
        stage.close();
    }
}
