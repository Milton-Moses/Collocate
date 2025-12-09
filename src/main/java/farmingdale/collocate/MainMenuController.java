package farmingdale.collocate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;


import java.io.IOException;
import java.net.URL;

public class MainMenuController {

    @FXML
    public VBox contentArea;
    @FXML
    private Button clientListButton;
    @FXML
    private Button HomeButton;
    @FXML
    private Button projectListButton;

    private TesterPerson sessionUser;

    // CHANGE 1: Updated type to HomeViewController
    // Note: ensure your FXML <fx:include> has fx:id="homeView" for this to work!
    @FXML
    private HomeController homeViewController;


    @FXML
    public void initialize() {
        setHomeButtonColorActive();
        System.out.println("Main Menu initialized.");
    }

    public void initData(TesterPerson user) {
        this.sessionUser = user;
        System.out.println("MainMenu received user: " + user.getUsername());

        // DEBUG CHECK
        if (homeViewController == null) {
            System.out.println("CRITICAL ERROR: homeViewController is NULL. Check fx:id in FXML!");
        } else {
            System.out.println("SUCCESS: Passing data to embedded Home Controller.");
            homeViewController.initData(user);
        }
    }

    /**
     * Loads a new view and passes the User data to the new controller.
     */
    public void loadView(String fxmlPath) {

        try {
            URL fxmlUrl = getClass().getResource(fxmlPath);

            if (fxmlUrl == null) {
                System.out.println("CRITICAL ERROR: FXML file not found at: " + fxmlPath);
                return;
            }

            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            Parent newView = loader.load();

            Object controller = loader.getController();

            // CHANGE 3: Check for HomeViewController
            if (controller instanceof HomeController) {
                ((HomeController) controller).initData(sessionUser);
            }
            else if (controller instanceof ProjectlistController) {
                // Example for future controllers:
                ((ProjectlistController) controller).initData(sessionUser);
            }


            contentArea.getChildren().setAll(newView);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // --- Navigation Handlers ---

    @FXML
    private void handleClientListButtonClick(ActionEvent event) {
        setClientListButtonColorActive();
        loadView("clientList.fxml");
    }

    @FXML
    private void handleProjectListButtonClick(ActionEvent event) {
        setProjectListButtonColorActive();
        loadView("ProjectList/projectlist.fxml");
    }

    @FXML
    private void handleHomeButtonClick(ActionEvent event) {
        setHomeButtonColorActive();
        loadView("Home/home.fxml");
    }

    // --- Button Styling Helpers ---

    private void setClientListButtonColorActive(){
        resetButtonStyles();
        clientListButton.setStyle("-fx-background-color: linear-gradient(to bottom right, rgba(255, 255, 255, 0.8), rgba(255, 255, 255, 0.3)); -fx-effect: dropshadow(three-pass-box, rgba(255, 255, 255, 0.4), 10, 0, 0, 0);");
    }

    private void setProjectListButtonColorActive(){
        resetButtonStyles();
        projectListButton.setStyle("-fx-background-color: linear-gradient(to bottom right, rgba(255, 255, 255, 0.8), rgba(255, 255, 255, 0.3)); -fx-effect: dropshadow(three-pass-box, rgba(255, 255, 255, 0.4), 10, 0, 0, 0);");
    }

    private void setHomeButtonColorActive(){
        resetButtonStyles();
        HomeButton.setStyle("-fx-background-color: linear-gradient(to bottom right, rgba(255, 255, 255, 0.8), rgba(255, 255, 255, 0.3)); -fx-effect: dropshadow(three-pass-box, rgba(255, 255, 255, 0.4), 10, 0, 0, 0);");
    }

    private void resetButtonStyles() {
        clientListButton.setStyle("");
        projectListButton.setStyle("");
        HomeButton.setStyle("");
    }
}