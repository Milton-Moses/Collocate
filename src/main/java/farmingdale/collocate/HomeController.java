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

    private TesterPerson currentUser;
    private MainMenuController mainController;

    @FXML
    public void initialize() {
        // We only set the loading text here.
        // We do NOT set up listeners yet because 'mainController' is still null.
        GreetingText.setText("Loading...");
    }

    /**
     * Called by MainMenuController to pass the User and the Controller reference.
     */
    public void initData(TesterPerson user, MainMenuController mainController) {
        this.currentUser = user;
        this.mainController = mainController;

        if (this.currentUser != null) {
            GreetingText.setText("Hello " + currentUser.getUsername());

            // 1. Populate the Lists
            if (currentUser.getRecentProjects() != null) {
                recentProjectsList.setItems(FXCollections.observableArrayList(currentUser.getRecentProjects()));
            }
            if (currentUser.getRecentClients() != null) {
                recentClientsList.setItems(FXCollections.observableArrayList(currentUser.getRecentClients()));
            }
            if (currentUser.getStarredProjects() != null) {
                starredProjectsList.setItems(FXCollections.observableArrayList(currentUser.getStarredProjects()));
            }

            // 2. Setup the Listeners (Now that mainController is valid)
            setupProjectListener(recentProjectsList);
            setupProjectListener(starredProjectsList);

            // Optional: Setup Client listener
            setupClientListener(recentClientsList);
        }
    }

    /**
     * Attaches a listener that uses the MainMenu controller to open the project.
     */
    private void setupProjectListener(ListView<String> listView) {
        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            // Check that we have a valid selection and the parent controller
            if (newVal != null && mainController != null) {

                // CORRECT: Ask the Main Menu to swap the content area
                mainController.openProject(newVal);

                // Clear selection so the user can click the same item again if they return
                listView.getSelectionModel().clearSelection();
            }
        });
    }

    private void setupClientListener(ListView<String> listView) {
        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                System.out.println("Go to client view for: " + newVal);
                // In the future: mainController.openClient(newVal);
                listView.getSelectionModel().clearSelection();
            }
        });
    }
}