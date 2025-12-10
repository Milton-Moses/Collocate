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

    @FXML public VBox contentArea;
    @FXML private Button clientListButton;
    @FXML private Button HomeButton;
    @FXML private Button projectListButton;

    @FXML private HomeController homeViewController;

    @FXML
    public void initialize() {
        setHomeButtonColorActive();
    }

    /**
     * Specialized method to load a specific project into the content area.
     */
    public void openProject(String projectName) {
        try {
            URL fxmlUrl = getClass().getResource("/farmingdale/collocate/ProjectView/projectview.fxml");

            if (fxmlUrl == null) {
                fxmlUrl = getClass().getResource("/farmingdale/collocate/projectview.fxml");
            }

            if (fxmlUrl == null) {
                System.out.println("CRITICAL ERROR: Could not find projectview.fxml in any expected folder.");
                return;
            }

            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            Parent view = loader.load();

            ProjectViewController controller = loader.getController();
            controller.setProjectName(projectName);


            contentArea.getChildren().setAll(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadView(String fxmlPath) {
        try {
            URL fxmlUrl = getClass().getResource(fxmlPath);
            if (fxmlUrl == null) {
                System.out.println("ERROR: Missing FXML at " + fxmlPath);
                return;
            }

            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            Parent newView = loader.load();
            Object controller = loader.getController();
            /*
            if (controller instanceof HomeController) {
                ((HomeController) controller).initData(sessionUser, this);
            }
            else if (controller instanceof ProjectlistController) {
                ((ProjectlistController) controller).initData(sessionUser, this);
            }*/

            contentArea.getChildren().setAll(newView);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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