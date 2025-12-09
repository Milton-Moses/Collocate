package farmingdale.collocate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ProjectlistController {

    @FXML
    private ListView<String> allProjectsList;

    @FXML
    private ListView<String> starredProjectsList;

    @FXML
    private ListView<String> recentProjectsList;

    private TesterPerson currentUser;

    public void initData(TesterPerson user) {
        this.currentUser = user;

        if (this.currentUser != null) {

            // --- CHANGE 1: Use getAllProjects() for the main list ---
            if (currentUser.getAllProjects() != null) {
                ObservableList<String> allProjects = FXCollections.observableArrayList(currentUser.getAllProjects());
                allProjectsList.setItems(allProjects);
            }

            // --- No changes below here (keeps using Recent list) ---
            if (currentUser.getRecentProjects() != null) {
                ObservableList<String> recentProjects = FXCollections.observableArrayList(currentUser.getRecentProjects());
                recentProjectsList.setItems(recentProjects);
            }

            // Starred Projects (Mock Data)
            if (currentUser.getStarredProjects() != null) {
                ObservableList<String> starredProjects = FXCollections.observableArrayList(currentUser.getStarredProjects());
                starredProjectsList.setItems(starredProjects);
            }
        }
    }
}