package farmingdale.collocate;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

/**
 * Controller for the calendar-view.fxml file, which serves as the main page for the application.
 * @author Aidan Rodriguez
 */
public class CalendarController {
    @FXML
    private Button aiButton, logoutButton, clientButton, newEventButton;

    /**
     * Returns the user to the Login page.
     * @throws IOException Thrown if FXML file fails to load.
     * @author Aidan Rodriguez
     */
    @FXML
    private void logOut() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
        Main.scene = new Scene(fxmlLoader.load(), 1080, 720);
        Main.mainStage.setScene(Main.scene);
        Main.mainStage.show();
    }

    /**
     * Changes current page to the AI view page.
     * @throws IOException Thrown if FXML file fails to load.
     * @author Aidan Rodriguez
     */
    @FXML
    private void goToAi() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ai-view.fxml"));
        Main.scene = new Scene(fxmlLoader.load(), 1080, 720);
        Main.mainStage.setScene(Main.scene);
        Main.mainStage.show();
    }
}
