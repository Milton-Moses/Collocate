package farmingdale.collocate;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

public class HomeController {

    @FXML private Text GreetingText;
    @FXML private ListView<String> recentProjectsList;
    @FXML private ListView<String> starredProjectsList;
    @FXML private ListView<String> recentClientsList;


    private MainMenuController mainController;

    @FXML
    public void initialize() {
        GreetingText.setText("Loading...");
    }

    /**
     * Called by MainMenuController to pass the User and the Controller reference.
     */
    public void initData(MainMenuController mainController) {
        /*this.currentUser = user;
        this.mainController = mainController;

        if (this.currentUser != null) {
            GreetingText.setText("Hello " + currentUser.getUsername());

            if (currentUser.getRecentProjects() != null) {
                recentProjectsList.setItems(FXCollections.observableArrayList(currentUser.getRecentProjects()));
            }
            if (currentUser.getRecentClients() != null) {
                recentClientsList.setItems(FXCollections.observableArrayList(currentUser.getRecentClients()));
            }
            if (currentUser.getStarredProjects() != null) {
                starredProjectsList.setItems(FXCollections.observableArrayList(currentUser.getStarredProjects()));
            }

            setupProjectListener(recentProjectsList);
            setupProjectListener(starredProjectsList);

            // Optional: Setup Client listener
            setupClientListener(recentClientsList);
        }*/
    }

    /**
     * Attaches a listener that uses the MainMenu controller to open the project.
     */
    private void setupProjectListener(ListView<String> listView) {
        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && mainController != null) {
                mainController.openProject(newVal);
                listView.getSelectionModel().clearSelection();
            }
        });
    }

    private void setupClientListener(ListView<String> listView) {
        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                System.out.println("Go to client view for: " + newVal);
                listView.getSelectionModel().clearSelection();
            }
        });
    }
}