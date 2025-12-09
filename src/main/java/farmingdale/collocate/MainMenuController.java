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


    @FXML
    public void initialize() {
        HomeButton.setStyle(
                "-fx-background-color: linear-gradient(to bottom right, rgba(255, 255, 255, 0.8), rgba(255, 255, 255, 0.3));" +
                        "-fx-effect: dropshadow(three-pass-box, rgba(255, 255, 255, 0.4), 10, 0, 0, 0);"
        );
        System.out.println("Main Menu initialized: Home Button set to ON");

    }

    public void loadView(String fxmlPath) {
        try {
            // use a "/" at the start to search from the "resources" root folder
            URL fxmlUrl = getClass().getResource(fxmlPath);

            // DEBUGGING: Check if file was found
            if (fxmlUrl == null) {
                System.out.println("CRITICAL ERROR: FXML file not found at: " + fxmlPath);
                System.out.println("Check your resources folder structure!");
                return; // Stop here to prevent the crash
            }

            Parent newView = FXMLLoader.load(fxmlUrl);
            contentArea.getChildren().removeAll();
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
        clientListButton.setStyle(
                "-fx-background-color: linear-gradient(to bottom right, rgba(255, 255, 255, 0.8), rgba(255, 255, 255, 0.3));" +
                        "-fx-effect: dropshadow(three-pass-box, rgba(255, 255, 255, 0.4), 10, 0, 0, 0);"
        );
        projectListButton.setStyle(
                ""
        );
        HomeButton.setStyle(
                ""
        );

    }

    private void setProjectListButtonColorActive(){
        projectListButton.setStyle(
                "-fx-background-color: linear-gradient(to bottom right, rgba(255, 255, 255, 0.8), rgba(255, 255, 255, 0.3));" +
                        "-fx-effect: dropshadow(three-pass-box, rgba(255, 255, 255, 0.4), 10, 0, 0, 0);"
        );
        clientListButton.setStyle(
                ""
        );
        HomeButton.setStyle(
                ""
        );

    }

    private void setHomeButtonColorActive(){
        HomeButton.setStyle(
                "-fx-background-color: linear-gradient(to bottom right, rgba(255, 255, 255, 0.8), rgba(255, 255, 255, 0.3));" +
                        "-fx-effect: dropshadow(three-pass-box, rgba(255, 255, 255, 0.4), 10, 0, 0, 0);"
        );
        projectListButton.setStyle(
                ""
        );
        clientListButton.setStyle(
                ""
        );
    }


}