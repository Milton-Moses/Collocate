package farmingdale.collocate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class HomeController {

    @FXML
    private Text GreetingText;

    // These names MUST match the fx:id tags in the FXML
    @FXML
    private ListView<String> recentProjectsList;
    @FXML
    private ListView<String> starredProjectsList;
    @FXML
    private ListView<String> recentClientsList;

    private TesterPerson currentUser;

    @FXML
    public void initialize() {
        GreetingText.setText("Loading...");
    }

    /**
     * Receives the logged-in user and populates the lists.
     */
    public void initData(TesterPerson user) {
        this.currentUser = user;

        if (this.currentUser != null) {
            // 1. Update Greeting
            GreetingText.setText("Hello " + currentUser.getUsername());

            // 2. Populate Recent Projects
            // We verify the list isn't null to avoid crashes
            if (currentUser.getRecentProjects() != null) {
                ObservableList<String> projects = FXCollections.observableArrayList(currentUser.getRecentProjects());
                recentProjectsList.setItems(projects);
            }

            // 3. Populate Recent Clients
            if (currentUser.getRecentClients() != null) {
                ObservableList<String> clients = FXCollections.observableArrayList(currentUser.getRecentClients());
                recentClientsList.setItems(clients);
            }

            // Starred Projects (Mock Data)
            if (currentUser.getStarredProjects() != null) {
                ObservableList<String> starredProjects = FXCollections.observableArrayList(currentUser.getStarredProjects());
                starredProjectsList.setItems(starredProjects);
            }
        }
    }
}