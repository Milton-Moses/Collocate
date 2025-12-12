package farmingdale.collocate;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

import static farmingdale.collocate.Main.currUser;

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
                mainController.openProject(currUser.getProjectByName(newVal));
                listView.getSelectionModel().clearSelection();
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

        if (currUser.getAllProjects() != null) {
            allProjectsList.setItems(FXCollections.observableArrayList(currUser.getAllProjects()));
        }/*
        if (currentUser.getRecentProjects() != null) {
            recentProjectsList.setItems(FXCollections.observableArrayList(currentUser.getRecentProjects()));
        }
        if (currentUser.getStarredProjects() != null) {
            starredProjectsList.setItems(FXCollections.observableArrayList(currentUser.getStarredProjects()));
        }*/
    }

    @FXML
    private void createNewProject() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/farmingdale/collocate/ProjectList/new-project-view.fxml"));

        Scene newScene = new Scene(fxmlLoader.load(), 200, 150);
        Stage newStage = new Stage();
        newStage.setTitle("New Project");
        newStage.setScene(newScene);
        newStage.showAndWait();
        populateLists();
    }
}