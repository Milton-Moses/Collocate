package farmingdale.collocate;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ProjectlistController {

    @FXML private ListView<String> allProjectsList;
    @FXML private ListView<String> starredProjectsList;
    @FXML private ListView<String> recentProjectsList;

    private TesterPerson currentUser;
    private MainMenuController mainController; // Reference to parent

    // Updated initData to accept the Main Controller
    public void initData(TesterPerson user, MainMenuController mainController) {
        this.currentUser = user;
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
        if (currentUser == null) return;

        if (currentUser.getAllProjects() != null) {
            allProjectsList.setItems(FXCollections.observableArrayList(currentUser.getAllProjects()));
        }
        if (currentUser.getRecentProjects() != null) {
            recentProjectsList.setItems(FXCollections.observableArrayList(currentUser.getRecentProjects()));
        }
        if (currentUser.getStarredProjects() != null) {
            starredProjectsList.setItems(FXCollections.observableArrayList(currentUser.getStarredProjects()));
        }
    }
}