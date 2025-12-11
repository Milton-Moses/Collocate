package farmingdale.collocate;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QuerySnapshot;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import static farmingdale.collocate.Main.fstore;

public class ProjectListController {

    @FXML private ListView<String> allProjectsList;
    @FXML private ListView<String> starredProjectsList;
    @FXML private ListView<String> recentProjectsList;

    //private TesterPerson currentUser;
    private MainMenuController mainController;


    public void initData(User user, MainMenuController mainController) {
        Main.currUser = user;
        this.mainController = mainController;

        setupListeners();
        populateLists();
    }

    private void setupListeners() {
        setupList(allProjectsList);
        setupList(starredProjectsList);
        setupList(recentProjectsList);
    }

    private void setupList(ListView<String> listView) {
        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && mainController != null) {
                mainController.openProject(newVal);
                Platform.runLater(() -> {
                    listView.getSelectionModel().clearSelection();
                });
            }
        });
    }

    private void populateLists() {
        if (Main.currUser == null) {
            System.out.println("ERROR: User not found.");
        }

        ApiFuture<QuerySnapshot> query = fstore.collection("projects").get();
        /*
        if (currentUser.getAllProjects() != null) {
            allProjectsList.setItems(FXCollections.observableArrayList(currentUser.getAllProjects()));
        }
        if (currentUser.getRecentProjects() != null) {
            recentProjectsList.setItems(FXCollections.observableArrayList(currentUser.getRecentProjects()));
        }
        if (currentUser.getStarredProjects() != null) {
            starredProjectsList.setItems(FXCollections.observableArrayList(currentUser.getStarredProjects()));
        }*/
    }
}