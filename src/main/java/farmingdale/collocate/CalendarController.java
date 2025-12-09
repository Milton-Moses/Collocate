package farmingdale.collocate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;

public class CalendarController {

    @FXML private Text monthYearText;
    @FXML private GridPane calendarGrid;
    @FXML private Button aiButton, logoutButton, clientButton, newEventButton;

    private YearMonth currentYearMonth;
    private String activeProjectName;

    @FXML
    public void initialize() {
        // Start on the current month
        currentYearMonth = YearMonth.now();
        populateCalendar(currentYearMonth);
    }

    private void populateCalendar(YearMonth yearMonth) {
        // 1. Update the Header text
        monthYearText.setText(yearMonth.getMonth().toString() + " " + yearMonth.getYear());

        // 2. Clear existing buttons from previous month
        calendarGrid.getChildren().clear();

        // 3. Get calendar details
        LocalDate calendarDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
        // Find out which day of week the 1st falls on (SUNDAY=0, MONDAY=1, etc if strictly configured, but Java is MON=1)
        // Adjusting so Sunday is column 0:
        int dayOfWeekOfFirst = calendarDate.getDayOfWeek().getValue() % 7; // Sunday becomes 0, Monday 1...
        int daysInMonth = yearMonth.lengthOfMonth();

        // 4. Loop to fill the grid
        int row = 0;
        int col = dayOfWeekOfFirst;

        for (int day = 1; day <= daysInMonth; day++) {
            // Create the visual element for the day
            Button dayButton = new Button(String.valueOf(day));
            dayButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); // Fill cell

            // Apply the 'Liquid Glass' style to each day cell
            dayButton.getStyleClass().add("calendar-day-button");

            // Highlight "Today"
            if (LocalDate.now().equals(LocalDate.of(yearMonth.getYear(), yearMonth.getMonth(), day))) {
                dayButton.getStyleClass().add("current-day");
            }

            // Add to grid
            calendarGrid.add(dayButton, col, row);

            // Calculate next cell position
            col++;
            if (col == 7) {
                col = 0;
                row++;
            }
        }
    }

    @FXML
    private void previousMonth() {
        currentYearMonth = currentYearMonth.minusMonths(1);
        populateCalendar(currentYearMonth);
    }

    @FXML
    private void nextMonth() {
        currentYearMonth = currentYearMonth.plusMonths(1);
        populateCalendar(currentYearMonth);
    }

    @FXML
    private void logOut() throws IOException {
        // Your existing navigation logic
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
        // Assuming Main.scene and Main.mainStage are static static accessible
        Main.scene = new Scene(fxmlLoader.load(), 1080, 720);
        Main.mainStage.setScene(Main.scene);
        Main.mainStage.show();
    }

    @FXML
    private void goToAi() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ai-view.fxml"));
        Main.scene = new Scene(fxmlLoader.load(), 1080, 720);
        Main.mainStage.setScene(Main.scene);
        Main.mainStage.show();
    }

    public void loadCalendarForProject(String projectName) {
        this.activeProjectName = projectName;
        System.out.println("Calendar initializing for project: " + projectName);

        // Optional: Hide the logout button since we are inside a tab
        if (logoutButton != null) {
            logoutButton.setVisible(false);
        }

        // TODO: Load events specifically for 'activeProjectName' from your database
        // populateCalendar(YearMonth.now()); // Re-run if your populate logic filters by project
    }
}