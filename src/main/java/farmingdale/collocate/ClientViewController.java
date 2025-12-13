package farmingdale.collocate;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static farmingdale.collocate.Main.currUser;
import static farmingdale.collocate.Main.fstore;

public class ClientViewController {
    @FXML
    private Button newClientButton, editClientButton, deleteClientButton;

    @FXML
    private ListView<String> clientListView;

    private MainMenuController mainController;

    private static Client focusedClient;

    public void initData(User user, MainMenuController mainController) {
        Main.currUser = user;
        this.mainController = mainController;

        setupListener();
        populateList();
    }

    private void populateList() {
        clientListView.getItems().clear();
        if (currUser.getClientList() != null) {
            clientListView.setItems(FXCollections.observableArrayList(currUser.getClientList()));
        }
    }

    private void setupListener() {
        setupList(clientListView);
    }

    private void setupList(ListView<String> listView) {
        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && mainController != null) {
                focusedClient = currUser.getClientByName(newVal);
                listView.getSelectionModel().clearSelection();
                Platform.runLater(() -> {
                    listView.getSelectionModel().clearSelection();
                });
            }
        });
    }

    @FXML
    private void viewClient() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/farmingdale/collocate/ClientView/client-edit.fxml"));

        Scene newScene = new Scene(fxmlLoader.load(), 600, 400);
        Stage newStage = new Stage();
        newStage.setTitle("Edit Client");
        newStage.setScene(newScene);
        ClientEditController controller = fxmlLoader.getController();
        newStage.setOnShowing(e -> {
            controller.getClientDetails(focusedClient);
        });
        newStage.showAndWait();
        populateList();
    }

    @FXML
    private void newClient() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/farmingdale/collocate/ClientView/client-edit.fxml"));

        Scene newScene = new Scene(fxmlLoader.load(), 600, 400);
        Stage newStage = new Stage();
        newStage.setTitle("New Client");
        ClientEditController controller = fxmlLoader.getController();
        newStage.setOnShowing(e -> {
            controller.setupNewClient();
        });
        newStage.setScene(newScene);
        newStage.showAndWait();
        populateList();
    }

    @FXML
    private void deleteClient() throws ExecutionException, InterruptedException {
        currUser.getClientListAsSource().remove(focusedClient);

        ApiFuture<QuerySnapshot> clientsQuery = fstore.collection("clientList").get();
        QuerySnapshot projectsQuerySnapshot = clientsQuery.get();
        List<QueryDocumentSnapshot> clientDocuments = projectsQuerySnapshot.getDocuments();

        for (QueryDocumentSnapshot document : clientDocuments) {
            if (document.get("clientName").equals(focusedClient.getName())) {
                ApiFuture<WriteResult> eventToDelete = fstore.collection("clientList").document(document.getId()).delete();
            }
        }
        populateList();
    }
}
